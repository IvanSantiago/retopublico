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

public class MMCOMENTARIOSGEO {
	private Integer icvecomentario;
	private Integer icvecategoria;
	protected Double dlatitud;
	protected Double dlongitud;
	private Timestamp tsfechacomentario;

	private String ccomentario;
	
	
	public MMCOMENTARIOSGEO() {
	}
	
	public MMCOMENTARIOSGEO(Double dlongitud, Double dlatitud) {
		this.dlongitud = dlongitud;
		this.dlatitud = dlatitud;
	}

	public MMCOMENTARIOSGEO(Double dlongitud, Double dlatitud, Double altitude) {
		this.dlongitud = dlongitud;
		this.dlatitud = dlatitud;
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

	public Timestamp getTsfechacomentario() {
		return tsfechacomentario;
	}

	public void setTsfechacomentario(Timestamp tsfechacomentario) {
		this.tsfechacomentario = tsfechacomentario;
	}

	public String getCcomentario() {
		return ccomentario;
	}

	public void setCcomentario(String ccomentario) {
		this.ccomentario = ccomentario;
	}

}