package org.hestia.system.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * 角色
 * @author whx
 *
 */
public class RoleService {
	
	public final static RoleService me = new RoleService();

	/**
	 * 根据token获取角色信息
	 * @param token
	 * @return
	 */
	public Record getRoleByToken(String token) {
		//"select role_id,role_name,token,state from system_role where token = ?";
		String sql = Db.getSql("system.getRoleByToken");
		return Db.findFirst(sql, token);
	}
	/**
	 * 根据ID获取角色信息
	 * @param id
	 * @return
	 */
	public Record getRoleByID(String id) {
		//"select role_id,role_name,token,state from system_role where role_id = ?";
		String sql = Db.getSql("system.getRoleByID");
		return Db.findFirst(sql, id);
	}
	
	/**
	 * 获取角色清单
	 * @return
	 */
	public List<Record> getRoleList(){
		String sql = Db.getSql("system.getRoleList");
		return Db.find(sql);
	}
}
