package cc.fireflyhut.girafe.controller.view;

import cc.fireflyhut.girafe.constants.ViewConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    /**
     * 主页
     * @return
     */
    @RequestMapping("/")
    public String mainPage() {
        return ViewConstants.MAIN_PAGE_HTML_FILE_NAME;
    }

    @RequestMapping("/favicon*")
    @ResponseBody
    public String favicon() {
        return "";
    }

    @RequestMapping("/robots.txt")
    public String robots() {
        return "robots";
    }
}
