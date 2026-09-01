package org.example.logitrack.client;

import org.example.logitrack.dto.NotificationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "notification-service",
        url = "${notification.service.url}"
)
public interface NotificationClient {

    @PostMapping("/api/notifications")
    void createNotification(
            @RequestBody NotificationRequest request
    );
}