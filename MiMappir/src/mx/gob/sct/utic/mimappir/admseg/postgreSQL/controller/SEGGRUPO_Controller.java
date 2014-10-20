package mx.gob.sct.utic.mimappir.admseg.postgreSQL.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGGRUPO;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.services.SEGGRUPO_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SEGGRUPO Controller - Spring
 * 
 * @author Ivan Santiago Méndez
 * 
 */

@Controller
@RequestMapping("/web")
public class SEGGRUPO_Controller  {

	private SEGGRUPO_Service Service;
	
	@RequestMapping(value="/SEGGRUPO_view.action")
	public @ResponseBody Map<String,? extends Object> externosView() throws Exception {

		try{

			List<SEGGRUPO> SEGGRUPO = Service.getList();

			return getMap(SEGGRUPO);

		} catch (Exception e) {

			return getModelMapError("Error retrieving SEGGRUPO from database. "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/SEGGRUPO_create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {

		try{

			List<SEGGRUPO> SEGGRUPO = Service.create(data);

			return getMap(SEGGRUPO);

		} catch (Exception e) {

			return getModelMapError("Error trying to create SEGGRUPO.");
		}
	}
	
	@RequestMapping(value="/SEGGRUPO_update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<SEGGRUPO> SEGGRUPO = Service.update(data);

			return getMap(SEGGRUPO);

		} catch (Exception e) {

			return getModelMapError("Error trying to update SEGGRUPO.");
		}
	}
	
	@RequestMapping(value="/SEGGRUPO_delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {

			return getModelMapError("Error trying to delete SEGGRUPO.");
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param SEGGRUPO
	 * @return
	 */
	private Map<String,Object> getMap(List<SEGGRUPO> SEGGRUPO){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", SEGGRUPO.size());
		modelMap.put("data", SEGGRUPO);
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
	public void setsistemaService(SEGGRUPO_Service service) {
		this.Service = service;
	}

}
