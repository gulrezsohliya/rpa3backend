package rpa.RestController.Admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

//import model.JSONmodels.Pageurls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import rpa.Models.master.Pageurls;
import rpa.Models.master.User;
import rpa.Models.master.UserPages;
import rpa.Services.Admin.PageurlsServiceInterface;

@RestController
public class PageurlsController {

	private static final Logger LOG = LoggerFactory.getLogger(PageurlsController.class);
	@Autowired
	PageurlsServiceInterface pageser;

	public static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true); // true == allow create
	}

	@GetMapping(value = "/getMenu.htm")
	public @ResponseBody String getMenu() {
		return pageser.getPageurls(SecurityContextHolder.getContext().getAuthentication().getName()).toJSONString();
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

	@GetMapping(value = "/listUserAndMappedPages")
	public @ResponseBody List<User> listUserAndMappedPages() {

		return pageser.listUserAndMappedPages();
	}
	
	@PostMapping(value = "/saveUserpages")
	public @ResponseBody String saveUserpages(@RequestBody List<UserPages> userpages) {

		return pageser.saveUserpages(userpages);
	}

	@PostMapping(value = "/saveMenu.htm")
    public ResponseEntity<String> saveMenu(@RequestBody Pageurls url) {
                
		return ResponseEntity.ok().body(pageser.savePageurl(url));
    }
}
