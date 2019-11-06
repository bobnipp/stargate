package com.afresearchlab.prismadapter.controller;

import com.afresearchlab.prismadapter.model.HealthCheck;
import com.afresearchlab.prismadapter.service.PrismCacheDataJammer;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

@RestController
public class HealthController {

    private JedisPool jedisPool;

    public HealthController(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @GetMapping
    @RequestMapping("/healthcheck")
    public HealthCheck getHealthCheck() {
        Jedis jedisClient = jedisPool.getResource();
        try {
            String reply = jedisClient.ping();

            if (StringUtils.isEmpty(reply)) {
                return new HealthCheck(false, -1);
            }

            String lastSave = jedisClient.get(PrismCacheDataJammer.LAST_SAVE);
            long lLastSave = -1L;
            if (lastSave != null) {
                try {
                    lLastSave = System.currentTimeMillis() - Long.parseLong(lastSave);
                } catch(NumberFormatException ex) {
                }
            }
            return new HealthCheck(true, lLastSave);
        } catch (JedisException ex) {
            return new HealthCheck(false, -1);
        } finally {
            jedisClient.close();
        }
    }
}
