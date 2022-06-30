package com.betvictor.producerservice.controller;

import com.betvictor.producerservice.model.KafkaMessage;
import com.betvictor.producerservice.service.ProducerService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "ProducerController", tags = "REST Apis related to producer controller")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ProducerController {

    private final ProducerService producerService;

    @PostMapping(path = "/kafka/produce")
    public String sendMessage(@RequestBody KafkaMessage kafkaMessage) {

        return producerService.sendMessage(kafkaMessage);

    }

}
