package mx.gob.sct.utic.qoscell.sigtic.db2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * C_R_UA POJO
 * 
 * @author Ivan Santiago
 *
 */
@JsonAutoDetect
@Entity
@Table(name="C_R_UA")
public class C_R_UA {
	
	private Integer UID_R_UA;
	private Integer UID_UA;
	private Integer RUA_UID;
	private Integer RIN_UID;
	private Integer RAF_UID;
	private Integer RAD_UID;
	private Integer RVP_UID;
	private Integer RRH_UID;
	private Integer RRF_UID;
	private String TEL_RUA;
	private String TEL_RIN;
	private String TEL_RAF;
	private String TEL_RAD;
	private String TEL_RVP;
	
	@Id
	@GeneratedValue
	@Column(name="UID_R_UA")
	public Integer getUID_R_UA() {
		return UID_R_UA;
	}
	public void setUID_R_UA(Integer uID_R_UA) {
		UID_R_UA = uID_R_UA;
	}
	@Column(name="UID_UA", nullable=false)
	public Integer getUID_UA() {
		return UID_UA;
	}
	public void setUID_UA(Integer uID_UA) {
		UID_UA = uID_UA;
	}
	@Column(name="RUA_UID", nullable=true)
	public Integer getRUA_UID() {
		return RUA_UID;
	}
	public void setRUA_UID(Integer rUA_UID) {
		RUA_UID = rUA_UID;
	}
	@Column(name="RIN_UID", nullable=true)
	public Integer getRIN_UID() {
		return RIN_UID;
	}
	public void setRIN_UID(Integer rIN_UID) {
		RIN_UID = rIN_UID;
	}
	@Column(name="RAF_UID", nullable=true)
	public Integer getRAF_UID() {
		return RAF_UID;
	}
	public void setRAF_UID(Integer rAF_UID) {
		RAF_UID = rAF_UID;
	}
	@Column(name="RAD_UID", nullable=true)
	public Integer getRAD_UID() {
		return RAD_UID;
	}
	public void setRAD_UID(Integer rAD_UID) {
		RAD_UID = rAD_UID;
	}
	@Column(name="RVP_UID", nullable=true)
	public Integer getRVP_UID() {
		return RVP_UID;
	}
	public void setRVP_UID(Integer rVP_UID) {
		RVP_UID = rVP_UID;
	}
	@Column(name="RRH_UID", nullable=false)
	public Integer getRRH_UID() {
		return RRH_UID;
	}
	public void setRRH_UID(Integer rRH_UID) {
		RRH_UID = rRH_UID;
	}
	@Column(name="RRF_UID", nullable=false)	
	public Integer getRRF_UID() {
		return RRF_UID;
	}
	public void setRRF_UID(Integer rRF_UID) {
		RRF_UID = rRF_UID;
	}
	@Column(name="TEL_RUA", nullable=true)
	public String getTEL_RUA() {
		return TEL_RUA;
	}
	public void setTEL_RUA(String tEL_RUA) {
		TEL_RUA = tEL_RUA;
	}
	@Column(name="TEL_RIN", nullable=true)
	public String getTEL_RIN() {
		return TEL_RIN;
	}
	public void setTEL_RIN(String tEL_RIN) {
		TEL_RIN = tEL_RIN;
	}
	@Column(name="TEL_RAF", nullable=true)
	public String getTEL_RAF() {
		return TEL_RAF;
	}
	public void setTEL_RAF(String tEL_RAF) {
		TEL_RAF = tEL_RAF;
	}
	@Column(name="TEL_RAD", nullable=true)
	public String getTEL_RAD() {
		return TEL_RAD;
	}
	public void setTEL_RAD(String tEL_RAD) {
		TEL_RAD = tEL_RAD;
	}
	@Column(name="TEL_RVP", nullable=true)
	public String getTEL_RVP() {
		return TEL_RVP;
	}
	public void setTEL_RVP(String tEL_RVP) {
		TEL_RVP = tEL_RVP;
	}
}
