package org.hestia.system.controller;

import java.util.Enumeration;
import java.util.HashMap;

import org.hestia.system.controller.service.HestiaService;
import org.hestia.system.model.UserServiece;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class UserController extends HestiaService{

	public void info() {
		System.out.println("user.info");
		Enumeration<String> e = getParaNames();
		while (e.hasMoreElements()) {
			String str = (String) e.nextElement();
			System.out.println(getPara(str));
		}
		HashMap<String, Object> userSession = this.getSessionAttr("LOGIN_TOKEN");
		renderJson(userSession);
	}
	
	public void getUserList() {
		int pageNumber = getParaToInt("pageNumber");
		int pageSize = getParaToInt("pageSize");
		Page<Record> userList = UserServiece.me.getUser(pageNumber, pageSize);
		renderJson(userList);
		
	}
	/**
	 * 保存用户
	 */
	public void saveUser() {
		Record user = new Record();
		Enumeration<String> enu = getParaNames();
		while(enu.hasMoreElements()) {
			String key = enu.nextElement();
			user.set(key, getPara(key));
		}
		HashMap<String, String> msg = new HashMap<>();
		if(UserServiece.me.saveUser(user)) {
			msg.put("code", "1");
			msg.put("msg", "保存成功!");
		}else {
			msg.put("code", "-1");
			msg.put("msg", "保存失败!");
		}
		renderJson(msg);
	}
}
