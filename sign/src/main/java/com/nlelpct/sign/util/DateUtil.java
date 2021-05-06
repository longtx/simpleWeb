package com.nlelpct.sign.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期处理工具类.
 */
public class DateUtil {

    private static final transient Log log = LogFactory.getLog(DateUtil.class);

    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
    public static final String HH_mm_ss = "HH:mm:ss";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String yyyyMMdd = "yyyyMMdd";

    public static SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
    public static SimpleDateFormat dateFormat01 = new SimpleDateFormat(yyyyMMddHHmmssSSS);
    public static SimpleDateFormat dateFormat02 = new SimpleDateFormat(yyyyMMddHHmmss);
    private static SimpleDateFormat timeFormat = new SimpleDateFormat(HH_mm_ss);
    private static SimpleDateFormat sdf_SSS = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss_SSS);
    private static SimpleDateFormat sdf_dd = new SimpleDateFormat(yyyy_MM_dd);
    public static SimpleDateFormat sdf_ddD = new SimpleDateFormat("yyyy.MM.dd");
    public static SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);

    public static final String str_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String str_MMDDHHMMSS = "MMddHHmmss";

    public static SimpleDateFormat sdf_yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat(str_yyyy_MM_dd_HH_mm_ss);
    public static SimpleDateFormat sdf_MMDDHHMMSS = new SimpleDateFormat(str_MMDDHHMMSS);

    private DateUtil() {
        // 私有构造方法.因为此类是工具类.
    }

    public static Date parseByStringDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateString);
            return date;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
      /**
      * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
      *
      * @param strDate
      * @return
      */
        public static Date  strToDateLong(String strDate) throws ParseException {
            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(strDate);
            return date;
        }



    /**
     * 获取格式化实例.
     *
     * @param pattern 如果为空使用DAFAULT_DATETIME_FORMAT
     * @return
     */
    public static SimpleDateFormat getFormatInstance(String pattern) {
        if (pattern == null || pattern.length() == 0) {
            pattern = yyyy_MM_dd_HH_mm_ss;
        }
        return new SimpleDateFormat(pattern);
    }

    /**
     * 格式化Calendar
     *
     * @param calendar
     * @return
     */
    public static String formatCalendar(Calendar calendar) {
        if (calendar == null) {
            return "";
        }
        return dateFormat.format(calendar.getTime());
    }

    public static String formatDate(Date d) {
        if (d == null) {
            return "";
        }
        return dateFormat.format(d);
    }

    /**
     * 格式化时间
     *
     * @return
     */
    public static String formatTime(Date d) {
        if (d == null) {
            return "";
        }
        return timeFormat.format(d);
    }

    /**
     * 格式化整数型日期
     *
     * @param intDate
     * @return
     */
    public static String formatIntDate(Integer intDate) {
        if (intDate == null) {
            return "";
        }
        Calendar c = newCalendar(intDate);
        return formatCalendar(c);
    }

    /**
     * 根据指定格式化来格式日期.
     *
     * @param date    待格式化的日期.
     * @param pattern 格式化样式或分格,如yyMMddHHmmss
     * @return 字符串型日期.
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        if (pattern == null || pattern.length() < 1) {
            return formatDate(date);
        }
        SimpleDateFormat simpleDateFormat = null;
        try {
            simpleDateFormat = new SimpleDateFormat(pattern);
        } catch (Exception e) {
            e.printStackTrace();
            return formatDate(date);
        }
        return simpleDateFormat.format(date);
    }

    /**
     * 取得Integer型的当前日期
     *
     * @return
     */
    public static Integer getIntNow() {
        return getIntDate(getNow());
    }

    /**
     * 取得Integer型的当前日期
     *
     * @return
     */
    public static Integer getIntToday() {
        return getIntDate(getNow());
    }

    /**
     * 取得Integer型的明天日期
     *
     * @return
     */
    public static Integer getIntTomorrow() {
        return getIntDate(getTomorrowNow());
    }

    /**
     * 取得Integer型的当前年份
     *
     * @return
     */
    public static Integer getIntYearNow() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        return year;
    }

    /**
     * 取得Integer型的当前月份
     *
     * @return
     */
    public static Integer getIntMonthNow() {
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH) + 1;
        return month;
    }

    public static String getStringToday() {
        return getIntDate(getNow()) + "";
    }

    /**
     * 根据年月日获取整型日期
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Integer getIntDate(int year, int month, int day) {
        return getIntDate(newCalendar(year, month, day));
    }

    /**
     * 根据年月日获取整型日期
     *
     * @return
     */
    public static Integer getIntDateNow() {
        Calendar c = Calendar.getInstance();
        int date = c.get(Calendar.DATE);
        return date;
    }

    /**
     * 某年月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Integer getFirstDayOfMonth(int year, int month) {
        return getIntDate(newCalendar(year, month, 1));
    }

    /**
     * 某年月的第一天
     */
    public static Integer getFirstDayOfThisMonth() {
        Integer year = DateUtil.getIntYearNow();
        Integer month = DateUtil.getIntMonthNow();
        return getIntDate(newCalendar(year, month, 1));
    }

    /**
     * 某年月的第一天
     *
     * @param date
     * @return
     * @time:2008-7-4 上午09:58:55
     */
    public static Integer getFistDayOfMonth(Date date) {
        Integer intDate = getIntDate(date);
        int year = intDate / 10000;
        int month = intDate % 10000 / 100;
        return getIntDate(newCalendar(year, month, 1));
    }

    /**
     * 某年月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Integer getLastDayOfMonth(int year, int month) {
        return intDateSub(getIntDate(newCalendar(year, month + 1, 1)), 1);
    }

    /**
     * 根据Calendar获取整型年份
     *
     * @param c
     * @return
     */
    public static Integer getIntYear(Calendar c) {
        int year = c.get(Calendar.YEAR);
        return year;
    }

    /**
     * 根据Calendar获取整型月份
     *
     * @param c
     * @return
     */
    public static Integer getIntMonth(Calendar c) {
        int month = c.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * 根据Calendar获取长整型日期
     *
     * @param c
     * @return
     */
    public static Long getLongDate(Calendar c) {
        long year = c.get(Calendar.YEAR);
        long month = c.get(Calendar.MONTH) + 1;
        long day = c.get(Calendar.DAY_OF_MONTH);
        long hour = c.get(Calendar.HOUR_OF_DAY);
        long min = c.get(Calendar.MINUTE);
        long sec = c.get(Calendar.SECOND);
        return year * 10000000000L + month * 100000000 + day * 1000000
                + hour * 10000 + min * 100 + sec;
    }

    /**
     * 根据Calendar获取整型日期
     *
     * @param c
     * @return
     */
    public static Integer getIntDate(Calendar c) {
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        return year * 10000 + month * 100 + day;
    }

    /**
     * 根据Date获取整型年份
     *
     * @param d
     * @return
     */
    public static Integer getIntYear(Date d) {
        if (d == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return getIntYear(c);
    }

    /**
     * 根据Date获取整型月份
     *
     * @param d
     * @return
     */
    public static Integer getIntMonth(Date d) {
        if (d == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return getIntMonth(c);
    }

    /**
     * 根据Date获取整型日期
     *
     * @param d
     * @return
     */
    public static Integer getIntDate(Date d) {
        if (d == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return getIntDate(c);
    }

    /**
     * 根据Integer获取Date日期
     *
     * @return
     */
    public static Date getDate(Long yyyyMMddHHmmss) {
        if (yyyyMMddHHmmss == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.set((int) (yyyyMMddHHmmss / 10000000000L), (int) (yyyyMMddHHmmss / 100000000 % 100 - 1), (int) (yyyyMMddHHmmss / 1000000 % 100)
                , (int) (yyyyMMddHHmmss / 10000 % 100), (int) (yyyyMMddHHmmss / 100 % 100), (int) (yyyyMMddHHmmss % 100));
        return c.getTime();
    }

    public static Date getDate(String date) {
        if (date == null || date.length() == 0) {
            return null;
        }

        try {
            if (date.contains("/")) {
                date = date.replaceAll("/", "-");
            }
            return getFormatInstance(yyyy_MM_dd_HH_mm_ss).parse(date);
        } catch (ParseException e) {
            log.error("解析[" + date + "]错误！", e);
            return null;
        }
    }

    public static Date getDate(String date, String pattern) {
        if (date == null || date.length() == 0) {
            return null;
        }

        try {
            if (date.contains("/")) {
                date = date.replaceAll("/", "-");
            }
            return getFormatInstance(pattern).parse(date);
        } catch (ParseException e) {
            log.error("解析[" + date + "]错误！", e);
            return null;
        }
    }

    public static Date getDateExp(String date, String pattern)
            throws ParseException {
        if (date == null || date.length() == 0) {
            return null;
        }
        return getFormatInstance(pattern).parse(date);

    }


    /**
     * 根据年份Integer获取Date日期
     *
     * @param year
     * @return
     */
    public static Date getFirstDayOfYear(Integer year) {
        if (year == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.set(year, 1, 1);
        return c.getTime();
    }

    /**
     * 根据年月日生成Calendar
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Calendar newCalendar(int year, int month, int day) {
        Calendar ret = Calendar.getInstance();
        if (year < 100) {
            year = 2000 + year;
        }
        ret.set(year, month - 1, day);
        return ret;
    }

    /**
     * 根据整型日期生成Calendar
     *
     * @param date
     * @return
     */
    public static Calendar newCalendar(int date) {
        int year = date / 10000;
        int month = (date % 10000) / 100;
        int day = date % 100;

        Calendar ret = Calendar.getInstance();
        ret.set(year, month - 1, day);
        return ret;
    }

    /**
     * 取得Date型的当前日期
     *
     * @return
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * 取得Date型的当前日期
     *
     * @return
     */
    public static Date getTomorrowNow() {
        return new Date(System.currentTimeMillis() + 86400000L);
    }

    /**
     * 取得Date型的当前日期,时间设为00:00:00
     *
     * @return
     */
    public static Date getToday() {
        return DateUtil.getDate(DateUtil.getIntToday() * 1000000L);
    }

    /**
     * 取得Date型的明天日期,时间设为00:00:00
     *
     * @return
     */
    public static Date getTomorrow() {
        return DateUtil.getDate(DateUtil.getIntTomorrow() * 1000000L);
    }

    /**
     * 整数型日期的加法
     *
     * @param date
     * @param days
     * @return
     */
    public static Integer intDateAdd(int date, int days) {
        int year = date / 10000;
        int month = (date % 10000) / 100;
        int day = date % 100;

        day += days;

        return getIntDate(year, month, day);
    }

    /**
     * 整数型日期的减法
     *
     * @param date
     * @param days
     * @return
     */
    public static Integer intDateSub(int date, int days) {
        return intDateAdd(date, -days);
    }

    /**
     * 计算两个整型日期之间的天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Integer daysBetweenDate(Integer startDate, Integer endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        Calendar c1 = newCalendar(startDate);
        Calendar c2 = newCalendar(endDate);

        Long lg = (c2.getTimeInMillis() - c1.getTimeInMillis()) / 1000 / 60 / 60 / 24;
        return lg.intValue();
    }

    /**
     * 计算两个整型日期之间的天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Integer daysBetweenDate(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        Long interval = endDate.getTime() - startDate.getTime();
        interval = interval / (24 * 60 * 60 * 1000);
        return interval.intValue();
    }

    /**
     * 取得当前日期.
     *
     * @return 当前日期, 字符串类型.
     */
    public static String getStringDate() {
        return getStringDate(DateUtil.getNow());
    }

    /**
     * 取得当前日期.
     *
     * @return 当前日期, 字符串类型.
     */
    public static String getStringDate1() {
        return getStringDatemilens(DateUtil.getNow());
    }

    /**
     * 根据calendar产生字符串型日期
     *
     * @param d
     * @return eg:20080707
     */
    public static String getStringDate(Date d) {
        if (d == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
        return sdf.format(d);
    }


    /**
     * 根据calendar产生字符串型日期
     *
     * @param d
     * @return eg:20080707
     */
    public static String getStringDatemilens(Date d) {
        if (d == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss_SSS);
        return sdf.format(d);
    }


    public static String getFormatStringDate(Date d) {
        if (d == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(d);
    }

    public static Date getFirstDayOfWeek(long millis) {
        long oneday = 1000 * 60 * 60 * 24;
        millis -= oneday;
        Date date = null;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 2;
        cal.add(Calendar.DATE, -day_of_week);
        String firstDayStr = sdf_dd.format(cal.getTime());
        try {
            Date firstDay = sdf_dd.parse(firstDayStr);
            cal.setTime(firstDay);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println("本周第一天: " + sdf_dd.format(cal.getTime()));
        date = cal.getTime();
        return date;
    }

    public static Date getLastDayOfWeek(long millis) {
        Date date = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(getFirstDayOfWeek(millis));
        cal.add(Calendar.DATE, 6);
        long day = 1000 * 60 * 60 * 24;
        //System.out.println("本周末: " + sdf_SSS.format(cal.getTime().getTime()+day-1));
        cal.setTimeInMillis(cal.getTime().getTime() + day - 1);
        date = cal.getTime();
        return date;
    }

    //产生下num周的日期集合，格式：2012.01.09~2012.01.15
    public static List<String> weekList(int num) {
        List<String> list = new ArrayList<String>();
        Date nowdate = new Date();
        long weekMill = 1000 * 60 * 60 * 24 * 7;
        for (int i = 1; i <= num; i++) {
            String first = sdf_ddD.format(DateUtil.getFirstDayOfWeek(nowdate.getTime() + i * weekMill));
            String last = sdf_ddD.format(DateUtil.getLastDayOfWeek(nowdate.getTime() + i * weekMill));
            String s = first + "~" + last;
            //System.out.println(s);
            list.add(s);
        }
        return list;
    }

    public static String loadPublishTimeSignByWeek(int week, String weekNum) {
        String s = "";
        long weekMill = 1000 * 60 * 60 * 24 * 7;
        Date date = null;
        for (int i = 0; i < Integer.parseInt(weekNum); i++) {
            if (week == i) {
                s = weekList(Integer.parseInt(weekNum)).get(i);
                break;
            }
        }
        return s;
    }

    public static void executeTime(Date startTime) {
        Date endTime = new Date();
        log.info("=========startTime--->" + sdf_SSS.format(startTime));
        log.info("=========endTime--->" + sdf_SSS.format(endTime));
        long useTime = endTime.getTime() - startTime.getTime();
        long hour = useTime / 1000 / 60 / 60;
        long allMinute = useTime / 1000 / 60;
        long allSecond = useTime / 1000;
        long minute = allMinute - hour * 60;
        long second = allSecond - allMinute * 60;
        log.info("=========总耗时--->" + hour + " 小时 " + minute + " 分钟  " + second + " 秒");
    }

    public static Date getDateByMills(long millis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        return cal.getTime();
    }

    public static Date addYearByNow() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        calendar.set(Calendar.YEAR, year + 50);
        Date date = calendar.getTime();
        return date;

    }

    public static String addDate(String initDate, int addDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = sdf.parse(initDate);
        } catch (ParseException e) {
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, addDate);
        String T3 = sdf.format(calendar.getTime());
        return T3;
    }
    // String类型的20141012转String类型的2014-10-12   用法：System.out.println(""+DateToStr(StrToDate("20141012")));


    public static String DateToStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str = format.format(date);
        return str;
    }

    public static String DateToStr2(Date date) {
    	if(date == null) {
    		return "";
    	}
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * 计算年龄：周岁
     *
     * @param curDate
     * @param birthday
     * @return
     */
    public static int getFullAge(Date curDate, Date birthday) {
        Calendar birthdayCalendar = Calendar.getInstance();
        birthdayCalendar.setTime(birthday);

        Calendar curDateCalendar = Calendar.getInstance();
        curDateCalendar.setTime(curDate);
        Integer age = curDateCalendar.get(Calendar.YEAR) - birthdayCalendar.get(Calendar.YEAR) - 1;
        Integer month = curDateCalendar.get(Calendar.MONTH) - birthdayCalendar.get(Calendar.MONTH);
        if (month > 0) {
            age++;
        } else if (month == 0) {
            if (curDateCalendar.get(Calendar.DAY_OF_MONTH) >= birthdayCalendar.get(Calendar.DAY_OF_MONTH)) {
                age++;
            }
        }

        return age;
    }

    public static Date addDayDate(Date initDate, int addDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = initDate;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, addDate);
        date = new Date(calendar.getTimeInMillis());
        return date;
    }

    public static String addDate(Date initDate, int addDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = initDate;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, addDate);
        String T3 = sdf.format(calendar.getTime());
        return T3;
    }

    public static Date strToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    // String类型的20141012转String类型的2014-10-12   用法：System.out.println(""+DateToStr1(StrToDate1("2014-10-12")));
    public static String DateToStr1(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String str = format.format(date);
        return str;
    }

    public static Date StrToDate1(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    //显示X天前的日期字符串  如几天是2015-09-22，则getDateString(-1)打印出2015-09-21
    public static String getDateString(int interval) {

        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_MONTH, interval);//今天的基础上加一天
        Date d2 = c.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String str2 = df.format(d2);

        return str2;
    }

    //获取当前时间的当前年月第一天
    public static String getFirstDateOfCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        System.out.println("===============first:" + first);
        return first;
    }
    //根据year参数获取当前年份第一天
    public static String getFirstDateStrByYear(int year){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.set(year,1,1);//设置为当前年份的一月一号
        String first = format.format(c.getTime());
        System.out.println("===============first:" + first);
        return first+" 00:00:00";
    }


    ////获取当前时间的当前年月的最后一天
    public static String getLastDateOfCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        System.out.println("===============last:" + last);
        return last;
    }

    public static void main(String[] args) {
        long day = 1000 * 60 * 60 * 24;
        long week = 7 * day;
        System.out.println(dateFormat.format(DateUtil.getFirstDayOfWeek(new Date().getTime() - week)));

    }

    public static String yearMonthToStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        String str = format.format(date);
        return str;
    }

    /**
     * 当前月份加减
     */
    public static String monthAdd(int addmonthvalue) {
        Calendar cal = Calendar.getInstance();
        //下面的就是把当前日期加一个月
        cal.add(Calendar.MONTH, addmonthvalue);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(cal.getTime());
    }

    /**
     * 两个日期直接的日期集合  2016-03-01至2016-03-05之间
     */
    public static List<String> yearMonthdayListBetweenTwoDay(String sdate, String edate) throws ParseException {
        List<String> list = new ArrayList<String>();
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse(sdate);
        startCalendar.setTime(startDate);
        Date endDate = df.parse(edate);
        endCalendar.setTime(endDate);
        list.add(sdate);
        while (true) {
            startCalendar.add(Calendar.DAY_OF_MONTH, 1);
            if (startCalendar.getTimeInMillis() < endCalendar.getTimeInMillis()) {//TODO 转数组或是集合，楼主看着写吧
                //System.out.println(df.format(startCalendar.getTime()));
                list.add(df.format(startCalendar.getTime()));
            } else {
                break;
            }
        }
        list.add(edate);

        return list;
    }

    /**
     * 两个日期直接的日期集合 2016-03-01至2016-03-05之间
     */
    public static List<String> dayListBetweenTwoDay(String sdate, String edate) throws ParseException {
        List<String> listnew = new ArrayList<String>();
        String newdate = "";
        List<String> list = new ArrayList<String>();
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
        Date startDate = df.parse(sdate);
        startCalendar.setTime(startDate);
        Date endDate = df.parse(edate);
        endCalendar.setTime(endDate);
        //list.add(sdate);
        list.add(df.format(startCalendar.getTime()));
        while (true) {
            startCalendar.add(Calendar.DAY_OF_MONTH, 1);
            if (startCalendar.getTimeInMillis() < endCalendar.getTimeInMillis()) {//TODO 转数组或是集合，楼主看着写吧
                //System.out.println(df.format(startCalendar.getTime()));
                list.add(df.format(startCalendar.getTime()));
            } else {
                break;
            }
        }
        //list.add(edate);
        list.add(df.format(endCalendar.getTime()));

        for (int i = 0; i < list.size(); i++) {
            newdate = list.get(i).substring(7, list.get(i).length());
            newdate = newdate + "日";
            if (newdate.indexOf("-") >= 0) {
                newdate = newdate.replace("-", "");
            }
            listnew.add(newdate);
        }
        return listnew;
    }

    /**
     * 两个日期直接的月份集合 2016-03至2016-09之间
     */
    public static List<String> getAllMonths(String start, String end) {
        String splitSign = "-";
        String regex = "\\d{4}" + splitSign + "(([0][1-9])|([1][012]))"; //判断YYYY-MM时间格式的正则表达式
        if (!start.matches(regex) || !end.matches(regex)) return new ArrayList<String>();

        List<String> list = new ArrayList<String>();
        if (start.compareTo(end) > 0) {
            //start大于end日期时，互换
            String temp = start;
            start = end;
            end = temp;
        }

        String temp = start; //从最小月份开始
        while (temp.compareTo(start) >= 0 && temp.compareTo(end) < 0) {
            list.add(temp); //首先加上最小月份,接着计算下一个月份
            String[] arr = temp.split(splitSign);
            int year = Integer.valueOf(arr[0]);
            int month = Integer.valueOf(arr[1]) + 1;
            if (month > 12) {
                month = 1;
                year++;
            }
            if (month < 10) {//补0操作
                temp = year + splitSign + "0" + month;
            } else {
                temp = year + splitSign + month;
            }
        }
        list.add(end);
        return list;
    }

    /**
     * 2016-03格式转为201603格式
     */
    public static List<String> returnAllMonths(String start, String end) {
        List<String> listnew = new ArrayList<String>();
        String splitSign = "-";
        String regex = "\\d{4}" + splitSign + "(([0][1-9])|([1][012]))"; //判断YYYY-MM时间格式的正则表达式
        if (!start.matches(regex) || !end.matches(regex)) return new ArrayList<String>();

        List<String> list = new ArrayList<String>();
        if (start.compareTo(end) > 0) {
            //start大于end日期时，互换
            String temp = start;
            start = end;
            end = temp;
        }

        String temp = start; //从最小月份开始
        while (temp.compareTo(start) >= 0 && temp.compareTo(end) < 0) {
            list.add(temp); //首先加上最小月份,接着计算下一个月份
            String[] arr = temp.split(splitSign);
            int year = Integer.valueOf(arr[0]);
            int month = Integer.valueOf(arr[1]) + 1;
            if (month > 12) {
                month = 1;
                year++;
            }
            if (month < 10) {//补0操作
                temp = year + splitSign + "0" + month;
            } else {
                temp = year + splitSign + month;
            }
        }
        list.add(end);
        for (int i = 0; i < list.size(); i++) {
            listnew.add(list.get(i).replace("-", ""));
        }
        return listnew;
    }

    //获取某年月的最后一天，2016-09格式
    public static String getLastDayOfYearMonth(String yearMonth) {
        String[] strings = yearMonth.split("-");
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, Integer.valueOf(strings[0]).intValue());
        //设置月份
        cal.set(Calendar.MONTH, Integer.valueOf(strings[1]).intValue() - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());

        return lastDayOfMonth;
    }

    //获取某年月的最后一天
    public static String getLastDayOfYearMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());

        return lastDayOfMonth;
    }

    public static String formatSeconds(long second) {
        long hour = second/3600;
        long minute = second%3600/60;
        long sec = second%60;
        if(hour>0){
            return  hour+"小时"+minute+"分"+sec+"秒";
        }else if(minute>0){
            return  minute+"分"+sec+"秒";
        }else{
            return sec+"秒";
        }
    }

    public static long secondsToMinutes(long second) {
        return Math.round(second/60);
    }


    /**
     * 获取当前时间整形（全精确度）
     */
    public static Integer getNowDoubleInt(){
        return Integer.valueOf(String.valueOf(new Date().getTime()).substring(0, 10));
    }

    public static Integer getDateDoubleInt(Date date){
        return Integer.valueOf(String.valueOf(date.getTime()).substring(0, 10));
    }

    public static Date getDateBehDateByDay(Date date,Integer day){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + day);
        Date today = cal.getTime();
        return today;
    }

    public static String dealDateFormat(String oldDate) {
        Date date1 = null;
        DateFormat df2 = null;
        try {
            oldDate= oldDate.replace("Z", " UTC");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            Date date = df.parse(oldDate);
            SimpleDateFormat df1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
            date1 = df1.parse(date.toString());
            df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return df2.format(date1);
    }
}
