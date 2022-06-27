package com.betvictor.producerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String message;
    private String sender;

    @JsonIgnore
    private String id = UUID.randomUUID().toString();
    @JsonIgnore
    private LocalDate messageDate = LocalDate.now();

}
