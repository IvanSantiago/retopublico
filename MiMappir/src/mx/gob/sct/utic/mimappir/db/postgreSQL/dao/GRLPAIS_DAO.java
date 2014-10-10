package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.GRLPAIS;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEGRLPAIS_PK;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * GRLPAIS POJO
 * 
 * @author Ivan Santiago
 *
 */
@Repository
public class GRLPAIS_DAO implements IGRLPAIS_DAO{
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	@Qualifier("sessionFactory_mimappir_POSGIS")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	/**
	 * Get List of GRLPAIS from database
	 * @return list of all GRLPAIS
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GRLPAIS> getList() {
		return hibernateTemplate.find("from GRLPAIS");
	}
	/**
	 * Get List of GRLPAIS from database
	 * @return list of all GRLPAIS
	 */
	@SuppressWarnings("unchecked")
	public List<GRLPAIS> getList(int ICVEPAIS) {
		return hibernateTemplate.find("from GRLPAIS where ICVEPAIS = ?",ICVEPAIS);
	}
	
	/**
	 * Delete a GRLPAIS with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(ICVEGRLPAIS_PK id){
		Object record = hibernateTemplate.load(GRLPAIS.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new GRLPAIS on the database or
	 * Update GRLPAIS
	 * @param GRLPAIS
	 * @return GRLPAIS added or updated in DB
	 */
	@Override
	public GRLPAIS save(GRLPAIS obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}
	
	public Integer getKey(GRLPAIS newobj){
			Criteria crit = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(GRLPAIS.class);
			crit.setProjection(Projections.max("ICVEGRLPAIS_PK.ICVEPAIS"));
			return (Integer) crit.uniqueResult();
	}

}
