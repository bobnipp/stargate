package com.afresearchlab.stargate.rfis.service;

import com.afresearchlab.stargate.persistence.ActivityRepository;
import com.afresearchlab.stargate.rfis.model.Activity;
import com.afresearchlab.stargate.rfis.model.ActivityRequest;
import com.afresearchlab.stargate.rfis.model.Attachment;
import com.afresearchlab.stargate.rfis.model.AttachmentRequest;
import com.afresearchlab.stargate.storage.StorageService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final StorageService storageService;

    public ActivityService(ActivityRepository activityRepository, StorageService storageService) {
        this.activityRepository = activityRepository;
        this.storageService = storageService;
    }

    public Activity create(ActivityRequest activityRequest) throws IOException {
        List<Attachment> attachments = new ArrayList<>();
        if (activityRequest.getAttachments() != null && activityRequest.getAttachments().size() > 0) {
            for (AttachmentRequest request : activityRequest.getAttachments()) {
                String byteString = request.getFileBytes();
                String byteStringWithoutMetaData = byteString.substring(byteString.indexOf(',') + 1);
                File file = new File(request.getFilename());
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(Base64.getDecoder().decode(byteStringWithoutMetaData));
                fos.close();

                storageService.upload(file, activityRequest.getRfiId());

                attachments.add(
                    new Attachment(request.getFilename(),
                        request.getFileSize(),
                        request.getFilename().substring(request.getFilename().lastIndexOf('.') + 1))
                );
            }
        }

        Activity activity = new Activity(activityRequest.getId(),
            activityRequest.getRfiId(),
            activityRequest.getText(),
            activityRequest.getUsername(),
            activityRequest.getTimestamp(),
            attachments
        );

        return this.activityRepository.save(activity);
    }

    public List<Activity> getAllByRfiId(String recordId) {
        return this.activityRepository.getAllByRfiId(recordId);
    }

    public void deleteById(Long id) {
        Activity activity = this.activityRepository.get(id);
        if (activity != null) {
            if (activity.getAttachments().size() > 0) {
                List<Attachment> attachments = activity.getAttachments();
                attachments.forEach(attachment -> {
                    this.storageService.delete(activity.getRfiId(), attachment.getName());
                });
            }
            this.activityRepository.deleteById(id);
        }
    }

}
