package mx.gob.sct.utic.mimappir.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javassist.expr.NewArray;

public class MenuNode {
	private int id;
	private String text;
	private boolean expanded;
	private List<MenuNode> children;
	private boolean leaf;
	private int parent;
	private String url;
	private String title;
	private String detail;
	
	public MenuNode() {
		this.id = -1;
		this.text = "root";
		this.expanded = false;
		this.children = new ArrayList<MenuNode>();
		this.leaf = true;
		this.parent = -1;
		this.url="";
		this.title="";
	}
	public MenuNode(int id, String text, boolean expanded,
			List<MenuNode> children, boolean leaf, int parent,String url, String title ) {
		this.id = id;
		this.text = text;
		this.expanded = expanded;
		this.children = children;
		this.leaf = leaf;
		this.parent = parent;
		this.url=url;
		this.title=title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public List<MenuNode> getChildren() {
		return children;
	}

	public void setChildren(List<MenuNode> children) {
		this.children = children;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
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
	public void addChild(MenuNode node) {		
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
	
	public MenuNode findNode(int idToFind){
		MenuNode nodeToFind = null;
		//System.out.println("Buscando nodo "+idToFind+" en "+ this.id );
		if(this.id==(idToFind)){//Se compara este nodo para ver si este nodo es el nodo a encontrar 
			nodeToFind = this;
			//System.out.println("SE encontro el id "+idToFind +" en "+this.id);
		}else{
			if(this.hasChildren()){
				//System.out.println("El nodo "+this.id +" si tiene "+this.getChildren().size()+" hijos");
				Iterator<MenuNode> it = this.children.iterator();
				while(it.hasNext() && nodeToFind == null){//Mientras tenga hijos y no haya encontrado el nodo a encontar sea nulo
					nodeToFind = it.next().findNode(idToFind);						
				}//Cuando ya no tiene hijos o encontro el nodo
			}
		}
		return nodeToFind;
	}
}
