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
@Table(name="C_SI")
public class C_SI {
	
	private Integer UID_SI;
	private String CATEGORIA;
	private String DESCRIPCION;
	private String METADATOS;
	private Integer HAS_CHILDS;
	private String NOMBRE;
	private Integer UID_TIPO_SISTEMA;
	private Integer UID_ESTADO_SISTEMA;
	private Integer UID_EMPLEADO;
	private Integer UID_LEN_DES;
	private Integer UID_APP_SERV;
	private Integer UID_PLATAFORMA;
	private Integer UID_EMPLEADO_C;
	private Integer UID_UAN;
	private Integer S_BD;
	private Integer S_P;
	private String SDP;
	private Integer R_SDP;
	private Integer E_SDP;
	private String HASEXTUSERS;
		
	@Id
	@GeneratedValue
	@Column(name="UID_SI")
	public Integer getUID_SI() {
		return UID_SI;
	}

	public void setUID_SI(Integer uID_SI) {
		UID_SI = uID_SI;
	}
	
	@Column(name="CATEGORIA", nullable=false)
	public String getCATEGORIA() {
		return CATEGORIA;
	}

	public void setCATEGORIA(String cATEGORIA) {
		CATEGORIA = cATEGORIA;
	}
	@Column(name="DESCRIPCION", nullable=false)
	public String getDESCRIPCION() {
		return DESCRIPCION;
	}

	public void setDESCRIPCION(String dESCRIPCION) {
		DESCRIPCION = dESCRIPCION;
	}
	
	@Column(name="METADATOS", nullable=true)
	public String getMETADATOS() {
		return METADATOS;
	}

	public void setMETADATOS(String mETADATOS) {
		METADATOS = mETADATOS;
	}
	@Column(name="HAS_CHILDS", nullable=false)
	public Integer getHAS_CHILDS() {
		return HAS_CHILDS;
	}

	public void setHAS_CHILDS(Integer hAS_CHILDS) {
		HAS_CHILDS = hAS_CHILDS;
	}
	@Column(name="NOMBRE", nullable=true)
	public String getNOMBRE() {
		return NOMBRE;
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}
	@Column(name="UID_TIPO_SISTEMA", nullable=false)
	public Integer getUID_TIPO_SISTEMA() {
		return UID_TIPO_SISTEMA;
	}

	public void setUID_TIPO_SISTEMA(Integer uID_TIPO_SISTEMA) {
		UID_TIPO_SISTEMA = uID_TIPO_SISTEMA;
	}
	@Column(name="UID_ESTADO_SISTEMA", nullable=false)
	public Integer getUID_ESTADO_SISTEMA() {
		return UID_ESTADO_SISTEMA;
	}

	public void setUID_ESTADO_SISTEMA(Integer uID_ESTADO_SISTEMA) {
		UID_ESTADO_SISTEMA = uID_ESTADO_SISTEMA;
	}
	@Column(name="UID_EMPLEADO", nullable=false)
	public Integer getUID_EMPLEADO() {
		return UID_EMPLEADO;
	}

	public void setUID_EMPLEADO(Integer uID_EMPLEADO) {
		UID_EMPLEADO = uID_EMPLEADO;
	}
	@Column(name="UID_LEN_DES", nullable=false)
	public Integer getUID_LEN_DES() {
		return UID_LEN_DES;
	}

	public void setUID_LEN_DES(Integer uID_LEN_DES) {
		UID_LEN_DES = uID_LEN_DES;
	}
	@Column(name="UID_APP_SERV", nullable=false)
	public Integer getUID_APP_SERV() {
		return UID_APP_SERV;
	}

	public void setUID_APP_SERV(Integer uID_APP_SERV) {
		UID_APP_SERV = uID_APP_SERV;
	}
	@Column(name="UID_PLATAFORMA", nullable=false)
	public Integer getUID_PLATAFORMA() {
		return UID_PLATAFORMA;
	}

	public void setUID_PLATAFORMA(Integer uID_PLATAFORMA) {
		UID_PLATAFORMA = uID_PLATAFORMA;
	}
	@Column(name="UID_EMPLEADO_C", nullable=false)
	public Integer getUID_EMPLEADO_C() {
		return UID_EMPLEADO_C;
	}

	public void setUID_EMPLEADO_C(Integer uID_EMPLEADO_C) {
		UID_EMPLEADO_C = uID_EMPLEADO_C;
	}
	@Column(name="UID_UAN", nullable=false)
	public Integer getUID_UAN() {
		return UID_UAN;
	}

	public void setUID_UAN(Integer uID_UAN) {
		UID_UAN = uID_UAN;
	}
	@Column(name="S_BD", nullable=false)
	public Integer getS_BD() {
		return S_BD;
	}

	public void setS_BD(Integer s_BD) {
		S_BD = s_BD;
	}
	@Column(name="S_P", nullable=false)
	public Integer getS_P() {
		return S_P;
	}

	public void setS_P(Integer s_P) {
		S_P = s_P;
	}
	@Column(name="SDP", nullable=false)
	public String getSDP() {
		return SDP;
	}

	public void setSDP(String sDP) {
		SDP = sDP;
	}
	@Column(name="R_SDP", nullable=false)
	public Integer getR_SDP() {
		return R_SDP;
	}

	public void setR_SDP(Integer r_SDP) {
		R_SDP = r_SDP;
	}
	@Column(name="E_SDP", nullable=false)
	public Integer getE_SDP() {
		return E_SDP;
	}

	public void setE_SDP(Integer e_SDP) {
		E_SDP = e_SDP;
	}
	@Column(name="HASEXTUSERS", nullable=true)
	public String getHASEXTUSERS() {
		return HASEXTUSERS;
	}

	public void setHASEXTUSERS(String hASEXTUSERS) {
		HASEXTUSERS = hASEXTUSERS;
	}

}
