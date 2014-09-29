package mx.gob.sct.utic.mimappir.sigtic.db2.model;

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
@Table(name="SOL_SI_PERF")
public class SOL_SI_PERF {
	
	private Integer UID_PER;
	private Integer UID_SI;
	private String PERFIL;
	private String DESC_L;
	private Integer ORDEN;
	
	@Id
	@GeneratedValue
	@Column(name="UID_PER")
	public Integer getUID_PER() {
		return UID_PER;
	}
	public void setUID_PER(Integer uID_PER) {
		UID_PER = uID_PER;
	}
	@Column(name="UID_SI", nullable=false)
	public Integer getUID_SI() {
		return UID_SI;
	}
	public void setUID_SI(Integer uID_SI) {
		UID_SI = uID_SI;
	}
	@Column(name="PERFIL", nullable=false)
	public String getPERFIL() {
		return PERFIL;
	}
	public void setPERFIL(String pERFIL) {
		PERFIL = pERFIL;
	}
	@Column(name="DESC_L", nullable=true)
	public String getDESC_L() {
		return DESC_L;
	}
	public void setDESC_L(String dESC_L) {
		DESC_L = dESC_L;
	}
	@Column(name="ORDEN", nullable=false)
	public Integer getORDEN() {
		return ORDEN;
	}
	public void setORDEN(Integer oRDEN) {
		ORDEN = oRDEN;
	}
}
