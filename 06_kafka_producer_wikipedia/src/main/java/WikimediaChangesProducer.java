import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.net.URI;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Slf4j
public class WikimediaChangesProducer {

  public static void main(String[] args) throws InterruptedException {
    final String bootstrapServers = "localhost:29092";
    final String topic = "wikimedia.recentchange";
    final String url = "https://stream.wikimedia.org/v2/stream/recentchange";

    // create producer topic
    Properties properties = new Properties();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

    KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

    EventHandler eventHandler = new WikimediaChangeHandler(kafkaProducer, topic);
    EventSource eventSource = (new EventSource.Builder(eventHandler, URI.create(url))).build();

    //start the producer in another thread
    log.info("Start producer....");
    eventSource.start();

    TimeUnit.MINUTES.sleep(1);

  }
}
