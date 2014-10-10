package mx.gob.sct.utic.mimappir.db.postgreSQL.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEGRLPAIS_PK;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * GRLPAIS POJO
 * 
 * @author Ivan Santiago
 *
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Entity
@Table(name="GRLPAIS")
public class GRLPAIS{
	@EmbeddedId
	@JsonProperty("ICVEGRLPAIS_PK")
	public ICVEGRLPAIS_PK ICVEGRLPAIS_PK;
	@Column(name="CNOMBRE", nullable=false)
	@JsonProperty("CNOMBRE")
	private String CNOMBRE;
	@Column(name="CABREVIATURA", nullable=false)
	@JsonProperty("CABREVIATURA")
	private String CABREVIATURA;
	@JsonProperty("ICVEGRLPAIS_ID")
	private String ICVEGRLPAIS_ID;
	
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
	
	public void setICVEGRLPAIS_PK(ICVEGRLPAIS_PK iCVEGRLPAIS_PK) {
		ICVEGRLPAIS_PK = iCVEGRLPAIS_PK;
	}
	public  ICVEGRLPAIS_PK getICVEGRLPAIS_PK() {
		return ICVEGRLPAIS_PK;
	}
	public void setICVEGRLPAIS_ID(String iCVEGRLPAIS_ID) {
		ICVEGRLPAIS_PK newPK = new ICVEGRLPAIS_PK();
		newPK.setICVEPAIS(Integer.parseInt(iCVEGRLPAIS_ID.split("-")[0]));
		setICVEGRLPAIS_PK(newPK);
		ICVEGRLPAIS_ID = iCVEGRLPAIS_ID;
	}
	public String getICVEGRLPAIS_ID() {
		ICVEGRLPAIS_ID = getICVEGRLPAIS_PK().getICVEPAIS()+"-";
		return ICVEGRLPAIS_ID;
	}

}
