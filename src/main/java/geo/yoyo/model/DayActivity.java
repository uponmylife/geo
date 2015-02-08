package geo.yoyo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DayActivity {
    private int date;
    private List<Activity> activities = new ArrayList<>();

    public DayActivity(int date, List<Activity> activities) {
        this.date = date;
        this.activities = activities;
    }
}
