package mx.gob.sct.utic.mimappir.db.postgreSQL.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMPREMIOS;
import mx.gob.sct.utic.mimappir.db.postgreSQL.services.PREMIOS_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/web")
public class PREMIOS_Controller {

	private PREMIOS_Service Service;

	@RequestMapping(value="/PREMIOS_view.action")
	public @ResponseBody Map<String,? extends Object> view() throws Exception {

		try{

			List<MMPREMIOS> PREMIOS = Service.getList();

			return getMap(PREMIOS);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error retrieving PREMIOS from database. "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/PREMIOS_create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {
		try{
			
			List<MMPREMIOS> PREMIOS = Service.create(data);
			
			return getMap(PREMIOS);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to create PREMIOS."+ e.getMessage());
		}
	}
		
	
	@RequestMapping(value="/PREMIOS_update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<MMPREMIOS> PREMIOS = Service.update(data);

			return getMap(PREMIOS);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to update PREMIOS."+ e.getMessage());
		}
	}
	
	@RequestMapping(value="/PREMIOS_delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to delete PREMIOS."+ e.getMessage());
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param PREMIOS
	 * @return
	 */
	private Map<String,Object> getMap(List<MMPREMIOS> PREMIOS){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", PREMIOS.size());
		modelMap.put("data", PREMIOS);
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
	public void setPREMIOSService(PREMIOS_Service service) {
		this.Service = service;
	}
	
}
