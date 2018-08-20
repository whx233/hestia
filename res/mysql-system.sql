/*系统级别的SQL*/
#namespace("system")
  /*获取菜单*/
  #sql("getMenuList")
	select distinct a.* from system_menu a,system_role_menu b where a.menu_id=b.menu_id and a.menu_id<>'0'
  #end
#end