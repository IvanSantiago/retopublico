package mx.gob.sct.utic.qoscell.sigtic.db2.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

/**
 * Contact POJO
 * 
 * @author Ivan Santiago
 *
 */
@Repository
public class SQL_ARG_DAO implements ISQL_ARG_DAO{
	private HibernateTemplate hibernateTemplate;


	@Autowired
	@Qualifier("sessionFactorySIGTICDB2")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);		
	}
	/**
	 * Get List of Objects from database based in a SQLQuery
	 * @return List of all Objects corresponded to the SQLQuery
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getList(String SQLQuery, String[] params, HttpServletRequest request) {
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(SQLQuery);
		//Query query = null;
		for(int i= 0 ; i < params.length ; i++){
			String DP[] = params[i].split(":");
			String VAL = "";
			// Tipos de Origen
			if(DP[0].compareTo("S") == 0 ){
				try {
					//La session en spring no existe solo el usuario logueado se obtiene del REQUEST
					VAL = ServletRequestUtils.getStringParameter(request, DP[2]);
				} catch (ServletRequestBindingException e) {
					e.printStackTrace();
				}
			}
			if(DP[0].compareTo("R") == 0 ){
				try {
					VAL = ServletRequestUtils.getStringParameter(request, DP[2]);
				} catch (ServletRequestBindingException e) {
					e.printStackTrace();
				}
			}
			query.setParameter(i, VAL);
		}		
		List<Object> result = query.list();
		return result;
	}
}
