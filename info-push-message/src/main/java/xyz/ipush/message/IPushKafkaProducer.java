package xyz.ipush.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * 消息生产者
 *
 * @author jwei
 * @date 2021/1/29
 */
@Component
public class IPushKafkaProducer {
    private final KafkaTemplate kafkaTemplate;

    public static final String TOPIC_DEFAULT = "default_topic";
    public static final String TOPIC_EMAIL_ACTIVE = "topic_email_active";
    public static final String TOPIC_INFO_SPIDER_TASK = "topic_info_spider_task";


    @Autowired
    public IPushKafkaProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void send(String topic, String message){
        kafkaTemplate.send(topic,message);
    }

}
