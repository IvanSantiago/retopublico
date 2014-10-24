package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMCATEGORIAS;
import mx.gob.sct.utic.mimappir.db.postgreSQL.model.pk.ICVECATEGORIA_PK;

public interface ICATEGORIAS_DAO {
	
	List<MMCATEGORIAS> getList();
	
	void delete(ICVECATEGORIA_PK id);
	
	MMCATEGORIAS save(MMCATEGORIAS x);
	
}
