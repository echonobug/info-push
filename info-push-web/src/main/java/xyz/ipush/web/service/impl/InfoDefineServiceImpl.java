package xyz.ipush.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.ipush.common.dto.EmailHtmlDTO;
import xyz.ipush.common.dto.InfoSpiderDTO;
import xyz.ipush.common.pojo.IPushInfoDefineContent;
import xyz.ipush.common.pojo.IPushSend;
import xyz.ipush.message.IPushKafkaProducer;
import xyz.ipush.web.entity.InfoData;
import xyz.ipush.web.entity.InfoDefine;
import xyz.ipush.web.mapper.InfoDefineMapper;
import xyz.ipush.web.service.InfoDataService;
import xyz.ipush.web.service.InfoDefineService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 信息定义  服务实现类
 * </p>
 *
 * @author jwei
 * @since 2021/03/02
 */
@Service
public class InfoDefineServiceImpl extends ServiceImpl<InfoDefineMapper, InfoDefine> implements InfoDefineService {

    @Resource
    private IPushKafkaProducer iPushKafkaProducer;
    @Resource
    private InfoDataService infoDataService;


    @Override
    public void testFind(String id) {
        InfoDefine infoDefine = getById(id);
        IPushInfoDefineContent iPushInfoDefineContent = JSON.parseObject(infoDefine.getContent(), IPushInfoDefineContent.class);
        InfoSpiderDTO infoSpiderDTO = new InfoSpiderDTO();
        infoSpiderDTO.setId(infoDefine.getId());
        infoSpiderDTO.setFind(iPushInfoDefineContent.getFind());
        iPushKafkaProducer.send(IPushKafkaProducer.TOPIC_INFO_SPIDER_TASK, JSON.toJSONString(infoSpiderDTO));
    }

    @Override
    public void testSend(String id) {
        InfoDefine infoDefine = getById(id);
        IPushInfoDefineContent iPushInfoDefineContent = JSON.parseObject(infoDefine.getContent(), IPushInfoDefineContent.class);
        IPushSend iPushSend = iPushInfoDefineContent.getSend();
        InfoData infoData = infoDataService.query().eq("info_define_id", id).one();
        EmailHtmlDTO emailHtmlDTO = new EmailHtmlDTO();
        emailHtmlDTO.setSubject(iPushSend.getSubject());
        emailHtmlDTO.setTo(Collections.singletonList("578796735@qq.com"));
        emailHtmlDTO.setModel(JSON.parseObject(infoData.getContent()));
        emailHtmlDTO.setHtml(iPushSend.getContent());
        iPushKafkaProducer.send(IPushKafkaProducer.TOPIC_EMAIL_INFO, JSON.toJSONString(emailHtmlDTO));
    }

}
