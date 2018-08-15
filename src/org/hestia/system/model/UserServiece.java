package org.hestia.system.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class UserServiece {
	
	public final static UserServiece me = new UserServiece();

	/**
	 * 根据用户名获取用户信息
	 * @param userName
	 * @return
	 */
	public Record getUserByName(String userName) {
		//"select user_id,user_name,nick_name,`password`,state,introduction,avatar,create_time from system_users where user_name = ?";
		String sql = Db.getSql("system.getUserByName");
		return Db.findFirst(sql, userName);
	}
	/**
	 * 根据id获取用户信息
	 * @param userID
	 * @return
	 */
	public Record getUserByID(String userID) {
		//"select user_id,user_name,nick_name,`password`,state,introduction,avatar,create_time from system_users where user_id = ?";
		String sql = Db.getSql("system.getUserByID");
		return Db.findFirst(sql, userID);
	}
	
	/**
	 * 获取用户列表
	 * @return
	 */
	public Page<Record> getUser(){
		return getUser(1, 999999999);
	}
	/**
	 * 获取分页用户列表
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Record> getUser(int pageNumber, int pageSize){
		//"select user_id,user_name,nick_name,`password`,state,introduction,avatar,create_time";
		String select = Db.getSql("system.getUser_select");
		//"from system_users order by user_id asc";
		String from = Db.getSql("system.getUser_from");
		return Db.paginate(pageNumber, pageSize, select, from);
	}
	
	/**
	 * 根据用户ID获取用户权限列表
	 * @param userID
	 * @return
	 */
	public List<Record> getUserRole(String userID) {
		//"select a.user_role_id,a.user_id,b.role_id,b.role_name,b.token,b.state from system_role_user a,system_role b where a.role_id=b.role_id and a.user_id = ?";
		String sql = Db.getSql("system.getUserRole");
		return Db.find(sql, userID);
	}
}
