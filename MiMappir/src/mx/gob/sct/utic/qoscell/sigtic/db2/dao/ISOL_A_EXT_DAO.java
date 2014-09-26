package mx.gob.sct.utic.qoscell.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.qoscell.sigtic.db2.model.SOL_A_EXT;

/**
 * Contact POJO
 * 
 * @author Ivan Santiago
 *
 */
public interface ISOL_A_EXT_DAO {

	List<SOL_A_EXT> getList();
	
	void delete(int id);
	
	SOL_A_EXT save(SOL_A_EXT x);

	
}
