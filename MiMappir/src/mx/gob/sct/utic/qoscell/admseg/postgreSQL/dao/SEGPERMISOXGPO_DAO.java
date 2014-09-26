package mx.gob.sct.utic.qoscell.admseg.postgreSQL.dao;

import java.util.Iterator;
import java.util.List;

import mx.gob.sct.utic.qoscell.admseg.postgreSQL.model.SEGGPOXUSR;
import mx.gob.sct.utic.qoscell.admseg.postgreSQL.model.SEGPERMISOXGPO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * SEGPERMISOXGPO DAO
 * 
 * @author Ivan Santiago
 *
 */
@Repository
public class SEGPERMISOXGPO_DAO implements ISEGPERMISOXGPO_DAO{
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	@Qualifier("sessionFactory_ADMSEG_POSGIS")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SEGPERMISOXGPO> getList() {
		return hibernateTemplate.find("FROM SEGPERMISOXGPO SEGPERMISOXGPO");	
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<SEGPERMISOXGPO> getSEGPERMISOXGPO(int LOGIN) {
		return hibernateTemplate.find("FROM SEGPERMISOXGPO SEGPERMISOXGPO where SEGPERMISOXGPO.CUSUARIO = ?",LOGIN);	
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<Short> getListFromGroups(List<SEGGPOXUSR> SEGGPOXUSR) {
		Iterator<SEGGPOXUSR> itGruposDelUsuario = SEGGPOXUSR.iterator();
		int cont = 1;
		StringBuffer st = new StringBuffer();
		while (itGruposDelUsuario.hasNext()) {
			SEGGPOXUSR grupo = (SEGGPOXUSR) itGruposDelUsuario.next();
			if(cont == 1){
				st.append(" WHERE SEGPERMISOXGPO.ICVESEGPERMISOXGPO_PK.ICVESISTEMA = " + grupo.getICVESEGGPOXUSR_PK().getICVESISTEMA()+
						" AND ( " +
						" SEGPERMISOXGPO.ICVESEGPERMISOXGPO_PK.ICVEGRUPO = " + grupo.getICVESEGGPOXUSR_PK().getICVEGRUPO()						
					);				
			}else{
				st.append(" OR " +
						"SEGPERMISOXGPO.ICVESEGPERMISOXGPO_PK.ICVEGRUPO = " + grupo.getICVESEGGPOXUSR_PK().getICVEGRUPO()
						);
			}
			cont++;
		}
		st.append(" ) ");
		return hibernateTemplate.find("select SEGPERMISOXGPO.ICVESEGPERMISOXGPO_PK.ICVEPROGRAMA FROM SEGPERMISOXGPO SEGPERMISOXGPO "+st.toString() + "GROUP BY SEGPERMISOXGPO.ICVESEGPERMISOXGPO_PK.ICVEPROGRAMA");	
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public SEGPERMISOXGPO getNewestUser(String LOGIN) {
		List<SEGPERMISOXGPO> registros = hibernateTemplate.find("FROM SEGPERMISOXGPO SEGPERMISOXGPO where SEGPERMISOXGPO.CUSUARIO = ? order by ICVEUSUARIO",LOGIN);	
		Iterator<SEGPERMISOXGPO> it = registros.iterator();
		SEGPERMISOXGPO dbUser = null;
		while(it.hasNext()){
			dbUser = it.next();
		}
		return dbUser;
	}

	/**
	 * Delete a contact with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(int id){
		Object record = hibernateTemplate.load(SEGPERMISOXGPO.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new Contact on the database or
	 * Update contact
	 * @param contact
	 * @return contact added or updated in DB
	 */
	@Override
	public SEGPERMISOXGPO save(SEGPERMISOXGPO object){
		hibernateTemplate.saveOrUpdate(object);
		return object;
	}

}
