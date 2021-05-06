package com.nlelpct.sign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nlelpct.sign.entity.User;
import com.nlelpct.sign.util.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author nlelpct
 * @since 2020-09-23
 */
public interface IUserService extends IService<User> {

    Result in(String username,String password);

    Result out(String username);
}
