package com.fnst.officeapply.framework.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fnst.officeapply.common.ComUtil;
import com.fnst.officeapply.common.ServletUtil;
import com.fnst.officeapply.framework.DispatcherExecuterFilter;
import com.fnst.officeapply.framework.FrameworkConstant;

public class ExceptionInterceptor implements Interceptor {

	Logger logger = DispatcherExecuterFilter.logger;
	
	public void intercepter(Object actionObj, Method method, Object[] args,
			InvocationHandler handler, DispatcherExecuterFilter dispatcher)
			throws Throwable {
		
		try {
			Object returnVal = handler.invoke(actionObj, method, args);
			if(returnVal == null){
				throw new ServletException("Action中方法返回为null");
			}
		} catch (Throwable e) {
			HttpServletRequest request = ServletUtil.getHttpServletRequest();
			HttpServletResponse response = ServletUtil.getHttpServletResponse();
			String errMsg = ComUtil.getThrowableStack(e);
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher(DispatcherExecuterFilter.dispatcher.getValue(FrameworkConstant.ERROR_PAGE_ACTION_KEY)).forward(request, response);
			logger.debug(errMsg);
		}
		
	}

}
