package xyz.ipush.finder.spider;

import us.codecraft.webmagic.downloader.PhantomJSDownloader;

/**
 * 定制的 PhantomJSDownloader
 *
 * @author jwei
 * @date 2021/3/3
 */
public class IPushPhantomJsDownloader extends PhantomJSDownloader {
    public IPushPhantomJsDownloader(String phantomJsCommand, String crawlJsPath) {
        super(phantomJsCommand, crawlJsPath);
    }
}
