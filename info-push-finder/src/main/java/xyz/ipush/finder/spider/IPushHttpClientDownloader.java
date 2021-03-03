package xyz.ipush.finder.spider;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import xyz.ipush.common.contant.IPushConstant;

import java.io.IOException;

/**
 * 定制的HttpClientDownloader
 *
 * @author jwei
 * @date 2021/3/2
 */
@Component
public class IPushHttpClientDownloader extends HttpClientDownloader {
    @Override
    protected Page handleResponse(Request request, String charset, HttpResponse httpResponse, Task task) throws IOException {
        Page page = super.handleResponse(request, charset, httpResponse, task);
        page.putField(IPushConstant.INFO_DEFINE_ID_KEY, task.getUUID());
        return page;
    }
}
