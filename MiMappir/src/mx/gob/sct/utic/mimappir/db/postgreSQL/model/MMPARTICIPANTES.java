package mx.gob.sct.utic.mimappir.db.postgreSQL.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEPARTICIPANTE_PK;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Entity
@Table(name="MMPARTICIPANTES")
public class MMPARTICIPANTES {

	@EmbeddedId
	@JsonProperty("ICVEPARTICIPANTE_PK")
	public ICVEPARTICIPANTE_PK ICVEPARTICIPANTE_PK;
	
	@Column(name="CPARTICIPANTE", nullable=false)
	@JsonProperty("CPARTICIPANTE")
	private String CPARTICIPANTE;
	
	@Column(name="CFBCUENTA", nullable=false)
	@JsonProperty("CFBCUENTA")
	private String CFBCUENTA;
	
	@Column(name="CCORREO", nullable=false)
	@JsonProperty("CCORREO")
	private String CCORREO;

	@Column(name="CNOMBRE", nullable=false)
	@JsonProperty("CNOMBRE")
	private String CNOMBRE;
	
	@Column(name="DTFECHAREGISTRO", nullable=false)
	@JsonProperty("DTFECHAREGISTRO")
	private String DTFECHAREGISTRO;

	@JsonProperty("ICVEPARTICIPANTE_ID")
	private String ICVEPARTICIPANTE_ID;

	
	public ICVEPARTICIPANTE_PK getICVEPARTICIPANTE_PK() {
		return ICVEPARTICIPANTE_PK;
	}

	public void setICVEPARTICIPANTE_PK(ICVEPARTICIPANTE_PK iCVEPARTICIPANTE_PK) {
		ICVEPARTICIPANTE_PK = iCVEPARTICIPANTE_PK;
	}

	public String getCPARTICIPANTE() {
		return CPARTICIPANTE;
	}

	public void setCPARTICIPANTE(String cPARTICIPANTE) {
		CPARTICIPANTE = cPARTICIPANTE;
	}

	public String getCFBCUENTA() {
		return CFBCUENTA;
	}

	public void setCFBCUENTA(String cFBCUENTA) {
		CFBCUENTA = cFBCUENTA;
	}

	public String getCCORREO() {
		return CCORREO;
	}

	public void setCCORREO(String cCORREO) {
		CCORREO = cCORREO;
	}

	public String getCNOMBRE() {
		return CNOMBRE;
	}

	public void setCNOMBRE(String cNOMBRE) {
		CNOMBRE = cNOMBRE;
	}

	public String getDTFECHAREGISTRO() {
		return DTFECHAREGISTRO;
	}

	public void setDTFECHAREGISTRO(String dTFECHAREGISTRO) {
		DTFECHAREGISTRO = dTFECHAREGISTRO;
	}

	public String getICVEPARTICIPANTE_ID() {
		ICVEPARTICIPANTE_ID = getICVEPARTICIPANTE_PK().getICVEPARTICIPANTE()+"-";
		return ICVEPARTICIPANTE_ID;
	}

	public void setICVEPARTICIPANTE_ID(String iCVEPARTICIPANTE_ID) {
		ICVEPARTICIPANTE_PK newPK = new ICVEPARTICIPANTE_PK();
		newPK.setICVEPARTICIPANTE(Integer.parseInt(iCVEPARTICIPANTE_ID.split("-")[0]));
		setICVEPARTICIPANTE_PK(newPK);
		ICVEPARTICIPANTE_ID = iCVEPARTICIPANTE_ID;
	}
	
}
