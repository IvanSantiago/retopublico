package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMSEGUIMIENTOSGEO;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SEGUIMIENTOSGEO_DAO implements ISEGUIMIENTOSGEO_DAO{
	private HibernateTemplate hibernateTemplate;

	@Autowired
	@Qualifier("sessionFactory_mimappir_POSGIS")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	/**
	 * Get List of SEGUIMIENTOSGEO from database
	 * @return list of all MMSEGUIMIENTOSGEO
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MMSEGUIMIENTOSGEO> getList() {		
		return hibernateTemplate.find("from MMSEGUIMIENTOSGEO");
	}
	/**
	 * Get List of SEGUIMIENTOSGEO from database
	 * @return list of all MMSEGUIMIENTOSGEO
	 */
	@SuppressWarnings("unchecked")
	public List<MMSEGUIMIENTOSGEO> getList(int icvesenal) {
		return hibernateTemplate.find("from MMSEGUIMIENTOSGEO where icvesenal = ?",icvesenal);
	}
	
	/**
	 * Delete a MMSEGUIMIENTOSGEO with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(Integer id){
		Object record = hibernateTemplate.load(MMSEGUIMIENTOSGEO.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new MMSEGUIMIENTOSGEO on the database or
	 * Update MMSEGUIMIENTOSGEO
	 * @param MMSEGUIMIENTOSGEO
	 * @return MMSEGUIMIENTOSGEO added or updated in DB
	 */
	@Override
	public MMSEGUIMIENTOSGEO save(MMSEGUIMIENTOSGEO obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}
	
	public Integer getKey(MMSEGUIMIENTOSGEO newobj){
			Criteria crit = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(MMSEGUIMIENTOSGEO.class);
			crit.setProjection(Projections.max("icvesenal"));
			return (Integer) crit.uniqueResult();
	}
	
}
