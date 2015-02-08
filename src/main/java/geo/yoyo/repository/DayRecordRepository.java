package geo.yoyo.repository;

import geo.yoyo.dao.DayRecordDao;
import geo.yoyo.model.DayRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static geo.util.DateUtil.getSystemDayDaysAgo;

@Repository
public class DayRecordRepository {
    @Autowired
    private DayRecordDao dayRecordDao;

    public void save(DayRecord dayRecord) {
        dayRecordDao.save(dayRecord);
    }

    public DayRecord findOne(int daysAgo) {
        int systemDay = getSystemDayDaysAgo(daysAgo);
        DayRecord dayRecord = dayRecordDao.findOne(systemDay);
        if (dayRecord == null) dayRecord = DayRecord.createNoActivitiesInstance(systemDay);
        return dayRecord;
    }

    public List<DayRecord> findRecentAll(int fromDaysAgo) {
        return dayRecordDao.findByDayGreaterThanEqualOrderByDayDesc(getSystemDayDaysAgo(fromDaysAgo));
    }


}
