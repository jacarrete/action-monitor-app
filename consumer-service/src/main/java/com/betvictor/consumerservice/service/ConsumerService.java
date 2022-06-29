package com.betvictor.consumerservice.service;

import com.betvictor.consumerservice.model.KafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerService {

    @Value("${websocket.topic}")
    private String topic;

    private final SimpMessagingTemplate template;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(@Payload KafkaMessage kafkaMessage) {

        log.info("Message received. Id : {} Sender: {} Message: {} Date: {}",
                kafkaMessage.getId(),
                kafkaMessage.getSender(),
                kafkaMessage.getMessage(),
                kafkaMessage.getMessageDate());

        template.convertAndSend("/" + topic, kafkaMessage);

        log.info("Sending to websocket");

    }

}
