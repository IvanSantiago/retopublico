package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMPARTICIPANTES;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEPARTICIPANTE_PK;

public interface IPARTICIPANTES_DAO {

	List<MMPARTICIPANTES> getList();
	
	void delete(ICVEPARTICIPANTE_PK id);
	
	MMPARTICIPANTES save(MMPARTICIPANTES x);
}
