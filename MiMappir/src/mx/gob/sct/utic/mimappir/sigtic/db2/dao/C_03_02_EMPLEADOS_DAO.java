package mx.gob.sct.utic.mimappir.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.sigtic.db2.model.C_03_02_EMPLEADOS;

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
public class C_03_02_EMPLEADOS_DAO implements IC_03_02_EMPLEADOS_DAO{
	
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
	public List<C_03_02_EMPLEADOS> getList() {
		return hibernateTemplate.find("from C_03_02_EMPLEADOS");
	}
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<C_03_02_EMPLEADOS> getC_03_02_EMPLEADOSofUID_EMPLEADO(int UID_EMPLEADO) {
		return hibernateTemplate.find("from C_03_02_EMPLEADOS where UID_EMPLEADO = ?",UID_EMPLEADO);
	}
	
	/**
	 * Delete a contact with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(int id){
		Object record = hibernateTemplate.load(C_03_02_EMPLEADOS.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new Contact on the database or
	 * Update contact
	 * @param contact
	 * @return contact added or updated in DB
	 */
	@Override
	public C_03_02_EMPLEADOS save(C_03_02_EMPLEADOS obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}

}
