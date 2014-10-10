package mx.gob.sct.utic.mimappir.db.postgreSQL.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ExcludeDefaultListeners;
import javax.persistence.Table;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEGRLENTIDADFED_PK;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEGRLPAIS_PK;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * GRLENTIDADFED POJO
 * 
 * @author Ivan Santiago
 *
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Entity
@Table(name="GRLENTIDADFED")
public class GRLENTIDADFED{
	@EmbeddedId
	@JsonProperty("ICVEGRLENTIDADFED_PK")
	public ICVEGRLENTIDADFED_PK ICVEGRLENTIDADFED_PK;
	@Column(name="CNOMBRE", nullable=false)
	@JsonProperty("CNOMBRE")
	private String CNOMBRE;
	@Column(name="CABREVIATURA", nullable=false)
	@JsonProperty("CABREVIATURA")
	private String CABREVIATURA;
	@JsonProperty("ICVEGRLENTIDADFED_ID")
	private String ICVEGRLENTIDADFED_ID;
	
	public void setCNOMBRE(String cNOMBRE) {
		CNOMBRE = cNOMBRE;
	}
	public String getCNOMBRE() {
		return CNOMBRE;
	}
	
	public void setCABREVIATURA(String cABREVIATURA) {
		CABREVIATURA = cABREVIATURA;
	}
	public String getCABREVIATURA() {
		return CABREVIATURA;
	}
	
	public void setICVEGRLENTIDADFED_PK(ICVEGRLENTIDADFED_PK iCVEGRLENTIDADFED_PK) {
		ICVEGRLENTIDADFED_PK = iCVEGRLENTIDADFED_PK;
	}
	public  ICVEGRLENTIDADFED_PK getICVEGRLENTIDADFED_PK() {
		return ICVEGRLENTIDADFED_PK;
	}
	public void setICVEGRLENTIDADFED_ID(String iCVEGRLENTIDADFED_ID) {
		ICVEGRLENTIDADFED_PK newPK = new ICVEGRLENTIDADFED_PK();
		newPK.setICVEPAIS(Integer.parseInt(iCVEGRLENTIDADFED_ID.split("-")[0]));
		newPK.setICVEENTIDADFED(Integer.parseInt(iCVEGRLENTIDADFED_ID.split("-")[1]));
		setICVEGRLENTIDADFED_PK(newPK);
		ICVEGRLENTIDADFED_ID = iCVEGRLENTIDADFED_ID;
	}
	public String getICVEGRLENTIDADFED_ID() {
		ICVEGRLENTIDADFED_ID = 
			getICVEGRLENTIDADFED_PK().getICVEPAIS()+"-"
		+getICVEGRLENTIDADFED_PK().getICVEENTIDADFED()+"-";
		return ICVEGRLENTIDADFED_ID;
	}

}
