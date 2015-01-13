package geo.smoke;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Smoke {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private long term;
    private int count;

    public Smoke(Date date, int termHours, int count) {
        this.date = date;
        this.term = termHours * 60 * 60 * 1000;
        this.count = count;
    }

    public String getFormattedDate() {
        return new SimpleDateFormat("M.d").format(date);
    }

    public int getTermHours() {
        return (int) millisi2Hours(term);
    }

    public String getScore() {
        return String.format("%.1f", millisi2Hours(term) / (double)count);
    }

    private double millisi2Hours(Long millis) {
        return (int) ((double) millis / (double) (1000 * 60 * 60));
    }
}
