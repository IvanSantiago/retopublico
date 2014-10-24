package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMIMAGENES;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEIMAGEN_PK;

public interface IIMAGENES_DAO {

	List<MMIMAGENES> getList();
	
	void delete(ICVEIMAGEN_PK id);
	
	MMIMAGENES save(MMIMAGENES x);
	
}
