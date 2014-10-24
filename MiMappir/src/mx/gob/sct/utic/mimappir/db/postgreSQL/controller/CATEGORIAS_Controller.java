package mx.gob.sct.utic.mimappir.db.postgreSQL.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMCATEGORIAS;
import mx.gob.sct.utic.mimappir.db.postgreSQL.services.CATEGORIAS_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/web")
public class CATEGORIAS_Controller {
	
	private CATEGORIAS_Service Service;

	@RequestMapping(value="/CATEGORIAS_view.action")
	public @ResponseBody Map<String,? extends Object> view() throws Exception {

		try{

			List<MMCATEGORIAS> CATEGORIAS = Service.getList();

			return getMap(CATEGORIAS);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error retrieving CATEGORIAS from database. "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/CATEGORIAS_create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {
		try{
			
			List<MMCATEGORIAS> CATEGORIAS = Service.create(data);
			
			return getMap(CATEGORIAS);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to create CATEGORIAS."+ e.getMessage());
		}
	}
		
	
	@RequestMapping(value="/CATEGORIAS_update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<MMCATEGORIAS> CATEGORIAS = Service.update(data);

			return getMap(CATEGORIAS);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to update CATEGORIAS."+ e.getMessage());
		}
	}
	
	@RequestMapping(value="/CATEGORIAS_delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to delete CATEGORIAS."+ e.getMessage());
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param CATEGORIAS
	 * @return
	 */
	private Map<String,Object> getMap(List<MMCATEGORIAS> CATEGORIAS){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", CATEGORIAS.size());
		modelMap.put("data", CATEGORIAS);
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
	public void setCATEGORIASService(CATEGORIAS_Service service) {
		this.Service = service;
	}

}
