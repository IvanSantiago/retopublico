package mx.gob.sct.utic.mimappir.admseg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGMENU;
import mx.gob.sct.utic.mimappir.admseg.services.SEGMENU_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SEGMENU Controller - Spring
 * 
 * @author Ivan Santiago Méndez
 * 
 */

@Controller
@RequestMapping("/web")
public class SEGMENU_Controller  {

	private SEGMENU_Service Service;
	
	@RequestMapping(value="/SEGMENU_view.action")
	public @ResponseBody Map<String,? extends Object> view() throws Exception {

		try{

			List<SEGMENU> SEGMENU = Service.getMenuList();

			return getMap(SEGMENU);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error retrieving SEGMENU from database. "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/SEGMENU_create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {

		try{

			List<SEGMENU> SEGMENU = Service.create(data);

			return getMap(SEGMENU);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to create SEGMENU."+ e.getMessage());
		}
	}
	
	@RequestMapping(value="/SEGMENU_update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<SEGMENU> SEGMENU = Service.update(data);

			return getMap(SEGMENU);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to update SEGMENU."+ e.getMessage());
		}
	}
	
	@RequestMapping(value="/SEGMENU_delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to delete SEGMENU."+ e.getMessage());
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param SEGMENU
	 * @return
	 */
	private Map<String,Object> getMap(List<SEGMENU> SEGMENU){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", SEGMENU.size());
		modelMap.put("data", SEGMENU);
		modelMap.put("success", true);
		
		return modelMap;
	}
	
	/**
	 * Generates modelMap to return in the modelAndView in case
	 * of exception
	 * @param msg message
	 * @return
	 */
	private Map<String,Object> getModelMapError(String msg){

		Map<String,Object> modelMap = new HashMap<String,Object>(2);
		modelMap.put("message", msg);
		modelMap.put("success", false);

		return modelMap;
	} 


	@Autowired
	public void setSEGMENUService(SEGMENU_Service service) {
		this.Service = service;
	}

}
