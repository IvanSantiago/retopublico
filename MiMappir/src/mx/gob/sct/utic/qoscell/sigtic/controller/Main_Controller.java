package mx.gob.sct.utic.qoscell.sigtic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sct.utic.qoscell.sigtic.db2.model.MENU;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.USUARIOS;
import mx.gob.sct.utic.qoscell.sigtic.services.CustomUserDetailsService;
import mx.gob.sct.utic.qoscell.sigtic.services.MENU_Service;
import mx.gob.sct.utic.qoscell.sigtic.services.USUARIOS_Service;
import mx.gob.sct.utic.qoscell.util.MenuCreator;
import mx.gob.sct.utic.qoscell.util.MenuNode;
import mx.gob.sct.utic.qoscell.util.Util;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles and retrieves the web pages or action depending on the URI template.
 * A user must be log-in first he can access these pages.
 * @param <customUserDetailsService>
 */
@Controller
@RequestMapping("/web")
public class Main_Controller {
	private MENU_Service MENU_Service;
    
	/**
	 * Handles and retrieves the common JSP page that everyone can see
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/main.action", method = RequestMethod.GET)
    public String getMainPage() {
    	return "mainpage";
	}
    
	/**
	 * Handles and retrieves the common JSP page that everyone can see
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/inicio.action", method = RequestMethod.GET)
    public String getInicioPage() {
    	return "inicio";
	}
    
    /**
     * Handles and retrieves the JSON object of the menu structure
     * 
     * @return the JSON object of menu
     */
    @RequestMapping(value = "/menuview.action", method = RequestMethod.POST)
	//public @ResponseBody Map<String,? extends Object> getMenu(@RequestParam Object UID_USUARIO) throws Exception {
    public @ResponseBody List<MenuNode> getMenuPOST() throws Exception {
    	try{
    		String LOGIN = SecurityContextHolder.getContext().getAuthentication().getName();
			List<MENU> registros = MENU_Service.getMenuForUser(LOGIN);
			List<MenuNode> ArbolMenu = new MenuCreator().createMainMenuFromList(registros);
			return ArbolMenu;

		} catch (Exception e) {
			e.printStackTrace();
			List<MenuNode> errorList = new ArrayList<MenuNode>();
			MenuNode nodoNuevo = new MenuNode();
			errorList.add(nodoNuevo);
			return errorList;
		}
	}
    
    /**
     * Handles and retrieves the JSON object of the menu structure
     * 
     * @return the JSON object of menu
     */
    @RequestMapping(value = "/menuviewget.action", method = RequestMethod.GET)
	//public @ResponseBody Map<String,? extends Object> getMenu(@RequestParam Object UID_USUARIO) throws Exception {
    public @ResponseBody List<MenuNode> getMenuGET(@RequestParam String LOGIN) throws Exception {
    	try{
    		//Integer IUID_USUARIO = Integer.parseInt((String)UID_USUARIO);
			List<MENU> registros = MENU_Service.getMenuForUser(LOGIN);
			List<MenuNode> ArbolMenu = new MenuCreator().createMainMenuFromList(registros);
			//return getMenuMap(ArbolMenu);
			return ArbolMenu;

		} catch (Exception e) {
			e.printStackTrace();
			List<MenuNode> errorList = new ArrayList<MenuNode>();
			MenuNode nodoNuevo = new MenuNode();
			errorList.add(nodoNuevo);
			return errorList;
		}
	}
    
    /**
     * Handles and retrieves the admin JSP page that only admins can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/crud.action", method = RequestMethod.GET)
    public String getCrudPage() {
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/adminpage.jsp
    	return "crudgrid";
	}   
    /**
     * Handles and retrieves the admin JSP page that only admins can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/admin.action", method = RequestMethod.GET)
    public String getAdminPage() {
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/adminpage.jsp
    	return "adminpage";
	}
    
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param registros
	 * @return
	 */
	@SuppressWarnings("unused")
	public Map<String,Object> getMenuMap(List<?> registros){
		/*
		 * Programacion para la obtencion de mapa para al generacion del arbol de despliegue
		 * 
		 * */
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", registros.size());
		modelMap.put("data", registros);
		modelMap.put("success", true);		
		return modelMap;
	}    
	
	@Autowired
	public void setMenuService(MENU_Service service) {
		this.MENU_Service = service;
	}	
}
