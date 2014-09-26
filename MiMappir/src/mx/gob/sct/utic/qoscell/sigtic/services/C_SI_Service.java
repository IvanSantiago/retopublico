package mx.gob.sct.utic.qoscell.sigtic.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.qoscell.sigtic.db2.dao.C_SI_DAO;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.C_SI;
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
public class C_SI_Service {
	
	private C_SI_DAO DAO;
	private Util util;

	/**
	 * Get specific system
	 * @param UID_SI - int id system
	 * @return C_SI - Finded system Object
	 */
	@Transactional(readOnly = true)
	public C_SI getC_SI(int UID_SI) {
		C_SI registro = DAO.getSystem(UID_SI); 
		return DAO.getSystem(UID_SI);
	}
			
	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<C_SI> getList(){

		return DAO.getList();
	}
	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<C_SI> getListExternos(){

		return DAO.getListExternos();
	}
	/**
	 * Create new Contact/Contacts
	 * @param data - json data from request
	 * @return created contacts
	 */
	@Transactional
	public List<C_SI> create(Object data){
		
        List<C_SI> newContacts = new ArrayList<C_SI>();
		
		List<C_SI> list = util.getContactsFromRequest(data);
		
		for (C_SI x : list){
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
	public List<C_SI> update(Object data){
		
		List<C_SI> returnContacts = new ArrayList<C_SI>();
		
		List<C_SI> updatedContacts = util.getContactsFromRequest(data);
		
		for (C_SI x : updatedContacts){
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
	public void setDAO(C_SI_DAO dao) {
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
