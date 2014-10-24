package mx.gob.sct.utic.mimappir.db.postgreSQL.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMIMAGENES;
import mx.gob.sct.utic.mimappir.db.postgreSQL.services.IMAGENES_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/web")
public class IMAGENES_Controller {

	private IMAGENES_Service Service;

	@RequestMapping(value="/IMAGENES_view.action")
	public @ResponseBody Map<String,? extends Object> view() throws Exception {

		try{

			List<MMIMAGENES> IMAGENES = Service.getList();

			return getMap(IMAGENES);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error retrieving IMAGENES from database. "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/IMAGENES_create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {
		try{
			
			List<MMIMAGENES> IMAGENES = Service.create(data);
			
			return getMap(IMAGENES);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to create IMAGENES."+ e.getMessage());
		}
	}
		
	
	@RequestMapping(value="/IMAGENES_update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<MMIMAGENES> IMAGENES = Service.update(data);

			return getMap(IMAGENES);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to update IMAGENES."+ e.getMessage());
		}
	}
	
	@RequestMapping(value="/IMAGENES_delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to delete IMAGENES."+ e.getMessage());
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param IMAGENES
	 * @return
	 */
	private Map<String,Object> getMap(List<MMIMAGENES> IMAGENES){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", IMAGENES.size());
		modelMap.put("data", IMAGENES);
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
	public void setIMAGENESService(IMAGENES_Service service) {
		this.Service = service;
	}

	
}
