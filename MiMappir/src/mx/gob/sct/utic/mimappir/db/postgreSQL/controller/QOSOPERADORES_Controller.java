package mx.gob.sct.utic.mimappir.db.postgreSQL.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.QOSOPERADORES;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import mx.gob.sct.utic.mimappir.db.postgreSQL.services.QOSOPERADORES_Service;

/**
 * QOSOPERADORES Controller - Spring
 * 
 * @author Ivan Santiago Méndez
 * 
 */
@Controller
@RequestMapping("/web")
public class QOSOPERADORES_Controller  {

	private QOSOPERADORES_Service QOSOPERADORES_Service;
	
	@RequestMapping(value="/QOSOPERADORES/view.action")
	public @ResponseBody Map<String,? extends Object> view() throws Exception {

		try{

			List<QOSOPERADORES> contacts = QOSOPERADORES_Service.getList();

			return getMap(contacts);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error retrieving QOSOPERADORES from database. "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/QOSOPERADORES/viewSpecific.action")
	public @ResponseBody Map<String,? extends Object> viewSpecific(
			@RequestParam(value = "dlatitud", required = true) Double latitud
			,@RequestParam(value = "dlongitud", required = true) Double longitud
			,@RequestParam(value = "dradio", required = true) Double radio) throws Exception {

		try{

			List<QOSOPERADORES> contacts = QOSOPERADORES_Service.getListSenalInto(latitud, longitud, radio);

			return getMap(contacts);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error retrieving spefici QOSOPERADORES from database. "+e.getMessage());
		}
	}	
	
	@RequestMapping(value="/QOSOPERADORES/create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {

		try{

			List<QOSOPERADORES> contacts = QOSOPERADORES_Service.create(data);

			return getMap(contacts);

		} catch (Exception e) {

			return getModelMapError("Error trying to create QOSOPERADORES.");
		}
	}
	
	@RequestMapping(value="/QOSOPERADORES/REGISTRO/SENAL.action")
	public @ResponseBody Map<String,? extends Object> create(
			@RequestParam(value = "dlatitud", required = true) Double latitud
			,@RequestParam(value = "dlongitud", required = true) Double longitud
			,@RequestParam(value = "d_altitud", required = true) Double altitud
			,@RequestParam(value = "iOPERADORESexitosas", required = true) Integer iOPERADORESexitosas
			,@RequestParam(value = "iOPERADORESfallidas", required = true) Integer iOPERADORESfallidas
			,@RequestParam(value = "iOPERADORESdesconocidas", required = true) Integer iOPERADORESdesconocidas
			) throws Exception {

		try{
			QOSOPERADORES newObject = new QOSOPERADORES();
			ArrayList<QOSOPERADORES> toSaveRegistry = new ArrayList<QOSOPERADORES>();
			toSaveRegistry.add(newObject);
			List<QOSOPERADORES> registry = QOSOPERADORES_Service.create(toSaveRegistry);
			return getMap(registry);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to create QOSOPERADORES.");
		}
	}
	
	
	@RequestMapping(value="/QOSOPERADORES/update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<QOSOPERADORES> contacts = QOSOPERADORES_Service.update(data);

			return getMap(contacts);

		} catch (Exception e) {

			return getModelMapError("Error trying to update QOSOPERADORES.");
		}
	}
	
	@RequestMapping(value="/QOSOPERADORES/delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			QOSOPERADORES_Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {

			return getModelMapError("Error trying to delete QOSOPERADORES.");
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param contacts
	 * @return
	 */
	private Map<String,Object> getMap(List<QOSOPERADORES> contacts){
		
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
	

	public QOSOPERADORES_Service getQOSOPERADORES_Service() {
		return QOSOPERADORES_Service;
	}
	
	@Autowired
	public void setQOSOPERADORES_Service(QOSOPERADORES_Service QOSOPERADORES_Service) {
		this.QOSOPERADORES_Service = QOSOPERADORES_Service;
	}

}
