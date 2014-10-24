package mx.gob.sct.utic.mimappir.db.postgreSQL.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.dao.COMENTARIOSGEO_DAO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMCOMENTARIOSGEO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;

@Service
public class COMENTARIOSGEO_Service {
private COMENTARIOSGEO_DAO DAO;
	
	/**
	 * Get all COMENTARIOSGEO
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public List<MMCOMENTARIOSGEO> getList(){
		return DAO.getList();
	}
		
	/**
	 * Create new COMENTARIOSGEO/COMENTARIOSGEO
	 * @param data - json data from request
	 * @return created COMENTARIOSGEO
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<MMCOMENTARIOSGEO> create(Object data){
		
        List<MMCOMENTARIOSGEO> returnCOMENTARIOSGEO = new ArrayList<MMCOMENTARIOSGEO>();
		List<MMCOMENTARIOSGEO> newCOMENTARIOSGEO = getCOMENTARIOSGEOFromRequest(data);
		
		for (MMCOMENTARIOSGEO x : newCOMENTARIOSGEO){
			//Colocacion de la Geoubicacion
			Coordinate coord = new Coordinate(x.getDlongitud(), x.getDlatitud());
			Point geoubicacion = new GeometryFactory(new PrecisionModel(),4326).createPoint(coord);
			x.setGeoubicacion(geoubicacion);
			
			Integer key = DAO.getKey(x);
			if(key==null){
				key=new Integer(1);
			}else{
				key=key+1;
			}
			x.setIcvecomentario(key);

			returnCOMENTARIOSGEO.add(DAO.save(x));
		}
		return returnCOMENTARIOSGEO;
	}
	
	
	
	/**
	 * Create new COMENTARIOSGEO
	 * @param data - COMENTARIOSGEO Object
	 * @return created COMENTARIOSGEO Object
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public MMCOMENTARIOSGEO create(MMCOMENTARIOSGEO COMENTARIOSGEO){				
		return DAO.save(COMENTARIOSGEO);
	}
	
	
	/**
	 * Update COMENTARIOSGEO/COMENTARIOSGEO
	 * @param data - json data from request
	 * @return updated COMENTARIOSGEO
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<MMCOMENTARIOSGEO> update(Object data){
		
		List<MMCOMENTARIOSGEO> returnCOMENTARIOSGEO = new ArrayList<MMCOMENTARIOSGEO>();
		
		List<MMCOMENTARIOSGEO> updatedCOMENTARIOSGEO = getCOMENTARIOSGEOFromRequest(data);
		
		for (MMCOMENTARIOSGEO x : updatedCOMENTARIOSGEO){
			//Colocacion de la Geoubicacion
			Coordinate coord = new Coordinate(x.getDlongitud(), x.getDlatitud());
			Point geoubicacion = new GeometryFactory(new PrecisionModel(),4326).createPoint(coord);
			x.setGeoubicacion(geoubicacion);
			
			returnCOMENTARIOSGEO.add(DAO.save(x));
		}
		
		return returnCOMENTARIOSGEO;
	}
	
	/**
	 * Spring use - DI
	 * @param COMENTARIOSGEO_DAO
	 */
	@Autowired
	public void setDAO(COMENTARIOSGEO_DAO dao) {
		this.DAO = dao;
	}
	
	/**
	 * Get list of COMENTARIOSGEO from request.
	 * @param data - json data from request 
	 * @return list of COMENTARIOSGEO
	 */
	public List<MMCOMENTARIOSGEO> getCOMENTARIOSGEOFromRequest(Object data){

		List<MMCOMENTARIOSGEO> list;

		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){

			list = getListCOMENTARIOSGEOFromJSON(data);

		} else { //it is only one object - cast to object/bean

			MMCOMENTARIOSGEO COMENTARIOSGEO = getCOMENTARIOSGEOFromJSON(data);

			list = new ArrayList<MMCOMENTARIOSGEO>();
			list.add(COMENTARIOSGEO);
		}

		return list;
	}
	
	/**
	 * Transform json data format into COMENTARIOSGEO object
	 * @param data - json data from request
	 * @return 
	 */
	private MMCOMENTARIOSGEO getCOMENTARIOSGEOFromJSON(Object data){
		JSONObject jsonObject = JSONObject.fromObject(data);
		MMCOMENTARIOSGEO newCOMENTARIOSGEO = (MMCOMENTARIOSGEO) JSONObject.toBean(jsonObject, MMCOMENTARIOSGEO.class);
		return newCOMENTARIOSGEO;
	}

	/**
	 * Transform json data format into list of COMENTARIOSGEO objects
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<MMCOMENTARIOSGEO> getListCOMENTARIOSGEOFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<MMCOMENTARIOSGEO> newCOMENTARIOSGEO = (List<MMCOMENTARIOSGEO>) JSONArray.toCollection(jsonArray,MMCOMENTARIOSGEO.class);
		return newCOMENTARIOSGEO;
	}

	/**
	 * Delete COMENTARIOSGEO by COMENTARIOSGEO_ID
	 * @param data - json data from request
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public void delete(Object data){
		List<String> deleteCOMENTARIOSGEO = getIdFromRequest(data);
		
		for (String id : deleteCOMENTARIOSGEO){			
			MMCOMENTARIOSGEO objToDelete = new MMCOMENTARIOSGEO();
			objToDelete.setIcvecomentario(Integer.valueOf(id));
			DAO.delete(objToDelete.getIcvecomentario());		
		}
	}
	
	/**
	 * Get list of String from request.
	 * @param data - json data from request 
	 * @return list of String
	 */
	public List<String> getIdFromRequest(Object data){

		List<String> list;

		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){

			list = getListIdFromJSON(data);

		} else { //it is only one object - cast to object/bean

			String COMENTARIOSGEO = getIdFromJSON(data);

			list = new ArrayList<String>();
			list.add(COMENTARIOSGEO);
		}

		return list;
	}
	
	/**
	 * Tranform array of Strings in json data format into
	 * list of Strings
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getListIdFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<String> COMENTARIOSGEO_ID = (List<String>) JSONArray.toCollection(jsonArray,String.class);
		return COMENTARIOSGEO_ID;
	}
	
	/**
	 * Tranform array of Strings in json data format into
	 * list of Strings
	 * @param data - json data from request
	 * @return
	 */
	public String getIdFromJSON(Object data){
		String Object = (String)data;
		String COMENTARIOSGEO_ID = Object.replaceAll("\"","");
		return COMENTARIOSGEO_ID;
	}
	
}
