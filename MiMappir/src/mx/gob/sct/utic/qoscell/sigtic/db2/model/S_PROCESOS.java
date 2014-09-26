package mx.gob.sct.utic.qoscell.sigtic.db2.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * S_PROCESOS POJO
 * 
 * @author Ivan Santiago
 *
 */
@JsonAutoDetect
@Entity
@Table(name="S_PROCESOS")
public class S_PROCESOS {
	
	private Integer UID_PROCESO;
	private String DESC_PROCESO;
	private Integer NIVEL_PROCESO;
	private String GRUPO_PROCESO;
	private String METADATOS;
	private String SUBGRUPO_PROCESO;
	private String OBS_PROCESO;
	private Integer UID_EMPLEADO;
	private Integer R_UID_EMPLEADO;
	private Integer F_UID_EMPLEADO;
	private String VERSION;
	private Integer UID_EMPLEADO_E;
	private Integer UID_EMPLEADO_A;
	private Integer UID_E_C_PROC;
	
	@Id
	@GeneratedValue
	@Column(name="UID_PROCESO")
	public Integer getUID_PROCESO() {
		return UID_PROCESO;
	}
	public void setUID_PROCESO(Integer uID_PROCESO) {
		UID_PROCESO = uID_PROCESO;
	}
	@Column(name="DESC_PROCESO", nullable=false)
	public String getDESC_PROCESO() {
		return DESC_PROCESO;
	}
	public void setDESC_PROCESO(String dESC_PROCESO) {
		DESC_PROCESO = dESC_PROCESO;
	}
	@Column(name="NIVEL_PROCESO", nullable=false)
	public Integer getNIVEL_PROCESO() {
		return NIVEL_PROCESO;
	}
	public void setNIVEL_PROCESO(Integer nIVEL_PROCESO) {
		NIVEL_PROCESO = nIVEL_PROCESO;
	}
	@Column(name="GRUPO_PROCESO", nullable=false)
	public String getGRUPO_PROCESO() {
		return GRUPO_PROCESO;
	}
	public void setGRUPO_PROCESO(String gRUPO_PROCESO) {
		GRUPO_PROCESO = gRUPO_PROCESO;
	}
	@Column(name="METADATOS", nullable=true)
	public String getMETADATOS() {
		return METADATOS;
	}
	public void setMETADATOS(String mETADATOS) {
		METADATOS = mETADATOS;
	}
	@Column(name="SUBGRUPO_PROCESO", nullable=true)
	public String getSUBGRUPO_PROCESO() {
		return SUBGRUPO_PROCESO;
	}
	public void setSUBGRUPO_PROCESO(String sUBGRUPO_PROCESO) {
		SUBGRUPO_PROCESO = sUBGRUPO_PROCESO;
	}
	@Column(name="OBS_PROCESO", nullable=true)
	public String getOBS_PROCESO() {
		return OBS_PROCESO;
	}
	public void setOBS_PROCESO(String oBS_PROCESO) {
		OBS_PROCESO = oBS_PROCESO;
	}
	@Column(name="UID_EMPLEADO", nullable=true)
	public Integer getUID_EMPLEADO() {
		return UID_EMPLEADO;
	}
	public void setUID_EMPLEADO(Integer uID_EMPLEADO) {
		UID_EMPLEADO = uID_EMPLEADO;
	}
	@Column(name="R_UID_EMPLEADO", nullable=false)
	public Integer getR_UID_EMPLEADO() {
		return R_UID_EMPLEADO;
	}
	public void setR_UID_EMPLEADO(Integer r_UID_EMPLEADO) {
		R_UID_EMPLEADO = r_UID_EMPLEADO;
	}
	@Column(name="F_UID_EMPLEADO", nullable=false)
	public Integer getF_UID_EMPLEADO() {
		return F_UID_EMPLEADO;
	}
	public void setF_UID_EMPLEADO(Integer f_UID_EMPLEADO) {
		F_UID_EMPLEADO = f_UID_EMPLEADO;
	}
	@Column(name="VERSION", nullable=true)
	public String getVERSION() {
		return VERSION;
	}
	public void setVERSION(String vERSION) {
		VERSION = vERSION;
	}
	@Column(name="UID_EMPLEADO_E", nullable=false)
	public Integer getUID_EMPLEADO_E() {
		return UID_EMPLEADO_E;
	}
	public void setUID_EMPLEADO_E(Integer uID_EMPLEADO_E) {
		UID_EMPLEADO_E = uID_EMPLEADO_E;
	}
	@Column(name="UID_EMPLEADO_A", nullable=false)
	public Integer getUID_EMPLEADO_A() {
		return UID_EMPLEADO_A;
	}
	public void setUID_EMPLEADO_A(Integer uID_EMPLEADO_A) {
		UID_EMPLEADO_A = uID_EMPLEADO_A;
	}
	@Column(name="UID_E_C_PROC", nullable=false)
	public Integer getUID_E_C_PROC() {
		return UID_E_C_PROC;
	}
	public void setUID_E_C_PROC(Integer uID_E_C_PROC) {
		UID_E_C_PROC = uID_E_C_PROC;
	}
}
