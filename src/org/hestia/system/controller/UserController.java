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
		if(userID == null || userID.equals("")) {
			msg.put("code", "-1");
			msg.put("msg", "传入人员编号不能为空!");
		}else {
			if(UserServiece.me.deleteUser(userID)) {
				msg.put("code", "1");
				msg.put("msg", "删除成功!");
			}else {
				msg.put("code", "-1");
				msg.put("msg", "删除失败!");
			}
		}
		renderJson(msg);
	}
	
	/**
	 * 更改用户状态
	 */
	public void updateState() {
		String userID = getPara("w_user_id");
		String state = getPara("w_state");
		HashMap<String, String> msg = new HashMap<>();
		if(userID == null || userID.equals("")) {
			msg.put("code", "-1");
			msg.put("msg", "传入人员编号不能为空!");
		}else {
			if(state==null || state.equals("") || state.equals("0")) {
				state = "0";
			}else {
				state = "1";
			}
			int i = UserServiece.me.updateState(userID,state);
			if(i>0) {
				msg.put("code", "1");
				msg.put("msg", "更新成功!");
			}else {
				msg.put("code", "-1");
				msg.put("msg", "更新失败!");
			}
		}
		renderJson(msg);
	}
	
	/**
	 * 修改密码
	 */
	public void updatePWD() {
		String userID = getPara("w_user_id");
		String newPWD = getPara("w_pwd");
		HashMap<String, String> msg = new HashMap<>();
		if(userID==null || userID.equals("")) {
			msg.put("code", "-1");
			msg.put("msg", "传入人员编号不能为空!");
		}else if (newPWD==null || newPWD.equals("")) {
			msg.put("code", "-1");
			msg.put("msg", "传入新密码不能为空!");
		}else {
			int i = UserServiece.me.updatePWD(userID, newPWD);
			if(i>0) {
				msg.put("code", "1");
				msg.put("msg", "修改成功!");
			}else {
				msg.put("code", "-1");
				msg.put("msg", "修改失败!");
			}
		}
		renderJson(msg);
	}
}
