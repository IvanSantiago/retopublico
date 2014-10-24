package mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk;

import java.io.Serializable;

import javax.persistence.Column;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;


@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ICVEPARTICIPANTE_PK implements Serializable{

	private static final long serialVersionUID = -7216300606613657349L;

	@Column(name="ICVEPARTICIPANTE", nullable=false)
	@JsonProperty("ICVEPARTICIPANTE")
	public int ICVEPARTICIPANTE;

	public int getICVEPARTICIPANTE() {
		return ICVEPARTICIPANTE;
	}

	public void setICVEPARTICIPANTE(int iCVEPARTICIPANTE) {
		ICVEPARTICIPANTE = iCVEPARTICIPANTE;
	}
	
}
