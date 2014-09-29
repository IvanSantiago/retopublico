package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import javax.persistence.EntityManager;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.QOSCSENALGEO;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.type.Type;
import org.hibernatespatial.GeometryUserType;
import org.hibernatespatial.criterion.SpatialRestrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.util.GeometricShapeFactory;

/**
 * QOSCSENALGEO DAO
 * 
 * @author Ivan Santiago
 *
 */
@Repository
public class QOSCSENALGEO_DAO implements IQOSCSENALGEO_DAO{
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	@Qualifier("sessionFactory_mimappir_POSGIS")
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<QOSCSENALGEO> getList() {
		return hibernateTemplate.find(" from QOSCSENALGEO");
	}
	/**
	 * Get List of contacts from database
	 * @return list of all QOSCSENALGEO with specific UID_PROCESO
	 */
	@SuppressWarnings("unchecked")
	public List<QOSCSENALGEO> getQOSCSENALGEOofUA(int UID_UA) {		
		return hibernateTemplate.find("from QOSCSENALGEO where geom = ?",UID_UA);
	}
	/**
	 * Delete a contact with the id passed as parameter
	 * @param id
	 */
	@Override
	public void delete(int id){
		Object record = hibernateTemplate.load(QOSCSENALGEO.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new Contact on the database or
	 * Update contact
	 * @param contact
	 * @return contact added or updated in DB
	 */
	@Override
	public QOSCSENALGEO save(QOSCSENALGEO obj){
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}
	
	public List<QOSCSENALGEO> findSenales(double latitud, double longitud, double radio) {
        Geometry area = createCircle( latitud,  longitud, radio);
	    Query q = hibernateTemplate.getSessionFactory().getCurrentSession()
	            .createQuery("from QOSCSENALGEO where ST_Point_Inside_Circle(geomubic, "+latitud+", "+longitud+", "+radio+") = true");
	    
	    List<QOSCSENALGEO> list = (List<QOSCSENALGEO>) q.list();
		return list; 
	}

	public List<QOSCSENALGEO> findSenales(double latitud, double longitud, double radio, int icveoperator) {
        Geometry area = createCircle( latitud,  longitud, radio);
	    Query q = hibernateTemplate.getSessionFactory().getCurrentSession()
	            .createQuery("from QOSCSENALGEO where ST_Point_Inside_Circle(geomubic, "+latitud+", "+longitud+", "+radio+") = true and iOperador ="+icveoperator);
	    
	    List<QOSCSENALGEO> list = (List<QOSCSENALGEO>) q.list();
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
