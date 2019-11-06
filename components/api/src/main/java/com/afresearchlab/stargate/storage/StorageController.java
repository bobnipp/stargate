package com.afresearchlab.stargate.storage;

import com.amazonaws.util.Base64;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/storage")
public class StorageController {
    private final StorageService service;

    public StorageController(StorageService service) {
        this.service = service;
    }

    @GetMapping(value = "/{recordId}/{filename}")
    public StorageResponse getFile(@PathVariable() String recordId, @PathVariable() String filename) {
        StorageResponse storageResponse = new StorageResponse();
        storageResponse.setFilename(filename);
        storageResponse.setRecordId(recordId);
        storageResponse.setBytes(Base64.encodeAsString(this.service.get(recordId, filename)));
        return storageResponse;
    }

}
