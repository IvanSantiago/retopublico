package mx.gob.sct.utic.qoscell.sigtic.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.qoscell.sigtic.db2.dao.C_03_02_EMPLEADOS_DAO;
import mx.gob.sct.utic.qoscell.sigtic.db2.model.C_03_02_EMPLEADOS;
import mx.gob.sct.utic.qoscell.util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * C_03_02_EMPLEADOS Service
 * 
 * @author Ivan Santiago
 * 
 */
@Service
public class C_03_02_EMPLEADOS_Service {
	
	private C_03_02_EMPLEADOS_DAO DAO;
	private Util util;

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<C_03_02_EMPLEADOS> getList(){

		return DAO.getList();
	}
	/**
	 * Get specific C_03_02_EMPLEADOS
	 * @return
	 */
	@Transactional(readOnly=true)
	public C_03_02_EMPLEADOS getC_03_02_EMPLEADOSofUID_EMPLEADO(int UID_EMPLEADO){
		List<C_03_02_EMPLEADOS> processes = DAO.getC_03_02_EMPLEADOSofUID_EMPLEADO(UID_EMPLEADO);
		C_03_02_EMPLEADOS findedProcess = null;
		// Search user based on the parameters
		for (C_03_02_EMPLEADOS proceso : processes) {
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
	public List<C_03_02_EMPLEADOS> create(Object data){
		
        List<C_03_02_EMPLEADOS> newContacts = new ArrayList<C_03_02_EMPLEADOS>();
		
		//List<C_03_02_EMPLEADOS> list = util.getContactsFromRequest(data);
		
		for (C_03_02_EMPLEADOS x : newContacts){
			newContacts.add(DAO.save(x));
		}
		
		return newContacts;
	}
	
	/**
	 * Create new C_03_02_EMPLEADOS
	 * @param data - C_03_02_EMPLEADOS Object
	 * @return created C_03_02_EMPLEADOS Object
	 */
	@Transactional
	public C_03_02_EMPLEADOS create(C_03_02_EMPLEADOS C_03_02_EMPLEADOS){				
		return DAO.save(C_03_02_EMPLEADOS);
	}
	
	
	/**
	 * Update contact/contacts
	 * @param data - json data from request
	 * @return updated contacts
	 */
	@Transactional
	public List<C_03_02_EMPLEADOS> update(Object data){
		
		List<C_03_02_EMPLEADOS> returnContacts = new ArrayList<C_03_02_EMPLEADOS>();
		
		//List<C_03_02_EMPLEADOS> updatedContacts = util.getContactsFromRequest(data);
		
		for (C_03_02_EMPLEADOS x : returnContacts){
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
	public void setDAO(C_03_02_EMPLEADOS_DAO dao) {
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
