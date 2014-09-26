package mx.gob.sct.utic.qoscell.admseg.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.qoscell.admseg.postgreSQL.dao.SEGUSUARIO_DAO;
import mx.gob.sct.utic.qoscell.admseg.postgreSQL.model.SEGUSUARIO;
import mx.gob.sct.utic.qoscell.util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SEGUSUARIO Service
 * 
 * @author Ivan Santiago
 * 
 */
@Service
public class SEGUSUARIO_Service {
	
	private SEGUSUARIO_DAO DAO;
	private Util util;

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS",readOnly=true)
	public List<SEGUSUARIO> getUsuariosList(){

		return DAO.getList();
	}
	
	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS",readOnly=true)
	public List<SEGUSUARIO> getUsuario(String Login){
		return DAO.getUser(Login);
	}
	
	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS",readOnly=true)
	public SEGUSUARIO getNewestUser(String Login){
		SEGUSUARIO newestuser = DAO.getNewestUser(Login); 
		if(newestuser == null){
			newestuser = new SEGUSUARIO();
		}
		return newestuser;
		
	}
	
	/**
	 * Create new Contact/Contacts
	 * @param data - json data from request
	 * @return created contacts
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS",readOnly=true)
	public List<SEGUSUARIO> create(Object data){
		
        List<SEGUSUARIO> newContacts = new ArrayList<SEGUSUARIO>();
		
		List<SEGUSUARIO> list = null;//util.getContactsFromRequest(data);
		
		for (SEGUSUARIO x : list){
			newContacts.add(DAO.save(x));
		}
		
		return newContacts;
	}
	
	
	/**
	 * Update contact/contacts
	 * @param data - json data from request
	 * @return updated contacts
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS")
	public List<SEGUSUARIO> update(Object data){
		
		List<SEGUSUARIO> returnContacts = new ArrayList<SEGUSUARIO>();
		
		List<SEGUSUARIO> updatedContacts = null;//util.getContactsFromRequest(data);
		
		for (SEGUSUARIO x : updatedContacts){
			returnContacts.add(DAO.save(x));
		}
		
		return returnContacts;
	}
	
	/**
	 * Delete contact/contacts
	 * @param data - json data from request
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS")
	public void delete(Object data){
		
		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){
			
			List<Integer> deleteContacts = util.getListIdFromJSON(data);
			
			for (Integer id : deleteContacts){
				DAO.delete(id);
			}
			
		} else { //it is only one object - cast to object/bean
			
			Integer id = Integer.parseInt(data.toString());
			
			DAO.delete(id);
		}
	}
	

	/**
	 * Spring use - DI
	 * @param sistemaDAO
	 */
	@Autowired
	public void setDAO(SEGUSUARIO_DAO dao) {
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
	
}
