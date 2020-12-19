package fun.jwei.ipush.web.controller;

import fun.jwei.ipush.web.entity.ResponseEntity;
import fun.jwei.ipush.web.service.UserService;
import fun.jwei.ipush.web.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("list")
    ResponseEntity list() {
        return ResponseEntity.success(userService.list());
    }

    @PostMapping("login")
    ResponseEntity login(@Validated @RequestBody UserVO userVO) {
        return ResponseEntity.success(userService.login(userVO));
    }


}
