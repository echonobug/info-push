package xyz.ipush.finder.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import xyz.ipush.common.contant.IPushConstant;
import xyz.ipush.common.dto.InfoSpiderDTO;
import xyz.ipush.common.pojo.IPushFind;
import xyz.ipush.common.pojo.IPushRequest;

import java.util.LinkedList;
import java.util.List;

/**
 * 网页爬虫
 *
 * @author jwei
 * @date 2021/3/2
 */
@Component
public class IPushSpider {
    @Autowired
    private IPushPageProcessor ipushPageProcessor;
    @Autowired
    private IPushPipeline ipushPipeline;
    @Autowired
    private IPushHttpClientDownloader ipushHttpClientDownloader;
    @Autowired
    private IPushPhantomJsDownloader iPushPhantomJsDownloader;

    public void run(InfoSpiderDTO infoSpiderDTO) {
        List<IPushFind> find = infoSpiderDTO.getFind();
        if (find.size() == 0) {
            return;
        }
        List<Request> requests = new LinkedList<>();
        boolean render = false;
        for (IPushFind iPushFind : find) {
            if (iPushFind.getRender()) {
                render = true;
            }
            Request request = new Request();
            IPushRequest findRequest = iPushFind.getRequest();
            request.setUrl(findRequest.getUrl());
            request.setMethod(findRequest.getMethod());
            request.putExtra(IPushConstant.REQUEST_PARSE_KEY, iPushFind.getParse());
            requests.add(request);
        }
        Spider.create(ipushPageProcessor)
                .startRequest(requests)
                .addPipeline(ipushPipeline)
                .setUUID(infoSpiderDTO.getId())
                .setDownloader(render ? iPushPhantomJsDownloader : ipushHttpClientDownloader)
                .thread(requests.size() > 5 ? 5 : requests.size())
                .run();
    }
}
