package mx.gob.sct.utic.qoscell.admseg.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.qoscell.admseg.postgreSQL.model.SEGSISTEMA;

/**
 * SEGSISTEMA DAO INTERFACE
 * 
 * @author Ivan Santiago
 *
 */
public interface ISEGSISTEMA_DAO {

	List<SEGSISTEMA> getList();
	
	void delete(int id);
	
	SEGSISTEMA save(SEGSISTEMA x);
	
}
