package mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk;

import java.io.Serializable;

import javax.persistence.Column;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;


@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ICVECOMENTARIO_PK implements Serializable {

	private static final long serialVersionUID = 4137301043747379173L;

	@Column(name="ICVECOMENTARIO", nullable=false)
	@JsonProperty("ICVECOMENTARIO")
	private int ICVECOMENTARIO;

	public int getICVECOMENTARIO() {
		return ICVECOMENTARIO;
	}

	public void setICVECOMENTARIO(int iCVECOMENTARIO) {
		ICVECOMENTARIO = iCVECOMENTARIO;
	}	
	
}
