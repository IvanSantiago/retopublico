package mx.gob.sct.utic.mimappir.admseg.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles and retrieves the login or denied page depending on the URI template
 */
@Controller
@RequestMapping("/auth") 
public class ADMSEG_LoginLogout_Controller {
	/**
	 * Handles and retrieves the login JSP page
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/admseglogin.action", method = RequestMethod.GET)
	public String getLoginPage(
			@RequestParam(value = "error", required = false) boolean error,
			ModelMap model) {

		// Add an error message to the model if login is unsuccessful
		// The 'error' parameter is set to true based on the when the
		// authentication has failed.
		// We declared this under the authentication-failure-url attribute
		// inside the spring-security.xml
		if (error == true) {
			// Assign an error message
			model.put("error",
					"You have entered an invalid username or password!");
		} else {
			model.put("error", "");
		}		
		return "loginpage";
	}

	/**
	 * Handles and retrieves the denied JSP page. This is shown whenever a
	 * regular user tries to access a page without permission.
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/admsegdenied.action", method = RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object> getDeniedPage() throws Exception {
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("success", false);
		modelMap.put("msg", "Login incorrecto");
		modelMap.put("url", "admsegerror.action");
		return modelMap;
	}

	/**
	 * Handles and retrieves the common JSP page that everyone can see
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/admsegerror.action", method = RequestMethod.GET)
    public String getErrorPage() {    
    	return "deniedpage";
	}
	
	/**
	 * Handles and retrieves the denied JSP page. This is shown whenever a
	 * regular user tries to access a page without permission.
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value="/admseggranted.action")
	public @ResponseBody Map<String,? extends Object> getGrantedPage() throws Exception {
			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);
			modelMap.put("msg", "Login correcto");
			modelMap.put("url", "../web/admsegmain.action");
			return modelMap;
	}
}