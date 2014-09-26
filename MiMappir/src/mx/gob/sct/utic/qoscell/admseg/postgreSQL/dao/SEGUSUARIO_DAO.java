package mx.gob.sct.utic.qoscell.admseg.postgreSQL.dao;

import java.util.Iterator;
import java.util.List;

import mx.gob.sct.utic.qoscell.admseg.postgreSQL.model.SEGUSUARIO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * SEGUSUARIO DAO
 * 
 * @author Ivan Santiago
 *
 */
@Repository
public class SEGUSUARIO_DAO implements ISEGUSUARIO_DAO{
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	@Qualifier("sessionFactory_ADMSEG_POSGIS")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	/**
	 * Retrieval of data from database of specific user.
	 */
	public SEGUSUARIO searchDatabase(String username) {
		// Retrieve all users from the database
		List<SEGUSUARIO> users = getUser(username);
		//List<SEGUSUARIO> users = getList();

		// Search user based on the parameters
		for (SEGUSUARIO dbUser : users) {
			if (dbUser.getCUSUARIO().equals(username) == true) {
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
	public List<SEGUSUARIO> getList() {
		return hibernateTemplate.find("FROM SEGUSUARIO SEGUSUARIO");	
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<SEGUSUARIO> getUser(String LOGIN) {
		return hibernateTemplate.find("FROM SEGUSUARIO SEGUSUARIO where SEGUSUARIO.CUSUARIO = ?",LOGIN);	
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public SEGUSUARIO getNewestUser(String LOGIN) {
		List<SEGUSUARIO> registros = hibernateTemplate.find("FROM SEGUSUARIO SEGUSUARIO where SEGUSUARIO.CUSUARIO = ? order by ICVEUSUARIO",LOGIN);	
		Iterator<SEGUSUARIO> it = registros.iterator();
		SEGUSUARIO dbUser = null;
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
		Object record = hibernateTemplate.load(SEGUSUARIO.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new Contact on the database or
	 * Update contact
	 * @param contact
	 * @return contact added or updated in DB
	 */
	@Override
	public SEGUSUARIO save(SEGUSUARIO object){
		hibernateTemplate.saveOrUpdate(object);
		return object;
	}

}
