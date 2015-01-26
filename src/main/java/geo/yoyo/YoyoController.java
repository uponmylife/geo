package geo.yoyo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/yoyo")
public class YoyoController {
    @Autowired
    private PracticeRepository practiceRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String form(Map<String, Object> model) {
        List<Practice> practices = practiceRepository.findByOrderByPkDayDesc();
        model.put("practiceTypes", PracticeType.findAll());
        model.put("reports", new ReportCreator(practices).getReports());
        model.put("records", new DayRecordCreator(practices).create());
        return "yoyo";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(Integer[] practices) {
        for (int practiceType : practices)
            practiceRepository.save(new Practice(new Date(), practiceType));
        return "redirect:/yoyo";
    }
}
