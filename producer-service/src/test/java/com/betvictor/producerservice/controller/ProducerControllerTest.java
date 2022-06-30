package com.betvictor.producerservice.controller;

import com.betvictor.producerservice.model.KafkaMessage;
import com.betvictor.producerservice.service.ProducerService;
import com.betvictor.producerservice.utils.TestUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {ProducerControllerTest.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(OutputCaptureExtension.class)
public class ProducerControllerTest {

    private final TestUtility testUtility = new TestUtility();

    private MockMvc mvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @BeforeEach
    public void setup() {
        ProducerService producerService = new ProducerService(kafkaTemplate);
        ProducerController producerController = new ProducerController(producerService);
        mvc = MockMvcBuilders.standaloneSetup(producerController).build();
    }

    @Test
    public void givenJsonPayload_whenSendingMessage_thenCallsKafkaSendMethod() throws Exception {
        KafkaMessage kafkaMessage = testUtility.deserializeData(testUtility.readMockDataFile("payload.json"), KafkaMessage.class);

        mvc.perform(post("/kafka/produce")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(kafkaMessage)))
                .andExpect(status().isOk());

        Mockito.verify(kafkaTemplate, times(1)).send(any(), any());
    }

    @Test
    public void testSendMessageConstructor() throws Exception {
        KafkaMessage kafkaMessage = new KafkaMessage("Juan", "Hi", "2022-06-30T08:53:38.733Z");

        mvc.perform(post("/kafka/produce")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(kafkaMessage)))
                .andExpect(status().isOk());

        Mockito.verify(kafkaTemplate, times(1)).send(any(), any());
    }
}
