package mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk;

import java.io.Serializable;

import javax.persistence.Column;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;


@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ICVEPREMIACION_PK implements Serializable{

	private static final long serialVersionUID = -8088079110903366201L;
	
	@Column(name="ICVEPREMIACION", nullable=false)
	@JsonProperty("ICVEPREMIACION")
	private int ICVEPREMIACION;

	public int getICVEPREMIACION() {
		return ICVEPREMIACION;
	}

	public void setICVEPREMIACION(int iCVEPREMIACION) {
		ICVEPREMIACION = iCVEPREMIACION;
	}
	
	
}
