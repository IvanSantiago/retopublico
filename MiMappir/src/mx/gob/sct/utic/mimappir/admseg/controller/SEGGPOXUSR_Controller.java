package mx.gob.sct.utic.mimappir.admseg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGGPOXUSR;
import mx.gob.sct.utic.mimappir.admseg.services.SEGGPOXUSR_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SEGGPOXUSR Controller - Spring
 * 
 * @author Ivan Santiago Méndez
 * 
 */

@Controller
@RequestMapping("/web")
public class SEGGPOXUSR_Controller  {

	private SEGGPOXUSR_Service Service;
	
	@RequestMapping(value="/SEGGPOXUSR_view.action")
	public @ResponseBody Map<String,? extends Object> externosView() throws Exception {

		try{

			List<SEGGPOXUSR> SEGGPOXUSR = Service.getList();

			return getMap(SEGGPOXUSR);

		} catch (Exception e) {

			return getModelMapError("Error retrieving SEGGPOXUSR from database. "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/SEGGPOXUSR_create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {

		try{

			List<SEGGPOXUSR> SEGGPOXUSR = Service.create(data);

			return getMap(SEGGPOXUSR);

		} catch (Exception e) {

			return getModelMapError("Error trying to create SEGGPOXUSR.");
		}
	}
	
	@RequestMapping(value="/SEGGPOXUSR_update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<SEGGPOXUSR> SEGGPOXUSR = Service.update(data);

			return getMap(SEGGPOXUSR);

		} catch (Exception e) {

			return getModelMapError("Error trying to update SEGGPOXUSR.");
		}
	}
	
	@RequestMapping(value="/SEGGPOXUSR_delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {

			return getModelMapError("Error trying to delete SEGGPOXUSR.");
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param SEGGPOXUSR
	 * @return
	 */
	private Map<String,Object> getMap(List<SEGGPOXUSR> SEGGPOXUSR){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", SEGGPOXUSR.size());
		modelMap.put("data", SEGGPOXUSR);
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
	public void setsistemaService(SEGGPOXUSR_Service service) {
		this.Service = service;
	}

}
