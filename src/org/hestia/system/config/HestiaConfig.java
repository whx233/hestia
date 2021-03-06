package org.hestia.system.config;


import org.hestia.system.controller.Index;
import org.hestia.system.controller.SystemController;
import org.hestia.system.controller.UserController;
import org.hestia.system.filter.HestiaInterceptor;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.jfinal.template.source.ClassPathSourceFactory;

public class HestiaConfig extends JFinalConfig{

	/**
	 * 配置常量
	 */
	@Override
	public void configConstant(Constants me) {
		// TODO Auto-generated method stub
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		PropKit.use("parameter.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
	}

	/**
	 * 配置路由
	 */
	@Override
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
		me.add("/", Index.class);// 第三个参数为该Controller的视图存放路径
		me.add("/system", SystemController.class);
		me.add("/user", UserController.class);
	}

	/**
	 * 配置模板引擎
	 */
	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 创建连接对象
	 * @return
	 */
	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim(),PropKit.get("driver").trim());
	}
	
	/**
	 * 配置插件
	 */
	@Override
	public void configPlugin(Plugins me) {
		// TODO Auto-generated method stub
		// 配置数据库连接池插件
		DruidPlugin druidPlugin = createDruidPlugin();
		me.add(druidPlugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		
		arp.getEngine().setSourceFactory(new ClassPathSourceFactory());
		arp.addSqlTemplate("mysql.sql");
		// 所有映射在 MappingKit 中自动化搞定
//		_MappingKit.mapping(arp);
		arp.setDevMode(true);
		me.add(arp);
		//arp.setDialect(new MysqlDialect()); //方言配置
	}

	/**
	 * 配置拦截器
	 */
	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		me.add(new SessionInViewInterceptor(true));//session拦截传递到前台
		me.addGlobalActionInterceptor(new HestiaInterceptor());//拦截请求
	}

	/**
	 * 配置处理器
	 */
	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		
	}



}
