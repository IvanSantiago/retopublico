package mx.gob.sct.utic.mimappir.admseg.postgreSQL.services;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.admseg.postgreSQL.dao.SEGGRUPO_DAO;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.dao.SEGMENU_DAO;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.dao.SEGPROGRAMA_DAO;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.dao.SEGUSUARIO_DAO;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.dao.SEGSISTEMA_DAO;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.dao.SEGGPOXUSR_DAO;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.dao.SEGPERMISOXGPO_DAO;

import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGGPOXUSR;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGGRUPO;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGMENU;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGPERMISOXGPO;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGPROGRAMA;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGSISTEMA;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGUSUARIO;
import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.pk.ICVESEGMENU_PK;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SEGMENU Service
 * 
 * @author Ivan Santiago
 * 
 */
@Service
public class SEGMENU_Service {
	
	private SEGUSUARIO_DAO SEGUSUARIO_DAO;
	private SEGGPOXUSR_DAO SEGGPOXUSR_DAO;
	private SEGSISTEMA_DAO SEGSISTEMA_DAO;
	private SEGPERMISOXGPO_DAO SEGPERMISOXGPO_DAO;
	private SEGPROGRAMA_DAO SEGPROGRAMA_DAO;
	private SEGMENU_DAO SEGMENU_DAO;
	/**
	 * Get all SEGMENU
	 * @return
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS")
	public List<SEGMENU> getMenuList(){
		return SEGMENU_DAO.getList();
	}
	
	/**
	 * Get all Menu options for a user
	 * @return
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS")
	public List<SEGMENU> getMenuForUser(String LOGIN){
		SEGSISTEMA segsistema = SEGSISTEMA_DAO.getSistema(new Short("48"));
		SEGUSUARIO segusuario = SEGUSUARIO_DAO.searchDatabase(LOGIN);
		List<SEGMENU> menuDelUsuario = new ArrayList<SEGMENU>();
		if(segusuario!= null){
			List<SEGGPOXUSR> gruposDelUsuario = SEGGPOXUSR_DAO.getListForUserOnSystem(segusuario, segsistema);
			List<Short> programasDeLosGrupo = SEGPERMISOXGPO_DAO.getListFromGroups(gruposDelUsuario);
			List<SEGPROGRAMA> programasConPermisos = SEGPROGRAMA_DAO.getListFromPermises(programasDeLosGrupo, segsistema);
			menuDelUsuario = SEGMENU_DAO.getListFromPermises(programasConPermisos);			
		}
		return menuDelUsuario;
	}

	/**
	 * Get all Menu options for a user
	 * @return
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS")
	public List<SEGUSUARIO> getSEGGPOXUSR(){
		return SEGMENU_DAO.getSpecificSEGGPOXUSR();
	}
	
	/**
	 * Create new Contact/SEGMENU
	 * @param data - json data from request
	 * @return created SEGMENU
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS")
	public List<SEGMENU> create(Object data){
		
        List<SEGMENU> newSEGMENU = new ArrayList<SEGMENU>();
		
		List<SEGMENU> list = null;//util.getSEGMENUFromRequest(data);
		
		for (SEGMENU x : list){
			newSEGMENU.add(SEGMENU_DAO.save(x));
		}
		
		return newSEGMENU;
	}
	
	
	/**
	 * Update contact/SEGMENU
	 * @param data - json data from request
	 * @return updated SEGMENU
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS")
	public List<SEGMENU> update(Object data){
		
		List<SEGMENU> returnSEGMENU = new ArrayList<SEGMENU>();
		
		List<SEGMENU> updatedSEGMENU = null;//util.getSEGMENUFromRequest(data);
		
		for (SEGMENU x : updatedSEGMENU){
			returnSEGMENU.add(SEGMENU_DAO.save(x));
		}
		
		return returnSEGMENU;
	}
	
	/**
	 * Delete contact/SEGMENU
	 * @param data - json data from request
	 */
	@Transactional(value="transactionManager_ADMSEG_POSGIS")
	public void delete(Object data){
		
		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){
			
/*			List<Integer> deleteSEGMENU = util.getListIdFromJSON(data);
			
			for (Integer id : deleteSEGMENU){
				DAO.delete(id);
			}*/
			
		} else { //it is only one object - cast to object/bean
			
			Integer id = Integer.parseInt(data.toString());
			
			SEGMENU_DAO.delete(id);
		}
	}

	/**
	 * Spring use - DI
	 * @param SEGSISTEMA_DAO DAO
	 */
	@Autowired
	public void setSEGSISTEMA_DAO(SEGSISTEMA_DAO dao) {
		this.SEGSISTEMA_DAO = dao;
	}	
	/**
	 * Spring use - DI
	 * @param SEGMENU_DAO
	 */
	@Autowired
	public void setSEGMENU_DAO(SEGMENU_DAO dao) {
		this.SEGMENU_DAO = dao;
	}
	/**
	 * Spring use - DI
	 * @param SEGUSUARIO_DAO
	 */
	@Autowired
	public void setSEGUSUARIO_DAO(SEGUSUARIO_DAO dao) {
		this.SEGUSUARIO_DAO = dao;
	}
	/**
	 * Spring use - DI
	 * @param SEGGPOXUSR_DAO DAO
	 */
	@Autowired
	public void setSEGGPOXUSR_DAO(SEGGPOXUSR_DAO dao) {
		this.SEGGPOXUSR_DAO = dao;
	}
	/**
	 * Spring use - DI
	 * @param SEGPERMISOXGPO_DAO DAO
	 */
	@Autowired
	public void setSEGPERMISOXGPO_DAO(SEGPERMISOXGPO_DAO dao) {
		this.SEGPERMISOXGPO_DAO = dao;
	}
	/**
	 * Spring use - DI
	 * @param SEGPROGRAMA_DAO DAO
	 */
	@Autowired
	public void setSEGPROGRAMA_DAO(SEGPROGRAMA_DAO dao) {
		this.SEGPROGRAMA_DAO = dao;
	}

}
