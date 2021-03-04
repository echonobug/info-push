package xyz.ipush.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import xyz.ipush.common.pojo.IPushFind;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.ipush.common.dto.InfoSpiderDTO;
import xyz.ipush.common.pojo.IPushInfoDefineContent;
import xyz.ipush.message.IPushKafkaProducer;
import xyz.ipush.web.entity.InfoDefine;
import xyz.ipush.web.mapper.InfoDefineMapper;
import xyz.ipush.web.service.InfoDefineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    @Autowired
    private IPushKafkaProducer iPushKafkaProducer;

    @Override
    public void testFind(String id) {
        InfoDefine infoDefine = getById(id);
        IPushInfoDefineContent iPushInfoDefineContent = JSONObject.parseObject(infoDefine.getContent(), IPushInfoDefineContent.class);
        InfoSpiderDTO infoSpiderDTO = new InfoSpiderDTO();
        infoSpiderDTO.setId(infoDefine.getId());
        infoSpiderDTO.setFind(iPushInfoDefineContent.getFind());
        iPushKafkaProducer.send(IPushKafkaProducer.TOPIC_INFO_SPIDER_TASK, JSON.toJSONString(infoSpiderDTO));
    }
}
