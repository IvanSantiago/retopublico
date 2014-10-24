package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMPREMIACIONES;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEPREMIACION_PK;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PREMIACIONES_DAO implements IPREMIACIONES_DAO{

	private HibernateTemplate hibernateTemplate;

	@Autowired
	@Qualifier("sessionFactory_mimappir_POSGIS")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	/**
	 * Get List of PREMIACIONES from database
	 * @return list of all PREMIACIONES
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MMPREMIACIONES> getList() {		
		return hibernateTemplate.find("from MMPREMIACIONES");
	}
	/**
	 * Get List of PREMIACIONES from database
	 * @return list of all PARTICIPANTES
	 */
	@SuppressWarnings("unchecked")
	public List<MMPREMIACIONES> getList(int ICVEPREMIACION) {
		return hibernateTemplate.find("from MMPREMIACIONES where ICVEPREMIACION = ?",ICVEPREMIACION);
	}
	
	/**
	 * Delete a PREMIACIONES with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(ICVEPREMIACION_PK id){
		Object record = hibernateTemplate.load(MMPREMIACIONES.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new PREMIACIONES on the database or
	 * Update PREMIACIONES
	 * @param MMPREMIACIONES
	 * @return PREMIACIONES added or updated in DB
	 */
	@Override
	public MMPREMIACIONES save(MMPREMIACIONES obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}
	
	public Integer getKey(MMPREMIACIONES newobj){
			Criteria crit = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(MMPREMIACIONES.class);
			crit.setProjection(Projections.max("ICVEPREMIACION_PK.ICVEPREMIACION"));
			return (Integer) crit.uniqueResult();
	}
	
}
