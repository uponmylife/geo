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
        if (lastSmoke != null) {
            prevDate = lastSmoke.getDate();
            if (DateUtil.isSameDay(prevDate, new Date())) throw new RuntimeException("only one submit for one day.");
        }
        repository.save(new Smoke(prevDate, count));
    }

    public List<SmokeView> getSmokeViews() {
        List<SmokeView> views = new ArrayList<SmokeView>();
        for (Smoke smoke : repository.findByOrderByDateDesc()) views.add(new SmokeView(smoke));
        return views;
    }

    public List<StatView> getStatViews() {
        List<StatView> views = new ArrayList<StatView>();
        List<Smoke> smokes = repository.findByOrderByDateAsc();
        StatRepository statRepository = new StatRepository(smokes);
        views.add(new StatView(StatView.Type.DAY3, statRepository.getAvgScore(3)));
        views.add(new StatView(StatView.Type.WEEK, statRepository.getAvgScore(7)));
        views.add(new StatView(StatView.Type.MONTH, statRepository.getAvgScore(30)));
        views.add(new StatView(StatView.Type.QUARTER, statRepository.getAvgScore(91)));
        views.add(new StatView(StatView.Type.HALF, statRepository.getAvgScore(182)));
        views.add(new StatView(StatView.Type.YEAR, statRepository.getAvgScore(365)));
        return views;
    }

}
