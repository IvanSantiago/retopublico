package mx.gob.sct.utic.qoscell.sigtic.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mx.gob.sct.utic.qoscell.sigtic.db2.dao.SQL_ARG_DAO;
import mx.gob.sct.utic.qoscell.sigtic.db2.dao.S_PROCESOS_DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Sistemas Service
 * 
 * @author Ivan Santiago
 * 
 */
@Service
public class SQL_ARG_Service {
	
	private SQL_ARG_DAO DAO;
	/**
	 * Spring use - DI
	 * @param sistemaDAO
	 */
	@Autowired
	public void setDAO(SQL_ARG_DAO dao) {
		this.DAO = dao;
	}
	/**
	 * Get all Objects of a SQLQuery
	 * @param String SQLQuery
	 * @return List of Objects of the SQLQuery
	 */
	@Transactional(readOnly=true)
	public List<Object> getList(String SQLQuery, String[] params,HttpServletRequest request){
		return DAO.getList(SQLQuery, params, request);
	}
}
