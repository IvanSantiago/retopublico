package mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk;

import java.io.Serializable;

import javax.persistence.Column;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ICVESENAL_PK implements Serializable{

	private static final long serialVersionUID = 9179455628961247438L;
	@Column(name="ICVESENAL", nullable=false)
	@JsonProperty("ICVESENAL")
	private int ICVESENAL;
	
	
	public int getICVESENAL() {
		return ICVESENAL;
	}
	public void setICVESENAL(int iCVESENAL) {
		ICVESENAL = iCVESENAL;
	}
	
	
	
}
