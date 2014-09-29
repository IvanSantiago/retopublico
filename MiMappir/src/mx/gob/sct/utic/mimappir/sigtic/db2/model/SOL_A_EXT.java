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
@Table(name="SOL_A_EXT")
public class SOL_A_EXT {
	private Integer UID_SOL;
	private String DS;
	private String NE;
	private String AP;
	private String AM;
	private String NOM;
	private String RFC;
	private String DIR;
	private String COL;
	private String CP;
	private String CD;
	private String EDO;
	private String EM;
	private String TEL;
	private Integer UID_R_PROCESO;
	private String CUENTA;
	private String PASS;
	private String OBS;
	private String OBS_DICT;
	private Integer UID_SI;
	private String CVE;
	
	@Id
	@GeneratedValue
	@Column(name="UID_SOL")
	public Integer getUID_SOL() {
		return UID_SOL;
	}
	public void setUID_SOL(Integer uID_SOL) {
		UID_SOL = uID_SOL;
	}
	@Column(name="DS", nullable=false)
	public String getDS() {
		return DS;
	}
	public void setDS(String dS) {
		DS = dS;
	}
	@Column(name="NE", nullable=false)
	public String getNE() {
		return NE;
	}
	public void setNE(String nE) {
		NE = nE;
	}
	@Column(name="AP", nullable=false)
	public String getAP() {
		return AP;
	}
	public void setAP(String aP) {
		AP = aP;
	}
	@Column(name="AM", nullable=false)
	public String getAM() {
		return AM;
	}
	public void setAM(String aM) {
		AM = aM;
	}
	@Column(name="NOM", nullable=false)
	public String getNOM() {
		return NOM;
	}
	public void setNOM(String nOM) {
		NOM = nOM;
	}
	@Column(name="RFC", nullable=false)
	public String getRFC() {
		return RFC;
	}
	public void setRFC(String rFC) {
		RFC = rFC;
	}
	@Column(name="DIR", nullable=false)
	public String getDIR() {
		return DIR;
	}
	public void setDIR(String dIR) {
		DIR = dIR;
	}
	@Column(name="COL", nullable=true)
	public String getCOL() {
		return COL;
	}
	public void setCOL(String cOL) {
		COL = cOL;
	}
	@Column(name="CP", nullable=true)
	public String getCP() {
		return CP;
	}
	public void setCP(String cP) {
		CP = cP;
	}
	@Column(name="CD", nullable=false)
	public String getCD() {
		return CD;
	}
	public void setCD(String cD) {
		CD = cD;
	}
	@Column(name="EDO", nullable=false)
	public String getEDO() {
		return EDO;
	}
	public void setEDO(String eDO) {
		EDO = eDO;
	}
	@Column(name="EM", nullable=true)
	public String getEM() {
		return EM;
	}
	public void setEM(String eM) {
		EM = eM;
	}
	@Column(name="TEL", nullable=true)
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	@Column(name="UID_R_PROCESO", nullable=false)
	public Integer getUID_R_PROCESO() {
		return UID_R_PROCESO;
	}
	public void setUID_R_PROCESO(Integer uID_R_PROCESO) {
		UID_R_PROCESO = uID_R_PROCESO;
	}
	@Column(name="CUENTA", nullable=true)
	public String getCUENTA() {
		return CUENTA;
	}
	public void setCUENTA(String cUENTA) {
		CUENTA = cUENTA;
	}
	@Column(name="PASS", nullable=true)
	public String getPASS() {
		return PASS;
	}
	public void setPASS(String pASS) {
		PASS = pASS;
	}
	@Column(name="OBS", nullable=true)
	public String getOBS() {
		return OBS;
	}
	public void setOBS(String oBS) {
		OBS = oBS;
	}
	@Column(name="OBS_DICT", nullable=true)
	public String getOBS_DICT() {
		return OBS_DICT;
	}
	public void setOBS_DICT(String oBS_DICT) {
		OBS_DICT = oBS_DICT;
	}
	@Column(name="UID_SI", nullable=true)
	public Integer getUID_SI() {
		return UID_SI;
	}
	public void setUID_SI(Integer uID_SI) {
		UID_SI = uID_SI;
	}
	@Column(name="CVE", nullable=true)
	public String getCVE() {
		return CVE;
	}
	public void setCVE(String cVE) {
		CVE = cVE;
	}
}
