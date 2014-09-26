package mx.gob.sct.utic.qoscell.db.postgreSQL.dao;

import java.util.List;
import mx.gob.sct.utic.qoscell.db.postgreSQL.model.QOSLLAMADAS;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.util.GeometricShapeFactory;

/**
 * QOSLLAMADAS DAO
 * 
 * @author Ivan Santiago
 *
 */
@Repository
public class QOSLLAMADAS_DAO implements IQOSLLAMADAS_DAO{
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	@Qualifier("sessionFactory_qoscell_POSGIS")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<QOSLLAMADAS> getList() {
		return hibernateTemplate.find(" from QOSLLAMADAS");
	}
	/**
	 * Get List of contacts from database
	 * @return list of all QOSLLAMADAS with specific UID_PROCESO
	 */
	@SuppressWarnings("unchecked")
	public List<QOSLLAMADAS> getQOSLLAMADASofUA(int UID_UA) {		
		return hibernateTemplate.find("from QOSLLAMADAS where geom = ?",UID_UA);
	}
	/**
	 * Delete a contact with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(int id){
		Object record = hibernateTemplate.load(QOSLLAMADAS.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new Contact on the database or
	 * Update contact
	 * @param contact
	 * @return contact added or updated in DB
	 */
	@Override
	public QOSLLAMADAS save(QOSLLAMADAS obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}
	
	public List<QOSLLAMADAS> findSenales(double latitud, double longitud, double radio) {
        Geometry area = createCircle( latitud,  longitud, radio);
	    Query q = hibernateTemplate.getSessionFactory().getCurrentSession()
	            .createQuery("from QOSLLAMADAS where ST_Point_Inside_Circle(geomubic, "+latitud+", "+longitud+", "+radio+") = true");
	    
	    List<QOSLLAMADAS> list = (List<QOSLLAMADAS>) q.list();
		return list; 
	}
	
	private Geometry createCircle(double x, double y, final double RADIUS) {
		  GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
		  shapeFactory.setNumPoints(32);
		  shapeFactory.setCentre(new Coordinate(x, y));//there are your coordinates
		  shapeFactory.setSize(RADIUS * 2);//this is how you set the radius
		  return shapeFactory.createCircle();
		}

}
