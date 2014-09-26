package mx.gob.sct.utic.qoscell.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.qoscell.sigtic.db2.model.S_PROCESOS;

/**
 * Contact POJO
 * 
 * @author Ivan Santiago
 *
 */
public interface IS_PROCESOS_DAO {

	List<S_PROCESOS> getList();
	
	void delete(int id);
	
	S_PROCESOS save(S_PROCESOS x);
	
}
