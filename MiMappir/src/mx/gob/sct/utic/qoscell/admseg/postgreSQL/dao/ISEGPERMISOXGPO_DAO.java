package mx.gob.sct.utic.qoscell.admseg.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.qoscell.admseg.postgreSQL.model.SEGPERMISOXGPO;

/**
 * SEGPERMISOXGPO DAO INTERFACE
 * 
 * @author Ivan Santiago
 *
 */
public interface ISEGPERMISOXGPO_DAO {

	List<SEGPERMISOXGPO> getList();
	
	void delete(int id);
	
	SEGPERMISOXGPO save(SEGPERMISOXGPO x);
	
}
