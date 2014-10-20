package mx.gob.sct.utic.mimappir.admseg.postgreSQL.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGUSUARIO;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.services.SEGUSUARIO_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SEGUSUARIO Controller - Spring
 * 
 * @author Ivan Santiago Méndez
 * 
 */

@Controller
@RequestMapping("/web")
public class SEGUSUARIO_Controller  {

	private SEGUSUARIO_Service Service;
	
	@RequestMapping(value="/SEGUSUARIO_view.action")
	public @ResponseBody Map<String,? extends Object> externosView() throws Exception {

		try{

			List<SEGUSUARIO> SEGUSUARIO = Service.getList();

			return getMap(SEGUSUARIO);

		} catch (Exception e) {

			return getModelMapError("Error retrieving SEGUSUARIO from database. "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/SEGUSUARIO_create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {

		try{

			List<SEGUSUARIO> SEGUSUARIO = Service.create(data);

			return getMap(SEGUSUARIO);

		} catch (Exception e) {

			return getModelMapError("Error trying to create SEGUSUARIO.");
		}
	}
	
	@RequestMapping(value="/SEGUSUARIO_update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<SEGUSUARIO> SEGUSUARIO = Service.update(data);

			return getMap(SEGUSUARIO);

		} catch (Exception e) {

			return getModelMapError("Error trying to update SEGUSUARIO.");
		}
	}
	
	@RequestMapping(value="/SEGUSUARIO_delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {

			return getModelMapError("Error trying to delete SEGUSUARIO.");
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param SEGUSUARIO
	 * @return
	 */
	private Map<String,Object> getMap(List<SEGUSUARIO> SEGUSUARIO){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", SEGUSUARIO.size());
		modelMap.put("data", SEGUSUARIO);
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
	public void setsistemaService(SEGUSUARIO_Service service) {
		this.Service = service;
	}

}
