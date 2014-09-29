package mx.gob.sct.utic.mimappir.sigtic.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.sigtic.db2.dao.OPT_DOCS_DAO;
import mx.gob.sct.utic.mimappir.sigtic.db2.model.OPT_DOCS;
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
public class OPT_DOCS_Service {
	
	private OPT_DOCS_DAO DAO;
	private Util util;

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<OPT_DOCS> getList(){

		return DAO.getList();
	}
	/**
	 * Get specific OPT_DOCS of UID_OPD
	 * @return
	 */
	@Transactional(readOnly=true)
	public OPT_DOCS getOPT_DOCS(int UID_OPD){
		List<OPT_DOCS> processes = DAO.getOPT_DOCS(UID_OPD);
		OPT_DOCS findedProcess = null;
		// Search user based on the parameters
		for (OPT_DOCS proceso : processes) {
			findedProcess= proceso;
		}
		return findedProcess;
	}

	/**
	 * Get OPT_DOCS of UID_OPTP
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<OPT_DOCS> getOPT_DOCSOfUID_OPTP(int UID_OPTP){
		return DAO.getOPT_DOCSOfUID_OPTP(UID_OPTP);
	}
	/**
	 * Create new Contact/Contacts
	 * @param data - json data from request
	 * @return created contacts
	 */
	@Transactional
	public List<OPT_DOCS> create(Object data){
		
        List<OPT_DOCS> newContacts = new ArrayList<OPT_DOCS>();
		
		//List<OPT_DOCS> list = util.getContactsFromRequest(data);
		
		for (OPT_DOCS x : newContacts){
			newContacts.add(DAO.save(x));
		}
		
		return newContacts;
	}
	
	/**
	 * Create new OPT_DOCS
	 * @param data - OPT_DOCS Object
	 * @return created OPT_DOCS Object
	 */
	@Transactional
	public OPT_DOCS create(OPT_DOCS OPT_DOCS){				
		return DAO.save(OPT_DOCS);
	}
	
	
	/**
	 * Update contact/contacts
	 * @param data - json data from request
	 * @return updated contacts
	 */
	@Transactional
	public List<OPT_DOCS> update(Object data){
		
		List<OPT_DOCS> returnContacts = new ArrayList<OPT_DOCS>();
		
		//List<OPT_DOCS> updatedContacts = util.getContactsFromRequest(data);
		
		for (OPT_DOCS x : returnContacts){
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
	public void setDAO(OPT_DOCS_DAO dao) {
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
