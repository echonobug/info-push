package xyz.ipush.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ipush.web.service.InfoDefineService;

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
    @Autowired
    private InfoDefineService infoDefineService;

    @GetMapping("find")
    public void find(String id) {
        infoDefineService.testFind(id);
    }
}
