package mx.gob.sct.utic.qoscell.admseg.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.qoscell.admseg.postgreSQL.model.SEGGRUPO;

/**
 * SEGGRUPO DAO INTERFACE
 * 
 * @author Ivan Santiago
 *
 */
public interface ISEGGRUPO_DAO {

	List<SEGGRUPO> getList();
	
	void delete(int id);
	
	SEGGRUPO save(SEGGRUPO x);
	
}
