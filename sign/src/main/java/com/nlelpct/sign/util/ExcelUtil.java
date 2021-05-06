package com.nlelpct.sign.util;

import jxl.format.UnderlineStyle;
import jxl.write.*;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/12/15.
 */
public class ExcelUtil {
    //保证线程安全
    private ExcelUtil() {
    }

    public static String getCellValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex);
        if (cell != null) {
            return getCellValue(cell);
        }
        return null;
    }
    public static SimpleDateFormat allDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DecimalFormat decimalFormat = new DecimalFormat("#");
    public static String getCellValue(Cell cell) {
        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                return allDateFormat.format(cell.getDateCellValue());
            }
            return decimalFormat.format(cell.getNumericCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            try {
                return decimalFormat.format(cell.getNumericCellValue());
            } catch (IllegalStateException e) {
                return String.valueOf(cell.getRichStringCellValue());
            }
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }


    public static Map<String ,Object> createWritableFontExcel(String[] title, WritableSheet sheet) throws IOException, WriteException {
        Map<String,Object> ret = new HashMap();
        //设置样式
        WritableFont wf = new WritableFont(WritableFont.ARIAL, 17,
                WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
        WritableFont wf_bold = new WritableFont(WritableFont.ARIAL, 13,
                WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
        WritableCellFormat wcf = new WritableCellFormat(wf); // 单元格定义
        wcf.setBackground(jxl.format.Colour.YELLOW); // 设置单元格的背景颜色
        wcf.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
        wcf.setVerticalAlignment(VerticalAlignment.CENTRE);

        WritableCellFormat wcf_center = new WritableCellFormat(); // 单元格定义
        wcf_center.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式

        WritableCellFormat wcf_center_bold = new WritableCellFormat(wf_bold); // 单元格定义
        wcf_center_bold.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
        wcf_center_bold.setVerticalAlignment(VerticalAlignment.CENTRE);

        WritableCellFormat wcf_background = new WritableCellFormat(); // 单元格定义
        wcf_background.setBackground(jxl.format.Colour.YELLOW); // 设置单元格的背景颜色
        //标题
        for(int i=0;i<title.length;i++){
            Label label = new Label(i,0,title[i],wcf_center_bold);
            sheet.addCell(label);
        }
        ret.put("wf",wf);
        ret.put("wf_bold",wf_bold);
        ret.put("wcf",wcf);
        ret.put("wcf_center",wcf_center);
        ret.put("wcf_center_bold",wcf_center_bold);
        ret.put("wcf_background",wcf_background);
        return ret;
    }


    /**
     * 是否为空对象
     *
     * @param obj
     * @return
     */
    public static boolean isNullObject(Object obj) {
        return null == obj;
    }

    /**
     * 是否为空字符串
     *
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str) {
        if (isNullObject(str) || "".equals(str.trim())) {
            return true;
        } else {
            return str.length() <= 0;
        }
    }
    /**
     * 是否为空List
     *
     * @param list
     * @return
     */

    public static boolean isNullOrEmpty(List list) {
        if (isNullObject(list) || list.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param str
     * @return
     */
    public static String objectToNoEmpty(String str){
        if(isNullOrEmpty(str)){
            return "";
        }else {
            return  str;
        }
    }

}
