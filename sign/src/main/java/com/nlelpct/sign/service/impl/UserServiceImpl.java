package com.nlelpct.sign.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nlelpct.sign.entity.User;
import com.nlelpct.sign.mapper.UserMapper;
import com.nlelpct.sign.service.IUserService;
import com.nlelpct.sign.util.Result;
import org.springframework.stereotype.Service;

/**
 * author:Administrator
 * createTime:2020/9/23 15:15
 * function:
 * description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public Result in(String username, String password) {
        Result ret = new Result();
        if((null!=username && null !=password && username.equals("admin") && password.equals("gdops@2020")) ||
                (null!=username && null !=password && username.equals("kaoshi") && password.equals("KS@2020!@#"))) {
            ret.setStatus( Result.OK);
        } else {
            ret.setStatus(Result.ERR);
            ret.setMsg("账号或密码错误，登录失败！");
        }
        return ret;
    }

    @Override
    public Result out(String username) {
        return null;
    }
}
