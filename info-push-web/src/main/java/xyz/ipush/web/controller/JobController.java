package xyz.ipush.web.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xyz.ipush.web.entity.ResponseEntity;
import xyz.ipush.web.service.JobService;

import javax.annotation.Resource;

/**
 * <p>
 * 任务执行记录  前端控制器
 * </p>
 *
 * @author jwei
 * @date 2021/03/07
 */
@CrossOrigin
@RestController
@RequestMapping("job")
public class JobController {

    @Resource
    private JobService jobService;

    @ApiOperation("获取任务执行日志")
    @GetMapping("log")
    public ResponseEntity log(@RequestParam(name = "page", defaultValue = "1") Integer page,
                              @RequestParam(name = "size", defaultValue = "5") Integer size,
                              String groupName, String jobName) {
        return ResponseEntity.success(jobService.log(groupName, jobName, page, size));
    }

}
