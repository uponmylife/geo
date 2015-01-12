package geo.smoke;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

import static geo.smoke.Stat.Type.*;

@Controller
@RequestMapping("/smoke")
public class SmokeController {
    @RequestMapping( method = RequestMethod.GET)
    public String form(Map<String, Object> model) {
        model.put("stats", Arrays.asList(new Stat(WEEK, 7.2), new Stat(MONTH, 5.5), new Stat(QUARTER, 1.1)));
        model.put("smokes", Arrays.asList(new Smoke(new Date(), 40, 20), new Smoke(new Date(), 30, 20), new Smoke(new Date(), 23, 8)));
        return "smoke";
    }
}
