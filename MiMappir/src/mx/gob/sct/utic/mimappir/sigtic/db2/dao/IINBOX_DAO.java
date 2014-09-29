package mx.gob.sct.utic.mimappir.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.sigtic.db2.model.INBOX;
/**
 * Contact POJO
 * 
 * @author Ivan Santiago
 *
 */
public interface IINBOX_DAO {

	List<INBOX> getList();
	
	void delete(int id);
	
	INBOX save(INBOX x);	
}
