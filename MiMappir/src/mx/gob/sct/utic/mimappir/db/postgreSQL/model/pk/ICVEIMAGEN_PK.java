package mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk;

import java.io.Serializable;

import javax.persistence.Column;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ICVEIMAGEN_PK implements Serializable{

	private static final long serialVersionUID = -5669532389310466003L;

	@Column(name="ICVEIMAGEN", nullable=false)
	@JsonProperty("ICVEIMAGEN")
	private int ICVEIMAGEN;


	public int getICVEIMAGEN() {
		return ICVEIMAGEN;
	}

	public void setICVEIMAGEN(int iCVEIMAGEN) {
		ICVEIMAGEN = iCVEIMAGEN;
	}
	
}
