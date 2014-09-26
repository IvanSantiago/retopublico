package mx.gob.sct.utic.qoscell.sigtic.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.qoscell.sigtic.db2.dao.INBOX_DAO;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.INBOX;
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
public class INBOX_Service {
	
	private INBOX_DAO DAO;
	private Util util;

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<INBOX> getList(){

		return DAO.getList();
	}
	
	/**
	 * Create new Contact/Contacts
	 * @param data - json data from request
	 * @return created contacts
	 */
	@Transactional
	public List<INBOX> create(Object data){
		
        List<INBOX> newContacts = new ArrayList<INBOX>();
		
		//List<INBOX> list = util.getContactsFromRequest(data);
		
		for (INBOX x : newContacts){
			newContacts.add(DAO.save(x));
		}
		
		return newContacts;
	}
	
	/**
	 * Create new INBOX
	 * @param data - INBOX Object
	 * @return created INBOX Object
	 */
	@Transactional
	public INBOX create(INBOX INBOX){				
		return DAO.save(INBOX);
	}
	
	
	/**
	 * Update contact/contacts
	 * @param data - json data from request
	 * @return updated contacts
	 */
	@Transactional
	public List<INBOX> update(Object data){
		
		List<INBOX> returnContacts = new ArrayList<INBOX>();
		
		//List<INBOX> updatedContacts = util.getContactsFromRequest(data);
		
		for (INBOX x : returnContacts){
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
	public void setDAO(INBOX_DAO dao) {
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
