package mx.gob.sct.utic.mimappir.admseg.postgreSQL.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.admseg.postgreSQL.dao.SEGSISTEMA_DAO;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGSISTEMA;
import mx.gob.sct.utic.mimappir.util.Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SEGSISTEMA Service
 * 
 * @author Ivan Santiago
 * 
 */
@Service
public class SEGSISTEMA_Service {
	
	private SEGSISTEMA_DAO DAO;
	private Util util;

	/**
	 * Get all SEGSISTEMA
	 * @return
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS")
	public List<SEGSISTEMA> getList(){
		return DAO.getList();
	}
	
	/**
	 * Create new SEGSISTEMA/SEGSISTEMA
	 * @param data - json data from request
	 * @return created SEGSISTEMA
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS")
	public List<SEGSISTEMA> create(Object data){
		
        List<SEGSISTEMA> returnSEGSISTEMA = new ArrayList<SEGSISTEMA>();
		
		List<SEGSISTEMA> newSEGSISTEMA = getSEGSISTEMAFromRequest(data);
		
/*		for (SEGSISTEMA x : newSEGSISTEMA){
			Integer key = DAO.getKey(x);
			if(key==null){
				key=new Integer(1);
			}else{
				key=key+1;
			}			
			x.getICVESEGSISTEMA_PK().setICVEOBRA(key);
			x.setICVESEGSISTEMA_ID(x.getICVESEGSISTEMA_PK().getICVEOBRA()+"-");
			returnSEGSISTEMA.add(DAO.save(x));
		}*/
		return returnSEGSISTEMA;
	}
	
	
	
	/**
	 * Create new SEGSISTEMA
	 * @param data - SEGSISTEMA Object
	 * @return created SEGSISTEMA Object
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS")
	public SEGSISTEMA create(SEGSISTEMA SEGSISTEMA){				
		return DAO.save(SEGSISTEMA);
	}
	
	
	/**
	 * Update SEGSISTEMA/SEGSISTEMA
	 * @param data - json data from request
	 * @return updated SEGSISTEMA
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS")
	public List<SEGSISTEMA> update(Object data){
		
		List<SEGSISTEMA> returnSEGSISTEMA = new ArrayList<SEGSISTEMA>();
		
		List<SEGSISTEMA> updatedSEGSISTEMA = getSEGSISTEMAFromRequest(data);
		
		for (SEGSISTEMA x : updatedSEGSISTEMA){
			returnSEGSISTEMA.add(DAO.save(x));
		}
		
		return returnSEGSISTEMA;
	}
	
	/**
	 * Delete SEGSISTEMA by SEGSISTEMA_ID
	 * @param data - json data from request
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS")
	public void delete(Object data){
		List<String> deleteSEGSISTEMA = getIdFromRequest(data);
/*		
		for (String id : deleteSEGSISTEMA){
			SEGSISTEMA objToDelete = new SEGSISTEMA();
			objToDelete.setICVESEGSISTEMA_ID(id);
			DAO.delete(objToDelete.getICVESEGSISTEMA_PK());
		}*/
	}
	

	/**
	 * Spring use - DI
	 * @param SEGSISTEMA_DAO
	 */
	@Autowired
	public void setDAO(SEGSISTEMA_DAO dao) {
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
	 * Get list of SEGSISTEMA from request.
	 * @param data - json data from request 
	 * @return list of SEGSISTEMA
	 */
	public List<SEGSISTEMA> getSEGSISTEMAFromRequest(Object data){

		List<SEGSISTEMA> list;

		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){

			list = getListSEGSISTEMAFromJSON(data);

		} else { //it is only one object - cast to object/bean

			SEGSISTEMA SEGSISTEMA = getSEGSISTEMAFromJSON(data);

			list = new ArrayList<SEGSISTEMA>();
			list.add(SEGSISTEMA);
		}

		return list;
	}

	/**
	 * Transform json data format into SEGSISTEMA object
	 * @param data - json data from request
	 * @return 
	 */
	private SEGSISTEMA getSEGSISTEMAFromJSON(Object data){
		JSONObject jsonObject = JSONObject.fromObject(data);
		SEGSISTEMA newSEGSISTEMA = (SEGSISTEMA) JSONObject.toBean(jsonObject, SEGSISTEMA.class);
		return newSEGSISTEMA;
	}

	/**
	 * Transform json data format into list of SEGSISTEMA objects
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<SEGSISTEMA> getListSEGSISTEMAFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<SEGSISTEMA> newSEGSISTEMA = (List<SEGSISTEMA>) JSONArray.toCollection(jsonArray,SEGSISTEMA.class);
		return newSEGSISTEMA;
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

			String SEGSISTEMA = getIdFromJSON(data);

			list = new ArrayList<String>();
			list.add(SEGSISTEMA);
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
		List<String> SEGSISTEMA_ID = (List<String>) JSONArray.toCollection(jsonArray,String.class);
		return SEGSISTEMA_ID;
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
		String SEGSISTEMA_ID = Object.replaceAll("\"","");
		return SEGSISTEMA_ID;
	}
}
