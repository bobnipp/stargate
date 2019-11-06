package com.afresearchlab.stargate.lists;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListsService {

    private final ListsRepository listsRepository;

    public ListsService(ListsRepository listsRepository) {
        this.listsRepository = listsRepository;
    }

    public List<CustomList> getLists() {
        return this.listsRepository.getLists();
    }

    public void createList(CustomList request) {
        this.listsRepository.createList(request);
    }

    public void updateList(CustomList request) throws JsonProcessingException {
        this.listsRepository.updateList(request);
    }

    public void addRecordToList(String listName, RecordIdentifier request) throws JsonProcessingException {
        this.listsRepository.addRecordToList(listName, request);
    }

    public void deleteList(String name) {this.listsRepository.deleteList(name); }

    public void removeRecordFromAllLists(RecordIdentifier recordIdentifier) {
        this.listsRepository.removeRecordFromAllLists(recordIdentifier);
    }

    public void removeRecordFromList(String listName, RecordIdentifier recordIdentifier) {
        this.listsRepository.removeRecordFromList(listName, recordIdentifier);
    }

    public void deleteAll() {
        this.listsRepository.deleteAll();
    }
}
