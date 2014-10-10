package mx.gob.sct.utic.mimappir.admseg.postgreSQL.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.pk.ICVESEGSISTEMA_PK;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * SEGSISTEMA POJO
 * 
 * @author Ivan Santiago
 *
 */

@JsonAutoDetect
@Entity
@Table(name="SEGSISTEMA")
public class SEGSISTEMA {
	@EmbeddedId
	@Column(name="ICVESEGSISTEMA_PK", nullable=false)
	@JsonProperty("ICVESEGSISTEMA_PK")
	private ICVESEGSISTEMA_PK ICVESEGSISTEMA_PK;
	
	@JsonProperty("ICVESEGSISTEMA_ID")
	@Transient
	private String ICVESEGSISTEMA_ID;
	
	@Column(name="ICVEUSUARIO", nullable=true)
	@JsonProperty("ICVEUSUARIO")
	private Long ICVEUSUARIO;
	
	@Column(name="CNOMBRE", nullable=false)
	@JsonProperty("CNOMBRE")
	private String CNOMBRE;
	
	@Column(name="CDSCSISTEMA", nullable=false)
	@JsonProperty("CDSCSISTEMA")
	private String CDSCSISTEMA;
	
	@Column(name="LBLOQUEADO", nullable=false)
	@JsonProperty("LBLOQUEADO")
	private Short LBLOQUEADO;

	public Long getICVEUSUARIO() {
		return ICVEUSUARIO;
	}

	public void setICVEUSUARIO(Long iCVEUSUARIO) {
		ICVEUSUARIO = iCVEUSUARIO;
	}

	public ICVESEGSISTEMA_PK getICVESEGSISTEMA_PK() {
		return ICVESEGSISTEMA_PK;
	}

	public void setICVESEGSISTEMA_PK(ICVESEGSISTEMA_PK iCVESEGSISTEMA_PK) {
		ICVESEGSISTEMA_PK = iCVESEGSISTEMA_PK;
	}

	public String getICVESEGSISTEMA_ID() {
		return ICVESEGSISTEMA_ID;
	}

	public void setICVESEGSISTEMA_ID(String iCVESEGSISTEMA_ID) {
		ICVESEGSISTEMA_ID = iCVESEGSISTEMA_ID;
	}

	public String getCNOMBRE() {
		return CNOMBRE;
	}

	public void setCNOMBRE(String cNOMBRE) {
		CNOMBRE = cNOMBRE;
	}

	public String getCDSCSISTEMA() {
		return CDSCSISTEMA;
	}

	public void setCDSCSISTEMA(String cDSCSISTEMA) {
		CDSCSISTEMA = cDSCSISTEMA;
	}

	public Short getLBLOQUEADO() {
		return LBLOQUEADO;
	}

	public void setLBLOQUEADO(Short lBLOQUEADO) {
		LBLOQUEADO = lBLOQUEADO;
	}

 
}
