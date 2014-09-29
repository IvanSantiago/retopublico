package mx.gob.sct.utic.mimappir.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.sigtic.db2.model.MENU;

/**
 * Contact POJO
 * 
 * @author Ivan Santiago
 *
 */
public interface IMENU_DAO {

	List<MENU> getList();
	
	List<MENU> getMenuListForUser(String UID_USUARIO);	
	
	void delete(int id);
	
	MENU save(MENU x);
	
}
