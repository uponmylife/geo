package geo.yoyo.dao;

import geo.yoyo.model.DayRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DayRecordDao extends CrudRepository<DayRecord, Integer> {
    List<DayRecord> findByDayGreaterThanEqualOrderByDayDesc(Integer day);
}
