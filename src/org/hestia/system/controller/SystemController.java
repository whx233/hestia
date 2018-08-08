package org.hestia.system.controller;

import java.util.HashMap;

import org.hestia.system.controller.service.HestiaService;
import org.hestia.system.utils.MenuTree;

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
		System.out.println(s);
		message.put("msg", s);
		renderJson(message);
	}

}
