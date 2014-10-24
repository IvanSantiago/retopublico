package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMCOMENTARIOSGEO;

public interface ICOMENTARIOSGEO_DAO {

	List<MMCOMENTARIOSGEO> getList();
	
	void delete(Integer id);
	
	MMCOMENTARIOSGEO save(MMCOMENTARIOSGEO x);
}
