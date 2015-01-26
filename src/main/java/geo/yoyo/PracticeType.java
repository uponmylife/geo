package geo.yoyo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PracticeType {
    private Integer code;
    private String name;
    public static String[] NAMES = {"simple meal", "rest a eating", "no night snack", "no alcohol", "drink 3l water", "running", "walk 5km", "early sleep"};

    public static int size() {
        return NAMES.length;
    }

    public static List<PracticeType> findAll() {
        List<PracticeType> list = new ArrayList<PracticeType>();
        for (int i=0; i<NAMES.length; i++) list.add(new PracticeType(i, NAMES[i]));
        return list;
    }
}
