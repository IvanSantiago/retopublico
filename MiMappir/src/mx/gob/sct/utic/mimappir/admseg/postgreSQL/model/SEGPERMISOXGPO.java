package mx.gob.sct.utic.mimappir.admseg.postgreSQL.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.pk.ICVESEGPERMISOXGPO_PK;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Entity
@Table(name="SEGPERMISOXGPO")
public class SEGPERMISOXGPO {
	
	@EmbeddedId
	@Column(name="ICVESEGPERMISOXGPO_PK", nullable=false)
	@JsonProperty("ICVESEGPERMISOXGPO_PK")
	private ICVESEGPERMISOXGPO_PK ICVESEGPERMISOXGPO_PK;
	
	@JsonProperty("ICVESEGPERMISOXGPO_ID")
	@Transient
	private String ICVESEGPERMISOXGPO_ID;

	@Column(name="LEJECUCION", nullable=false)
	@JsonProperty("LEJECUCION")
	private Short LEJECUCION;

	@Column(name="LACTUALIZACION", nullable=false)
	@JsonProperty("LACTUALIZACION")
	private Short LACTUALIZACION;

	public ICVESEGPERMISOXGPO_PK getICVESEGPERMISOXGPO_PK() {
		return ICVESEGPERMISOXGPO_PK;
	}

	public void setICVESEGPERMISOXGPO_PK(ICVESEGPERMISOXGPO_PK iCVESEGPERMISOXGPO_PK) {
		ICVESEGPERMISOXGPO_PK = iCVESEGPERMISOXGPO_PK;
	}

	public String getICVESEGPERMISOXGPO_ID() {
		return ICVESEGPERMISOXGPO_ID;
	}

	public void setICVESEGPERMISOXGPO_ID(String iCVESEGPERMISOXGPO_ID) {
		ICVESEGPERMISOXGPO_ID = iCVESEGPERMISOXGPO_ID;
	}

	public Short getLEJECUCION() {
		return LEJECUCION;
	}

	public void setLEJECUCION(Short lEJECUCION) {
		LEJECUCION = lEJECUCION;
	}

	public Short getLACTUALIZACION() {
		return LACTUALIZACION;
	}

	public void setLACTUALIZACION(Short lACTUALIZACION) {
		LACTUALIZACION = lACTUALIZACION;
	}

}
