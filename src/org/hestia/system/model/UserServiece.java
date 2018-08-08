package org.hestia.system.model;

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
		String sql = "select user_id,user_name,nick_name,`password`,state,introduction,avatar,create_time from system_users where user_name = ?";
		return Db.findFirst(sql, userName);
	}
	/**
	 * 根据id获取用户信息
	 * @param userID
	 * @return
	 */
	public Record getUserByID(String userID) {
		String sql = "select user_id,user_name,nick_name,`password`,state,introduction,avatar,create_time from system_users where user_id = ?";
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
		String select = "select user_id,user_name,nick_name,`password`,state,introduction,avatar,create_time";
		String from = "from system_users order by user_id asc";
		return Db.paginate(pageNumber, pageSize, select, from);
	}
}
