package xyz.ipush.web.service.impl;

import xyz.ipush.web.entity.InfoData;
import xyz.ipush.web.mapper.InfoDataMapper;
import xyz.ipush.web.service.InfoDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 根据信息定义获取的数据  服务实现类
 * </p>
 *
 * @author jwei
 * @since 2021/03/02
 */
@Service
public class InfoDataServiceImpl extends ServiceImpl<InfoDataMapper, InfoData> implements InfoDataService {
	
}
