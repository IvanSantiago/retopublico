package mx.gob.sct.utic.mimappir.db.postgreSQL.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEUSUARIO_PK;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Entity
@Table(name="MMUSUARIOS")
public class MMUSUARIOS {
	
	@EmbeddedId
	@JsonProperty("ICVEUSUARIO_PK")
	public ICVEUSUARIO_PK ICVEUSUARIO_PK;
	
	@Column(name="CNOMBRE", nullable=false)
	@JsonProperty("CNOMBRE")
	private String CNOMBRE;
	
	@Column(name="DTFECHAREGISTRO", nullable=false)
	@JsonProperty("DTFECHAREGISTRO")
	private String DTFECHAREGISTRO;
	
	@JsonProperty("ICVEUSUARIO_ID")
	private String ICVEUSUARIO_ID;


	public ICVEUSUARIO_PK getICVEUSUARIO_PK() {
		return ICVEUSUARIO_PK;
	}

	public void setICVEUSUARIO_PK(ICVEUSUARIO_PK iCVEUSUARIO_PK) {
		ICVEUSUARIO_PK = iCVEUSUARIO_PK;
	}

	public String getCNOMBRE() {
		return CNOMBRE;
	}

	public void setCNOMBRE(String cNOMBRE) {
		CNOMBRE = cNOMBRE;
	}
	
	public String getDTFECHAREGISTRO() {
		return DTFECHAREGISTRO;
	}

	public void setDTFECHAREGISTRO(String dTFECHAREGISTRO) {
		DTFECHAREGISTRO = dTFECHAREGISTRO;
	}

	public String getICVEUSUARIO_ID() {
		ICVEUSUARIO_ID = getICVEUSUARIO_PK().getICVEUSUARIO()+"-";
		return ICVEUSUARIO_ID;
	}

	public void setICVEUSUARIO_ID(String iCVEUSUARIO_ID) {
		ICVEUSUARIO_PK newPK = new ICVEUSUARIO_PK();
		newPK.setICVEUSUARIO(Integer.parseInt(iCVEUSUARIO_ID.split("-")[0]));
		setICVEUSUARIO_PK(newPK);
		ICVEUSUARIO_ID = iCVEUSUARIO_ID;
	}	
	
}
