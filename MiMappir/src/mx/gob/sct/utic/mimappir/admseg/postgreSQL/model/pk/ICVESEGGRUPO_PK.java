package mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ICVESEGGRUPO_PK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6962137119750640512L;
	@Column(name = "ICVESISTEMA")
	@JsonProperty("ICVESISTEMA")
	private Short ICVESISTEMA;

	@Column(name="ICVEGRUPO")
	@JsonProperty("ICVEGRUPO")
	private Short ICVEGRUPO;

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

}