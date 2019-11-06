package com.afresearchlab.prismadapter.service;

import com.afresearchlab.prismadapter.PrismException;
import com.google.common.collect.Lists;
import com.saic.prism.ws.coredataws.prismcoredataws.*;
import com.saic.prism.ws.researchws.prismresearchws.PRISMResearchWSEndPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrismSOAPService {
    private final PRISMResearchWSEndPoint researchWSEndPoint;
    private final PRISMCoreDataWSEndPoint coreWSEndPoint;
    private final String prismUsername;
    private final String prismPassword;

    public PrismSOAPService(PRISMResearchWSEndPoint researchWSEndPoint,
                            PRISMCoreDataWSEndPoint coreWSEndPoint,
                            @Value("${prism.soap.username}") String prismUsername,
                            @Value("${prism.soap.password}") String prismPassword) {
        this.researchWSEndPoint = researchWSEndPoint;
        this.coreWSEndPoint = coreWSEndPoint;
        this.prismUsername = prismUsername;
        this.prismPassword = prismPassword;
    }

    public PrismNom getNomination(String key) {
        return getNominations(Collections.singletonList(key)).get(0);
    }

    public List<PrismNom> getNominations(List<String> keys) {
        List<PrismNom> prismNomList = new ArrayList<>();

        List<List<String>> keyRequests = Lists.partition(keys, 20);

        for (List<String> keyRequestList : keyRequests) {
            PrismMultiDataRequest request = new PrismMultiDataRequest();
            request.setUserID(this.prismUsername);
            request.setPassword(this.prismPassword);
            request.getKeyList().addAll(keyRequestList);

            PrismNomResponse fullNoms = this.coreWSEndPoint.getNOM(request);

            if (fullNoms.getErrorMessages().size() > 0) {
                String errorMessage = fullNoms.getErrorMessages().stream().collect(Collectors.joining(" "));
                throw new PrismException(errorMessage);
            }

            prismNomList.addAll(fullNoms.getPrismNomList());
        }

        return prismNomList;
    }

    public Prismcr getCollectionRequirement(String key) {
        PrismMultiDataRequest request = new PrismMultiDataRequest();
        request.setUserID(this.prismUsername);
        request.setPassword(this.prismPassword);
        request.getKeyList().add(key);

        PrismcrResponse fullCrs = this.coreWSEndPoint.getCR(request);

        if (fullCrs.getErrorMessages().size() > 0) {
            String errorMessage = fullCrs.getErrorMessages().stream().collect(Collectors.joining(" "));
            throw new PrismException(errorMessage);
        }

        return fullCrs.getPrismCRList().get(0);
    }


    public PrismTarget getTarget(String key) {
        PrismMultiDataRequest request = new PrismMultiDataRequest();
        request.setUserID(this.prismUsername);
        request.setPassword(this.prismPassword);
        request.getKeyList().add(key);

        PrismTargetResponse target = this.coreWSEndPoint.getTarget(request);

        if (target.getErrorMessages().size() > 0) {
            String errorMessage = target.getErrorMessages().stream().collect(Collectors.joining(" "));
            throw new PrismException(errorMessage);
        }

        return target.getPrismTargetList().get(0);
    }
}
