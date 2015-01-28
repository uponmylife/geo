package geo.yoyo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Practice {
    @EmbeddedId
    private Pk pk;
    private int score;

    public static final String DATE_STRING_PATTERN = "yyyyMMdd";
    public static final int DEFAULT_SCORE = 25;

    public Practice(Date date, Integer type) {
        this(new Pk(date, type), DEFAULT_SCORE);
    }

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Pk implements Serializable {
        private String day;
        private Integer type;

        public Pk(Date date, Integer type) {
            day = toDay(date);
            this.type = type;
        }
    }

    public String getDay() {
        return pk.day;
    }

    public boolean isSameDay(Date date) {
        return toDay(date).equals(pk.day);
    }

    public Integer getType() {
        return pk.type;
    }

    public Date getDate() {
        try {
            return DateUtils.parseDate(pk.day, new String[]{DATE_STRING_PATTERN});
        } catch (ParseException e) {
            return null;
        }
    }

    public static String toDay(Date date) {
        return DateFormatUtils.format(date, DATE_STRING_PATTERN);
    }
}
