package life.majiang.community.controller;

import life.majiang.community.constant.R;
import life.majiang.community.constant.RedisConstant;
import life.majiang.community.constant.ResultCode;
import life.majiang.community.model.UserInfo;
import life.majiang.community.redis.RedisRepository;
import life.majiang.community.service.Auth2Service;
import life.majiang.community.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: liu bin bin
 * @Date: 2021/2/19 10:18
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/auth2")
public class Auth2LoginController {

    @Autowired
    private Auth2Service auth2Service;

    @Autowired
    private RedisRepository redisRepository;
    /**
     * 登陆按钮跳转到login页面
     * @return
     */
    @GetMapping("/loginPage")
    public String loginPage(){
       return "login";
    }

    /**
     * 注册按钮跳转到register页面
     * @return
     */
    @GetMapping("/registerPage")
    public String registerPage(){
        return "register";
    }

    /**
     * 用户登录
     * @param userInfo
     * @param model
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/login")
    public String login(UserInfo userInfo, Model model, HttpServletRequest request, HttpServletResponse response){

        R result = auth2Service.login(userInfo,response,request,redisRepository);
        log.info("login log:{}", JsonUtils.toJson(result));

        if (!result.getCode().equals(ResultCode.SUCCESS.getCode())){
            model.addAttribute("message",result.getMsg());
            return "error";
        }

        return "redirect:/";
    }

    @PostMapping("/register")
    public String register(UserInfo userInfo, Model model){
        if (StringUtils.isBlank(userInfo.getUsername())|| StringUtils.isBlank(userInfo.getPassword())||
        StringUtils.isBlank(userInfo.getEmail())|| StringUtils.isBlank(userInfo.getPhone())){
            model.addAttribute("message", R.fail(ResultCode.REGISTER_INFO_SHORTAGE));

            return "error";
        }

        R result = auth2Service.register(userInfo);
        if (!result.getCode().equals(ResultCode.SUCCESS.getCode())) {
            model.addAttribute("message", auth2Service.register(userInfo).getMsg());
            return "error";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){

        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        //删除redis中的token信息缓存
        redisRepository.getJedis().del(RedisConstant.UserInfo.REDIS_SESSION_KEY + ":" + userInfo.getToken());

        //删除页面session中的user缓存和cookie缓存
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

//    @RequestMapping("/check/{param}/{type}")
//    @ResponseBody
//    public R checkData(@PathVariable String params,
//                       @PathVariable Integer type,
//                       String callback){
//
//        R result = auth2Service.checkData(params,type);
//
//
//
//
//
//    }



}
