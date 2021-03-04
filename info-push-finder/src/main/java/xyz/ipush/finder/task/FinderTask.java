package xyz.ipush.finder.task;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import xyz.ipush.common.dto.InfoSpiderDTO;
import xyz.ipush.finder.spider.IPushSpider;
import xyz.ipush.message.IPushKafkaProducer;

/**
 * 从kafka取任务
 *
 * @author jwei
 * @date 2021/3/2
 */
@Slf4j
@Component
public class FinderTask {

    @Autowired
    private IPushSpider iPushSpider;

    @KafkaListener(topics = IPushKafkaProducer.TOPIC_INFO_SPIDER_TASK)
    public void processMessage(String content) {
        InfoSpiderDTO infoSpiderDTO = JSON.parseObject(content, InfoSpiderDTO.class);
        iPushSpider.run(infoSpiderDTO);
    }
}
