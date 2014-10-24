package mx.gob.sct.utic.mimappir.db.postgreSQL.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEIMAGEN_PK;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Entity
@Table(name="MMIMAGENES")
public class MMIMAGENES {
	
	@EmbeddedId
	@JsonProperty("ICVEIMAGEN_PK")
	public ICVEIMAGEN_PK ICVEIMAGEN_PK;
	
	@Column(name="ICVECOMENTARIO", nullable=false)
	@JsonProperty("ICVECOMENTARIO")
	private int ICVECOMENTARIO;
	
	@JsonIgnore
	@Column(name="BLIMAGENBIT", nullable=true)
	private Blob BLIMAGENBIT;
	
	@Column(name="CTIPOIMAGEN", nullable=false)
	@JsonProperty("CTIPOIMAGEN")
	private String CTIPOIMAGEN;

	@JsonProperty("ICVEIMAGEN_ID")
	private String ICVEIMAGEN_ID;

	
	public ICVEIMAGEN_PK getICVEIMAGEN_PK() {
		return ICVEIMAGEN_PK;
	}

	public void setICVEIMAGEN_PK(ICVEIMAGEN_PK iCVEIMAGEN_PK) {
		ICVEIMAGEN_PK = iCVEIMAGEN_PK;
	}

	public int getICVECOMENTARIO() {
		return ICVECOMENTARIO;
	}

	public void setICVECOMENTARIO(int iCVECOMENTARIO) {
		ICVECOMENTARIO = iCVECOMENTARIO;
	}

	public Blob getBLIMAGENBIT() {
		return BLIMAGENBIT;
	}

	public void setBLIMAGENBIT(Blob bLIMAGENBIT) {
		BLIMAGENBIT = bLIMAGENBIT;
	}

	public String getCTIPOIMAGEN() {
		return CTIPOIMAGEN;
	}

	public void setCTIPOIMAGEN(String cTIPOIMAGEN) {
		CTIPOIMAGEN = cTIPOIMAGEN;
	}

	public String getICVEIMAGEN_ID() {
		ICVEIMAGEN_ID = getICVEIMAGEN_PK().getICVEIMAGEN()+"-";
		return ICVEIMAGEN_ID;
	}

	public void setICVEIMAGEN_ID(String iCVEIMAGEN_ID) {
		ICVEIMAGEN_PK newPK = new ICVEIMAGEN_PK();
		newPK.setICVEIMAGEN(Integer.parseInt(iCVEIMAGEN_ID.split("-")[0]));
		setICVEIMAGEN_PK(newPK);
		ICVEIMAGEN_ID = iCVEIMAGEN_ID;
	}
	
}
