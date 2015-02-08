package geo.yoyo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Activity {
    private ActivityType type;
    private boolean done;

    public Activity(String code, boolean done) {
        this.type = ActivityType.get(code);
        this.done = done;
    }
}
