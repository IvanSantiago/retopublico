package mx.gob.sct.utic.mimappir.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.sigtic.db2.model.S_PROCESOS;

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
public class S_PROCESOS_DAO implements IS_PROCESOS_DAO{
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	@Qualifier("sessionFactorySIGTICDB2")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<S_PROCESOS> getList() {
		return hibernateTemplate.find("from S_PROCESOS");
	}
	/**
	 * Get List of contacts from database
	 * @return list of all S_PROCESOS with specific UID_PROCESO
	 */
	@SuppressWarnings("unchecked")
	public List<S_PROCESOS> getS_PROCESOS(int UID_PROCESO) {		
		return hibernateTemplate.find("from S_PROCESOS where UID_PROCESO = ?",UID_PROCESO);
	}
	/**
	 * Delete a contact with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(int id){
		Object record = hibernateTemplate.load(S_PROCESOS.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new Contact on the database or
	 * Update contact
	 * @param contact
	 * @return contact added or updated in DB
	 */
	@Override
	public S_PROCESOS save(S_PROCESOS obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}

}
