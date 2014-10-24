package mx.gob.sct.utic.mimappir.db.postgreSQL.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMPARTICIPANTES;
import mx.gob.sct.utic.mimappir.db.postgreSQL.services.PARTICIPANTES_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/web")
public class PARTICIPANTES_Controller {

	private PARTICIPANTES_Service Service;

	@RequestMapping(value="/PARTICIPANTES_view.action")
	public @ResponseBody Map<String,? extends Object> view() throws Exception {

		try{

			List<MMPARTICIPANTES> PARTICIPANTES = Service.getList();
			
			return getMap(PARTICIPANTES);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error retrieving PARTICIPANTES from database. "+e.getMessage());
		}
	}
	
	
/*  Prueba comunicacion */
	@RequestMapping(value="/PARTICIPANTES_viewSpecificOperator.action")
	public @ResponseBody Map<String,? extends Object> viewSpecificOperator(
			@RequestParam(value = "ICVEPARTICIPANTE", required = true) int ICVEPARTICIPANTE,
			@RequestParam(value = "CCORREO", required = true) String CCORREO,
			@RequestParam(value = "CFBCUENTA ", required = true) String CFBCUENTA,
			@RequestParam(value = "CPARTICIPANTE ", required = true) String CPARTICIPANTE,
			@RequestParam(value = "DTFECHAREGISTRO", required = true) String DTFECHAREGISTRO,
			@RequestParam(value = "ICVEPARTICIPANTE_ID", required = true) String ICVEPARTICIPANTE_ID) throws Exception {
			try{
	
				List<MMPARTICIPANTES> PARTICIPANTES = Service.getList();
	
				return getMap(PARTICIPANTES);
	
			} catch (Exception e) {
				e.printStackTrace();
				return getModelMapError("Error retrieving PARTICIPANTES from database. "+e.getMessage());
			}
	}

//	Iterator<PARTICIPANTES> iterator = PARTICIPANTES.iterator();
//	while(iterator.hasNext()){
//		PARTICIPANTES participante = new PARTICIPANTES();
//		participante = iterator.next();
////		participante.setCFECHAREGISTRO(String.valueOf(participante.getDTFECHAREGISTRO()));
//		System.out.println("Iterador: ["+ 
//		participante.getICVEPARTICIPANTE_PK().getICVEPARTICIPANTE()+" - "+
//		participante.getCPARTICIPANTE()+" - "+
//		participante.getCNOMBRE()+" - "+
//		participante.getCFBCUENTA()+" - "+
//		participante.getICVEPARTICIPANTE_ID()+" - "+
//		participante.getDTFECHAREGISTRO()+	" -  Fecha en String: "+	
////		participante.getCFECHAREGISTRO()	+"]");
//	}
	
	
	@RequestMapping(value="/PARTICIPANTES_create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {
		try{
			
			List<MMPARTICIPANTES> PARTICIPANTES = Service.create(data);
			
			return getMap(PARTICIPANTES);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to create PARTICIPANTES."+ e.getMessage());
		}
	}
		
	
	@RequestMapping(value="/PARTICIPANTES_update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<MMPARTICIPANTES> PARTICIPANTES = Service.update(data);

			return getMap(PARTICIPANTES);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to update PARTICIPANTES."+ e.getMessage());
		}
	}
	
	@RequestMapping(value="/PARTICIPANTES_delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to delete PARTICIPANTES."+ e.getMessage());
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param PARTICIPANTES
	 * @return
	 */
	private Map<String,Object> getMap(List<MMPARTICIPANTES> PARTICIPANTES){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", PARTICIPANTES.size());
		modelMap.put("data", PARTICIPANTES);
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
	public void setPARTICIPANTESService(PARTICIPANTES_Service service) {
		this.Service = service;
	}
	
}
