package mx.gob.sct.utic.qoscell.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.qoscell.sigtic.db2.model.SOL_SI_PERF;

/**
 * Contact POJO
 * 
 * @author Ivan Santiago
 *
 */
public interface ISOL_SI_PERF_DAO {

	List<SOL_SI_PERF> getList();
	
	void delete(int id);
	
	SOL_SI_PERF save(SOL_SI_PERF x);

	List<SOL_SI_PERF> getPerfilesForSis(Integer UID_SI);
	
}
