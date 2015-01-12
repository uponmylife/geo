package geo.smoke;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
@RequestMapping("/smoke")
public class SmokeController {
    @RequestMapping(method = RequestMethod.GET)
    public String form(Map<String, Object> model) {
        model.put("smokes", Arrays.asList(new Smoke(new Date(), 40, 20), new Smoke(new Date(), 30, 20), new Smoke(new Date(), 23, 8)));
        return "smoke";
    }
}
