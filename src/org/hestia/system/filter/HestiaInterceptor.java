package org.hestia.system.filter;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * 请求过滤
 * @author whx
 *
 */
public class HestiaInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		Logger log = Logger.getLogger(HestiaInterceptor.class);
		Controller con = inv.getController();
		String methodName = inv.getMethodName();//请求方法名
		/***日志记录请求***/
		log.info("*****请求方法***** "+con.getClass().getName()+"."+methodName);
		
		//登陆过滤 LOGIN_TOKEN
		HashMap<String, Object> session = con.getSessionAttr("LOGIN_TOKEN");
		if(methodName!="login" && session==null) {
			HashMap<String, String> msg = new HashMap<>();
			msg.put("code", "0");
			msg.put("msg", "还未登录，请先登录!");
			con.renderJson(msg);
		}else {
			inv.invoke();
		}
		/**
		String params = "";
		Enumeration<String> en = con.getParaNames();
		while (en.hasMoreElements()) {
			String key = en.nextElement();
			params = params + key + ":" + con.getPara(key) + ", ";
		}
		
		if (params!=null && params.endsWith(", ")) {
			int i = params.lastIndexOf(", ");
			params = params.substring(0,i);
			params = "(" + params + ")";
			log.info("*****参数***** "+params);//密码会记录下来的情况
		}**/
	}

}
