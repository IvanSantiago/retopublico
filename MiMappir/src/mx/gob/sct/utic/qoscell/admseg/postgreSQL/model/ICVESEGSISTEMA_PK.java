package mx.gob.sct.utic.qoscell.admseg.postgreSQL.model;

import java.io.Serializable;

import javax.persistence.Column;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ICVESEGSISTEMA_PK implements Serializable{
		/**
	 * 
	 */
	@Column(name="ICVESISTEMA", nullable=false)
	@JsonProperty("ICVESISTEMA")
	private Short ICVESISTEMA;

	public Short getICVESISTEMA() {
		return ICVESISTEMA;
	}

	public void setICVESISTEMA(Short iCVESISTEMA) {
		ICVESISTEMA = iCVESISTEMA;
	}
}