package mx.gob.sct.utic.mimappir.admseg.postgreSQL.model;

import java.io.Serializable;

import javax.persistence.Column;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ICVESEGGPOXUSR_PK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6962137119750640512L;
	@Column(name="ICVESISTEMA", nullable=false)
	@JsonProperty("ICVESISTEMA")
	private Short ICVESISTEMA;
	
	@Column(name="ICVEUSUARIO", nullable=false)
	@JsonProperty("ICVEUSUARIO")
	private Long ICVEUSUARIO;

	@Column(name="ICVEGRUPO", nullable=false)
	@JsonProperty("ICVEGRUPO")
	private Short ICVEGRUPO;

	public Short getICVESISTEMA() {
		return ICVESISTEMA;
	}

	public void setICVESISTEMA(Short iCVESISTEMA) {
		ICVESISTEMA = iCVESISTEMA;
	}

	public Long getICVEUSUARIO() {
		return ICVEUSUARIO;
	}

	public void setICVEUSUARIO(Long iCVEUSUARIO) {
		ICVEUSUARIO = iCVEUSUARIO;
	}

	public Short getICVEGRUPO() {
		return ICVEGRUPO;
	}

	public void setICVEGRUPO(Short iCVEGRUPO) {
		ICVEGRUPO = iCVEGRUPO;
	}

}