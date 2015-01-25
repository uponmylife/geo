package geo.yoyo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PracticeRepository extends CrudRepository<Practice, Practice.Pk> {
    List<Practice> findByOrderByPkDayDesc();
}
