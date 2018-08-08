package org.hestia.system.utils;

import java.util.ArrayList;
import java.util.List;

public class MenuNode {
	private String menu_id;
	private String path;
	private String name;
	private String component;
	private Boolean is_leaf;
	private Boolean auto_expand;
	private Boolean hidden;
	private String parents_id;
	private List<MenuNode> children;
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public Boolean getIs_leaf() {
		return is_leaf;
	}
	public void setIs_leaf(Boolean is_leaf) {
		this.is_leaf = is_leaf;
	}
	public Boolean getAuto_expand() {
		return auto_expand;
	}
	public void setAuto_expand(Boolean auto_expand) {
		this.auto_expand = auto_expand;
	}
	public Boolean getHidden() {
		return hidden;
	}
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}
	public String getParents_id() {
		return parents_id;
	}
	public void setParents_id(String parents_id) {
		this.parents_id = parents_id;
	}
	public List<MenuNode> getChildren() {
		return children;
	}
	public void setChildren(List<MenuNode> children) {
		this.children = children;
	}

	public void appendChild(MenuNode menuNode) {
		if (getChildren() == null) {
			children = new ArrayList<MenuNode>();
		}
		if (menuNode != null) {
			children.add(menuNode);
		}
	}
}
