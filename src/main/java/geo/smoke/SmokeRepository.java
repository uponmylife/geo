package geo.smoke;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SmokeRepository extends CrudRepository<Smoke, Long> {
    List<Smoke> findByOrderByDateDesc();
    Smoke findTop1ByOrderByDateDesc();
}
