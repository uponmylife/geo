package geo.smoke;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.time.DateUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class SmokeView {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private long term;
    private int count;

    public SmokeView(Date prevDate, int count) {
        this(prevDate, count, new Date());
    }

    public SmokeView(Date prevDate, int count, Date now) {
        date = now;
        this.count = count;
        if (prevDate == null) term = 0l;
        else term = date.getTime() - prevDate.getTime();
    }

    public String getFormattedDate() {
        return new SimpleDateFormat("M.d").format(date);
    }

    public int getTermHours() {
        return (int) millisi2Hours(term);
    }

    public String getScoreStr() {
        return String.format("%.1f", getScore());
    }

    public double getScore() {
        return millisi2Hours(term) / (double)count;
    }

    public Date getPrevDate() {
        return new Date(date.getTime() - term);
    }

    public boolean hasPrevDate() {
        return term > 0l;
    }

    private double millisi2Hours(Long millis) {
        return (double) millis / (double) DateUtils.MILLIS_PER_HOUR;
    }
}
