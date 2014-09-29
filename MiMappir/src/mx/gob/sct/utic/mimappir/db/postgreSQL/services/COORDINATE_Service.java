package mx.gob.sct.utic.mimappir.db.postgreSQL.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.dao.COORDINATE_DAO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.COORDINATE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * COORDINATE Service
 * 
 * @author Ivan Santiago
 * 
 */
@Service
public class COORDINATE_Service {
	
	private COORDINATE_DAO DAO;

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<COORDINATE> getList(){

		return DAO.getList();
	}

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public COORDINATE getCOORDINATEofUA(int UID_UA){
		List<COORDINATE> processes = DAO.getCOORDINATEofUA(UID_UA);
		COORDINATE findedProcess = null;
		// Search user based on the parameters
		for (COORDINATE proceso : processes) {
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
	public List<COORDINATE> create(Object data){
		
        List<COORDINATE> newContacts = new ArrayList<COORDINATE>();
		
		//List<COORDINATE> list = util.getContactsFromRequest(data);
		
		for (COORDINATE x : newContacts){
			newContacts.add(DAO.save(x));
		}
		
		return newContacts;
	}
	
	/**
	 * Create new COORDINATE
	 * @param data - COORDINATE Object
	 * @return created COORDINATE Object
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public COORDINATE create(COORDINATE COORDINATE){				
		return DAO.save(COORDINATE);
	}
	
	
	/**
	 * Update contact/contacts
	 * @param data - json data from request
	 * @return updated contacts
	 */
	@Transactional(value="transactionManager_mimappir_POSGIS",readOnly=true)
	public List<COORDINATE> update(Object data){
		
		List<COORDINATE> returnContacts = new ArrayList<COORDINATE>();
		
		//List<COORDINATE> updatedContacts = util.getContactsFromRequest(data);
		
		for (COORDINATE x : returnContacts){
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
	public void setDAO(COORDINATE_DAO dao) {
		this.DAO = dao;
	}


	
}
