package life.majiang.community.interceptor;

import com.alibaba.fastjson.JSON;
import life.majiang.community.constant.RedisConstant;
import life.majiang.community.enums.AdPosEnum;
import life.majiang.community.mapper.UserInfoMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
import life.majiang.community.model.UserInfo;
import life.majiang.community.model.UserInfoExample;
import life.majiang.community.redis.RedisRepository;
import life.majiang.community.service.AdService;
import life.majiang.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by codedrinker on 2019/5/16.
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private AdService adService;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RedisRepository redisRepository;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //设置 context 级别的属性
        request.getServletContext().setAttribute("redirectUri", redirectUri);
        // 没有登录的时候也可以查看导航
        for (AdPosEnum adPos : AdPosEnum.values()) {
            request.getServletContext().setAttribute(adPos.name(), adService.list(adPos.name()));
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
//                    //user表，第三方登陆方式
//                    UserExample userExample = new UserExample();
//                    userExample.createCriteria()
//                            .andTokenEqualTo(token);
//                    List<User> users = userMapper.selectByExample(userExample);

//                    //userInfo表，通过查数据库的方式获取userInfo信息
//                    UserInfoExample userInfoExample = new UserInfoExample();
//                    userInfoExample.createCriteria()
//                            .andTokenEqualTo(token);
//                    List<UserInfo> users = userInfoMapper.selectByExample(userInfoExample);

//                    if (users.size() != 0) {
//                        HttpSession session = request.getSession();
//                        session.setAttribute("user", users.get(0));
////                        Long unreadCount = notificationService.unreadCount(users.get(0).getId());
//                        Long unreadCount = notificationService.unreadCount(Long.valueOf(String.valueOf(users.get(0).getId())));
//                        session.setAttribute("unreadCount", unreadCount);
//                    }

                    //通过redis取值的方式
                    Map<String,String> users =  redisRepository.getJedis().hgetAll(RedisConstant.UserInfo.REDIS_SESSION_KEY + ":" + token);
                    if (!CollectionUtils.isEmpty(users)){
                        HttpSession session = request.getSession();
                        session.setAttribute("user", JSON.parseObject(JSON.toJSONString(users),UserInfo.class));
                    }

                    break;
                }
            }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
