package mx.gob.sct.utic.mimappir.admseg.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.admseg.postgreSQL.dao.SEGGPOXUSR_DAO;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGGPOXUSR;
import mx.gob.sct.utic.mimappir.util.Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SEGGPOXUSR Service
 * 
 * @author Ivan Santiago
 * 
 */
@Service
public class SEGGPOXUSR_Service {
	
	private SEGGPOXUSR_DAO DAO;
	private Util util;

	/**
	 * Get all SEGGPOXUSR
	 * @return
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS")
	public List<SEGGPOXUSR> getList(){
		return DAO.getList();
	}
	/**
	 * Get specific SEGGPOXUSR
	 * @return
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS")
	public SEGGPOXUSR getSEGGPOXUSR(Long ICVEUSUARIO){
		List<SEGGPOXUSR> processes = DAO.getSEGGPOXUSR(ICVEUSUARIO);
		SEGGPOXUSR findedProcess = null;
		// Search user based on the parameters
		for (SEGGPOXUSR proceso : processes) {
			findedProcess= proceso;
		}
		return findedProcess;
	}
	
	/**
	 * Create new SEGGPOXUSR/SEGGPOXUSR
	 * @param data - json data from request
	 * @return created SEGGPOXUSR
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS")
	public List<SEGGPOXUSR> create(Object data){
		
        List<SEGGPOXUSR> returnSEGGPOXUSR = new ArrayList<SEGGPOXUSR>();
		
		List<SEGGPOXUSR> newSEGGPOXUSR = getSEGGPOXUSRFromRequest(data);
		
/*		for (SEGGPOXUSR x : newSEGGPOXUSR){
			Integer key = DAO.getKey(x);
			if(key==null){
				key=new Integer(1);
			}else{
				key=key+1;
			}			
			x.getICVESEGGPOXUSR_PK().setICVEOBRA(key);
			x.setICVESEGGPOXUSR_ID(x.getICVESEGGPOXUSR_PK().getICVEOBRA()+"-");
			returnSEGGPOXUSR.add(DAO.save(x));
		}*/
		return returnSEGGPOXUSR;
	}
	
	
	
	/**
	 * Create new SEGGPOXUSR
	 * @param data - SEGGPOXUSR Object
	 * @return created SEGGPOXUSR Object
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS")
	public SEGGPOXUSR create(SEGGPOXUSR SEGGPOXUSR){				
		return DAO.save(SEGGPOXUSR);
	}
	
	
	/**
	 * Update SEGGPOXUSR/SEGGPOXUSR
	 * @param data - json data from request
	 * @return updated SEGGPOXUSR
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS")
	public List<SEGGPOXUSR> update(Object data){
		
		List<SEGGPOXUSR> returnSEGGPOXUSR = new ArrayList<SEGGPOXUSR>();
		
		List<SEGGPOXUSR> updatedSEGGPOXUSR = getSEGGPOXUSRFromRequest(data);
		
		for (SEGGPOXUSR x : updatedSEGGPOXUSR){
			returnSEGGPOXUSR.add(DAO.save(x));
		}
		
		return returnSEGGPOXUSR;
	}
	
	/**
	 * Delete SEGGPOXUSR by SEGGPOXUSR_ID
	 * @param data - json data from request
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS")
	public void delete(Object data){
		List<String> deleteSEGGPOXUSR = getIdFromRequest(data);
/*		
		for (String id : deleteSEGGPOXUSR){
			SEGGPOXUSR objToDelete = new SEGGPOXUSR();
			objToDelete.setICVESEGGPOXUSR_ID(id);
			DAO.delete(objToDelete.getICVESEGGPOXUSR_PK());
		}*/
	}
	

	/**
	 * Spring use - DI
	 * @param SEGGPOXUSR_DAO
	 */
	@Autowired
	public void setDAO(SEGGPOXUSR_DAO dao) {
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
	 * Get list of SEGGPOXUSR from request.
	 * @param data - json data from request 
	 * @return list of SEGGPOXUSR
	 */
	public List<SEGGPOXUSR> getSEGGPOXUSRFromRequest(Object data){

		List<SEGGPOXUSR> list;

		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){

			list = getListSEGGPOXUSRFromJSON(data);

		} else { //it is only one object - cast to object/bean

			SEGGPOXUSR SEGGPOXUSR = getSEGGPOXUSRFromJSON(data);

			list = new ArrayList<SEGGPOXUSR>();
			list.add(SEGGPOXUSR);
		}

		return list;
	}

	/**
	 * Transform json data format into SEGGPOXUSR object
	 * @param data - json data from request
	 * @return 
	 */
	private SEGGPOXUSR getSEGGPOXUSRFromJSON(Object data){
		JSONObject jsonObject = JSONObject.fromObject(data);
		SEGGPOXUSR newSEGGPOXUSR = (SEGGPOXUSR) JSONObject.toBean(jsonObject, SEGGPOXUSR.class);
		return newSEGGPOXUSR;
	}

	/**
	 * Transform json data format into list of SEGGPOXUSR objects
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<SEGGPOXUSR> getListSEGGPOXUSRFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<SEGGPOXUSR> newSEGGPOXUSR = (List<SEGGPOXUSR>) JSONArray.toCollection(jsonArray,SEGGPOXUSR.class);
		return newSEGGPOXUSR;
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

			String SEGGPOXUSR = getIdFromJSON(data);

			list = new ArrayList<String>();
			list.add(SEGGPOXUSR);
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
		List<String> SEGGPOXUSR_ID = (List<String>) JSONArray.toCollection(jsonArray,String.class);
		return SEGGPOXUSR_ID;
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
		String SEGGPOXUSR_ID = Object.replaceAll("\"","");
		return SEGGPOXUSR_ID;
	}
}
