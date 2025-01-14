package com.vheekey.book_service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vheekey.book_service.config.KafkaConfig;
import com.vheekey.book_service.events.BookCreated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaConfig kafkaConfig;
    private final ObjectMapper objectMapper;

    public KafkaProducerService(
            KafkaTemplate<String, String> kafkaTemplate,
            KafkaConfig kafkaConfig,
            ObjectMapper objectMapper
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaConfig = kafkaConfig;
        this.objectMapper = objectMapper;
    }

    public boolean publishMessage(BookCreated bookCreated) {
        try {
            String bookCreatedJson = objectMapper.writeValueAsString(bookCreated);
            kafkaTemplate.send(this.kafkaConfig.getProducerTopic(), bookCreated.getBookId(), bookCreatedJson);
            log.info("Published book_created{}", bookCreatedJson);

            return true;
        } catch (Exception e) {
            log.error("Error while publishing message", e);
            return false;
        }
    }
}
