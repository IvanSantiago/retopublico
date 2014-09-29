package mx.gob.sct.utic.mimappir.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.sigtic.db2.model.C_R_UA;

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
public class C_R_UA_DAO implements IC_R_UA_DAO{
	
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
	public List<C_R_UA> getList() {
		return hibernateTemplate.find("from C_R_UA");
	}
	/**
	 * Get List of contacts from database
	 * @return list of all C_R_UA with specific UID_PROCESO
	 */
	@SuppressWarnings("unchecked")
	public List<C_R_UA> getC_R_UAofUA(int UID_UA) {		
		return hibernateTemplate.find("from C_R_UA where UID_UA = ?",UID_UA);
	}
	/**
	 * Delete a contact with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(int id){
		Object record = hibernateTemplate.load(C_R_UA.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new Contact on the database or
	 * Update contact
	 * @param contact
	 * @return contact added or updated in DB
	 */
	@Override
	public C_R_UA save(C_R_UA obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}

}
