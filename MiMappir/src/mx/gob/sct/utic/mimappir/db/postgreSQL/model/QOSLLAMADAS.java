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

@JsonAutoDetect
@Entity
@Table(name = "QOSLLAMADAS")
public class QOSLLAMADAS {
	@Id
	@GeneratedValue
	@Column(name="icvellamada")
	private Integer icvellamada;
	@Column(name="dlatitud", nullable=false)
	protected Double dlatitud;
	@Column(name="dlongitud", nullable=false)
	protected Double dlongitud;
	@JsonIgnore
	@Column(name="d_altitud", nullable=false)
	protected Double d_altitude;
	@Column(name="tsregistro", nullable=false)
	private Timestamp tsregistro;
	@JsonIgnore
	@Type(type = "org.hibernatespatial.GeometryUserType")
	@Column(name="geomubic",columnDefinition="Geometry",nullable = true)
	private Point geomubic;

	@Column(name="illamadasexitosas", nullable=false)
	private Integer illamadasexitosas;
	@Column(name="illamadasfallidas", nullable=false)
	private Integer illamadasfallidas;
	@Column(name="illamadasdesconocidas", nullable=false)
	private Integer illamadasdesconocidas;
	
	public QOSLLAMADAS() {
	}
	
	public QOSLLAMADAS(Double dlongitud, Double dlatitud) {
		this.dlongitud = dlongitud;
		this.dlatitud = dlatitud;
		Coordinate coord = new Coordinate(this.dlongitud, this.dlatitud);
		this.geomubic = new GeometryFactory().createPoint(coord);
	}

	public QOSLLAMADAS(Double dlongitud, Double dlatitud, Double altitude) {
		this.dlongitud = dlongitud;
		this.dlatitud = dlatitud;
		this.d_altitude = altitude;
		Coordinate coord = new Coordinate(this.dlongitud, this.dlatitud,this.d_altitude);
		this.geomubic = new GeometryFactory().createPoint(coord);
	}

	public QOSLLAMADAS(String coordinates) {
		String[] coords = coordinates.replaceAll(",\\s+", ",").trim()
				.split(",");
		if ((coords.length < 1) && (coords.length > 3)) {
			throw new IllegalArgumentException();
		}
		this.dlongitud = Double.parseDouble(coords[0]);
		this.dlatitud = Double.parseDouble(coords[1]);
		if (coords.length == 3)
			this.d_altitude = Double.parseDouble(coords[2]);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.dlongitud);
		sb.append(",");
		sb.append(this.dlatitud);
		if (this.d_altitude != 0.0D) {
			sb.append(",");
			sb.append(this.d_altitude);
		}
		return sb.toString();
	}

	
	public Double getD_altitude() {
		return d_altitude;
	}

	public void setD_altitude(Double d_altitude) {
		this.d_altitude = d_altitude;
	}

	public Point getGeomubic() {
		return geomubic;
	}

	public void setGeomubic(Point geomubic) {
		this.geomubic = geomubic;
	}

	public Timestamp getTsregistro() {
		return tsregistro;
	}

	public void setTsregistro(Timestamp tsregistro) {
		this.tsregistro = tsregistro;
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

	public int hashCode() {
		int prime = 31;
		int result = 1;

		long temp = Double.doubleToLongBits(this.dlongitud);
		result = 31 * result + (int) (temp ^ temp >>> 32);
		temp = Double.doubleToLongBits(this.dlatitud);
		result = 31 * result + (int) (temp ^ temp >>> 32);
		temp = Double.doubleToLongBits(this.d_altitude);
		result = 31 * result + (int) (temp ^ temp >>> 32);
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof QOSLLAMADAS)) {
			return false;
		}
		QOSLLAMADAS other = (QOSLLAMADAS) obj;
		if (this.dlongitud != other.dlongitud) {
			return false;
		}
		if (this.dlatitud != other.dlatitud) {
			return false;
		}

		return (this.d_altitude == other.d_altitude);
	}

	public QOSLLAMADAS withdlongitud(Double dlongitud) {
		setDlongitud(dlongitud);
		return this;
	}

	public QOSLLAMADAS withdlatitud(Double dlatitud) {
		setDlatitud(dlatitud);
		return this;
	}

	public QOSLLAMADAS withAltitude(Double altitude) {
		setD_altitude(altitude);
		return this;
	}

	public QOSLLAMADAS clone() {
		QOSLLAMADAS copy;
		try {
			copy = (QOSLLAMADAS) super.clone();
		} catch (CloneNotSupportedException _x) {
			throw new InternalError(_x.toString());
		}
		return copy;
	}

	public Integer getIcvellamada() {
		return icvellamada;
	}

	public void setIcvellamada(Integer icvellamada) {
		this.icvellamada = icvellamada;
	}

	public Integer getIllamadasexitosas() {
		return illamadasexitosas;
	}

	public void setIllamadasexitosas(Integer illamadasexitosas) {
		this.illamadasexitosas = illamadasexitosas;
	}

	public Integer getIllamadasfallidas() {
		return illamadasfallidas;
	}

	public void setIllamadasfallidas(Integer illamadasfallidas) {
		this.illamadasfallidas = illamadasfallidas;
	}

	public Integer getIllamadasdesconocidas() {
		return illamadasdesconocidas;
	}

	public void setIllamadasdesconocidas(Integer illamadasdesconocidas) {
		this.illamadasdesconocidas = illamadasdesconocidas;
	}
	
}