package xyz.ipush.web.service;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Map;

/**
 * subscription service test
 *
 * @author jwei
 * @date 2021/3/6
 */
@SpringBootTest
public class SubscriptionServiceTest {
    @Resource
    private SubscriptionService subscriptionService;

    @Test
    public void list() {
        PageInfo<Map<String, Object>> list = subscriptionService.list(1, 10, "1365979587283648513");
        Assert.notNull(list, "error");
    }

}
