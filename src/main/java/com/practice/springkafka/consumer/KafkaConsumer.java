package com.practice.springkafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.springkafka.controller.dto.MyMessage;

@Component
public class KafkaConsumer {

  private static final String TOPIC_NAME = "topic5";

  ObjectMapper objectMapper = new ObjectMapper();

  @KafkaListener(topics = TOPIC_NAME)
  public void listenMessage(String jsonMessage) {
    try {
      MyMessage message = objectMapper.readValue(jsonMessage, MyMessage.class);
      System.out.println(">>>" + message.name() + "," +message.message());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
