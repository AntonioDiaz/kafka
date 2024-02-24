# 04 - Kafka Custom Serializer

https://howtodoinjava.com/kafka/spring-boot-jsonserializer-example/
* Properties  
````properties
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer

spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer
````

* Start docker container broker.
* Create topic __topic-users__
```shell
  kafka-topics.sh \
  --create \
  --topic topic-users \
  --bootstrap-server localhost:29092
  ```
* Example of publishing:  
    http://localhost:8083/publish-user?name=Diego&surname=Godin

    ![image](https://github.com/AntonioDiaz/kafka/assets/725743/f6881eb8-c2f2-4c93-8f5a-a1f4aa60b055)


* Consuming messages:  
    http://localhost:8083/read-users
    ![image](https://github.com/AntonioDiaz/kafka/assets/725743/4af67441-c151-47a7-ad1a-289c36b5aaf6)



