package com.nlelpct.sign.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("t_sign")
public class Sign implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    /**
     * 参会人姓名
     */
    @TableField("t_name")
    private String name;

    /**
     * 参会人手机号
     */
    @TableField("t_phone")
    private String phone;

    /**
     * 参会人所在单位名称
     */
    @TableField("t_company")
    private String company;

    /**
     * 联系邮箱
     */
    @TableField("t_email")
    private String email;

    /**
     * 0.不开发票 1.增值税专用发票 2.增值税普通发票
     */
    @TableField("t_receipt_type")
    private Integer receiptType;

    /**
     * 单位全称
     */
    @TableField("t_receipt_name")
    private String receiptName;

    /**
     * 纳税人识别号
     */
    @TableField("t_receipt_num")
    private String receiptNum;

    /**
     * 单位地址
     */
    @TableField("t_receipt_address")
    private String receiptAddress;

    /**
     * 银行开户账号
     */
    @TableField("t_receipt_account")
    private String receiptAccount;

    /**
     * 开户行
     */
    @TableField("t_receipt_bank")
    private String receiptBank;

    /**
     * 单位电话
     */
    @TableField("t_receipt_phone")
    private String receiptPhone;

    /**
     * 创建时间
     */
    @TableField("t_create_time")
    private Date createTime;

    public static final String ID = "id";

    public static final String T_NAME = "t_name";

    public static final String T_PHONE = "t_phone";

    public static final String T_COMPANY = "t_company";

    public static final String T_EMAIL = "t_email";

    public static final String T_RECEIPT_TYPE = "t_receipt_type";

    public static final String T_RECEIPT_NAME = "t_receipt_name";

    public static final String T_RECEIPT_NUM = "t_receipt_num";

    public static final String T_RECEIPT_ADDRESS = "t_receipt_address";

    public static final String T_RECEIPT_ACCOUNT = "t_receipt_account";

    public static final String T_RECEIPT_BANK = "t_receipt_bank";

    public static final String T_RECEIPT_PHONE = "t_receipt_phone";

    public static final String T_CREATE_TIME = "t_create_time";

}
