package mx.gob.sct.utic.mimappir.admseg.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.admseg.postgreSQL.dao.SEGGRUPO_DAO;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGGRUPO;
import mx.gob.sct.utic.mimappir.util.Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SEGGRUPO Service
 * 
 * @author Ivan Santiago
 * 
 */
@Service
public class SEGGRUPO_Service {
	
	private SEGGRUPO_DAO DAO;
	private Util util;

	/**
	 * Get all SEGGRUPO
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_DB2",readOnly=true)
	public List<SEGGRUPO> getList(){
		return DAO.getList();
	}
	/**
	 * Get specific SEGGRUPO
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_DB2",readOnly=true)
	public SEGGRUPO getSEGGRUPO(int ICVEOBRA){
		List<SEGGRUPO> processes = DAO.getSEGGRUPO(ICVEOBRA);
		SEGGRUPO findedProcess = null;
		// Search user based on the parameters
		for (SEGGRUPO proceso : processes) {
			findedProcess= proceso;
		}
		return findedProcess;
	}
	
	/**
	 * Create new SEGGRUPO/SEGGRUPO
	 * @param data - json data from request
	 * @return created SEGGRUPO
	 */
	@Transactional(value="transactionManager_mimappir_DB2")
	public List<SEGGRUPO> create(Object data){
		
        List<SEGGRUPO> returnSEGGRUPO = new ArrayList<SEGGRUPO>();
		
		List<SEGGRUPO> newSEGGRUPO = getSEGGRUPOFromRequest(data);
		
/*		for (SEGGRUPO x : newSEGGRUPO){
			Integer key = DAO.getKey(x);
			if(key==null){
				key=new Integer(1);
			}else{
				key=key+1;
			}			
			x.getICVESEGGRUPO_PK().setICVEOBRA(key);
			x.setICVESEGGRUPO_ID(x.getICVESEGGRUPO_PK().getICVEOBRA()+"-");
			returnSEGGRUPO.add(DAO.save(x));
		}*/
		return returnSEGGRUPO;
	}
	
	
	
	/**
	 * Create new SEGGRUPO
	 * @param data - SEGGRUPO Object
	 * @return created SEGGRUPO Object
	 */
	@Transactional(value="transactionManager_mimappir_DB2")
	public SEGGRUPO create(SEGGRUPO SEGGRUPO){				
		return DAO.save(SEGGRUPO);
	}
	
	
	/**
	 * Update SEGGRUPO/SEGGRUPO
	 * @param data - json data from request
	 * @return updated SEGGRUPO
	 */
	@Transactional(value="transactionManager_mimappir_DB2")
	public List<SEGGRUPO> update(Object data){
		
		List<SEGGRUPO> returnSEGGRUPO = new ArrayList<SEGGRUPO>();
		
		List<SEGGRUPO> updatedSEGGRUPO = getSEGGRUPOFromRequest(data);
		
		for (SEGGRUPO x : updatedSEGGRUPO){
			returnSEGGRUPO.add(DAO.save(x));
		}
		
		return returnSEGGRUPO;
	}
	
	/**
	 * Delete SEGGRUPO by SEGGRUPO_ID
	 * @param data - json data from request
	 */
	@Transactional(value="transactionManager_mimappir_DB2")
	public void delete(Object data){
		List<String> deleteSEGGRUPO = getIdFromRequest(data);
/*		
		for (String id : deleteSEGGRUPO){
			SEGGRUPO objToDelete = new SEGGRUPO();
			objToDelete.setICVESEGGRUPO_ID(id);
			DAO.delete(objToDelete.getICVESEGGRUPO_PK());
		}*/
	}
	

	/**
	 * Spring use - DI
	 * @param SEGGRUPO_DAO
	 */
	@Autowired
	public void setDAO(SEGGRUPO_DAO dao) {
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
	 * Get list of SEGGRUPO from request.
	 * @param data - json data from request 
	 * @return list of SEGGRUPO
	 */
	public List<SEGGRUPO> getSEGGRUPOFromRequest(Object data){

		List<SEGGRUPO> list;

		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){

			list = getListSEGGRUPOFromJSON(data);

		} else { //it is only one object - cast to object/bean

			SEGGRUPO SEGGRUPO = getSEGGRUPOFromJSON(data);

			list = new ArrayList<SEGGRUPO>();
			list.add(SEGGRUPO);
		}

		return list;
	}

	/**
	 * Transform json data format into SEGGRUPO object
	 * @param data - json data from request
	 * @return 
	 */
	private SEGGRUPO getSEGGRUPOFromJSON(Object data){
		JSONObject jsonObject = JSONObject.fromObject(data);
		SEGGRUPO newSEGGRUPO = (SEGGRUPO) JSONObject.toBean(jsonObject, SEGGRUPO.class);
		return newSEGGRUPO;
	}

	/**
	 * Transform json data format into list of SEGGRUPO objects
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<SEGGRUPO> getListSEGGRUPOFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<SEGGRUPO> newSEGGRUPO = (List<SEGGRUPO>) JSONArray.toCollection(jsonArray,SEGGRUPO.class);
		return newSEGGRUPO;
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

			String SEGGRUPO = getIdFromJSON(data);

			list = new ArrayList<String>();
			list.add(SEGGRUPO);
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
		List<String> SEGGRUPO_ID = (List<String>) JSONArray.toCollection(jsonArray,String.class);
		return SEGGRUPO_ID;
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
		String SEGGRUPO_ID = Object.replaceAll("\"","");
		return SEGGRUPO_ID;
	}
}
