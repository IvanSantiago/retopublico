package mx.gob.sct.utic.mimappir.db.postgreSQL.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.GRLPAIS;
import mx.gob.sct.utic.mimappir.db.postgreSQL.services.GRLPAIS_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * GRLPAIS Controller - Spring
 * 
 * @author Ivan Santiago Méndez
 * 
 */

@Controller
@RequestMapping("/web")
public class GRLPAIS_Controller  {

	private GRLPAIS_Service Service;
	
	@RequestMapping(value="/GRLPAIS_view.action")
	public @ResponseBody Map<String,? extends Object> view() throws Exception {

		try{

			List<GRLPAIS> GRLPAIS = Service.getList();

			return getMap(GRLPAIS);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error retrieving GRLPAIS from database. "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/GRLPAIS_create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {

		try{

			List<GRLPAIS> GRLPAIS = Service.create(data);

			return getMap(GRLPAIS);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to create GRLPAIS."+ e.getMessage());
		}
	}
	
	@RequestMapping(value="/GRLPAIS_update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<GRLPAIS> GRLPAIS = Service.update(data);

			return getMap(GRLPAIS);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to update GRLPAIS."+ e.getMessage());
		}
	}
	
	@RequestMapping(value="/GRLPAIS_delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to delete GRLPAIS."+ e.getMessage());
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param GRLPAIS
	 * @return
	 */
	private Map<String,Object> getMap(List<GRLPAIS> GRLPAIS){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", GRLPAIS.size());
		modelMap.put("data", GRLPAIS);
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
	public void setGRLPAISService(GRLPAIS_Service service) {
		this.Service = service;
	}

}
