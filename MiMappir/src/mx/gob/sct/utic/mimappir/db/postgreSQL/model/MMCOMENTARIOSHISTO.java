package mx.gob.sct.utic.mimappir.db.postgreSQL.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVECOMENTARIO_PK;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Entity
@Table(name="MMCOMENTARIOSHISTO")
public class MMCOMENTARIOSHISTO {
	
	@EmbeddedId
	@JsonProperty("ICVECOMENTARIO_PK")
	public ICVECOMENTARIO_PK ICVECOMENTARIO_PK;
	
	@Column(name="ICVEPARTICIPANTE", nullable=false)
	@JsonProperty("ICVEPARTICIPANTE")
	private int ICVEPARTICIPANTE;

	@Column(name="IPARTICIPA", nullable=false)
	@JsonProperty("IPARTICIPA")
	private int IPARTICIPA;

	@Column(name="DTFECHAREVISION", nullable=false)
	@JsonProperty("DTFECHAREVISION")
	private String DTFECHAREVISION;

	@Column(name="DTFECHASORTEO", nullable=false)
	@JsonProperty("DTFECHASORTEO")
	private String DTFECHASORTEO;
	
	@Column(name="IPUBLICADO", nullable=false)
	@JsonProperty("IPUBLICADO")
	private int IPUBLICADO;
	
	@Column(name="ICVEUSUARIOREVISOR", nullable=false)
	@JsonProperty("ICVEUSUARIOREVISOR")
	private int ICVEUSUARIOREVISOR;
	
	@Column(name="ICVEPREMIACION", nullable=false)
	@JsonProperty("ICVEPREMIACION")
	private int ICVEPREMIACION;

	@JsonProperty("ICVECOMENTARIO_ID")
	private String ICVECOMENTARIO_ID;

	
	public ICVECOMENTARIO_PK getICVECOMENTARIO_PK() {
		return ICVECOMENTARIO_PK;
	}

	public void setICVECOMENTARIO_PK(ICVECOMENTARIO_PK iCVECOMENTARIO_PK) {
		ICVECOMENTARIO_PK = iCVECOMENTARIO_PK;
	}

	public int getICVEPARTICIPANTE() {
		return ICVEPARTICIPANTE;
	}

	public void setICVEPARTICIPANTE(int iCVEPARTICIPANTE) {
		ICVEPARTICIPANTE = iCVEPARTICIPANTE;
	}

	public int getIPARTICIPA() {
		return IPARTICIPA;
	}

	public void setIPARTICIPA(int iPARTICIPA) {
		IPARTICIPA = iPARTICIPA;
	}

	public String getDTFECHAREVISION() {
		return DTFECHAREVISION;
	}

	public void setDTFECHAREVISION(String dTFECHAREVISION) {
		DTFECHAREVISION = dTFECHAREVISION;
	}

	public String getDTFECHASORTEO() {
		return DTFECHASORTEO;
	}

	public void setDTFECHASORTEO(String dTFECHASORTEO) {
		DTFECHASORTEO = dTFECHASORTEO;
	}

	public int getIPUBLICADO() {
		return IPUBLICADO;
	}

	public void setIPUBLICADO(int iPUBLICADO) {
		IPUBLICADO = iPUBLICADO;
	}

	public int getICVEUSUARIOREVISOR() {
		return ICVEUSUARIOREVISOR;
	}

	public void setICVEUSUARIOREVISOR(int iCVEUSUARIOREVISOR) {
		ICVEUSUARIOREVISOR = iCVEUSUARIOREVISOR;
	}

	public int getICVEPREMIACION() {
		return ICVEPREMIACION;
	}

	public void setICVEPREMIACION(int iCVEPREMIACION) {
		ICVEPREMIACION = iCVEPREMIACION;
	}

	public String getICVECOMENTARIO_ID() {
		ICVECOMENTARIO_ID = getICVECOMENTARIO_PK().getICVECOMENTARIO()+"-";
		return ICVECOMENTARIO_ID;
	}

	public void setICVECOMENTARIO_ID(String iCVECOMENTARIO_ID) {
		ICVECOMENTARIO_PK newPK = new ICVECOMENTARIO_PK();
		newPK.setICVECOMENTARIO(Integer.parseInt(iCVECOMENTARIO_ID.split("-")[0]));
		setICVECOMENTARIO_PK(newPK);
		ICVECOMENTARIO_ID = iCVECOMENTARIO_ID;
	}
	
}
