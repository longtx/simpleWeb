package com.nlelpct.sign.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author nlelpct
 * @since 2020-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SignVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 参会人姓名
     */
    private String name;

    /**
     * 参会人手机号
     */
    private String phone;

    /**
     * 参会人所在单位名称
     */
    private String company;

    /**
     * 联系邮箱
     */
    private String email;

    /**
     * 0.不开发票 1.增值税专用发票 2.增值税普通发票
     */
    private Integer receiptType;

    /**
     * 单位全称
     */
    private String receiptName;

    /**
     * 纳税人识别号
     */
    private String receiptNum;

    /**
     * 单位地址
     */
    private String receiptAddress;

    /**
     * 银行开户账号
     */
    private String receiptAccount;

    /**
     * 开户行
     */
    private String receiptBank;

    /**
     * 单位电话
     */
    private String receiptPhone;

}
