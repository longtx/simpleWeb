package com.nlelpct.sign.util;


public class CodeMsg {


    private int code;
    private String msg;

    //全局通用
    public static final CodeMsg SERVER_ERROR = new CodeMsg(520520, "别慌，服务端异常！");
    public static final CodeMsg DATA_ERROR = new CodeMsg(400100, "别慌，常规数据有误！%s");
    public static final CodeMsg PARAMS_NULL = new CodeMsg(400100, "参数不能为空！%s");
    public static final CodeMsg OSS_ERROR = new CodeMsg(100000, "操作OSS出现异常");

    //用户和企业管理模块
    public static final String REGISTER_SUCCESS = "注册成功！";
    public static final String RESET_PASS_SUCCESS = "密码已重置成功！";
    public static final String RESET_PASS_FAILED = "修改失败！";
    public static final CodeMsg NO_SUCH_USER = new CodeMsg(500101, "当前系统不存在：%s账号，请重新输入！");
    public static final CodeMsg USER_NOT_EXIST = new CodeMsg(500101, "账号不存在！");
    public static final CodeMsg USER_FORBIDEN = new CodeMsg(500101, "您的用户账号已被禁用！如需继续使用请联系 Tianrui@qq.com");
    public static final CodeMsg CAN_NOT_FIND = new CodeMsg(500102, "不好意思，找不到：%s");
    public static final CodeMsg CAN_NOT_BE_EMPTY = new CodeMsg(500103, "参数不能为空或空字符串：%s");
    public static final CodeMsg INVALID_PHONE = new CodeMsg(500104, "手机格式不合法：%s");
    public static final CodeMsg THE_PHONE_REGISTED = new CodeMsg(500105, "该手机号已经注册");
    public static final CodeMsg SMSCODE_ERROR = new CodeMsg(500106, "验证码错误");
    public static final CodeMsg CAN_NOT_FIND_LOGO_BY_COMPANY_ID = new CodeMsg(500107, "找不到该企业的LOGO：%s");
    public static final CodeMsg PASSWORD_RESET_ERROR_01 = new CodeMsg(500108, "新密码与原密码不能一样");
    public static final CodeMsg PASSWORD_ERROR_01 = new CodeMsg(500109, "密码不能与账号相同");
    public static final CodeMsg PASSWORD_RESET_FAILED_ERROR = new CodeMsg(500110, "修改失败！");
    public static final CodeMsg KEY_EXIST = new CodeMsg(5001011, "KEY值已经存在：%s");
    ;
    public static final CodeMsg OVER_LIMITS = new CodeMsg(400100, "企业人数已达最大限制！");
    public static final CodeMsg NO_REGISTED = new CodeMsg(5001013, "当前系统不存在该手机号，下一步完善员工信息后自动添加账号：%s");
    public static final CodeMsg USER_STATUS_UNHEALTHY = new CodeMsg(5001014, "该账号状态异常！：%s");
    public static final CodeMsg AUTHORISE_FAIL = new CodeMsg(500201, "账号或密码有误！");
    public static final CodeMsg NO_SUCH_SYSTEM = new CodeMsg(500202, "没有该系统：JWT授权失败！");
    public static final CodeMsg SYSTEM_PASSWORD_ERROR = new CodeMsg(500203, "系统密码错误：JWT授权失败！");
    public static final CodeMsg TOKEN_ERROR_EXPIRED = new CodeMsg(500203, "TOKEN过期，请重新登录！");
    public static final CodeMsg TOKEN_ERROR_INVALID = new CodeMsg(500203, "TOKEN不合法，请重新登录！");
    public static final CodeMsg EXPIRED_IN_REDIS = new CodeMsg(500203, "你已被登出，请重新登录！");
    public static final CodeMsg ACCOUNT_IS_TEMP_FORBIDDEN = new CodeMsg(500204, "错误次数过多，请%s分钟之后再试！");
    public static final CodeMsg PASSWORD_OVER_DATE = new CodeMsg(500205, "当前密码的使用已超过%s天，为了使用安全，请重置密码!");
    public static final CodeMsg IMAGE_CODE_EXPIRED = new CodeMsg(500206, "验证码已经过期！");
    public static final CodeMsg IMAGE_CODE_ERR = new CodeMsg(500207, "验证码错误！");
    public static final CodeMsg ACCOUNT_IS_DISABLED = new CodeMsg(500301, "您的账号已禁用！");
    public static final CodeMsg ACCOUNT_OR_PASSWORD_ERROR = new CodeMsg(500302, "账号或者密码不正确！");
    public static final CodeMsg TRYINGNOMORE = new CodeMsg(500303, "尝试次数过多！");

    // 是否签署协议
    public static final CodeMsg NOT_SIGNED_AGREEMENT = new CodeMsg(500401, "您还未签署协议！");
    public static final CodeMsg SIGNED_AGREEMENT_NOT_LATEST = new CodeMsg(500402, "您签署的协议有最新版本,请重新签署！");

    //HTTP服务模块
    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(msg, args);
        return new CodeMsg(code, message);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
