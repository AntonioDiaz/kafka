<h1>Kafka Java Examples</h1> 

<!-- TOC -->
  * [Concepts](#concepts)
  * [01 - Kafka java client](#01---kafka-java-client)
  * [02 - Kafka Spring consumer](#02---kafka-spring-consumer)
  * [03 - Kafka Spring producer](#03---kafka-spring-producer)
  * [04 - Kafka Custom Serializer](#04---kafka-custom-serializer)
  * [05 - Kafka Retry and Error Handling](#05---kafka-retry-and-error-handling)
  * [06 - Kafka wikimedia producer](#06---kafka-wikimedia-producer)
  * [Run on local environment](#run-on-local-environment)
  * [Kafka commands](#kafka-commands)
<!-- TOC -->

## Concepts
* A Kafka `Consumer` is a client application that reads data from a Kafka cluster.
* Consumer subscribes to one or more `topics`, and consumes published messages. 
* `Producers` send messages to a topic, which is a category name where records are stored and published. 
* Topics are divided into several `partitions` to allow them to scale horizontally. 
* Each partition is an immutable sequence of messages.
* Consumers can read messages from a specific partition by specifying an `offset`, which is the position of the message within the partition.
* An `ack` (acknowledgment) is a message sent by a consumer to a Kafka `broker` to indicate that it has successfully processed a record. The consumer offset will be updated once the ack is sent.

* The `ack mode` determines when the broker updates the consumer’s offset. 
  * `auto-commit` the consumer sends an acknowledgment to the broker as **soon as it receives a message** 
  * `after-processing` the consumer only sends an acknowledgment to the broker **after it has successfully processed the message** 
  * `manual` the consumer waits until it **receives specific instructions** before sending an acknowledgment to the broker

* [There](https://docs.spring.io/spring-kafka/api/org/springframework/kafka/listener/ContainerProperties.AckMode.html) are several ack modes available that we can configure:
  * `AckMode.RECORD` In this after-processing mode, the consumer sends an acknowledgment for each message it processes. 
  * `AckMode.BATCH` In this manual mode, the consumer sends an acknowledgment for a batch of messages, rather than for each message. 
  * `AckMode.COUNT` In this manual mode, the consumer sends an acknowledgment after it has processed a specific number of messages. 
  * `AckMode.MANUAL` In this manual mode, the consumer does not send an acknowledgment for the messages it processes. 
  * `AckMode.TIME` In this manual mode, the consumer sends an acknowledgment after a certain amount of time has passed.


## 01 - Kafka java client
Example consumer and producer.
* https://developer.confluent.io/tutorials/kafka-console-consumer-read-specific-offsets-partitions/kafka.html
* https://www.baeldung.com/java-kafka-consumer-api-read

## 02 - Kafka Spring consumer
Example of Kafka consumer using [Kafka Spring Project](https://spring.io/projects/spring-kafka) 
* https://www.baeldung.com/spring-kafka

## 03 - Kafka Spring producer
Example of Kafka consumer using [Kafka Spring Project](https://spring.io/projects/spring-kafka)
* https://www.baeldung.com/spring-kafka

![image](https://github.com/AntonioDiaz/kafka/assets/725743/397637e1-b44f-4a1f-bc77-70289e983d10)

## 04 - Kafka Custom Serializer
* https://howtodoinjava.com/kafka/spring-boot-jsonserializer-example/

## 05 - Kafka Retry and Error Handling
* https://tech4gods.com/spring-kafka-retry-and-error-handling-guide/#2_Prerequisites_and_Setup
* https://www.baeldung.com/spring-retry-kafka-consumer

## 06 - Kafka wikimedia producer
Example from udemy course: https://www.udemy.com/course/apache-kafka/

## Run on local environment 
* Install Apache Kafka: https://kafka.apache.org/downloads
* Install [Docker Desktop](https://www.docker.com/products/docker-desktop/).
* Start a container with a Kafka broker, using docker-compose.yml
    ````shell
    docker compose up -d
    ````

  ![image](https://github.com/AntonioDiaz/kafka/assets/725743/bda6c589-47b7-41ea-a302-24ba2007fe16)

## Kafka commands

* Show topics
  ```shell
  kafka-topics.sh \
    --bootstrap-server localhost:29092 \
    --list 
  ```

* Delete topic
  ```shell
  kafka-topics.sh \
    --bootstrap-server localhost:29092 \
    --delete \
    --topic example-topic
  ```

* Create topic
  ```shell
   kafka-topics.sh \
    --bootstrap-server localhost:29092 \
    --topic example-topic \
    --create \
    --partitions 2
  ```
  
* Topic details
  ```shell
   kafka-topics.sh \
    --bootstrap-server localhost:29092 \
    --describe \
    --topic example-topic
  ```
  

  
* Publish message on a topic
  ```shell
  kafka-console-producer.sh \
    --bootstrap-server localhost:29092 \
    --topic example-topic \
    --property parse.key=true \
    --property key.separator=-
  ```

* Consuming messages
  ```shell
  kafka-console-consumer.sh \
    --bootstrap-server localhost:29092 \
    --topic example-topic \
    --from-beginning \
    --property print.key=true \
    --property key.separator=" - " \
    --property print.partition=true \
    --property print.offset=true \
    --group customer_application
  ```
  
* Show Consumer groups
  ```shell
  kafka-consumer-groups.sh \
    --bootstrap-server localhost:29092 \
    --list
  ```

* Show current offset of a consumer group
  ```shell
  kafka-consumer-groups.sh \
    --bootstrap-server localhost:29092 \
    --group customer_application \
    --describe
  ```

* Update offset for consumer group and topic
  ```shell
  kafka-consumer-groups.sh \
    --bootstrap-server localhost:29092 \
    --group customer_application \
    --topic example-topic \
    --reset-offsets \
    --to-earliest \
    --execute
  ```

## Partition assignment example
Target Partition
```
targetPartition = Math.abs(Utils.murmur2(keyBytes)) % (numPartitions -1)
```
Murmur2 java example [here](https://github.com/AntonioDiaz/kafka/blob/3085d5f6faa3bf8bfe8d51bc19c98d8f55d5d613/01_kafka_java_client/src/main/java/org/adiaz/TestingMurmur.java)

![image](https://github.com/AntonioDiaz/kafka/assets/725743/bf5be869-49f4-4bd4-b62f-0ac713576076)