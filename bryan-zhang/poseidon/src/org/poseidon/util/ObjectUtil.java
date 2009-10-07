package org.poseidon.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;

import org.apache.log4j.Logger;

/**
 * ObjectUtil
 * 
 * @author 何晓滨
 * @version 创建时间：Sep 1, 2008 3:00:29 PM
 */
public abstract class ObjectUtil {

	private static final Logger LOGGER = Logger.getLogger(ObjectUtil.class);
	static{
		LoggerUtil.initLogging(LOGGER);
	}
	
    /**
     * 得到格式化的字符串值
     * @param obj
     * @return 格式化字符串
     */
    public static String getDisplayString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /**
     * 得到格式化的对象数据域信息
     * @param obj
     * @return 格式化字符串
     */
    public static String toString(Object obj) {
        StringBuilder sb = new StringBuilder(128);
        Class<?> clazz = (obj == null ? null : obj.getClass());
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                String name = fields[i].getName();
                if ("serialVersionUID".equals(name)) { // ignore
                    continue;
                }
                fields[i].setAccessible(true);
                Object value = null;
                try {
                    value = fields[i].get(obj);
                } catch (Exception e) { // ignore
                }
                sb.append('[').append(name).append('=').append(getDisplayString(value)).append(']');
            }
            clazz = clazz.getSuperclass();
        }
        return sb.toString();
    }

    /**
     * 序列化
     * @param os
     *            输出流
     * @param object
     *            对象
     * @throws IOException
     */
    public static void writeObject(OutputStream os, Object object) throws IOException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(os);
            oos.writeObject(object);
            oos.flush();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                	LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 反序列化
     * @param is
     *            输入流
     * @return 对象
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object readObject(InputStream is) throws IOException, ClassNotFoundException {
        Object object = null;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(is);
            object = ois.readObject();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                	LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return object;
    }
    
    /**
     * 当前线程休眠一段时间
     * @param millis
     *            休眠的时间，单位毫秒
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        	LOGGER.error(e.getMessage(), e);
        }
    }

}
