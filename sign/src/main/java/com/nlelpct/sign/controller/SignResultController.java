package com.nlelpct.sign.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nlelpct.sign.entity.Sign;
import com.nlelpct.sign.service.ISignService;
import com.nlelpct.sign.util.CodeMsg;
import com.nlelpct.sign.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * author:Administrator
 * createTime:2020/9/23 11:29
 * function:
 * description: 报名结果
 */
@RestController
@RequestMapping("/api/signResult")
public class SignResultController {

    @Autowired
    private ISignService iSignService;

    @ApiOperation(value = "获取所有-（需要一个唯一标识码区分）", notes = "获取所有-（需要一个唯一标识码区分）")
    @PostMapping(value = "/list")
    public Result list(HttpServletRequest request) {
        return Result.buildOk("suc",iSignService.list(new QueryWrapper<Sign>().orderByDesc(Sign.T_CREATE_TIME)));
    }

    @ApiOperation(value = "批量或者全量导出-（需要一个唯一标识码区分）", notes = "批量或者全量导出-（需要一个唯一标识码区分）")
    @PostMapping(value = "/outPut")
    public void outPut(HttpServletRequest request, HttpServletResponse response) {
        try {
            String ids = request.getParameter("ids");
            if(ids != null && !ids.equals("")){
                ids = ids.substring(0,ids.length()-1);
                iSignService.batchOutPut(ids,request,response);
            } else {
                iSignService.batchAll("",request,response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
