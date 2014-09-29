package mx.gob.sct.utic.mimappir.db.postgreSQL.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.QOSLLAMADAS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import mx.gob.sct.utic.mimappir.db.postgreSQL.services.QOSLLAMADAS_Service;

/**
 * QOSLLAMADAS Controller - Spring
 * 
 * @author Ivan Santiago Méndez
 * 
 */
@Controller
@RequestMapping("/web")
public class QOSLLAMADAS_Controller  {

	private QOSLLAMADAS_Service QOSLLAMADAS_Service;
	
	@RequestMapping(value="/QOSLLAMADAS/view.action")
	public @ResponseBody Map<String,? extends Object> view() throws Exception {

		try{

			List<QOSLLAMADAS> contacts = QOSLLAMADAS_Service.getList();

			return getMap(contacts);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error retrieving QOSLLAMADAS from database. "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/QOSLLAMADAS/viewSpecific.action")
	public @ResponseBody Map<String,? extends Object> viewSpecific(
			@RequestParam(value = "dlatitud", required = true) Double latitud
			,@RequestParam(value = "dlongitud", required = true) Double longitud
			,@RequestParam(value = "dradio", required = true) Double radio) throws Exception {

		try{

			List<QOSLLAMADAS> contacts = QOSLLAMADAS_Service.getListSenalInto(latitud, longitud, radio);

			return getMap(contacts);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error retrieving spefici QOSLLAMADAS from database. "+e.getMessage());
		}
	}	
	
	@RequestMapping(value="/QOSLLAMADAS/create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {

		try{

			List<QOSLLAMADAS> contacts = QOSLLAMADAS_Service.create(data);

			return getMap(contacts);

		} catch (Exception e) {

			return getModelMapError("Error trying to create QOSLLAMADAS.");
		}
	}
	
	@RequestMapping(value="/QOSLLAMADAS/REGISTRO/SENAL.action")
	public @ResponseBody Map<String,? extends Object> create(
			@RequestParam(value = "dlatitud", required = true) Double latitud
			,@RequestParam(value = "dlongitud", required = true) Double longitud
			,@RequestParam(value = "d_altitud", required = true) Double altitud
			,@RequestParam(value = "illamadasexitosas", required = true) Integer illamadasexitosas
			,@RequestParam(value = "illamadasfallidas", required = true) Integer illamadasfallidas
			,@RequestParam(value = "illamadasdesconocidas", required = true) Integer illamadasdesconocidas
			) throws Exception {

		try{
			QOSLLAMADAS newObject = null;
			ArrayList<QOSLLAMADAS> toSaveRegistry = new ArrayList<QOSLLAMADAS>();
			newObject = new QOSLLAMADAS(longitud,latitud,altitud);
			newObject.setTsregistro(new Timestamp(System.currentTimeMillis()));
			newObject.setIllamadasexitosas(illamadasexitosas);
			newObject.setIllamadasfallidas(illamadasfallidas);
			newObject.setIllamadasdesconocidas(illamadasdesconocidas);
			toSaveRegistry.add(newObject);
			List<QOSLLAMADAS> registry = QOSLLAMADAS_Service.create(toSaveRegistry);
			return getMap(registry);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to create QOSLLAMADAS.");
		}
	}
	
	
	@RequestMapping(value="/QOSLLAMADAS/update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<QOSLLAMADAS> contacts = QOSLLAMADAS_Service.update(data);

			return getMap(contacts);

		} catch (Exception e) {

			return getModelMapError("Error trying to update QOSLLAMADAS.");
		}
	}
	
	@RequestMapping(value="/QOSLLAMADAS/delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			QOSLLAMADAS_Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {

			return getModelMapError("Error trying to delete QOSLLAMADAS.");
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param contacts
	 * @return
	 */
	private Map<String,Object> getMap(List<QOSLLAMADAS> contacts){
		
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
	

	public QOSLLAMADAS_Service getQOSLLAMADAS_Service() {
		return QOSLLAMADAS_Service;
	}
	
	@Autowired
	public void setQOSLLAMADAS_Service(QOSLLAMADAS_Service QOSLLAMADAS_Service) {
		this.QOSLLAMADAS_Service = QOSLLAMADAS_Service;
	}

}
