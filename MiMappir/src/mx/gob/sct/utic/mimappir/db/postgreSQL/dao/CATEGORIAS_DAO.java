package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMCATEGORIAS;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVECATEGORIA_PK;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CATEGORIAS_DAO implements ICATEGORIAS_DAO{

	private HibernateTemplate hibernateTemplate;

	@Autowired
	@Qualifier("sessionFactory_mimappir_POSGIS")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	/**
	 * Get List of CATEGORIAS from database
	 * @return list of all CATEGORIAS
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MMCATEGORIAS> getList() {		
		return hibernateTemplate.find("from MMCATEGORIAS");
	}
	/**
	 * Get List of CATEGORIAS from database
	 * @return list of all CATEGORIAS
	 */
	@SuppressWarnings("unchecked")
	public List<MMCATEGORIAS> getList(int ICVECATEGORIA) {
		return hibernateTemplate.find("from MMCATEGORIAS where ICVECATEGORIA = ?",ICVECATEGORIA);
	}
	
	/**
	 * Delete a CATEGORIAS with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(ICVECATEGORIA_PK id){
		Object record = hibernateTemplate.load(MMCATEGORIAS.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new CATEGORIAS on the database or
	 * Update CATEGORIAS
	 * @param MMCATEGORIAS
	 * @return CATEGORIAS added or updated in DB
	 */
	@Override
	public MMCATEGORIAS save(MMCATEGORIAS obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}
	
	public Integer getKey(MMCATEGORIAS newobj){
			Criteria crit = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(MMCATEGORIAS.class);
			crit.setProjection(Projections.max("ICVECATEGORIA_PK.ICVECATEGORIA"));
			return (Integer) crit.uniqueResult();
	}
	
}
