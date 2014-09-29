package mx.gob.sct.utic.mimappir.sigtic.db2.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * C_01_04_UA POJO
 * 
 * @author Ivan Santiago
 *
 */
@JsonAutoDetect
@Entity
@Table(name="C_01_04_UA")
public class C_01_04_UA {
	
	private Integer UID_UA;
	private Integer UID_CIUDAD;
	private String UA;
	private String DESC_UA;
	private String SIGLAS;
	private Integer UID_TIPO_UA;
	private String UA_35CAR;
	private String DESC_C_UA;
	private Integer SUP;
	@Id
	@GeneratedValue
	@Column(name="UID_UA")
	public Integer getUID_UA() {
		return UID_UA;
	}
	public void setUID_UA(Integer uID_UA) {
		UID_UA = uID_UA;
	}
	@Column(name="UID_CIUDAD", nullable=false)
	public Integer getUID_CIUDAD() {
		return UID_CIUDAD;
	}
	public void setUID_CIUDAD(Integer uID_CIUDAD) {
		UID_CIUDAD = uID_CIUDAD;
	}
	@Column(name="UA", nullable=false)
	public String getUA() {
		return UA;
	}
	public void setUA(String uA) {
		UA = uA;
	}
	@Column(name="DESC_UA", nullable=false)
	public String getDESC_UA() {
		return DESC_UA;
	}
	public void setDESC_UA(String dESC_UA) {
		DESC_UA = dESC_UA;
	}
	@Column(name="SIGLAS", nullable=true)
	public String getSIGLAS() {
		return SIGLAS;
	}
	public void setSIGLAS(String sIGLAS) {
		SIGLAS = sIGLAS;
	}
	@Column(name="UID_TIPO_UA", nullable=true)
	public Integer getUID_TIPO_UA() {
		return UID_TIPO_UA;
	}
	public void setUID_TIPO_UA(Integer uID_TIPO_UA) {
		UID_TIPO_UA = uID_TIPO_UA;
	}
	@Column(name="UA_35CAR", nullable=true)
	public String getUA_35CAR() {
		return UA_35CAR;
	}
	public void setUA_35CAR(String uA_35CAR) {
		UA_35CAR = uA_35CAR;
	}
	@Column(name="DESC_C_UA", nullable=true)
	public String getDESC_C_UA() {
		return DESC_C_UA;
	}
	public void setDESC_C_UA(String dESC_C_UA) {
		DESC_C_UA = dESC_C_UA;
	}
	@Column(name="SUP", nullable=true)
	public Integer getSUP() {
		return SUP;
	}
	public void setSUP(Integer sUP) {
		SUP = sUP;
	}
}
