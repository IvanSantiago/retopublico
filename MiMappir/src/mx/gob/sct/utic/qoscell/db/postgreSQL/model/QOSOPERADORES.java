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
@Table(name = "QOSOPERADORES")
public class QOSOPERADORES {
	@Id
	@GeneratedValue
	@Column(name="icveoperador")
	private Integer icveoperador;
	@Column(name="nombre", nullable=false)
	private String nombre;
	@Column(name="descripcion", nullable=true)
	private String descripcion;
	
	public QOSOPERADORES() {
	}

	public Integer getIcveoperador() {
		return icveoperador;
	}

	public void setIcveoperador(Integer icveoperador) {
		this.icveoperador = icveoperador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}