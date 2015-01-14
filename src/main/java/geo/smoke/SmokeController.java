package geo.smoke;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

import static geo.smoke.StatView.Type.*;

@Controller
@RequestMapping("/smoke")
public class SmokeController {
    @Autowired
    private SmokeService service;

    @RequestMapping(method = RequestMethod.GET)
    public String form(Map<String, Object> model) {
        model.put("stats", Arrays.asList(new StatView(WEEK, 7.2), new StatView(MONTH, 5.5), new StatView(QUARTER, 1.1)));
        model.put("smokes", service.getSmokeViews());
        return "smoke";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(Map<String, Object> model, int count) {
        service.addSmoke(count);
        return form(model);
    }
}
