package mx.gob.sct.utic.mimappir.admseg.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGUSUARIO;

/**
 * SEGUSUARIO DAO INTERFACE
 * 
 * @author Ivan Santiago
 *
 */
public interface ISEGUSUARIO_DAO {

	List<SEGUSUARIO> getList();
	
	void delete(int id);
	
	SEGUSUARIO save(SEGUSUARIO x);
	
}
