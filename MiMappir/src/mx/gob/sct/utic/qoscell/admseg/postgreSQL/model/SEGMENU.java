package mx.gob.sct.utic.qoscell.admseg.postgreSQL.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Entity
@Table(name="SEGMENU")

public class SEGMENU {

	@EmbeddedId
	@Column(name="ICVEMENU_PK", nullable=false)
	@JsonProperty("ICVEMENU_PK")
	private ICVEMENU_PK ICVEMENU_PK;
	
	@JsonProperty("ICVESEGMENU_ID")
	@Transient
	private String ICVESEGMENU_ID;

	@Column(name="CREFERENCIA", nullable=false)
	@JsonProperty("CREFERENCIA")
	private String CREFERENCIA;

	@Column(name="CDSCMENU", nullable=false)
	@JsonProperty("CDSCMENU")
	private String CDSCMENU;
	
	@Column(name="IOPCPADRE", nullable=true)
	@JsonProperty("IOPCPADRE")
	private Long IOPCPADRE;

	@Column(name="LBLOQUEADO", nullable=false)
	@JsonProperty("LBLOQUEADO")
	private Short LBLOQUEADO;

	@Column(name="CNOMPAGINA", nullable=false)
	@JsonProperty("CNOMPAGINA")
	private String CNOMPAGINA;

	@Column(name="CCLASECSS", nullable=false)
	@JsonProperty("CCLASECSS")
	private String CCLASECSS;

	@Column(name="CICONCLS", nullable=false)
	@JsonProperty("CICONCLS")
	private String CICONCLS;



	public ICVEMENU_PK getICVEMENU_PK() {
		return ICVEMENU_PK;
	}

	public void setICVEMENU_PK(ICVEMENU_PK iCVEMENU_PK) {
		ICVEMENU_PK = iCVEMENU_PK;
	}

	public String getICVESEGMENU_ID() {
		return ICVESEGMENU_ID;
	}

	public void setICVESEGMENU_ID(String iCVESEGMENU_ID) {
		ICVESEGMENU_ID = iCVESEGMENU_ID;
	}

	public String getCREFERENCIA() {
		return CREFERENCIA;
	}

	public void setCREFERENCIA(String cREFERENCIA) {
		CREFERENCIA = cREFERENCIA;
	}

	public String getCDSCMENU() {
		return CDSCMENU;
	}

	public void setCDSCMENU(String cDSCMENU) {
		CDSCMENU = cDSCMENU;
	}

	public Long getIOPCPADRE() {
		return IOPCPADRE;
	}

	public void setIOPCPADRE(Long iOPCPADRE) {
		IOPCPADRE = iOPCPADRE;
	}

	public Short getLBLOQUEADO() {
		return LBLOQUEADO;
	}

	public void setLBLOQUEADO(Short lBLOQUEADO) {
		LBLOQUEADO = lBLOQUEADO;
	}

	public String getCNOMPAGINA() {
		return CNOMPAGINA;
	}

	public void setCNOMPAGINA(String cNOMPAGINA) {
		CNOMPAGINA = cNOMPAGINA;
	}

	public String getCCLASECSS() {
		return CCLASECSS;
	}

	public void setCCLASECSS(String cCLASECSS) {
		CCLASECSS = cCLASECSS;
	}

	public String getCICONCLS() {
		return CICONCLS;
	}

	public void setCICONCLS(String cICONCLS) {
		CICONCLS = cICONCLS;
	}
}
