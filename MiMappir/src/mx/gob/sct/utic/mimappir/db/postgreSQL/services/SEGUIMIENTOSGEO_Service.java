package mx.gob.sct.utic.mimappir.db.postgreSQL.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.dao.SEGUIMIENTOSGEO_DAO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMSEGUIMIENTOSGEO;
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
public class SEGUIMIENTOSGEO_Service {
private SEGUIMIENTOSGEO_DAO DAO;
	
	/**
	 * Get all SEGUIMIENTOSGEO
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public List<MMSEGUIMIENTOSGEO> getList(){
		return DAO.getList();
	}
		
	/**
	 * Create new SEGUIMIENTOSGEO/SEGUIMIENTOSGEO
	 * @param data - json data from request
	 * @return created SEGUIMIENTOSGEO
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<MMSEGUIMIENTOSGEO> create(Object data){
		
        List<MMSEGUIMIENTOSGEO> returnSEGUIMIENTOSGEO = new ArrayList<MMSEGUIMIENTOSGEO>();	
		List<MMSEGUIMIENTOSGEO> newSEGUIMIENTOSGEO = getSEGUIMIENTOSGEOFromRequest(data);
		
		for (MMSEGUIMIENTOSGEO x : newSEGUIMIENTOSGEO){
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
			x.setIcvesenal(key);
			
			returnSEGUIMIENTOSGEO.add(DAO.save(x));
		}
		return returnSEGUIMIENTOSGEO;
	}
	
	
	
	/**
	 * Create new SEGUIMIENTOSGEO
	 * @param data - SEGUIMIENTOSGEO Object
	 * @return created SEGUIMIENTOSGEO Object
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public MMSEGUIMIENTOSGEO create(MMSEGUIMIENTOSGEO SEGUIMIENTOSGEO){				
		return DAO.save(SEGUIMIENTOSGEO);
	}
	
	
	/**
	 * Update SEGUIMIENTOSGEO/SEGUIMIENTOSGEO
	 * @param data - json data from request
	 * @return updated SEGUIMIENTOSGEO
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<MMSEGUIMIENTOSGEO> update(Object data){
		
		List<MMSEGUIMIENTOSGEO> returnSEGUIMIENTOSGEO = new ArrayList<MMSEGUIMIENTOSGEO>();
		
		List<MMSEGUIMIENTOSGEO> updatedSEGUIMIENTOSGEO = getSEGUIMIENTOSGEOFromRequest(data);
		
		for (MMSEGUIMIENTOSGEO x : updatedSEGUIMIENTOSGEO){
			//Colocacion de la Geoubicacion
			Coordinate coord = new Coordinate(x.getDlongitud(), x.getDlatitud());
			Point geoubicacion = new GeometryFactory(new PrecisionModel(),4326).createPoint(coord);
			x.setGeoubicacion(geoubicacion);
			
			returnSEGUIMIENTOSGEO.add(DAO.save(x));
		}
		
		return returnSEGUIMIENTOSGEO;
	}
	
	/**
	 * Spring use - DI
	 * @param SEGUIMIENTOSGEO_DAO
	 */
	@Autowired
	public void setDAO(SEGUIMIENTOSGEO_DAO dao) {
		this.DAO = dao;
	}
	
	/**
	 * Get list of SEGUIMIENTOSGEO from request.
	 * @param data - json data from request 
	 * @return list of SEGUIMIENTOSGEO
	 */
	public List<MMSEGUIMIENTOSGEO> getSEGUIMIENTOSGEOFromRequest(Object data){

		List<MMSEGUIMIENTOSGEO> list;

		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){

			list = getListSEGUIMIENTOSGEOFromJSON(data);

		} else { //it is only one object - cast to object/bean

			MMSEGUIMIENTOSGEO SEGUIMIENTOSGEO = getSEGUIMIENTOSGEOFromJSON(data);

			list = new ArrayList<MMSEGUIMIENTOSGEO>();
			list.add(SEGUIMIENTOSGEO);
		}

		return list;
	}
	
	/**
	 * Transform json data format into SEGUIMIENTOSGEO object
	 * @param data - json data from request
	 * @return 
	 */
	private MMSEGUIMIENTOSGEO getSEGUIMIENTOSGEOFromJSON(Object data){
		JSONObject jsonObject = JSONObject.fromObject(data);
		MMSEGUIMIENTOSGEO newSEGUIMIENTOSGEO = (MMSEGUIMIENTOSGEO) JSONObject.toBean(jsonObject, MMSEGUIMIENTOSGEO.class);
		return newSEGUIMIENTOSGEO;
	}

	/**
	 * Transform json data format into list of SEGUIMIENTOSGEO objects
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<MMSEGUIMIENTOSGEO> getListSEGUIMIENTOSGEOFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<MMSEGUIMIENTOSGEO> newSEGUIMIENTOSGEO = (List<MMSEGUIMIENTOSGEO>) JSONArray.toCollection(jsonArray,MMSEGUIMIENTOSGEO.class);
		return newSEGUIMIENTOSGEO;
	}

	/**
	 * Delete SEGUIMIENTOSGEO by SEGUIMIENTOSGEO_ID
	 * @param data - json data from request
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public void delete(Object data){
		List<String> deleteSEGUIMIENTOSGEO = getIdFromRequest(data);
		
		for (String id : deleteSEGUIMIENTOSGEO){			
			MMSEGUIMIENTOSGEO objToDelete = new MMSEGUIMIENTOSGEO();
			objToDelete.setIcvesenal(Integer.valueOf(id));
			DAO.delete(objToDelete.getIcvesenal());	
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

			String SEGUIMIENTOSGEO = getIdFromJSON(data);

			list = new ArrayList<String>();
			list.add(SEGUIMIENTOSGEO);
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
		List<String> SEGUIMIENTOSGEO_ID = (List<String>) JSONArray.toCollection(jsonArray,String.class);
		return SEGUIMIENTOSGEO_ID;
	}
	
	/**
	 * Tranform array of Strings in json data format into
	 * list of Strings
	 * @param data - json data from request
	 * @return
	 */
	public String getIdFromJSON(Object data){
		String Object = (String)data;
		String SEGUIMIENTOSGEO_ID = Object.replaceAll("\"","");
		return SEGUIMIENTOSGEO_ID;
	}
	
}
