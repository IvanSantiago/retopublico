package mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk;

import java.io.Serializable;

import javax.persistence.Column;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;


@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ICVEUSUARIO_PK implements Serializable{

	private static final long serialVersionUID = 9179455628961247438L;
	@Column(name="ICVEUSUARIO", nullable=false)
	@JsonProperty("ICVEUSUARIO")
	private Integer ICVEUSUARIO;
	
	
	public Integer getICVEUSUARIO() {
		return ICVEUSUARIO;
	}
	public void setICVEUSUARIO(Integer iCVEUSUARIO) {
		ICVEUSUARIO = iCVEUSUARIO;
	}
	
}
