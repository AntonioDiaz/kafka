package com.adiaz.kafka_custom_serializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@RestController
public class HomeController {

  @Value("${topic-name}")
  String myTopic;
  
  @Autowired
  private KafkaTemplate<String, UserKafka> kafkaTemplate;

  @GetMapping("/publish-user")
  public ResponseEntity<String> publishUser(@RequestParam String name, @RequestParam String surname) throws Exception {
    UserKafka userKafka = UserKafka.builder()
            .id(new Random().nextLong())
            .name(name)
            .surname(surname)
            .build();
    CompletableFuture<SendResult<String, UserKafka>> send = kafkaTemplate.send(myTopic, userKafka);
    return ResponseEntity.ok(String.format("user sent -><br>%s, <br>%s", userKafka, send.get().getRecordMetadata()));
  }

  @GetMapping("/read-users")
  public ResponseEntity<List<UserKafka>> readUsers() {
    return ResponseEntity.ok(UserListener.receivedUsers);
  }

}
