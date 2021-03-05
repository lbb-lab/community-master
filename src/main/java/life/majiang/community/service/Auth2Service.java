package life.majiang.community.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.BeanProperty;
import life.majiang.community.constant.R;
import life.majiang.community.constant.RedisConstant;
import life.majiang.community.constant.ResultCode;
import life.majiang.community.constant.UserConstant;
import life.majiang.community.mapper.UserInfoMapper;
import life.majiang.community.model.UserInfo;
import life.majiang.community.model.UserInfoExample;
import life.majiang.community.redis.RedisRepository;
import life.majiang.community.utils.JsonUtils;
import life.majiang.community.utils.Md5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author: liu bin bin
 * @Date: 2021/2/19 13:49
 * @Description:
 */
@Service
public class Auth2Service {

    @Resource
    private UserInfoMapper userInfoMapper;

//    private

    /**
     * 校验用户名、手机号、或者邮箱是否存在该用户信息
     * @param params
     * @param type
     * @return
     */
    public boolean checkData(String params, Integer type) {

        UserInfoExample example = new UserInfoExample();

        UserInfoExample.Criteria criteria = example.createCriteria();

        if (UserConstant.CheckDataType.USERNAME.equals(type)){
            criteria.andUsernameEqualTo(params);
        }
        else if (UserConstant.CheckDataType.PHONE.equals(type)){
            criteria.andPhoneEqualTo(params);
        }
        else if (UserConstant.CheckDataType.EMAIL.equals(type)){
            criteria.andEmailEqualTo(params);
        }

        List<UserInfo> user = userInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(user)){
            return true;
        }
        return false;
    }

    public R register(UserInfo userInfo){

        if (StringUtils.isBlank(userInfo.getUsername())||
                StringUtils.isBlank(userInfo.getPassword())){
            return R.fail(ResultCode.USER_NAME_OR_PASSWORD_IS_NULL);
        }

        //校验数据是否重复
        //校验用户名
        boolean result = checkData(userInfo.getUsername(), UserConstant.CheckDataType.USERNAME);
        if(!result){
            return R.fail(ResultCode.USER_NAME_DUPLICATE);
        }
        //校验手机号
        result = checkData(userInfo.getPhone(), UserConstant.CheckDataType.PHONE);
        if(!result){
            return R.fail(ResultCode.USER_PHONE_DUPLICATE);
        }
        //校验邮箱
        result=checkData(userInfo.getEmail(), UserConstant.CheckDataType.EMAIL);
        if(!result){
            return R.fail(ResultCode.USER_EMAIL_DUPLICATE);
        }

        String passwordMd5 = Md5.genMd5(userInfo.getPassword());
        userInfo.setPassword(passwordMd5);

        Integer insert = userInfoMapper.insert(userInfo);

        return R.success(insert);

    }


    public R login(UserInfo user, HttpServletResponse response, HttpServletRequest request, RedisRepository redis) {
        if (StringUtils.isBlank(user.getUsername())||
                StringUtils.isBlank(user.getPassword())){
           return R.fail(ResultCode.USER_NAME_OR_PASSWORD_IS_NULL);
        }

        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());

        //查找用户信息
        List<UserInfo> list = userInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)){
            return R.fail(ResultCode.USER_UNREGISTER);
        }
        UserInfo userInfo = list.get(0);
        //校验密码
        if (!Md5.genMd5(user.getPassword()).equals(userInfo.getPassword())){
            return R.fail(ResultCode.PASSWORD_ERROR);
        }

        //生成token
        String token = UUID.randomUUID().toString();

        //更新token
        UserInfoExample example1 = new UserInfoExample();
        UserInfo userInfoToken = new UserInfo();
        BeanUtils.copyProperties(userInfo,userInfoToken);
        userInfoToken.setToken(token);
        userInfoMapper.updateByPrimaryKey(userInfoToken);


//        //信息写入redis—json字符串的形式
//        //key:REDIS_SESSION:{TOKEN}
//        //value:user转成json
//        jedis.set(RedisConstant.UserInfo.REDIS_SESSION_KEY + ":" + token, JsonUtils.toJson(userInfo));
//        //控制台打印redis的键值
//        System.out.println(RedisConstant.UserInfo.REDIS_SESSION_KEY + ":" + token);
//        //设置过期时间
//        jedis.expire(RedisConstant.UserInfo.REDIS_SESSION_KEY + ":" + token, RedisConstant.UserInfo.SESSION_EXPIRE);

        //信息写入redis - hash的方式
//        UserInfoExample example2 = new UserInfoExample();
//        UserInfoExample.Criteria criteria2 = example.createCriteria();
//        criteria2.andUsernameEqualTo(user.getUsername());
//        UserInfo user0 = userInfoMapper.selectByExample(example2).get(0);

        //密码设置null，防止泄露
        userInfoToken.setPassword(null);
        //信息写入redis - hash的方式
        Map map = JSON.parseObject(JSON.toJSONString(userInfoToken),Map.class);
        map.put("id",String.valueOf(userInfo.getId()));
        redis.getJedis().hmset(RedisConstant.UserInfo.REDIS_SESSION_KEY + ":" + token,map);
        System.out.println(RedisConstant.UserInfo.REDIS_SESSION_KEY + ":" + token);
        redis.getJedis().expire(RedisConstant.UserInfo.REDIS_SESSION_KEY + ":" + token, RedisConstant.UserInfo.SESSION_EXPIRE);

        //用户信息写入session
        userInfo.setToken(token);
        HttpSession session = request.getSession();
        session.setAttribute("user",userInfo);

        //写入cookie
        Cookie cookie = new Cookie("token",token);
        cookie.setMaxAge(RedisConstant.UserInfo.SESSION_EXPIRE);
        cookie.setPath("/");
        response.addCookie(cookie);

        return R.success(token);
    }


}
