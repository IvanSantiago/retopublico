package mx.gob.sct.utic.mimappir.sigtic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sct.utic.mimappir.sigtic.db2.model.C_SI;
import mx.gob.sct.utic.mimappir.sigtic.db2.model.SOL_SI_PERF;
import mx.gob.sct.utic.mimappir.sigtic.services.C_SI_Service;
import mx.gob.sct.utic.mimappir.sigtic.services.SOL_SI_PERF_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Controller - Spring
 * 
 * @author Ivan Santiago Méndez
 * 
 */
@Controller
@RequestMapping("/web")
public class SOL_SI_PERF_Controller  {

	private SOL_SI_PERF_Service Service;
	
	@RequestMapping(value="/SOL_SI_PERF_view.action")
	public @ResponseBody Map<String,? extends Object> view() throws Exception {

		try{

			List<SOL_SI_PERF> contacts = Service.getList();

			return getMap(contacts);

		} catch (Exception e) {

			return getModelMapError("Error retrieving Contacts from database. "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/SOL_SI_PERF_viewForOneSystem.action")
	public @ResponseBody Map<String,? extends Object> viewForOneSystem(@RequestParam Integer UID_SI) throws Exception {

		try{

			List<SOL_SI_PERF> registros = Service.getPerfilesDelSistema(UID_SI);

			return getMap(registros);

		} catch (Exception e) {

			return getModelMapError("Error retrieving Contacts from database. "+e.getMessage());
		}
	}
	
	
	
	@RequestMapping(value="/SOL_SI_PERF_create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {

		try{

			List<SOL_SI_PERF> contacts = Service.create(data);

			return getMap(contacts);

		} catch (Exception e) {

			return getModelMapError("Error trying to create contact.");
		}
	}
	
	@RequestMapping(value="/SOL_SI_PERF_update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<SOL_SI_PERF> contacts = Service.update(data);

			return getMap(contacts);

		} catch (Exception e) {

			return getModelMapError("Error trying to update contact.");
		}
	}
	
	@RequestMapping(value="/SOL_SI_PERF_delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {

			return getModelMapError("Error trying to delete contact.");
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param contacts
	 * @return
	 */
	private Map<String,Object> getMap(List<SOL_SI_PERF> contacts){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", contacts.size());
		modelMap.put("data", contacts);
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
	public void setsistemaService(SOL_SI_PERF_Service service) {
		this.Service = service;
	}

}
