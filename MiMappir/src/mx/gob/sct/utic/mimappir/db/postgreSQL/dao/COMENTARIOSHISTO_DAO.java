package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMCOMENTARIOSHISTO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVECOMENTARIO_PK;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class COMENTARIOSHISTO_DAO implements ICOMENTARIOSHISTO_DAO{

	private HibernateTemplate hibernateTemplate;

	@Autowired
	@Qualifier("sessionFactory_mimappir_POSGIS")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	/**
	 * Get List of COMENTARIOSHISTO from database
	 * @return list of all COMENTARIOSHISTO
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MMCOMENTARIOSHISTO> getList() {		
		return hibernateTemplate.find("from MMCOMENTARIOSHISTO");
	}
	/**
	 * Get List of COMENTARIOSHISTO from database
	 * @return list of all COMENTARIOSHISTO
	 */
	@SuppressWarnings("unchecked")
	public List<MMCOMENTARIOSHISTO> getList(int ICVECOMENTARIO) {
		return hibernateTemplate.find("from MMCOMENTARIOSHISTO where ICVECOMENTARIO = ?",ICVECOMENTARIO);
	}
	
	/**
	 * Delete a COMENTARIOSHISTO with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(ICVECOMENTARIO_PK id){
		Object record = hibernateTemplate.load(MMCOMENTARIOSHISTO.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new COMENTARIOSHISTO on the database or
	 * Update COMENTARIOSHISTO
	 * @param MMCOMENTARIOSHISTO
	 * @return COMENTARIOSHISTO added or updated in DB
	 */
	@Override
	public MMCOMENTARIOSHISTO save(MMCOMENTARIOSHISTO obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}
	
	public Integer getKey(MMCOMENTARIOSHISTO newobj){
			Criteria crit = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(MMCOMENTARIOSHISTO.class);
			crit.setProjection(Projections.max("ICVECOMENTARIO_PK.ICVECOMENTARIO"));
			return (Integer) crit.uniqueResult();
	}
	
}
