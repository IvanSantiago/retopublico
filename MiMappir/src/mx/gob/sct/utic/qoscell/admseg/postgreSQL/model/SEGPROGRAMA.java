package mx.gob.sct.utic.qoscell.admseg.postgreSQL.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Entity
@Table(name="SEGPROGRAMA")
public class SEGPROGRAMA {
	@EmbeddedId
	@Column(name="ICVESEGPROGRAMA_PK", nullable=false)
	@JsonProperty("ICVESEGPROGRAMA_PK")
	private ICVESEGPROGRAMA_PK ICVESEGPROGRAMA_PK;
	
	@JsonProperty("ICVESEGPROGRAMA_ID")
	@Transient
	private String ICVESEGPROGRAMA_ID;
	
	@Column(name="CNOMBRE", nullable=false)
	@JsonProperty("CNOMBRE")
	private String CNOMBRE;
	
	@Column(name="CDSCPROGRAMA", nullable=false)
	@JsonProperty("CDSCPROGRAMA")
	private String CDSCPROGRAMA;
	
	@Column(name="LTIENEESCRITURA", nullable=false)
	@JsonProperty("LTIENEESCRITURA")
	private Short LTIENEESCRITURA;
	
	@Column(name="LCONFIGURABLE", nullable=false)
	@JsonProperty("LCONFIGURABLE")
	private Short LCONFIGURABLE;
	
	@Column(name="LBLOQUEADO", nullable=false)
	@JsonProperty("LBLOQUEADO")
	private Short LBLOQUEADO;

	public ICVESEGPROGRAMA_PK getICVESEGPROGRAMA_PK() {
		return ICVESEGPROGRAMA_PK;
	}
	public void setICVESEGPROGRAMA_PK(ICVESEGPROGRAMA_PK iCVESEGPROGRAMA_PK) {
		ICVESEGPROGRAMA_PK = iCVESEGPROGRAMA_PK;
	}
	public String getICVESEGPROGRAMA_ID() {
		return ICVESEGPROGRAMA_ID;
	}
	public void setICVESEGPROGRAMA_ID(String iCVESEGPROGRAMA_ID) {
		ICVESEGPROGRAMA_ID = iCVESEGPROGRAMA_ID;
	}
	public String getCNOMBRE() {
		return CNOMBRE;
	}
	public void setCNOMBRE(String cNOMBRE) {
		CNOMBRE = cNOMBRE;
	}
	public String getCDSCPROGRAMA() {
		return CDSCPROGRAMA;
	}
	public void setCDSCPROGRAMA(String cDSCPROGRAMA) {
		CDSCPROGRAMA = cDSCPROGRAMA;
	}
	public Short getLTIENEESCRITURA() {
		return LTIENEESCRITURA;
	}
	public void setLTIENEESCRITURA(Short lTIENEESCRITURA) {
		LTIENEESCRITURA = lTIENEESCRITURA;
	}
	public Short getLCONFIGURABLE() {
		return LCONFIGURABLE;
	}
	public void setLCONFIGURABLE(Short lCONFIGURABLE) {
		LCONFIGURABLE = lCONFIGURABLE;
	}
	public Short getLBLOQUEADO() {
		return LBLOQUEADO;
	}
	public void setLBLOQUEADO(Short lBLOQUEADO) {
		LBLOQUEADO = lBLOQUEADO;
	}
}
