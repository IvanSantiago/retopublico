package mx.gob.sct.utic.mimappir.sigtic.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.sigtic.db2.dao.USUARIOS_DAO;
import mx.gob.sct.utic.mimappir.sigtic.db2.model.USUARIOS;
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
public class USUARIOS_Service {
	
	private USUARIOS_DAO DAO;
	private Util util;

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<USUARIOS> getUsuariosList(){

		return DAO.getList();
	}
	
	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<USUARIOS> getUsuario(String Login){
		return DAO.getUser(Login);
	}
	
	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public USUARIOS getNewestUser(String Login){
		USUARIOS newestuser = DAO.getNewestUser(Login); 
		if(newestuser == null){
			newestuser = new USUARIOS();
			newestuser.setUID_UA(70);// SIN UNIDAD ADMINISTRATIVA en base de datos
			newestuser.setUID_EMPLEADO(9999999);// SIN INFORMACION en la base datos
		}
		return newestuser;
		
	}
	
	/**
	 * Create new Contact/Contacts
	 * @param data - json data from request
	 * @return created contacts
	 */
	@Transactional
	public List<USUARIOS> create(Object data){
		
        List<USUARIOS> newContacts = new ArrayList<USUARIOS>();
		
		List<USUARIOS> list = null;//util.getContactsFromRequest(data);
		
		for (USUARIOS x : list){
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
	public List<USUARIOS> update(Object data){
		
		List<USUARIOS> returnContacts = new ArrayList<USUARIOS>();
		
		List<USUARIOS> updatedContacts = null;//util.getContactsFromRequest(data);
		
		for (USUARIOS x : updatedContacts){
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
	public void setDAO(USUARIOS_DAO dao) {
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
