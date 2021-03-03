package xyz.ipush.finder;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import xyz.ipush.common.dto.InfoSpiderDTO;
import xyz.ipush.message.IPushKafkaProducer;

/**
 * 从kafka取任务
 *
 * @author jwei
 * @date 2021/3/2
 */
@Slf4j
@Component
public class KafkaFinderTask {

    @KafkaListener(topics = IPushKafkaProducer.TOPIC_INFO_SPIDER_TASK)
    public void processMessage(String content) {
        InfoSpiderDTO infoSpiderDTO = JSON.parseObject(content, InfoSpiderDTO.class);

    }
}
