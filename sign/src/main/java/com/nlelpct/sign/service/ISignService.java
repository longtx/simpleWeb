package com.nlelpct.sign.service;

import com.nlelpct.sign.entity.Sign;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nlelpct.sign.util.Result;
import com.nlelpct.sign.vo.SignVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author nlelpct
 * @since 2020-09-23
 */
public interface ISignService extends IService<Sign> {

    Result saveInfo(SignVo signVo);

    Result validatePhone(String phone);

    void batchOutPut(String ids, HttpServletRequest request, HttpServletResponse response) throws IOException;

    void batchAll(String cs, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
