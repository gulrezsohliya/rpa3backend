package rpa.controller.page;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rpa.services.admin.AdminService;
import rpa.services.admin.PageurlsService;

@Controller
public class PageController {

	private static final Logger LOG = Logger.getLogger(PageController.class);
	@Autowired private AdminService service;
	@Autowired PageurlsService pageser;
	/********************************************************
	 * PAGEURLs
	 ***********************************************************/
	 
	@RequestMapping(value = "/createuser", method = RequestMethod.GET)
	public String createUser() {
		LOG.info("createuser");
		return "init/createuser";
	}

	@RequestMapping(value = "/initializecell", method = RequestMethod.GET)
	public String initCell() {
		LOG.info("initializecell");
		return "init/initializecell";
	}

	@RequestMapping(value = "/initializeoffice", method = RequestMethod.GET)
	public String initOffice() {
		LOG.info("initializeoffice");
		return "init/initializeoffice";
	}
	
	@RequestMapping(value = "accesscontrol.htm", method = RequestMethod.GET)
    public String pageLoad_AccessControl(ModelMap model) {
        return "accesscontrol";
    }

    @RequestMapping(value = "initpageurl.htm", method = RequestMethod.GET)
    public String pageLoad_Menu(ModelMap model) {
        model.addAttribute("headers", pageser.getHeaders());
        return "initpageurl";
    }

    
}
