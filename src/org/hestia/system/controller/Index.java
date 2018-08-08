package org.hestia.system.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hestia.system.controller.service.HestiaService;
import org.hestia.system.model.UserServiece;

import com.jfinal.plugin.activerecord.Record;

public class Index extends HestiaService{

	
	public void index() {
		//启动提示
		log.info("启动成功后显示信息");
		redirect("/index.html");
	}
	
	public void login() {
		String userName = getPara("username");
		Record user = UserServiece.me.getUserByName(userName);
		System.out.println(user);
		HashMap<String, Object> message = new HashMap<>();
		if(user==null) {
			message.put("code", "-1");
			message.put("msg", "用户名不存在");
			renderJson(message);
		}else {
			String pwdIN = getPara("password");
			String pwdOUT = user.getStr("password");
			if(pwdIN.equals(pwdOUT)) {
				HashMap<String, Object> admin = new HashMap<>();
				List<String> adminList = new ArrayList<>();
				adminList.add("admin");
				admin.put("roles", adminList);
				admin.put("token", user.getStr("user_id"));
				admin.put("introduction", user.getStr("introduction"));
				admin.put("avatar", user.getStr("avatar"));
				admin.put("name", user.getStr("nick_name"));
				message.put(userName,admin);
				
				this.setSessionAttr("LOGIN_TOKEN", admin);
				message.put("code", "1");
				message.put("msg", "登录成功");
				renderJson(message);
			}else {
				message.put("code", "-1");
				message.put("msg", "密码错误");
				renderJson(message);
				
			}
		}
		
		
		
	}
	
	public void logout() {
		HashMap<String, String> message = new HashMap<>();
		message.put("msg", "abcd");
		renderJson(message);
	}
}
