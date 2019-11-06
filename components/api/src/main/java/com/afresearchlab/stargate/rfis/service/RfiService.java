package com.afresearchlab.stargate.rfis.service;

import com.afresearchlab.prismadaptermodels.RecordHistory;
import com.afresearchlab.stargate.healthcheck.HealthCheck;
import com.afresearchlab.stargate.lists.ListsService;
import com.afresearchlab.stargate.lists.RecordIdentifier;
import com.afresearchlab.stargate.persistence.ActivityRepository;
import com.afresearchlab.stargate.persistence.HistoryRepository;
import com.afresearchlab.stargate.persistence.RfiRepository;
import com.afresearchlab.stargate.persistence.SensorRepository;
import com.afresearchlab.stargate.persistence.TargetRepository;
import com.afresearchlab.stargate.rfis.model.Activity;
import com.afresearchlab.stargate.rfis.model.Link;
import com.afresearchlab.stargate.rfis.model.Rfi;
import com.afresearchlab.stargate.rfis.model.Sensor;
import com.afresearchlab.stargate.rfis.model.Target;
import com.afresearchlab.stargate.rfis.requests.CreateRfiRequest;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class RfiService {
    private final RfiRepository rfiRepository;
    private final ActivityRepository activityRepository;
    private final TargetRepository targetRepository;
    private final SensorRepository sensorRepository;
    private final HistoryRepository historyRepository;
    private final LinkService linkService;
    private final ListsService listService;

    public RfiService(RfiRepository rfiRepository,
                      ActivityRepository activityRepository,
                      TargetRepository targetRepository,
                      SensorRepository sensorRepository,
                      HistoryRepository historyRepository,
                      LinkService linkService,
                      ListsService listsService) {
        this.rfiRepository = rfiRepository;
        this.activityRepository = activityRepository;
        this.targetRepository = targetRepository;
        this.sensorRepository = sensorRepository;
        this.historyRepository = historyRepository;
        this.linkService = linkService;
        this.listService = listsService;
    }

    public Rfi create(String username, CreateRfiRequest request) {
        Rfi createdRfi = this.rfiRepository.save(
                new Rfi(null,
                        request.getTitle(),
                        request.getCountry(),
                        request.getCoordinates(),
                        request.getCity(),
                        request.getRegion(),
                        request.getNai(),
                        request.getPriority(),
                        request.getStatus(),
                        request.getJustification(),
                        request.getPrevResearch(),
                        request.getRequirementTypeId(),
                        request.getSubWorkflowId(),
                        request.getClassificationId(),
                        request.getCustomClassification(),
                        request.getCaveatId(),
                        request.getSubmittingOrgId(),
                        request.getPoc(),
                        request.getNipfCodeId(),
                        request.getPirNameId(),
                        request.getCentcomIsrRoleId(),
                        request.getOperation(),
                        request.getCollectionStartDate(),
                        request.getCollectionEndDate(),
                        request.getCollectionTerm(),
                        request.getCollectionType(),
                        request.getAssignedTeamId(),
                        request.getAssignee(),
                        request.getCollectionGuidance(),
                        request.getEeis(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                ));

        request.getTargets().forEach(target -> target.setRfiId(createdRfi.getId()));
        request.getSensors().forEach(sensor -> sensor.setRfiId(createdRfi.getId()));

        this.targetRepository.saveAll(request.getTargets());
        this.sensorRepository.saveAll(request.getSensors());
        this.historyRepository.save(username, "Insert", createdRfi.getId());

        return get(createdRfi.getId());
    }

    public List<Rfi> getAll() {
        return rfiRepository.getAll().stream().map(
                rfi -> {
                    List<Link> links = this.linkService.getAllByRecordId(rfi.getId().toString());
                    List<Activity> activities = this.activityRepository.getAllByRfiId(rfi.getId().toString());
                    List<Target> targets = this.targetRepository.getAll(rfi.getId());
                    targets = targets.isEmpty() ? null : targets;
                    List<Sensor> sensors = this.sensorRepository.getAll(rfi.getId());
                    sensors = sensors.isEmpty() ? null : sensors;
                    List<RecordHistory> histories = this.historyRepository.getAllByRfiId(rfi.getId());

                    String plottedCoordinates = Optional.ofNullable(targets)
                            .map(plottedTargets -> plottedTargets.stream().map(Target::getCoordinates).filter(t -> !StringUtils.isEmpty(t)).findFirst().orElseGet(rfi::getCoordinates))
                            .orElseGet(rfi::getCoordinates);

                    if (plottedCoordinates != null) {
                        String[] sub = plottedCoordinates.split("; ");
                        plottedCoordinates = sub[0];
                    }

                    return rfi.toBuilder()
                            .coordinates(plottedCoordinates)
                            .links(links)
                            .activities(activities)
                            .recordHistory(histories)
                            .targets(targets)
                            .sensors(sensors)
                            .build();
                })
                .collect(Collectors.toList());
    }

    public Rfi updateRfi(String username, Rfi rfi) {
        rfiRepository.update(rfi);

        targetRepository.deleteAllByRfiId(rfi.getId());
        targetRepository.saveAll(rfi.getTargets());

        sensorRepository.deleteAllByRfiId(rfi.getId());
        sensorRepository.saveAll(rfi.getSensors());

        historyRepository.save(username, "Update", rfi.getId());

        return get(rfi.getId());
    }

    public List<String> search(String title, String country) {
        return null;
    }

    public Rfi get(Long id) {
        Rfi rfi = this.rfiRepository.get(id);
        if (rfi != null) {
            rfi.setLinks(this.linkService.getAllByRecordId(id.toString()));
            rfi.setActivities(this.activityRepository.getAllByRfiId(id.toString()));
            rfi.setTargets(this.targetRepository.getAll(id));
            rfi.setSensors(this.sensorRepository.getAll(id));
            rfi.setRecordHistory(this.historyRepository.getAllByRfiId(id));
            return rfi;
        } else {
            return null;
        }
    }

    @Transactional
    public void delete(Long id) {
        this.linkService.deleteAllByRecordId(id.toString());
        this.activityRepository.deleteAllByRfiId(id.toString());
        this.targetRepository.deleteAllByRfiId(id);
        this.historyRepository.deleteAllByRfiId(id);
        this.sensorRepository.deleteAllByRfiId(id);
        this.listService.removeRecordFromAllLists(new RecordIdentifier("0", id.toString()));
        this.rfiRepository.delete(id);
    }

    @Transactional
    public void deleteAll() {
        this.linkService.deleteAll();
        this.activityRepository.deleteAll();
        this.targetRepository.deleteAll();
        this.historyRepository.deleteAll();
        this.sensorRepository.deleteAll();
        this.listService.deleteAll();
        this.rfiRepository.deleteAll();
    }

    @HystrixCommand(
        fallbackMethod = "failHealthCheck",
        commandProperties = {
            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "1000"),
        }
    )
    public HealthCheck healthCheck() {
        try {
            return new HealthCheck(this.rfiRepository.healthCheck(), -1);
        } catch (Exception e) {
            return failHealthCheck();
        }
    }

    public HealthCheck failHealthCheck() {
        return new HealthCheck(this.rfiRepository.healthCheck(), -1);
    }
}
