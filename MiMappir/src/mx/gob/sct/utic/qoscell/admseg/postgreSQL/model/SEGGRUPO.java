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

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Entity
@Table(name="SEGGRUPO")
public class SEGGRUPO {

	@EmbeddedId
	@Column(name="ICVESEGGRUPO_PK", nullable=false)
	@JsonProperty("ICVESEGGRUPO_PK")
	private ICVESEGGRUPO_PK ICVESEGGRUPO_PK;
	
	@JsonProperty("ICVESEGGRUPO_ID")
	@Transient
	private String ICVESEGGRUPO_ID;
	
	@Column(name="CDSCGRUPO", nullable=false)
	@JsonProperty("CDSCGRUPO")
	private String CDSCGRUPO;

	@Column(name="LBLOQUEADO", nullable=false)
	@JsonProperty("LBLOQUEADO")
	private Short LBLOQUEADO;

	public ICVESEGGRUPO_PK getICVESEGGRUPO_PK() {
		return ICVESEGGRUPO_PK;
	}

	public void setICVESEGGRUPO_PK(ICVESEGGRUPO_PK iCVESEGGRUPO_PK) {
		ICVESEGGRUPO_PK = iCVESEGGRUPO_PK;
	}

	public String getICVESEGGRUPO_ID() {
		return ICVESEGGRUPO_ID;
	}

	public void setICVESEGGRUPO_ID(String iCVESEGGRUPO_ID) {
		ICVESEGGRUPO_ID = iCVESEGGRUPO_ID;
	}

	public String getCDSCGRUPO() {
		return CDSCGRUPO;
	}

	public void setCDSCGRUPO(String cDSCGRUPO) {
		CDSCGRUPO = cDSCGRUPO;
	}

	public Short getLBLOQUEADO() {
		return LBLOQUEADO;
	}

	public void setLBLOQUEADO(Short lBLOQUEADO) {
		LBLOQUEADO = lBLOQUEADO;
	}
}
