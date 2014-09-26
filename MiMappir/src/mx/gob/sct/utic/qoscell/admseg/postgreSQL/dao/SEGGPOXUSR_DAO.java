package mx.gob.sct.utic.qoscell.admseg.postgreSQL.dao;

import java.util.Iterator;
import java.util.List;

import mx.gob.sct.utic.qoscell.admseg.postgreSQL.model.SEGGPOXUSR;
import mx.gob.sct.utic.qoscell.admseg.postgreSQL.model.SEGSISTEMA;
import mx.gob.sct.utic.qoscell.admseg.postgreSQL.model.SEGUSUARIO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * SEGGPOXUSR DAO
 * 
 * @author Ivan Santiago
 *
 */
@Repository
public class SEGGPOXUSR_DAO implements ISEGGPOXUSR_DAO{
	
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
	public List<SEGGPOXUSR> getList() {
		return hibernateTemplate.find("FROM SEGGPOXUSR SEGGPOXUSR");	
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<SEGGPOXUSR> getSEGGPOXUSR(Long ICVEUSUARIO) {
		return hibernateTemplate.find("FROM SEGGPOXUSR SEGGPOXUSR where SEGGPOXUSR.ICVEUSUARIO = ?",ICVEUSUARIO);	
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<SEGGPOXUSR> getListForUserOnSystem(SEGUSUARIO SEGUSUARIO, SEGSISTEMA SEGSISTEMA) {
		return hibernateTemplate.find(
				" FROM SEGGPOXUSR SEGGPOXUSR " +
				" WHERE " +
				" SEGGPOXUSR.ICVESEGGPOXUSR_PK.ICVEUSUARIO = ? " +
				" AND SEGGPOXUSR.ICVESEGGPOXUSR_PK.ICVESISTEMA = ? "
				,SEGUSUARIO.getICVESEGUSUARIO_PK().getICVEUSUARIO()
				,SEGSISTEMA.getICVESEGSISTEMA_PK().getICVESISTEMA()
				);	
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public SEGGPOXUSR getNewestUser(String LOGIN) {
		List<SEGGPOXUSR> registros = hibernateTemplate.find("FROM SEGGPOXUSR SEGGPOXUSR where SEGGPOXUSR.CUSUARIO = ? order by ICVEUSUARIO",LOGIN);	
		Iterator<SEGGPOXUSR> it = registros.iterator();
		SEGGPOXUSR dbUser = null;
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
		Object record = hibernateTemplate.load(SEGGPOXUSR.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new Contact on the database or
	 * Update contact
	 * @param contact
	 * @return contact added or updated in DB
	 */
	@Override
	public SEGGPOXUSR save(SEGGPOXUSR object){
		hibernateTemplate.saveOrUpdate(object);
		return object;
	}

}
