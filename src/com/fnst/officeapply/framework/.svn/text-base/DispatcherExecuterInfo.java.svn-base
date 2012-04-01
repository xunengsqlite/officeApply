package com.fnst.officeapply.framework;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.ServletContext;

import com.fnst.officeapply.common.ComUtil;
import com.fnst.officeapply.exception.OfficeException;
import com.fnst.officeapply.framework.DispatcherExecuterFilter.DispatcherHanlder;
import com.fnst.officeapply.framework.DispatcherExecuterFilter.InterceptorParseException;
import com.fnst.officeapply.framework.interceptor.Interceptor;

public class DispatcherExecuterInfo implements FrameworkConstant{

	private static ResourceBundle executer;

	private static DispatcherExecuterInfo instance;
	
	/**
	 *parse properties file
	 * @param application
	 * @throws InterceptorParseException
	 */
	public synchronized void parsePropFile(DispatcherExecuterFilter dispatcher, String contextPath, ServletContext application) throws InterceptorParseException{
		//when devMode,it is need to parse the file everytime when request is received.
		executer = ResourceBundle.getBundle(DISPATCHER_EXECUTER);
		Hashtable<String, DispatcherHanlder> request_table = new Hashtable<String, DispatcherHanlder>();
		List<Interceptor> interceptors = new ArrayList<Interceptor>();
		
		Enumeration<String> keys = executer.getKeys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String value = executer.getString(key);
			if (key.startsWith(INTERCEPTOR_KEY_PREFIX)) {
				Interceptor interceptor = dispatcher.getInterceptor(value);
				interceptors.add(interceptor);
			}else {
				String[] values = value.split(",");
				if (values.length > 1) {
					request_table.put(contextPath + key, dispatcher.new DispatcherHanlder(key, values));
				}
			}
		}
		
		application.setAttribute(REQUEST_TABLE, request_table);
		application.setAttribute(INTERCEPTOR_LIST, interceptors);
	}
	
	private DispatcherExecuterInfo(){}
	
	
	public synchronized static DispatcherExecuterInfo newInstance(){
		
		if(instance == null){
			instance = new DispatcherExecuterInfo();
		}
		return instance;
	}
	
	public synchronized String getValue(String key){
		String value = null;
		if(executer == null){
			executer = ResourceBundle.getBundle(DISPATCHER_EXECUTER);
		}
		try{
			if (!ComUtil.isBlank(key)) {
				value = executer.getString(key);
			}
		}catch(Exception e){
			String message = "no such constant : " + key;
			logger.error(message,e);
			throw new RuntimeException(message);
		}
		return value;
	}
	
}

@SuppressWarnings("serial")
class NoConstantFoundException extends OfficeException{
	public NoConstantFoundException(String constantKey) {
		super("no such constant : " + constantKey);
	}
	public NoConstantFoundException(String constantKey,Throwable cause) {
		super("no such constant : " + constantKey,cause);
	}
}
