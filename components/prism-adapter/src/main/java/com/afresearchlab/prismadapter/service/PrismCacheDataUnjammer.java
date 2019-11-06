package com.afresearchlab.prismadapter.service;

import com.afresearchlab.prismadaptermodels.FullNom;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saic.prism.ws.coredataws.prismcoredataws.PrismNom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.afresearchlab.prismadapter.service.PrismCacheDataJammer.FULL_NOM_KEY;
import static com.afresearchlab.prismadapter.service.PrismCacheDataJammer.NOM_LIST_KEY;

@Service
public class PrismCacheDataUnjammer {

    private Logger logger = LoggerFactory.getLogger(PrismCacheDataJammer.class);

    private JedisPool jedisPool;
    private ObjectMapper objectMapper;

    public PrismCacheDataUnjammer(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
        this.objectMapper = new ObjectMapper();
    }

    public List<PrismNom> getAllNominations() {
        Jedis jedisClient = jedisPool.getResource();
        try {
            return Optional.ofNullable(jedisClient.get(NOM_LIST_KEY)).map(listString -> {
                try {
                    return (List<PrismNom>) objectMapper.readValue(listString, new TypeReference<List<PrismNom>>() {});
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).orElse(Collections.emptyList());
        } catch (JedisException ex) {
            throw new RuntimeException(ex);
        } finally {
            jedisClient.close();
        }
    }

    public FullNom getNomination(String id) {
        Jedis jedisClient = jedisPool.getResource();
        try {
            String nomAsString = jedisClient.hget(FULL_NOM_KEY, id);
            try {
                return objectMapper.readValue(nomAsString, FullNom.class);
            } catch (Exception e) {
                logger.error("Failed to retrieve nomination with id: " + id);
                throw new RuntimeException(e);
            }
        } catch (JedisException ex) {
            throw new RuntimeException(ex);
        } finally {
            jedisClient.close();
        }
    }
}
