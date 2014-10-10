package mx.gob.sct.utic.mimappir.admseg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGPROGRAMA;
import mx.gob.sct.utic.mimappir.admseg.services.SEGPROGRAMA_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SEGPROGRAMA Controller - Spring
 * 
 * @author Ivan Santiago Méndez
 * 
 */

@Controller
@RequestMapping("/web")
public class SEGPROGRAMA_Controller  {

	private SEGPROGRAMA_Service Service;
	
	@RequestMapping(value="/SEGPROGRAMA_view.action")
	public @ResponseBody Map<String,? extends Object> externosView() throws Exception {

		try{

			List<SEGPROGRAMA> SEGPROGRAMA = Service.getList();

			return getMap(SEGPROGRAMA);

		} catch (Exception e) {

			return getModelMapError("Error retrieving SEGPROGRAMA from database. "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/SEGPROGRAMA_create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {

		try{

			List<SEGPROGRAMA> SEGPROGRAMA = Service.create(data);

			return getMap(SEGPROGRAMA);

		} catch (Exception e) {

			return getModelMapError("Error trying to create SEGPROGRAMA.");
		}
	}
	
	@RequestMapping(value="/SEGPROGRAMA_update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<SEGPROGRAMA> SEGPROGRAMA = Service.update(data);

			return getMap(SEGPROGRAMA);

		} catch (Exception e) {

			return getModelMapError("Error trying to update SEGPROGRAMA.");
		}
	}
	
	@RequestMapping(value="/SEGPROGRAMA_delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {

			return getModelMapError("Error trying to delete SEGPROGRAMA.");
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param SEGPROGRAMA
	 * @return
	 */
	private Map<String,Object> getMap(List<SEGPROGRAMA> SEGPROGRAMA){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", SEGPROGRAMA.size());
		modelMap.put("data", SEGPROGRAMA);
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
	public void setsistemaService(SEGPROGRAMA_Service service) {
		this.Service = service;
	}

}
