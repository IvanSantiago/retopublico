package mx.gob.sct.utic.mimappir.sigtic.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.sigtic.db2.dao.C_R_UA_DAO;
import mx.gob.sct.utic.mimappir.sigtic.db2.model.C_R_UA;
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
public class C_R_UA_Service {
	
	private C_R_UA_DAO DAO;
	private Util util;

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<C_R_UA> getList(){

		return DAO.getList();
	}

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public C_R_UA getC_R_UAofUA(int UID_UA){
		List<C_R_UA> processes = DAO.getC_R_UAofUA(UID_UA);
		C_R_UA findedProcess = null;
		// Search user based on the parameters
		for (C_R_UA proceso : processes) {
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
	public List<C_R_UA> create(Object data){
		
        List<C_R_UA> newContacts = new ArrayList<C_R_UA>();
		
		//List<C_R_UA> list = util.getContactsFromRequest(data);
		
		for (C_R_UA x : newContacts){
			newContacts.add(DAO.save(x));
		}
		
		return newContacts;
	}
	
	/**
	 * Create new C_R_UA
	 * @param data - C_R_UA Object
	 * @return created C_R_UA Object
	 */
	@Transactional
	public C_R_UA create(C_R_UA C_R_UA){				
		return DAO.save(C_R_UA);
	}
	
	
	/**
	 * Update contact/contacts
	 * @param data - json data from request
	 * @return updated contacts
	 */
	@Transactional
	public List<C_R_UA> update(Object data){
		
		List<C_R_UA> returnContacts = new ArrayList<C_R_UA>();
		
		//List<C_R_UA> updatedContacts = util.getContactsFromRequest(data);
		
		for (C_R_UA x : returnContacts){
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
	public void setDAO(C_R_UA_DAO dao) {
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
