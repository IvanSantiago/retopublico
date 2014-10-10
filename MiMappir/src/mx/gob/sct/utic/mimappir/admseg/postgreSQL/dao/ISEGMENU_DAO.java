package mx.gob.sct.utic.mimappir.admseg.postgreSQL.dao;

import java.util.List;

import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.pk.ICVESEGMENU_PK;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGMENU;

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
