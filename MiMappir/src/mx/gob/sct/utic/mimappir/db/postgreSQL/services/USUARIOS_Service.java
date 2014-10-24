package mx.gob.sct.utic.mimappir.db.postgreSQL.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.dao.USUARIOS_DAO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMUSUARIOS;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class USUARIOS_Service {

	private USUARIOS_DAO DAO;
	
	/**
	 * Get all USUARIOS
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public List<MMUSUARIOS> getList(){
		return DAO.getList();
	}
		
	/**
	 * Create new USUARIOS/USUARIOS
	 * @param data - json data from request
	 * @return created USUARIOS
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<MMUSUARIOS> create(Object data){
		
        List<MMUSUARIOS> returnUSUARIOS = new ArrayList<MMUSUARIOS>();
		
		List<MMUSUARIOS> newUSUARIOS = getUSUARIOSFromRequest(data);
		
		for (MMUSUARIOS x : newUSUARIOS){
			Integer key = DAO.getKey(x);
			if(key==null){
				key=new Integer(1);
			}else{
				key=key+1;
			}			
			x.getICVEUSUARIO_PK().setICVEUSUARIO(key);
			x.setICVEUSUARIO_ID(x.getICVEUSUARIO_PK().getICVEUSUARIO()+"-");
			returnUSUARIOS.add(DAO.save(x));
		}
		return returnUSUARIOS;
	}
	
	
	
	/**
	 * Create new USUARIOS
	 * @param data - USUARIOS Object
	 * @return created USUARIOS Object
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public MMUSUARIOS create(MMUSUARIOS USUARIOS){				
		return DAO.save(USUARIOS);
	}
	
	
	/**
	 * Update USUARIOS/USUARIOS
	 * @param data - json data from request
	 * @return updated USUARIOS
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<MMUSUARIOS> update(Object data){
		
		List<MMUSUARIOS> returnUSUARIOS = new ArrayList<MMUSUARIOS>();
		
		List<MMUSUARIOS> updatedUSUARIOS = getUSUARIOSFromRequest(data);
		
		for (MMUSUARIOS x : updatedUSUARIOS){
			returnUSUARIOS.add(DAO.save(x));
		}
		
		return returnUSUARIOS;
	}
	
	/**
	 * Spring use - DI
	 * @param USUARIOS_DAO
	 */
	@Autowired
	public void setDAO(USUARIOS_DAO dao) {
		this.DAO = dao;
	}
	
	/**
	 * Get list of USUARIOS from request.
	 * @param data - json data from request 
	 * @return list of USUARIOS
	 */
	public List<MMUSUARIOS> getUSUARIOSFromRequest(Object data){

		List<MMUSUARIOS> list;

		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){

			list = getListUSUARIOSFromJSON(data);

		} else { //it is only one object - cast to object/bean

			MMUSUARIOS USUARIOS = getUSUARIOSFromJSON(data);

			list = new ArrayList<MMUSUARIOS>();
			list.add(USUARIOS);
		}

		return list;
	}
	
	/**
	 * Transform json data format into USUARIOS object
	 * @param data - json data from request
	 * @return 
	 */
	private MMUSUARIOS getUSUARIOSFromJSON(Object data){
		JSONObject jsonObject = JSONObject.fromObject(data);
		MMUSUARIOS newUSUARIOS = (MMUSUARIOS) JSONObject.toBean(jsonObject, MMUSUARIOS.class);
		return newUSUARIOS;
	}

	/**
	 * Transform json data format into list of USUARIOS objects
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<MMUSUARIOS> getListUSUARIOSFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<MMUSUARIOS> newUSUARIOS = (List<MMUSUARIOS>) JSONArray.toCollection(jsonArray,MMUSUARIOS.class);
		return newUSUARIOS;
	}

	/**
	 * Delete USUARIOS by USUARIOS_ID
	 * @param data - json data from request
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public void delete(Object data){
		List<String> deleteUSUARIOS = getIdFromRequest(data);
		
		for (String id : deleteUSUARIOS){			
			MMUSUARIOS objToDelete = new MMUSUARIOS();
			objToDelete.setICVEUSUARIO_ID(id);
			DAO.delete(objToDelete.getICVEUSUARIO_PK());		
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

			String USUARIOS = getIdFromJSON(data);

			list = new ArrayList<String>();
			list.add(USUARIOS);
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
		List<String> USUARIOS_ID = (List<String>) JSONArray.toCollection(jsonArray,String.class);
		return USUARIOS_ID;
	}
	
	/**
	 * Tranform array of Strings in json data format into
	 * list of Strings
	 * @param data - json data from request
	 * @return
	 */
	public String getIdFromJSON(Object data){
		String Object = (String)data;
		String USUARIOS_ID = Object.replaceAll("\"","");
		return USUARIOS_ID;
	}
	
}
