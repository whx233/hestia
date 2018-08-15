package org.hestia.system.controller;

import java.util.HashMap;

import org.hestia.system.controller.service.HestiaService;
import org.hestia.system.model.RoleService;
import org.hestia.system.utils.MenuTree;

import com.jfinal.plugin.activerecord.Record;

public class SystemController extends HestiaService{
	
	/**
	 * 获取角色情况
	 */
	public void getRole() {
		
	}
	
	/**
	 * 获取菜单
	 */
	public void getMenu() {
		//权限ID
		HashMap<String, String> message = new HashMap<>();
		String roleId = getPara("role_id");
		if(roleId==null || roleId.equals("")) {
			message.put("msg", "角色ID不能为空(role_id)");
			message.put("code", "-1");
			log.error(message);
			renderJson(message);
		}
		
		MenuTree mt = new MenuTree();
		String s = mt.menuBuild(roleId);
		message.put("msg", s);
		renderJson(message);
	}
	
	/**
	 * 获取菜单
	 */
	public void loadMenu() {
		String str = getPara("role");
		String [] token = str.split(",");
		String [] role = new String[token.length];
		int j = 0;
		for(int i = 0;i<token.length;i++) {
			Record r = RoleService.me.getRoleByToken(token[i]);
			if(r!=null) {
				role[j] = r.getStr("role_id");
				j = j + 1;
			}
		}
		MenuTree mt = new MenuTree();
		String s = mt.menuBuild(role);
		System.out.println(s);
		renderJson(s);
//		renderJson("[{\"path\":\"/demo\",\"name\":\"demo\",\"component\":\"/layout/Layout\",\"children\":[{\"path\":\"dynamic-table\",\"name\":\"dynamicTable\",\"component\":\"/table/dynamicTable/index\",\"hidden\":false,\"meta\":{\"noCache\":false,\"title\":\"dynamicTable\"}},{\"path\":\"icons\",\"name\":\"icons\",\"component\":\"/svg-icons/index\",\"meta\":{\"icon\":\"icon\",\"title\":\"icons\"}}],\"meta\":{\"noCache\":false,\"icon\":\"component\",\"title\":\"demo\"}}]");
	}

}
