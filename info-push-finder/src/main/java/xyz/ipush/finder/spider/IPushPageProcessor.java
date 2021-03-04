package xyz.ipush.finder.spider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import xyz.ipush.common.contant.IPushConstant;
import xyz.ipush.common.pojo.IPushField;
import xyz.ipush.common.pojo.IPushParse;

import java.util.List;

/**
 * 爬取页面内容处理
 *
 * @author jwei
 * @date 2021/3/2
 */
@Component
@Slf4j
public class IPushPageProcessor implements PageProcessor {
    /**
     * 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
     */
    private final Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        List<IPushParse> parseList = page.getRequest().getExtra(IPushConstant.REQUEST_PARSE_KEY);
        for (IPushParse parse : parseList) {
            List<IPushField> content = parse.getContent();
            switch (parse.getParser()) {
                case IPushParse.XSOUP:
                    content.forEach(f -> page.putField(f.getFieldName(), page.getHtml().xpath(f.getSelector()).toString()));
                    break;
                case IPushParse.JSOUP:
                    log.warn("暂不支持的HTML解析器:{}", parse.getParser());
                    break;
                default:
                    log.warn("不支持的HTML解析器:{}", parse.getParser());
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
