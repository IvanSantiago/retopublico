package mx.gob.sct.utic.mimappir.admseg.postgreSQL.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.pk.ICVESEGGPOXUSR_PK;

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
		ICVESEGGPOXUSR_ID = 
		getICVESEGGPOXUSR_PK().getICVESISTEMA()+"-"+
		getICVESEGGPOXUSR_PK().getICVEUSUARIO()+"-"+
		getICVESEGGPOXUSR_PK().getICVEGRUPO()+"-";
		return ICVESEGGPOXUSR_ID;
	}

	public void setICVESEGGPOXUSR_ID(String iCVESEGGPOXUSR_ID) {
		ICVESEGGPOXUSR_PK newPK = new ICVESEGGPOXUSR_PK();
		newPK.setICVESISTEMA(Short.parseShort(iCVESEGGPOXUSR_ID.split("-")[0]));
		newPK.setICVEUSUARIO(Long.parseLong(iCVESEGGPOXUSR_ID.split("-")[1]));
		newPK.setICVEGRUPO(Short.parseShort(iCVESEGGPOXUSR_ID.split("-")[2]));
		setICVESEGGPOXUSR_PK(newPK);
		ICVESEGGPOXUSR_ID = iCVESEGGPOXUSR_ID;
	}
}
