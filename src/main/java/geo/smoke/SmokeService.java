package geo.smoke;

import geo.util.DateUtil;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SmokeService {
    @Autowired
    private SmokeRepository repository;

    public void addSmoke(int count) {
        Date prevDate = null;
        Smoke lastSmoke = repository.findTop1ByOrderByDateDesc();
        if (lastSmoke != null) prevDate = lastSmoke.getDate();
        repository.save(new Smoke(prevDate, count));
    }

    public List<SmokeView> getSmokeViews() {
        List<SmokeView> views = new ArrayList<SmokeView>();
        for (Smoke smoke : repository.findByOrderByDateDesc()) views.add(new SmokeView(smoke));
        return views;
    }

    public List<StatView> getStatViews() {
        List<Stat> views = new ArrayList<Stat>();

        List<Smoke> smokes = repository.findByOrderByDateAsc();
        StatRepository statRepository = new StatRepository(smokes);


        return null;

    }

    private Double getAvgScore(StatRepository statRepository, int daysAgo) {
        Date now = new Date();
        Date startDate = DateUtils.addDays(now, -1 * daysAgo);
        List<Stat> stats = statRepository.findAll(DateUtil.startOfDay(startDate), now);
        if (stats.size() == 0 || stats.get(0).hasSameDay(startDate)) return null;
        double sum = 0.0;
        for (Stat stat : stats) sum += (stat.getScore() * stat.getWeight());
        return sum / (double) stats.size();
    }
}
