package org.hestia.system.model;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hestia.system.utils.Sequence;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
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
	
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	public boolean saveUser(Record inRecord) {
		boolean succeed = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				// TODO Auto-generated method stub
				int userID = Sequence.me.nextval("USER_ID");
				Record user = new Record();
				
				user.set("user_id", userID);
				user.set("create_time", new Date());
				user.set("user_name", inRecord.getStr("user_name"));
				user.set("nick_name", inRecord.getStr("nick_name"));
				user.set("password", inRecord.getStr("password"));
				user.set("introduction", inRecord.getStr("introduction"));
				user.set("state", "1");
				user.set("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
				if(Db.save("system_users", user)) {
					boolean r = false;
					String roleStr = inRecord.get("role");
					String [] str = roleStr.split(",");
					for(String s:str) {
						Record role = new Record();
						role.set("user_role_id", Sequence.me.nextval("USER_ROLE_ID"));
						role.set("role_id", s);
						role.set("user_id", userID);
						if(Db.save("system_role_user", role)){
							r = true;
						}else {
							return false;
						}
					}
					return r;
				}else {
					return false;
				}
				
			}
		});
		return succeed;
	}
	
	/**
	 * 删除用户
	 * @param userID
	 * @return
	 */
	public boolean deleteUser(String userID) {
		boolean succeed = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				// TODO Auto-generated method stub
				String sql = Db.getSql("user.deleteUser");
				String sqlR = Db.getSql("user.deleteRoleUser");
				int i = Db.delete(sql, userID);
				if(i>0) {
					i = Db.delete(sqlR, userID);
					if(i>0) {
						return true;
					}else {
						return false;
					}
				}else {
					return false;
				}
			}
		});
		return succeed;
	}
}
