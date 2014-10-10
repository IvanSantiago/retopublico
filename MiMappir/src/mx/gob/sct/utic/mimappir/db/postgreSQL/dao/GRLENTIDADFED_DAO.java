package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.GRLENTIDADFED;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEGRLENTIDADFED_PK;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * GRLENTIDADFED POJO
 * 
 * @author Ivan Santiago
 *
 */
@Repository
public class GRLENTIDADFED_DAO implements IGRLENTIDADFED_DAO{
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	@Qualifier("sessionFactory_mimappir_POSGIS")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	/**
	 * Get List of GRLENTIDADFED from database
	 * @return list of all GRLENTIDADFED
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GRLENTIDADFED> getList() {
		return hibernateTemplate.find("from GRLENTIDADFED");
	}
	/**
	 * Get List of GRLENTIDADFED from database
	 * @return list of all GRLENTIDADFED
	 */
	@SuppressWarnings("unchecked")
	public List<GRLENTIDADFED> getGRLENTIDADFED(int ICVEENTIDADFED) {
		return hibernateTemplate.find("from GRLENTIDADFED where ICVEENTIDADFED = ?",ICVEENTIDADFED);
	}
	
	/**
	 * Delete a GRLENTIDADFED with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(ICVEGRLENTIDADFED_PK id){
		Object record = hibernateTemplate.load(GRLENTIDADFED.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new GRLENTIDADFED on the database or
	 * Update GRLENTIDADFED
	 * @param GRLENTIDADFED
	 * @return GRLENTIDADFED added or updated in DB
	 */
	@Override
	public GRLENTIDADFED save(GRLENTIDADFED obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}
	
	public Integer getKey(GRLENTIDADFED newobj){
			Criteria crit = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(GRLENTIDADFED.class);
			crit.setProjection(Projections.max("ICVEGRLENTIDADFED_PK.ICVEENTIDADFED"));
			crit.add(Restrictions.eq( "ICVEGRLENTIDADFED_PK.ICVEPAIS", newobj.getICVEGRLENTIDADFED_PK().getICVEPAIS()));
			return (Integer) crit.uniqueResult();
	}

}
