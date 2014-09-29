package mx.gob.sct.utic.mimappir.db.postgreSQL.model;

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
@Table(name = "coordinate")
public class COORDINATE {
	private int icvecoordinate;
	private Point geom;
	protected double longitude;
	protected double latitude;
	protected double altitude;

	public COORDINATE() {
	}
	
	public COORDINATE(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
		Coordinate coord = new Coordinate(this.longitude, this.latitude);
		this.geom = new GeometryFactory().createPoint(coord);
	}

	public COORDINATE(double longitude, double latitude, double altitude) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
		Coordinate coord = new Coordinate(this.longitude, this.latitude,this.altitude);
		this.geom = new GeometryFactory().createPoint(coord);
	}

	public COORDINATE(String coordinates) {
		String[] coords = coordinates.replaceAll(",\\s+", ",").trim()
				.split(",");
		if ((coords.length < 1) && (coords.length > 3)) {
			throw new IllegalArgumentException();
		}
		this.longitude = Double.parseDouble(coords[0]);
		this.latitude = Double.parseDouble(coords[1]);
		if (coords.length == 3)
			this.altitude = Double.parseDouble(coords[2]);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.longitude);
		sb.append(",");
		sb.append(this.latitude);
		if (this.altitude != 0.0D) {
			sb.append(",");
			sb.append(this.altitude);
		}
		return sb.toString();
	}
	
	@Id
	@GeneratedValue
	@Column(name="icvecoordinate")
	public int getIcvecoordinate() {
		return icvecoordinate;
	}

	public void setIcvecoordinate(int icvecoordinate) {
		this.icvecoordinate = icvecoordinate;
	}

	@Type(type = "org.hibernatespatial.GeometryUserType")
	@Column(name="geom",columnDefinition="GEOMETRY")
	@JsonIgnore
	public Point getGeom() {
		return geom;
	}

	public void setGeom(Point geom) {
		this.geom = geom;
	}
	@Column(name="longitude", nullable=false)
	public double getLongitude() {
		return this.longitude;
	}

	public COORDINATE setLongitude(double longitude) {
		this.longitude = longitude;
		return this;
	}
	@Column(name="latitude", nullable=false)
	public double getLatitude() {
		return this.latitude;
	}

	public COORDINATE setLatitude(double latitude) {
		this.latitude = latitude;
		return this;
	}
	@Column(name="altitude", nullable=false)
	public double getAltitude() {
		return this.altitude;
	}

	public COORDINATE setAltitude(double altitude) {
		this.altitude = altitude;
		return this;
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;

		long temp = Double.doubleToLongBits(this.longitude);
		result = 31 * result + (int) (temp ^ temp >>> 32);
		temp = Double.doubleToLongBits(this.latitude);
		result = 31 * result + (int) (temp ^ temp >>> 32);
		temp = Double.doubleToLongBits(this.altitude);
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
		if (!(obj instanceof COORDINATE)) {
			return false;
		}
		COORDINATE other = (COORDINATE) obj;
		if (this.longitude != other.longitude) {
			return false;
		}
		if (this.latitude != other.latitude) {
			return false;
		}

		return (this.altitude == other.altitude);
	}

	public COORDINATE withLongitude(double longitude) {
		setLongitude(longitude);
		return this;
	}

	public COORDINATE withLatitude(double latitude) {
		setLatitude(latitude);
		return this;
	}

	public COORDINATE withAltitude(double altitude) {
		setAltitude(altitude);
		return this;
	}

	public COORDINATE clone() {
		COORDINATE copy;
		try {
			copy = (COORDINATE) super.clone();
		} catch (CloneNotSupportedException _x) {
			throw new InternalError(_x.toString());
		}
		return copy;
	}
}