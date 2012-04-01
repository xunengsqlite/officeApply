package com.fnst.officeapply.framework.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.fnst.officeapply.framework.DispatcherExecuterFilter;

public interface Interceptor {

	public void intercepter(Object actionObj, Method method, Object[] args, InvocationHandler handler, DispatcherExecuterFilter dispatcher)throws Throwable;

//	public long after(Method method, Object[] args,long time);
//
//	public void afterThrowing(Method method, Object[] args,Throwable t);
//
//	public void afterFinally(Method method, Object[] args);
	
}
