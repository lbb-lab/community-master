package life.majiang.community.constant;

/**
 * @author: liu bin bin
 * @Date: 2021/2/20 15:59
 * @Description:
 */
public interface RedisConstant {

    interface UserInfo{
        Integer SESSION_EXPIRE = 60 * 30;

        String REDIS_SESSION_KEY = "USER_LOGIN_SESSION_KEY";
    }



}
