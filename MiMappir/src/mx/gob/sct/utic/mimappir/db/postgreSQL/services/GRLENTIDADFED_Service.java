package mx.gob.sct.utic.mimappir.db.postgreSQL.services;

import java.util.ArrayList;
import java.util.List;
import mx.gob.sct.utic.mimappir.db.postgreSQL.dao.GRLENTIDADFED_DAO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.GRLENTIDADFED;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.GRLENTIDADFED;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.GRLENTIDADFED;
import mx.gob.sct.utic.mimappir.util.Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * GRLENTIDADFED Service
 * 
 * @author Ivan Santiago
 * 
 */
@Service
public class GRLENTIDADFED_Service {
	
	private GRLENTIDADFED_DAO DAO;
	private Util util;

	/**
	 * Get all GRLENTIDADFED
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public List<GRLENTIDADFED> getList(){
		return DAO.getList();
	}
	/**
	 * Get specific GRLENTIDADFED
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public GRLENTIDADFED getGRLENTIDADFED(int ICVEENTIDADFED){
		List<GRLENTIDADFED> processes = DAO.getGRLENTIDADFED(ICVEENTIDADFED);
		GRLENTIDADFED findedProcess = null;
		// Search user based on the parameters
		for (GRLENTIDADFED proceso : processes) {
			findedProcess= proceso;
		}
		return findedProcess;
	}
	
	/**
	 * Create new GRLENTIDADFED/GRLENTIDADFED
	 * @param data - json data from request
	 * @return created GRLENTIDADFED
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<GRLENTIDADFED> create(Object data){
		
        List<GRLENTIDADFED> returnGRLENTIDADFED = new ArrayList<GRLENTIDADFED>();
		
		List<GRLENTIDADFED> newGRLENTIDADFED = getGRLENTIDADFEDFromRequest(data);
		
		for (GRLENTIDADFED x : newGRLENTIDADFED){
			Integer key = DAO.getKey(x);
			if(key==null){
				key=new Integer(1);
			}else{
				key=key+1;
			}			
			x.getICVEGRLENTIDADFED_PK().setICVEENTIDADFED(key);
			x.setICVEGRLENTIDADFED_ID(x.getICVEGRLENTIDADFED_PK().getICVEPAIS()+"-"+x.getICVEGRLENTIDADFED_PK().getICVEENTIDADFED());
			returnGRLENTIDADFED.add(DAO.save(x));
		}
		return returnGRLENTIDADFED;
	}
	
	
	
	/**
	 * Create new GRLENTIDADFED
	 * @param data - GRLENTIDADFED Object
	 * @return created GRLENTIDADFED Object
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public GRLENTIDADFED create(GRLENTIDADFED GRLENTIDADFED){				
		return DAO.save(GRLENTIDADFED);
	}
	
	
	/**
	 * Update GRLENTIDADFED/GRLENTIDADFED
	 * @param data - json data from request
	 * @return updated GRLENTIDADFED
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<GRLENTIDADFED> update(Object data){
		
		List<GRLENTIDADFED> returnGRLENTIDADFED = new ArrayList<GRLENTIDADFED>();
		
		List<GRLENTIDADFED> updatedGRLENTIDADFED = getGRLENTIDADFEDFromRequest(data);
		
		for (GRLENTIDADFED x : updatedGRLENTIDADFED){
			returnGRLENTIDADFED.add(DAO.save(x));
		}
		
		return returnGRLENTIDADFED;
	}
	


	/**
	 * Spring use - DI
	 * @param GRLENTIDADFED_DAO
	 */
	@Autowired
	public void setDAO(GRLENTIDADFED_DAO dao) {
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
	 * Get list of GRLENTIDADFED from request.
	 * @param data - json data from request 
	 * @return list of GRLENTIDADFED
	 */
	public List<GRLENTIDADFED> getGRLENTIDADFEDFromRequest(Object data){

		List<GRLENTIDADFED> list;

		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){

			list = getListGRLENTIDADFEDFromJSON(data);

		} else { //it is only one object - cast to object/bean

			GRLENTIDADFED GRLENTIDADFED = getGRLENTIDADFEDFromJSON(data);

			list = new ArrayList<GRLENTIDADFED>();
			list.add(GRLENTIDADFED);
		}

		return list;
	}
	
	/**
	 * Transform json data format into GRLENTIDADFED object
	 * @param data - json data from request
	 * @return 
	 */
	private GRLENTIDADFED getGRLENTIDADFEDFromJSON(Object data){
		JSONObject jsonObject = JSONObject.fromObject(data);
		GRLENTIDADFED newGRLENTIDADFED = (GRLENTIDADFED) JSONObject.toBean(jsonObject, GRLENTIDADFED.class);
		return newGRLENTIDADFED;
	}

	/**
	 * Transform json data format into list of GRLENTIDADFED objects
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<GRLENTIDADFED> getListGRLENTIDADFEDFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<GRLENTIDADFED> newGRLENTIDADFED = (List<GRLENTIDADFED>) JSONArray.toCollection(jsonArray,GRLENTIDADFED.class);
		return newGRLENTIDADFED;
	}

	/**
	 * Delete GRLENTIDADFED by GRLENTIDADFED_ID
	 * @param data - json data from request
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public void delete(Object data){
		List<String> deleteGRLENTIDADFED = getIdFromRequest(data);
		
		for (String id : deleteGRLENTIDADFED){
			GRLENTIDADFED objToDelete = new GRLENTIDADFED();
			objToDelete.setICVEGRLENTIDADFED_ID(id);
			DAO.delete(objToDelete.getICVEGRLENTIDADFED_PK());
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

			String GRLENTIDADFED = getIdFromJSON(data);

			list = new ArrayList<String>();
			list.add(GRLENTIDADFED);
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
		List<String> GRLENTIDADFED_ID = (List<String>) JSONArray.toCollection(jsonArray,String.class);
		return GRLENTIDADFED_ID;
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
		String GRLENTIDADFED_ID = Object.replaceAll("\"","");
		return GRLENTIDADFED_ID;
	}
}
