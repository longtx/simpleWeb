package com.nlelpct.sign.controller;

import com.nlelpct.sign.entity.User;
import com.nlelpct.sign.service.ISignService;
import com.nlelpct.sign.service.IUserService;
import com.nlelpct.sign.util.Result;
import com.nlelpct.sign.vo.SignVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * author:Administrator
 * createTime:2020/9/23 11:29
 * function:
 * description: 登入登出
 */
@RestController
@RequestMapping("login")
public class LoginLoginOutController {
    @Autowired
    private IUserService iUserService;

    @ApiOperation(value = "用户登入", notes = "用户登入")
    @PostMapping(value = "/in")
    public Result in(@RequestBody User user,
                     HttpServletRequest request) {
        return iUserService.in(user.getUsername(),user.getPassword());
    }

    @ApiOperation(value = "用户登出", notes = "用户登出")
    @GetMapping(value = "/out")
    public Result validate(@RequestParam String username) {
        return iUserService.out(username);
    }
}
