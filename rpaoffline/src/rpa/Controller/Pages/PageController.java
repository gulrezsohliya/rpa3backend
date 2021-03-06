package rpa.Controller.Pages;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rpa.Services.Admin.IntializationServiceInterface;
import rpa.Services.Admin.PageurlsServiceInterface;

@Controller
public class PageController {

	private static final Logger LOG = Logger.getLogger(PageController.class);
	@Autowired
	private IntializationServiceInterface service;
	@Autowired
	PageurlsServiceInterface pageser;

	/********************************************************
	 * PAGEURLs
	 ***********************************************************/

	@RequestMapping(value = "/advertisement.htm", method = RequestMethod.GET)
	public String advertisement() {
		return "init/advertisement";
	}

	@RequestMapping(value = "/optionalsubjects.htm", method = RequestMethod.GET)
	public String initoptionalsubject() {
		return "init/optionalsubjects";
	}

	@RequestMapping(value = "/examsubjects.htm", method = RequestMethod.GET)
	public String initexamsubject() {
		return "init/examsubjects";
	}

	@RequestMapping(value = "/othercategories.htm", method = RequestMethod.GET)
	public String initOtherCategories() {
		return "init/othercategories";
	}

	@RequestMapping(value = "/cells.htm", method = RequestMethod.GET)
	public String initCell() {
		return "init/cells";
	}

	@RequestMapping(value = "/officeCenterMap.htm", method = RequestMethod.GET)
	public String initofficeCenterMap() {
		return "init/officeCenterMap";
	}

	@RequestMapping(value = "/examcenter.htm", method = RequestMethod.GET)
	public String initExamCenter() {
		return "init/examcenter";
	}

	@RequestMapping(value = "/venue.htm", method = RequestMethod.GET)
	public String initVenue() {
		return "init/venue";
	}

	@RequestMapping(value = "/office.htm", method = RequestMethod.GET)
	public String initOffice() {
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

	@RequestMapping(value = "/createuser.htm", method = RequestMethod.GET)
	public String createUser(Model model) {
		return "init/createuser";
	}

}
