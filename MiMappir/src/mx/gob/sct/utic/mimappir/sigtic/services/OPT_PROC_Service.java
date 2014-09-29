package mx.gob.sct.utic.mimappir.sigtic.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.sigtic.db2.dao.OPT_PROC_DAO;
import mx.gob.sct.utic.mimappir.sigtic.db2.model.OPT_PROC;
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
public class OPT_PROC_Service {
	
	private OPT_PROC_DAO DAO;
	private Util util;

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<OPT_PROC> getList(){

		return DAO.getList();
	}
	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public OPT_PROC getOPT_PROC(int UID_OPTP){
		List<OPT_PROC> processes = DAO.getOPT_PROC(UID_OPTP);
		OPT_PROC findedProcess = null;
		// Search user based on the parameters
		for (OPT_PROC proceso : processes) {
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
	public List<OPT_PROC> create(Object data){
		
        List<OPT_PROC> newContacts = new ArrayList<OPT_PROC>();
		
		//List<OPT_PROC> list = util.getContactsFromRequest(data);
		
		for (OPT_PROC x : newContacts){
			newContacts.add(DAO.save(x));
		}
		
		return newContacts;
	}
	
	/**
	 * Create new OPT_PROC
	 * @param data - OPT_PROC Object
	 * @return created OPT_PROC Object
	 */
	@Transactional
	public OPT_PROC create(OPT_PROC OPT_PROC){				
		return DAO.save(OPT_PROC);
	}
	
	
	/**
	 * Update contact/contacts
	 * @param data - json data from request
	 * @return updated contacts
	 */
	@Transactional
	public List<OPT_PROC> update(Object data){
		
		List<OPT_PROC> returnContacts = new ArrayList<OPT_PROC>();
		
		//List<OPT_PROC> updatedContacts = util.getContactsFromRequest(data);
		
		for (OPT_PROC x : returnContacts){
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
	public void setDAO(OPT_PROC_DAO dao) {
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
