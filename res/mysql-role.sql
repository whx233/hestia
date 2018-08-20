/*系统级别的SQL*/
#namespace("role")
  /*根据角色获取人员信息*/
  #sql("getUserRole")
	select a.user_role_id,a.user_id,b.role_id,b.role_name,b.token,b.state from system_role_user a,system_role b where a.role_id=b.role_id and a.user_id = ?
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