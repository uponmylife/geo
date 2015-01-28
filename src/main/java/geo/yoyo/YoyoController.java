package geo.yoyo;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
@RequestMapping("/yoyo")
public class YoyoController {
    @Autowired
    private PracticeRepository practiceRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String form(Integer dayAgo, Map<String, Object> model) {
        if (dayAgo == null) dayAgo = 0;
        List<Practice> practices = practiceRepository.findByOrderByPkDayDesc();
        model.put("formDefault", getFormDefault(dayAgo, practices));
        model.put("dayAgo", dayAgo);
        model.put("practiceTypes", PracticeType.findAll());
        model.put("reports", new ReportCreator(practices).getReports());
        model.put("records", new DayRecordCreator(practices).create());
        return "yoyo";
    }

    private Set getFormDefault(Integer dayAgo, List<Practice> practices) {
        Set<Integer> set = new HashSet<Integer>();
        for (Practice practice : practices) {
            Date formDate = DateUtils.addDays(new Date(), dayAgo);
            if (practice.isSameDay(formDate)) set.add(practice.getType());
        }
        return set;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(Integer dayAgo, Integer[] practices) {
        Date date = DateUtils.addDays(new Date(), dayAgo);
        for (int practiceType : practices)
            practiceRepository.save(new Practice(date, practiceType));
        return "redirect:/yoyo?dayAgo=" + dayAgo;
    }
}
