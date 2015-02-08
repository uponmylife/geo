package geo.yoyo.repository;

import geo.yoyo.model.DayActivity;
import geo.yoyo.model.DayRecord;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static geo.util.DateUtil.systemDays;
import static geo.util.DateUtil.today;

@Repository
public class DayActivityRepository {
    @Autowired
    private DayRecordRepository dayRecordRepository;
    @Autowired
    private Function<DayRecord, DayActivity> activityConverter = (day) -> new DayActivity(day.getDate(), day.activities());

    public void save(DayActivity dayActivity) {
        DayRecord dayRecord = new DayRecord();
        dayRecord.setActivities(dayActivity.getActivities());
        dayRecordRepository.save(dayRecord);
    }

    public DayActivity findOne(int daysAgo) {
        int systemDay = getSystemDayDaysAgo(daysAgo);
        DayRecord dayRecord = dayRecordRepository.findOne(systemDay);
        if (dayRecord == null) dayRecord = DayRecord.createNoActivitiesInstance(systemDay);
        return activityConverter.apply(dayRecord);
    }

    public List<DayActivity> findRecentAll(int fromDaysAgo) {
        List<DayRecord> dayRecords = dayRecordRepository.findByDateGreaterThanEqualOrderByDateDesc(getSystemDayDaysAgo(fromDaysAgo));
        return dayRecords.stream().map(activityConverter).collect(Collectors.toList());
    }

    private int getSystemDayDaysAgo(int daysAgo) {
        return systemDays(DateUtils.addDays(today(), -1 * daysAgo));
    }
}
