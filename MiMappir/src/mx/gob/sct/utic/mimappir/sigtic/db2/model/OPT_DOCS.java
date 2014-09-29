package mx.gob.sct.utic.mimappir.sigtic.db2.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * OPT_DOCS POJO
 * 
 * @author Ivan Santiago
 *
 */
@JsonAutoDetect
@Entity
@Table(name="OPT_DOCS")
public class OPT_DOCS {

	private Integer UID_OPD;
	private Integer UID_OPTP;
	private Integer ORDEN;
	private String LAB_ARG;
	private String REP_ARG;
	private String META_ARG;
	private String TIPO_ARG;
	private String SQL_ARG;
	private String PSQL_ARG;
	
	@Id
	@GeneratedValue
	@Column(name="UID_OPD")
	public Integer getUID_OPD() {
		return UID_OPD;
	}
	public void setUID_OPD(Integer uID_OPD) {
		UID_OPD = uID_OPD;
	}
	@Column(name="UID_OPTP", nullable=false)
	public Integer getUID_OPTP() {
		return UID_OPTP;
	}
	public void setUID_OPTP(Integer uID_OPTP) {
		UID_OPTP = uID_OPTP;
	}
	@Column(name="ORDEN", nullable=false)
	public Integer getORDEN() {
		return ORDEN;
	}
	public void setORDEN(Integer oRDEN) {
		ORDEN = oRDEN;
	}
	@Column(name="LAB_ARG", nullable=false)
	public String getLAB_ARG() {
		return LAB_ARG;
	}
	public void setLAB_ARG(String lAB_ARG) {
		LAB_ARG = lAB_ARG;
	}
	@Column(name="REP_ARG", nullable=true)
	public String getREP_ARG() {
		return REP_ARG;
	}
	public void setREP_ARG(String rEP_ARG) {
		REP_ARG = rEP_ARG;
	}
	@Column(name="META_ARG", nullable=true)
	public String getMETA_ARG() {
		return META_ARG;
	}
	public void setMETA_ARG(String mETA_ARG) {
		META_ARG = mETA_ARG;
	}
	@Column(name="TIPO_ARG", nullable=true)
	public String getTIPO_ARG() {
		return TIPO_ARG;
	}
	public void setTIPO_ARG(String tIPO_ARG) {
		TIPO_ARG = tIPO_ARG;
	}
	@Column(name="SQL_ARG", nullable=true)
	public String getSQL_ARG() {
		return SQL_ARG;
	}
	public void setSQL_ARG(String sQL_ARG) {
		SQL_ARG = sQL_ARG;
	}
	@Column(name="PSQL_ARG", nullable=true)
	public String getPSQL_ARG() {
		return PSQL_ARG;
	}
	public void setPSQL_ARG(String pSQL_ARG) {
		PSQL_ARG = pSQL_ARG;
	}
}
