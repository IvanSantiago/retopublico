package mx.gob.sct.utic.mimappir.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.sigtic.db2.model.OPT_DOCS;

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
public class OPT_DOCS_DAO implements IOPT_DOCS_DAO{
	
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
	public List<OPT_DOCS> getList() {
		return hibernateTemplate.find("from OPT_DOCS");
	}
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<OPT_DOCS> getOPT_DOCS(Integer UID_OPD) {
		return hibernateTemplate.find("from OPT_DOCS where UID_OPD = ?",UID_OPD);
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<OPT_DOCS> getOPT_DOCSOfUID_OPTP(Integer UID_OPTP) {
		return hibernateTemplate.find("from OPT_DOCS where UID_OPTP = ? order by ORDEN",UID_OPTP);
	}
	
	/**
	 * Delete a contact with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(int id){
		Object record = hibernateTemplate.load(OPT_DOCS.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new Contact on the database or
	 * Update contact
	 * @param contact
	 * @return contact added or updated in DB
	 */
	@Override
	public OPT_DOCS save(OPT_DOCS obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}

}
