package mx.gob.sct.utic.mimappir.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.stereotype.Component;

import mx.gob.sct.utic.mimappir.admseg.postgreSQL.model.SEGMENU;
import mx.gob.sct.utic.mimappir.sigtic.db2.model.MENU;
import net.sf.cglib.core.MethodWrapper;

/**
 * MenuTree class. Contains some common methods that can be used
 * for build de menu
 * 
 * @author Ivan Santiago
 *
 */
public class MenuCreator {
	private MenuNode root;
	private MenuNodeObject rootObject;
	
	public MenuCreator(){
		this.root = new MenuNode();
		this.rootObject = new MenuNodeObject();
	}
	
	/**
	 * Crea el menu a partir de una lista ordenada de registros por nivel y se enlazan por nodo padre
	 * @param id
	 */
	public List<MenuNode> createMainMenuFromList(List<MENU> registros){
		Iterator<MENU> it = registros.iterator();
		while(it.hasNext()){
			//Creamos el nodo a partir de la los registros de la base de datos
			MENU registro = it.next(); 
			MenuNode nodoNuevo = new MenuNode();
			nodoNuevo.setId(registro.getID());
			nodoNuevo.setTitle(registro.getSTATUS());			
			if(registro.getID_P() > 0){
				nodoNuevo.setParent(registro.getID_P());				
			}
			nodoNuevo.setUrl(registro.getREDIR());
			nodoNuevo.setText(registro.getNOMMENU());
			nodoNuevo.setDetail(registro.getACCIONES());
			
			MenuNode nodoPadre = root.findNode(nodoNuevo.getParent());
			
			if(nodoPadre == null){
				this.root.addChild(nodoNuevo);
			}else{
				nodoPadre.addChild(nodoNuevo);
			}
		}
		return this.root.getChildren();	
	}
	
	/**
	 * Crea el menu a partir de una lista ordenada de registros por nivel y se enlazan por nodo padre
	 * @param id
	 */
	public List<MenuNodeObject> createMainSEGMenuFromList(List<SEGMENU> registros){
		Iterator<SEGMENU> it = registros.iterator();
		while(it.hasNext()){
			//Creamos el nodo a partir de la los registros de la base de datos
			SEGMENU registro = it.next(); 
			MenuNodeObject nodoNuevo = new MenuNodeObject();
			nodoNuevo.setId(registro.getICVEMENU_PK().getIORDEN());
			nodoNuevo.setTitle(registro.getCDSCMENU());			
			
			if(registro.getIOPCPADRE()!= null)
			if(registro.getIOPCPADRE() > 0){
				nodoNuevo.setParent(registro.getIOPCPADRE());				
			}
			nodoNuevo.setUrl(registro.getCNOMPAGINA());
			nodoNuevo.setText(registro.getCDSCMENU());
			nodoNuevo.setDetail(registro.getCDSCMENU());
			
			MenuNodeObject nodoPadre = rootObject.findNodeObject(nodoNuevo.getParent());
			
			if(nodoPadre == null){
				this.rootObject.addChild(nodoNuevo);
			}else{
				nodoPadre.addChild(nodoNuevo);
			}
		}
		return this.rootObject.getChildren();	
	}

}
