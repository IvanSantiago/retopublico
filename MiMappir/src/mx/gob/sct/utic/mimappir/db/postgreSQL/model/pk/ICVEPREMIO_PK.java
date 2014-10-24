package mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk;

import java.io.Serializable;

import javax.persistence.Column;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;


@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ICVEPREMIO_PK implements Serializable{

	private static final long serialVersionUID = -6870926164373177969L;
	
	@Column(name="ICVEPREMIO", nullable=false)
	@JsonProperty("ICVEPREMIO")
	private int ICVEPREMIO;
	
	public int getICVEPREMIO() {
		return ICVEPREMIO;
	}
	
	public void setICVEPREMIO(int iCVEPREMIO) {
		ICVEPREMIO = iCVEPREMIO;
	}
	
	
	
}

