package mx.gob.sct.utic.qoscell.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.qoscell.sigtic.db2.model.C_03_02_EMPLEADOS;

/**
 * C_03_02_EMPLEADOS DAO INTERFACE
 * 
 * @author Ivan Santiago
 *
 */
public interface IC_03_02_EMPLEADOS_DAO {

	List<C_03_02_EMPLEADOS> getList();
	
	void delete(int id);
	
	C_03_02_EMPLEADOS save(C_03_02_EMPLEADOS x);
	
}
