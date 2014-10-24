package mx.gob.sct.utic.mimappir.db.postgreSQL.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.dao.CATEGORIAS_DAO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMCATEGORIAS;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CATEGORIAS_Service {
private CATEGORIAS_DAO DAO;
	
	/**
	 * Get all CATEGORIAS
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public List<MMCATEGORIAS> getList(){
		return DAO.getList();
	}
		
	/**
	 * Create new CATEGORIAS/CATEGORIAS
	 * @param data - json data from request
	 * @return created CATEGORIAS
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<MMCATEGORIAS> create(Object data){
		
        List<MMCATEGORIAS> returnCATEGORIAS = new ArrayList<MMCATEGORIAS>();
		
		List<MMCATEGORIAS> newCATEGORIAS = getCATEGORIASFromRequest(data);
		
		for (MMCATEGORIAS x : newCATEGORIAS){
			Integer key = DAO.getKey(x);
			if(key==null){
				key=new Integer(1);
			}else{
				key=key+1;
			}			
			x.getICVECATEGORIA_PK().setICVECATEGORIA(key);
			x.setICVECATEGORIA_ID(x.getICVECATEGORIA_PK().getICVECATEGORIA()+"-");
			returnCATEGORIAS.add(DAO.save(x));
		}
		return returnCATEGORIAS;
	}
	
	
	
	/**
	 * Create new CATEGORIAS
	 * @param data - CATEGORIAS Object
	 * @return created CATEGORIAS Object
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public MMCATEGORIAS create(MMCATEGORIAS CATEGORIAS){				
		return DAO.save(CATEGORIAS);
	}
	
	
	/**
	 * Update CATEGORIAS/CATEGORIAS
	 * @param data - json data from request
	 * @return updated CATEGORIAS
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<MMCATEGORIAS> update(Object data){
		
		List<MMCATEGORIAS> returnCATEGORIAS = new ArrayList<MMCATEGORIAS>();
		
		List<MMCATEGORIAS> updatedCATEGORIAS = getCATEGORIASFromRequest(data);
		
		for (MMCATEGORIAS x : updatedCATEGORIAS){
			returnCATEGORIAS.add(DAO.save(x));
		}
		
		return returnCATEGORIAS;
	}
	
	/**
	 * Spring use - DI
	 * @param CATEGORIAS_DAO
	 */
	@Autowired
	public void setDAO(CATEGORIAS_DAO dao) {
		this.DAO = dao;
	}
	
	/**
	 * Get list of CATEGORIAS from request.
	 * @param data - json data from request 
	 * @return list of CATEGORIAS
	 */
	public List<MMCATEGORIAS> getCATEGORIASFromRequest(Object data){

		List<MMCATEGORIAS> list;

		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){

			list = getListCATEGORIASFromJSON(data);

		} else { //it is only one object - cast to object/bean

			MMCATEGORIAS CATEGORIAS = getCATEGORIASFromJSON(data);

			list = new ArrayList<MMCATEGORIAS>();
			list.add(CATEGORIAS);
		}

		return list;
	}
	
	/**
	 * Transform json data format into CATEGORIAS object
	 * @param data - json data from request
	 * @return 
	 */
	private MMCATEGORIAS getCATEGORIASFromJSON(Object data){
		JSONObject jsonObject = JSONObject.fromObject(data);
		MMCATEGORIAS newCATEGORIAS = (MMCATEGORIAS) JSONObject.toBean(jsonObject, MMCATEGORIAS.class);
		return newCATEGORIAS;
	}

	/**
	 * Transform json data format into list of CATEGORIAS objects
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<MMCATEGORIAS> getListCATEGORIASFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<MMCATEGORIAS> newCATEGORIAS = (List<MMCATEGORIAS>) JSONArray.toCollection(jsonArray,MMCATEGORIAS.class);
		return newCATEGORIAS;
	}

	/**
	 * Delete CATEGORIAS by CATEGORIAS_ID
	 * @param data - json data from request
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public void delete(Object data){
		List<String> deleteCATEGORIAS = getIdFromRequest(data);
		
		for (String id : deleteCATEGORIAS){			
			MMCATEGORIAS objToDelete = new MMCATEGORIAS();
			objToDelete.setICVECATEGORIA_ID(id);
			DAO.delete(objToDelete.getICVECATEGORIA_PK());		
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

			String CATEGORIAS = getIdFromJSON(data);

			list = new ArrayList<String>();
			list.add(CATEGORIAS);
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
		List<String> CATEGORIAS_ID = (List<String>) JSONArray.toCollection(jsonArray,String.class);
		return CATEGORIAS_ID;
	}
	
	/**
	 * Tranform array of Strings in json data format into
	 * list of Strings
	 * @param data - json data from request
	 * @return
	 */
	public String getIdFromJSON(Object data){
		String Object = (String)data;
		String CATEGORIAS_ID = Object.replaceAll("\"","");
		return CATEGORIAS_ID;
	}
	
}
