package geo.util;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

public class DateUtil {
    public static Date startOfDay(Date date) {
        return DateUtils.setMilliseconds(DateUtils.setSeconds(DateUtils.setMinutes(DateUtils.setHours(date, 0), 0), 0), 0);
    }

    public static boolean isSameDay(Date date1, Date date2) {
        String pattern = "yyyyMMdd";
        String d1 = DateFormatUtils.format(date1, pattern);
        String d2 = DateFormatUtils.format(date2, pattern);
        return d1.equals(d2);
    }

    public static Date today() {
        return new Date();
    }

    public static Date yesterday() {
        return DateUtils.addDays(new Date(), -1);
    }

    public static int diffDay(Date date1, Date date2) {
        long day1 = date1.getTime() / DateUtils.MILLIS_PER_DAY;
        long day2 = date2.getTime() / DateUtils.MILLIS_PER_DAY;
        return (int) (day1 - day2);
    }
}
