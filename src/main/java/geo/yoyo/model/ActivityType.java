package geo.yoyo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ActivityType {
    private String code;
    private String name;
    private int score;
    private int order;
    private String icon;

    public static List<ActivityType> LIST = Arrays.asList(
            new ActivityType("a01", "normal meal", 15, 10, "meal"),
            new ActivityType("a02", "no day snack", 15, 20, "snack"),
            new ActivityType("a03", "no night snack", 15, 30, "snack"),
            new ActivityType("a04", "no alcohol", 10, 40, "drink"),
            new ActivityType("a05", "low salt", 30, 50, "salt"),
            new ActivityType("a06", "rest a eating", 30, 60, "fasting"),
            new ActivityType("a07", "3l water", 10, 70, "water"),
            new ActivityType("a08", "training", 25, 80, "running"),
            new ActivityType("a09", "10k step", 10, 90, "foot"),
            new ActivityType("a10", "+10k step", 15, 100, "foot"),
            new ActivityType("a11", "reading", 15, 110, "book"),
            new ActivityType("a12", "early sleep", 15, 120, "sleep")
    );

    private static Map<String, ActivityType> MAP =
            LIST.stream().collect(Collectors.toMap(ActivityType::getCode, Function.<ActivityType>identity()));

    public static ActivityType get(String code) {
        return MAP.get(code);
    }

    public static boolean hasCode(String code) {
        return MAP.containsKey(code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActivityType)) return false;

        ActivityType that = (ActivityType) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }
}
