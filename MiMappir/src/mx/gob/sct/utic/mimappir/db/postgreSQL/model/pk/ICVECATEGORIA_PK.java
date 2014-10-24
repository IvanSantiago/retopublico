package mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk;

import java.io.Serializable;

import javax.persistence.Column;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ICVECATEGORIA_PK implements Serializable{

	private static final long serialVersionUID = -3530320861452571416L;
	
	@Column(name="ICVECATEGORIA", nullable=false)
	@JsonProperty("ICVECATEGORIA")
	public int ICVECATEGORIA;

	public int getICVECATEGORIA() {
		return ICVECATEGORIA;
	}

	public void setICVECATEGORIA(int iCVECATEGORIA) {
		ICVECATEGORIA = iCVECATEGORIA;
	}

}
