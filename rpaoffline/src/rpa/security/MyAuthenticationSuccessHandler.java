package rpa.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private static final Logger LOG = Logger.getLogger(MyAuthenticationSuccessHandler.class); 
//		@Autowired
//	    private AdminDao admindao = null;
	    
	    public static HttpSession session() {
	        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	        return attr.getRequest().getSession(true); // true == allow create    
	    }

	    @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        	LOG.info("onAuthenticationSuccess");
        	LOG.info(authentication.getCredentials().toString());
	    	HttpSession session = request.getSession();
//	        Userlogins Currentuser = admindao.getCurrentUser();
	        setDefaultTargetUrl("/home.htm");
//	            session.setAttribute("user", (Currentuser.getOfficername()!=null)?Currentuser.getOfficername():null);
//	            session.setAttribute("userrole", (Currentuser.getUserrole()!=null)?Currentuser.getUserrole():null);
	        super.onAuthenticationSuccess(request, response, authentication);

	    }
}













