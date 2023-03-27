package com.practice.springkafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.practice.springkafka.controller.dto.MyMessage;

@Configuration
public class KafkaProducerConfig {

  private static final String BOOTSTRAP_SERVER = "localhost:9092";

  @Bean
  public ProducerFactory<String, String> producerFactory() {
    Map<String, Object> configProdps = new HashMap<>();
    configProdps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
    configProdps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProdps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

    return new DefaultKafkaProducerFactory<>(configProdps);
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public ProducerFactory<String, MyMessage> jsonProducerFactory() {
    Map<String, Object> configProdps = new HashMap<>();
    configProdps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
    configProdps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProdps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

    return new DefaultKafkaProducerFactory<>(configProdps);
  }

  @Bean
  public KafkaTemplate<String, MyMessage> jsonKafkaTemplate() {
    return new KafkaTemplate<>(jsonProducerFactory());
  }
}
