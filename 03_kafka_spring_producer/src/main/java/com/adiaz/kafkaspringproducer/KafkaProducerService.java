package com.adiaz.kafkaspringproducer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class KafkaProducerService {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Value("${topic_name}")
  private String topicName;

  public RecordMetadata sendMessage(String msg) throws Exception{
      CompletableFuture<SendResult<String, String>> send = kafkaTemplate.send(topicName, "key1", msg);
      SendResult<String, String> stringStringSendResult = send.get();
      log.debug(stringStringSendResult.toString());
      return send.get().getRecordMetadata();
  }
}
