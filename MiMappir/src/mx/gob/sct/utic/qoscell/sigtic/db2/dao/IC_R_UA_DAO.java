package mx.gob.sct.utic.qoscell.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.qoscell.sigtic.db2.model.C_R_UA;

/**
 * Contact POJO
 * 
 * @author Ivan Santiago
 *
 */
public interface IC_R_UA_DAO {

	List<C_R_UA> getList();
	
	void delete(int id);
	
	C_R_UA save(C_R_UA x);
	
}
