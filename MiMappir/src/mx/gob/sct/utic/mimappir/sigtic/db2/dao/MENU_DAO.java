package mx.gob.sct.utic.mimappir.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.sigtic.db2.model.MENU;

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
public class MENU_DAO implements IMENU_DAO{
	
	private HibernateTemplate hibernateTemplate;
	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;

	@Autowired
	@Qualifier("sessionFactorySIGTICDB2")
	public void setSessionFactory(SessionFactory sessionFactory) {
		sessionFactory = sessionFactory;
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MENU> getList() {
		return hibernateTemplate.find("FROM MENU");
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MENU> getMenuListForUser(String LOGIN) {
		return hibernateTemplate.find("select menu FROM USUARIOS usuarios, MENU menu where usuarios.LOGIN = ? and menu.NIVEL_U <= usuarios.NIVEL order by menu.NIVEL asc, menu.ID",LOGIN);
	}
	

	/**
	 * Delete a contact with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(int id){
		Object record = hibernateTemplate.load(MENU.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new Contact on the database or
	 * Update contact
	 * @param contact
	 * @return contact added or updated in DB
	 */
	@Override
	public MENU save(MENU object){
		hibernateTemplate.saveOrUpdate(object);
		return object;
	}

}
