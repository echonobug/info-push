package xyz.ipush.web.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.ipush.web.entity.ResponseEntity;
import xyz.ipush.web.entity.Subscription;
import xyz.ipush.web.service.SubscriptionService;
import xyz.ipush.web.util.SecurityContextUtil;
import xyz.ipush.web.vo.SubscriptionVO;

import javax.annotation.Resource;

/**
 * <p>
 * 订阅表  前端控制器
 * </p>
 *
 * @author jwei
 * @date 2021/03/06
 */
@CrossOrigin
@RestController
@RequestMapping("subscription")
public class SubscriptionController {
    @Resource
    private SubscriptionService subscriptionService;

    @ApiOperation("信息订阅")
    @PostMapping("subscribe")
    public ResponseEntity subscribe(@Validated @RequestBody SubscriptionVO subscriptionVO) {
        Subscription subscription = new Subscription();
        subscription.setInfoDefineId(subscriptionVO.getId());
        subscription.setUserId(SecurityContextUtil.getUser().getId());
        subscription.setCron(subscriptionVO.getCron());
        subscriptionService.save(subscription);
        return ResponseEntity.success("信息订阅成功！");
    }

    @ApiOperation("获取订阅信息分页数据")
    @GetMapping("list")
    public ResponseEntity list(@RequestParam(name = "page", defaultValue = "1") Integer page,
                               @RequestParam(name = "size", defaultValue = "5") Integer size) {
        return ResponseEntity.success(
                subscriptionService.list(page, size, SecurityContextUtil.getUser().getId())
        );
    }

    @ApiOperation("取消信息订阅")
    @GetMapping("cancel")
    public ResponseEntity cancel(String id) {
        subscriptionService.removeById(id);
        return ResponseEntity.success("取消订阅成功！");
    }

    @ApiOperation("更新信息订阅")
    @PostMapping("update")
    public ResponseEntity update(@RequestBody Subscription subscription) {
        subscriptionService.updateById(subscription);
        return ResponseEntity.success("更新信息订阅成功！");
    }

}
