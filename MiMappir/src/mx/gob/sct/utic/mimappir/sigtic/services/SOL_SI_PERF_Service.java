package mx.gob.sct.utic.mimappir.sigtic.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.sigtic.db2.dao.SOL_SI_PERF_DAO;
import mx.gob.sct.utic.mimappir.sigtic.db2.model.SOL_SI_PERF;
import mx.gob.sct.utic.mimappir.util.Util;

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
public class SOL_SI_PERF_Service {
	
	private SOL_SI_PERF_DAO DAO;
	private Util util;

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<SOL_SI_PERF> getList(){

		return DAO.getList();
	}
	
	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<SOL_SI_PERF> getPerfilesDelSistema(Integer UID_SI){

		return DAO.getPerfilesForSis(UID_SI);
	}
	
	
	/**
	 * Create new Contact/Contacts
	 * @param data - json data from request
	 * @return created contacts
	 */
	@Transactional
	public List<SOL_SI_PERF> create(Object data){
		
        List<SOL_SI_PERF> newContacts = new ArrayList<SOL_SI_PERF>();
		
		//List<SOL_SI_PERF> list = util.getContactsFromRequest(data);
		
		for (SOL_SI_PERF x : newContacts){
			newContacts.add(DAO.save(x));
		}
		
		return newContacts;
	}
	
	
	/**
	 * Update contact/contacts
	 * @param data - json data from request
	 * @return updated contacts
	 */
	@Transactional
	public List<SOL_SI_PERF> update(Object data){
		
		List<SOL_SI_PERF> returnContacts = new ArrayList<SOL_SI_PERF>();
		
		//List<SOL_SI_PERF> updatedContacts = util.getContactsFromRequest(data);
		
		for (SOL_SI_PERF x : returnContacts){
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
	public void setDAO(SOL_SI_PERF_DAO dao) {
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
