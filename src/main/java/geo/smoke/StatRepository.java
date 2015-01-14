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

    public StatRepository(List<Smoke> smokes) {
        for (Smoke smoke : smokes) if (smoke.hasPrevDate()) stats.addAll(generate(smoke));
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

    public List<Stat> findAll(Date startDate, Date endDate) {
        List<Stat> result = new ArrayList<Stat>();
        for (Stat stat : stats) if (stat.between(startDate, endDate)) result.add(stat);
        return result;
    }
}
