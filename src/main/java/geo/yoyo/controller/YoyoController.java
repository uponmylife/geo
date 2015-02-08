package geo.yoyo.controller;

import geo.yoyo.model.DayActivity;
import geo.yoyo.service.DayActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping("/yoyo")
public class YoyoController {
    @Autowired
    private DayActivityService repository;

    @RequestMapping(method = RequestMethod.GET)
    public String form(Integer dayAgo, Map<String, Object> model) {
        if (dayAgo == null) dayAgo = 0;

        model.put("dayAgo", dayAgo);
        model.put("dayActivity", repository.findOne(dayAgo));
        model.put("dayActivities", repository.findRecentAll(30));

        return "yoyo";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(Integer dayAgo, String[] activityCodes) {
        repository.save(new DayActivity(dayAgo, Arrays.asList(activityCodes)));
        return "redirect:/yoyo?dayAgo=" + dayAgo;
    }
}
