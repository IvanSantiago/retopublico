package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.GRLMUNICIPIO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.GRLMUNICIPIO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEGRLMUNICIPIO_PK;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * GRLMUNICIPIO POJO
 * 
 * @author Ivan Santiago
 *
 */
@Repository
public class GRLMUNICIPIO_DAO implements IGRLMUNICIPIO_DAO{
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	@Qualifier("sessionFactory_mimappir_POSGIS")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	/**
	 * Get List of GRLMUNICIPIO from database
	 * @return list of all GRLMUNICIPIO
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GRLMUNICIPIO> getList() {
		return hibernateTemplate.find("from GRLMUNICIPIO");
	}
	/**
	 * Get List of GRLMUNICIPIO from database
	 * @return list of all GRLMUNICIPIO
	 */
	@SuppressWarnings("unchecked")
	public List<GRLMUNICIPIO> getGRLMUNICIPIO(int ICVEENTIDADFED) {
		return hibernateTemplate.find("from GRLMUNICIPIO where ICVEENTIDADFED = ?",ICVEENTIDADFED);
	}
	
	/**
	 * Delete a GRLMUNICIPIO with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(ICVEGRLMUNICIPIO_PK id){
		Object record = hibernateTemplate.load(GRLMUNICIPIO.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new GRLMUNICIPIO on the database or
	 * Update GRLMUNICIPIO
	 * @param GRLMUNICIPIO
	 * @return GRLMUNICIPIO added or updated in DB
	 */
	@Override
	public GRLMUNICIPIO save(GRLMUNICIPIO obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}
	
	public Integer getKey(GRLMUNICIPIO newobj){
			Criteria crit = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(GRLMUNICIPIO.class);
			crit.setProjection(Projections.max("ICVEGRLMUNICIPIO_PK.ICVEMUNICIPIO"));
			crit.add(Restrictions.eq( "ICVEGRLMUNICIPIO_PK.ICVEPAIS", newobj.getICVEGRLMUNICIPIO_PK().getICVEPAIS()));
			crit.add(Restrictions.eq( "ICVEGRLMUNICIPIO_PK.ICVEENTIDADFED", newobj.getICVEGRLMUNICIPIO_PK().getICVEENTIDADFED()));
			return (Integer) crit.uniqueResult();
	}

}
