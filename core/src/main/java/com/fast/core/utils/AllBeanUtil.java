package com.fast.core.utils;

import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.beans.BeanMap;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AllBeanUtil {
	public static Object generateObject(Map<Object, Class<?>> properties) {
        BeanGenerator generator = new BeanGenerator();
        Set<Object> keySet = properties.keySet();
        for(Iterator<Object> i = keySet.iterator(); i.hasNext();) {
            String key = (String)i.next();
            generator.addProperty(key, properties.get(key));
        }
        return generator.create();
    }
	
	public static String getRealName(String proxyName) {
		return proxyName.substring("$cglib_prop_".length());
	}
	
	public static Object getValue(Object obj, String property) {
        BeanMap beanMap = BeanMap.create(obj);
        return beanMap.get(property);
    }

    public static void setValue(Object obj, String property, Object value) {
        BeanMap beanMap = BeanMap.create(obj);
        beanMap.put(property, value);
    }
    
    public static Map<String,String> bean2Map(Object object){
    	Map<String, String> map = new HashMap<String,String>();
    	Field[] fields = object.getClass().getDeclaredFields();
        for(Field field : fields) {
        	String key = getRealName(field.getName());
        	map.put(key, getValue(object,key) != null ? getValue(object,key).toString() : "");
        }
		return map;
    };

	public static String allBeanToString(Object allbean) {
		StringBuilder str = new StringBuilder();
		Field[] fields = allbean.getClass().getDeclaredFields();
        for(Field field : fields) {
        	String key = getRealName(field.getName());
        	str.append(key + "=" + getValue(allbean,key) + "\t");
        }
		return str.length() > 0 ? str.delete(str.lastIndexOf("\t"), str.lastIndexOf("\t") + 2).toString() : "";
	}
}
