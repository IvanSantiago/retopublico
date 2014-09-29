package mx.gob.sct.utic.mimappir.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.sigtic.db2.model.DOC_PROC;

/**
 * Contact POJO
 * 
 * @author Ivan Santiago
 *
 */
public interface IDOC_PROC_DAO {

	List<DOC_PROC> getList();
	
	void delete(int id);
	
	DOC_PROC save(DOC_PROC x);
	
}
