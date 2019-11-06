package com.afresearchlab.stargate.requirements.service;

import com.afresearchlab.prismadaptermodels.FullNom;
import com.afresearchlab.prismadaptermodels.Target;
import com.afresearchlab.stargate.healthcheck.HealthCheck;
import com.afresearchlab.stargate.requirements.model.ImmFullNom;
import com.afresearchlab.stargate.requirements.persistence.PrismRepository;
import com.afresearchlab.stargate.rfis.model.Activity;
import com.afresearchlab.stargate.rfis.model.Link;
import com.afresearchlab.stargate.rfis.service.ActivityService;
import com.afresearchlab.stargate.rfis.service.LinkService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.saic.prism.ws.coredataws.prismcoredataws.PrismNom;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrismService {

    private final LinkService linkService;
    private final ActivityService activityService;
    private final PrismRepository prismRepository;

    public PrismService(LinkService linkService, PrismRepository prismRepository, ActivityService activityService) {
        this.linkService = linkService;
        this.prismRepository = prismRepository;
        this.activityService = activityService;
    }

    public List<ImmFullNom> getAllNominations() {
        List<PrismNom> nomList = this.prismRepository.getAllNominations();

        return nomList.stream().map(prismNom -> {
                FullNom fullNom = this.prismRepository.getNominationById(prismNom.getId());
                return getImmFullNom(fullNom);
            }
        ).collect(Collectors.toList());
    }

    public ImmFullNom getNominationById(String id) {
        FullNom fullNom = this.prismRepository.getNominationById(id);

        if (fullNom != null) {
            return getImmFullNom(fullNom);
        } else {
            return null;
        }
    }

    @HystrixCommand(fallbackMethod = "failHealthCheck")
    public HealthCheck healthCheck() {
        try {
            return this.prismRepository.healthCheck();
        } catch (Exception e) {
            return failHealthCheck();
        }
    }

    private ImmFullNom getImmFullNom(FullNom fullNom) {
        List<Activity> activities = activityService.getAllByRfiId(fullNom.getId());
        List<Link> links = linkService.getAllByRecordId(fullNom.getId());

        return ImmFullNom.builder()
            .id(fullNom.getId())
            .title(fullNom.getTitle())
            .country(fullNom.getCountry())
            .region(fullNom.getRegion())
            .priority(fullNom.getPriority())
            .status(fullNom.getStatus())
            .justification(fullNom.getJustification())
            .poc(fullNom.getPoc())
            .collectionStartDate(fullNom.getCollectionStartDate())
            .collectionEndDate(fullNom.getCollectionEndDate())
            .collectionTerm(fullNom.getCollectionTerm())
            .collectionType(fullNom.getCollectionType())
            .assignee(fullNom.getAssignee())
            .createdOn(fullNom.getCreatedOn())
            .prevResearch(fullNom.getPrevResearch())
            .collectionGuidance(fullNom.getCollectionGuidance())
            .eeis(fullNom.getEeis())
            .targets(fullNom.getTargets())
            .recordHistory(fullNom.getRecordHistory())
            .sensors(fullNom.getSensors())
            .links(links)
            .activities(activities)
            .build();
    }

    private HealthCheck failHealthCheck() {
        return new HealthCheck(false, -1);
    }
}
