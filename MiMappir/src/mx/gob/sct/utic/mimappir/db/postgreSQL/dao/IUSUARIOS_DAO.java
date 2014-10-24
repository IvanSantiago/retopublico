package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMUSUARIOS;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEUSUARIO_PK;


public interface IUSUARIOS_DAO {

	List<MMUSUARIOS> getList();
		
	void delete(ICVEUSUARIO_PK id);
		
	MMUSUARIOS save(MMUSUARIOS x);
		
	
}
