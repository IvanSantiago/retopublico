package mx.gob.sct.utic.mimappir.sigtic.db2.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


/**
 * Contact POJO
 * 
 * @author Ivan Santiago
 *
 */
public interface ISQL_ARG_DAO {
	List<Object> getList(String SQLQuery, String[] params,HttpServletRequest request);
}
