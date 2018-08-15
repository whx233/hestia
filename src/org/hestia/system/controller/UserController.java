package org.hestia.system.controller;

import java.util.Enumeration;
import java.util.HashMap;

import org.hestia.system.controller.service.HestiaService;

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
}
