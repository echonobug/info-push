package xyz.ipush.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ipush.web.service.InfoDefineService;

import javax.annotation.Resource;

/**
 * <p>
 * 信息定义  前端控制器
 * </p>
 *
 * @author jwei
 * @date 2021/03/02
 */
@RestController
@RequestMapping("infodefine")
public class InfoDefineController {
    @Resource
    private InfoDefineService infoDefineService;

    @GetMapping("find")
    public void find(String id) {
        infoDefineService.testFind(id);
    }

    @GetMapping("send")
    public void send(String id) {
        infoDefineService.testSend(id);
    }
}
