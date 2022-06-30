package com.betvictor.consumerservice.service;

import com.betvictor.consumerservice.model.KafkaMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private static Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

    @Value("${websocket.topic}")
    private String topic;

    private final SimpMessagingTemplate template;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(@Payload KafkaMessage kafkaMessage) {

        LOGGER.info("Message received. Sender: {} Message: {} Time: {}",
                kafkaMessage.getSender(),
                kafkaMessage.getMessage(),
                kafkaMessage.getMessageDateTime());

        template.convertAndSend("/" + topic, kafkaMessage);

        LOGGER.info("Sending to websocket");

    }

}
