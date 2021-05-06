package com.nlelpct.sign.controller;


import com.nlelpct.sign.service.ISignService;
import com.nlelpct.sign.util.Result;
import com.nlelpct.sign.vo.SignVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author nlelpct
 * @since 2020-09-23
 */
@Controller
@RequestMapping("/sign")
public class SignController {

    @Autowired
    private ISignService signService;

    @ApiOperation(value = "保存报名信息", notes = "填写资料---> 保存")
    @PostMapping(value = "/save")
    @ResponseBody
    public Result save(@RequestBody SignVo signVo) {
        return signService.saveInfo(signVo);
    }

    @ApiOperation(value = "手机号查重", notes = "填写资料---> 校验")
    @GetMapping(value = "/validate")
    @ResponseBody
    public Result validate(@RequestParam String phone) {
        return signService.validatePhone(phone);
    }

    @RequestMapping("/")
    public String redirectToQuery() {
        return "redirect:/sign/index.html";
    }
}

