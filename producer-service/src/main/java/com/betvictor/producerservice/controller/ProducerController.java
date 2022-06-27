package com.betvictor.producerservice.controller;

import com.betvictor.producerservice.model.KafkaMessage;
import com.betvictor.producerservice.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping ("/kafka/produce")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ProducerController {

    private final ProducerService producerService;

    @PostMapping
    public String sentMessage(@RequestBody KafkaMessage kafkaMessage) {

        producerService.sendMessage(kafkaMessage);

        return "Published Successfully";
    }

}
