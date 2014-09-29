package mx.gob.sct.utic.mimappir.admseg.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGGPOXUSR;

/**
 * SEGGPOXUSR DAO INTERFACE
 * 
 * @author Ivan Santiago
 *
 */
public interface ISEGGPOXUSR_DAO {

	List<SEGGPOXUSR> getList();
	
	void delete(int id);
	
	SEGGPOXUSR save(SEGGPOXUSR x);
	
}
