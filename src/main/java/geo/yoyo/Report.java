package geo.yoyo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Report {
    private String range;
    private int[] typeScore;
    private int score;

    public String getLevel() {
        if (score < Practice.DEFAULT_SCORE * 2) return "FAT";
        if (score < Practice.DEFAULT_SCORE * 3) return "ACTIVE";
        if (score < Practice.DEFAULT_SCORE * 4) return "GOOD";
        if (score < Practice.DEFAULT_SCORE * 5) return "HUMAN";
        if (score < Practice.DEFAULT_SCORE * 6) return "MAN";
        if (score < Practice.DEFAULT_SCORE * 7) return "CHARMER";
        if (score < Practice.DEFAULT_SCORE * 8) return "ACTOR";
        return "HERO";
    }
}
