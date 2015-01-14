package geo.smoke;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SmokeRepository extends CrudRepository<Smoke, Long> {
    Smoke findTop1ByOrderByDateDesc();

    List<Smoke> findByOrderByDateAsc();

    List<Smoke> findByOrderByDateDesc();
}
