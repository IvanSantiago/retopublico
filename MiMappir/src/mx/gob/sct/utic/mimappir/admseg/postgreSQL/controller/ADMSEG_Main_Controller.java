package mx.gob.sct.utic.mimappir.admseg.postgreSQL.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGGPOXUSR;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGMENU;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGSISTEMA;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGUSUARIO;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.services.SEGMENU_Service;
import mx.gob.sct.utic.mimappir.sigtic.db2.model.MENU;
import mx.gob.sct.utic.mimappir.util.MenuCreator;
import mx.gob.sct.utic.mimappir.util.MenuNode;
import mx.gob.sct.utic.mimappir.util.MenuNodeObject;
import mx.gob.sct.utic.mimappir.util.Util;

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
public class ADMSEG_Main_Controller {
	private SEGMENU_Service MENU_Service;
    
	/**
	 * Handles and retrieves the common JSP page that everyone can see
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/admsegmain.action", method = RequestMethod.GET)
    public String getMainPage() {
    	return "mainpage";
	}
    
	/**
	 * Handles and retrieves the common JSP page that everyone can see
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/admseginicio.action", method = RequestMethod.GET)
    public String getInicioPage() {
    	return "inicio";
	}
    
    /**
     * Handles and retrieves the JSON object of the menu structure
     * 
     * @return the JSON object of menu
     */
    @RequestMapping(value = "/admsegmenuview.action")
	//public @ResponseBody Map<String,? extends Object> getMenu(@RequestParam Object UID_USUARIO) throws Exception {
    public @ResponseBody List<MenuNodeObject> getMenuPOST() throws Exception {
    	try{
    		String LOGIN = SecurityContextHolder.getContext().getAuthentication().getName();
			List<SEGMENU> registros = MENU_Service.getMenuForUser(LOGIN);
			List<MenuNodeObject> ArbolMenu = new MenuCreator().createMainSEGMenuFromList(registros);
			return ArbolMenu;

		} catch (Exception e) {
			e.printStackTrace();
			List<MenuNodeObject> errorList = new ArrayList<MenuNodeObject>();
			MenuNodeObject nodoNuevo = new MenuNodeObject();
			errorList.add(nodoNuevo);
			return errorList;
		}
	}
    
    /**
     * Handles and retrieves the JSON object of the menu structure
     * 
     * @return the JSON object of menu
     */
    @RequestMapping(value = "/admsegSEGUSUARIOview.action")
	//public @ResponseBody Map<String,? extends Object> getMenu(@RequestParam Object UID_USUARIO) throws Exception {
    public @ResponseBody List<SEGUSUARIO> getSEGGPOXUSR() throws Exception {
    	try{
    		List<SEGUSUARIO> registros = MENU_Service.getSEGGPOXUSR();
			return registros;

		} catch (Exception e) {
			e.printStackTrace();
			List<SEGUSUARIO> errorList = new ArrayList<SEGUSUARIO>();
			SEGUSUARIO nodoNuevo = new SEGUSUARIO();
			errorList.add(nodoNuevo);
			return errorList;
		}
	}
    
    /**
     * Handles and retrieves the JSON object of the menu structure
     * 
     * @return the JSON object of menu
     */
    @RequestMapping(value = "/admsegmenuviewget.action", method = RequestMethod.GET)
	//public @ResponseBody Map<String,? extends Object> getMenu(@RequestParam Object UID_USUARIO) throws Exception {
    public @ResponseBody List<MenuNodeObject> getMenuGET(@RequestParam String LOGIN) throws Exception {
    	try{
    		//Integer IUID_USUARIO = Integer.parseInt((String)UID_USUARIO);
			List<SEGMENU> registros = MENU_Service.getMenuForUser(LOGIN);
			List<MenuNodeObject> ArbolMenu = new MenuCreator().createMainSEGMenuFromList(registros);
			//return getMenuMap(ArbolMenu);
			return ArbolMenu;

		} catch (Exception e) {
			e.printStackTrace();
			List<MenuNodeObject> errorList = new ArrayList<MenuNodeObject>();
			MenuNodeObject nodoNuevo = new MenuNodeObject();
			errorList.add(nodoNuevo);
			return errorList;
		}
	}
    
    /**
     * Handles and retrieves the admin JSP page that only admins can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/admsegcrud.action", method = RequestMethod.GET)
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
    @RequestMapping(value = "/admsegadmin.action", method = RequestMethod.GET)
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
	public void setMenuService(SEGMENU_Service service) {
		this.MENU_Service = service;
	}	
}
