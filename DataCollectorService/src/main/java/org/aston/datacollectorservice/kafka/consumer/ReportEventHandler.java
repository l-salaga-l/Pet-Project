package org.aston.datacollectorservice.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aston.datacollectorservice.model.Notification;
import org.aston.datacollectorservice.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReportEventHandler {
    private final NotificationService notificationService;

    @KafkaListener(topics = "report-created-event-topic", groupId = "report-created-events")
    public void listen(String record) {
        Notification notification = Notification.builder()
                .body(record)
                .build();

        notificationService.saveNotification(notification);

        log.info("Message accepted: {}", record);
    }
}
