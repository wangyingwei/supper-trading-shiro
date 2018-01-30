package com.eccl.common.util.objectUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ObjectUtil {

	/**
	 * 将Object转成double
	 * 
	 * @param value
	 *            需要转换的值
	 * @param defaultValue
	 *            转换失败后返回的值
	 * @return 转换后的值
	 * @author 郭辉
	 */
	public static double getDouble(Object value, double defaultValue) {
		try {
			return Double.parseDouble(value.toString().trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 将Object转成int
	 * 
	 * @param value
	 *            需要转换的值
	 * @param defaultValue
	 *            转换失败后返回的值
	 * @return 转换后的值
	 * @author 郭辉
	 */
	public static int stringToInt(Object value, int defaultValue) {
		try {
			return Integer.parseInt(value.toString().trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 将Object转成String
	 * 
	 * @param value
	 *            需要转换的值
	 * @return 转换后的值，值为NULL时返回空字符串
	 * @author 郭辉
	 */
	public static String getStr(Object value) {
		if (value == null)
			return "";
		else
			return value.toString().trim();
	}

	/**
	 * 将Object转成String
	 * 
	 * @param value
	 *            需要转换的值
	 * @param defaultValue
	 *            转换失败后返回的值
	 * @return 转换后的值
	 * @author 郭辉
	 */
	public static String getStr(Object value, String defaultValue) {
		if (value == null)
			return defaultValue;
		else
			return value.toString().trim();
	}

	/**
	 * 深度复制Object，注：Object中包含的对象，必须实现java.io.Serializable接口
	 * 
	 * @param src
	 *            复制源
	 * @return 副本
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object objectCopy(Object object) {
		Object dest = null;
		try {
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(byteOut);
			out.writeObject(object);
			ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
			ObjectInputStream in = new ObjectInputStream(byteIn);
			dest = in.readObject();
		} catch (Exception e) {
			e.toString();
		}
		return dest;
	}

	/**
	 * 将Object转成String
	 * 
	 * @param value
	 *            需要转换的值
	 * @return 转换后的值，值为NULL时返回空字符串
	 * @author 郭辉
	 */
	public static boolean isNullOrEmptyStr(String value) {
		if (value == null) {
			return true;
		} else if ("".equals(value)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取obj对象fieldName的Field
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	/**
	 * 获取obj对象fieldName的属性值
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getValueByFieldName(Object obj, String fieldName)
			throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if (field != null) {
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}

	/**
	 * 设置obj对象fieldName的属性值
	 * 
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void setValueByFieldName(Object obj, String fieldName, Object value)
			throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}

	public static void setValueByMethod(Object obj, String fieldName, Object value) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		boolean loop = true;
		for (Class<?> superClass = obj.getClass().getSuperclass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			Class fatherClass = superClass;
			Field ff[] = fatherClass.getDeclaredFields();
			for (int i = 0; i < ff.length; i++) {
				Field f = ff[i];// 取出每一个属性，如deleteDate
				if (f.getName().equals(fieldName)) {
					Method setMethod = fatherClass.getMethod("set" + upperHeadChar(f.getName()), f.getType());
					setMethod.invoke(obj, value);
					loop = false;
					break;
				}
			}
			if (!loop) {
				break;
			}
		}

	}

	public static List<String> getFields(Class t) {
		Field[] fields = t.getDeclaredFields();
		List<String> list = new ArrayList<String>();
		for (Field f : fields) {
			String name = f.getName();
			list.add(name);
		}
		return list;
	}

	public static Object getDefaultConstrucotrObject(Class type) {
		Object o = null;
		try {
			Constructor cons = type.getDeclaredConstructor();
			o = cons.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void clonePropertiesFromFather(Object dest, Object father) throws Exception {

		for (Class<?> superClass = dest.getClass().getSuperclass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			Class fatherClass = superClass;
			Field ff[] = fatherClass.getDeclaredFields();

			for (int i = 0; i < ff.length; i++) {
				Field f = ff[i];// 取出每一个属性，如deleteDate
				Class type = f.getType();
				Method m = fatherClass.getMethod("get" + upperHeadChar(f.getName()));// 方法getDeleteDate
				Object obj = m.invoke(father);// 取出属性值
				Method setMethod = fatherClass.getMethod("set" + upperHeadChar(f.getName()), type);
				setMethod.invoke(dest, obj);
			}
		}

	}

	private static String upperHeadChar(String in) {
		String head = in.substring(0, 1);
		String out = head.toUpperCase() + in.substring(1, in.length());
		return out;
	}

	public static void cloneProperties(Object dest, Object orig) throws Exception {
		for (Class<?> superClass = dest.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			Class fatherClass = superClass;
			Field ff[] = fatherClass.getDeclaredFields();

			for (int i = 0; i < ff.length; i++) {
				Field f = ff[i];// 取出每一个属性，如deleteDate
				Class type = f.getType();
				Method m;
				try {
					m = fatherClass.getMethod("get" + upperHeadChar(f.getName()));
				} catch (SecurityException e) {
					continue;
				} catch (NoSuchMethodException e) {
					continue;
				} // 方法getDeleteDate
				Object obj = m.invoke(orig);// 取出属性值
				Method setMethod = fatherClass.getMethod("set" + upperHeadChar(f.getName()), type);
				setMethod.invoke(dest, obj);
			}
		}
	}

}
