package mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ICVESEGPERMISOXGPO_PK implements Serializable{
	@Column(name="ICVESISTEMA")
	@JsonProperty("ICVESISTEMA")
	private Short ICVESISTEMA;
	
	@Column(name="ICVEGRUPO", nullable=false)
	@JsonProperty("ICVEGRUPO")
	private Short ICVEGRUPO;
	
	@Column(name="ICVEPROGRAMA", nullable=false)
	@JsonProperty("ICVEPROGRAMA")
	private Short ICVEPROGRAMA;
	/**
	 * 
	 */
	private static final long serialVersionUID = -6962137119750640512L;
	public Short getICVESISTEMA() {
		return ICVESISTEMA;
	}
	public void setICVESISTEMA(Short iCVESISTEMA) {
		ICVESISTEMA = iCVESISTEMA;
	}
	public Short getICVEGRUPO() {
		return ICVEGRUPO;
	}
	public void setICVEGRUPO(Short iCVEGRUPO) {
		ICVEGRUPO = iCVEGRUPO;
	}
	public Short getICVEPROGRAMA() {
		return ICVEPROGRAMA;
	}
	public void setICVEPROGRAMA(Short iCVEPROGRAMA) {
		ICVEPROGRAMA = iCVEPROGRAMA;
	}

}