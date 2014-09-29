package mx.gob.sct.utic.mimappir.sigtic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sct.utic.mimappir.sigtic.db2.model.C_SI;
import mx.gob.sct.utic.mimappir.sigtic.services.C_SI_Service;

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
public class C_SI_Controller  {

	private C_SI_Service Service;
	
	@RequestMapping(value="/C_SI_externosView.action")
	public @ResponseBody Map<String,? extends Object> externosView() throws Exception {

		try{

			List<C_SI> contacts = Service.getListExternos();

			return getMap(contacts);

		} catch (Exception e) {

			return getModelMapError("Error retrieving Contacts from database. "+e.getMessage());
		}
	}
	@RequestMapping(value="/view.action")
	public @ResponseBody Map<String,? extends Object> view() throws Exception {

		try{

			List<C_SI> contacts = Service.getList();

			return getMap(contacts);

		} catch (Exception e) {

			return getModelMapError("Error retrieving Contacts from database. "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {

		try{

			List<C_SI> contacts = Service.create(data);

			return getMap(contacts);

		} catch (Exception e) {

			return getModelMapError("Error trying to create contact.");
		}
	}
	
	@RequestMapping(value="/update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<C_SI> contacts = Service.update(data);

			return getMap(contacts);

		} catch (Exception e) {

			return getModelMapError("Error trying to update contact.");
		}
	}
	
	@RequestMapping(value="/delete.action")
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
	private Map<String,Object> getMap(List<C_SI> contacts){
		
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
	public void setsistemaService(C_SI_Service service) {
		this.Service = service;
	}

}
