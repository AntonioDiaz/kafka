package com.adiaz.kafka_custom_serializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class UserListener {

  public static List<UserKafka> receivedUsers = new ArrayList<>();

  @KafkaListener(topics = "${topic-name}")
  public void consume(UserKafka userKafka) {
    log.info(String.format("User received -> %s", userKafka));
    receivedUsers.add(userKafka);
  }

}
