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

        Double day3Score = statRepository.getAvgScore(3);
        if (day3Score != null) views.add(new StatView(StatView.Type.DAY3, day3Score));

        Double weekScore = statRepository.getAvgScore(7);
        if (weekScore !=null) views.add(new StatView(StatView.Type.WEEK, weekScore));

        Double monthScore = statRepository.getAvgScore(30);
        if (monthScore != null) views.add(new StatView(StatView.Type.MONTH, monthScore));

        Double quarterScore = statRepository.getAvgScore(91);
        if (quarterScore != null) views.add(new StatView(StatView.Type.QUARTER, quarterScore));

        Double halfScore = statRepository.getAvgScore(182);
        if (halfScore != null) views.add(new StatView(StatView.Type.HALF, halfScore));

        Double yearScore = statRepository.getAvgScore(365);
        if (yearScore != null) views.add(new StatView(StatView.Type.YEAR, yearScore));

        return views;
    }

}
