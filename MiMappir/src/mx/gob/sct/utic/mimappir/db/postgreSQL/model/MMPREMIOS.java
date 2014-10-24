package mx.gob.sct.utic.mimappir.db.postgreSQL.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEPREMIO_PK;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Entity
@Table(name="MMPREMIOS")
public class MMPREMIOS {

	@EmbeddedId
	@JsonProperty("ICVEPREMIO_PK")
	public ICVEPREMIO_PK ICVEPREMIO_PK;
	
	@Column(name="CPREMIO", nullable=false)
	@JsonProperty("CPREMIO")
	private String CPREMIO;
	
	@JsonProperty("ICVEPREMIO_ID")
	private String ICVEPREMIO_ID;

	
	public ICVEPREMIO_PK getICVEPREMIO_PK() {
		return ICVEPREMIO_PK;
	}

	public void setICVEPREMIO_PK(ICVEPREMIO_PK iCVEPREMIO_PK) {
		ICVEPREMIO_PK = iCVEPREMIO_PK;
	}

	public String getCPREMIO() {
		return CPREMIO;
	}

	public void setCPREMIO(String cPREMIO) {
		CPREMIO = cPREMIO;
	}

	public String getICVEPREMIO_ID() {
		ICVEPREMIO_ID = getICVEPREMIO_PK().getICVEPREMIO()+"-";
		return ICVEPREMIO_ID;
	}

	public void setICVEPREMIO_ID(String iCVEPREMIO_ID) {
		ICVEPREMIO_PK newPK = new ICVEPREMIO_PK();
		newPK.setICVEPREMIO(Integer.parseInt(iCVEPREMIO_ID.split("-")[0]));
		setICVEPREMIO_PK(newPK);
		ICVEPREMIO_ID = iCVEPREMIO_ID;
	}

}
