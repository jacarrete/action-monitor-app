package com.betvictor.producerservice.service;

import com.betvictor.producerservice.model.KafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    public String sendMessage(KafkaMessage kafkaMessage) {

        kafkaMessage.setMessageDateTime(LocalDateTime.now().toString());

        log.info("Sending... " + kafkaMessage + " to topic: " + topicName);

        kafkaTemplate.send(topicName, kafkaMessage);

        return "Published Successfully";

    }

}
