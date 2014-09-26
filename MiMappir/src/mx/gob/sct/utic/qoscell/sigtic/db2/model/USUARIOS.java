package mx.gob.sct.utic.qoscell.sigtic.db2.model;

import java.sql.Date;

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
@Table(name="USUARIOS")
public class USUARIOS {
	
	private Integer UID_USUARIO;
	private String LOGIN;
	private String PASS;
	private Integer UID_EMPLEADO;	
	private String MENU;
	private String CHILDREN;
	private String ARGS;
	private Integer NIVEL;
	private Integer ROL;
	private Integer UID_UA;
	private String ACTIVO;
	private Date FECHA_VENCE_PASS;
		
	@Id
	@GeneratedValue
	@Column(name="UID_USUARIO", nullable=false)
	public Integer getUID_USUARIO() {
		return UID_USUARIO;
	}
	public void setUID_USUARIO(Integer uID_USUARIO) {
		UID_USUARIO = uID_USUARIO;
	}
	@Column(name="LOGIN", nullable=false)
	public String getLOGIN() {
		return LOGIN;
	}
	public void setLOGIN(String lOGIN) {
		LOGIN = lOGIN;
	}
	@Column(name="PASS", nullable=false)
	public String getPASS() {
		return PASS;
	}
	public void setPASS(String pASS) {
		PASS = pASS;
	}
	@Column(name="UID_EMPLEADO", nullable=false)
	public Integer getUID_EMPLEADO() {
		return UID_EMPLEADO;
	}
	public void setUID_EMPLEADO(Integer uID_EMPLEADO) {
		UID_EMPLEADO = uID_EMPLEADO;
	}
	@Column(name="MENU", nullable=true)
	public String getMENU() {
		return MENU;
	}
	public void setMENU(String mENU) {
		MENU = mENU;
	}
	@Column(name="CHILDREN", nullable=true)
	public String getCHILDREN() {
		return CHILDREN;
	}
	public void setCHILDREN(String cHILDREN) {
		CHILDREN = cHILDREN;
	}
	@Column(name="ARGS", nullable=true)
	public String getARGS() {
		return ARGS;
	}
	public void setARGS(String aRGS) {
		ARGS = aRGS;
	}
	@Column(name="NIVEL", nullable=true)
	public Integer getNIVEL() {
		return NIVEL;
	}
	public void setNIVEL(Integer nIVEL) {
		NIVEL = nIVEL;
	}
	@Column(name="ROL", nullable=true)
	public Integer getROL() {
		return ROL;
	}
	public void setROL(Integer rOL) {
		ROL = rOL;
	}
	@Column(name="UID_UA", nullable=true)
	public Integer getUID_UA() {
		return UID_UA;
	}
	public void setUID_UA(Integer uID_UA) {
		UID_UA = uID_UA;
	}
	@Column(name="ACTIVO", nullable=false)
	public String getACTIVO() {
		return ACTIVO;
	}
	public void setACTIVO(String aCTIVO) {
		ACTIVO = aCTIVO;
	}
	@Column(name="FECHA_VENCE_PASS", nullable=true)
	public Date getFECHA_VENCE_PASS() {
		return FECHA_VENCE_PASS;
	}
	public void setFECHA_VENCE_PASS(Date fECHA_VENCE_PASS) {
		FECHA_VENCE_PASS = fECHA_VENCE_PASS;
	}
}
