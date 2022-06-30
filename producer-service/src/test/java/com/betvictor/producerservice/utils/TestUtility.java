package com.betvictor.producerservice.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class TestUtility {

    public String readMockDataFile(String path) throws URISyntaxException, IOException {
        Path filePath = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(path)).toURI());
        return Files.readString(filePath);
    }

    public <T> T deserializeData(String data, Class<T> className) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(data, className);
    }
}
