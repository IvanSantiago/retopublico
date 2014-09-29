package mx.gob.sct.utic.mimappir.db.postgreSQL.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.QOSCSENALGEO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;

import mx.gob.sct.utic.mimappir.db.postgreSQL.services.QOSCSENALGEO_Service;

/**
 * QOSCSENALGEO Controller - Spring
 * 
 * @author Ivan Santiago Méndez
 * 
 */
@Controller
@RequestMapping("/web")
public class QOSCSENALGEO_Controller  {

	private QOSCSENALGEO_Service QOSCSENALGEO_Service;
	
	@RequestMapping(value="/QOSCSENALGEO/view.action")
	public @ResponseBody Map<String,? extends Object> view() throws Exception {

		try{

			List<QOSCSENALGEO> contacts = QOSCSENALGEO_Service.getList();

			return getMap(contacts);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error retrieving QOSCSENALGEO from database. "+e.getMessage());
		}
	}

	@RequestMapping(value="/QOSCSENALGEO/viewSpecificOperator.action")
	public @ResponseBody Map<String,? extends Object> viewSpecificOperator(
			@RequestParam(value = "dlatitud", required = true) Double latitud
			,@RequestParam(value = "dlongitud", required = true) Double longitud
			,@RequestParam(value = "dradio", required = true) Double radio
			,@RequestParam(value = "icveoperador", required = true) int iCveOperador) throws Exception {

		try{

			List<QOSCSENALGEO> contacts = QOSCSENALGEO_Service.getListSenalInfoOperator(latitud, longitud, radio, iCveOperador);

			return getMap(contacts);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error retrieving spefici QOSCSENALGEO from database. "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/QOSCSENALGEO/viewSpecific.action")
	public @ResponseBody Map<String,? extends Object> viewSpecific(
			@RequestParam(value = "dlatitud", required = true) Double latitud
			,@RequestParam(value = "dlongitud", required = true) Double longitud
			,@RequestParam(value = "dradio", required = true) Double radio) throws Exception {

		try{

			List<QOSCSENALGEO> contacts = QOSCSENALGEO_Service.getListSenalInto(latitud, longitud, radio);

			return getMap(contacts);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error retrieving spefici QOSCSENALGEO from database. "+e.getMessage());
		}
	}	
	
	@RequestMapping(value="/QOSCSENALGEO/create.action")
	public @ResponseBody Map<String,? extends Object> create(@RequestParam Object data) throws Exception {

		try{

			List<QOSCSENALGEO> contacts = QOSCSENALGEO_Service.create(data);

			return getMap(contacts);

		} catch (Exception e) {

			return getModelMapError("Error trying to create QOSCSENALGEO.");
		}
	}
	
	@RequestMapping(value="/QOSCSENALGEO/REGISTRO/SENAL.action")
	public @ResponseBody Map<String,? extends Object> create(
			@RequestParam(value = "dintensidad", required = true) Double intensidad
			,@RequestParam(value = "danchobanda", required = true) Double anchobanda
			,@RequestParam(value = "dlatitud", required = true) Double latitud
			,@RequestParam(value = "dlongitud", required = true) Double longitud
			,@RequestParam(value = "d_altitud", required = true) Double altitud
			,@RequestParam(value = "dtipoconexiond", required = true) Integer tipoconex
			,@RequestParam(value = "ioperador", required = true) Integer ioperador
			,@RequestParam(value = "idcell", required = true) Integer IdCell
			,@RequestParam(value = "latitudecell", required = true) Integer LatitudeCell
			,@RequestParam(value = "longitudcell", required = true) Integer LongitudeCell
			,@RequestParam(value = "cellphonemodel", required = true) String CellPhoneModel
			,@RequestParam(value = "cellphonemanufacturer", required = true) String CellPhoneManufacturer
			,@RequestParam(value = "tsregistro", required = false) Long tsregistro
			) throws Exception {

		try{
			QOSCSENALGEO newObject = null;
			ArrayList<QOSCSENALGEO> toSaveRegistry = new ArrayList<QOSCSENALGEO>();
			newObject = new QOSCSENALGEO(longitud,latitud,altitud);
			newObject.setDintensidad(intensidad);
			newObject.setDanchobanda(anchobanda);
			newObject.setTsregistro(new java.sql.Timestamp(tsregistro));
			newObject.setiTipoConex(tipoconex);
			newObject.setiOperador(ioperador);
			newObject.setIcveCelula(IdCell);
			newObject.setDlatitudCelula(LatitudeCell);
			newObject.setDlongitudCelula(LongitudeCell);
			newObject.setcCellPhoneModel(CellPhoneModel);
			newObject.setcCellPhoneManufacturer(CellPhoneManufacturer);
			
			Coordinate coord = new Coordinate(LongitudeCell, LatitudeCell);
			newObject.setGeomUbicCelula(new GeometryFactory().createPoint(coord));
			
			toSaveRegistry.add(newObject);
			List<QOSCSENALGEO> registry = QOSCSENALGEO_Service.create(toSaveRegistry);
			return getMap(registry);

		} catch (Exception e) {
			e.printStackTrace();
			return getModelMapError("Error trying to create QOSCSENALGEO.");
		}
	}
	
	
	@RequestMapping(value="/QOSCSENALGEO/update.action")
	public @ResponseBody Map<String,? extends Object> update(@RequestParam Object data) throws Exception {
		try{

			List<QOSCSENALGEO> contacts = QOSCSENALGEO_Service.update(data);

			return getMap(contacts);

		} catch (Exception e) {

			return getModelMapError("Error trying to update QOSCSENALGEO.");
		}
	}
	
	@RequestMapping(value="/QOSCSENALGEO/delete.action")
	public @ResponseBody Map<String,? extends Object> delete(@RequestParam Object data) throws Exception {
		
		try{

			QOSCSENALGEO_Service.delete(data);

			Map<String,Object> modelMap = new HashMap<String,Object>(3);
			modelMap.put("success", true);

			return modelMap;

		} catch (Exception e) {

			return getModelMapError("Error trying to delete QOSCSENALGEO.");
		}
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param contacts
	 * @return
	 */
	private Map<String,Object> getMap(List<QOSCSENALGEO> contacts){
		
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
	

	public QOSCSENALGEO_Service getQOSCSENALGEO_Service() {
		return QOSCSENALGEO_Service;
	}
	
	@Autowired
	public void setQOSCSENALGEO_Service(QOSCSENALGEO_Service QOSCSENALGEO_Service) {
		this.QOSCSENALGEO_Service = QOSCSENALGEO_Service;
	}

}
