package life.majiang.community.filter;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import life.majiang.community.dto.ParamLogDTO;
import life.majiang.community.utils.AddrUtil;
import life.majiang.community.utils.ParamLogHolder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

//import org.springframework.beans.factory.annotation.Autowired;

/**
 * 打印参数日志过滤器
 *
 * @author mengjiaxing
 * @since 2020/7/27
 */
@Slf4j
@Component
@WebFilter(urlPatterns = "/*", filterName = "____requestWrapperFilter")
public class RequestWrapperFilter implements Filter {

//    @Autowired
//    private RedisRepository redisRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.SIMPLIFIED_CHINESE);
        MDC.put("filterRequestId", format.format(new Date())
                + "_" + IdUtil.fastSimpleUUID().substring(0, 8));
        try {
            RequestWrapper requestWrapper = null;
            ResponseWrapper responseWrapper = null;
            ParamLogDTO paramLogDTO = new ParamLogDTO();
            ParamLogHolder.setStartTimeMs(System.currentTimeMillis());
            if (servletRequest instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) servletRequest;
                requestWrapper = new RequestWrapper(request);
                String url = request.getRequestURI();
                String ip = AddrUtil.getRemoteAddr(request);
                Map<String, String[]> params = request.getParameterMap();
                Map<String, Object> param = new HashMap<>();
                params.forEach((k, v) -> {
                    if (v.length == 1) {
                        param.put(k, v[0]);
                    } else {
                        param.put(k, v);
                    }
                });


                Map<String, String> headers = new HashMap<>();
                Enumeration<String> headerEnum = request.getHeaderNames();
                while (headerEnum.hasMoreElements()) {
                    String headerName = headerEnum.nextElement();
                    headers.put(headerName, request.getHeader(headerName));
                }
                paramLogDTO.setUrl(url);
                paramLogDTO.setIp(ip);
                paramLogDTO.setRemoteHost(request.getRemoteHost());
                paramLogDTO.setRemoteAddr(request.getRemoteAddr());
                paramLogDTO.setParams(param);
                paramLogDTO.setHeaders(headers);
                if (null != requestWrapper.getJsonBody()) {
                    paramLogDTO.setBody(requestWrapper.getJsonBody());
                } else {
                    paramLogDTO.setBody(requestWrapper.getBody());
                }
            }
            if (servletResponse instanceof HttpServletResponse) {
                responseWrapper = new ResponseWrapper((HttpServletResponse) servletResponse);
            }
            ParamLogHolder.setParamLogDTO(paramLogDTO);
            if (requestWrapper == null) {
                if (responseWrapper == null) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    filterChain.doFilter(servletRequest, responseWrapper);
                }
            } else {
                if (responseWrapper == null) {
                    filterChain.doFilter(requestWrapper, servletResponse);
                } else {
                    filterChain.doFilter(requestWrapper, responseWrapper);
                }
            }
            if (null != responseWrapper) {
                byte[] bytes = responseWrapper.getBytes();
                //部分接口不打印日志的返回参数
//                List<Object> notPrintParamLogList = redisRepository.getList(CommonConstant.NOT_PRINT_PARAM_LOG_KEY, 0, -1);
                if (true) {
                    //只获取返回类型为application/json，application/xml，text/html，text/plain的数据
                    if (null != servletResponse.getContentType() &&
                            (servletResponse.getContentType().contains(MediaType.APPLICATION_JSON_VALUE) ||
                                    servletResponse.getContentType().contains(MediaType.APPLICATION_XML_VALUE) ||
                                    servletResponse.getContentType().contains(MediaType.TEXT_HTML_VALUE) ||
                                    servletResponse.getContentType().contains(MediaType.TEXT_PLAIN_VALUE))) {
                        String resp = new String(bytes, StandardCharsets.UTF_8);
                        JSONObject jsonResp = null;
                        try {
                            jsonResp = JSONObject.parseObject(resp);
                        } catch (Exception e) {

                        }
                        if (null != jsonResp) {
                            paramLogDTO.setResp(jsonResp);
                        } else {
                            paramLogDTO.setResp(resp);
                        }

                    }
                }
                //将数据 再写到 response 中
                servletResponse.getOutputStream().write(bytes);
                servletResponse.getOutputStream().flush();
                servletResponse.getOutputStream().close();
            }
            Long cosTimeMs = ParamLogHolder.getCosTimeMs();
            if (null != cosTimeMs) {
                paramLogDTO.setCosTimeMs(cosTimeMs);
            }
            ParamLogHolder.removeParamLogDTO();
            log.info("call api info: paramLogDTO={}", JSONObject.toJSONString(paramLogDTO));
        } finally {
            MDC.remove("filterRequestId");
            ParamLogHolder.removeStartTimeMs();
        }
    }

    @Override
    public void destroy() {

    }
}
