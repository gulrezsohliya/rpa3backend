package rpa.Security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import rpa.Dao.Admin.InitializationDaoInterface;
import rpa.Models.master.User;
import rpa.Services.Admin.PageurlsServiceInterface;

@Component
//@SessionAttributes({"user"})
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private static final Logger LOG = Logger.getLogger(MyAuthenticationSuccessHandler.class); 
		@Autowired
	    private InitializationDaoInterface admindao = null;
		@Autowired
		PageurlsServiceInterface pageser;
	    
	    @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        	
	    	HttpSession session = request.getSession();
	        User user = admindao.listUsers(SecurityContextHolder.getContext().getAuthentication().getName());
	        user.setPasswords(null);
//	        session.setAttribute("menu", pageser.getPageurls(SecurityContextHolder.getContext().getAuthentication().getName()).toJSONString());
	        session.setAttribute("menu", pageser.getPageurls(user.getUsercode()));
	        session.setAttribute("user", user);
	        setDefaultTargetUrl("/home.htm");
	        super.onAuthenticationSuccess(request, response, authentication);

	    }
}













