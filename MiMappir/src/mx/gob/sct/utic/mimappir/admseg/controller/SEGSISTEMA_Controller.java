package mx.gob.sct.utic.mimappir.admseg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGSISTEMA;
import mx.gob.sct.utic.mimappir.admseg.services.SEGSISTEMA_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SEGSISTEMA Controller - Spring
 * 
 * @author Ivan Santiago M�ndez
 * 
 */

@Controller
@RequestMapping("/web")
public class SEGSISTEMA_Controller  {

	private SEGSISTEMA_Service Service;
	
	@RequestMapping(value="/SEGSISTEMA_view.action")
	public @ResponseBody Map<String,? extends Object> externosView() throws Exception {

		try{

			List<SEGSISTEMA> SEGSISTEMA = Service.getList();

			return getMap(SEGSISTEMA);

		} catch (Exception e) {

			return getModelMapError("Error retrieving SEGSISTEMA from database. "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/SEGSISTEMA_create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {

		try{

			List<SEGSISTEMA> SEGSISTEMA = Service.create(data);

			return getMap(SEGSISTEMA);

		} catch (Exception e) {

			return getModelMapError("Error trying to create SEGSISTEMA.");
		}
	}
	
	@RequestMapping(value="/SEGSISTEMA_update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<SEGSISTEMA> SEGSISTEMA = Service.update(data);

			return getMap(SEGSISTEMA);

		} catch (Exception e) {

			return getModelMapError("Error trying to update SEGSISTEMA.");
		}
	}
	
	@RequestMapping(value="/SEGSISTEMA_delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {

			return getModelMapError("Error trying to delete SEGSISTEMA.");
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param SEGSISTEMA
	 * @return
	 */
	private Map<String,Object> getMap(List<SEGSISTEMA> SEGSISTEMA){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", SEGSISTEMA.size());
		modelMap.put("data", SEGSISTEMA);
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
	public void setsistemaService(SEGSISTEMA_Service service) {
		this.Service = service;
	}

}
