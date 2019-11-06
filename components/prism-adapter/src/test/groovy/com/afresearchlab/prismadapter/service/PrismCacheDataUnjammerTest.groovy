package com.afresearchlab.prismadapter.service

import com.afresearchlab.prismadaptermodels.FullNom
import com.fasterxml.jackson.databind.ObjectMapper
import com.saic.prism.ws.coredataws.prismcoredataws.PrismNom
import com.saic.prism.ws.coredataws.prismcoredataws.PrismNomIMINT
import com.saic.prism.ws.coredataws.prismcoredataws.PrismNomStatus
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import spock.lang.Specification

class PrismCacheDataUnjammerTest extends Specification {

    def 'getNominations pulls the list of noms from the cache'() {
        given:
        PrismNomIMINT prismNomIMINT = new PrismNomIMINT()
        prismNomIMINT.status = PrismNomStatus.APPROVE
        def objectMapper = new ObjectMapper()

        PrismNom nom1 = new PrismNom()
        nom1.id = 'id1'
        nom1.key = 'key1'
        nom1.name = 'name1'
        nom1.nomImint = prismNomIMINT

        PrismNom nom2 = new PrismNom()
        nom2.id = 'id2'
        nom2.key = 'key2'
        nom2.name = 'name2'
        nom2.nomImint = prismNomIMINT
        def nomsString = objectMapper.writeValueAsString([nom1, nom2])

        def jedisPool = Mock(JedisPool)
        def jedisClient = Mock(Jedis)
        def subject = new PrismCacheDataUnjammer(jedisPool)
        1 * jedisPool.getResource() >> jedisClient

        when:
        def result = subject.getAllNominations()

        then:
        1 * jedisClient.get("nom_list") >> nomsString
        result.id == ['id1', 'id2']
        result.key == ['key1', 'key2']
    }

    def 'getNominations returns an empty list if the cache is empty'() {
        given:
        def jedisPool = Mock(JedisPool)
        def jedisClient = Mock(Jedis)
        def subject = new PrismCacheDataUnjammer(jedisPool)
        1 * jedisPool.getResource() >> jedisClient

        when:
        def result = subject.getAllNominations()

        then:
        1 * jedisClient.get("nom_list") >> null
        result == []
    }

    def 'getNomination pulls nomination from the cache'() {
        given:
        def jedisPool = Mock(JedisPool)
        def jedisClient = Mock(Jedis)
        def subject = new PrismCacheDataUnjammer(jedisPool)
        1 * jedisPool.getResource() >> jedisClient
        FullNom nom = FullNom.builder().build()
        def objectMapper = new ObjectMapper()

        when:
        subject.getNomination('key')

        then:
        1 * jedisClient.hget('full_noms', 'key') >> objectMapper.writeValueAsString(nom)
    }
}
