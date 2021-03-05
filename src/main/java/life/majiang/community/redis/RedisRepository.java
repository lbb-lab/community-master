package life.majiang.community.redis;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @author: liu bin bin
 * @Date: 2021/3/4 17:50
 * @Description:
 */
@Component
public class RedisRepository {
    private Jedis jedis = new Jedis("localhost",6379);

    public Jedis getJedis() {
        return jedis;
    }
}
