package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.QOSLLAMADAS;

/**
 * QOSLLAMADAS POJO INTERFACE
 * 
 * @author Ivan Santiago
 *
 */
public interface IQOSLLAMADAS_DAO {

	List<QOSLLAMADAS> getList();
	
	void delete(int id);
	
	QOSLLAMADAS save(QOSLLAMADAS x);
	
}
