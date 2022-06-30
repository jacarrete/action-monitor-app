package com.betvictor.producerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"sender", "message", "messageDateTime"})
public class KafkaMessage {

    private String sender;
    private String message;
    private LocalDateTime messageDateTime = LocalDateTime.now();

}
