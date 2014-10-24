package mx.gob.sct.utic.mimappir.db.postgreSQL.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMUSUARIOS;
import mx.gob.sct.utic.mimappir.db.postgreSQL.services.USUARIOS_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/web")
public class USUARIOS_Controller {

	private USUARIOS_Service Service;

	@RequestMapping(value="/USUARIOS_view.action")
	public @ResponseBody Map<String,? extends Object> view() throws Exception {

		try{

			List<MMUSUARIOS> USUARIOS = Service.getList();

			return getMap(USUARIOS);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error retrieving USUARIOS from database. "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/USUARIOS_create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {
		try{
			
			List<MMUSUARIOS> USUARIOS = Service.create(data);
			
			return getMap(USUARIOS);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to create USUARIOS."+ e.getMessage());
		}
	}
		
	
	@RequestMapping(value="/USUARIOS_update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<MMUSUARIOS> USUARIOS = Service.update(data);

			return getMap(USUARIOS);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to update USUARIOS."+ e.getMessage());
		}
	}
	
	@RequestMapping(value="/USUARIOS_delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to delete USUARIOS."+ e.getMessage());
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param USUARIOS
	 * @return
	 */
	private Map<String,Object> getMap(List<MMUSUARIOS> USUARIOS){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", USUARIOS.size());
		modelMap.put("data", USUARIOS);
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
	public void setUSUARIOSService(USUARIOS_Service service) {
		this.Service = service;
	}
	
}
