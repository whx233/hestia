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
	/**
	 * 获取用户列表
	 */
	public void getUserList() {
		int pageNumber = getParaToInt("pageNumber");
		int pageSize = getParaToInt("pageSize");
		Page<Record> userList = UserServiece.me.getUser(pageNumber, pageSize);
		renderJson(userList);
		
	}
	/**
	 * 根据用户名获取用户信息
	 */
	public void getUserByName() {
		String userName = getPara("w_user_name");
		Record u = UserServiece.me.getUserByName(userName);
		HashMap<String, Object> msg = new HashMap<>();
		if(u!=null && u.getStr("user_name").equals(userName)) {
			msg.put("code", "1");
			msg.put("msg", "获取成功!");
			msg.put("userInfo", u);
		}else {
			msg.put("code", "-1");
			msg.put("msg", "未找到对应的用户!");
		}
		renderJson(msg);
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
	/**
	 * 删除用户
	 */
	public void deleteUser() {
		String userID = getPara("user_id");
		HashMap<String, String> msg = new HashMap<>();
		if(UserServiece.me.deleteUser(userID)) {
			msg.put("code", "1");
			msg.put("msg", "删除成功!");
		}else {
			msg.put("code", "-1");
			msg.put("msg", "删除失败!");
		}
		
		renderJson(msg);
	}
}
