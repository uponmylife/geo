package geo.yoyo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Practice {
    @EmbeddedId
    private Pk pk;
    private int score;

    public static String[] TYPE_NAMES = {"NormalMeal", "PauseEating", "Running", "EarlySleep", "Sober"};

    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Pk implements Serializable {
        private String date;
        private Integer type;
    }
}
