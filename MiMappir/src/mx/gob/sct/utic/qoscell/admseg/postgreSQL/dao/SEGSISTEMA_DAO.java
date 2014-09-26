package mx.gob.sct.utic.qoscell.admseg.postgreSQL.dao;

import java.util.Iterator;
import java.util.List;

import mx.gob.sct.utic.qoscell.admseg.postgreSQL.model.SEGSISTEMA;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * SEGSISTEMA DAO
 * 
 * @author Ivan Santiago
 *
 */
@Repository
public class SEGSISTEMA_DAO implements ISEGSISTEMA_DAO{
	
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
	public List<SEGSISTEMA> getList() {
		return hibernateTemplate.find("FROM SEGSISTEMA SEGSISTEMA");	
	}
	
	/**
	 * Get List of contacts from database
	 * @return SEGSISTEMA
	 */
	@SuppressWarnings("unchecked")
	public SEGSISTEMA getSistema(Short ICVESISTEMA) {
		List<SEGSISTEMA> registros = hibernateTemplate.find("FROM SEGSISTEMA SEGSISTEMA where SEGSISTEMA.ICVESEGSISTEMA_PK.ICVESISTEMA = ?",ICVESISTEMA);	
		Iterator<SEGSISTEMA> it = registros.iterator();
		SEGSISTEMA segsistema = null;
		while(it.hasNext()){
			segsistema = it.next();
		}
		return segsistema;	
	}
	
	/**
	 * Delete a contact with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(int id){
		Object record = hibernateTemplate.load(SEGSISTEMA.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new Contact on the database or
	 * Update contact
	 * @param contact
	 * @return contact added or updated in DB
	 */
	@Override
	public SEGSISTEMA save(SEGSISTEMA object){
		hibernateTemplate.saveOrUpdate(object);
		return object;
	}

}
