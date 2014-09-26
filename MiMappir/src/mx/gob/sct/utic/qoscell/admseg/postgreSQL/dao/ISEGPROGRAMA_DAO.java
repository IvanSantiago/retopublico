package mx.gob.sct.utic.qoscell.admseg.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.qoscell.admseg.postgreSQL.model.SEGPROGRAMA;

/**
 * SEGPROGRAMA DAO INTERFACE
 * 
 * @author Ivan Santiago
 *
 */
public interface ISEGPROGRAMA_DAO {

	List<SEGPROGRAMA> getList();
	
	void delete(int id);
	
	SEGPROGRAMA save(SEGPROGRAMA x);
	
}
