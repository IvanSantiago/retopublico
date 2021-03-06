package mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk;

import java.io.Serializable;

import javax.persistence.Column;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ICVEGRLMUNICIPIO_PK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4525086574589254870L;
	@Column(name="ICVEPAIS", nullable=false)
	@JsonProperty("ICVEPAIS")
	private Integer ICVEPAIS;
	@Column(name="ICVEENTIDADFED", nullable=false)
	@JsonProperty("ICVEENTIDADFED")
	private Integer ICVEENTIDADFED;
	@Column(name="ICVEMUNICIPIO", nullable=false)
	@JsonProperty("ICVEMUNICIPIO")
	private Integer ICVEMUNICIPIO;
	
	public void setICVEENTIDADFED(Integer iCVEENTIDADFED) {
		ICVEENTIDADFED = iCVEENTIDADFED;
	}
	public Integer getICVEENTIDADFED() {
		return ICVEENTIDADFED;
	}
	
	public void setICVEPAIS(Integer iCVEPAIS) {
		ICVEPAIS = iCVEPAIS;
	}
	public Integer getICVEPAIS() {
		return ICVEPAIS;
	}
	public void setICVEMUNICIPIO(Integer iCVEMUNICIPIO) {
		ICVEMUNICIPIO = iCVEMUNICIPIO;
	}
	public Integer getICVEMUNICIPIO() {
		return ICVEMUNICIPIO;
	}
	
}