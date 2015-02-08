package geo.yoyo.service;

import geo.yoyo.model.DayActivity;
import geo.yoyo.model.DayRecord;
import geo.yoyo.repository.DayRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DayActivityService {
    @Autowired
    private DayRecordRepository dayRecordRepository;

    public void save(DayActivity dayActivity) {
        DayRecord dayRecord = new DayRecord();
        dayRecord.setDay(dayActivity.getDay());
        dayRecord.setActivities(dayActivity.getActivities());
        dayRecordRepository.save(dayRecord);
    }

    public DayActivity findOne(int daysAgo) {
        return new DayActivity(dayRecordRepository.findOne(daysAgo));
    }

    public List<DayActivity> findRecentAll(int fromDaysAgo) {
        return dayRecordRepository.findRecentAll(fromDaysAgo).stream()
                .map(dayRecord -> new DayActivity(dayRecord))
                .collect(Collectors.toList());
    }
}
