package xyz.ipush.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import xyz.ipush.common.contant.IPushConstant;
import xyz.ipush.common.dto.EmailHtmlDTO;
import xyz.ipush.common.dto.InfoSpiderDTO;
import xyz.ipush.common.pojo.IPushInfoDefineContent;
import xyz.ipush.common.pojo.IPushSend;
import xyz.ipush.message.IPushKafkaProducer;
import xyz.ipush.web.dto.InfoDTO;
import xyz.ipush.web.entity.*;
import xyz.ipush.web.mapper.InfoDefineMapper;
import xyz.ipush.web.mapper.InfoMapper;
import xyz.ipush.web.scheduler.job.IPushJob;
import xyz.ipush.web.service.*;

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
    @Resource
    private SubscriptionService subscriptionService;
    @Resource
    private UserService userService;
    @Resource
    private JobService jobService;
    @Resource
    private InfoMapper infoMapper;


    @Override
    public void find(String id) {
        Job job = new Job();
        job.setGroupName(IPushJob.FIND);
        job.setJobName(id);
        InfoDefine infoDefine = getById(id);
        if (infoDefine != null) {
            IPushInfoDefineContent iPushInfoDefineContent = JSON.parseObject(infoDefine.getContent(), IPushInfoDefineContent.class);
            InfoSpiderDTO infoSpiderDTO = new InfoSpiderDTO();
            infoSpiderDTO.setId(infoDefine.getId());
            infoSpiderDTO.setFind(iPushInfoDefineContent.getFind());
            iPushKafkaProducer.send(IPushKafkaProducer.TOPIC_INFO_SPIDER_TASK, JSON.toJSONString(infoSpiderDTO));
            job.setJobStatus(IPushConstant.JOB_SUCCESS);
        } else {
            job.setJobStatus(IPushConstant.JOB_FAIL);
            job.setDescription("未找到id为" + id + "的信息定义");
        }
        jobService.save(job);
    }

    @Override
    public void send(String id) {
        Job job = new Job();
        job.setGroupName(IPushJob.SEND);
        job.setJobName(id);
        Subscription subscription = subscriptionService.getById(id);
        InfoDefine infoDefine = null;
        if (subscription != null) {
            infoDefine = getById(subscription.getInfoDefineId());
        }
        if (infoDefine != null) {
            IPushInfoDefineContent iPushInfoDefineContent = JSON.parseObject(infoDefine.getContent(), IPushInfoDefineContent.class);
            IPushSend iPushSend = iPushInfoDefineContent.getSend();
            InfoData infoData = infoDataService.query().eq("info_define_id", infoDefine.getId()).one();
            User user = userService.getById(subscription.getUserId());
            EmailHtmlDTO emailHtmlDTO = new EmailHtmlDTO();
            emailHtmlDTO.setSubject(iPushSend.getSubject());
            emailHtmlDTO.setTo(Collections.singletonList(user.getEmail()));
            emailHtmlDTO.setModel(JSON.parseObject(infoData.getContent()));
            emailHtmlDTO.setHtml(iPushSend.getContent());
            iPushKafkaProducer.send(IPushKafkaProducer.TOPIC_EMAIL_INFO, JSON.toJSONString(emailHtmlDTO));
            job.setJobStatus(IPushConstant.JOB_SUCCESS);
        } else {
            job.setJobStatus(IPushConstant.JOB_FAIL);
            job.setDescription("未找到id为" + id + "的订阅记录或对应的信息定义未找到！");
        }
        jobService.save(job);
    }

    @Override
    public PageInfo<InfoDTO> list(Integer page, Integer size, String keyword, String userId) {
        PageHelper.startPage(page, size);
        List<InfoDTO> infos = infoMapper.listWithIfConcerned(keyword.length() > 0 ? "%" + keyword + "%" : null, userId);
        return new PageInfo<>(infos);
    }
}
