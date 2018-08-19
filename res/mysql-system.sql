/*系统级别的SQL*/
#namespace("system")
  /*获取菜单*/
  #sql("getMenuList")
	select distinct a.* from system_menu a,system_role_menu b where a.menu_id=b.menu_id and a.menu_id<>'0'
  #end
  /*根据用户名获取人员信息*/
  #sql("getUserByName")
	select a.user_id,a.user_name,a.nick_name,a.`password`,a.state,a.introduction,a.avatar,a.create_time,group_concat(c.role_id SEPARATOR ',') role_id,group_concat(c.role_name SEPARATOR ',') role_name,group_concat(c.token SEPARATOR ',') token from system_users a,system_role_user b,system_role c where a.user_id = b.user_id and b.role_id = c.role_id and c.state='1' and a.user_name = ? group by a.user_id,a.user_name,a.nick_name,a.`password`,a.state,a.introduction,a.avatar,a.create_time order by a.user_id
  #end
  /*根据ID获取人员信息*/
  #sql("getUserByID")
	select a.user_id,a.user_name,a.nick_name,a.`password`,a.state,a.introduction,a.avatar,a.create_time,group_concat(c.role_id SEPARATOR ',') role_id,group_concat(c.role_name SEPARATOR ',') role_name,group_concat(c.token SEPARATOR ',') token from system_users a,system_role_user b,system_role c where a.user_id = b.user_id and b.role_id = c.role_id and c.state='1' and a.user_id = ? group by a.user_id,a.user_name,a.nick_name,a.`password`,a.state,a.introduction,a.avatar,a.create_time order by a.user_id
  #end
  /*根据角色获取人员信息*/
  #sql("getUserRole")
	select a.user_role_id,a.user_id,b.role_id,b.role_name,b.token,b.state from system_role_user a,system_role b where a.role_id=b.role_id and a.user_id = ?
  #end
  
  #sql("getUser_select")
	select a.user_id,a.user_name,a.nick_name,a.`password`,a.state,a.introduction,a.avatar,a.create_time,group_concat(c.role_id SEPARATOR ',') role_id,group_concat(c.role_name SEPARATOR ',') role_name,group_concat(c.token SEPARATOR ',') token
  #end
  
  #sql("getUser_from")
	from system_users a,system_role_user b,system_role c where a.user_id = b.user_id and b.role_id = c.role_id and c.state='1' group by a.user_id,a.user_name,a.nick_name,a.`password`,a.state,a.introduction,a.avatar,a.create_time order by a.user_id
  #end
  
  #sql("getRoleByToken")
	select role_id,role_name,token,state from system_role where token = ?
  #end
  
  #sql("getRoleByID")
	select role_id,role_name,token,state from system_role where role_id = ?
  #end
  /*获取码表清单*/
  #sql("getCodeType")
	select a.type_name,a.type_code,a.type_value,a.remark from system_code a where upper(a.type_name) = ?
  #end
  /*获取角色列表*/
  #sql("getRoleList")
	select a.role_id,a.role_name,a.token from system_role a where a.state='1'
  #end
#end