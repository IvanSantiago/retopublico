package mx.gob.sct.utic.mimappir.admseg.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.admseg.postgreSQL.dao.SEGPERMISOXGPO_DAO;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGPERMISOXGPO;
import mx.gob.sct.utic.mimappir.util.Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SEGPERMISOXGPO Service
 * 
 * @author Ivan Santiago
 * 
 */
@Service
public class SEGPERMISOXGPO_Service {
	
	private SEGPERMISOXGPO_DAO DAO;
	private Util util;

	/**
	 * Get all SEGPERMISOXGPO
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_DB2",readOnly=true)
	public List<SEGPERMISOXGPO> getList(){
		return DAO.getList();
	}
	/**
	 * Get specific SEGPERMISOXGPO
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_DB2",readOnly=true)
	public SEGPERMISOXGPO getSEGPERMISOXGPO(int ICVEOBRA){
		List<SEGPERMISOXGPO> processes = DAO.getSEGPERMISOXGPO(ICVEOBRA);
		SEGPERMISOXGPO findedProcess = null;
		// Search user based on the parameters
		for (SEGPERMISOXGPO proceso : processes) {
			findedProcess= proceso;
		}
		return findedProcess;
	}
	
	/**
	 * Create new SEGPERMISOXGPO/SEGPERMISOXGPO
	 * @param data - json data from request
	 * @return created SEGPERMISOXGPO
	 */
	@Transactional(value="transactionManager_mimappir_DB2")
	public List<SEGPERMISOXGPO> create(Object data){
		
        List<SEGPERMISOXGPO> returnSEGPERMISOXGPO = new ArrayList<SEGPERMISOXGPO>();
		
		List<SEGPERMISOXGPO> newSEGPERMISOXGPO = getSEGPERMISOXGPOFromRequest(data);
		
/*		for (SEGPERMISOXGPO x : newSEGPERMISOXGPO){
			Integer key = DAO.getKey(x);
			if(key==null){
				key=new Integer(1);
			}else{
				key=key+1;
			}			
			x.getICVESEGPERMISOXGPO_PK().setICVEOBRA(key);
			x.setICVESEGPERMISOXGPO_ID(x.getICVESEGPERMISOXGPO_PK().getICVEOBRA()+"-");
			returnSEGPERMISOXGPO.add(DAO.save(x));
		}*/
		return returnSEGPERMISOXGPO;
	}
	
	
	
	/**
	 * Create new SEGPERMISOXGPO
	 * @param data - SEGPERMISOXGPO Object
	 * @return created SEGPERMISOXGPO Object
	 */
	@Transactional(value="transactionManager_mimappir_DB2")
	public SEGPERMISOXGPO create(SEGPERMISOXGPO SEGPERMISOXGPO){				
		return DAO.save(SEGPERMISOXGPO);
	}
	
	
	/**
	 * Update SEGPERMISOXGPO/SEGPERMISOXGPO
	 * @param data - json data from request
	 * @return updated SEGPERMISOXGPO
	 */
	@Transactional(value="transactionManager_mimappir_DB2")
	public List<SEGPERMISOXGPO> update(Object data){
		
		List<SEGPERMISOXGPO> returnSEGPERMISOXGPO = new ArrayList<SEGPERMISOXGPO>();
		
		List<SEGPERMISOXGPO> updatedSEGPERMISOXGPO = getSEGPERMISOXGPOFromRequest(data);
		
		for (SEGPERMISOXGPO x : updatedSEGPERMISOXGPO){
			returnSEGPERMISOXGPO.add(DAO.save(x));
		}
		
		return returnSEGPERMISOXGPO;
	}
	
	/**
	 * Delete SEGPERMISOXGPO by SEGPERMISOXGPO_ID
	 * @param data - json data from request
	 */
	@Transactional(value="transactionManager_mimappir_DB2")
	public void delete(Object data){
		List<String> deleteSEGPERMISOXGPO = getIdFromRequest(data);
/*		
		for (String id : deleteSEGPERMISOXGPO){
			SEGPERMISOXGPO objToDelete = new SEGPERMISOXGPO();
			objToDelete.setICVESEGPERMISOXGPO_ID(id);
			DAO.delete(objToDelete.getICVESEGPERMISOXGPO_PK());
		}*/
	}
	

	/**
	 * Spring use - DI
	 * @param SEGPERMISOXGPO_DAO
	 */
	@Autowired
	public void setDAO(SEGPERMISOXGPO_DAO dao) {
		this.DAO = dao;
	}

	/**
	 * Spring use - DI
	 * @param util
	 */
	@Autowired
	public void setUtil(Util util) {
		this.util = util;
	}
	
	/**
	 * Get list of SEGPERMISOXGPO from request.
	 * @param data - json data from request 
	 * @return list of SEGPERMISOXGPO
	 */
	public List<SEGPERMISOXGPO> getSEGPERMISOXGPOFromRequest(Object data){

		List<SEGPERMISOXGPO> list;

		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){

			list = getListSEGPERMISOXGPOFromJSON(data);

		} else { //it is only one object - cast to object/bean

			SEGPERMISOXGPO SEGPERMISOXGPO = getSEGPERMISOXGPOFromJSON(data);

			list = new ArrayList<SEGPERMISOXGPO>();
			list.add(SEGPERMISOXGPO);
		}

		return list;
	}

	/**
	 * Transform json data format into SEGPERMISOXGPO object
	 * @param data - json data from request
	 * @return 
	 */
	private SEGPERMISOXGPO getSEGPERMISOXGPOFromJSON(Object data){
		JSONObject jsonObject = JSONObject.fromObject(data);
		SEGPERMISOXGPO newSEGPERMISOXGPO = (SEGPERMISOXGPO) JSONObject.toBean(jsonObject, SEGPERMISOXGPO.class);
		return newSEGPERMISOXGPO;
	}

	/**
	 * Transform json data format into list of SEGPERMISOXGPO objects
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<SEGPERMISOXGPO> getListSEGPERMISOXGPOFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<SEGPERMISOXGPO> newSEGPERMISOXGPO = (List<SEGPERMISOXGPO>) JSONArray.toCollection(jsonArray,SEGPERMISOXGPO.class);
		return newSEGPERMISOXGPO;
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

			String SEGPERMISOXGPO = getIdFromJSON(data);

			list = new ArrayList<String>();
			list.add(SEGPERMISOXGPO);
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
		List<String> SEGPERMISOXGPO_ID = (List<String>) JSONArray.toCollection(jsonArray,String.class);
		return SEGPERMISOXGPO_ID;
	}
	
	/**
	 * Tranform array of Strings in json data format into
	 * list of Strings
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getIdFromJSON(Object data){
		String Object = (String)data;
		String SEGPERMISOXGPO_ID = Object.replaceAll("\"","");
		return SEGPERMISOXGPO_ID;
	}
}
