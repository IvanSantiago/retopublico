package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.GRLMUNICIPIO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEGRLMUNICIPIO_PK;

/**
 * GRLMUNICIPIO POJO INTERFACE
 * 
 * @author Ivan Santiago
 *
 */
public interface IGRLMUNICIPIO_DAO {

	List<GRLMUNICIPIO> getList();
	
	GRLMUNICIPIO save(GRLMUNICIPIO x);

	void delete(ICVEGRLMUNICIPIO_PK id);
	
}
