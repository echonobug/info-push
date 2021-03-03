package xyz.ipush.finder.spider;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 爬取页面内容处理
 *
 * @author jwei
 * @date 2021/3/2
 */
@Component
public class IPushPageProcessor implements PageProcessor {
    /**
     * 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
      */
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        System.out.println(page);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
