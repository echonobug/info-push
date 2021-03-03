package xyz.ipush.finder.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.PhantomJSDownloader;
import xyz.ipush.common.contant.IPushConstant;

/**
 * 定制的 PhantomJSDownloader
 *
 * @author jwei
 * @date 2021/3/3
 */
public class IPushPhantomJsDownloader extends PhantomJSDownloader {
    @Override
    public Page download(Request request, Task task) {
        Page page = super.download(request, task);
        page.putField(IPushConstant.INFO_DEFINE_ID_KEY, task.getUUID());
        return page;
    }

    public IPushPhantomJsDownloader(String phantomJsCommand, String crawlJsPath) {
        super(phantomJsCommand, crawlJsPath);
    }
}
