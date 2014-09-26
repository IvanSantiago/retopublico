package mx.gob.sct.utic.qoscell.sigtic.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.qoscell.sigtic.db2.dao.DOC_PROC_DAO;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.DOC_PROC;
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
public class DOC_PROC_Service {
	
	private DOC_PROC_DAO DAO;
	private Util util;

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<DOC_PROC> getList(){

		return DAO.getList();
	}
	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public DOC_PROC getDOC_PROC(int UID_OPTP){
		List<DOC_PROC> processes = DAO.getDOC_PROC(UID_OPTP);
		DOC_PROC findedProcess = null;
		// Search user based on the parameters
		for (DOC_PROC proceso : processes) {
			findedProcess= proceso;
		}
		return findedProcess;
	}
	
	/**
	 * Get DOC_PROC of a R_UID_PROCESOS
	 * @return
	 */
	@Transactional(readOnly=true)
	public DOC_PROC getDOC_PROCofR_UID_PROCESOS(int UID_R_PROCESOS){
		List<DOC_PROC> processes = DAO.getDOC_PROCofR_UID_PROCESOS(UID_R_PROCESOS);
		DOC_PROC findedProcess = null;
		// Search user based on the parameters
		for (DOC_PROC proceso : processes) {
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
	public List<DOC_PROC> create(Object data){
		
        List<DOC_PROC> newContacts = new ArrayList<DOC_PROC>();
		
		//List<DOC_PROC> list = util.getContactsFromRequest(data);
		
		for (DOC_PROC x : newContacts){
			newContacts.add(DAO.save(x));
		}
		
		return newContacts;
	}
	
	/**
	 * Create new DOC_PROC
	 * @param data - DOC_PROC Object
	 * @return created DOC_PROC Object
	 */
	@Transactional
	public DOC_PROC create(DOC_PROC DOC_PROC){				
		return DAO.save(DOC_PROC);
	}
	
	
	/**
	 * Update contact/contacts
	 * @param data - json data from request
	 * @return updated contacts
	 */
	@Transactional
	public List<DOC_PROC> update(Object data){
		
		List<DOC_PROC> returnContacts = new ArrayList<DOC_PROC>();
		
		//List<DOC_PROC> updatedContacts = util.getContactsFromRequest(data);
		
		for (DOC_PROC x : returnContacts){
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
	public void setDAO(DOC_PROC_DAO dao) {
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
