package mx.gob.sct.utic.mimappir.db.postgreSQL.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "MMCOMENTARIOSGEO")
public class MMCOMENTARIOSGEO {
	@Id
	@Column(name="icvecomentario")
	private Integer icvecomentario;
	
	@Column(name="icvecategoria")
	private Integer icvecategoria;
	
	@Column(name="dlatitud", nullable=false)
	protected Double dlatitud;
	@Column(name="dlongitud", nullable=false)
	protected Double dlongitud;

	@JsonIgnore
	@Type(type = "org.hibernatespatial.GeometryUserType")
	@Column(name="geoubicacion",columnDefinition="Geometry",nullable = true)
	private Point geoubicacion;
	
	@Column(name="tsfechacomentario", nullable=false)
	private String tsfechacomentario;

	@Column(name="ccomentario", nullable=false)
	private String ccomentario;
	
	
	public MMCOMENTARIOSGEO() {
	}
	
	public MMCOMENTARIOSGEO(Double dlongitud, Double dlatitud) {
		this.dlongitud = dlongitud;
		this.dlatitud = dlatitud;
		Coordinate coord = new Coordinate(this.dlongitud, this.dlatitud);
		this.geoubicacion = new GeometryFactory(new PrecisionModel(),4326).createPoint(coord);
	}

	public MMCOMENTARIOSGEO(Double dlongitud, Double dlatitud, Double altitude) {
		this.dlongitud = dlongitud;
		this.dlatitud = dlatitud;
		
		Coordinate coord = new Coordinate(this.dlongitud, this.dlatitud);
		this.geoubicacion = new GeometryFactory(new PrecisionModel(),4326).createPoint(coord);
	}

	public MMCOMENTARIOSGEO(String coordinates) {
		String[] coords = coordinates.replaceAll(",\\s+", ",").trim()
				.split(",");
		if ((coords.length < 1) && (coords.length > 3)) {
			throw new IllegalArgumentException();
		}
		this.dlongitud = Double.parseDouble(coords[0]);
		this.dlatitud = Double.parseDouble(coords[1]);

	}

	public Integer getIcvecomentario() {
		return icvecomentario;
	}

	public void setIcvecomentario(Integer icvecomentario) {
		this.icvecomentario = icvecomentario;
	}

	public Integer getIcvecategoria() {
		return icvecategoria;
	}

	public void setIcvecategoria(Integer icvecategoria) {
		this.icvecategoria = icvecategoria;
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

	public String getTsfechacomentario() {
		return tsfechacomentario;
	}

	public void setTsfechacomentario(String tsfechacomentario) {
		this.tsfechacomentario = tsfechacomentario;
	}

	public String getCcomentario() {
		return ccomentario;
	}

	public void setCcomentario(String ccomentario) {
		this.ccomentario = ccomentario;
	}

}