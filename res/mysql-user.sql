/*系统级别的SQL*/
#namespace("user")
  /*删除用户*/
  #sql("deleteUser")
	delete from system_user where user_id = ?
  #end
  /*删除用户权限*/
  #sql("deleteRoleUser")
	delete from system_role_user where user_id = ?
  #end
  /*根据用户名获取人员信息*/
  #sql("getUserByName")
	select a.user_id,a.user_name,a.nick_name,a.email,a.`password`,a.state,a.introduction,a.avatar,a.create_time,group_concat(c.role_id SEPARATOR ',') role_id,group_concat(c.role_name SEPARATOR ',') role_name,group_concat(c.token SEPARATOR ',') token from system_user a,system_role_user b,system_role c where a.user_id = b.user_id and b.role_id = c.role_id and c.state='1' and a.user_name = ? group by a.user_id,a.user_name,a.nick_name,a.`password`,a.state,a.introduction,a.avatar,a.create_time order by a.user_id
  #end
  /*根据ID获取人员信息*/
  #sql("getUserByID")
	select a.user_id,a.user_name,a.nick_name,a.email,a.`password`,a.state,a.introduction,a.avatar,a.create_time,group_concat(c.role_id SEPARATOR ',') role_id,group_concat(c.role_name SEPARATOR ',') role_name,group_concat(c.token SEPARATOR ',') token from system_user a,system_role_user b,system_role c where a.user_id = b.user_id and b.role_id = c.role_id and c.state='1' and a.user_id = ? group by a.user_id,a.user_name,a.nick_name,a.`password`,a.state,a.introduction,a.avatar,a.create_time order by a.user_id
  #end
  #sql("getUser_select")
	select a.user_id,a.user_name,a.nick_name,a.email,a.`password`,a.state,a.introduction,a.avatar,a.create_time,group_concat(c.role_id SEPARATOR ',') role_id,group_concat(c.role_name SEPARATOR ',') role_name,group_concat(c.token SEPARATOR ',') token
  #end
  
  #sql("getUser_from")
	from system_user a,system_role_user b,system_role c where a.user_id = b.user_id and b.role_id = c.role_id and c.state='1' group by a.user_id,a.user_name,a.nick_name,a.`password`,a.state,a.introduction,a.avatar,a.create_time order by a.user_id
  #end
  #sql("updateState")
	update system_user set state = ? where user_id = ?
  #end
  #sql("updatePWD")
	update system_user set password = ? where user_id = ?
  #end
#end