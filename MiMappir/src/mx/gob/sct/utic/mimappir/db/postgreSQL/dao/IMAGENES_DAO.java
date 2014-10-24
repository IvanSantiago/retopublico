package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMIMAGENES;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEIMAGEN_PK;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class IMAGENES_DAO implements IIMAGENES_DAO{
	private HibernateTemplate hibernateTemplate;

	@Autowired
	@Qualifier("sessionFactory_mimappir_POSGIS")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	/**
	 * Get List of IMAGENES from database
	 * @return list of all IMAGENES
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MMIMAGENES> getList() {		
		return hibernateTemplate.find("from MMIMAGENES");
	}
	/**
	 * Get List of IMAGENES from database
	 * @return list of all IMAGENES
	 */
	@SuppressWarnings("unchecked")
	public List<MMIMAGENES> getList(int ICVEIMAGEN) {
		return hibernateTemplate.find("from MMIMAGENES where ICVEIMAGEN = ?",ICVEIMAGEN);
	}
	
	/**
	 * Delete a IMAGENES with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(ICVEIMAGEN_PK id){
		Object record = hibernateTemplate.load(MMIMAGENES.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new IMAGENES on the database or
	 * Update IMAGENES
	 * @param MMIMAGENES
	 * @return IMAGENES added or updated in DB
	 */
	@Override
	public MMIMAGENES save(MMIMAGENES obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}
	
	public Integer getKey(MMIMAGENES newobj){
			Criteria crit = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(MMIMAGENES.class);
			crit.setProjection(Projections.max("ICVEIMAGEN_PK.ICVEIMAGEN"));
			return (Integer) crit.uniqueResult();
	}
	
}
