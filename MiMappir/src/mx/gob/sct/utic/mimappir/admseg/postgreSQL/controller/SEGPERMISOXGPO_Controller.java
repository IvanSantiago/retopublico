package mx.gob.sct.utic.mimappir.admseg.postgreSQL.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGPERMISOXGPO;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.services.SEGPERMISOXGPO_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SEGPERMISOXGPO Controller - Spring
 * 
 * @author Ivan Santiago Méndez
 * 
 */

@Controller
@RequestMapping("/web")
public class SEGPERMISOXGPO_Controller  {

	private SEGPERMISOXGPO_Service Service;
	
	@RequestMapping(value="/SEGPERMISOXGPO_view.action")
	public @ResponseBody Map<String,? extends Object> externosView() throws Exception {

		try{

			List<SEGPERMISOXGPO> SEGPERMISOXGPO = Service.getList();

			return getMap(SEGPERMISOXGPO);

		} catch (Exception e) {

			return getModelMapError("Error retrieving SEGPERMISOXGPO from database. "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/SEGPERMISOXGPO_create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {

		try{

			List<SEGPERMISOXGPO> SEGPERMISOXGPO = Service.create(data);

			return getMap(SEGPERMISOXGPO);

		} catch (Exception e) {

			return getModelMapError("Error trying to create SEGPERMISOXGPO.");
		}
	}
	
	@RequestMapping(value="/SEGPERMISOXGPO_update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<SEGPERMISOXGPO> SEGPERMISOXGPO = Service.update(data);

			return getMap(SEGPERMISOXGPO);

		} catch (Exception e) {

			return getModelMapError("Error trying to update SEGPERMISOXGPO.");
		}
	}
	
	@RequestMapping(value="/SEGPERMISOXGPO_delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {

			return getModelMapError("Error trying to delete SEGPERMISOXGPO.");
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param SEGPERMISOXGPO
	 * @return
	 */
	private Map<String,Object> getMap(List<SEGPERMISOXGPO> SEGPERMISOXGPO){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", SEGPERMISOXGPO.size());
		modelMap.put("data", SEGPERMISOXGPO);
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
	public void setsistemaService(SEGPERMISOXGPO_Service service) {
		this.Service = service;
	}

}
