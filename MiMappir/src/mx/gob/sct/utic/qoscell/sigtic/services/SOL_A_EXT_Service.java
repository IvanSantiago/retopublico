package mx.gob.sct.utic.qoscell.sigtic.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.qoscell.sigtic.db2.dao.SOL_A_EXT_DAO;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.C_01_04_UA;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.SOL_A_EXT;
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
public class SOL_A_EXT_Service {
	
	private SOL_A_EXT_DAO DAO;
	private Util util;

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<SOL_A_EXT> getList(){

		return DAO.getList();
	}
	/**
	 * Get specific SOL_A_EXT
	 * @return
	 */
	@Transactional(readOnly=true)
	public SOL_A_EXT getSOL_A_EXTofUID_R_PROCESO(int UID_R_PROCESO){
		List<SOL_A_EXT> processes = DAO.getSOL_A_EXTofUID_R_PROCESO(UID_R_PROCESO);
		SOL_A_EXT findedProcess = null;
		// Search user based on the parameters
		for (SOL_A_EXT proceso : processes) {
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
	public List<SOL_A_EXT> create(Object data){
		
        List<SOL_A_EXT> newContacts = new ArrayList<SOL_A_EXT>();
		
		//List<SOL_A_EXT> list = util.getContactsFromRequest(data);
		
		for (SOL_A_EXT x : newContacts){
			newContacts.add(DAO.save(x));
		}
		
		return newContacts;
	}
	
	/**
	 * Create new SOL_A_EXT
	 * @param data - SOL_A_EXT Object
	 * @return created SOL_A_EXT Object
	 */
	@Transactional
	public SOL_A_EXT create(SOL_A_EXT SOL_A_EXT){				
		return DAO.save(SOL_A_EXT);
	}
	
	
	/**
	 * Update contact/contacts
	 * @param data - json data from request
	 * @return updated contacts
	 */
	@Transactional
	public List<SOL_A_EXT> update(Object data){
		
		List<SOL_A_EXT> returnContacts = new ArrayList<SOL_A_EXT>();
		
		//List<SOL_A_EXT> updatedContacts = util.getContactsFromRequest(data);
		
		for (SOL_A_EXT x : returnContacts){
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
	public void setDAO(SOL_A_EXT_DAO dao) {
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
