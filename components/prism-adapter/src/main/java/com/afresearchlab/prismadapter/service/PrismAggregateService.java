package com.afresearchlab.prismadapter.service;

import com.afresearchlab.prismadapter.model.PrismUiNomination;
import com.afresearchlab.prismadaptermodels.FullNom;
import com.afresearchlab.prismadaptermodels.IMMPrismTargetType;
import com.afresearchlab.prismadaptermodels.RecordHistory;
import com.afresearchlab.prismadaptermodels.Sensor;
import com.afresearchlab.prismadaptermodels.Target;
import com.saic.prism.ws.coredataws.prismcoredataws.*;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class PrismAggregateService {

    private PrismSOAPService prismSOAPService;
    private PrismUiService prismUiService;

    public PrismAggregateService(PrismSOAPService prismSOAPService, PrismUiService prismUiService) {

        this.prismSOAPService = prismSOAPService;
        this.prismUiService = prismUiService;
    }

    public List<PrismNom> getAllNominations() {
        List<PrismUiNomination> allNominations = prismUiService.getAllNominations();
        List<String> keys = allNominations.stream().map(PrismUiNomination::getKey).collect(toList());
        return prismSOAPService.getNominations(keys).stream()
            .sorted((nom1, nom2) -> {
                GregorianCalendar date1 = nom1.getDataInfo().getCreatedOn().toGregorianCalendar();
                GregorianCalendar date2 = nom2.getDataInfo().getCreatedOn().toGregorianCalendar();
                return date2.compareTo(date1);
            })
            .collect(toList());
    }

    public FullNom transformToFullNom(PrismNom nomination) {
        FullNom.FullNomBuilder builder = FullNom.builder()
            .id(nomination.getId())
            .title(nomination.getName())
            .status(nomination.getNomImint().getStatus().value())
            .priority(nomination.getPrecedence())
            .status(nomination.getNomImint().getStatus().value())
            .justification(nomination.getJustification())
            .assignee(nomination.getNomImint().getRespOrg())
            .prevResearch(nomination.getComments())
            .createdOn(nomination.getDataInfo().getCreatedOn().toString());

        if (!nomination.getNomImint().getCrList().isEmpty()) {
            Prismcr cr = prismSOAPService.getCollectionRequirement(nomination.getNomImint().getCrList().get(0).getKey());
            List<Target> targets = cr.getTargetList().stream()
                .map(tgt -> tgt.getTarget().getKey())
                .map(targetKey -> prismSOAPService.getTarget(targetKey))
                .map(this::prismTargetToTarget)
                .collect(toList());

            List<Sensor> sensors = cr.getSensorList().stream()
                .map(prismcrSensor -> {
                    String sensorModeName = (prismcrSensor.getSensorMode() != null) ? prismcrSensor.getSensorMode().getName() : null;
                    Sensor.SensorBuilder sensorBuilder = Sensor.builder()
                        .mode(sensorModeName)
                        .requiredQuality(prismcrSensor.getPredNiirs())
                        .desiredQuality(prismcrSensor.getDesiredNiirs())
                        .startAzimuth(prismcrSensor.getStartAzimuth())
                        .stopAzimuth(prismcrSensor.getStopAzimuth())
                        .startElevation(prismcrSensor.getStartElevation())
                        .stopElevation(prismcrSensor.getStopElevation())
                        .minSunAzimuth(prismcrSensor.getMinSunAzimuth())
                        .maxSunAzimuth(prismcrSensor.getMaxSunAzimuth());
                    PrismSensor sensor = prismcrSensor.getSensor();
                    if (sensor != null) {
                        sensorBuilder.sensor(sensor.getName());
                    }
                    PrismSensorType sensorType = prismcrSensor.getSensorType();
                    if (sensorType != null) {
                        sensorBuilder.type(sensorType.getType());
                    }
                    PrismSensorMode mode = prismcrSensor.getSensorMode();
                    if (mode != null) {
                        sensorBuilder.mode(mode.getName());
                    }
                    return sensorBuilder.build();
                })
                .collect(Collectors.toList());

            List<RecordHistory> recordHistory = prismUiService.getNominationHistory(nomination.getKey());

            String countries = cr.getCountryCodeList().stream()
                .map(PrismCountryCode::getName)
                .collect(Collectors.joining(", "));
            String regions = cr.getGeoRegionList().stream()
                .map(it -> it.getCode() + " - " + it.getDescription())
                .collect(Collectors.joining(", "));

            builder
                .targets(targets)
                .recordHistory(recordHistory)
                .sensors(sensors)
                .country(countries)
                .region(regions)
                .poc(cr.getDataInfo().getCreatedBy() + ", " + cr.getDataInfo().getLastModifiedBy())
                .collectionStartDate(cr.getStartDate().toString())
                .collectionEndDate(cr.getStopDate().toString())
                .collectionTerm(cr.getAdHocFlag().value())
                .collectionType(cr.getType().value())
                .collectionGuidance(cr.getDescription());
        }
        return builder.build();
    }

    private Target prismTargetToTarget(PrismTarget prismTarget) {
        String coordinates;

        switch (prismTarget.getType()) {
            case DSA:
                coordinates = prismTarget.getDsa().getCoordinates().stream()
                    .map(this::prismCoordToCoordinates)
                    .collect(Collectors.joining(", "));
                break;
            case LOC:
                coordinates = prismTarget.getLoc().getCoordinates().stream()
                    .map(this::prismCoordToCoordinates)
                    .collect(Collectors.joining(", "));
                break;
            case RWAC:
                Prismrwac rwac = prismTarget.getRwac();
                coordinates = prismCoordToCoordinates(rwac.getLowerLeftGeo()) + ", " + prismCoordToCoordinates(rwac.getUpperRightGeo());
                break;
            case POINT:
                coordinates = prismCoordToCoordinates(prismTarget.getPoint().getGeoCoord());
                break;
            default:
                coordinates = "";
        }
        return Target.builder()
            .name(prismTarget.getName())
            .type(IMMPrismTargetType.fromValue(prismTarget.getType().value()))
            .coordinates(coordinates)
            .build();
    }

    private String prismCoordToCoordinates(PrismCoord coord) {
        return String.valueOf(coord.getLatDouble()) + " " + coord.getLongDouble();
    }
}
