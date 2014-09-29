package mx.gob.sct.utic.mimappir.sigtic.db2.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * Menu POJO
 * 
 * @author Ivan Santiago
 *
 */
@JsonAutoDetect
@Entity
@Table(name="INBOX")
public class INBOX {
	
	private Integer UID_INBOX;
	private Integer UID_R_PROCESO;
	private Integer UID_PROCESO;
	private Integer PASO;
	private Integer UID_UA;
	private Integer UID_EMPLEADO;
	private String I_CONTAINER;
	private Timestamp STAMP;
	private Integer STATUS;
	
	@Id
	@GeneratedValue
	@Column(name="UID_INBOX")
	public Integer getUID_INBOX() {
		return UID_INBOX;
	}
	public void setUID_INBOX(Integer uID_INBOX) {
		UID_INBOX = uID_INBOX;
	}
	@Column(name="UID_R_PROCESO", nullable=false)
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
	@Column(name="PASO", nullable=false)
	public Integer getPASO() {
		return PASO;
	}
	public void setPASO(Integer pASO) {
		PASO = pASO;
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
	@Column(name="I_CONTAINER", nullable=true)
	public String getI_CONTAINER() {
		return I_CONTAINER;
	}
	public void setI_CONTAINER(String i_CONTAINER) {
		I_CONTAINER = i_CONTAINER;
	}
	@Column(name="STAMP", nullable=false)
	public Timestamp getSTAMP() {
		return STAMP;
	}
	public void setSTAMP(Timestamp sTAMP) {
		STAMP = sTAMP;
	}
	@Column(name="STATUS", nullable=false)
	public Integer getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}
}
