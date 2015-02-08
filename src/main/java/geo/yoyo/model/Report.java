package geo.yoyo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class Report {
    private ReportType type;
    private List<Activity> activities;

    public int getScore() {
        return activities.stream().mapToInt(activity -> activity.getType().getScore()).sum() / type.getTerm();
    }

    public Map<ActivityType, Integer> getActivityCounter() {
        Map<ActivityType, Integer> counter = new HashMap<>();
        ActivityType.LIST.forEach(activityType -> counter.put(activityType, 0));
        activities.forEach(activity -> counter.put(activity.getType(), counter.get(activity.getType()) + 1));
        return counter;
    }
}
