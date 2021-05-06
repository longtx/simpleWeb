package com.nlelpct.sign.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nlelpct.sign.entity.Sign;
import com.nlelpct.sign.mapper.SignMapper;
import com.nlelpct.sign.service.ISignService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nlelpct.sign.util.DateUtil;
import com.nlelpct.sign.util.ExcelUtil;
import com.nlelpct.sign.util.Result;
import com.nlelpct.sign.vo.SignVo;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author nlelpct
 * @since 2020-09-23
 */
@Service
public class SignServiceImpl extends ServiceImpl<SignMapper, Sign> implements ISignService {


    @Override
    public Result saveInfo(SignVo signVo) {
        // 1.校验数据
        Result result = validate(signVo);
        if (result.getStatus() != Result.OK) {
            return result;
        }
        // 2.保存
        Sign sign = new Sign();
        BeanUtils.copyProperties(signVo, sign);
        sign.setId(UUID.randomUUID().toString().replace("-", ""))
                .setCreateTime(new Date());
        this.save(sign);
        return Result.ok("保存成功");
    }

    @Override
    public Result validatePhone(String phone) {
        int count = this.count(new QueryWrapper<Sign>().eq(Sign.T_PHONE, phone));
        if (count > 0) {
            return Result.err("该手机号已经重复");
        }
        return Result.ok("该手机号可用");
    }

    @Override
    public void batchOutPut(String ids, HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> qid = Arrays.asList(ids.split(","));
        downloadFile(request,response,this.batchByBean(this.listByIds(qid),request));
    }

    @Override
    public void batchAll(String cs, HttpServletRequest request, HttpServletResponse response) throws IOException {
        downloadFile(request,response,this.batchByBean(this.list(new QueryWrapper<Sign>().orderByDesc(Sign.T_CREATE_TIME)),request));
    }

    private File batchByBean(List<Sign> signs,HttpServletRequest request) {
        try {
            String[] title = {"参会人姓名","参会人手机号码","参会人所在单位名称","联系邮箱","发票类型","单位全称/名称","纳税人识别号","单位地址","单位电话","银行开户账号","开户行","报名提交时间"};
            File excel = new File(request.getSession().getServletContext().getRealPath("/")+DateUtil.getStringDate(new Date()));
            WritableWorkbook wwb = Workbook.createWorkbook(excel);
            //sheet名
            WritableSheet sheet = wwb.createSheet("准考证导出信息", 0);
            String zt = request.getParameter("zt");
            sheet.setColumnView(0, 15);
            sheet.setColumnView(1, 50);
            sheet.setColumnView(2, 50);
            sheet.setColumnView(3, 50);
            sheet.setColumnView(4, 30);
            sheet.setColumnView(5, 25);
            sheet.setColumnView(6, 25);
            sheet.setColumnView(7, 25);
            sheet.setColumnView(8, 50);
            sheet.setColumnView(9, 50);
            sheet.setColumnView(10, 50);
            sheet.setColumnView(11, 50);
            sheet.setRowView(0, 500);

            Map mp = ExcelUtil.createWritableFontExcel(title,sheet);
            //内容 j:行
            for(int j=1;j<signs.size()+1;j++){
                Sign entity = signs.get(j-1);

                //姓名
                Label label02 = null;
                label02 = new Label(0,j, ExcelUtil.objectToNoEmpty(entity.getName()));
                sheet.addCell(label02);

                //手机号
                Label label03 = null;
                label03 = new Label(1,j, ExcelUtil.objectToNoEmpty(entity.getPhone()));
                sheet.addCell(label03);
                //身份证号
                Label label04 = null;
                label04 = new Label(2,j, ExcelUtil.objectToNoEmpty(entity.getCompany()));
                sheet.addCell(label04);
                //邮箱
                Label label05 = null;
                label05 = new Label(3,j, ExcelUtil.objectToNoEmpty(entity.getEmail()));
                sheet.addCell(label05);
                //发票类型  0.不开发票 1.增值税专用发票 2.增值税普通发票
                Label label06 = null;
                label06 = new Label(4,j, entity.getReceiptType()==0?"不开发票":entity.getReceiptType()==1?"增值税专用发票":entity.getReceiptType()==2?"增值税普通发票":"");
                sheet.addCell(label06);
                //单位名称
                Label label07 = null;
                label07 = new Label(5,j, ExcelUtil.objectToNoEmpty(entity.getReceiptName()));
                sheet.addCell(label07);
                //纳税人识别号
                Label label08 = null;
                label08 = new Label(6,j, ExcelUtil.objectToNoEmpty(entity.getReceiptNum()));
                sheet.addCell(label08);
                //单位地址
                Label label09 = new Label(7,j, ExcelUtil.objectToNoEmpty(entity.getReceiptAddress()));
                sheet.addCell(label09);
                //单位电话
                Label label10 = new Label(8,j, ExcelUtil.objectToNoEmpty(entity.getReceiptPhone()));
                sheet.addCell(label10);
                //银行开户账号
                Label label11 = new Label(9,j, ExcelUtil.objectToNoEmpty(entity.getReceiptAccount()));
                sheet.addCell(label11);
                //开户行
                Label label12 = new Label(10,j, ExcelUtil.objectToNoEmpty(entity.getReceiptBank()));
                sheet.addCell(label12);
                //报名提交时间
                Label label13 = new Label(11,j,ExcelUtil.objectToNoEmpty(DateUtil.getStringDate(entity.getCreateTime())));
                sheet.addCell(label13);
            }
            wwb.write();
            wwb.close();
            return excel;
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 文件下载
     * @param request
     * @param response
     * @param excel
     * @throws IOException
     */
    public void downloadFile(HttpServletRequest request,
                             HttpServletResponse response, File excel) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(excel));
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = bis.read(buff, 0, 100)) > -1) {
            swapStream.write(buff, 0, rc);
        }
        bis.close();
        byte[] buffer = swapStream.toByteArray();

        String name = excel.getName();
        String userAgent = request.getHeader("USER-AGENT");
        if (null != userAgent) {
            if (-1 != userAgent.indexOf("MSIE")) { // IE
                name = URLEncoder.encode(name, "UTF-8");
            } else if (-1 != userAgent.indexOf("Firefox") &&
                    !userAgent.contains("AppleWebKit")) { // Firefox
                name = new String(name.getBytes("UTF-8"), "ISO8859-1");
            } else if (userAgent.contains("AppleWebKit")) { // Google Chrome
                name = new String(name.getBytes("GBK"), "ISO-8859-1");
            } else {
                name = URLEncoder.encode(name, "UTF-8");
            }
        }

        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;filename="+name);
        response.addHeader("Content-Length", "" + buffer.length);
        BufferedOutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
    }

    private Result validate(SignVo signVo) {
        // 1.校验非空字段
        // 2.查重
        return validatePhone(signVo.getPhone());
    }
}
