package mx.gob.sct.utic.qoscell.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.qoscell.sigtic.db2.model.R_PROCESOS;

/**
 * Contact POJO
 * 
 * @author Ivan Santiago
 *
 */
public interface IR_PROCESOS_DAO {

	List<R_PROCESOS> getList();
	
	void delete(int id);
	
	R_PROCESOS save(R_PROCESOS x);

	List<R_PROCESOS> getSolicitudInfo(Integer UID_R_PROCESO);
	
}
