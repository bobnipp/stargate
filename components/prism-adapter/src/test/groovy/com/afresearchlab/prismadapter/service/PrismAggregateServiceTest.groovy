package com.afresearchlab.prismadapter.service

import com.afresearchlab.prismadapter.model.PrismUiNomination
import com.afresearchlab.prismadaptermodels.FullNom
import com.afresearchlab.prismadaptermodels.IMMPrismTargetType
import com.afresearchlab.prismadaptermodels.RecordHistory
import com.afresearchlab.prismadaptermodels.Sensor
import com.afresearchlab.prismadaptermodels.Target
import com.saic.prism.ws.coredataws.prismcoredataws.PrismCoord
import com.saic.prism.ws.coredataws.prismcoredataws.PrismCountryCode
import com.saic.prism.ws.coredataws.prismcoredataws.PrismDataInfo
import com.saic.prism.ws.coredataws.prismcoredataws.PrismGeoRegion
import com.saic.prism.ws.coredataws.prismcoredataws.PrismNom
import com.saic.prism.ws.coredataws.prismcoredataws.PrismNomIMINT
import com.saic.prism.ws.coredataws.prismcoredataws.PrismNomStatus
import com.saic.prism.ws.coredataws.prismcoredataws.PrismPoint
import com.saic.prism.ws.coredataws.prismcoredataws.PrismRequirementType
import com.saic.prism.ws.coredataws.prismcoredataws.PrismSensor
import com.saic.prism.ws.coredataws.prismcoredataws.PrismSensorMode
import com.saic.prism.ws.coredataws.prismcoredataws.PrismSensorType
import com.saic.prism.ws.coredataws.prismcoredataws.PrismStandingAdhocFlag
import com.saic.prism.ws.coredataws.prismcoredataws.PrismState
import com.saic.prism.ws.coredataws.prismcoredataws.PrismTarget
import com.saic.prism.ws.coredataws.prismcoredataws.PrismTargetShort
import com.saic.prism.ws.coredataws.prismcoredataws.Prismcr
import com.saic.prism.ws.coredataws.prismcoredataws.PrismcrSensor
import com.saic.prism.ws.coredataws.prismcoredataws.PrismcrShort
import com.saic.prism.ws.coredataws.prismcoredataws.PrismcrTarget
import com.saic.prism.ws.coredataws.prismcoredataws.Prismdsa
import com.saic.prism.ws.coredataws.prismcoredataws.Prismloc
import com.saic.prism.ws.coredataws.prismcoredataws.Prismrwac
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl
import spock.lang.Specification

class PrismAggregateServiceTest extends Specification {

    def prismSoapService
    def prismUiService
    def subject

    def setup() {
        prismSoapService = Mock(PrismSOAPService)
        prismUiService = Mock(PrismUiService)
        subject = new PrismAggregateService(prismSoapService, prismUiService)
    }

    def 'getAllNominations() returns a list of all nominations (including inactive/unappred) in descending order of creation date'() {
        given:
        def imint = new PrismNomIMINT()
        imint.setStatus(PrismNomStatus.SUBMITTED)

        def dataInfo1 = new PrismDataInfo()
        dataInfo1.setPrismState(PrismState.ACTIVE)
        dataInfo1.setCreatedOn(XMLGregorianCalendarImpl.createDate(2017, 1, 1, 0))

        def nom1 = new PrismNom()
        nom1.setKey('key1')
        nom1.setName('name1')
        nom1.setNomImint(imint)
        nom1.setDataInfo(dataInfo1)

        def dataInfo2 = new PrismDataInfo()
        dataInfo2.setPrismState(PrismState.INACTIVE)
        dataInfo2.setCreatedOn(XMLGregorianCalendarImpl.createDate(2018, 1, 1, 0))

        def nom2 = new PrismNom()
        nom2.setKey('key2')
        nom2.setName('name2')
        nom2.setNomImint(imint)
        nom2.setDataInfo(dataInfo2)

        when:
        def result = subject.getAllNominations()

        then:
        1 * prismSoapService.getNominations(['key1', 'key2']) >> [nom1, nom2]
        1 * prismUiService.getAllNominations() >> [
            new PrismUiNomination('key1', 'name1'),
            new PrismUiNomination('key2', 'name2')
        ]
        result.key == ['key2', 'key1']
        result.name == ['name2', 'name1']
    }

    def 'getNom gets a nom with no cr'() {
        given:
        def imint = new PrismNomIMINT()
        imint.setStatus(PrismNomStatus.APPROVE)
        imint.setRespOrg('Pentagon')

        def nom = new PrismNom()
        nom.setKey('nomKey')
        nom.setName('nomName')
        nom.setId('nomId')
        nom.setNomImint(imint)
        nom.setPrecedence('very high')
        nom.setJustification('because I said so')
        nom.setComments('COMMENT LOG')
        def dataInfo = new PrismDataInfo()
        dataInfo.createdOn = XMLGregorianCalendarImpl.createDate(2018, 4, 3, 0)
        nom.setDataInfo(dataInfo)

        def expected = FullNom.builder()
            .id('nomId')
            .title('nomName')
            .status('APPROVE')
            .priority('very high')
            .justification('because I said so')
            .prevResearch('COMMENT LOG')
            .assignee('Pentagon')
            .createdOn("2018-04-03Z")
            .build()

        when:
        def result = subject.transformToFullNom(nom)

        then:
        result == expected
    }

    def 'getNom gets a nom with a cr, an associated history, and dsa/loc/rwac/point targets'() {
        given:
        def dsaCoord1 = createPrismCoord(2.0, 3.0)
        def dsaCoord2 = createPrismCoord(1.0, 1.0)
        def locCoord1 = createPrismCoord(-2.0, -3.0)
        def locCoord2 = createPrismCoord(-1.0, -1.0)
        def rwacCoord1 = createPrismCoord(-2.1, -3.1)
        def rwacCoord2 = createPrismCoord(-1.1, -1.1)
        def pointCoord1 = createPrismCoord(-2.2, -3.2)

        def prismDsa = new Prismdsa()
        prismDsa.getCoordinates().add(dsaCoord1)
        prismDsa.getCoordinates().add(dsaCoord2)
        def prismLoc = new Prismloc()
        prismLoc.getCoordinates().add(locCoord1)
        prismLoc.getCoordinates().add(locCoord2)
        def prismRwac = new Prismrwac()
        prismRwac.setLowerLeftGeo(rwacCoord1)
        prismRwac.setUpperRightGeo(rwacCoord2)
        def prismPoint = new PrismPoint()
        prismPoint.setGeoCoord(pointCoord1)

        def dsaTarget = createPrismTarget(IMMPrismTargetType.DSA, "dsaTarget")
        dsaTarget.setDsa(prismDsa)
        def locTarget = createPrismTarget(IMMPrismTargetType.LOC, "locTarget")
        locTarget.setLoc(prismLoc)
        def rwacTarget = createPrismTarget(IMMPrismTargetType.RWAC, "rwacTarget")
        rwacTarget.setRwac(prismRwac)
        def pointTarget = createPrismTarget(IMMPrismTargetType.POINT, "pointTarget")
        pointTarget.setPoint(prismPoint)

        def dsaTargetShort = createTargetShort('dsaTargetKey')
        def locTargetShort = createTargetShort('locTargetKey')
        def rwacTargetShort = createTargetShort('rwacTargetKey')
        def pointTargetShort = createTargetShort('pointTargetKey')

        PrismcrTarget dsaCrTarget = createCrTarget(dsaTargetShort)
        def locCrTarget = createCrTarget(locTargetShort)
        def rwacCrTarget = createCrTarget(rwacTargetShort)
        def pointCrTarget = createCrTarget(pointTargetShort)

        def region1 = new PrismGeoRegion()
        region1.setCode('DEN')
        region1.setDescription('is awesome')

        def region2 = new PrismGeoRegion()
        region2.setCode('COS')
        region2.setDescription('is fine')

        def country1 = new PrismCountryCode()
        country1.setName('Soviet Union')

        def country2 = new PrismCountryCode()
        country2.setName('Democratic Peoples Republic of Korea')

        def dataInfo = new PrismDataInfo()
        dataInfo.setCreatedBy('Kim Jong Un')
        dataInfo.setLastModifiedBy('Joseph Stalin')
        dataInfo.setCreatedOn(XMLGregorianCalendarImpl.parse('2018-08-12T13:00:00'))

        def prismCrSensorType = new PrismSensorType()
        prismCrSensorType.setType("FMV")
        def prismSensorMode = new PrismSensorMode()
        prismSensorMode.setName("252")
        def prismTypeSensor = new PrismcrSensor()
        prismTypeSensor.setSensorType(prismCrSensorType)
        prismTypeSensor.setSensorMode(prismSensorMode)
        prismTypeSensor.setPredNiirs(5.0)
        prismTypeSensor.setDesiredNiirs(6.0)
        prismTypeSensor.setStartAzimuth(0)
        prismTypeSensor.setStopAzimuth(359.9)
        prismTypeSensor.setStartElevation(0)
        prismTypeSensor.setStopElevation(90)
        prismTypeSensor.setMinSunAzimuth(120)
        prismTypeSensor.setMaxSunAzimuth(180)

        def prismSensorSensor = new PrismcrSensor()
        def sensor = new PrismSensor()
        sensor.setName("ASARS")
        prismSensorSensor.setSensor(sensor)
        prismSensorSensor.setPredNiirs(4.0)
        prismSensorSensor.setDesiredNiirs(7.0)
        prismSensorSensor.setStartAzimuth(1)
        prismSensorSensor.setStopAzimuth(349.9)
        prismSensorSensor.setStartElevation(2)
        prismSensorSensor.setStopElevation(80)
        prismSensorSensor.setMinSunAzimuth(110)
        prismSensorSensor.setMaxSunAzimuth(170)

        def prismCr = new Prismcr()
        prismCr.getTargetList().add(dsaCrTarget)
        prismCr.getTargetList().add(locCrTarget)
        prismCr.getTargetList().add(rwacCrTarget)
        prismCr.getTargetList().add(pointCrTarget)
        prismCr.getGeoRegionList().add(region1)
        prismCr.getGeoRegionList().add(region2)
        prismCr.getCountryCodeList().add(country1)
        prismCr.getCountryCodeList().add(country2)
        prismCr.setDataInfo(dataInfo)
        prismCr.setType(PrismRequirementType.ACTIVE)
        prismCr.setAdHocFlag(PrismStandingAdhocFlag.AD_HOC)
        prismCr.setStartDate(XMLGregorianCalendarImpl.createDate(2000, 1, 1, 0))
        prismCr.setStopDate(XMLGregorianCalendarImpl.createDate(2000, 2, 1, 0))
        prismCr.setDescription('guide the collection')
        prismCr.getSensorList().add(prismTypeSensor)
        prismCr.getSensorList().add(prismSensorSensor)

        def prismCrShort = new PrismcrShort()
        prismCrShort.setKey('crKey')

        def imint = new PrismNomIMINT()
        imint.getCrList().add(prismCrShort)
        imint.setStatus(PrismNomStatus.APPROVE)
        imint.setRespOrg('Pentagon')

        def nom = new PrismNom()
        nom.setKey('nomKey')
        nom.setName('nomName')
        nom.setId('nomId')
        nom.setNomImint(imint)
        nom.setPrecedence('very high')
        nom.setJustification('because I said so')
        nom.setComments('COMMENT LOG')
        nom.setDataInfo(dataInfo)

        def expectedDsaCoords = '2.0 3.0, 1.0 1.0'
        def expectedLocCoords = '-2.0 -3.0, -1.0 -1.0'
        def expectedRwacCoords = '-2.1 -3.1, -1.1 -1.1'
        def expectedPointCoords = '-2.2 -3.2'
        def expectedDsaTarget = new Target("dsaTarget", IMMPrismTargetType.DSA, expectedDsaCoords)
        def expectedLocTarget = new Target("locTarget", IMMPrismTargetType.LOC, expectedLocCoords)
        def expectedRwacTarget = new Target("rwacTarget", IMMPrismTargetType.RWAC, expectedRwacCoords)
        def expectedPointTarget = new Target("pointTarget", IMMPrismTargetType.POINT, expectedPointCoords)
        def sensor1 = Sensor.builder()
            .type("FMV")
            .mode("252")
            .requiredQuality(5.0)
            .desiredQuality(6.0)
            .startAzimuth(0)
            .stopAzimuth(359.9)
            .startElevation(0)
            .stopElevation(90)
            .minSunAzimuth(120)
            .maxSunAzimuth(180)
            .build()
        def sensor2 = Sensor.builder()
            .sensor("ASARS")
            .requiredQuality(4.0)
            .desiredQuality(7.0)
            .startAzimuth(1)
            .stopAzimuth(349.9)
            .startElevation(2)
            .stopElevation(80)
            .minSunAzimuth(110)
            .maxSunAzimuth(170)
            .build()

        RecordHistory h1 = RecordHistory.builder().username('Jimbo').action('insert').date(new Date().toString()).build()
        RecordHistory h2 = RecordHistory.builder().username('Freddy').action('update').date(new Date().toString()).build()
        List<RecordHistory> history = new ArrayList();
        history.add(h1)
        history.add(h2)

        def expected = FullNom.builder()
            .id('nomId')
            .title('nomName')
            .status('APPROVE')
            .targets(Arrays.asList(expectedDsaTarget, expectedLocTarget, expectedRwacTarget, expectedPointTarget))
            .country('Soviet Union, Democratic Peoples Republic of Korea')
            .region('DEN - is awesome, COS - is fine')
            .priority('very high')
            .justification('because I said so')
            .poc('Kim Jong Un, Joseph Stalin')
            .collectionStartDate('2000-01-01Z')
            .collectionEndDate('2000-02-01Z')
            .collectionTerm('AD_HOC')
            .collectionType('ACTIVE')
            .prevResearch('COMMENT LOG')
            .collectionGuidance('guide the collection')
            .createdOn('2018-08-12T13:00:00')
            .assignee('Pentagon')
            .sensors(Arrays.asList(sensor1, sensor2))
            .recordHistory(history)
            .build()

        when:
        def result = subject.transformToFullNom(nom)

        then:
        1 * prismSoapService.getCollectionRequirement('crKey') >> prismCr
        1 * prismSoapService.getTarget('dsaTargetKey') >> dsaTarget
        1 * prismSoapService.getTarget('locTargetKey') >> locTarget
        1 * prismSoapService.getTarget('rwacTargetKey') >> rwacTarget
        1 * prismSoapService.getTarget('pointTargetKey') >> pointTarget
        1 * prismUiService.getNominationHistory('nomKey') >> history
        result == expected
    }

    private PrismCoord createPrismCoord(BigDecimal lat, BigDecimal lon) {
        def dsaCoord2 = new PrismCoord()
        dsaCoord2.setLatDouble(lat)
        dsaCoord2.setLongDouble(lon)
        dsaCoord2
    }

    private PrismTarget createPrismTarget(IMMPrismTargetType type, String name) {
        def prismTarget = new PrismTarget()
        prismTarget.setType(com.saic.prism.ws.coredataws.prismcoredataws.PrismTargetType.fromValue(type.value()))
        prismTarget.setName(name)
        prismTarget
    }

    private PrismTargetShort createTargetShort(String key) {
        def targetShort = new PrismTargetShort()
        targetShort.setKey(key)
        targetShort
    }

    private PrismcrTarget createCrTarget(PrismTargetShort targetShort) {
        def crTarget = new PrismcrTarget()
        crTarget.setTarget(targetShort)
        crTarget
    }
}
