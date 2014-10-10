package mx.gob.sct.utic.mimappir.admseg.postgreSQL.dao;

import java.util.Iterator;
import java.util.List;


import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.pk.ICVESEGMENU_PK;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGGPOXUSR;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGMENU;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGPERMISOXGPO;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGPROGRAMA;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGSISTEMA;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGUSUARIO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * Contact POJO
 * 
 * @author Ivan Santiago
 *
 */
@Repository
public class SEGMENU_DAO implements ISEGMENU_DAO{
	
	private HibernateTemplate hibernateTemplate;
	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;

	@Autowired
	@Qualifier("sessionFactory_ADMSEG_POSGIS")
	public void setSessionFactory(SessionFactory sessionFactory) {
		sessionFactory = sessionFactory;
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SEGMENU> getList() {
		return hibernateTemplate.find("FROM SEGMENU");
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<SEGMENU> getSpecificSistema(ICVESEGMENU_PK ICVESEGMENU_PK) {
		return hibernateTemplate.find("FROM SEGMENU menu where ICVESISTEMA = ? order by IORDEN asc",ICVESEGMENU_PK.getICVESISTEMA());
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SEGMENU> getSpecific(ICVESEGMENU_PK ICVESEGMENU_PK) {
		return hibernateTemplate.find("FROM SEGMENU menu where ICVESISTEMA = ? and IORDEN = ? order by IORDEN asc",ICVESEGMENU_PK.getICVESISTEMA(),ICVESEGMENU_PK.getIORDEN());
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<SEGMENU> getSpecificMENU(SEGUSUARIO SEGUSUARIO, SEGSISTEMA SEGSISTEMA) {
		return hibernateTemplate.find(
				" SELECT A " +
				" FROM SEGMENU A " +
				" INNER JOIN A.SEGSISTEMA B " +
				" INNER JOIN A.SEGPROGRAMA C " +
				//" WHERE B.CNOMBRE='Sistema de la DGPMPT' " +
				" WHERE (C.ICVESEGPROGRAMA_PK.ICVESISTEMA=7 AND C.ICVESEGPROGRAMA_PK.ICVEPROGRAMA=514) " +				
				" ORDER BY A.ICVEMENU_PK.IORDEN ASC"
				);
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<SEGMENU> getListFromPermises(List<SEGPROGRAMA> SEGPROGRAMA) {
		Iterator<SEGPROGRAMA> it = SEGPROGRAMA.iterator();
		int cont = 1;
		StringBuffer st = new StringBuffer();
		while (it.hasNext()) {
			SEGPROGRAMA registro = (SEGPROGRAMA) it.next();
			if(cont == 1){
				st.append(" WHERE SEGMENU.ICVEMENU_PK.ICVESISTEMA = "+ registro.getICVESEGPROGRAMA_PK().getICVESISTEMA() +
						" AND ( " +
						" SEGMENU.CNOMPAGINA = '" + registro.getCNOMBRE() +"' "
						);				
			}else{
				st.append(" OR " +
						" SEGMENU.CNOMPAGINA = '" + registro.getCNOMBRE() +"' "						
						);
			}
			cont++;
		}
		st.append(" ) ");
		return hibernateTemplate.find(" FROM SEGMENU SEGMENU "+st.toString()+" AND SEGMENU.LBLOQUEADO=0 ORDER BY SEGMENU.ICVEMENU_PK.IORDEN, SEGMENU.IOPCPADRE");	
	}
/*	*//**
	 * Get List of contacts from database
	 * @return list of all contacts
	 *//*
	@SuppressWarnings("unchecked")
	public List<SEGUSUARIO> getSpecificSEGGPOXUSR() {
		return hibernateTemplate.find(
				" SELECT A " +
				" FROM SEGUSUARIO A " +
				" INNER JOIN A.SEGGRUPO B " +
				" INNER JOIN B.SEGPROGRAMA C " +
				" WHERE B.ICVESEGGRUPO_PK.ICVEGRUPO = 1 " +
				" AND A.ICVESEGUSUARIO_PK.ICVEUSUARIO = 3707 "+
				" AND C.ICVESEGPROGRAMA_PK.ICVEPROGRAMA = 154 "
				);
	}*/
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<SEGUSUARIO> getSpecificSEGGPOXUSR() {
		return hibernateTemplate.find(
				" SELECT A " +
				" FROM SEGUSUARIO A " +
				" INNER JOIN A.SEGGRUPO B " +
				" INNER JOIN B.SEGPERMISOXGPO C " +
				" INNER JOIN C.SEGPROGRAMA D " +
				" WHERE " +
				" A.ICVESEGUSUARIO_PK.ICVEUSUARIO = 3707 "+
				" AND B.ICVESEGGRUPO_PK.ICVEGRUPO = 1 " +				
				" AND C.ICVESEGPERMISOXGPO_PK.ICVEPROGRAMA = 2 "
				);
	}
	
	/**
	 * Delete a contact with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(int id){
		Object record = hibernateTemplate.load(SEGMENU.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new Contact on the database or
	 * Update contact
	 * @param contact
	 * @return contact added or updated in DB
	 */
	@Override
	public SEGMENU save(SEGMENU object){
		hibernateTemplate.saveOrUpdate(object);
		return object;
	}

}
