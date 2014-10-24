package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMCOMENTARIOSHISTO;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVECOMENTARIO_PK;

public interface ICOMENTARIOSHISTO_DAO {

	List<MMCOMENTARIOSHISTO> getList();
	
	void delete(ICVECOMENTARIO_PK id);
	
	MMCOMENTARIOSHISTO save(MMCOMENTARIOSHISTO x);
	
}
