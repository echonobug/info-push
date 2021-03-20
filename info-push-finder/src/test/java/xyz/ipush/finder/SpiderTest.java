package xyz.ipush.finder;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.ipush.common.dto.InfoSpiderDTO;
import xyz.ipush.common.pojo.IPushInfoDefineContent;
import xyz.ipush.finder.spider.IPushSpider;

import javax.annotation.Resource;

/**
 * 爬虫测试类
 *
 * @author jwei
 * @date 2021/3/2
 */
@SpringBootTest
public class SpiderTest {
    @Resource
    private IPushSpider ipushSpider;

    private final String content = "{\n" +
            "  \"find\": [\n" +
            "    {\n" +
            "      \"parse\": [\n" +
            "        {\n" +
            "          \"parser\": \"JsonPath\",\n" +
            "          \"content\": [\n" +
            "            {\n" +
            "              \"extra\": {},\n" +
            "              \"selector\": \"$.data[0].currentRank\",\n" +
            "              \"fieldName\": \"rank\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"extra\": {},\n" +
            "              \"selector\": \"$.data[0].articleTitle\",\n" +
            "              \"fieldName\": \"title\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"extra\": {},\n" +
            "              \"selector\": \"$.data[0].articleDetailUrl\",\n" +
            "              \"fieldName\": \"url\"\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ],\n" +
            "      \"render\": false,\n" +
            "      \"request\": {\n" +
            "        \"url\": \"https://blog.csdn.net/phoenix/web/blog/hotRank?page=0&pageSize=25&child_channel=\",\n" +
            "        \"header\": \"\",\n" +
            "        \"method\": \"get\",\n" +
            "        \"cookies\": \"\"\n" +
            "      }\n" +
            "    }\n" +
            "  ],\n" +
            "  \"send\": {\n" +
            "    \"type\": \"email_html\",\n" +
            "    \"content\": \"hello\",\n" +
            "    \"subject\": \"csdn综合榜1\"\n" +
            "  }\n" +
            "}";

    @Test
    void run() {
        InfoSpiderDTO infoSpiderDTO = new InfoSpiderDTO();
        infoSpiderDTO.setId("123456");
        IPushInfoDefineContent iPushInfoDefineContent = JSONObject.parseObject(content, IPushInfoDefineContent.class);
        infoSpiderDTO.setFind(iPushInfoDefineContent.getFind());
        ipushSpider.run(infoSpiderDTO);
    }

    @Test
    void jsonParse() {

        IPushInfoDefineContent iPushInfoDefineContent = JSONObject.parseObject(content, IPushInfoDefineContent.class);
        System.out.println(iPushInfoDefineContent);


    }

}
