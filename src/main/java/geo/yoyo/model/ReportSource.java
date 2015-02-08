package geo.yoyo.model;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static geo.util.DateUtil.systemDays;
import static geo.util.DateUtil.today;

@AllArgsConstructor
public class ReportSource {
    private List<DayRecord> dayRecords;

    public List<Activity> getActivities(int term) throws NotEnoughException {
        if (dayRecords.size() == 0) throw new NotEnoughException();
        int startDay = systemDays(today()) - term;
        if (startDay < dayRecords.get(dayRecords.size() - 1).getDay()) throw new NotEnoughException();
        List<Activity> activities = new ArrayList<>();
        dayRecords.stream().filter(r -> r.getDay() >= startDay)
                .map(r -> r.activitiesForReport())
                .forEach(list -> activities.addAll(list));
        return activities;
    }

    public static class NotEnoughException extends Exception {
    }
}
