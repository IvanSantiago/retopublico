package mx.gob.sct.utic.mimappir.db.postgreSQL.services;

import java.util.ArrayList;
import java.util.List;
import mx.gob.sct.utic.mimappir.db.postgreSQL.dao.GRLMUNICIPIO_DAO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.GRLMUNICIPIO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.GRLMUNICIPIO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.GRLMUNICIPIO;
import mx.gob.sct.utic.mimappir.util.Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * GRLMUNICIPIO Service
 * 
 * @author Ivan Santiago
 * 
 */
@Service
public class GRLMUNICIPIO_Service {
	
	private GRLMUNICIPIO_DAO DAO;
	private Util util;

	/**
	 * Get all GRLMUNICIPIO
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public List<GRLMUNICIPIO> getList(){
		return DAO.getList();
	}
	/**
	 * Get specific GRLMUNICIPIO
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public GRLMUNICIPIO getGRLMUNICIPIO(int ICVEOBRA){
		List<GRLMUNICIPIO> processes = DAO.getGRLMUNICIPIO(ICVEOBRA);
		GRLMUNICIPIO findedProcess = null;
		// Search user based on the parameters
		for (GRLMUNICIPIO proceso : processes) {
			findedProcess= proceso;
		}
		return findedProcess;
	}
	
	/**
	 * Create new GRLMUNICIPIO/GRLMUNICIPIO
	 * @param data - json data from request
	 * @return created GRLMUNICIPIO
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<GRLMUNICIPIO> create(Object data){
		
        List<GRLMUNICIPIO> returnGRLMUNICIPIO = new ArrayList<GRLMUNICIPIO>();
		
		List<GRLMUNICIPIO> newGRLMUNICIPIO = getGRLMUNICIPIOFromRequest(data);
		
		for (GRLMUNICIPIO x : newGRLMUNICIPIO){
			Integer key = DAO.getKey(x);
			if(key==null){
				key=new Integer(1);
			}else{
				key=key+1;
			}			
			x.getICVEGRLMUNICIPIO_PK().setICVEMUNICIPIO(key);
			x.setICVEGRLMUNICIPIO_ID(x.getICVEGRLMUNICIPIO_PK().getICVEPAIS()+"-"+x.getICVEGRLMUNICIPIO_PK().getICVEENTIDADFED()+"-"+x.getICVEGRLMUNICIPIO_PK().getICVEMUNICIPIO());
			returnGRLMUNICIPIO.add(DAO.save(x));
		}
		return returnGRLMUNICIPIO;
	}
	
	
	
	/**
	 * Create new GRLMUNICIPIO
	 * @param data - GRLMUNICIPIO Object
	 * @return created GRLMUNICIPIO Object
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public GRLMUNICIPIO create(GRLMUNICIPIO GRLMUNICIPIO){				
		return DAO.save(GRLMUNICIPIO);
	}
	
	
	/**
	 * Update GRLMUNICIPIO/GRLMUNICIPIO
	 * @param data - json data from request
	 * @return updated GRLMUNICIPIO
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<GRLMUNICIPIO> update(Object data){
		
		List<GRLMUNICIPIO> returnGRLMUNICIPIO = new ArrayList<GRLMUNICIPIO>();
		
		List<GRLMUNICIPIO> updatedGRLMUNICIPIO = getGRLMUNICIPIOFromRequest(data);
		
		for (GRLMUNICIPIO x : updatedGRLMUNICIPIO){
			returnGRLMUNICIPIO.add(DAO.save(x));
		}
		
		return returnGRLMUNICIPIO;
	}
	
	/**
	 * Spring use - DI
	 * @param GRLMUNICIPIO_DAO
	 */
	@Autowired
	public void setDAO(GRLMUNICIPIO_DAO dao) {
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
	 * Get list of GRLMUNICIPIO from request.
	 * @param data - json data from request 
	 * @return list of GRLMUNICIPIO
	 */
	public List<GRLMUNICIPIO> getGRLMUNICIPIOFromRequest(Object data){

		List<GRLMUNICIPIO> list;

		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){

			list = getListGRLMUNICIPIOFromJSON(data);

		} else { //it is only one object - cast to object/bean

			GRLMUNICIPIO GRLMUNICIPIO = getGRLMUNICIPIOFromJSON(data);

			list = new ArrayList<GRLMUNICIPIO>();
			list.add(GRLMUNICIPIO);
		}

		return list;
	}
	
	/**
	 * Transform json data format into GRLMUNICIPIO object
	 * @param data - json data from request
	 * @return 
	 */
	private GRLMUNICIPIO getGRLMUNICIPIOFromJSON(Object data){
		JSONObject jsonObject = JSONObject.fromObject(data);
		GRLMUNICIPIO newGRLMUNICIPIO = (GRLMUNICIPIO) JSONObject.toBean(jsonObject, GRLMUNICIPIO.class);
		return newGRLMUNICIPIO;
	}

	/**
	 * Transform json data format into list of GRLMUNICIPIO objects
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<GRLMUNICIPIO> getListGRLMUNICIPIOFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<GRLMUNICIPIO> newGRLMUNICIPIO = (List<GRLMUNICIPIO>) JSONArray.toCollection(jsonArray,GRLMUNICIPIO.class);
		return newGRLMUNICIPIO;
	}
	
	/**
	 * Delete GRLMUNICIPIO by GRLMUNICIPIO_ID
	 * @param data - json data from request
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public void delete(Object data){
		List<String> deleteGRLMUNICIPIO = getIdFromRequest(data);
		
		for (String id : deleteGRLMUNICIPIO){
			GRLMUNICIPIO objToDelete = new GRLMUNICIPIO();
			objToDelete.setICVEGRLMUNICIPIO_ID(id);
			DAO.delete(objToDelete.getICVEGRLMUNICIPIO_PK());
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

			String GRLMUNICIPIO = getIdFromJSON(data);

			list = new ArrayList<String>();
			list.add(GRLMUNICIPIO);
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
		List<String> GRLMUNICIPIO_ID = (List<String>) JSONArray.toCollection(jsonArray,String.class);
		return GRLMUNICIPIO_ID;
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
		String GRLMUNICIPIO_ID = Object.replaceAll("\"","");
		return GRLMUNICIPIO_ID;
	}
}
