package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.QOSCSENALGEO;

/**
 * QOSCSENALGEO POJO INTERFACE
 * 
 * @author Ivan Santiago
 *
 */
public interface IQOSCSENALGEO_DAO {

	List<QOSCSENALGEO> getList();
	
	void delete(int id);
	
	QOSCSENALGEO save(QOSCSENALGEO x);
	
}
