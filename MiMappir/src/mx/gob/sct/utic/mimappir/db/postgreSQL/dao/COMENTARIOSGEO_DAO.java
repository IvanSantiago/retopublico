package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMCOMENTARIOSGEO;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class COMENTARIOSGEO_DAO implements ICOMENTARIOSGEO_DAO{
	private HibernateTemplate hibernateTemplate;

	@Autowired
	@Qualifier("sessionFactory_mimappir_POSGIS")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	/**
	 * Get List of COMENTARIOSGEO from database
	 * @return list of all MMCOMENTARIOSGEO
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MMCOMENTARIOSGEO> getList() {		
		return hibernateTemplate.find("from MMCOMENTARIOSGEO");
	}
	/**
	 * Get List of COMENTARIOSGEO from database
	 * @return list of all MMCOMENTARIOSGEO
	 */
	@SuppressWarnings("unchecked")
	public List<MMCOMENTARIOSGEO> getList(int icvecomentario) {
		return hibernateTemplate.find("from MMCOMENTARIOSGEO where icvecomentario = ?",icvecomentario);
	}
	
	/**
	 * Delete a MMCOMENTARIOSGEO with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(Integer id){
		Object record = hibernateTemplate.load(MMCOMENTARIOSGEO.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new MMCOMENTARIOSGEO on the database or
	 * Update MMCOMENTARIOSGEO
	 * @param MMCATEGORIAS
	 * @return MMCOMENTARIOSGEO added or updated in DB
	 */
	@Override
	public MMCOMENTARIOSGEO save(MMCOMENTARIOSGEO obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}
	
	public Integer getKey(MMCOMENTARIOSGEO newobj){
			Criteria crit = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(MMCOMENTARIOSGEO.class);
			crit.setProjection(Projections.max("icvecomentario"));
			return (Integer) crit.uniqueResult();
	}
	
}
