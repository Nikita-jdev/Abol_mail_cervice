package org.mail_service.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public abstract class AbstractKafkaConsumer<T> {

    public T listener(String message, Class<T> eventType) {
        T eventKafka;
        try {
            eventKafka = new ObjectMapper().readValue(
                    message, eventType);
        } catch (JsonProcessingException e) {
            log.error("Failed to make from JSON");
            throw new RuntimeException(Arrays.toString(e.getStackTrace()));
        }
        return eventKafka;
    }
}
