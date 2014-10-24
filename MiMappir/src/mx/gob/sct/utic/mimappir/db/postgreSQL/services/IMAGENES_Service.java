package mx.gob.sct.utic.mimappir.db.postgreSQL.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.dao.IMAGENES_DAO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMIMAGENES;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IMAGENES_Service {
private IMAGENES_DAO DAO;
	
	/**
	 * Get all IMAGENES
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public List<MMIMAGENES> getList(){
		return DAO.getList();
	}
		
	/**
	 * Create new IMAGENES/IMAGENES
	 * @param data - json data from request
	 * @return created IMAGENES
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<MMIMAGENES> create(Object data){
		
        List<MMIMAGENES> returnIMAGENES = new ArrayList<MMIMAGENES>();
		
		List<MMIMAGENES> newIMAGENES = getIMAGENESFromRequest(data);
		
		for (MMIMAGENES x : newIMAGENES){
			Integer key = DAO.getKey(x);
			if(key==null){
				key=new Integer(1);
			}else{
				key=key+1;
			}			
			x.getICVEIMAGEN_PK().setICVEIMAGEN(key);
			x.setICVEIMAGEN_ID(x.getICVEIMAGEN_PK().getICVEIMAGEN()+"-");
			returnIMAGENES.add(DAO.save(x));
		}
		return returnIMAGENES;
	}
	
	
	
	/**
	 * Create new IMAGENES
	 * @param data - IMAGENES Object
	 * @return created IMAGENES Object
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public MMIMAGENES create(MMIMAGENES IMAGENES){				
		return DAO.save(IMAGENES);
	}
	
	
	/**
	 * Update IMAGENES/IMAGENES
	 * @param data - json data from request
	 * @return updated IMAGENES
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<MMIMAGENES> update(Object data){
		
		List<MMIMAGENES> returnIMAGENES = new ArrayList<MMIMAGENES>();
		
		List<MMIMAGENES> updatedIMAGENES = getIMAGENESFromRequest(data);
		
		for (MMIMAGENES x : updatedIMAGENES){
			returnIMAGENES.add(DAO.save(x));
		}
		
		return returnIMAGENES;
	}
	
	/**
	 * Spring use - DI
	 * @param IMAGENES_DAO
	 */
	@Autowired
	public void setDAO(IMAGENES_DAO dao) {
		this.DAO = dao;
	}
	
	/**
	 * Get list of IMAGENES from request.
	 * @param data - json data from request 
	 * @return list of IMAGENES
	 */
	public List<MMIMAGENES> getIMAGENESFromRequest(Object data){

		List<MMIMAGENES> list;

		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){

			list = getListIMAGENESFromJSON(data);

		} else { //it is only one object - cast to object/bean

			MMIMAGENES IMAGENES = getIMAGENESFromJSON(data);

			list = new ArrayList<MMIMAGENES>();
			list.add(IMAGENES);
		}

		return list;
	}
	
	/**
	 * Transform json data format into IMAGENES object
	 * @param data - json data from request
	 * @return 
	 */
	private MMIMAGENES getIMAGENESFromJSON(Object data){
		JSONObject jsonObject = JSONObject.fromObject(data);
		MMIMAGENES newIMAGENES = (MMIMAGENES) JSONObject.toBean(jsonObject, MMIMAGENES.class);
		return newIMAGENES;
	}

	/**
	 * Transform json data format into list of IMAGENES objects
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<MMIMAGENES> getListIMAGENESFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<MMIMAGENES> newIMAGENES = (List<MMIMAGENES>) JSONArray.toCollection(jsonArray,MMIMAGENES.class);
		return newIMAGENES;
	}

	/**
	 * Delete IMAGENES by IMAGENES_ID
	 * @param data - json data from request
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public void delete(Object data){
		List<String> deleteIMAGENES = getIdFromRequest(data);
		
		for (String id : deleteIMAGENES){			
			MMIMAGENES objToDelete = new MMIMAGENES();
			objToDelete.setICVEIMAGEN_ID(id);
			DAO.delete(objToDelete.getICVEIMAGEN_PK());		
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

			String IMAGENES = getIdFromJSON(data);

			list = new ArrayList<String>();
			list.add(IMAGENES);
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
		List<String> IMAGENES_ID = (List<String>) JSONArray.toCollection(jsonArray,String.class);
		return IMAGENES_ID;
	}
	
	/**
	 * Tranform array of Strings in json data format into
	 * list of Strings
	 * @param data - json data from request
	 * @return
	 */
	public String getIdFromJSON(Object data){
		String Object = (String)data;
		String IMAGENES_ID = Object.replaceAll("\"","");
		return IMAGENES_ID;
	}
	
}
