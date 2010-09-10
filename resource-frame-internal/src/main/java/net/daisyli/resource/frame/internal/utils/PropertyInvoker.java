package net.daisyli.resource.frame.internal.utils;

import java.lang.reflect.Method;

public class PropertyInvoker {
	public static Object getter(Object obj, String attr) {

		try {
			Method method = obj.getClass().getMethod("get" + initStr(attr));
			Object val = method.invoke(obj);
			return val;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Boolean setter(Object obj, String attr, Object val,Class<?> clazz) {

		try {
			Method method = obj.getClass().getMethod("set" + initStr(attr),
					clazz);
			method.invoke(obj, val);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String initStr(String old) {

		String str = old.substring(0, 1).toUpperCase() + old.substring(1);
		return str;
	}
}
