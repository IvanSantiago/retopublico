package mx.gob.sct.utic.mimappir.db.postgreSQL.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;

@JsonAutoDetect
@Entity
@Table(name = "MMSEGUIMIENTOS")
public class MMSEGUIMIENTOS {
	@Id
	@GeneratedValue
	@Column(name="icvesenal")
	private Integer icvesenal;
	
	@Column(name="dlatitud", nullable=false)
	protected Double dlatitud;
	@Column(name="dlongitud", nullable=false)
	protected Double dlongitud;

	@JsonIgnore
	@Type(type = "org.hibernatespatial.GeometryUserType")
	@Column(name="geoubicacion",columnDefinition="Geometry",nullable = true)
	private Point geoubicacion;
	
	@Column(name="tsregistro", nullable=false)
	private Timestamp tsregistro;

	@Column(name="icveparticipante", nullable=false)
	private Integer icveparticipante;
	
	
	public MMSEGUIMIENTOS() {
	}
	
	public MMSEGUIMIENTOS(Double dlongitud, Double dlatitud) {
		this.dlongitud = dlongitud;
		this.dlatitud = dlatitud;
		Coordinate coord = new Coordinate(this.dlongitud, this.dlatitud);
		this.geoubicacion = new GeometryFactory(new PrecisionModel(),4326).createPoint(coord);
	}

	public MMSEGUIMIENTOS(Double dlongitud, Double dlatitud, Double altitude) {
		this.dlongitud = dlongitud;
		this.dlatitud = dlatitud;
		
		Coordinate coord = new Coordinate(this.dlongitud, this.dlatitud);
		this.geoubicacion = new GeometryFactory(new PrecisionModel(),4326).createPoint(coord);
	}

	public MMSEGUIMIENTOS(String coordinates) {
		String[] coords = coordinates.replaceAll(",\\s+", ",").trim()
				.split(",");
		if ((coords.length < 1) && (coords.length > 3)) {
			throw new IllegalArgumentException();
		}
		this.dlongitud = Double.parseDouble(coords[0]);
		this.dlatitud = Double.parseDouble(coords[1]);

	}

	public Integer getIcvesenal() {
		return icvesenal;
	}

	public void setIcvesenal(Integer icvesenal) {
		this.icvesenal = icvesenal;
	}

	public Double getDlatitud() {
		return dlatitud;
	}

	public void setDlatitud(Double dlatitud) {
		this.dlatitud = dlatitud;
	}

	public Double getDlongitud() {
		return dlongitud;
	}

	public void setDlongitud(Double dlongitud) {
		this.dlongitud = dlongitud;
	}

	public Point getGeoubicacion() {
		return geoubicacion;
	}

	public void setGeoubicacion(Point geoubicacion) {
		this.geoubicacion = geoubicacion;
	}

	public Timestamp getTsregistro() {
		return tsregistro;
	}

	public void setTsregistro(Timestamp tsregistro) {
		this.tsregistro = tsregistro;
	}

	public Integer getIcveparticipante() {
		return icveparticipante;
	}

	public void setIcveparticipante(Integer icveparticipante) {
		this.icveparticipante = icveparticipante;
	}

}