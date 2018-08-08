package org.hestia.system.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class MenuTree {

	public static List<MenuNode> getMenuList(String roleID) {
		String sql = "select a.* from system_menu a,system_role_menu b where a.menu_id=b.menu_id and a.menu_id<>'0' and b.role_id=?";
		List<Record> recordList = Db.find(sql, roleID);

		List<MenuNode> list = new ArrayList<>();
		for (int i = 0; i < recordList.size(); i++) {
			Record record = recordList.get(i);
			MenuNode mn = new MenuNode();
			mn.setMenu_id(record.getStr("menu_id"));
			mn.setPath(record.getStr("path"));
			mn.setName(record.getStr("name"));
			mn.setComponent(record.getStr("component"));
			mn.setIs_leaf(record.getBoolean("is_leaf"));
			mn.setAuto_expand(record.getBoolean("auto_expand"));
			mn.setHidden(record.getBoolean("hidden"));
			mn.setParents_id(record.getStr("parents_id"));
			list.add(mn);
		}
		return list;
	}

	/**
	 * 拼菜单树
	 * @return
	 */
	public String menuBuild(String roleId) {
		//所有菜单列表
		List<MenuNode> list = getMenuList(roleId);
		List<MenuNode> nodeList = new ArrayList<MenuNode>();
		
		for (MenuNode menuNode : list) {
			if (!menuNode.getIs_leaf()) {
				// 防止垃圾数据造成没有子孙的空树枝节点。
				// 空树枝节点也需要一个空chidren子节点，否则页面树会进入死循环。
				menuNode.appendChild(null);
			}
			boolean mark = false;
			for (MenuNode node2 : list) {
				if (menuNode.getParents_id().equals(node2.getMenu_id())) {
					mark = true;
					node2.appendChild(menuNode);
					break;
				}
			}
			if (!mark) {
				nodeList.add(menuNode);
			}
		}
		// 转换成Json
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String str = gson.toJson(nodeList);
		return str;
	}
	
	/**
	 * 获取跟节点列表
	 * @param list
	 * @return
	 */
//	public List<MenuNode> getRootNodes(List<MenuNode> list){
//		List<MenuNode> rootNodes = new ArrayList<>();
//		for(MenuNode n:list) {
//			if(rootNode(n, list)) {
//				rootNodes.add(n);
//			}
//		}
//		return rootNodes;
//	}
	
	/**
	 * 判断是否跟节点
	 * @param node
	 * @return
	 */
//	public boolean rootNode(MenuNode node,List<MenuNode> nodeList) {
//		boolean isRootNode = true;
//		for(MenuNode n:nodeList) {
//			if(node.getParents_id().equals(n.getMenu_id())) {
//				isRootNode = false;
//				break;
//			}
//		}
//		return isRootNode;
//	}

}
