package geo.yoyo.repository;

import geo.yoyo.model.DayActivity;
import geo.yoyo.model.DayRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static geo.util.DateUtil.getSystemDayDaysAgo;

@Repository
public class DayActivityRepository {
    @Autowired
    private DayRecordRepository dayRecordRepository;

    public void save(DayActivity dayActivity) {
        DayRecord dayRecord = new DayRecord();
        dayRecord.setDay(dayActivity.getDay());
        dayRecord.setActivities(dayActivity.getActivities());
        dayRecordRepository.save(dayRecord);
    }

    public DayActivity findOne(int daysAgo) {
        int systemDay = getSystemDayDaysAgo(daysAgo);
        DayRecord dayRecord = dayRecordRepository.findOne(systemDay);
        if (dayRecord == null) dayRecord = DayRecord.createNoActivitiesInstance(systemDay);
        return new DayActivity(dayRecord);
    }

    public List<DayActivity> findRecentAll(int fromDaysAgo) {
        List<DayRecord> dayRecords = dayRecordRepository.findByDayGreaterThanEqualOrderByDayDesc(getSystemDayDaysAgo(fromDaysAgo));
        return dayRecords.stream().map((dayRecord) -> new DayActivity(dayRecord)).collect(Collectors.toList());
    }
}
