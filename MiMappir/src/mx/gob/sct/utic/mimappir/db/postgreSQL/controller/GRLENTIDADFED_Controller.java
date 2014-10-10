package mx.gob.sct.utic.mimappir.db.postgreSQL.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.GRLENTIDADFED;
import mx.gob.sct.utic.mimappir.db.postgreSQL.services.GRLENTIDADFED_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * GRLENTIDADFED Controller - Spring
 * 
 * @author Ivan Santiago Méndez
 * 
 */

@Controller
@RequestMapping("/web")
public class GRLENTIDADFED_Controller  {

	private GRLENTIDADFED_Service Service;
	
	@RequestMapping(value="/GRLENTIDADFED_view.action")
	public @ResponseBody Map<String,? extends Object> externosView() throws Exception {

		try{

			List<GRLENTIDADFED> GRLENTIDADFED = Service.getList();

			return getMap(GRLENTIDADFED);

		} catch (Exception e) {

			return getModelMapError("Error retrieving GRLENTIDADFED from database. "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/GRLENTIDADFED_create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {

		try{

			List<GRLENTIDADFED> GRLENTIDADFED = Service.create(data);

			return getMap(GRLENTIDADFED);

		} catch (Exception e) {

			return getModelMapError("Error trying to create GRLENTIDADFED.");
		}
	}
	
	@RequestMapping(value="/GRLENTIDADFED_update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<GRLENTIDADFED> GRLENTIDADFED = Service.update(data);

			return getMap(GRLENTIDADFED);

		} catch (Exception e) {

			return getModelMapError("Error trying to update GRLENTIDADFED.");
		}
	}
	
	@RequestMapping(value="/GRLENTIDADFED_delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {

			return getModelMapError("Error trying to delete GRLENTIDADFED.");
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param GRLENTIDADFED
	 * @return
	 */
	private Map<String,Object> getMap(List<GRLENTIDADFED> GRLENTIDADFED){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", GRLENTIDADFED.size());
		modelMap.put("data", GRLENTIDADFED);
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
	public void setsistemaService(GRLENTIDADFED_Service service) {
		this.Service = service;
	}

}
