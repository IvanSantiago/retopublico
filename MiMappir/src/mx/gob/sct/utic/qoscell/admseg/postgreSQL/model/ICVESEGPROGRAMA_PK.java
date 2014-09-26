package mx.gob.sct.utic.qoscell.admseg.postgreSQL.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ICVESEGPROGRAMA_PK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5769359423642429606L;

	/**
	 * 
	 */
	@Column(name="ICVESISTEMA", nullable=false)
	@JsonProperty("ICVESISTEMA")
	private Short ICVESISTEMA;

	@Column(name="ICVEPROGRAMA", nullable=false)
	@JsonProperty("ICVEPROGRAMA")
	private Short ICVEPROGRAMA;

	public Short getICVESISTEMA() {
		return ICVESISTEMA;
	}

	public void setICVESISTEMA(Short iCVESISTEMA) {
		ICVESISTEMA = iCVESISTEMA;
	}

	public Short getICVEPROGRAMA() {
		return ICVEPROGRAMA;
	}

	public void setICVEPROGRAMA(Short iCVEPROGRAMA) {
		ICVEPROGRAMA = iCVEPROGRAMA;
	}


}