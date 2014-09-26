package mx.gob.sct.utic.qoscell.admseg.postgreSQL.model;

import java.io.Serializable;

import javax.persistence.Column;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ICVESEGUSUARIO_PK implements Serializable{

	/**
	 * 
	 */


	@Column(name="ICVEUSUARIO", nullable=false)
	@JsonProperty("ICVEUSUARIO")
	private Long ICVEUSUARIO;

	public Long getICVEUSUARIO() {
		return ICVEUSUARIO;
	}

	public void setICVEUSUARIO(Long iCVEUSUARIO) {
		ICVEUSUARIO = iCVEUSUARIO;
	}

}