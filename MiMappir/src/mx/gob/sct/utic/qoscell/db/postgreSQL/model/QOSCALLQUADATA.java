package mx.gob.sct.utic.qoscell.db.postgreSQL.model;

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
@Table(name = "QOSCSENALGEO")
public class QOSCALLQUADATA {
	@Id
	@GeneratedValue
	@Column(name="icvesenal")
	private Integer icvesenal;
	@Column(name="dlatitud", nullable=false)
	protected Double dlatitud;
	@Column(name="dlongitud", nullable=false)
	protected Double dlongitud;
	@JsonIgnore
	@Column(name="d_altitud", nullable=false)
	protected Double d_altitude;
	@Column(name="dintensidad", nullable=false)
	protected Double dintensidad;
	@Column(name="danchobanda", nullable=false)
	protected Double danchobanda;
	@JsonIgnore
	@Type(type = "org.hibernatespatial.GeometryUserType")
	@Column(name="geomubic",columnDefinition="Geometry",nullable = true)
	private Point geomubic;
	@Column(name="tsregistro", nullable=false)
	private Timestamp tsregistro;
	@Column(name="iTipoConex", nullable=false)
	private Integer iTipoConex;
	@Column(name="iOperador", nullable=false)
	private Integer iOperador;
	
	
	
	
	
	public QOSCALLQUADATA() {
	}
	
	public QOSCALLQUADATA(Double dlongitud, Double dlatitud) {
		this.dlongitud = dlongitud;
		this.dlatitud = dlatitud;
		Coordinate coord = new Coordinate(this.dlongitud, this.dlatitud);
		this.geomubic = new GeometryFactory().createPoint(coord);
	}

	public QOSCALLQUADATA(Double dlongitud, Double dlatitud, Double altitude) {
		this.dlongitud = dlongitud;
		this.dlatitud = dlatitud;
		this.d_altitude = altitude;
		Coordinate coord = new Coordinate(this.dlongitud, this.dlatitud,this.d_altitude);
		this.geomubic = new GeometryFactory().createPoint(coord);
	}

	public QOSCALLQUADATA(String coordinates) {
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

	public Integer getIcvesenal() {
		return icvesenal;
	}

	public void setIcvesenal(Integer icvesenal) {
		this.icvesenal = icvesenal;
	}

	public Double getD_altitude() {
		return d_altitude;
	}

	public void setD_altitude(Double d_altitude) {
		this.d_altitude = d_altitude;
	}

	public Double getDintensidad() {
		return dintensidad;
	}

	public void setDintensidad(Double dintensidad) {
		this.dintensidad = dintensidad;
	}

	public Double getDanchobanda() {
		return danchobanda;
	}

	public void setDanchobanda(Double danchobanda) {
		this.danchobanda = danchobanda;
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

	public Integer getiTipoConex() {
		return iTipoConex;
	}

	public void setiTipoConex(Integer iTipoConex) {
		this.iTipoConex = iTipoConex;
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
		if (!(obj instanceof QOSCALLQUADATA)) {
			return false;
		}
		QOSCALLQUADATA other = (QOSCALLQUADATA) obj;
		if (this.dlongitud != other.dlongitud) {
			return false;
		}
		if (this.dlatitud != other.dlatitud) {
			return false;
		}

		return (this.d_altitude == other.d_altitude);
	}

	public QOSCALLQUADATA withdlongitud(Double dlongitud) {
		setDlongitud(dlongitud);
		return this;
	}

	public QOSCALLQUADATA withdlatitud(Double dlatitud) {
		setDlatitud(dlatitud);
		return this;
	}

	public QOSCALLQUADATA withAltitude(Double altitude) {
		setD_altitude(altitude);
		return this;
	}

	public QOSCALLQUADATA clone() {
		QOSCALLQUADATA copy;
		try {
			copy = (QOSCALLQUADATA) super.clone();
		} catch (CloneNotSupportedException _x) {
			throw new InternalError(_x.toString());
		}
		return copy;
	}

	public Integer getiOperador() {
		return iOperador;
	}

	public void setiOperador(Integer iOperador) {
		this.iOperador = iOperador;
	}
	
	
}