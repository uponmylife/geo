package geo.yoyo.repository;

import geo.yoyo.model.DayRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DayRecordRepository extends CrudRepository<DayRecord, Integer> {
    List<DayRecord> findByDateGreaterThanEqualOrderByDateDesc(Integer date);
}
