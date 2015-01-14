package geo.smoke;

import lombok.Data;

@Data
public class StatView {
    private Type type;
    private String score;
    private int level;

    public StatView(Type type, double score) {
        this.type = type;
        this.score = String.format("%.1f", score);
        for (int i=0; i<LEVEL_SCORE.length; i++) if (score >= LEVEL_SCORE[i]) level = i;
    }

    public static double[] LEVEL_SCORE = {0.0, 4.0, 6.0, 8.0, 12.0, 24.0};
    public static String[] LEVEL_NAME = {"HEAVY SMOKER", "STARTED", "EXCELLENT", "SMOKER", "RUNNER", "MASTER"};
    public enum Type {DAY3, WEEK, MONTH, QUARTER, HALF, YEAR}

    public String getLevelName() {
        return LEVEL_NAME[level];
    }
}
