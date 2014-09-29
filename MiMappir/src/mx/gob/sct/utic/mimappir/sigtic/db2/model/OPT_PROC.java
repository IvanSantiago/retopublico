package mx.gob.sct.utic.mimappir.sigtic.db2.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * OPT_PROC POJO
 * 
 * @author Ivan Santiago
 *
 */
@JsonAutoDetect
@Entity
@Table(name="OPT_PROC")
public class OPT_PROC {
	
	private Integer UID_OPTP;
	private Integer UID_PROCESO;
	private String DESC_OPT;
	private Integer ORDEN;
	private Integer NIVEL;
	
	@Id
	@GeneratedValue
	@Column(name="UID_OPTP")
	public Integer getUID_OPTP() {
		return UID_OPTP;
	}
	public void setUID_OPTP(Integer uID_OPTP) {
		UID_OPTP = uID_OPTP;
	}
	@Column(name="UID_PROCESO", nullable=false)
	public Integer getUID_PROCESO() {
		return UID_PROCESO;
	}
	public void setUID_PROCESO(Integer uID_PROCESO) {
		UID_PROCESO = uID_PROCESO;
	}
	@Column(name="DESC_OPT", nullable=false)
	public String getDESC_OPT() {
		return DESC_OPT;
	}
	public void setDESC_OPT(String dESC_OPT) {
		DESC_OPT = dESC_OPT;
	}
	@Column(name="ORDEN", nullable=false)
	public Integer getORDEN() {
		return ORDEN;
	}
	public void setORDEN(Integer oRDEN) {
		ORDEN = oRDEN;
	}
	@Column(name="NIVEL", nullable=true)
	public Integer getNIVEL() {
		return NIVEL;
	}
	public void setNIVEL(Integer nIVEL) {
		NIVEL = nIVEL;
	}
}
