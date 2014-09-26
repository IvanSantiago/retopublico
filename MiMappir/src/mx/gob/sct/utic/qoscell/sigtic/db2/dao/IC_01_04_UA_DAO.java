package mx.gob.sct.utic.qoscell.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.qoscell.sigtic.db2.model.C_01_04_UA;

/**
 * Contact POJO
 * 
 * @author Ivan Santiago
 *
 */
public interface IC_01_04_UA_DAO {

	List<C_01_04_UA> getList();
	
	void delete(int id);
	
	C_01_04_UA save(C_01_04_UA x);
	
}
