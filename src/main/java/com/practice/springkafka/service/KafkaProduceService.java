package com.practice.springkafka.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.practice.springkafka.controller.dto.MyMessage;

@Service
public class KafkaProduceService {

  private static final String TOPIC_NAME = "topic5";

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private KafkaTemplate<String, MyMessage> jsonKafkaTemplate;

  public void send(String message) {
    kafkaTemplate.send(TOPIC_NAME, message);
  }

  public void sendWithCallback(String message) {
    CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC_NAME, message);

    future.thenAccept(result -> {
      System.out.println("Sent " + message + " offset:" + result.getRecordMetadata().offset());
    }).exceptionally(ex -> {
      System.out.println("Failed " + message + " due to: " + ex.getMessage());
      return null;
    });
  }

  public void sendJson(MyMessage message) {
    jsonKafkaTemplate.send(TOPIC_NAME, message);
  }
}
