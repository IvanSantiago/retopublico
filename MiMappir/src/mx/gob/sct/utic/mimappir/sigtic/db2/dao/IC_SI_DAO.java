package mx.gob.sct.utic.mimappir.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.sigtic.db2.model.C_SI;

/**
 * Contact POJO
 * 
 * @author Ivan Santiago
 *
 */
public interface IC_SI_DAO {

	List<C_SI> getList();
	
	void delete(int id);
	
	C_SI save(C_SI x);
	
}
