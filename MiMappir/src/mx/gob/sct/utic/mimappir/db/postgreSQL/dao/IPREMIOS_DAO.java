package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMPREMIOS;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVEPREMIO_PK;


public interface IPREMIOS_DAO {

	List<MMPREMIOS> getList();
	
	void delete(ICVEPREMIO_PK id);
	
	MMPREMIOS save(MMPREMIOS x);
	
}
