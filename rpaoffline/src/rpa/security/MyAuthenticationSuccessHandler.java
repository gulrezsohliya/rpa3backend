package rpa.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import rpa.dao.admin.InitializationDaoInterface;
import rpa.models.master.User;

@Component
//@SessionAttributes({"user"})
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private static final Logger LOG = Logger.getLogger(MyAuthenticationSuccessHandler.class); 
		@Autowired
	    private InitializationDaoInterface admindao = null;
	    
//	    public static HttpSession session() {
//	        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//	        return attr.getRequest().getSession(true); // true == allow create    
//	    }

	    @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        	
	    	HttpSession session = request.getSession();
	        User user = admindao.listUsers(authentication.getName());
	        user.setPasswords(null);
	        session.setAttribute("user", user.getUsername());
	        setDefaultTargetUrl("/home.htm");
	        super.onAuthenticationSuccess(request, response, authentication);

	    }
}













