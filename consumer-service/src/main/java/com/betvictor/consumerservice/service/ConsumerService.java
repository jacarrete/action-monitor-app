package com.betvictor.consumerservice.service;

import com.betvictor.consumerservice.model.KafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(@Payload KafkaMessage kafkaMessage) {

        log.info("Message received. Id : {} Sender: {} Message: {} Date: {}",
                kafkaMessage.getId(),
                kafkaMessage.getSender(),
                kafkaMessage.getMessage(),
                kafkaMessage.getMessageDate());
    }

}
