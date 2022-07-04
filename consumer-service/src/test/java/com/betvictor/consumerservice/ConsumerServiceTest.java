package com.betvictor.consumerservice;

import com.betvictor.consumerservice.model.KafkaMessage;
import com.betvictor.consumerservice.service.ConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class ConsumerServiceTest {

    @Autowired
    public KafkaTemplate<String, String> template;

    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    private ConsumerService consumer;

    @Captor
    ArgumentCaptor<KafkaMessage> userArgumentCaptor;

    @Test
    public void givenEmbeddedKafkaBroker_whenSendingWithSimpleProducer_thenMessageReceived() throws Exception {
        String message = objectMapper.writeValueAsString(
                new KafkaMessage("Joe", "Hi", "2022-06-30T08:53:38.733Z"));

        template.send("transaction", message);

        verify(consumer, timeout(1000).times(1)).listen(userArgumentCaptor.capture());
    }

}