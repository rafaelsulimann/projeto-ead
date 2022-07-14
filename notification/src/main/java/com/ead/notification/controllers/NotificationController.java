package com.ead.notification.controllers;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ead.notification.dtos.NotificationDto;
import com.ead.notification.models.NotificationModel;
import com.ead.notification.services.NotificationService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class NotificationController {

    final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping(value = "/users/{userId}/notifications")
    public ResponseEntity<Page<NotificationModel>> findAllNotificationsByUser(@PathVariable UUID userId,
            @PageableDefault(page = 0, size = 10, sort = "notificationId", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(notificationService.findAllNotificationsByUser(userId, pageable));
    }

    @PutMapping(value = "/users/{userId}/notifications/{notificationId}")
    public ResponseEntity<Object> updateNotification(@PathVariable UUID userId, @PathVariable UUID notificationId,
            @RequestBody @Valid NotificationDto notificationDto) {
        Optional<NotificationModel> notificationModelOptional = notificationService
                .findByNotificationIdAndUserId(notificationId, userId);
        if (!notificationModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification not found.");
        }
        notificationModelOptional.get().setNotificationStatus(notificationDto.getNotificationStatus());
        notificationService.saveNotification(notificationModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(notificationModelOptional.get());
    }

}
