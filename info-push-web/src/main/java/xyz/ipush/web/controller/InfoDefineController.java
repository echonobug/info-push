package xyz.ipush.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xyz.ipush.web.entity.ResponseEntity;
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
@CrossOrigin
@RestController
@RequestMapping("infoDefine")
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

    @ApiOperation("获取信息定义分页数据")
    @GetMapping("list")
    public ResponseEntity list(@RequestParam(name = "page", defaultValue = "1") Integer page,
                               @RequestParam(name = "size", defaultValue = "5") Integer size,
                               @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return ResponseEntity.success(
                infoDefineService.page(new Page<>(page, size),
                        infoDefineService.query().select("id", "title", "description", "image")
                                .like("title", keyword).or().like("description", keyword).getWrapper())
        );
    }

}
