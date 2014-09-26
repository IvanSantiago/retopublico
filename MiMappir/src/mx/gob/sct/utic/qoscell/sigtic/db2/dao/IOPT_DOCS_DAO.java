package mx.gob.sct.utic.qoscell.sigtic.db2.dao;

import java.util.List;

import mx.gob.sct.utic.qoscell.sigtic.db2.model.OPT_DOCS;

/**
 * Contact POJO
 * 
 * @author Ivan Santiago
 *
 */
public interface IOPT_DOCS_DAO {

	List<OPT_DOCS> getList();
	
	void delete(int id);
	
	OPT_DOCS save(OPT_DOCS x);
	
}
