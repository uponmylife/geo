package geo.util;

import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

public class DateUtil {
    public static Date startOfDay(Date date) {
        return DateUtils.setMilliseconds(DateUtils.setSeconds(DateUtils.setMinutes(DateUtils.setHours(date, 0), 0), 0), 0);
    }
}
