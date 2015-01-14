package geo.smoke;

import geo.util.DateUtil;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.time.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
public class StatRepository {
    private List<Stat> stats = new ArrayList<Stat>();
    private Date now;

    public StatRepository(List<Smoke> smokes) {
        for (Smoke smoke : smokes) if (smoke.hasPrevDate()) stats.addAll(generate(smoke));
        if (smokes.size()>0) now = smokes.get(smokes.size()-1).getDate();
    }

    List<Stat> generate(Smoke smoke) {
        List<Stat> list = new ArrayList<Stat>();
        list.add(new Stat(smoke.getPrevDate(), smoke.getScore(), getDayWeight(smoke.getPrevDate(), false)));
        Date middleDate = DateUtils.addDays(smoke.getPrevDate(), 1);
        while (middleDate.before(DateUtil.startOfDay(smoke.getDate()))) {
            list.add(new Stat(middleDate, smoke.getScore(), 1.0));
            middleDate = DateUtils.addDays(middleDate, 1);
        }
        list.add(new Stat(smoke.getDate(), smoke.getScore(), getDayWeight(smoke.getDate(), true)));
        return list;
    }

    double getDayWeight(Date date, boolean before) {
        long hours = DateUtils.getFragmentInHours(date, Calendar.DATE);
        if (before) return hours / 24.0;
        else return (24 - hours) / 24.0;
    }

    List<Stat> findAll(Date startDate, Date endDate) {
        List<Stat> result = new ArrayList<Stat>();
        for (Stat stat : stats) if (stat.between(startDate, endDate)) result.add(stat);
        return result;
    }

    public Double getAvgScore(int daysAgo) {
        if (now == null) return null;
        Date startDate = DateUtils.addDays(now, -1 * daysAgo);
        List<Stat> stats = findAll(DateUtil.startOfDay(startDate), now);
        if (stats.size() == 0 || !stats.get(0).hasSameDay(startDate)) return null;
        double sum = 0.0;
        double weightSum = 0.0;
        for (Stat stat : stats) {
            weightSum += stat.getWeight();
            sum += stat.getWeight()/stat.getScore();
        }
        return weightSum / sum;
    }
}
