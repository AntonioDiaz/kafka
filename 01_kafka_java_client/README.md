# 01 - Kafka java client

Example consumer and producer.
* https://developer.confluent.io/tutorials/kafka-console-consumer-read-specific-offsets-partitions/kafka.html
* https://www.baeldung.com/java-kafka-consumer-api-read

## How to test on local
* Install Apache Kafka: https://kafka.apache.org/downloads 
* Needs Docker Desktop running.
* Start a Container with a Kafka broker on local: 
````shell
docker compose up -d
````

* Command: create the topic: example-topic
````shell
 kafka-topics.sh \
  --create \
  --topic example-topic \
  --boot
````

* Command: start a consumer listening on example-topic:
````shell
kafka-console-consumer \
--topic example-topic \
--bootstrap-server localhost:29092 \
--from-beginning \
--property print.key=true \
--property key.separator="-" \
--partition 0
--group customer_application
````


