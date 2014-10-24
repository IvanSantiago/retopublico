package mx.gob.sct.utic.mimappir.db.postgreSQL.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVECATEGORIA_PK;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Entity
@Table(name="MMCATEGORIAS")
public class MMCATEGORIAS {
	
	@EmbeddedId
	@JsonProperty("ICVECATEGORIA_PK")
	public ICVECATEGORIA_PK ICVECATEGORIA_PK;
	
	@Column(name="CDESCRIPCION", nullable=false)
	@JsonProperty("CDESCRIPCION")
	private String CDESCRIPCION;
	
	@JsonProperty("ICVECATEGORIA_ID")
	private String ICVECATEGORIA_ID;

	public ICVECATEGORIA_PK getICVECATEGORIA_PK() {
		return ICVECATEGORIA_PK;
	}

	public void setICVECATEGORIA_PK(ICVECATEGORIA_PK iCVECATEGORIA_PK) {
		ICVECATEGORIA_PK = iCVECATEGORIA_PK;
	}

	public String getCDESCRIPCION() {
		return CDESCRIPCION;
	}

	public void setCDESCRIPCION(String cDESCRIPCION) {
		CDESCRIPCION = cDESCRIPCION;
	}

	public String getICVECATEGORIA_ID() {
		ICVECATEGORIA_ID = getICVECATEGORIA_PK().getICVECATEGORIA()+"-";
		return ICVECATEGORIA_ID;
	}

	public void setICVECATEGORIA_ID(String iCVECATEGORIA_ID) {
		ICVECATEGORIA_PK newPK = new ICVECATEGORIA_PK();
		newPK.setICVECATEGORIA(Integer.parseInt(iCVECATEGORIA_ID.split("-")[0]));
		setICVECATEGORIA_PK(newPK);
		ICVECATEGORIA_ID = iCVECATEGORIA_ID;
	}	
	
}

