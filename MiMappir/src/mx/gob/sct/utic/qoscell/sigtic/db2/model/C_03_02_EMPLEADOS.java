package mx.gob.sct.utic.qoscell.sigtic.db2.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * C_03_02_EMPLEADOS POJO
 * 
 * @author Ivan Santiago
 *
 */
@JsonAutoDetect
@Entity
@Table(name="C_03_02_EMPLEADOS")
public class C_03_02_EMPLEADOS {
	
	private Integer UID_EMPLEADO;
	private Integer UID_UA;
	private Integer NUM_EMPLEADO;
	private String NOMBRE_COMPLETO;
	private Integer UID_TIPO_EMPLEADO;
	private Integer UID_UA_P;
	private String ACTIVO;
	private String OBSERVACIONES;
	private String TITULO;
	private String AP;
	private String AM;
	private String PN;
	private String SN;
	private String CU;
	private Integer NUEVA_EXT;
	
	@Id
	@GeneratedValue
	@Column(name="UID_EMPLEADO")
	public Integer getUID_EMPLEADO() {
		return UID_EMPLEADO;
	}
	public void setUID_EMPLEADO(Integer uID_EMPLEADO) {
		UID_EMPLEADO = uID_EMPLEADO;
	}
	@Column(name="UID_UA", nullable=false)
	public Integer getUID_UA() {
		return UID_UA;
	}
	public void setUID_UA(Integer uID_UA) {
		UID_UA = uID_UA;
	}
	@Column(name="NUM_EMPLEADO", nullable=false)
	public Integer getNUM_EMPLEADO() {
		return NUM_EMPLEADO;
	}
	public void setNUM_EMPLEADO(Integer nUM_EMPLEADO) {
		NUM_EMPLEADO = nUM_EMPLEADO;
	}
	@Column(name="NOMBRE_COMPLETO", nullable=true)
	public String getNOMBRE_COMPLETO() {
		return NOMBRE_COMPLETO;
	}
	public void setNOMBRE_COMPLETO(String nOMBRE_COMPLETO) {
		NOMBRE_COMPLETO = nOMBRE_COMPLETO;
	}
	@Column(name="UID_TIPO_EMPLEADO", nullable=true)
	public Integer getUID_TIPO_EMPLEADO() {
		return UID_TIPO_EMPLEADO;
	}
	public void setUID_TIPO_EMPLEADO(Integer uID_TIPO_EMPLEADO) {
		UID_TIPO_EMPLEADO = uID_TIPO_EMPLEADO;
	}
	@Column(name="UID_UA_P", nullable=true)
	public Integer getUID_UA_P() {
		return UID_UA_P;
	}
	public void setUID_UA_P(Integer uID_UA_P) {
		UID_UA_P = uID_UA_P;
	}
	@Column(name="ACTIVO", nullable=true)
	public String getACTIVO() {
		return ACTIVO;
	}
	public void setACTIVO(String aCTIVO) {
		ACTIVO = aCTIVO;
	}
	@Column(name="OBSERVACIONES", nullable=true)
	public String getOBSERVACIONES() {
		return OBSERVACIONES;
	}
	public void setOBSERVACIONES(String oBSERVACIONES) {
		OBSERVACIONES = oBSERVACIONES;
	}
	@Column(name="TITULO", nullable=true)
	public String getTITULO() {
		return TITULO;
	}
	public void setTITULO(String tITULO) {
		TITULO = tITULO;
	}
	@Column(name="AP", nullable=true)
	public String getAP() {
		return AP;
	}
	public void setAP(String aP) {
		AP = aP;
	}
	@Column(name="AM", nullable=true)
	public String getAM() {
		return AM;
	}
	public void setAM(String aM) {
		AM = aM;
	}
	@Column(name="PN", nullable=true)
	public String getPN() {
		return PN;
	}
	public void setPN(String pN) {
		PN = pN;
	}
	@Column(name="SN", nullable=true)
	public String getSN() {
		return SN;
	}
	public void setSN(String sN) {
		SN = sN;
	}
	@Column(name="CU", nullable=true)
	public String getCU() {
		return CU;
	}
	public void setCU(String cU) {
		CU = cU;
	}
	@Column(name="NUEVA_EXT", nullable=true)
	public Integer getNUEVA_EXT() {
		return NUEVA_EXT;
	}
	public void setNUEVA_EXT(Integer nUEVA_EXT) {
		NUEVA_EXT = nUEVA_EXT;
	}
}
