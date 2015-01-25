package geo.yoyo;

import java.util.*;

public class DayRecordCreator {
    private List<Practice> practices;

    public DayRecordCreator(List<Practice> practices) {
        this.practices = practices;
    }

    public Collection<DayRecord> create() {
        Map<String, DayRecord> map = new TreeMap<String, DayRecord>(Collections.reverseOrder());
        for (Practice practice : practices) {
            DayRecord record = map.get(practice.getDay());
            if (record == null) {
                record = new DayRecord(practice.getDate());
                map.put(practice.getDay(), record);
            }
            record.setPractice(practice.getType());
            record.addScore(practice.getScore());
        }
        return map.values();
    }
}
