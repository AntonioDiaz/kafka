package com.adiaz.kafkaspringclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CustomKafkaListener {

  private static final List<String> messages = new ArrayList<>();

  @KafkaListener(topics = "${topic_name}")
  public void listenWithHeaders(ConsumerRecord<String, String> record) {
    messages.add(String.format("%s - %s", record.key(), record.value()));
    log.info("Received Message: " + record);
  }

  public static List<String> getMessages() {
    return messages;
  }
}
