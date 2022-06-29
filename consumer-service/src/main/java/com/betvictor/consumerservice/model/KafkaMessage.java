package com.betvictor.consumerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"id", "message", "sender", "messageDate"})
public class KafkaMessage {

    private String id = UUID.randomUUID().toString();
    private LocalDate messageDate = LocalDate.now();

    private String sender;
    private String message;

}
