package com.adiaz.kafkaspringproducer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HomeController {

  @Autowired
  KafkaProducerService publishKafkaService;

  @GetMapping("/publish")
  public ResponseEntity<String> publishMessage(@RequestParam String message) {
    try {
      RecordMetadata recordMetadata = publishKafkaService.sendMessage(message);
      String myBody = String.format("published <br> topic: %s <br> partition: %s <br> offset: %s",
              recordMetadata.topic(), recordMetadata.offset(), recordMetadata.offset());
      return ResponseEntity.ok(myBody);
    } catch (Exception e) {
      String msgError = "Error on publishing message -> " + e.getMessage();
      log.error(msgError, e);
      return ResponseEntity.ok(msgError);
    }
  }
}
