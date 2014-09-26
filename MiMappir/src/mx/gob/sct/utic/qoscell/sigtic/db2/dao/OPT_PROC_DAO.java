package mx.gob.sct.utic.qoscell.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.qoscell.sigtic.db2.model.OPT_PROC;

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
public class OPT_PROC_DAO implements IOPT_PROC_DAO{
	
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
	public List<OPT_PROC> getList() {
		return hibernateTemplate.find("from OPT_PROC");
	}
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<OPT_PROC> getOPT_PROC(Integer UID_OPTP) {
		return hibernateTemplate.find("from OPT_PROC where UID_OPTP = ?",UID_OPTP);
	}
	/**
	 * Delete a contact with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(int id){
		Object record = hibernateTemplate.load(OPT_PROC.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new Contact on the database or
	 * Update contact
	 * @param contact
	 * @return contact added or updated in DB
	 */
	@Override
	public OPT_PROC save(OPT_PROC obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}

}
