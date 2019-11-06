package com.afresearchlab.stargate.rfis.service;

import com.afresearchlab.prismadaptermodels.FullNom;
import com.afresearchlab.stargate.persistence.LinkRepository;
import com.afresearchlab.stargate.persistence.RfiRepository;
import com.afresearchlab.stargate.requirements.NomNotFoundException;
import com.afresearchlab.stargate.requirements.persistence.PrismRepository;
import com.afresearchlab.stargate.rfis.model.Rfi;
import com.afresearchlab.stargate.rfis.model.Link;
import com.afresearchlab.stargate.rfis.requests.CreateRecordLinkRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkService {
    private final LinkRepository linkRepository;
    private final RfiRepository rfiRepository;
    private final PrismRepository prismRepository;

    public LinkService(LinkRepository linkRepository, RfiRepository rfiRepository, PrismRepository prismRepository) {
        this.linkRepository = linkRepository;
        this.rfiRepository = rfiRepository;
        this.prismRepository = prismRepository;
    }

    public void create(CreateRecordLinkRequest request) {
        Link link = new Link(request.getRecord1Id(), request.getRecord2Id(), request.getRecord1System(), request.getRecord2System(), null, null);
        this.linkRepository.save(link);
    }

    public List<Link> getAllByRecordId(String recordId) {
        List<Link> links = this.linkRepository.getAllByRecordId(recordId);
        links.forEach(link -> {
            String system;
            String id;

            if (link.getRecord2Id().equals(recordId)) {
                system = link.getRecord1System();
                id = link.getRecord1Id();
            } else {
                system = link.getRecord2System();
                id = link.getRecord2Id();
            }

            switch (system.toUpperCase()) {
                case "IMM":
                    Rfi rfi1 = this.rfiRepository.get(Long.parseLong(id));
                    if (rfi1 != null) {
                        link.setTitle(rfi1.getTitle());
                        link.setStatus(rfi1.getStatus());
                    }
                    break;
                case "PRISM":
                    try {
                        FullNom nom = this.prismRepository.getNominationById(id);
                        if (nom != null) {
                            link.setTitle(nom.getTitle());
                            link.setStatus(nom.getStatus());
                        }
                    } catch (NomNotFoundException e) { /* We are swallowing this for now (y tho) */}
                    break;
            }
        });
        return links;
    }

    public void delete(String record1Id, String record2Id, String record1System, String record2System) {
        this.linkRepository.delete(record1Id, record2Id, record1System, record2System);
    }

    public void saveAll(List<Link> links, String recordId) {
        this.linkRepository.saveAll(links, recordId);
    }

    public void deleteAllByRecordId(String rfiId) {
        this.linkRepository.deleteAllByRecordId(rfiId);
    }

    public void deleteAll() {
        this.linkRepository.deleteAll();
    }
}
