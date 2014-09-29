package mx.gob.sct.utic.mimappir.sigtic.db2.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * DOC_PROC POJO
 * 
 * @author Ivan Santiago
 *
 */
@JsonAutoDetect
@Entity
@Table(name="DOC_PROC")
public class DOC_PROC {
	
	private Integer UID_DOC;
	private Integer UID_R_PROCESO;
	private Integer UID_PROCESO;
	private Integer UID_UA;
	private Timestamp VALIDACION;
	private String FIRMA;
	private Integer DOC_ID;
	
	@Id
	@GeneratedValue
	@Column(name="UID_DOC")
	public Integer getUID_DOC() {
		return UID_DOC;
	}
	public void setUID_DOC(Integer uID_DOC) {
		UID_DOC = uID_DOC;
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
	@Column(name="UID_UA", nullable=false)
	public Integer getUID_UA() {
		return UID_UA;
	}
	public void setUID_UA(Integer uID_UA) {
		UID_UA = uID_UA;
	}
	@Column(name="VALIDACION", nullable=false)
	public Timestamp getVALIDACION() {
		return VALIDACION;
	}
	public void setVALIDACION(Timestamp vALIDACION) {
		VALIDACION = vALIDACION;
	}
	@Column(name="FIRMA", nullable=false)
	public String getFIRMA() {
		return FIRMA;
	}
	public void setFIRMA(String fIRMA) {
		FIRMA = fIRMA;
	}
	@Column(name="DOC_ID", nullable=false)
	public Integer getDOC_ID() {
		return DOC_ID;
	}
	public void setDOC_ID(Integer dOC_ID) {
		DOC_ID = dOC_ID;
	}	
}
