package xyz.ipush.finder.spider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * 爬虫配置类
 *
 * @author jwei
 * @date 2021/3/3
 */
@Configuration
public class IPushSpiderConfig {

    /**
     * phantomjs 执行路径
     */
    @Value("${ipush.finder.phantomjs.command}")
    private String command;
    private final String crawlJsPath = new File(this.getClass().getResource("/js/crawl.js").getPath()).getPath();


    @Bean
    IPushPhantomJsDownloader iPushPhantomJsDownloader() {
        return new IPushPhantomJsDownloader(command, crawlJsPath);
    }
}
