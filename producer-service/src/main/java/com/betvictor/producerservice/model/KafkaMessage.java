package com.betvictor.producerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"sender", "message", "messageDateTime"})
public class KafkaMessage {

    private String sender;
    private String message;
    private String messageDateTime;

}
