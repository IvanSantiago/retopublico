package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.QOSOPERADORES;

/**
 * QOSOPERADORES POJO INTERFACE
 * 
 * @author Ivan Santiago
 *
 */
public interface IQOSOPERADORES_DAO {

	List<QOSOPERADORES> getList();
	
	void delete(int id);
	
	QOSOPERADORES save(QOSOPERADORES x);
	
}
