package mx.gob.sct.utic.qoscell.db.postgreSQL.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.qoscell.db.postgreSQL.dao.QOSOPERADORES_DAO;
import mx.gob.sct.utic.qoscell.db.postgreSQL.model.QOSOPERADORES;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * QOSOPERADORES Service
 * 
 * @author Ivan Santiago
 * 
 */
@Service
public class QOSOPERADORES_Service {
	
	private QOSOPERADORES_DAO DAO;

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(value="transactionManager_qoscell_POSGIS")
	public List<QOSOPERADORES> getList(){
		return DAO.getList();
	}
	
	/**
	 * Get senales dentro de un radio de ubicaciones
	 * @return
	 */
	@Transactional(value="transactionManager_qoscell_POSGIS")
	public List<QOSOPERADORES> getListSenalInto(double latitud, double longitud, double radio){
		return DAO.findSenales( latitud,  longitud,  radio);
	}

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(value="transactionManager_qoscell_POSGIS",readOnly=true)
	public QOSOPERADORES getQOSOPERADORESofUA(int UID_UA){
		List<QOSOPERADORES> processes = DAO.getQOSOPERADORESofUA(UID_UA);
		QOSOPERADORES findedProcess = null;
		// Search user based on the parameters
		for (QOSOPERADORES proceso : processes) {
			findedProcess= proceso;
		}
		return findedProcess;
	}
	
	/**
	 * Create new Contact/Contacts
	 * @param data - json data from request
	 * @return created contacts
	 */
	@Transactional(value="transactionManager_qoscell_POSGIS",readOnly=true)
	public List<QOSOPERADORES> create(Object data){
		
        List<QOSOPERADORES> newContacts = new ArrayList<QOSOPERADORES>();
		
		//List<QOSOPERADORES> list = util.getContactsFromRequest(data);
		
		for (QOSOPERADORES x : newContacts){
			newContacts.add(DAO.save(x));
		}
		
		return newContacts;
	}
	
	/**
	 * Create new Contact/Contacts
	 * @param data - json data from request
	 * @return created contacts
	 */
	@Transactional(value="transactionManager_qoscell_POSGIS")
	public List<QOSOPERADORES> create(List<QOSOPERADORES> toSaveRegistry){
		List<QOSOPERADORES> newsRegistry = new ArrayList<QOSOPERADORES>();
		
		for (QOSOPERADORES x : toSaveRegistry){
			newsRegistry.add(DAO.save(x));
		}		
		return newsRegistry;
	}
	
	/**
	 * Create new QOSOPERADORES
	 * @param data - QOSOPERADORES Object
	 * @return created QOSOPERADORES Object
	 */
	@Transactional(value="transactionManager_qoscell_POSGIS",readOnly=true)
	public QOSOPERADORES create(QOSOPERADORES QOSOPERADORES){				
		return DAO.save(QOSOPERADORES);
	}
	
	
	/**
	 * Update contact/contacts
	 * @param data - json data from request
	 * @return updated contacts
	 */
	@Transactional(value="transactionManager_qoscell_POSGIS",readOnly=true)
	public List<QOSOPERADORES> update(Object data){
		
		List<QOSOPERADORES> returnContacts = new ArrayList<QOSOPERADORES>();
		
		//List<QOSOPERADORES> updatedContacts = util.getContactsFromRequest(data);
		
		for (QOSOPERADORES x : returnContacts){
			returnContacts.add(DAO.save(x));
		}
		
		return returnContacts;
	}
	
	/**
	 * Delete contact/contacts
	 * @param data - json data from request
	 */
	@Transactional(value="transactionManager_qoscell_POSGIS",readOnly=true)
	public void delete(Object data){
		
		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){
			
			List<Integer> deleteContacts = null;
			
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
	public void setDAO(QOSOPERADORES_DAO dao) {
		this.DAO = dao;
	}


	
}
