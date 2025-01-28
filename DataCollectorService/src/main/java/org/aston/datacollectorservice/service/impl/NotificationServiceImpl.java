package org.aston.datacollectorservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.aston.datacollectorservice.model.Notification;
import org.aston.datacollectorservice.repository.NotificationRepository;
import org.aston.datacollectorservice.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    public void saveNotification(Notification notification) {
        notificationRepository.save(notification);
    }
}
