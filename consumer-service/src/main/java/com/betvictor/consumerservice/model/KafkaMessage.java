package com.betvictor.consumerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"id", "message", "sender", "messageDate"})
public class KafkaMessage {

    private String message;
    private String sender;

    private String id;
    private LocalDate messageDate;

}
