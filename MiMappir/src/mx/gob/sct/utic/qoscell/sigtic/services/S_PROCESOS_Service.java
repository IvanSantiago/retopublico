package mx.gob.sct.utic.qoscell.sigtic.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.qoscell.sigtic.db2.dao.S_PROCESOS_DAO;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.S_PROCESOS;
import mx.gob.sct.utic.qoscell.util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Sistemas Service
 * 
 * @author Ivan Santiago
 * 
 */
@Service
public class S_PROCESOS_Service {
	
	private S_PROCESOS_DAO DAO;
	private Util util;

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<S_PROCESOS> getList(){

		return DAO.getList();
	}

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public S_PROCESOS getS_PROCESOS(int UID_PROCESO){
		List<S_PROCESOS> processes = DAO.getS_PROCESOS(UID_PROCESO);
		S_PROCESOS findedProcess = null;
		// Search user based on the parameters
		for (S_PROCESOS proceso : processes) {
			findedProcess= proceso;
		}
		return findedProcess;
	}
	
	/**
	 * Create new Contact/Contacts
	 * @param data - json data from request
	 * @return created contacts
	 */
	@Transactional
	public List<S_PROCESOS> create(Object data){
		
        List<S_PROCESOS> newContacts = new ArrayList<S_PROCESOS>();
		
		//List<S_PROCESOS> list = util.getContactsFromRequest(data);
		
		for (S_PROCESOS x : newContacts){
			newContacts.add(DAO.save(x));
		}
		
		return newContacts;
	}
	
	/**
	 * Create new S_PROCESOS
	 * @param data - S_PROCESOS Object
	 * @return created S_PROCESOS Object
	 */
	@Transactional
	public S_PROCESOS create(S_PROCESOS S_PROCESOS){				
		return DAO.save(S_PROCESOS);
	}
	
	
	/**
	 * Update contact/contacts
	 * @param data - json data from request
	 * @return updated contacts
	 */
	@Transactional
	public List<S_PROCESOS> update(Object data){
		
		List<S_PROCESOS> returnContacts = new ArrayList<S_PROCESOS>();
		
		//List<S_PROCESOS> updatedContacts = util.getContactsFromRequest(data);
		
		for (S_PROCESOS x : returnContacts){
			returnContacts.add(DAO.save(x));
		}
		
		return returnContacts;
	}
	
	/**
	 * Delete contact/contacts
	 * @param data - json data from request
	 */
	@Transactional
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
	public void setDAO(S_PROCESOS_DAO dao) {
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
