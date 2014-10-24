package mx.gob.sct.utic.mimappir.db.postgreSQL.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.dao.PARTICIPANTES_DAO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMPARTICIPANTES;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PARTICIPANTES_Service {
private PARTICIPANTES_DAO DAO;
	
	/**
	 * Get all PARTICIPANTES
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public List<MMPARTICIPANTES> getList(){
		return DAO.getList();
	}
		
	/**
	 * Create new PARTICIPANTES/PARTICIPANTES
	 * @param data - json data from request
	 * @return created PARTICIPANTES
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<MMPARTICIPANTES> create(Object data){
		
        List<MMPARTICIPANTES> returnPARTICIPANTES = new ArrayList<MMPARTICIPANTES>();
		
		List<MMPARTICIPANTES> newPARTICIPANTES = getPARTICIPANTESFromRequest(data);
		
		for (MMPARTICIPANTES x : newPARTICIPANTES){
			Integer key = DAO.getKey(x);
			if(key==null){
				key=new Integer(1);
			}else{
				key=key+1;
			}			
			x.getICVEPARTICIPANTE_PK().setICVEPARTICIPANTE(key);
			x.setICVEPARTICIPANTE_ID(x.getICVEPARTICIPANTE_PK().getICVEPARTICIPANTE()+"-");
			returnPARTICIPANTES.add(DAO.save(x));
		}
		return returnPARTICIPANTES;
	}
	
	
	
	/**
	 * Create new PARTICIPANTES
	 * @param data - PARTICIPANTES Object
	 * @return created PARTICIPANTES Object
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public MMPARTICIPANTES create(MMPARTICIPANTES PARTICIPANTES){				
		return DAO.save(PARTICIPANTES);
	}
	
	
	/**
	 * Update PARTICIPANTES/PARTICIPANTES
	 * @param data - json data from request
	 * @return updated PARTICIPANTES
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<MMPARTICIPANTES> update(Object data){
		
		List<MMPARTICIPANTES> returnPARTICIPANTES = new ArrayList<MMPARTICIPANTES>();
		
		List<MMPARTICIPANTES> updatedPARTICIPANTES = getPARTICIPANTESFromRequest(data);
		
		for (MMPARTICIPANTES x : updatedPARTICIPANTES){
			returnPARTICIPANTES.add(DAO.save(x));
		}
		
		return returnPARTICIPANTES;
	}
	
	/**
	 * Spring use - DI
	 * @param PARTICIPANTES_DAO
	 */
	@Autowired
	public void setDAO(PARTICIPANTES_DAO dao) {
		this.DAO = dao;
	}
	
	/**
	 * Get list of PARTICIPANTES from request.
	 * @param data - json data from request 
	 * @return list of PARTICIPANTES
	 */
	public List<MMPARTICIPANTES> getPARTICIPANTESFromRequest(Object data){

		List<MMPARTICIPANTES> list;

		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){

			list = getListPARTICIPANTESFromJSON(data);

		} else { //it is only one object - cast to object/bean

			MMPARTICIPANTES PARTICIPANTES = getPARTICIPANTESFromJSON(data);

			list = new ArrayList<MMPARTICIPANTES>();
			list.add(PARTICIPANTES);
		}

		return list;
	}
	
	/**
	 * Transform json data format into PARTICIPANTES object
	 * @param data - json data from request
	 * @return 
	 */
	private MMPARTICIPANTES getPARTICIPANTESFromJSON(Object data){
		JSONObject jsonObject = JSONObject.fromObject(data);
		MMPARTICIPANTES newPARTICIPANTES = (MMPARTICIPANTES) JSONObject.toBean(jsonObject, MMPARTICIPANTES.class);
		return newPARTICIPANTES;
	}

	/**
	 * Transform json data format into list of PARTICIPANTES objects
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<MMPARTICIPANTES> getListPARTICIPANTESFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<MMPARTICIPANTES> newPARTICIPANTES = (List<MMPARTICIPANTES>) JSONArray.toCollection(jsonArray,MMPARTICIPANTES.class);
		return newPARTICIPANTES;
	}

	/**
	 * Delete PARTICIPANTES by PARTICIPANTES_ID
	 * @param data - json data from request
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public void delete(Object data){
		List<String> deletePARTICIPANTES = getIdFromRequest(data);
		
		for (String id : deletePARTICIPANTES){			
			MMPARTICIPANTES objToDelete = new MMPARTICIPANTES();
			objToDelete.setICVEPARTICIPANTE_ID(id);
			DAO.delete(objToDelete.getICVEPARTICIPANTE_PK());		
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

			String PARTICIPANTES = getIdFromJSON(data);

			list = new ArrayList<String>();
			list.add(PARTICIPANTES);
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
		List<String> PARTICIPANTES_ID = (List<String>) JSONArray.toCollection(jsonArray,String.class);
		return PARTICIPANTES_ID;
	}
	
	/**
	 * Tranform array of Strings in json data format into
	 * list of Strings
	 * @param data - json data from request
	 * @return
	 */
	public String getIdFromJSON(Object data){
		String Object = (String)data;
		String PARTICIPANTES_ID = Object.replaceAll("\"","");
		return PARTICIPANTES_ID;
	}
	
}
