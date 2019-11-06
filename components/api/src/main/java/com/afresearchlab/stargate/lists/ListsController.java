package com.afresearchlab.stargate.lists;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lists")
public class ListsController {

    private final ListsService listsService;

    public ListsController(ListsService listsService) {
        this.listsService = listsService;
    }

    @GetMapping
    public List<CustomList> getLists() {
        return listsService.getLists();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createList(@RequestBody CustomList request) {
        this.listsService.createList(request);
    }

    @PostMapping(path = "/{listName}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRecordToList(@PathVariable("listName") String listName, @RequestBody RecordIdentifier request) throws JsonProcessingException {
        this.listsService.addRecordToList(listName, request);
    }

    @PutMapping()
    public void updateList(@RequestBody CustomList request) throws JsonProcessingException {
        this.listsService.updateList(request);
    }

    @DeleteMapping(path = "/{listName}/{recordId}/{recordType}")
    public void removeRecordFromList(@PathVariable("listName") String listName, @PathVariable("recordId") String recordId,
                                     @PathVariable("recordType") String recordType) {
        this.listsService.removeRecordFromList(listName, new RecordIdentifier(recordType, recordId));
    }

    @DeleteMapping
    public void deleteList(@RequestParam("name") String name) {
        this.listsService.deleteList(name);
    }
}
