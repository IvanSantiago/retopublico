package mx.gob.sct.utic.qoscell.admseg.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.qoscell.admseg.postgreSQL.model.ICVESEGMENU_PK;
import mx.gob.sct.utic.qoscell.admseg.postgreSQL.model.SEGMENU;

/**
 * Contact POJO
 * 
 * @author Ivan Santiago
 *
 */
public interface ISEGMENU_DAO {

	List<SEGMENU> getList();
	
	void delete(int id);
	
	SEGMENU save(SEGMENU x);

	List<SEGMENU> getSpecific(ICVESEGMENU_PK ICVESEGMENU_PK);
	
}
