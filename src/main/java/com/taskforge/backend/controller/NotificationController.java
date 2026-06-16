package com.taskforge.backend.controller;

import com.taskforge.backend.dto.NotificationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/notify")
    public void sendNotification(
            NotificationMessage message) {

        messagingTemplate.convertAndSend(
                "/topic/notifications",
                message);
    }
}