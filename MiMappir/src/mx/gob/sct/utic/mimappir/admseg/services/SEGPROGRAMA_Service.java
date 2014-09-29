package mx.gob.sct.utic.mimappir.admseg.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.admseg.postgreSQL.dao.SEGPROGRAMA_DAO;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGPROGRAMA;
import mx.gob.sct.utic.mimappir.util.Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SEGPROGRAMA Service
 * 
 * @author Ivan Santiago
 * 
 */
@Service
public class SEGPROGRAMA_Service {
	
	private SEGPROGRAMA_DAO DAO;
	private Util util;

	/**
	 * Get all SEGPROGRAMA
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_DB2",readOnly=true)
	public List<SEGPROGRAMA> getList(){
		return DAO.getList();
	}
	
	/**
	 * Create new SEGPROGRAMA/SEGPROGRAMA
	 * @param data - json data from request
	 * @return created SEGPROGRAMA
	 */
	@Transactional(value="transactionManager_mimappir_DB2")
	public List<SEGPROGRAMA> create(Object data){
		
        List<SEGPROGRAMA> returnSEGPROGRAMA = new ArrayList<SEGPROGRAMA>();
		
		List<SEGPROGRAMA> newSEGPROGRAMA = getSEGPROGRAMAFromRequest(data);
		
/*		for (SEGPROGRAMA x : newSEGPROGRAMA){
			Integer key = DAO.getKey(x);
			if(key==null){
				key=new Integer(1);
			}else{
				key=key+1;
			}			
			x.getICVESEGPROGRAMA_PK().setICVEOBRA(key);
			x.setICVESEGPROGRAMA_ID(x.getICVESEGPROGRAMA_PK().getICVEOBRA()+"-");
			returnSEGPROGRAMA.add(DAO.save(x));
		}*/
		return returnSEGPROGRAMA;
	}
	
	
	
	/**
	 * Create new SEGPROGRAMA
	 * @param data - SEGPROGRAMA Object
	 * @return created SEGPROGRAMA Object
	 */
	@Transactional(value="transactionManager_mimappir_DB2")
	public SEGPROGRAMA create(SEGPROGRAMA SEGPROGRAMA){				
		return DAO.save(SEGPROGRAMA);
	}
	
	
	/**
	 * Update SEGPROGRAMA/SEGPROGRAMA
	 * @param data - json data from request
	 * @return updated SEGPROGRAMA
	 */
	@Transactional(value="transactionManager_mimappir_DB2")
	public List<SEGPROGRAMA> update(Object data){
		
		List<SEGPROGRAMA> returnSEGPROGRAMA = new ArrayList<SEGPROGRAMA>();
		
		List<SEGPROGRAMA> updatedSEGPROGRAMA = getSEGPROGRAMAFromRequest(data);
		
		for (SEGPROGRAMA x : updatedSEGPROGRAMA){
			returnSEGPROGRAMA.add(DAO.save(x));
		}
		
		return returnSEGPROGRAMA;
	}
	
	/**
	 * Delete SEGPROGRAMA by SEGPROGRAMA_ID
	 * @param data - json data from request
	 */
	@Transactional(value="transactionManager_mimappir_DB2")
	public void delete(Object data){
		List<String> deleteSEGPROGRAMA = getIdFromRequest(data);
/*		
		for (String id : deleteSEGPROGRAMA){
			SEGPROGRAMA objToDelete = new SEGPROGRAMA();
			objToDelete.setICVESEGPROGRAMA_ID(id);
			DAO.delete(objToDelete.getICVESEGPROGRAMA_PK());
		}*/
	}
	

	/**
	 * Spring use - DI
	 * @param SEGPROGRAMA_DAO
	 */
	@Autowired
	public void setDAO(SEGPROGRAMA_DAO dao) {
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
	 * Get list of SEGPROGRAMA from request.
	 * @param data - json data from request 
	 * @return list of SEGPROGRAMA
	 */
	public List<SEGPROGRAMA> getSEGPROGRAMAFromRequest(Object data){

		List<SEGPROGRAMA> list;

		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){

			list = getListSEGPROGRAMAFromJSON(data);

		} else { //it is only one object - cast to object/bean

			SEGPROGRAMA SEGPROGRAMA = getSEGPROGRAMAFromJSON(data);

			list = new ArrayList<SEGPROGRAMA>();
			list.add(SEGPROGRAMA);
		}

		return list;
	}

	/**
	 * Transform json data format into SEGPROGRAMA object
	 * @param data - json data from request
	 * @return 
	 */
	private SEGPROGRAMA getSEGPROGRAMAFromJSON(Object data){
		JSONObject jsonObject = JSONObject.fromObject(data);
		SEGPROGRAMA newSEGPROGRAMA = (SEGPROGRAMA) JSONObject.toBean(jsonObject, SEGPROGRAMA.class);
		return newSEGPROGRAMA;
	}

	/**
	 * Transform json data format into list of SEGPROGRAMA objects
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<SEGPROGRAMA> getListSEGPROGRAMAFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<SEGPROGRAMA> newSEGPROGRAMA = (List<SEGPROGRAMA>) JSONArray.toCollection(jsonArray,SEGPROGRAMA.class);
		return newSEGPROGRAMA;
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

			String SEGPROGRAMA = getIdFromJSON(data);

			list = new ArrayList<String>();
			list.add(SEGPROGRAMA);
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
		List<String> SEGPROGRAMA_ID = (List<String>) JSONArray.toCollection(jsonArray,String.class);
		return SEGPROGRAMA_ID;
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
		String SEGPROGRAMA_ID = Object.replaceAll("\"","");
		return SEGPROGRAMA_ID;
	}
}
