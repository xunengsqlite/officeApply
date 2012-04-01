package com.fnst.officeapply.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import com.fnst.officeapply.framework.interceptor.Interceptor;

public class ActionInvocation implements InvocationHandler{

	private Object obj;
	
	private Object proxy;
	
	private int size;

	private Object returnVal;
	
	private List<Interceptor> interceptors;
	
	private DispatcherExecuterFilter dispatcher;

	public ActionInvocation(Object obj, DispatcherExecuterFilter dispatcher){
		interceptors = new ArrayList<Interceptor>();
		this.obj = obj;
		this.dispatcher = dispatcher;
		bindProxy();
	}
	
	private Object bindProxy(){
		Object proxy = Proxy.newProxyInstance(ActionInvocation.class.getClassLoader(), obj.getClass().getInterfaces(), this);
		this.proxy = proxy;
		return proxy;
	}
	
	public ActionInvocation addInterceptor(Interceptor interceptor){
		if(interceptor != null){
			interceptors.add(interceptor);
		}
		return this;
	}
	
	public ActionInvocation addInterceptor(List<Interceptor> interceptors){
		if(interceptors != null){
			interceptors.addAll(interceptors);
		}
		return this;
	}
	
	public ActionInvocation(Object obj, List<Interceptor> interceptors, DispatcherExecuterFilter dispatcher) {
		if(interceptors != null){
			this.interceptors = interceptors;
		}else{
			interceptors = new ArrayList<Interceptor>();
		}
		
		this.dispatcher = dispatcher;
		this.obj = obj;
		bindProxy();
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		try {
			if (size >= interceptors.size()) {
				size = 0;
				returnVal = method.invoke(obj, args);
				return returnVal;
			}
			
			interceptors.get(size++).intercepter(obj, method, args, this, dispatcher);
			
		} catch (Throwable e) {
			throw e;
		} finally {
		}

		return returnVal;
	}

	public Object getProxy() {
		return proxy;
	}

	public Object getReturnVal() {
		return returnVal;
	}

}
