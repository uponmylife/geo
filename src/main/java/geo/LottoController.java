package geo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class LottoController {
    @RequestMapping("/lotto")
    public String lotto(Map<String, Object> model) {
        model.put("message", "크리스마스선물");
        return "lotto";
    }
}
