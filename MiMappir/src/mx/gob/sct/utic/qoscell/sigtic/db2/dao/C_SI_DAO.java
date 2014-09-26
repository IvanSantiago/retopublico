package mx.gob.sct.utic.qoscell.sigtic.db2.dao;

import java.util.Iterator;
import java.util.List;

import mx.gob.sct.utic.qoscell.sigtic.db2.model.C_SI;

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
public class C_SI_DAO implements IC_SI_DAO{
	
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
	public C_SI getSystem(int UID_SI) {
		List<C_SI> registros = hibernateTemplate.find("SELECT sistema FROM C_SI sistema where sistema.UID_SI = ?",UID_SI);	
		Iterator<C_SI> it = registros.iterator();
		C_SI registro = null;
		while(it.hasNext()){
			registro = it.next();
		}
		return registro;
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<C_SI> getList() {
		return hibernateTemplate.find("from C_SI");
	}
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<C_SI> getListExternos() {
		String tieneUsuariosExternos = "SI";
		return hibernateTemplate.find("from C_SI sistema where sistema.HASEXTUSERS = ?",tieneUsuariosExternos);
	}
	/**
	 * Delete a contact with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(int id){
		Object record = hibernateTemplate.load(C_SI.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new Contact on the database or
	 * Update contact
	 * @param contact
	 * @return contact added or updated in DB
	 */
	@Override
	public C_SI save(C_SI sistema){
		hibernateTemplate.saveOrUpdate(sistema);
		return sistema;
	}

}
