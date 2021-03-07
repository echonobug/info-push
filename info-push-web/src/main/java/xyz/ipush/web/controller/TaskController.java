package xyz.ipush.web.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xyz.ipush.web.entity.ResponseEntity;
import xyz.ipush.web.service.TaskService;

import javax.annotation.Resource;

/**
 * 任务管理 API
 *
 * @author jwei
 * @date 2021/3/7
 */
@CrossOrigin
@RestController
@RequestMapping("task")
public class TaskController {

    @Resource
    private TaskService taskService;

    @ApiOperation("获取任务信息分页数据")
    @GetMapping("list")
    public ResponseEntity list(@RequestParam(name = "page", defaultValue = "1") Integer page,
                               @RequestParam(name = "size", defaultValue = "5") Integer size) {
        return ResponseEntity.success(
                taskService.list(page, size)
        );
    }
}
