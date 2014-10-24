package mx.gob.sct.utic.mimappir.db.postgreSQL.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEPREMIACION_PK;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;


@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Entity
@Table(name="MMPREMIACIONES")
public class MMPREMIACIONES {
	
	@EmbeddedId
	@JsonProperty("ICVEPREMIACION_PK")
	public ICVEPREMIACION_PK ICVEPREMIACION_PK;
	
	@Column(name="IPUBLICADO", nullable=false)
	@JsonProperty("IPUBLICADO")
	private int IPUBLICADO;

	@Column(name="DTFECHAPREMIACION", nullable=false)
	@JsonProperty("DTFECHAPREMIACION")
	private String DTFECHAPREMIACION;
	
	@Column(name="ICVEPREMIO", nullable=false)
	@JsonProperty("ICVEPREMIO")
	private int ICVEPREMIO;

	@Column(name="ICVEUSUARIOPREMIA", nullable=false)
	@JsonProperty("ICVEUSUARIOPREMIA")
	private int ICVEUSUARIOPREMIA;

	@JsonProperty("ICVEPREMIACION_ID")
	private String ICVEPREMIACION_ID;
	
	
	public ICVEPREMIACION_PK getICVEPREMIACION_PK() {
		return ICVEPREMIACION_PK;
	}

	public void setICVEPREMIACION_PK(ICVEPREMIACION_PK iCVEPREMIACION_PK) {
		ICVEPREMIACION_PK = iCVEPREMIACION_PK;
	}

	public int getIPUBLICADO() {
		return IPUBLICADO;
	}

	public void setIPUBLICADO(int iPUBLICADO) {
		IPUBLICADO = iPUBLICADO;
	}

	public String getDTFECHAPREMIACION() {
		return DTFECHAPREMIACION;
	}

	public void setDTFECHAPREMIACION(String dTFECHAPREMIACION) {
		DTFECHAPREMIACION = dTFECHAPREMIACION;
	}

	public int getICVEPREMIO() {
		return ICVEPREMIO;
	}

	public void setICVEPREMIO(int iCVEPREMIO) {
		ICVEPREMIO = iCVEPREMIO;
	}

	public int getICVEUSUARIOPREMIA() {
		return ICVEUSUARIOPREMIA;
	}

	public void setICVEUSUARIOPREMIA(int iCVEUSUARIOPREMIA) {
		ICVEUSUARIOPREMIA = iCVEUSUARIOPREMIA;
	}

	public String getICVEPREMIACION_ID() {
		ICVEPREMIACION_ID = getICVEPREMIACION_PK().getICVEPREMIACION()+"-";
		return ICVEPREMIACION_ID;
	}

	public void setICVEPREMIACION_ID(String iCVEPREMIACION_ID) {
		ICVEPREMIACION_PK newPK = new ICVEPREMIACION_PK();
		newPK.setICVEPREMIACION(Integer.parseInt(iCVEPREMIACION_ID.split("-")[0]));
		setICVEPREMIACION_PK(newPK);
		ICVEPREMIACION_ID = iCVEPREMIACION_ID;
	}

}
