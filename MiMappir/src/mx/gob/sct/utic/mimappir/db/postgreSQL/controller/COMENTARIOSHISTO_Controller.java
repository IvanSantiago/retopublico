package mx.gob.sct.utic.mimappir.db.postgreSQL.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMCOMENTARIOSHISTO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.services.COMENTARIOSHISTO_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/web")
public class COMENTARIOSHISTO_Controller {


	private COMENTARIOSHISTO_Service Service;

	@RequestMapping(value="/COMENTARIOSHISTO_view.action")
	public @ResponseBody Map<String,? extends Object> view() throws Exception {

		try{

			List<MMCOMENTARIOSHISTO> COMENTARIOSHISTO = Service.getList();
			
			return getMap(COMENTARIOSHISTO);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error retrieving PARTICIPANTES from database. "+e.getMessage());
		}
	}
	
	
	@RequestMapping(value="/COMENTARIOSHISTO_create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {
		try{
			
			List<MMCOMENTARIOSHISTO> COMENTARIOSHISTO = Service.create(data);
			
			return getMap(COMENTARIOSHISTO);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to create COMENTARIOSHISTO."+ e.getMessage());
		}
	}
		
	
	@RequestMapping(value="/COMENTARIOSHISTO_update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<MMCOMENTARIOSHISTO> COMENTARIOSHISTO = Service.update(data);

			return getMap(COMENTARIOSHISTO);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to update COMENTARIOSHISTO."+ e.getMessage());
		}
	}
	
	@RequestMapping(value="/COMENTARIOSHISTO_delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to delete COMENTARIOSHISTO."+ e.getMessage());
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param COMENTARIOSHISTO
	 * @return
	 */
	private Map<String,Object> getMap(List<MMCOMENTARIOSHISTO> COMENTARIOSHISTO){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", COMENTARIOSHISTO.size());
		modelMap.put("data", COMENTARIOSHISTO);
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
	public void setCOMENTARIOSHISTOService(COMENTARIOSHISTO_Service service) {
		this.Service = service;
	}
	
	
}
