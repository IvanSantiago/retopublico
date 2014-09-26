package mx.gob.sct.utic.qoscell.sigtic.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.qoscell.sigtic.db2.dao.MENU_DAO;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.MENU;
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
public class MENU_Service {
	
	private MENU_DAO DAO;
	private Util util;

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<MENU> getMenuList(){
		return DAO.getList();
	}
	
	/**
	 * Get all Menu options for a user
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<MENU> getMenuForUser(String LOGIN){
		return DAO.getMenuListForUser(LOGIN);
	}
	/**
	 * Create new Contact/Contacts
	 * @param data - json data from request
	 * @return created contacts
	 */
	@Transactional
	public List<MENU> create(Object data){
		
        List<MENU> newContacts = new ArrayList<MENU>();
		
		List<MENU> list = null;//util.getContactsFromRequest(data);
		
		for (MENU x : list){
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
	public List<MENU> update(Object data){
		
		List<MENU> returnContacts = new ArrayList<MENU>();
		
		List<MENU> updatedContacts = null;//util.getContactsFromRequest(data);
		
		for (MENU x : updatedContacts){
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
	public void setDAO(MENU_DAO dao) {
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
