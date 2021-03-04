package rpa.controller.page;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rpa.services.admin.IntializationServiceInterface;
import rpa.services.admin.PageurlsServiceInterface;

@Controller
public class PageController {

	private static final Logger LOG = Logger.getLogger(PageController.class);
	@Autowired private IntializationServiceInterface service;
	@Autowired PageurlsServiceInterface pageser;
	/********************************************************
	 * PAGEURLs
	 ***********************************************************/
	 
	@RequestMapping(value = "/createuser.htm", method = RequestMethod.GET)
	public String createUser(Model model) {
		return "init/createuser";
	}

	@RequestMapping(value = "/cell.htm", method = RequestMethod.GET)
	public String initCell() {
		LOG.info("cell");
		return "init/cell";
	}

	@RequestMapping(value = "/examcenter.htm", method = RequestMethod.GET)
	public String initExamCenter() {
		LOG.info("ExamCenter");
		return "init/examcenter";
	}

	@RequestMapping(value = "/office.htm", method = RequestMethod.GET)
	public String initOffice() {
		LOG.info("office");
		return "init/office";
	}
	
	@RequestMapping(value = "accesscontrol.htm", method = RequestMethod.GET)
    public String pageLoad_AccessControl(ModelMap model) {
        return "init/accesscontrol";
    }

    @RequestMapping(value = "initpageurl.htm", method = RequestMethod.GET)
    public String pageLoad_Menu(ModelMap model) {
        model.addAttribute("headers", pageser.getHeaders());
        return "init/initpageurl";
    }

    
}
