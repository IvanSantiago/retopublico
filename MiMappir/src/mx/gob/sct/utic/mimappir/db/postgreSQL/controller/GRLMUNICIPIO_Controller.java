package mx.gob.sct.utic.mimappir.db.postgreSQL.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.GRLMUNICIPIO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.services.GRLMUNICIPIO_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * GRLMUNICIPIO Controller - Spring
 * 
 * @author Ivan Santiago Méndez
 * 
 */

@Controller
@RequestMapping("/web")
public class GRLMUNICIPIO_Controller  {

	private GRLMUNICIPIO_Service Service;
	
	@RequestMapping(value="/GRLMUNICIPIO_view.action")
	public @ResponseBody Map<String,? extends Object> externosView() throws Exception {

		try{

			List<GRLMUNICIPIO> GRLMUNICIPIO = Service.getList();

			return getMap(GRLMUNICIPIO);

		} catch (Exception e) {

			return getModelMapError("Error retrieving GRLMUNICIPIO from database. "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/GRLMUNICIPIO_create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {

		try{

			List<GRLMUNICIPIO> GRLMUNICIPIO = Service.create(data);

			return getMap(GRLMUNICIPIO);

		} catch (Exception e) {

			return getModelMapError("Error trying to create GRLMUNICIPIO.");
		}
	}
	
	@RequestMapping(value="/GRLMUNICIPIO_update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<GRLMUNICIPIO> GRLMUNICIPIO = Service.update(data);

			return getMap(GRLMUNICIPIO);

		} catch (Exception e) {

			return getModelMapError("Error trying to update GRLMUNICIPIO.");
		}
	}
	
	@RequestMapping(value="/GRLMUNICIPIO_delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {

			return getModelMapError("Error trying to delete GRLMUNICIPIO.");
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param GRLMUNICIPIO
	 * @return
	 */
	private Map<String,Object> getMap(List<GRLMUNICIPIO> GRLMUNICIPIO){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", GRLMUNICIPIO.size());
		modelMap.put("data", GRLMUNICIPIO);
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
	public void setsistemaService(GRLMUNICIPIO_Service service) {
		this.Service = service;
	}

}
