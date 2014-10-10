package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.GRLENTIDADFED;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEGRLENTIDADFED_PK;

/**
 * GRLENTIDADFED POJO INTERFACE
 * 
 * @author Ivan Santiago
 *
 */
public interface IGRLENTIDADFED_DAO {

	List<GRLENTIDADFED> getList();
	
	GRLENTIDADFED save(GRLENTIDADFED x);

	void delete(ICVEGRLENTIDADFED_PK id);
	
}
