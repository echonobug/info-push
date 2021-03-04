package xyz.ipush.finder.spider;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import xyz.ipush.common.dto.InfoDataDTO;
import xyz.ipush.message.IPushKafkaProducer;

/**
 * Ipush Pipeline
 *
 * @author jwei
 * @date 2021/3/2
 */
@Component
public class IPushPipeline implements Pipeline {

    @Autowired
    private IPushKafkaProducer iPushKafkaProducer;

    @Override
    public void process(ResultItems resultItems, Task task) {
        InfoDataDTO infoDataDto = new InfoDataDTO();
        infoDataDto.setDefineId(task.getUUID());
        infoDataDto.setContent(resultItems.getAll());
        iPushKafkaProducer.send(IPushKafkaProducer.TOPIC_INFO_DATA, JSON.toJSONString(infoDataDto));
    }
}
