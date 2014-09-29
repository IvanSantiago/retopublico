package mx.gob.sct.utic.mimappir.admseg.postgreSQL.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 * SEGUSUARIO POJO
 * 
 * @author Ivan Santiago
 *
 */

@JsonAutoDetect
@Entity
@Table(name="SEGUSUARIO")
public class SEGUSUARIO {
	@EmbeddedId
	@Column(name="ICVESEGUSUARIO_PK", nullable=false)
	@JsonProperty("ICVESEGUSUARIO_PK")
	private ICVESEGUSUARIO_PK ICVESEGUSUARIO_PK;

	@JsonProperty("ICVESEGUSUARIO_ID")
	@Transient
	private String ICVESEGUSUARIO_ID;
	
	private Date DTREGISTRO;
	private String CUSUARIO;
	private String CPASSWORD;
	private String CNOMBRE;
	private String CAPPATERNO;
	private String CAPMATERNO;
	private String CCALLE;
	private String CCOLONIA;
	private Short ICVEPAIS;	
	private Short ICVEENTIDADFED;
	private Long ICVEMUNICIPIO;
	private Long ICODIGOPOSTAL;
	private String CTELEFONO;
	private Short ICVEUNIDADORG;
	private Short LBLOQUEADO;
	private String CECORREO;
	private Date DTCAMBIOCONTRA;

	public String getICVESEGUSUARIO_ID() {
		return ICVESEGUSUARIO_ID;
	}
	public void setICVESEGUSUARIO_ID(String iCVESEGUSUARIO_ID) {
		ICVESEGUSUARIO_ID = iCVESEGUSUARIO_ID;
	}
	public ICVESEGUSUARIO_PK getICVESEGUSUARIO_PK() {
		return ICVESEGUSUARIO_PK;
	}
	public void setICVESEGUSUARIO_PK(ICVESEGUSUARIO_PK iCVESEGUSUARIO_PK) {
		ICVESEGUSUARIO_PK = iCVESEGUSUARIO_PK;
	}

	@Column(name="DTREGISTRO", nullable=true)
	public Date getDTREGISTRO() {
		return DTREGISTRO;
	}
	public void setDTREGISTRO(Date dTREGISTRO) {
		DTREGISTRO = dTREGISTRO;
	}
	@Column(name="CUSUARIO", nullable=false)
	public String getCUSUARIO() {
		return CUSUARIO;
	}
	public void setCUSUARIO(String cUSUARIO) {
		CUSUARIO = cUSUARIO;
	}
	@Column(name="CPASSWORD", nullable=false)
	public String getCPASSWORD() {
		return CPASSWORD;
	}
	public void setCPASSWORD(String cPASSWORD) {
		CPASSWORD = cPASSWORD;
	}
	@Column(name="CNOMBRE", nullable=false)
	public String getCNOMBRE() {
		return CNOMBRE;
	}
	public void setCNOMBRE(String cNOMBRE) {
		CNOMBRE = cNOMBRE;
	}
	@Column(name="CAPPATERNO", nullable=false)
	public String getCAPPATERNO() {
		return CAPPATERNO;
	}
	public void setCAPPATERNO(String cAPPATERNO) {
		CAPPATERNO = cAPPATERNO;
	}
	@Column(name="CAPMATERNO", nullable=true)
	public String getCAPMATERNO() {
		return CAPMATERNO;
	}
	public void setCAPMATERNO(String cAPMATERNO) {
		CAPMATERNO = cAPMATERNO;
	}
	@Column(name="CCALLE", nullable=false)
	public String getCCALLE() {
		return CCALLE;
	}
	public void setCCALLE(String cCALLE) {
		CCALLE = cCALLE;
	}
	@Column(name="CCOLONIA", nullable=true)
	public String getCCOLONIA() {
		return CCOLONIA;
	}
	public void setCCOLONIA(String cCOLONIA) {
		CCOLONIA = cCOLONIA;
	}
	@Column(name="ICVEPAIS", nullable=false)
	public Short getICVEPAIS() {
		return ICVEPAIS;
	}
	public void setICVEPAIS(Short iCVEPAIS) {
		ICVEPAIS = iCVEPAIS;
	}
	@Column(name="ICVEENTIDADFED", nullable=true)
	public Short getICVEENTIDADFED() {
		return ICVEENTIDADFED;
	}
	public void setICVEENTIDADFED(Short iCVEENTIDADFED) {
		ICVEENTIDADFED = iCVEENTIDADFED;
	}
	@Column(name="ICVEMUNICIPIO", nullable=true)
	public Long getICVEMUNICIPIO() {
		return ICVEMUNICIPIO;
	}
	public void setICVEMUNICIPIO(Long iCVEMUNICIPIO) {
		ICVEMUNICIPIO = iCVEMUNICIPIO;
	}
	@Column(name="ICODIGOPOSTAL", nullable=true)
	public Long getICODIGOPOSTAL() {
		return ICODIGOPOSTAL;
	}
	public void setICODIGOPOSTAL(Long iCODIGOPOSTAL) {
		ICODIGOPOSTAL = iCODIGOPOSTAL;
	}
	@Column(name="CTELEFONO", nullable=false)
	public String getCTELEFONO() {
		return CTELEFONO;
	}
	public void setCTELEFONO(String cTELEFONO) {
		CTELEFONO = cTELEFONO;
	}
	@Column(name="ICVEUNIDADORG", nullable=true)
	public Short getICVEUNIDADORG() {
		return ICVEUNIDADORG;
	}
	public void setICVEUNIDADORG(Short iCVEUNIDADORG) {
		ICVEUNIDADORG = iCVEUNIDADORG;
	}
	@Column(name="LBLOQUEADO", nullable=false)
	public Short getLBLOQUEADO() {
		return LBLOQUEADO;
	}
	public void setLBLOQUEADO(Short lBLOQUEADO) {
		LBLOQUEADO = lBLOQUEADO;
	}
	@Column(name="CECORREO", nullable=true)
	public String getCECORREO() {
		return CECORREO;
	}
	public void setCECORREO(String cECORREO) {
		CECORREO = cECORREO;
	}
	@Column(name="DTCAMBIOCONTRA", nullable=true)
	public Date getDTCAMBIOCONTRA() {
		return DTCAMBIOCONTRA;
	}
	public void setDTCAMBIOCONTRA(Date dTCAMBIOCONTRA) {
		DTCAMBIOCONTRA = dTCAMBIOCONTRA;
	}
}
