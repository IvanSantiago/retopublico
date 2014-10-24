package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMPREMIOS;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEPREMIO_PK;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PREMIOS_DAO implements IPREMIOS_DAO{

	private HibernateTemplate hibernateTemplate;

	@Autowired
	@Qualifier("sessionFactory_mimappir_POSGIS")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	/**
	 * Get List of PREMIOS from database
	 * @return list of all PREMIOS
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MMPREMIOS> getList() {		
		return hibernateTemplate.find("from MMPREMIOS");
	}
	/**
	 * Get List of PREMIOS from database
	 * @return list of all PREMIOS
	 */
	@SuppressWarnings("unchecked")
	public List<MMPREMIOS> getList(int ICVEPREMIO) {
		return hibernateTemplate.find("from MMPREMIOS where ICVEPREMIO = ?",ICVEPREMIO);
	}
	
	/**
	 * Delete a PREMIOS with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(ICVEPREMIO_PK id){
		Object record = hibernateTemplate.load(MMPREMIOS.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new PREMIOS on the database or
	 * Update PREMIOS
	 * @param MMPREMIOS
	 * @return PREMIOS added or updated in DB
	 */
	@Override
	public MMPREMIOS save(MMPREMIOS obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}
	
	public Integer getKey(MMPREMIOS newobj){
			Criteria crit = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(MMPREMIOS.class);
			crit.setProjection(Projections.max("ICVEPREMIO_PK.ICVEPREMIO"));
			return (Integer) crit.uniqueResult();
	}
	
}
