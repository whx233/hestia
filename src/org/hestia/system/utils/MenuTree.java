package org.hestia.system.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class MenuTree {

	/**
	 * 获取菜单列表
	 * @param roleID
	 * @return
	 */
	public static List<MenuNode> getMenuList(Object... roleID) {
//		"select distinct a.* from system_menu a,system_role_menu b where a.menu_id=b.menu_id and a.menu_id<>'0'"
		String sql = Db.getSql("system.getMenuList");
		String in = " and b.role_id in (";
		for(int i=0;i<roleID.length;i++) {
			if(i==roleID.length-1) {
				in = in + "?"+")";
			}else {
				in = in + "?,";
			}
		}
		sql = sql + in;
		
		List<Record> recordList = Db.find(sql, roleID);
		System.out.println(recordList.size());
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
			HashMap<String, Object> meta = new HashMap<>();
			String icon = record.getStr("icon");
			if(icon!=null && !icon.equals("")) {
				meta.put("icon", record.getStr("icon"));
			}
			String title = record.getStr("title");
			if(title!=null && !title.equals("")) {
				meta.put("title", record.getStr("title"));
			}
			boolean noCache = record.getBoolean("noCache");
			if(!noCache) {
				meta.put("noCache", noCache);
			}
			if(meta.size()>0) {
				mn.setMeta(meta);
			}
			list.add(mn);
		}
		return list;
	}

	/**
	 * 拼菜单树
	 * @return
	 */
	public String menuBuild(String... roleId) {
		Object [] objRoleList = new Object[roleId.length];
		for(int i=0;i<roleId.length;i++) {
			objRoleList[i] = roleId[i];
		}
		//所有菜单列表
		List<MenuNode> list = getMenuList(objRoleList);
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
	

}
