import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

@Slf4j
public class WikimediaChangeHandler implements EventHandler {

  private final KafkaProducer<String, String> kafkaProducer;
  private final String topic;

  public WikimediaChangeHandler(KafkaProducer<String, String> kafkaProducer, String topic) {
    this.kafkaProducer = kafkaProducer;
    this.topic = topic;
  }

  @Override
  public void onOpen() {
    log.info("onOpen");
  }

  @Override
  public void onClosed() {
    log.info("onClose");
    kafkaProducer.close();
  }

  @Override
  public void onMessage(String s, MessageEvent messageEvent) {
    log.info("onMessage data: {}", messageEvent.getData());
    kafkaProducer.send(new ProducerRecord<>(topic, messageEvent.getData()));
  }

  @Override
  public void onComment(String s) { }

  @Override
  public void onError(Throwable throwable) {
    log.error("error -> {}", throwable.getMessage());
  }
}
