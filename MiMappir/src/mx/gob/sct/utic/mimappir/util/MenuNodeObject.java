package mx.gob.sct.utic.mimappir.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javassist.expr.NewArray;

public class MenuNodeObject {
	private Long id;
	private String text;
	private boolean expanded;
	private List<MenuNodeObject> children;
	private boolean leaf;
	private Long parent;
	private String url;
	private String title;
	private String detail;
	
	public MenuNodeObject() {
		this.id = (long) -1;
		this.text = "root";
		this.expanded = false;
		this.children = new ArrayList<MenuNodeObject>();
		this.leaf = true;
		this.parent = (long) -1;
		this.url="";
		this.title="";
	}
	public MenuNodeObject(Long id, String text, boolean expanded,
			List<MenuNodeObject> children, boolean leaf, Long parent,String url, String title ) {
		this.id = id;
		this.text = text;
		this.expanded = expanded;
		this.children = children;
		this.leaf = leaf;
		this.parent = parent;
		this.url=url;
		this.title=title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public List<MenuNodeObject> getChildren() {
		return children;
	}
	public void setChildren(List<MenuNodeObject> children) {
		this.children = children;
	}
	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void addChild(MenuNodeObject node) {		
		this.children.add(node);
		this.leaf = false;
	}
	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public boolean hasChildren() {
		if(this.children.size()>0){
			return true;
		}else{
			return false;
		}		 
	}
	
	public MenuNodeObject findNodeObject(Long idToFind){
		MenuNodeObject nodeToFind = null;
		//System.out.println("Buscando nodo "+idToFind+" en "+ this.id );
		if(this.id.equals(idToFind)){//Se compara este nodo para ver si este nodo es el nodo a encontrar 
			nodeToFind = this;
			//System.out.println("SE encontro el id "+idToFind +" en "+this.id);
		}else{
			if(this.hasChildren()){
				//System.out.println("El nodo "+this.id +" si tiene "+this.getChildren().size()+" hijos");
				Iterator<MenuNodeObject> it = this.children.iterator();
				while(it.hasNext() && nodeToFind == null){//Mientras tenga hijos y no haya encontrado el nodo a encontar sea nulo
					nodeToFind = it.next().findNodeObject(idToFind);						
				}//Cuando ya no tiene hijos o encontro el nodo
			}
		}
		return nodeToFind;
	}
}
