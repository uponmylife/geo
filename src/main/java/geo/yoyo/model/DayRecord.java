package geo.yoyo.model;

import lombok.Data;
import org.apache.commons.beanutils.PropertyUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity(name = "yoyo")
@Data
public class DayRecord {
    @Id
    private int day;
    private boolean a01;
    private boolean a02;
    private boolean a03;
    private boolean a04;
    private boolean a05;
    private boolean a06;
    private boolean a07;
    private boolean a08;
    private boolean a09;
    private boolean a10;
    private boolean a11;
    private boolean a12;
    private boolean a13;
    private boolean a14;
    private boolean a15;
    private boolean a16;

    public static DayRecord createNoActivitiesInstance(int day) {
        DayRecord dayRecord = new DayRecord();
        dayRecord.setDay(day);
        return dayRecord;
    }

    public List<Activity> activities() {
        List<Activity> activities = new ArrayList();
        try {
            Map<String, Object> objectMap = PropertyUtils.describe(this);
            for (String attr : objectMap.keySet()) {
                Object obj = objectMap.get(attr);
                if (obj instanceof Boolean && ActivityType.hasCode(attr)) activities.add(new Activity(attr, (boolean)obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        activities.sort((a1, a2) -> a1.getType().getOrder() - a2.getType().getOrder());

        return activities;
    }

    public void setActivities(List<Activity> activities) {
        for (Activity activity : activities) {
            try {
                PropertyUtils.setProperty(this, activity.getType().getCode(), activity.isDone());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Activity> activitiesForReport() {
        List<Activity> activities = new ArrayList();
        try {
            Map<String, Object> objectMap = PropertyUtils.describe(this);
            for (String attr : objectMap.keySet()) {
                Object obj = objectMap.get(attr);
                if (obj instanceof Boolean && ActivityType.hasCode(attr) && (boolean)obj) activities.add(new Activity(attr, true));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return activities;
    }
}
