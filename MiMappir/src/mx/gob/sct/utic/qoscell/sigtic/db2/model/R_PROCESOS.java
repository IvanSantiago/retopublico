package mx.gob.sct.utic.qoscell.sigtic.db2.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * R_PROCESOS POJO
 * 
 * @author Ivan Santiago
 *
 */
@JsonAutoDetect
@Entity
@Table(name="R_PROCESOS")
public class R_PROCESOS {
	
	private Integer UID_R_PROCESO;
	private Integer UID_PROCESO;
	private Integer UID_UA;
	private Integer UID_EMPLEADO;
	private Timestamp STAMP;
	private Timestamp STAMP_F;
	private Integer UID_EMPLEADO_SOL;
	private Integer UID_STATUS_PROC;
	
	@Id
	@GeneratedValue
	@Column(name="UID_R_PROCESO")
	public Integer getUID_R_PROCESO() {
		return UID_R_PROCESO;
	}
	public void setUID_R_PROCESO(Integer uID_R_PROCESO) {
		UID_R_PROCESO = uID_R_PROCESO;
	}
	@Column(name="UID_PROCESO", nullable=false)
	public Integer getUID_PROCESO() {
		return UID_PROCESO;
	}
	public void setUID_PROCESO(Integer uID_PROCESO) {
		UID_PROCESO = uID_PROCESO;
	}
	@Column(name="UID_UA", nullable=false)
	public Integer getUID_UA() {
		return UID_UA;
	}
	public void setUID_UA(Integer uID_UA) {
		UID_UA = uID_UA;
	}
	@Column(name="UID_EMPLEADO", nullable=false)
	public Integer getUID_EMPLEADO() {
		return UID_EMPLEADO;
	}
	public void setUID_EMPLEADO(Integer uID_EMPLEADO) {
		UID_EMPLEADO = uID_EMPLEADO;
	}
	@Column(name="STAMP", nullable=true)
	public Timestamp getSTAMP() {
		return STAMP;
	}
	public void setSTAMP(Timestamp sTAMP) {
		STAMP = sTAMP;
	}
	@Column(name="STAMP_F", nullable=true)
	public Timestamp getSTAMP_F() {
		return STAMP_F;
	}
	public void setSTAMP_F(Timestamp sTAMP_F) {
		STAMP_F = sTAMP_F;
	}
	@Column(name="UID_EMPLEADO_SOL", nullable=true)
	public Integer getUID_EMPLEADO_SOL() {
		return UID_EMPLEADO_SOL;
	}
	public void setUID_EMPLEADO_SOL(Integer uID_EMPLEADO_SOL) {
		UID_EMPLEADO_SOL = uID_EMPLEADO_SOL;
	}
	@Column(name="UID_STATUS_PROC", nullable=true)
	public Integer getUID_STATUS_PROC() {
		return UID_STATUS_PROC;
	}
	public void setUID_STATUS_PROC(Integer uID_STATUS_PROC) {
		UID_STATUS_PROC = uID_STATUS_PROC;
	}
}
