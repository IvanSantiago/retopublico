package mx.gob.sct.utic.qoscell.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.qoscell.db.postgreSQL.model.COORDINATE;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * COORDINATE DAO
 * 
 * @author Ivan Santiago
 *
 */
@Repository
public class COORDINATE_DAO implements ICOORDINATE_DAO{
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	@Qualifier("sessionFactory_qoscell_POSGIS")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<COORDINATE> getList() {
		return hibernateTemplate.find(" from COORDINATE");
	}
	/**
	 * Get List of contacts from database
	 * @return list of all COORDINATE with specific UID_PROCESO
	 */
	@SuppressWarnings("unchecked")
	public List<COORDINATE> getCOORDINATEofUA(int UID_UA) {		
		return hibernateTemplate.find("from COORDINATE where geom = ?",UID_UA);
	}
	/**
	 * Delete a contact with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(int id){
		Object record = hibernateTemplate.load(COORDINATE.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new Contact on the database or
	 * Update contact
	 * @param contact
	 * @return contact added or updated in DB
	 */
	@Override
	public COORDINATE save(COORDINATE obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}

}
