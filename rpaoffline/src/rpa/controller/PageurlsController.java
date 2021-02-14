package rpa.controller;
import rpa.models.Pageurls;
import rpa.services.PageurlsService;

import java.util.List;

import javax.servlet.http.HttpSession;
//import model.JSONmodels.Pageurls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class PageurlsController {

    private static final Logger logger = LoggerFactory.getLogger(PageurlsController.class);
    @Autowired
    PageurlsService pageser;

    public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create    
    }

    @RequestMapping(value = "initpageurl.htm", method = RequestMethod.GET)
    public String pageLoad_Menu(ModelMap model) {

        model.addAttribute("headers", getHeaders());
        return "initpageurl";
    }

    @RequestMapping(value = "/getMenu.htm", method = RequestMethod.POST)
    public @ResponseBody
    String getMenu() {

        return pageser.getPageurls().toJSONString();
    }

 //   @RequestMapping(value = "/getHeaders.htm", method = RequestMethod.POST)
    public String getHeaders() {

        return pageser.getHeaders();
    }
//
//    @RequestMapping(value = "/getSubmenu.htm", method = RequestMethod.POST)
//    public @ResponseBody 
//            String getSubmenu(String val) {
//                
//        return pageser.getSubmenu(val);
//    }
//
//    @RequestMapping(value = "/getSubsubmenu.htm", method = RequestMethod.POST)
//    public @ResponseBody 
//            String getSubmenu(String parent,String submenu) {
//                
//        return pageser.getSubmenu(parent,submenu);
//    }
//
//    @RequestMapping(value = "/saveMenu.htm", method = RequestMethod.POST)
//    public @ResponseBody 
//            String saveMenu(@RequestBody Pageurls url) {
//                
//        return pageser.savePageurl(new model.persitent.Pageurls(url));
//    }
//    
    @RequestMapping(value = "/listUrls", method = RequestMethod.POST)
    public @ResponseBody 
            List<Pageurls> listUrls() {
                
        return pageser.listUrls();
    }
}
