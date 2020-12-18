package ipush.service.impl;

import ipush.entity.AccessLog;
import ipush.mapper.AccessLogMapper;
import ipush.service.AccessLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 访问日志  服务实现类
 * </p>
 *
 * @author jwei
 * @since 2020/12/18
 */
@Service
public class AccessLogServiceImpl extends ServiceImpl<AccessLogMapper, AccessLog> implements AccessLogService {
	
}