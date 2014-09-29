package mx.gob.sct.utic.mimappir.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.sigtic.db2.model.OPT_PROC;

/**
 * Contact POJO
 * 
 * @author Ivan Santiago
 *
 */
public interface IOPT_PROC_DAO {

	List<OPT_PROC> getList();
	
	void delete(int id);
	
	OPT_PROC save(OPT_PROC x);
	
}
