package com.fnst.officeapply.framework.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import com.fnst.officeapply.framework.DispatcherExecuterFilter;

public class RequestValInterceptor implements Interceptor {


	public void intercepter(Object actionObj, Method method, Object[] args,
			InvocationHandler handler, DispatcherExecuterFilter dispatcher)
			throws Throwable {
		//封装requestValues
//		dispatcher.setRequestVal(actionObj);
		handler.invoke(actionObj, method, args);
		
	}
}
