package org.hestia.system.controller;

import java.util.HashMap;

import org.hestia.system.controller.service.HestiaService;

public class UserController extends HestiaService{

	public void info() {
		System.out.println("user info");
		HashMap<String, Object> userSession = this.getSessionAttr("LOGIN_TOKEN");
		renderJson(userSession);
	}
}
