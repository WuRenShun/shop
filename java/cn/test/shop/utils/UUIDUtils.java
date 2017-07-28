package cn.test.shop.utils;

import java.util.UUID;
/**
 * 生成随机字符串的的工具
 * @author RENSHUN
 *
 */


public class UUIDUtils {
	
	public static String getUUID(){
		
		return UUID.randomUUID().toString().replace("-", "");
		
	}

}
