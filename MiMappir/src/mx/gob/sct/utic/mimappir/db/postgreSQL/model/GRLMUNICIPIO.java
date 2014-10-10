package mx.gob.sct.utic.mimappir.db.postgreSQL.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ExcludeDefaultListeners;
import javax.persistence.Table;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEGRLMUNICIPIO_PK;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEGRLMUNICIPIO_PK;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * GRLMUNICIPIO POJO
 * 
 * @author Ivan Santiago
 *
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Entity
@Table(name="GRLMUNICIPIO")
public class GRLMUNICIPIO{
	@EmbeddedId
	@JsonProperty("ICVEGRLMUNICIPIO_PK")
	public ICVEGRLMUNICIPIO_PK ICVEGRLMUNICIPIO_PK;
	@Column(name="CNOMBRE", nullable=false)
	@JsonProperty("CNOMBRE")
	private String CNOMBRE;
	@Column(name="CABREVIATURA", nullable=false)
	@JsonProperty("CABREVIATURA")
	private String CABREVIATURA;
	@JsonProperty("ICVEGRLMUNICIPIO_ID")
	private String ICVEGRLMUNICIPIO_ID;
	
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
	
	public void setICVEGRLMUNICIPIO_PK(ICVEGRLMUNICIPIO_PK iCVEGRLMUNICIPIO_PK) {
		ICVEGRLMUNICIPIO_PK = iCVEGRLMUNICIPIO_PK;
	}
	public  ICVEGRLMUNICIPIO_PK getICVEGRLMUNICIPIO_PK() {
		return ICVEGRLMUNICIPIO_PK;
	}
	public void setICVEGRLMUNICIPIO_ID(String iCVEGRLMUNICIPIO_ID) {
		ICVEGRLMUNICIPIO_PK newPK = new ICVEGRLMUNICIPIO_PK();
		newPK.setICVEPAIS(Integer.parseInt(iCVEGRLMUNICIPIO_ID.split("-")[0]));
		newPK.setICVEENTIDADFED(Integer.parseInt(iCVEGRLMUNICIPIO_ID.split("-")[1]));
		newPK.setICVEMUNICIPIO(Integer.parseInt(iCVEGRLMUNICIPIO_ID.split("-")[2]));
		setICVEGRLMUNICIPIO_PK(newPK);
		ICVEGRLMUNICIPIO_ID = iCVEGRLMUNICIPIO_ID;
	}
	public String getICVEGRLMUNICIPIO_ID() {
		ICVEGRLMUNICIPIO_ID = getICVEGRLMUNICIPIO_PK().getICVEPAIS()+"-"
		+getICVEGRLMUNICIPIO_PK().getICVEENTIDADFED()+"-"
		+getICVEGRLMUNICIPIO_PK().getICVEMUNICIPIO()+"-";
		return ICVEGRLMUNICIPIO_ID;
	}

}
