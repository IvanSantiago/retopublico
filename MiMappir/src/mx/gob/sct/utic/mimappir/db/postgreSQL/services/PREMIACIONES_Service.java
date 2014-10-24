package mx.gob.sct.utic.mimappir.db.postgreSQL.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.dao.PREMIACIONES_DAO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMPREMIACIONES;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PREMIACIONES_Service {

private PREMIACIONES_DAO DAO;
	
	/**
	 * Get all PREMIACIONES
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public List<MMPREMIACIONES> getList(){
		return DAO.getList();
	}
		
	/**
	 * Create new PREMIACIONES/PREMIACIONES
	 * @param data - json data from request
	 * @return created PREMIACIONES
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<MMPREMIACIONES> create(Object data){
		
        List<MMPREMIACIONES> returnPREMIACIONES = new ArrayList<MMPREMIACIONES>();
		
		List<MMPREMIACIONES> newPREMIACIONES = getPREMIACIONESFromRequest(data);
		
		for (MMPREMIACIONES x : newPREMIACIONES){
			Integer key = DAO.getKey(x);
			if(key==null){
				key=new Integer(1);
			}else{
				key=key+1;
			}			
			x.getICVEPREMIACION_PK().setICVEPREMIACION(key);
			x.setICVEPREMIACION_ID(x.getICVEPREMIACION_PK().getICVEPREMIACION()+"-");
			returnPREMIACIONES.add(DAO.save(x));
		}
		return returnPREMIACIONES;
	}
	
	
	
	/**
	 * Create new PREMIACIONES
	 * @param data - PREMIACIONES Object
	 * @return created PREMIACIONES Object
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public MMPREMIACIONES create(MMPREMIACIONES PREMIACIONES){				
		return DAO.save(PREMIACIONES);
	}
	
	
	/**
	 * Update PREMIACIONES/PREMIACIONES
	 * @param data - json data from request
	 * @return updated PREMIACIONES
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<MMPREMIACIONES> update(Object data){
		
		List<MMPREMIACIONES> returnPREMIACIONES = new ArrayList<MMPREMIACIONES>();
		
		List<MMPREMIACIONES> updatedPREMIACIONES = getPREMIACIONESFromRequest(data);
		
		for (MMPREMIACIONES x : updatedPREMIACIONES){
			returnPREMIACIONES.add(DAO.save(x));
		}
		
		return returnPREMIACIONES;
	}
	
	/**
	 * Spring use - DI
	 * @param PREMIACIONES_DAO
	 */
	@Autowired
	public void setDAO(PREMIACIONES_DAO dao) {
		this.DAO = dao;
	}
	
	/**
	 * Get list of PREMIACIONES from request.
	 * @param data - json data from request 
	 * @return list of PREMIACIONES
	 */
	public List<MMPREMIACIONES> getPREMIACIONESFromRequest(Object data){

		List<MMPREMIACIONES> list;

		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){

			list = getListPREMIACIONESFromJSON(data);

		} else { //it is only one object - cast to object/bean

			MMPREMIACIONES PREMIACIONES = getPREMIACIONESFromJSON(data);

			list = new ArrayList<MMPREMIACIONES>();
			list.add(PREMIACIONES);
		}

		return list;
	}
	
	/**
	 * Transform json data format into PREMIACIONES object
	 * @param data - json data from request
	 * @return 
	 */
	private MMPREMIACIONES getPREMIACIONESFromJSON(Object data){
		JSONObject jsonObject = JSONObject.fromObject(data);
		MMPREMIACIONES newPREMIACIONES = (MMPREMIACIONES) JSONObject.toBean(jsonObject, MMPREMIACIONES.class);
		return newPREMIACIONES;
	}

	/**
	 * Transform json data format into list of PREMIACIONES objects
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<MMPREMIACIONES> getListPREMIACIONESFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<MMPREMIACIONES> newPREMIACIONES = (List<MMPREMIACIONES>) JSONArray.toCollection(jsonArray,MMPREMIACIONES.class);
		return newPREMIACIONES;
	}

	/**
	 * Delete PREMIACIONES by PREMIACIONES_ID
	 * @param data - json data from request
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public void delete(Object data){
		List<String> deletePREMIACIONES = getIdFromRequest(data);
		
		for (String id : deletePREMIACIONES){			
			MMPREMIACIONES objToDelete = new MMPREMIACIONES();
			objToDelete.setICVEPREMIACION_ID(id);
			DAO.delete(objToDelete.getICVEPREMIACION_PK());		
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

			String PREMIACIONES = getIdFromJSON(data);

			list = new ArrayList<String>();
			list.add(PREMIACIONES);
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
		List<String> PREMIACION_ID = (List<String>) JSONArray.toCollection(jsonArray,String.class);
		return PREMIACION_ID;
	}
	
	/**
	 * Tranform array of Strings in json data format into
	 * list of Strings
	 * @param data - json data from request
	 * @return
	 */
	public String getIdFromJSON(Object data){
		String Object = (String)data;
		String PREMIACIONES_ID = Object.replaceAll("\"","");
		return PREMIACIONES_ID;
	}
	
	
}
