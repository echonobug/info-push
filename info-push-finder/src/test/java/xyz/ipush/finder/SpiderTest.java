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
            "    \"find\": [\n" +
            "      {\n" +
            "        \"render\": true,\n" +
            "        \"request\": {\n" +
            "          \"url\": \"http://www.weather.com.cn/weather/101270101.shtml\",\n" +
            "          \"method\": \"get\",\n" +
            "          \"header\": \"\",\n" +
            "          \"cookies\": \"\"\n" +
            "        },\n" +
            "        \"parse\": [\n" +
            "          {\n" +
            "            \"parser\": \"jsoup\",\n" +
            "            \"content\": [\n" +
            "              {\n" +
            "                \"fieldName\": \" \",\n" +
            "                \"selector\": \"\",\n" +
            "                \"extra\": {}\n" +
            "              }\n" +
            "            ]\n" +
            "          },\n" +
            "          {\n" +
            "            \"parser\": \"xsoup\",\n" +
            "            \"content\": [\n" +
            "              {\n" +
            "                \"field_name\": \"wea\",\n" +
            "                \"selector\": \"//*[@id=\\\"7d\\\"]/ul/li[1]/p[1]/text()\",\n" +
            "                \"extra\": {}\n" +
            "              }\n" +
            "              {\n" +
            "                \"field_name\": \"tem\",\n" +
            "                \"selector\": \"//*[@id=\\\"7d\\\"]/ul/li[1]/p[2]/i/text()\",\n" +
            "                \"extra\": {}\n" +
            "              }\n" +
            "            ]\n" +
            "          }\n" +
            "        ]\n" +
            "      }\n" +
            "    ],\n" +
            "    \"send\": {\n" +
            "      \"type\": \"email_html\",\n" +
            "      \"content\": \"html\"\n" +
            "    }\n" +
            "  }";

    @Test
    void run() {
        InfoSpiderDTO infoSpiderDTO = new InfoSpiderDTO();
        infoSpiderDTO.setId("12345");
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