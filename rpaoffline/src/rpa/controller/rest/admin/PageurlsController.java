package rpa.controller.rest.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

//import model.JSONmodels.Pageurls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import rpa.models.master.Pageurls;
import rpa.services.admin.PageurlsService;

@RestController
public class PageurlsController {

	private static final Logger logger = LoggerFactory.getLogger(PageurlsController.class);
	@Autowired PageurlsService pageser;

	public static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true); // true == allow create
	}

	@GetMapping(value = "/getMenu.htm")
	public @ResponseBody String getMenu() {
		return pageser.getPageurls().toJSONString();
	}

	// @RequestMapping(value = "/getHeaders.htm", method = RequestMethod.POST)
	public String getHeaders() {
		return pageser.getHeaders();
	}

	@GetMapping(value = "/getSubmenu")
	public @ResponseBody List<Map<String, Object>> getSubmenu(@RequestParam String val) {

		return pageser.getSubmenu(val);
	}

	@GetMapping(value = "/listUrls")
	public @ResponseBody List<Pageurls> listUrls() {
		return pageser.listUrls();
	}

}
