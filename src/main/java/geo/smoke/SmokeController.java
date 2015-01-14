package geo.smoke;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping("/smoke")
@Slf4j
public class SmokeController {
    @Autowired
    private SmokeService service;

    @RequestMapping(method = RequestMethod.GET)
    public String form(Map<String, Object> model) {
        model.put("stats", service.getStatViews());
        model.put("smokes", service.getSmokeViews());
        return "smoke";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(Map<String, Object> model, int count) {
        service.addSmoke(count);
        return "redirect:/smoke";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Map<String, Object> model, Exception e) {
        log.error(e.toString(), e);
        model.put("message", e.toString());
        return "error";
    }
}
