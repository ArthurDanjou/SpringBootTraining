package fr.arthurdanjou.springboottraining.database.redis;

import fr.arthurdanjou.springboottraining.SpringBoot;
import fr.arthurdanjou.springboottraining.config.file.RedisConfig;
import lombok.Getter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.List;
import java.util.Set;

@Getter
public class RedisConnector {

    private SpringBoot main;
    private JedisPool jedisPool;
    private Jedis jedis;

    public RedisConnector(SpringBoot main, RedisConfig redisConfig) {
        this.main = main;
        JedisPoolConfig config = new JedisPoolConfig();
        this.jedisPool = new JedisPool(config, redisConfig.getHost(), redisConfig.getPort(), 300, redisConfig.getPassword());
        this.jedis = this.getJedis();
        if (jedis != null) {
            main.getLogger().info("Connecté à Redis !");
        }
    }

    public void shutdown() {
        this.jedisPool.close();
        this.jedis.close();
        this.jedis = null;
    }

    public void set(String key, String value) {
        this.jedis.set(key, value);
    }

    public String get(String key) {
        return this.jedis.get(key);
    }

    public Jedis getJedis() {
        return this.jedisPool.getResource();
    }

    public Pipeline pipelined() {
        return this.jedisPool.getResource().pipelined();
    }

    public List<Object> getValues(List<String> keys) {
        Pipeline pipeline = this.pipelined();
        keys.forEach(pipeline::get);
        return pipeline.syncAndReturnAll();
    }

    public List<Object> getAll() {
        Pipeline pipeline = this.pipelined();
        Set<String> keys = this.jedis.keys("'*'");
        keys.forEach(pipeline::get);
        return pipeline.syncAndReturnAll();
    }

}
