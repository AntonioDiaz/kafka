package com.adiaz.kafkaerrors;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
public class CustomKafkaListener {

  private static final List<String> messages = new ArrayList<>();

  //blocking example

  @RetryableTopic(
          kafkaTemplate = "kafkaTemplate",
          attempts = "4",
          backoff = @Backoff(delay = 5000, maxDelay = 15000))
  @KafkaListener(topics = "${topic_name}")
  public void listenWithHeaders(
          @Header(KafkaHeaders.RECEIVED_TOPIC) String receivedTopic,
          ConsumerRecord<String, String> record) {
    log.info("receivedTopic -> {}", receivedTopic);
    if (!StringUtils.hasText(record.key())) {
      String errorMsg = "KEY IS MANDATORY";
      log.error(errorMsg);
      throw new IllegalArgumentException(errorMsg);
    }
    messages.add(String.format("%s - %s", record.key(), record.value()));
    log.info("Received Message: " + record);
  }

  public static List<String> getMessages() {
    return messages;
  }

}
