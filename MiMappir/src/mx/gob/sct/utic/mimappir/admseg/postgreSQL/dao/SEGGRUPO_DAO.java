package mx.gob.sct.utic.mimappir.admseg.postgreSQL.dao;

import java.util.Iterator;
import java.util.List;

import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGGRUPO;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGUSUARIO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * SEGGRUPO DAO
 * 
 * @author Ivan Santiago
 *
 */
@Repository
public class SEGGRUPO_DAO implements ISEGGRUPO_DAO{
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	@Qualifier("sessionFactory_ADMSEG_POSGIS")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SEGGRUPO> getList() {
		return hibernateTemplate.find("FROM SEGGRUPO SEGGRUPO");	
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<SEGGRUPO> getSEGGRUPO(int LOGIN) {
		return hibernateTemplate.find("FROM SEGGRUPO SEGGRUPO where SEGGRUPO.CUSUARIO = ?",LOGIN);	
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public SEGGRUPO getNewestUser(String LOGIN) {
		List<SEGGRUPO> registros = hibernateTemplate.find("FROM SEGGRUPO SEGGRUPO where SEGGRUPO.CUSUARIO = ? order by ICVEUSUARIO",LOGIN);	
		Iterator<SEGGRUPO> it = registros.iterator();
		SEGGRUPO dbUser = null;
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
		Object record = hibernateTemplate.load(SEGGRUPO.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new Contact on the database or
	 * Update contact
	 * @param contact
	 * @return contact added or updated in DB
	 */
	@Override
	public SEGGRUPO save(SEGGRUPO object){
		hibernateTemplate.saveOrUpdate(object);
		return object;
	}

}
