package mx.gob.sct.utic.mimappir.db.postgreSQL.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.dao.QOSCSENALGEO_DAO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.QOSCSENALGEO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * QOSCSENALGEO Service
 * 
 * @author Ivan Santiago
 * 
 */
@Service
public class QOSCSENALGEO_Service {
	
	private QOSCSENALGEO_DAO DAO;

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<QOSCSENALGEO> getList(){
		return DAO.getList();
	}
	
	/**
	 * Get senales dentro de un radio de ubicaciones
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<QOSCSENALGEO> getListSenalInto(double latitud, double longitud, double radio){
		return DAO.findSenales( latitud,  longitud,  radio);
	}
	
	/**
	 * Get senales dentro de un radio de ubicaciones
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS")
	public List<QOSCSENALGEO> getListSenalInfoOperator(double latitud, double longitud, double radio, int icveoperator){
		return DAO.findSenales( latitud,  longitud,  radio, icveoperator);
	}

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public QOSCSENALGEO getQOSCSENALGEOofUA(int UID_UA){
		List<QOSCSENALGEO> processes = DAO.getQOSCSENALGEOofUA(UID_UA);
		QOSCSENALGEO findedProcess = null;
		// Search user based on the parameters
		for (QOSCSENALGEO proceso : processes) {
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
	public List<QOSCSENALGEO> create(Object data){
		
        List<QOSCSENALGEO> newContacts = new ArrayList<QOSCSENALGEO>();
		
		//List<QOSCSENALGEO> list = util.getContactsFromRequest(data);
		
		for (QOSCSENALGEO x : newContacts){
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
	public List<QOSCSENALGEO> create(List<QOSCSENALGEO> toSaveRegistry){
		List<QOSCSENALGEO> newsRegistry = new ArrayList<QOSCSENALGEO>();
		
		for (QOSCSENALGEO x : toSaveRegistry){
			newsRegistry.add(DAO.save(x));
		}		
		return newsRegistry;
	}
	
	/**
	 * Create new QOSCSENALGEO
	 * @param data - QOSCSENALGEO Object
	 * @return created QOSCSENALGEO Object
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public QOSCSENALGEO create(QOSCSENALGEO QOSCSENALGEO){				
		return DAO.save(QOSCSENALGEO);
	}
	
	
	/**
	 * Update contact/contacts
	 * @param data - json data from request
	 * @return updated contacts
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public List<QOSCSENALGEO> update(Object data){
		
		List<QOSCSENALGEO> returnContacts = new ArrayList<QOSCSENALGEO>();
		
		//List<QOSCSENALGEO> updatedContacts = util.getContactsFromRequest(data);
		
		for (QOSCSENALGEO x : returnContacts){
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
	public void setDAO(QOSCSENALGEO_DAO dao) {
		this.DAO = dao;
	}


	
}
