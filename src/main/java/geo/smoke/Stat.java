package geo.smoke;

import lombok.Data;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

@Data
public class Stat {
    public static final String DATE_FORMAT = "yyyyMMdd";
    private String date;
    private double score;
    private double weight;

    public Stat(Date date, double score, double weight) {
        this.date = format(date);
        this.score = score;
        this.weight = weight;
    }

    private String format(Date date) {
        return DateFormatUtils.format(date, DATE_FORMAT);
    }

    public boolean between(Date start, Date end) {
        return date.compareTo(format(start)) >= 0 && date.compareTo(format(end)) <= 0;
    }

    public boolean hasSameDay(Date date) {
        return this.date.equals(DateFormatUtils.format(date, DATE_FORMAT));
    }
}
