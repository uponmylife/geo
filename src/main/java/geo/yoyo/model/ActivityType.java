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

    public static List<ActivityType> LIST = Arrays.asList(
            new ActivityType("a01", "normal meal", 0),
            new ActivityType("a02", "no day snack", 0),
            new ActivityType("a03", "no night snack", 0),
            new ActivityType("a04", "no alcohol", 0),
            new ActivityType("a05", "low salt", 0),
            new ActivityType("a06", "rest a eating", 0),
            new ActivityType("a07", "3l water", 0),
            new ActivityType("a08", "training", 0),
            new ActivityType("a09", "10k step", 0),
            new ActivityType("a10", "+10k step", 0),
            new ActivityType("a11", "reading", 0),
            new ActivityType("a12", "early sleep", 0)
    );

    private static Map<String, ActivityType> MAP =
            LIST.stream().collect(Collectors.toMap(ActivityType::getCode, Function.<ActivityType>identity()));

    public static ActivityType get(String code) {
        return MAP.get(code);
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
