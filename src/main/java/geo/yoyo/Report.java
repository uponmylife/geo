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
        if (score < 50) return "FAT";
        if (score < 75) return "ACTIVE";
        if (score < 100) return "GOOD";
        if (score < 125) return "HUMAN";
        if (score < 150) return "MAN";
        if (score < 175) return "CHARMER";
        if (score < 200) return "ACTOR";
        return "HERO";
    }
}
