package mx.gob.sct.utic.qoscell.sigtic.db2.dao;

import java.util.Iterator;
import java.util.List;

import mx.gob.sct.utic.qoscell.sigtic.db2.model.USUARIOS;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * Contact POJO
 * 
 * @author Ivan Santiago
 *
 */
@Repository
public class USUARIOS_DAO implements IUSUARIOS_DAO{
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	@Qualifier("sessionFactorySIGTICDB2")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	/**
	 * Retrieval of data from database of specific user.
	 */
	public USUARIOS searchDatabase(String username) {
		// Retrieve all users from the database
		//List<USUARIOS> users = getUser(username);
		List<USUARIOS> users = getList();

		// Search user based on the parameters
		for (USUARIOS dbUser : users) {
			if (dbUser.getLOGIN().equals(username) == true) {
					// return matching user
					return dbUser;					
				
			}
		}
		return null;
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<USUARIOS> getList() {
		return hibernateTemplate.find("FROM USUARIOS");	
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<USUARIOS> getUser(String LOGIN) {
		return hibernateTemplate.find("FROM USUARIOS usuarios where usuarios.LOGIN = ?",LOGIN);	
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public USUARIOS getNewestUser(String LOGIN) {
		List<USUARIOS> registros = hibernateTemplate.find("FROM USUARIOS usuarios where usuarios.LOGIN = ? order by UID_USUARIO",LOGIN);	
		Iterator<USUARIOS> it = registros.iterator();
		USUARIOS dbUser = null;
		while(it.hasNext()){
			dbUser = it.next();
		}
		return dbUser;
	}

	/**
	 * Delete a contact with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(int id){
		Object record = hibernateTemplate.load(USUARIOS.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new Contact on the database or
	 * Update contact
	 * @param contact
	 * @return contact added or updated in DB
	 */
	@Override
	public USUARIOS save(USUARIOS object){
		hibernateTemplate.saveOrUpdate(object);
		return object;
	}

}
