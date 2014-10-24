package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMUSUARIOS;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEUSUARIO_PK;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class USUARIOS_DAO implements IUSUARIOS_DAO{

	private HibernateTemplate hibernateTemplate;

	@Autowired
	@Qualifier("sessionFactory_mimappir_POSGIS")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	/**
	 * Get List of USUARIOS from database
	 * @return list of all USUARIOS
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MMUSUARIOS> getList() {		
		return hibernateTemplate.find("from MMUSUARIOS");
	}
	/**
	 * Get List of USUARIOS from database
	 * @return list of all USUARIOS
	 */
	@SuppressWarnings("unchecked")
	public List<MMUSUARIOS> getList(int ICVEUSUARIO) {
		return hibernateTemplate.find("from MMUSUARIOS where ICVEUSUARIO = ?",ICVEUSUARIO);
	}
	
	/**
	 * Delete a USUARIOS with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(ICVEUSUARIO_PK id){
		Object record = hibernateTemplate.load(MMUSUARIOS.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new USUARIOS on the database or
	 * Update USUARIOS
	 * @param MMUSUARIOS
	 * @return USUARIOS added or updated in DB
	 */
	@Override
	public MMUSUARIOS save(MMUSUARIOS obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}
	
	public Integer getKey(MMUSUARIOS newobj){
			Criteria crit = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(MMUSUARIOS.class);
			crit.setProjection(Projections.max("ICVEUSUARIO_PK.ICVEUSUARIO"));
			return (Integer) crit.uniqueResult();
	}

}
