package geo.smoke;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
@RequestMapping("/smoke")
public class SmokeController {
//    @Autowired
    private SmokeRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public String form(Map<String, Object> model) {
        model.put("smokes", Arrays.asList(new Smoke(new Date(), 40, 20), new Smoke(new Date(), 30, 20), new Smoke(new Date(), 23, 8)));
        return "smoke";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(Map<String, Object> model, int count) {
        System.out.println("count=" + count);
        return form(model);
    }
}
