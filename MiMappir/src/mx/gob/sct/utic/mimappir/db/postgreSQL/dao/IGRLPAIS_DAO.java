package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.GRLPAIS;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEGRLPAIS_PK;

/**
 * GRLPAIS POJO INTERFACE
 * 
 * @author Ivan Santiago
 *
 */
public interface IGRLPAIS_DAO {

	List<GRLPAIS> getList();
	
	void delete(ICVEGRLPAIS_PK id);
	
	GRLPAIS save(GRLPAIS x);
	
}
