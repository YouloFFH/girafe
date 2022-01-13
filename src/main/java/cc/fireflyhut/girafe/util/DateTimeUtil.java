package cc.fireflyhut.girafe.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateTimeUtil {

    public static final String FORMAT_FULL_TIME_STAMP = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_FULL_TIME_STAMP_NOSPACE = "yyyyMMddHHmmssSSS";

    /**
     * 获取yyyyMMddHHmmssSSS的当前时间
     */
    public static String getNowPureString() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_FULL_TIME_STAMP_NOSPACE);
        String res = sdf.format(Calendar.getInstance().getTime());
        return res;
    }

    /**
     * 获得从当前时刻其的偏移指定小时
     * @param hour
     * @return
     */
    public static Date getDateOffsetHour(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }

    /**
     * 计算传入时间和现在的时间偏移，偏移单位：秒
     * @param date 传入时间
     * @return 当前时间-传入时间=返回秒数
     */
    public static double offsetSecondByNow(Date date) {
        long inTime = date.getTime();
        long now = System.currentTimeMillis();
        double res = now - inTime;
        return res / 1000;
    }

    /**
     * 获取此刻Date时间对象
     * @return date对象
     */
    public static Date getNowDate() {
        return Calendar.getInstance().getTime();
    }

    /**
     * 将传入的Date对象转转换成yyyy-MM-dd HH:mm:ss SSS格式字符串
     * @param date
     * @return
     */
    public static String convertDateToFullString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_FULL_TIME_STAMP);
        return sdf.format(date);
    }

    /**
     * 和传入的Date对象比较
     * @param inDate 传入的Date时间
     * @return 传入date比当前时间早返回-1，传入date比当前时间晚返回1，相等返回0
     */
    public static int compareWithNowDate(Date inDate) {
        long in = inDate.getTime();
        long now = Calendar.getInstance().getTime().getTime();
        if (in < now) {
            return -1;
        } else if (in > now) {
            return 1;
        }
        return 0;
    }

}
