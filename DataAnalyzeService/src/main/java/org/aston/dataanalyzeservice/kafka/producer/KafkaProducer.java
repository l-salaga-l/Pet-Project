package org.aston.dataanalyzeservice.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.aston.dataanalyzeservice.dto.event.ReportCreatedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("report-created-event-topic")
    public String topicName;

    public void send(ReportCreatedEvent event) {
        CompletableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topicName, event.getReportName(), event.toString());

        future.whenComplete((result, throwable) -> {
            if (throwable != null) {
                log.error("{}",throwable.getMessage());
            } else {
                log.info("{}", result.getRecordMetadata());
            }
        });
    }
}
