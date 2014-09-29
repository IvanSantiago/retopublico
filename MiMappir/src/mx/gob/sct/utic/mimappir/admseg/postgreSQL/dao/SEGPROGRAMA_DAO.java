package mx.gob.sct.utic.mimappir.admseg.postgreSQL.dao;

import java.util.Iterator;
import java.util.List;

import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGGPOXUSR;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGPERMISOXGPO;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGPROGRAMA;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGSISTEMA;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * SEGPROGRAMA DAO
 * 
 * @author Ivan Santiago
 *
 */
@Repository
public class SEGPROGRAMA_DAO implements ISEGPROGRAMA_DAO{
	
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
	public List<SEGPROGRAMA> getList() {
		return hibernateTemplate.find("FROM SEGPROGRAMA SEGPROGRAMA");	
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<SEGPROGRAMA> getListFromPermises(List<Short> SEGPERMISOXGPO, SEGSISTEMA SEGSISTEMA) {
		Iterator<Short> itGruposDelUsuario = SEGPERMISOXGPO.iterator();
		int cont = 1;
		StringBuffer st = new StringBuffer();
		while (itGruposDelUsuario.hasNext()) {
			Short reg = (Short) itGruposDelUsuario.next();
			if(cont == 1){
				st.append(" WHERE SEGPROGRAMA.ICVESEGPROGRAMA_PK.ICVESISTEMA = " + SEGSISTEMA.getICVESEGSISTEMA_PK().getICVESISTEMA() +
						" AND ( " +
						" SEGPROGRAMA.ICVESEGPROGRAMA_PK.ICVEPROGRAMA = " + reg 						
						);				
			}else{
				st.append(" OR " +
						" SEGPROGRAMA.ICVESEGPROGRAMA_PK.ICVEPROGRAMA = " + reg 
						);
			}
			cont++;
		}
		st.append(" ) ");
		return hibernateTemplate.find("FROM SEGPROGRAMA SEGPROGRAMA "+st.toString());	
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public SEGPROGRAMA getNewestUser(String LOGIN) {
		List<SEGPROGRAMA> registros = hibernateTemplate.find("FROM SEGPROGRAMA SEGPROGRAMA where SEGPROGRAMA.CUSUARIO = ? order by ICVEUSUARIO",LOGIN);	
		Iterator<SEGPROGRAMA> it = registros.iterator();
		SEGPROGRAMA dbUser = null;
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
		Object record = hibernateTemplate.load(SEGPROGRAMA.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new Contact on the database or
	 * Update contact
	 * @param contact
	 * @return contact added or updated in DB
	 */
	@Override
	public SEGPROGRAMA save(SEGPROGRAMA object){
		hibernateTemplate.saveOrUpdate(object);
		return object;
	}

}
