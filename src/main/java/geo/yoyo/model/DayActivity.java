package geo.yoyo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.ArrayList;
import java.util.List;

import static geo.util.DateUtil.*;

@Data
@NoArgsConstructor
public class DayActivity {
    private int day;
    private List<Activity> activities = new ArrayList<>();

    public DayActivity(DayRecord dayRecord) {
        this.day = dayRecord.getDay();
        this.activities = dayRecord.activities();
    }

    public DayActivity(int daysAgo, List<String> codes) {
        this(DayRecord.createNoActivitiesInstance(getSystemDayDaysAgo(daysAgo)));
        activities.forEach((activity) -> activity.setDone(codes.contains(activity.getType().getCode())));
    }

    public int getScore() {
        return activities.stream().mapToInt((activity) -> activity.isDone() ? activity.getType().getScore() : 0).sum();
    }

    // velocity
    public String getDateString() {
        return DateFormatUtils.format(fromSystemDays(day), "MM.dd");
    }
}
