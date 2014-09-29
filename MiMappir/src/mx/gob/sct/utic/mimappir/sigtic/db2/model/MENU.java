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
@Table(name="MENUEXT")
public class MENU {

	private Integer ID;
	private Integer NIVEL;
	private String NOMMENUEXT;
	private Integer ID_P;
	private String STATUS;
	private String TABLA;
	private String ACCIONES;
	private String ARGS;
	private Integer HAS_CHILDREN;
	private String REDIR;
	private Integer NIVEL_U;
	private Integer ROL_U;
	
	@Id
	@GeneratedValue
	@Column(name="ID", nullable=false)
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}
	@Column(name="NIVEL", nullable=false)
	public Integer getNIVEL() {
		return NIVEL;
	}

	public void setNIVEL(Integer nIVEL) {
		NIVEL = nIVEL;
	}
	@Column(name="NOMMENUEXT", nullable=false)
	public String getNOMMENU() {
		return NOMMENUEXT;
	}

	public void setNOMMENU(String nOMMENU) {
		NOMMENUEXT = nOMMENU;
	}
	@Column(name="ID_P", nullable=false)
	public Integer getID_P() {
		return ID_P;
	}

	public void setID_P(Integer iD_P) {
		ID_P = iD_P;
	}
	@Column(name="STATUS", nullable=false)
	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	@Column(name="TABLA", nullable=true)
	public String getTABLA() {
		return TABLA;
	}

	public void setTABLA(String tABLA) {
		TABLA = tABLA;
	}
	@Column(name="ACCIONES", nullable=true)
	public String getACCIONES() {
		return ACCIONES;
	}

	public void setACCIONES(String aCCIONES) {
		ACCIONES = aCCIONES;
	}
	@Column(name="ARGS", nullable=true)
	public String getARGS() {
		return ARGS;
	}

	public void setARGS(String aRGS) {
		ARGS = aRGS;
	}
	@Column(name="HAS_CHILDREN", nullable=false)
	public Integer getHAS_CHILDREN() {
		return HAS_CHILDREN;
	}

	public void setHAS_CHILDREN(Integer hAS_CHILDREN) {
		HAS_CHILDREN = hAS_CHILDREN;
	}
	@Column(name="REDIR", nullable=true)
	public String getREDIR() {
		return REDIR;
	}

	public void setREDIR(String rEDIR) {
		REDIR = rEDIR;
	}
	@Column(name="NIVEL_U", nullable=true)
	public Integer getNIVEL_U() {
		return NIVEL_U;
	}

	public void setNIVEL_U(Integer nIVEL_U) {
		NIVEL_U = nIVEL_U;
	}
	@Column(name="ROL_U", nullable=true)
	public Integer getROL_U() {
		return ROL_U;
	}

	public void setROL_U(Integer rOL_U) {
		ROL_U = rOL_U;
	}
}
