package geo.yoyo;

import lombok.Getter;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

@Getter
public class DayRecord {
    private String date;
    private boolean[] practices = new boolean[PracticeType.size()];
    private int score = 0;

    public DayRecord(Date date) {
        this.date = DateFormatUtils.format(date, "MM.dd");
    }

    public void setPractice(Integer type) {
        practices[type] = true;
    }

    public void addScore(int score) {
        this.score += score;
    }
}
