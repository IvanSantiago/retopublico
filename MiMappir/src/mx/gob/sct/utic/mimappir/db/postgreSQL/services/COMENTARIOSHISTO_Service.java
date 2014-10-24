package mx.gob.sct.utic.mimappir.db.postgreSQL.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.dao.COMENTARIOSHISTO_DAO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMCOMENTARIOSHISTO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class COMENTARIOSHISTO_Service {

private COMENTARIOSHISTO_DAO DAO;
	
	/**
	 * Get all COMENTARIOSHISTO
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public List<MMCOMENTARIOSHISTO> getList(){
		return DAO.getList();
	}
		
	/**
	 * Create new COMENTARIOSHISTO/COMENTARIOSHISTO
	 * @param data - json data from request
	 * @return created COMENTARIOSHISTO
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<MMCOMENTARIOSHISTO> create(Object data){
		
        List<MMCOMENTARIOSHISTO> returnCOMENTARIOSHISTO = new ArrayList<MMCOMENTARIOSHISTO>();
		
		List<MMCOMENTARIOSHISTO> newCOMENTARIOSHISTO = getCOMENTARIOSHISTOFromRequest(data);
		
		for (MMCOMENTARIOSHISTO x : newCOMENTARIOSHISTO){
			Integer key = DAO.getKey(x);
			if(key==null){
				key=new Integer(1);
			}else{
				key=key+1;
			}			
			x.getICVECOMENTARIO_PK().setICVECOMENTARIO(key);
			x.setICVECOMENTARIO_ID(x.getICVECOMENTARIO_PK().getICVECOMENTARIO()+"-");
			returnCOMENTARIOSHISTO.add(DAO.save(x));
		}
		return returnCOMENTARIOSHISTO;
	}
	
	
	
	/**
	 * Create new COMENTARIOSHISTO
	 * @param data - COMENTARIOSHISTO Object
	 * @return created COMENTARIOSHISTO Object
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public MMCOMENTARIOSHISTO create(MMCOMENTARIOSHISTO COMENTARIOSHISTO){				
		return DAO.save(COMENTARIOSHISTO);
	}
	
	
	/**
	 * Update COMENTARIOSHISTO/COMENTARIOSHISTO
	 * @param data - json data from request
	 * @return updated COMENTARIOSHISTO
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<MMCOMENTARIOSHISTO> update(Object data){
		
		List<MMCOMENTARIOSHISTO> returnCOMENTARIOSHISTO = new ArrayList<MMCOMENTARIOSHISTO>();
		
		List<MMCOMENTARIOSHISTO> updatedCOMENTARIOSHISTO = getCOMENTARIOSHISTOFromRequest(data);
		
		for (MMCOMENTARIOSHISTO x : updatedCOMENTARIOSHISTO){
			returnCOMENTARIOSHISTO.add(DAO.save(x));
		}
		
		return returnCOMENTARIOSHISTO;
	}
	
	/**
	 * Spring use - DI
	 * @param COMENTARIOSHISTO_DAO
	 */
	@Autowired
	public void setDAO(COMENTARIOSHISTO_DAO dao) {
		this.DAO = dao;
	}
	
	/**
	 * Get list of COMENTARIOSHISTO from request.
	 * @param data - json data from request 
	 * @return list of COMENTARIOSHISTO
	 */
	public List<MMCOMENTARIOSHISTO> getCOMENTARIOSHISTOFromRequest(Object data){

		List<MMCOMENTARIOSHISTO> list;

		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){

			list = getListCOMENTARIOSHISTOFromJSON(data);

		} else { //it is only one object - cast to object/bean

			MMCOMENTARIOSHISTO COMENTARIOSHISTO = getCOMENTARIOSHISTOFromJSON(data);

			list = new ArrayList<MMCOMENTARIOSHISTO>();
			list.add(COMENTARIOSHISTO);
		}

		return list;
	}
	
	/**
	 * Transform json data format into COMENTARIOSHISTO object
	 * @param data - json data from request
	 * @return 
	 */
	private MMCOMENTARIOSHISTO getCOMENTARIOSHISTOFromJSON(Object data){
		JSONObject jsonObject = JSONObject.fromObject(data);
		MMCOMENTARIOSHISTO newCOMENTARIOSHISTO = (MMCOMENTARIOSHISTO) JSONObject.toBean(jsonObject, MMCOMENTARIOSHISTO.class);
		return newCOMENTARIOSHISTO;
	}

	/**
	 * Transform json data format into list of COMENTARIOSHISTO objects
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<MMCOMENTARIOSHISTO> getListCOMENTARIOSHISTOFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<MMCOMENTARIOSHISTO> newCOMENTARIOSHISTO = (List<MMCOMENTARIOSHISTO>) JSONArray.toCollection(jsonArray,MMCOMENTARIOSHISTO.class);
		return newCOMENTARIOSHISTO;
	}

	/**
	 * Delete COMENTARIOSHISTO by COMENTARIOSHISTO_ID
	 * @param data - json data from request
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public void delete(Object data){
		List<String> deleteCOMENTARIOSHISTO = getIdFromRequest(data);
		
		for (String id : deleteCOMENTARIOSHISTO){			
			MMCOMENTARIOSHISTO objToDelete = new MMCOMENTARIOSHISTO();
			objToDelete.setICVECOMENTARIO_ID(id);
			DAO.delete(objToDelete.getICVECOMENTARIO_PK());		
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

			String COMENTARIOSHISTO = getIdFromJSON(data);

			list = new ArrayList<String>();
			list.add(COMENTARIOSHISTO);
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
		List<String> ICVECOMENTARIO_ID = (List<String>) JSONArray.toCollection(jsonArray,String.class);
		return ICVECOMENTARIO_ID;
	}
	
	/**
	 * Tranform array of Strings in json data format into
	 * list of Strings
	 * @param data - json data from request
	 * @return
	 */
	public String getIdFromJSON(Object data){
		String Object = (String)data;
		String ICVECOMENTARIO_ID = Object.replaceAll("\"","");
		return ICVECOMENTARIO_ID;
	}
}
