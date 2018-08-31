package org.hestia.system.utils;

import java.util.HashMap;

import com.jfinal.plugin.activerecord.Record;

public class Tools {
	
	public final static Tools me = new Tools();
	
	/**
	 * 校验Record里面不能为空
	 * @param data
	 * @param key
	 * @return HashMap [code,key,msg] code = "-1" 说明存在空值
	 */
	public HashMap<String, String> checkNull(Record data,String [] key) {
		HashMap<String, String> msg = new HashMap<String, String>();
		msg.put("code", "0");
		msg.put("key", "");
		msg.put("msg", "ok");
		for(String str:key) {
			if(data.get(str)==null || data.get(str).equals("")) {
				msg.put("code", "-1");
				msg.put("key", str);
				msg.put("msg", "key: " + str + " 对应的值为空!");
				break;
			}
		}
		return msg;
	}
	/**
	 * 格式化数据，根据入参，打包record
	 * @param data
	 * @param key
	 * @return
	 */
	public Record formatRecord(Record data,String [] key) {
		Record r = new Record();
		for(String str:key) {
			if(data.get(str)!=null) {
				r.set(str, data.get(str));
			}
		}
		return r;
	}

}
