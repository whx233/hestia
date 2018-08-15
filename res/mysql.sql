#namespace("system")
  #sql("getMenuList")
	select distinct a.* from system_menu a,system_role_menu b where a.menu_id=b.menu_id and a.menu_id<>'0'
  #end
  
  #sql("getUserByName")
	select user_id,user_name,nick_name,`password`,state,introduction,avatar,create_time from system_users where user_name = ?
  #end
  
  #sql("getUserByID")
	select user_id,user_name,nick_name,`password`,state,introduction,avatar,create_time from system_users where user_id = ?
  #end
  
  #sql("getUserRole")
	select a.user_role_id,a.user_id,b.role_id,b.role_name,b.token,b.state from system_role_user a,system_role b where a.role_id=b.role_id and a.user_id = ?
  #end
  
  #sql("getUser_select")
	select user_id,user_name,nick_name,`password`,state,introduction,avatar,create_time
  #end
  
  #sql("getUser_from")
	from system_users order by user_id asc
  #end
  
  #sql("getRoleByToken")
	select role_id,role_name,token,state from system_role where token = ?
  #end
  
  #sql("getRoleByID")
	select role_id,role_name,token,state from system_role where role_id = ?
  #end
  
#end