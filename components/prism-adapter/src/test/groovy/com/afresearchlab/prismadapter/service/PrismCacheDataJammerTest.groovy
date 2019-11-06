package com.afresearchlab.prismadapter.service

import com.afresearchlab.prismadaptermodels.FullNom
import com.fasterxml.jackson.databind.ObjectMapper
import com.saic.prism.ws.coredataws.prismcoredataws.PrismNom
import com.saic.prism.ws.coredataws.prismcoredataws.PrismNomIMINT
import com.saic.prism.ws.coredataws.prismcoredataws.PrismNomStatus
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import redis.clients.jedis.Transaction
import spock.lang.Specification

class PrismCacheDataJammerTest extends Specification {

    def mapper

    def setup() {
        mapper = new ObjectMapper()
    }

    def 'inserts nominations list into cache'() {
        given:
        PrismNomIMINT prismNomIMINT = new PrismNomIMINT()
        prismNomIMINT.status = PrismNomStatus.APPROVE

        PrismNom nom1 = new PrismNom()
        nom1.id = 'id1'
        nom1.name = 'name1'
        nom1.nomImint = prismNomIMINT

        PrismNom nom2 = new PrismNom()
        nom2.id = 'id2'
        nom2.name = 'name2'
        nom2.nomImint = prismNomIMINT

        def fullNom1 = FullNom.builder()
            .build()
        def fullNom2 = FullNom.builder()
            .build()

        def jedisPool = Mock(JedisPool)
        def jedisClient = Mock(Jedis)
        1 * jedisPool.getResource() >> jedisClient
        def transaction = Mock(Transaction)
        def prismAggregateService = Mock(PrismAggregateService)
        def subject = new PrismCacheDataJammer(jedisPool, prismAggregateService)

        when:
        subject.jamIt()

        then:
        1 * prismAggregateService.getAllNominations() >> [nom1, nom2]
        1 * prismAggregateService.transformToFullNom(nom1) >> fullNom1
        1 * prismAggregateService.transformToFullNom(nom2) >> fullNom2

        1 * jedisClient.multi() >> transaction

        1 * transaction.set('nom_list', mapper.writeValueAsString([nom1, nom2]))
        1 * transaction.hset('full_noms', 'id1', mapper.writeValueAsString(fullNom1))
        1 * transaction.hset('full_noms', 'id2', mapper.writeValueAsString(fullNom2))
        1 * transaction.exec()
    }

    def 'deletes the cache'() {
        given:
        def jedisPool = Mock(JedisPool)
        def jedisClient = Mock(Jedis)
        1 * jedisPool.getResource() >> jedisClient
        def prismAggregateService = Mock(PrismAggregateService)
        def subject = new PrismCacheDataJammer(jedisPool, prismAggregateService)

        when:
        subject.deleteIt()

        then:
        1 * jedisClient.flushAll()
        1 * jedisClient.flushDB()
    }
}
