package org.adiaz;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class Consumer {
  public static void main(String[] args) {
    try {
      Properties properties = new Properties();
      properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Consts.KAFKA_BOOTSTRAP);
      properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
      properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
      properties.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
      properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
      KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
      TopicPartition partition0 = new TopicPartition(Consts.KAFKA_TOPIC, 0);
      kafkaConsumer.assign(List.of(partition0));
      ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofSeconds(10));
      System.out.printf("consumed messages -> %d\n", records.count());
      for (ConsumerRecord<String, String> record : records) {
        System.out.printf("partition = %d, offset = %d, key = %s, value = %s%n", record.partition(), record.offset(), record.key(), record.value());
      }

      kafkaConsumer.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}