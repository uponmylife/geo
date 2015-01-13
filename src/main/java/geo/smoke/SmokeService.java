package geo.smoke;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmokeService {
    @Autowired
    private SmokeRepository repository;

    public void add(Smoke smoke) {
        Smoke lastSmoke = repository.findTop1ByOrderByDateDesc();
        if (lastSmoke == null) smoke.setTerm(0l);
        else smoke.setTerm(System.currentTimeMillis() - lastSmoke.getDate().getTime());
        repository.save(smoke);
    }

    public List<Smoke> list() {
        return repository.findByOrderByDateDesc();
    }
}
