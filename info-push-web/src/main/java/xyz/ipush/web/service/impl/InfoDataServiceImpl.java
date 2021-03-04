package xyz.ipush.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import xyz.ipush.common.dto.InfoDataDTO;
import xyz.ipush.message.IPushKafkaProducer;
import xyz.ipush.web.entity.InfoData;
import xyz.ipush.web.mapper.InfoDataMapper;
import xyz.ipush.web.service.InfoDataService;

/**
 * <p>
 * 根据信息定义获取的数据  服务实现类
 * </p>
 *
 * @author jwei
 * @since 2021/03/02
 */
@Slf4j
@Service
public class InfoDataServiceImpl extends ServiceImpl<InfoDataMapper, InfoData> implements InfoDataService {

    @Override
    @KafkaListener(topics = IPushKafkaProducer.TOPIC_INFO_DATA)
    public void processMessage(String content) {
        InfoDataDTO infoDataDto = JSON.parseObject(content, InfoDataDTO.class);
        InfoData infoData = new InfoData();
        infoData.setInfoDefineId(infoDataDto.getDefineId());
        infoData.setContent(JSON.toJSONString(infoDataDto.getContent()));
        save(infoData);
    }

}
