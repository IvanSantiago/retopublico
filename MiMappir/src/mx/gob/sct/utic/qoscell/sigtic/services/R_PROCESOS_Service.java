package mx.gob.sct.utic.qoscell.sigtic.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.qoscell.sigtic.db2.dao.R_PROCESOS_DAO;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.R_PROCESOS;
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
public class R_PROCESOS_Service {
	
	private R_PROCESOS_DAO DAO;
	private Util util;

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<R_PROCESOS> getList(){

		return DAO.getList();
	}
	/**
	 * Get specific process
	 * @return
	 */
	@Transactional(readOnly=true)
	public R_PROCESOS getR_PROCESOS(int UID_R_PROCESO){
		List<R_PROCESOS> processes = DAO.getR_PROCESOS(UID_R_PROCESO);
		R_PROCESOS findedProcess = null;
		// Search user based on the parameters
		for (R_PROCESOS proceso : processes) {
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
	public List<R_PROCESOS> create(Object data){
		
        List<R_PROCESOS> newContacts = new ArrayList<R_PROCESOS>();
		
		//List<R_PROCESOS> list = util.getContactsFromRequest(data);
		
		for (R_PROCESOS x : newContacts){
			newContacts.add(DAO.save(x));
		}
		
		return newContacts;
	}
	
	/**
	 * Create new R_PROCESOS
	 * @param data - R_PROCESOS Object
	 * @return created R_PROCESOS Object
	 */
	@Transactional
	public R_PROCESOS create(R_PROCESOS r_procesos){				
		return DAO.save(r_procesos);
	}
	
	
	/**
	 * Update contact/contacts
	 * @param data - json data from request
	 * @return updated contacts
	 */
	@Transactional
	public List<R_PROCESOS> update(Object data){
		
		List<R_PROCESOS> returnContacts = new ArrayList<R_PROCESOS>();
		
		//List<R_PROCESOS> updatedContacts = util.getContactsFromRequest(data);
		
		for (R_PROCESOS x : returnContacts){
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
	public void setDAO(R_PROCESOS_DAO dao) {
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
