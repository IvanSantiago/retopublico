package mx.gob.sct.utic.qoscell.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.qoscell.sigtic.db2.model.USUARIOS;

/**
 * Contact POJO
 * 
 * @author Ivan Santiago
 *
 */
public interface IUSUARIOS_DAO {

	List<USUARIOS> getList();
	
	void delete(int id);
	
	USUARIOS save(USUARIOS x);
	
}
