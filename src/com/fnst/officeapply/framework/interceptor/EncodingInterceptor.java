package com.fnst.officeapply.framework.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.fnst.officeapply.framework.DispatcherExecuterFilter;
import com.fnst.officeapply.framework.FrameworkConstant;

public class EncodingInterceptor implements Interceptor {
	
	public static final String encoding = DispatcherExecuterFilter.dispatcher.getValue(FrameworkConstant.CONSTANT_ENCODING);
	
	public void intercepter(Object actionObj, Method method, Object[] args,
			InvocationHandler handler, DispatcherExecuterFilter dispatcher)
			throws Throwable {
//		DispatcherExecuterFilter.logger.debug("set the encoding:" + encoding);
		
//		ServletUtil.getHttpServletRequest().setCharacterEncoding(encoding);
		handler.invoke(actionObj, method, args);
//		ServletUtil.getHttpServletRequest().setCharacterEncoding(encoding);
	}

}
