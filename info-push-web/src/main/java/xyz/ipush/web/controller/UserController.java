package xyz.ipush.web.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.ipush.web.entity.ResponseEntity;
import xyz.ipush.web.exception.IPushException;
import xyz.ipush.web.service.UserService;
import xyz.ipush.web.vo.UserLoginVO;
import xyz.ipush.web.vo.UserRegisterVO;

/**
 * <p>
 * 用户表  前端控制器
 * </p>
 *
 * @author jwei
 * @date 2020/12/16
 */
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("获取系统用户列表")
    @GetMapping("list")
    ResponseEntity list() {
        return ResponseEntity.success(userService.list());
    }

    @ApiOperation("用户登录")
    @PostMapping("login")
    ResponseEntity login(@Validated @RequestBody UserLoginVO userVO) {
        try {
            return ResponseEntity.success(userService.login(userVO));
        } catch (IPushException e) {
            return ResponseEntity.error(e);
        }
    }

    @ApiOperation("获取当前登录用户信息")
    @GetMapping("info")
    ResponseEntity info() {
        return ResponseEntity.success(userService.getInfo());
    }

    @ApiOperation("用户注册")
    @PostMapping("register")
    ResponseEntity register(@Validated @RequestBody UserRegisterVO userVO) {
        try {
            userService.register(userVO);
            return ResponseEntity.success();
        } catch (IPushException e) {
            return ResponseEntity.error(e);
        }
    }

    @ApiOperation("账号激活")
    @GetMapping("active")
    ResponseEntity active(@ApiParam("账号信息") String content) {
        try {
            userService.active(content);
        } catch (IPushException e) {
            return ResponseEntity.error(e);
        }
        return ResponseEntity.success("激活成功！");
    }

    @ApiOperation("验证用户名是否可用")
    @GetMapping("validateUsername")
    ResponseEntity validateUsername(@ApiParam("用户名") String username) {
        try {
            userService.validateUsername(username);
        } catch (IPushException e) {
            return ResponseEntity.error(e);
        }
        return ResponseEntity.success("该用户名可用！");
    }

}
