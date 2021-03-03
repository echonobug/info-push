package xyz.ipush.finder.spider;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Ipush Pipeline
 *
 * @author jwei
 * @date 2021/3/2
 */
@Component
public class IPushPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println(resultItems);
    }
}
