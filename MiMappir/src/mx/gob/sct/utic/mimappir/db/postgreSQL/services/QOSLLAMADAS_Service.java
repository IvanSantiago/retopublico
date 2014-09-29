package mx.gob.sct.utic.mimappir.db.postgreSQL.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.dao.QOSLLAMADAS_DAO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.QOSLLAMADAS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * QOSLLAMADAS Service
 * 
 * @author Ivan Santiago
 * 
 */
@Service
public class QOSLLAMADAS_Service {
	
	private QOSLLAMADAS_DAO DAO;

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<QOSLLAMADAS> getList(){
		return DAO.getList();
	}
	
	/**
	 * Get senales dentro de un radio de ubicaciones
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<QOSLLAMADAS> getListSenalInto(double latitud, double longitud, double radio){
		return DAO.findSenales( latitud,  longitud,  radio);
	}

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public QOSLLAMADAS getQOSLLAMADASofUA(int UID_UA){
		List<QOSLLAMADAS> processes = DAO.getQOSLLAMADASofUA(UID_UA);
		QOSLLAMADAS findedProcess = null;
		// Search user based on the parameters
		for (QOSLLAMADAS proceso : processes) {
			findedProcess= proceso;
		}
		return findedProcess;
	}
	
	/**
	 * Create new Contact/Contacts
	 * @param data - json data from request
	 * @return created contacts
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public List<QOSLLAMADAS> create(Object data){
		
        List<QOSLLAMADAS> newContacts = new ArrayList<QOSLLAMADAS>();
		
		//List<QOSLLAMADAS> list = util.getContactsFromRequest(data);
		
		for (QOSLLAMADAS x : newContacts){
			newContacts.add(DAO.save(x));
		}
		
		return newContacts;
	}
	
	/**
	 * Create new Contact/Contacts
	 * @param data - json data from request
	 * @return created contacts
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<QOSLLAMADAS> create(List<QOSLLAMADAS> toSaveRegistry){
		List<QOSLLAMADAS> newsRegistry = new ArrayList<QOSLLAMADAS>();
		
		for (QOSLLAMADAS x : toSaveRegistry){
			newsRegistry.add(DAO.save(x));
		}		
		return newsRegistry;
	}
	
	/**
	 * Create new QOSLLAMADAS
	 * @param data - QOSLLAMADAS Object
	 * @return created QOSLLAMADAS Object
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public QOSLLAMADAS create(QOSLLAMADAS QOSLLAMADAS){				
		return DAO.save(QOSLLAMADAS);
	}
	
	
	/**
	 * Update contact/contacts
	 * @param data - json data from request
	 * @return updated contacts
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public List<QOSLLAMADAS> update(Object data){
		
		List<QOSLLAMADAS> returnContacts = new ArrayList<QOSLLAMADAS>();
		
		//List<QOSLLAMADAS> updatedContacts = util.getContactsFromRequest(data);
		
		for (QOSLLAMADAS x : returnContacts){
			returnContacts.add(DAO.save(x));
		}
		
		return returnContacts;
	}
	
	/**
	 * Delete contact/contacts
	 * @param data - json data from request
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
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
	public void setDAO(QOSLLAMADAS_DAO dao) {
		this.DAO = dao;
	}


	
}
