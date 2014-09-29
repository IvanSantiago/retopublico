package mx.gob.sct.utic.mimappir.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.sigtic.db2.model.DOC_PROC;

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
public class DOC_PROC_DAO implements IDOC_PROC_DAO{
	
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
	public List<DOC_PROC> getList() {
		return hibernateTemplate.find("from DOC_PROC");
	}
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<DOC_PROC> getDOC_PROC(Integer UID_DOC) {
		return hibernateTemplate.find("from DOC_PROC where UID_DOC = ?",UID_DOC);
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<DOC_PROC> getDOC_PROCofR_UID_PROCESOS(int UID_R_PROCESOS) {
		return hibernateTemplate.find("from DOC_PROC where UID_R_PROCESO = ?", UID_R_PROCESOS);
	}
	
	/**
	 * Delete a contact with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(int id){
		Object record = hibernateTemplate.load(DOC_PROC.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new Contact on the database or
	 * Update contact
	 * @param contact
	 * @return contact added or updated in DB
	 */
	@Override
	public DOC_PROC save(DOC_PROC obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}

}
