package com.xing.manage.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    public static String formatDateTime(long mss) {
        String DateTimes = null;
        long days = mss / ( 60 * 60 * 24);
        long hours = (mss % ( 60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % ( 60 * 60)) /60;
        long seconds = mss % 60;
        if(days>0){
            DateTimes= days + "天" + hours + "小时" + minutes + "分钟"
                    + seconds + "秒";
        }else if(hours>0){
            DateTimes=hours + "小时" + minutes + "分钟"
                    + seconds + "秒";
        }else if(minutes>0){
            DateTimes=minutes + "分钟"
                    + seconds + "秒";
        }else{
            DateTimes=seconds + "秒";
        }

        return DateTimes;
    }

    /***
     *   D  天   W  周  M 月
     * @param inspectionPeriod
     * @return
     */
    public static Long figureStartTime(String inspectionPeriod) {
        long[] longs = parsePeriod(inspectionPeriod);
        return longs[0];
    }

    public static Long figureEndTime(String inspectionPeriod) {
        long[] longs = parsePeriod(inspectionPeriod);
        return longs[1];
    }

    public static int figurePeriodCount(String inspectionPeriod) {

        return 0;
    }

    /***
     * M1-30   DWM
     * @param inspectionPeriod
     */
    public static final long[]  parsePeriod(String inspectionPeriod){
        String  string = inspectionPeriod;
        long[] time =new long[2];
        String  string1 =    string.substring(1);
        String[] split = string1.split("-");
        String strStart = split[0].toString();
        String strEnd = split[1].toString();

        if (string.startsWith("M")){

            Date thisMonthDayStart = getThisMonthDay(Integer.parseInt(strStart));
            Date thisMonthDayEnd = getThisMonthDay(Integer.parseInt(strEnd));
            time[0]=thisMonthDayStart.getTime();
            time[1]=thisMonthDayEnd.getTime();


        }else if(string.startsWith("W")){
            Date thisWeekDayStart = getThisWeekDay(Integer.parseInt(strStart));
            Date thisWeekDayEnd = getThisWeekDay(Integer.parseInt(strEnd));
            time[0]=thisWeekDayStart.getTime();
            time[1]=thisWeekDayEnd.getTime();

        }else if (string.startsWith("D")){
            Date thisDayStart = getThisDay(Integer.parseInt(strStart));
            Date thisDayEnd = getThisDay(Integer.parseInt(strEnd));
            time[0]=thisDayStart.getTime();
            time[1]=thisDayEnd.getTime();
        }

        return  time;
    }
    /**
     * 获得当前月的第几号
     *
     * @return
     * @author hq.zheng
     */
    public static Date getThisMonthDay(int dayofMonth){
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, dayofMonth);
        cale.set(Calendar.HOUR_OF_DAY, 0);
        cale.set(Calendar.MINUTE, 0);
        cale.set(Calendar.SECOND, 0);
        return cale.getTime();
    }
    /**
     * 获得当前周的周几
     *
     * @return
     * @author hq.zheng
     */
    public static Date getThisWeekDay(int dayofWeek){
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_WEEK, dayofWeek+1);
        cale.set(Calendar.HOUR_OF_DAY, 0);
        cale.set(Calendar.MINUTE, 0);
        cale.set(Calendar.SECOND, 0);
        return cale.getTime();
    }

    /**
     * 获得当天的第几个小时
     *
     * @return
     * @author hq.zheng
     */
    public static Date getThisDay(int hourofDay){
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.HOUR_OF_DAY, hourofDay);
        cale.set(Calendar.MINUTE, 0);
        cale.set(Calendar.SECOND, 0);
        return cale.getTime();
    }
    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @author hq.zheng
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 获取当前月的第一天
     *
     * @return
     * @author hq.zheng
     */
    public static Date getThisMonthFirstDay(){
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return cale.getTime();
    }
    /**
     * 获取当前月的最后一天
     *
     * @return
     * @author hq.zheng
     */
    public static Date getThisMonthLastday(){
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return cale.getTime();
    }
    /**
     * 获取上月的第一天
     *
     * @return
     * @author hq.zheng
     */
    public static Date getPrecedMonthFirstDay(){
        Calendar cale = Calendar.getInstance();
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, -1);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return cale.getTime();
    }
    /**
     * 获取上月的最后一天
     *
     * @return
     * @author hq.zheng
     */
    public static Date getPrecedMonthLastday(){
        Calendar cale = Calendar.getInstance();
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return cale.getTime();
    }

    /**
     *
     *
     * @return
     * @author hq.zheng
     */
    public static Date getCurrentDay(){
        Calendar cale = Calendar.getInstance();
        cale = Calendar.getInstance();

        return cale.getTime();
    }
    /**
     * 将字符串时间转成日期时间
     *
     * @param nowDate 当前时间
     * @param format 转的格式，如[yyyy-MM-dd],[yyyy年MM月dd日 HH时mm分ss秒]
     * @return
     * @author hq.zheng
     */
    public static Date formatStringToDate(String nowDate,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date=null;
        try {
            date=sdf.parse(nowDate);
        } catch (Exception e) {
            e.printStackTrace();
            return date;
        }
        return date;
    }



}
