package mx.gob.sct.utic.qoscell.admseg.postgreSQL.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Entity
@Table(name="SEGGPOXUSR")
public class SEGGPOXUSR {
	@EmbeddedId
	@Column(name="ICVESEGGPOXUSR_PK", nullable=false)
	@JsonProperty("ICVESEGGPOXUSR_PK")
	private ICVESEGGPOXUSR_PK ICVESEGGPOXUSR_PK;

	
	@JsonProperty("ICVESEGGPOXUSR_ID")
	@Transient
	private String ICVESEGGPOXUSR_ID;

	public ICVESEGGPOXUSR_PK getICVESEGGPOXUSR_PK() {
		return ICVESEGGPOXUSR_PK;
	}

	public void setICVESEGGPOXUSR_PK(ICVESEGGPOXUSR_PK iCVESEGGPOXUSR_PK) {
		ICVESEGGPOXUSR_PK = iCVESEGGPOXUSR_PK;
	}

	public String getICVESEGGPOXUSR_ID() {
		return ICVESEGGPOXUSR_ID;
	}

	public void setICVESEGGPOXUSR_ID(String iCVESEGGPOXUSR_ID) {
		ICVESEGGPOXUSR_ID = iCVESEGGPOXUSR_ID;
	}
}
