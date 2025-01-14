package com.vheekey.book_service.config;

import lombok.Data;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Data
public class KafkaConfig {

    private final String producerTopic;

    public KafkaConfig(@Value("${app.kafka.producer.topic}") String producerTopic) {
        this.producerTopic = producerTopic;
    }

    @Bean
    public NewTopic producerTopic() {
        return TopicBuilder.name(this.producerTopic)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
