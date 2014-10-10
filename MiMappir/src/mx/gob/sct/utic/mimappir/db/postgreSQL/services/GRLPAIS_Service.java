package mx.gob.sct.utic.mimappir.db.postgreSQL.services;

import java.util.ArrayList;
import java.util.List;
import mx.gob.sct.utic.mimappir.db.postgreSQL.dao.GRLPAIS_DAO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.GRLPAIS;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.GRLPAIS;
import mx.gob.sct.utic.mimappir.util.Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * GRLPAIS Service
 * 
 * @author Ivan Santiago
 * 
 */
@Service
public class GRLPAIS_Service {
	
	private GRLPAIS_DAO DAO;

	/**
	 * Get all GRLPAIS
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public List<GRLPAIS> getList(){
		return DAO.getList();
	}
	
	/**
	 * Create new GRLPAIS/GRLPAIS
	 * @param data - json data from request
	 * @return created GRLPAIS
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<GRLPAIS> create(Object data){
		
        List<GRLPAIS> returnGRLPAIS = new ArrayList<GRLPAIS>();
		
		List<GRLPAIS> newGRLPAIS = getGRLPAISFromRequest(data);
		
		for (GRLPAIS x : newGRLPAIS){
			Integer key = DAO.getKey(x);
			if(key==null){
				key=new Integer(1);
			}else{
				key=key+1;
			}			
			x.getICVEGRLPAIS_PK().setICVEPAIS(key);
			x.setICVEGRLPAIS_ID(x.getICVEGRLPAIS_PK().getICVEPAIS()+"-");
			returnGRLPAIS.add(DAO.save(x));
		}
		return returnGRLPAIS;
	}
	
	
	
	/**
	 * Create new GRLPAIS
	 * @param data - GRLPAIS Object
	 * @return created GRLPAIS Object
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public GRLPAIS create(GRLPAIS GRLPAIS){				
		return DAO.save(GRLPAIS);
	}
	
	
	/**
	 * Update GRLPAIS/GRLPAIS
	 * @param data - json data from request
	 * @return updated GRLPAIS
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<GRLPAIS> update(Object data){
		
		List<GRLPAIS> returnGRLPAIS = new ArrayList<GRLPAIS>();
		
		List<GRLPAIS> updatedGRLPAIS = getGRLPAISFromRequest(data);
		
		for (GRLPAIS x : updatedGRLPAIS){
			returnGRLPAIS.add(DAO.save(x));
		}
		
		return returnGRLPAIS;
	}
	
	/**
	 * Spring use - DI
	 * @param GRLPAIS_DAO
	 */
	@Autowired
	public void setDAO(GRLPAIS_DAO dao) {
		this.DAO = dao;
	}
	
	/**
	 * Get list of GRLPAIS from request.
	 * @param data - json data from request 
	 * @return list of GRLPAIS
	 */
	public List<GRLPAIS> getGRLPAISFromRequest(Object data){

		List<GRLPAIS> list;

		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){

			list = getListGRLPAISFromJSON(data);

		} else { //it is only one object - cast to object/bean

			GRLPAIS GRLPAIS = getGRLPAISFromJSON(data);

			list = new ArrayList<GRLPAIS>();
			list.add(GRLPAIS);
		}

		return list;
	}
	
	/**
	 * Transform json data format into GRLPAIS object
	 * @param data - json data from request
	 * @return 
	 */
	private GRLPAIS getGRLPAISFromJSON(Object data){
		JSONObject jsonObject = JSONObject.fromObject(data);
		GRLPAIS newGRLPAIS = (GRLPAIS) JSONObject.toBean(jsonObject, GRLPAIS.class);
		return newGRLPAIS;
	}

	/**
	 * Transform json data format into list of GRLPAIS objects
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<GRLPAIS> getListGRLPAISFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<GRLPAIS> newGRLPAIS = (List<GRLPAIS>) JSONArray.toCollection(jsonArray,GRLPAIS.class);
		return newGRLPAIS;
	}

	/**
	 * Delete GRLPAIS by GRLPAIS_ID
	 * @param data - json data from request
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public void delete(Object data){
		List<String> deleteGRLPAIS = getIdFromRequest(data);
		
		for (String id : deleteGRLPAIS){
			GRLPAIS objToDelete = new GRLPAIS();
			objToDelete.setICVEGRLPAIS_ID(id);
			DAO.delete(objToDelete.getICVEGRLPAIS_PK());
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

			String GRLPAIS = getIdFromJSON(data);

			list = new ArrayList<String>();
			list.add(GRLPAIS);
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
		List<String> GRLPAIS_ID = (List<String>) JSONArray.toCollection(jsonArray,String.class);
		return GRLPAIS_ID;
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
		String GRLPAIS_ID = Object.replaceAll("\"","");
		return GRLPAIS_ID;
	}
	
}
