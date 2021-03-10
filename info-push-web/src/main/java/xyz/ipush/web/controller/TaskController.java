package xyz.ipush.web.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.ipush.web.entity.ResponseEntity;
import xyz.ipush.web.exception.IPushException;
import xyz.ipush.web.service.TaskService;
import xyz.ipush.web.vo.TaskKeyVO;

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

    @ApiOperation("上线任务")
    @PostMapping("online")
    public ResponseEntity online(@RequestBody @Validated TaskKeyVO taskKeyVO) {
        try {
            taskService.online(taskKeyVO);
            return ResponseEntity.success("任务上线成功！");
        } catch (IPushException e) {
            return ResponseEntity.error(e);
        }
    }

    @ApiOperation("下线任务")
    @PostMapping("offline")
    public ResponseEntity offline(@RequestBody @Validated TaskKeyVO taskKeyVO) {
        try {
            taskService.offline(taskKeyVO);
            return ResponseEntity.success("任务下线成功！");
        } catch (IPushException e) {
            return ResponseEntity.error(e);
        }
    }
}
