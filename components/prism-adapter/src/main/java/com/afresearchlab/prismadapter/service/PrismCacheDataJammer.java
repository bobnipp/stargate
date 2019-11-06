package com.afresearchlab.prismadapter.service;

import com.afresearchlab.prismadaptermodels.FullNom;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saic.prism.ws.coredataws.prismcoredataws.PrismNom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisException;

import java.util.Date;
import java.util.List;

@Service
public class PrismCacheDataJammer {

    Logger logger = LoggerFactory.getLogger(PrismCacheDataJammer.class);

    public final static String NOM_LIST_KEY = "nom_list";
    public final static String FULL_NOM_KEY = "full_noms";
    public final static String LAST_SAVE = "last_save";

    private JedisPool jedisPool;
    private PrismAggregateService prismAggregateService;
    private ObjectMapper objectMapper;

    public PrismCacheDataJammer(JedisPool jedisPool, PrismAggregateService prismAggregateService) {
        this.jedisPool = jedisPool;
        this.prismAggregateService = prismAggregateService;
        this.objectMapper = new ObjectMapper();
    }

    public void jamIt() {
        Jedis jedisClient = jedisPool.getResource();
        try {
            logger.info("Initiating data jamming...");

            final Transaction transaction = jedisClient.multi();
            List<PrismNom> nomList = prismAggregateService.getAllNominations();
            try {
                transaction.set(NOM_LIST_KEY, this.objectMapper.writeValueAsString(nomList));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            nomList.forEach((prismNom -> {
                FullNom nom = prismAggregateService.transformToFullNom(prismNom);
                try {
                    transaction.hset(FULL_NOM_KEY, prismNom.getId(), this.objectMapper.writeValueAsString(nom));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }));

            transaction.set(LAST_SAVE, Long.toString(System.currentTimeMillis()));

            transaction.exec();
            logger.info("Data jamming has completed successfully!");
        } catch (JedisException ex) {
            throw new RuntimeException(ex);
        } finally {
            jedisClient.close();
        }
    }

    public void deleteIt() {
        Jedis jedisClient = jedisPool.getResource();
        try {
            jedisClient.flushAll();
            jedisClient.flushDB();
        } catch (JedisException ex) {
            throw new RuntimeException(ex);
        } finally {
            jedisClient.close();
        }
    }
}
