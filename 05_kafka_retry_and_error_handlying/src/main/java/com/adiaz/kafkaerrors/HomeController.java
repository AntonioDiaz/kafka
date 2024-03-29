package com.adiaz.kafkaerrors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @GetMapping("messages")
  public ResponseEntity<String> messages() {
    if (CustomKafkaListener.getMessages().isEmpty()) {
      return ResponseEntity.ok("NO MESSAGES YET :(");
    }
    return ResponseEntity.ok(String.join("\n<br>", CustomKafkaListener.getMessages()));
  }

}
