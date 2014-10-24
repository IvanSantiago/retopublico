package mx.gob.sct.utic.mimappir.db.postgreSQL.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMPREMIACIONES;
import mx.gob.sct.utic.mimappir.db.postgreSQL.services.PREMIACIONES_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/web")
public class PREMIACIONES_Controller {

	private PREMIACIONES_Service Service;

	@RequestMapping(value="/PREMIACIONES_view.action")
	public @ResponseBody Map<String,? extends Object> view() throws Exception {

		try{

			List<MMPREMIACIONES> PREMIACIONES = Service.getList();
			
			return getMap(PREMIACIONES);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error retrieving PREMIACIONES from database. "+e.getMessage());
		}
	}
	
	
	@RequestMapping(value="/PREMIACIONES_create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {
		try{
			
			List<MMPREMIACIONES> PREMIACIONES = Service.create(data);
			
			return getMap(PREMIACIONES);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to create PREMIACIONES."+ e.getMessage());
		}
	}
		
	
	@RequestMapping(value="/PREMIACIONES_update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<MMPREMIACIONES> PREMIACIONES = Service.update(data);

			return getMap(PREMIACIONES);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to update PREMIACIONES."+ e.getMessage());
		}
	}
	
	@RequestMapping(value="/PREMIACIONES_delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to delete PREMIACIONES."+ e.getMessage());
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param PREMIACIONES
	 * @return
	 */
	private Map<String,Object> getMap(List<MMPREMIACIONES> PREMIACIONES){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", PREMIACIONES.size());
		modelMap.put("data", PREMIACIONES);
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
	public void setPREMIACIONESService(PREMIACIONES_Service service) {
		this.Service = service;
	}
	
	
}
