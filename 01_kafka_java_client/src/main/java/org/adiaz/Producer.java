package org.adiaz;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.LocalDateTime;
import java.util.Properties;

public class Producer {

  public static void main(String[] args) {
    //propeties
    Properties properties = new Properties();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Consts.KAFKA_BOOTSTRAP);
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    // producer factory
    KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
    String key = "key1";
    String value = "Diego Simeone ->" + LocalDateTime.now().toString();
    ProducerRecord<String, String> record = new ProducerRecord<>(Consts.KAFKA_TOPIC, key, value);
    kafkaProducer.send(record);
    // producer publish message
    kafkaProducer.close();
  }
}
