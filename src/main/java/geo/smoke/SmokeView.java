package geo.smoke;

import lombok.Data;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

@Data
public class SmokeView {
    private String date;
    private int termHours;
    private int count;
    private String score;

    public SmokeView(Smoke smoke) {
        date = DateFormatUtils.format(smoke.getDate(), "y.M.d HH:mm");
        termHours = (int) (smoke.getTerm() / DateUtils.MILLIS_PER_HOUR);
        count = smoke.getCount();
        score = String.format("%.1f", getScore());
    }
}
