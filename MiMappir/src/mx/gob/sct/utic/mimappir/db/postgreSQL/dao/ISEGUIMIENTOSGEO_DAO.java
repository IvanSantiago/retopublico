package mx.gob.sct.utic.mimappir.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.db.postgreSQL.model.MMSEGUIMIENTOSGEO;

public interface ISEGUIMIENTOSGEO_DAO {
	
	List<MMSEGUIMIENTOSGEO> getList();
	
	void delete(Integer id);
	
	MMSEGUIMIENTOSGEO save(MMSEGUIMIENTOSGEO x);
	
}
