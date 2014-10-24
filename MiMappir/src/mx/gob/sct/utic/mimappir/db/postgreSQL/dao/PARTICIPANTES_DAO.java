package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMPARTICIPANTES;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEPARTICIPANTE_PK;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PARTICIPANTES_DAO implements IPARTICIPANTES_DAO{

	private HibernateTemplate hibernateTemplate;

	@Autowired
	@Qualifier("sessionFactory_mimappir_POSGIS")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	/**
	 * Get List of PARTICIPANTES from database
	 * @return list of all PARTICIPANTES
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MMPARTICIPANTES> getList() {		
		return hibernateTemplate.find("from MMPARTICIPANTES");
	}
	/**
	 * Get List of PARTICIPANTES from database
	 * @return list of all PARTICIPANTES
	 */
	@SuppressWarnings("unchecked")
	public List<MMPARTICIPANTES> getList(int ICVEPARTICIPANTE) {
		return hibernateTemplate.find("from MMPARTICIPANTES where ICVEPARTICIPANTE = ?",ICVEPARTICIPANTE);
	}
	
	/**
	 * Delete a PARTICIPANTES with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(ICVEPARTICIPANTE_PK id){
		Object record = hibernateTemplate.load(MMPARTICIPANTES.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new PARTICIPANTES on the database or
	 * Update PARTICIPANTES
	 * @param MMPARTICIPANTES
	 * @return PARTICIPANTES added or updated in DB
	 */
	@Override
	public MMPARTICIPANTES save(MMPARTICIPANTES obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}
	
	public Integer getKey(MMPARTICIPANTES newobj){
			Criteria crit = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(MMPARTICIPANTES.class);
			crit.setProjection(Projections.max("ICVEPARTICIPANTE_PK.ICVEPARTICIPANTE"));
			return (Integer) crit.uniqueResult();
	}
	
	
}
