package mx.gob.sct.utic.mimappir.db.postgreSQL.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMSEGUIMIENTOSGEO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.QOSCSENALGEO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.services.SEGUIMIENTOSGEO_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;

@Controller
@RequestMapping("/web")
public class SEGUIMIENTOSGEO_Controller {
	private SEGUIMIENTOSGEO_Service Service;

	@RequestMapping(value="/SEGUIMIENTOSGEO_view.action")
	public @ResponseBody Map<String,? extends Object> view() throws Exception {

		try{

			List<MMSEGUIMIENTOSGEO> SEGUIMIENTOSGEO = Service.getList();

			return getMap(SEGUIMIENTOSGEO);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error retrieving SEGUIMIENTOSGEO from database. "+e.getMessage());
		}
	}
	
	
	@RequestMapping(value="/SEGUIMIENTOSGEO_create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {
		try{
			
			List<MMSEGUIMIENTOSGEO> SEGUIMIENTOSGEO = Service.create(data);
			
			return getMap(SEGUIMIENTOSGEO);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to create SEGUIMIENTOSGEO."+ e.getMessage());
		}
	}
		
	
	@RequestMapping(value="/SEGUIMIENTOSGEO_update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<MMSEGUIMIENTOSGEO> SEGUIMIENTOSGEO = Service.update(data);

			return getMap(SEGUIMIENTOSGEO);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to update SEGUIMIENTOSGEO."+ e.getMessage());
		}
	}
	
	@RequestMapping(value="/SEGUIMIENTOSGEO_delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to delete SEGUIMIENTOSGEO."+ e.getMessage());
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param SEGUIMIENTOSGEO
	 * @return
	 */
	private Map<String,Object> getMap(List<MMSEGUIMIENTOSGEO> SEGUIMIENTOSGEO){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", SEGUIMIENTOSGEO.size());
		modelMap.put("data", SEGUIMIENTOSGEO);
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
	public void setSEGUIMIENTOSGEOService(SEGUIMIENTOSGEO_Service service) {
		this.Service = service;
	}

}
