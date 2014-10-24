package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMPREMIACIONES;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEPREMIACION_PK;


public interface IPREMIACIONES_DAO {

	List<MMPREMIACIONES> getList();
	
	void delete(ICVEPREMIACION_PK id);
	
	MMPREMIACIONES save(MMPREMIACIONES x);
	
}
