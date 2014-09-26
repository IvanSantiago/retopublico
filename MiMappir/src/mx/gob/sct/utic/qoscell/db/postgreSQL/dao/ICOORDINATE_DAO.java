package mx.gob.sct.utic.qoscell.db.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.qoscell.db.postgreSQL.model.COORDINATE;

/**
 * COORDINATE POJO
 * 
 * @author Ivan Santiago
 *
 */
public interface ICOORDINATE_DAO {

	List<COORDINATE> getList();
	
	void delete(int id);
	
	COORDINATE save(COORDINATE x);
	
}
