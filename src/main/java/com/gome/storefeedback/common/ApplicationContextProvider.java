package com.gome.storefeedback.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware {
	private static ApplicationContext ctx;
	private static Properties props = null;

	public static ApplicationContext getApplicationContext() {
		return ctx;
	}
	
    public void setApplicationContext(ApplicationContext ctx) { 
    	ApplicationContextProvider.ctx = ctx; 
    } 
    
	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		checkApplicationContext();
		return (T) ctx.getBean(name);
	}

	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 * 如果有多个Bean符合Class, 取出第一个.
	 */
	public static <T> T getBean(Class<T> requiredType) {
		checkApplicationContext();
		return ctx.getBean(requiredType);
	}

	public static String getProperty(String name) {
		if (props == null) {
			props = new Properties();

			checkApplicationContext();
			InputStream is;
			Properties _props = new Properties();
			try {
				is = ApplicationContextProvider.class.getResourceAsStream("/application.properties");
				_props.load(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			props.putAll(_props);
		}
		
		return props.getProperty(name); 
	}

	/**
	 * 清除applicationContext静态变量.
	 */
	public static void cleanApplicationContext() {
		ctx = null;
	}

	private static void checkApplicationContext() {
		if (ctx == null) {
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义ApplicationContextProvider");
		}
	}
}