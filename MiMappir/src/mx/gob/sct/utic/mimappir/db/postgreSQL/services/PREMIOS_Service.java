package mx.gob.sct.utic.mimappir.db.postgreSQL.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.dao.PREMIOS_DAO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMPREMIOS;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PREMIOS_Service {

private PREMIOS_DAO DAO;
	
	/**
	 * Get all PREMIOS
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public List<MMPREMIOS> getList(){
		return DAO.getList();
	}
		
	/**
	 * Create new PREMIOS/PREMIOS
	 * @param data - json data from request
	 * @return created PREMIOS
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<MMPREMIOS> create(Object data){
		
        List<MMPREMIOS> returnPREMIOS = new ArrayList<MMPREMIOS>();
		
		List<MMPREMIOS> newPREMIOS = getPREMIOSFromRequest(data);
		
		for (MMPREMIOS x : newPREMIOS){
			Integer key = DAO.getKey(x);
			if(key==null){
				key=new Integer(1);
			}else{
				key=key+1;
			}			
			x.getICVEPREMIO_PK().setICVEPREMIO(key);
			x.setICVEPREMIO_ID(x.getICVEPREMIO_PK().getICVEPREMIO()+"-");
			returnPREMIOS.add(DAO.save(x));
		}
		return returnPREMIOS;
	}
	
	
	
	/**
	 * Create new PREMIOS
	 * @param data - PREMIOS Object
	 * @return created PREMIOS Object
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public MMPREMIOS create(MMPREMIOS PREMIOS){				
		return DAO.save(PREMIOS);
	}
	
	
	/**
	 * Update PREMIOS/PREMIOS
	 * @param data - json data from request
	 * @return updated PREMIOS
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<MMPREMIOS> update(Object data){
		
		List<MMPREMIOS> returnPREMIOS = new ArrayList<MMPREMIOS>();
		
		List<MMPREMIOS> updatedPREMIOS = getPREMIOSFromRequest(data);
		
		for (MMPREMIOS x : updatedPREMIOS){
			returnPREMIOS.add(DAO.save(x));
		}
		
		return returnPREMIOS;
	}
	
	/**
	 * Spring use - DI
	 * @param PREMIOS_DAO
	 */
	@Autowired
	public void setDAO(PREMIOS_DAO dao) {
		this.DAO = dao;
	}
	
	/**
	 * Get list of PREMIOS from request.
	 * @param data - json data from request 
	 * @return list of PREMIOS
	 */
	public List<MMPREMIOS> getPREMIOSFromRequest(Object data){

		List<MMPREMIOS> list;

		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){

			list = getListPREMIOSFromJSON(data);

		} else { //it is only one object - cast to object/bean

			MMPREMIOS PREMIOS = getPREMIOSFromJSON(data);

			list = new ArrayList<MMPREMIOS>();
			list.add(PREMIOS);
		}

		return list;
	}
	
	/**
	 * Transform json data format into PREMIOS object
	 * @param data - json data from request
	 * @return 
	 */
	private MMPREMIOS getPREMIOSFromJSON(Object data){
		JSONObject jsonObject = JSONObject.fromObject(data);
		MMPREMIOS newPREMIOS = (MMPREMIOS) JSONObject.toBean(jsonObject, MMPREMIOS.class);
		return newPREMIOS;
	}

	/**
	 * Transform json data format into list of PREMIOS objects
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<MMPREMIOS> getListPREMIOSFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<MMPREMIOS> newPREMIOS = (List<MMPREMIOS>) JSONArray.toCollection(jsonArray,MMPREMIOS.class);
		return newPREMIOS;
	}

	/**
	 * Delete PREMIOS by PREMIOS_ID
	 * @param data - json data from request
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public void delete(Object data){
		List<String> deletePREMIOS = getIdFromRequest(data);
		
		for (String id : deletePREMIOS){			
			MMPREMIOS objToDelete = new MMPREMIOS();
			objToDelete.setICVEPREMIO_ID(id);
			DAO.delete(objToDelete.getICVEPREMIO_PK());		
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

			String PREMIOS = getIdFromJSON(data);

			list = new ArrayList<String>();
			list.add(PREMIOS);
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
		List<String> PREMIOS_ID = (List<String>) JSONArray.toCollection(jsonArray,String.class);
		return PREMIOS_ID;
	}
	
	/**
	 * Tranform array of Strings in json data format into
	 * list of Strings
	 * @param data - json data from request
	 * @return
	 */
	public String getIdFromJSON(Object data){
		String Object = (String)data;
		String PREMIOS_ID = Object.replaceAll("\"","");
		return PREMIOS_ID;
	}
	
}
