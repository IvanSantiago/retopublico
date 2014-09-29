package mx.gob.sct.utic.mimappir.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.sigtic.db2.model.R_PROCESOS;

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
public class R_PROCESOS_DAO implements IR_PROCESOS_DAO{
	
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
	public List<R_PROCESOS> getSolicitudInfo(Integer UID_R_PROCESO) {
		return hibernateTemplate.find("R_PROCESOS A " +
				"left outer join S_PROCESOS B on (A.UID_PROCESO=B.UID_PROCESO) " +
				"left outer join OPT_PROC C on (A.UID_EMPLEADO_SOL=C.UID_OPTP) " +
				"left outer join C_01_04_UA D on (A.UID_UA=D.UID_UA) " +
				"where A.UID_R_PROCESO = ?",UID_R_PROCESO);
	}
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<R_PROCESOS> getList() {
		return hibernateTemplate.find("from R_PROCESOS");
	}
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<R_PROCESOS> getR_PROCESOS(int UID_R_PROCESO) {		
		return hibernateTemplate.find("from R_PROCESOS where UID_R_PROCESO = ?",UID_R_PROCESO);
	}

	/**
	 * Delete a contact with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(int id){
		Object record = hibernateTemplate.load(R_PROCESOS.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new Contact on the database or
	 * Update contact
	 * @param contact
	 * @return contact added or updated in DB
	 */
	@Override
	public R_PROCESOS save(R_PROCESOS obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}

}
