/*系统级别的SQL*/
#namespace("user")
  /*删除用户*/
  #sql("deleteUser")
	delete from system_users where user_id = ?
  #end
  /*删除用户权限*/
  #sql("deleteRoleUser")
	delete from system_role_user where user_id = ?
  #end
#end