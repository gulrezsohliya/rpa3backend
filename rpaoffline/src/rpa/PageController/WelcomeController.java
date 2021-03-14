package rpa.PageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

	@RequestMapping("/index.htm")
	public String index() {
		return "index";
	}

	@RequestMapping("/home.htm")
	public String home() {
		return "home";
	}
}
