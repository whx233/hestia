package org.hestia.system.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class SystemService {
	
	public final static SystemService me = new SystemService();

	/**
	 * 获取码表
	 * @param type
	 * @return
	 */
	public List<Record> getCodeType(String type){
		type = type.toUpperCase();
		String sql = Db.getSql("getCodeType");
		return Db.find(sql, type);
	}
}
