package mx.gob.sct.utic.mimappir.db.postgreSQL.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMCOMENTARIOSGEO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.services.COMENTARIOSGEO_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/web")
public class COMENTARIOSGEO_Controller {
	private COMENTARIOSGEO_Service Service;

	@RequestMapping(value="/COMENTARIOSGEO_view.action")
	public @ResponseBody Map<String,? extends Object> view() throws Exception {

		try{

			List<MMCOMENTARIOSGEO> COMENTARIOSGEO = Service.getList();

			return getMap(COMENTARIOSGEO);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error retrieving COMENTARIOSGEO from database. "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/COMENTARIOSGEO_create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {
		try{
			
			List<MMCOMENTARIOSGEO> COMENTARIOSGEO = Service.create(data);
			
			return getMap(COMENTARIOSGEO);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to create COMENTARIOSGEO."+ e.getMessage());
		}
	}
		
	
	@RequestMapping(value="/COMENTARIOSGEO_update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<MMCOMENTARIOSGEO> COMENTARIOSGEO = Service.update(data);

			return getMap(COMENTARIOSGEO);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to update COMENTARIOSGEO."+ e.getMessage());
		}
	}
	
	@RequestMapping(value="/COMENTARIOSGEO_delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to delete COMENTARIOSGEO."+ e.getMessage());
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param COMENTARIOSGEO
	 * @return
	 */
	private Map<String,Object> getMap(List<MMCOMENTARIOSGEO> COMENTARIOSGEO){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", COMENTARIOSGEO.size());
		modelMap.put("data", COMENTARIOSGEO);
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
	public void setCOMENTARIOSGEOService(COMENTARIOSGEO_Service service) {
		this.Service = service;
	}

}
