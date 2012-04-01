package com.fnst.officeapply.framework;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fnst.officeapply.common.AdminConfig;
import com.fnst.officeapply.common.ComUtil;
import com.fnst.officeapply.common.ServletUtil;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.dbpool.DBPoolManager;
import com.fnst.officeapply.entity.UserInfo;
import com.fnst.officeapply.exception.OfficeException;
import com.fnst.officeapply.framework.annotation.AuthorizerRequest;
import com.fnst.officeapply.framework.interceptor.Interceptor;

public class DispatcherExecuterFilter extends ActionSupport implements Filter{
	
	private static final long serialVersionUID = 1L;
	
	private static String contextPath = null;
	
	public static final String encoding = dispatcher.getValue(CONSTANT_ENCODING);
	
	public static AdminConfig adminConfig;
	
    public DispatcherExecuterFilter() throws OfficeException {
    	adminConfig = AdminConfig.getAdminConfig();
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		//request.setCharacterEncoding(encoding);
		ServletContext application = request.getSession(true).getServletContext();
		String requestName = request.getRequestURI();
		try {
			if (requestName.endsWith(dispatcher.getValue(CONSTANT_ACTION_EXTENSION))) {
				//develop mode
				if (Boolean.parseBoolean(dispatcher.getValue(CONSTANT_DEVMODE))) {
					logger.debug("devMode = true");
					dispatcher.parsePropFile(this,contextPath,application);
				}
				
				ServletUtil.fillLocalPool(request, response);
				requestName = requestName.replaceAll("/+", "/");
				logger.debug(requestName);
				//菜单ID设置
				this.putListID(request);
				DispatcherHanlder handler = getRequestHandler(request, requestName);
				if (handler.canProcess(request)) {
					String loginRedirect = handler.getLoginRedirect();
					int authorizerStep = handler.getAuthorizerStep();
					//1.请求页面是否需要登录.2.是否已经登录
					if (loginRedirect != null && !isAuthorized(request)){
						//返回登录页面
						String returnVal = gotoAuthorisePage(loginRedirect, request);
						request.getRequestDispatcher(returnVal).forward(request, response);
						return;
					}else if(loginRedirect != null && isAuthorized(request)){
						UserInfo user = getUserFSession(request);
						String authorizerRedirect = handler.getAuthorizerRedirect();
						int permission = (user == null ? 0 : user.getPermission());
						//是否有足够的权限
						if(authorizerRedirect != null && authorizerStep > permission){
							String returnVal = gotoAccessFailPage(authorizerRedirect, request);
							request.getRequestDispatcher(returnVal).forward(request, response);
							return;
						}
					}
					
					String returnVal = handler.invokeHandler(request, response);
					if(returnVal == null){
						return;
					}
					request.getRequestDispatcher(returnVal).forward(request, response);
				} else {
					throw new RequestMethodNotAllowException(request, requestName);
				}
			} else {
				logger.debug("跳转...");
				chain.doFilter(request,response);
			}
		} catch (Exception e) {
			String errMsg = ComUtil.getThrowableStack(e);
			logger.info(errMsg);
			String redirectPage = gotoErrorPage(request, e);
			request.getRequestDispatcher(redirectPage).forward(request, response);
		} finally {
			ServletUtil.clearLocalPool();
		}

	}

	public void init(FilterConfig config) throws ServletException {
		
		try {
			ServletContext application = config.getServletContext();
			contextPath = application.getContextPath();
			
			initParamTypes(application);
			dispatcher.parsePropFile(this,contextPath,application);
		} catch (InterceptorParseException e) {
			logger.error("Interceptor fail to parse:",e);
			throw new ServletException(e);
		}
	}
    
	private void initParamTypes(ServletContext application){
		
		Map<String, List<String>> paramTypes = new HashMap<String, List<String>>();
		
		List<String> bytes = ComUtil.setParamTypes("byte", "[B", "java.lang.Byte", "[Ljava.lang.Byte;");
		
		List<String> chars = ComUtil.setParamTypes("char", "[C", "java.lang.Character", "[Ljava.lang.Character;");
		
		List<String> ints = ComUtil.setParamTypes("int", "[I", "java.lang.Integer", "[Ljava.lang.Integer;");
		
		List<String> longs = ComUtil.setParamTypes("long", "[J", "java.lang.Long", "[Ljava.lang.Long;");
		
		List<String> floats = ComUtil.setParamTypes("float", "[F", "java.lang.Float", "[Ljava.lang.Float;");
		
		List<String> doubles = ComUtil.setParamTypes("double", "[D", "java.lang.Double", "[Ljava.lang.Double;");
		
		List<String> booleans = ComUtil.setParamTypes("boolean", "[Z", "java.lang.Boolean", "[Ljava.lang.Boolean;");
		
		List<String> shorts = ComUtil.setParamTypes("short", "[S", "java.lang.Short", "[Ljava.lang.Short;");
		
		paramTypes.put("Byte", bytes);
		paramTypes.put("Character", chars);
		paramTypes.put("Long", longs);
		paramTypes.put("Integer", ints);
		paramTypes.put("Float", floats);
		paramTypes.put("Double", doubles);
		paramTypes.put("Boolean", booleans);
		paramTypes.put("Short", shorts);
		
		application.setAttribute(PARAM_TYPES_MAP, paramTypes);
	}
	
	@SuppressWarnings("unchecked")
	protected String getParamClassType(HttpServletRequest request, String type){
		ServletContext application = request.getSession(true).getServletContext();
		Map<String, List<String>> paramTypes = (Map<String, List<String>>)application.getAttribute(PARAM_TYPES_MAP);
		if(paramTypes == null){
			throw new RuntimeException("NO SUCH TYPE:" + type);
		}
		Set<String> keys = paramTypes.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()){
			String key = it.next();
			List<String> paramlists = paramTypes.get(key);
			boolean f = paramlists.contains(type);
			if(f){
				return key;
			}
		}
		return null;
	}
	
    @SuppressWarnings("unchecked")
	protected DispatcherHanlder getRequestHandler(HttpServletRequest request, String requestName) throws NoRequestFoundException{
    	ServletContext application = request.getSession(true).getServletContext();
		Hashtable<String, DispatcherHanlder> table = (Hashtable<String, DispatcherHanlder>)application.getAttribute(REQUEST_TABLE);
		if(table == null){
			throw new NoRequestFoundException(requestName);
		}
		DispatcherHanlder handler = table.get(requestName);
		if (handler == null){
			throw new NoRequestFoundException(requestName);
		}
		
		return handler;
	}
    
	protected Interceptor getInterceptor(String className) throws InterceptorParseException {
    	Interceptor interceptor = null;
		try {
			interceptor = (Interceptor)Class.forName(className).newInstance();
			return interceptor;
		} catch (InstantiationException e) {
			throw new InterceptorParseException(className,e);
		} catch (IllegalAccessException e) {
			throw new InterceptorParseException(className,e);
		} catch (ClassNotFoundException e) {
			throw new InterceptorParseException(className,e);
		}
    }
    
    @SuppressWarnings("unchecked")
	protected List<Interceptor>  getInterceptors(HttpServletRequest request){
    	ServletContext application = request.getSession(true).getServletContext();
    	List<Interceptor> interceptors = (List<Interceptor>)application.getAttribute(INTERCEPTOR_LIST);
    	return interceptors;
    }
    
    protected Object getActionProxy(HttpServletRequest request,  Object actionObject) throws InstantiationException, IllegalAccessException, ClassNotFoundException, InterceptorParseException{
    	
    	List<Interceptor>  interceptors = getInterceptors(request);
    	ActionInvocation handler = new ActionInvocation(actionObject, interceptors, this);
    	return handler.getProxy();
    } 
    
    @SuppressWarnings("unchecked")
	protected Map<String, Method> getMethods(HttpServletRequest request, String className) throws ClassNotFoundException{
    	ServletContext application = request.getSession(true).getServletContext();
    	Hashtable<String, Map<String, Method>> table = (Hashtable<String, Map<String, Method>>)application.getAttribute(METHOD_TABLE);
    	if(table == null){
			table = new Hashtable<String, Map<String, Method>>();
			application.setAttribute(METHOD_TABLE, table);
    	}
    	Map<String, Method> methods = table.get(className);
    	if(methods == null){
    		methods = getMethods(className);
    		table.put(className, methods);
    	}
    	return methods;
    }
    
    private Map<String, Method> getMethods(String className) throws ClassNotFoundException{
    	Class<?> actionClass = Class.forName(className);
    	Method[] methods = actionClass.getMethods();
		Map<String, Method> meths = new HashMap<String, Method>();
		for(Method m: methods){
			String name = m.getName().toLowerCase();
			meths.put(name, m);
		}
		logger.debug(className + "->" + meths);
		return meths;
    }
    
    @SuppressWarnings("unchecked")
	public void setRequestVal(Object actionObject) 
    throws ClassNotFoundException, OfficeException{
    	
    	HttpServletRequest request = ServletUtil.getHttpServletRequest();
		HttpServletResponse response = ServletUtil.getHttpServletResponse();
		//String reqWay = request.getMethod();
    	Class<?> actionClass =actionObject.getClass();
    	String className = actionClass.getName();
    	//get action's methods
    	Map<String, Method> methods = getMethods(request, className);
		Map<String,String[]> reqmap = request.getParameterMap();
		for (String key : reqmap.keySet()) {
			String[] values = reqmap.get(key);
			if(values == null || values.length == 0){
				continue;
			}
			String methodName = (ACTION_SET_METHOD_PREFIX + key).toLowerCase();
			Method m = methods.get(methodName);
			if(m == null){
				continue;
			}
			
			Class<?>[] paramTypes = m.getParameterTypes();
			if(paramTypes != null && paramTypes.length == 1){
				//encoding values.
				String[] encodingVals = ComUtil.encodingVals(values);
				String paramTypeName = paramTypes[0].getName();
				
				logger.debug(paramTypeName);
				String paramTypeClss = getParamClassType(request,paramTypeName);
				
				Object[] args = getMethodParam(paramTypeClss, paramTypeName, encodingVals);
				try {
					m.invoke(actionObject, args);
				} catch (IllegalArgumentException e) {
					throw new IllegalArgumentException("parameters error: ", e);
				} catch (IllegalAccessException e) {
					throw new IllegalArgumentException("parameters error: ", e);
				} catch (InvocationTargetException e) {
					throw new IllegalArgumentException("parameters error: ", e);
				}
			}
		}
		response.setHeader("Cache-Control", "no-cache");
	}
    
    private Object[] getMethodParam(String paramTypeClss, String paramTypeName, String[] encodingVals){
		Object[] args;
    	int valSize = encodingVals.length;
    	if("Byte".equalsIgnoreCase(paramTypeClss)){
			byte[] vs = new byte[valSize];
			for(int i=0 ; i< valSize ; i++){
				vs[i] = Byte.parseByte(encodingVals[i]);
			}
			if(paramTypeName.startsWith("[")){
				args = new Object[] { vs } ;
			}else{
				args = (valSize != 1 )? new Object[] { vs } : new Object[] { vs[0] };
			}
		}else if("Character".equalsIgnoreCase(paramTypeClss)){
			char[] vs = new char[valSize];
			for(int i=0 ; i< valSize ; i++){
				vs[i] =(char)(Integer.parseInt(encodingVals[i]));
			}
			if(paramTypeName.startsWith("[")){
				args = new Object[] { vs } ;
			}else{
				args = (valSize != 1 )? new Object[] { vs } : new Object[] { vs[0] };
			}
		}else if("Short".equalsIgnoreCase(paramTypeClss)){
			short[] vs = new short[valSize];
			for(int i=0 ; i< valSize ; i++){
				vs[i] =Short.parseShort(encodingVals[i]);
			}
			if(paramTypeName.startsWith("[")){
				args = new Object[] { vs } ;
			}else{
				args = (valSize != 1 )? new Object[] { vs } : new Object[] { vs[0] };
			}
		}else if("Long".equalsIgnoreCase(paramTypeClss)){
			long [] vs = new long[valSize];
			for(int i=0 ; i< valSize ; i++){
				vs[i] = Long.parseLong(encodingVals[i]);
			}
			if(paramTypeName.startsWith("[")){
				args = new Object[] { vs } ;
			}else{
				args = (valSize != 1 )? new Object[] { vs } : new Object[] { vs[0] };
			}
		}else if("Integer".equalsIgnoreCase(paramTypeClss)){
			int [] vs = new int[valSize];
			for(int i=0 ; i< valSize ; i++){
				vs[i] = Integer.parseInt(encodingVals[i]);
			}
			if(paramTypeName.startsWith("[")){
				args = new Object[] { vs } ;
			}else{
				args = (valSize != 1 )? new Object[] { vs } : new Object[] { vs[0] };
			}
		}else if("Float".equalsIgnoreCase(paramTypeClss)){
			float [] vs = new float[valSize];
			for(int i=0 ; i< valSize ; i++){
				vs[i] = Float.parseFloat(encodingVals[i]);
			}
			if(paramTypeName.startsWith("[")){
				args = new Object[] { vs } ;
			}else{
				args = (valSize != 1 )? new Object[] { vs } : new Object[] { vs[0] };
			}
		}else if("Double".equalsIgnoreCase(paramTypeClss)){
			double [] vs = new double[valSize];
			for(int i=0 ; i< valSize ; i++){
				vs[i] = Double.parseDouble(encodingVals[i]);
			}
			if(paramTypeName.startsWith("[")){
				args = new Object[] { vs } ;
			}else{
				args = (valSize != 1 )? new Object[] { vs } : new Object[] { vs[0] };
			}
		}else if("Boolean".equalsIgnoreCase(paramTypeClss)){
			boolean [] vs = new boolean[valSize];
			for(int i=0 ; i< valSize ; i++){
				vs[i] = Boolean.parseBoolean(encodingVals[i]);
			}
			if(paramTypeName.startsWith("[")){
				args = new Object[] { vs } ;
			}else{
				args = (valSize != 1 )? new Object[] { vs } : new Object[] { vs[0] };
			}
		}else{
			if(paramTypeName.startsWith("[")){
				args = new Object[] { encodingVals } ;
			}else{
				args = (valSize != 1 )? new Object[] { encodingVals } : new Object[] { encodingVals[0] };
			}
		}
    	
    	return args;
    }
    
    protected void putListID(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String listID = request.getParameter("listID");
		if(listID != null){
			session.setAttribute("listID", listID);
		}
	}
    
	public void destroy() {
		try {
			DBPoolManager.getInstance().release();
		} catch (DBPoolException e) {
			logger.error("释放连接池失败!",e);
		}		
	}
	
	protected class DispatcherHanlder {
		
		private String requestName;
		private String className;
		private String methodName;
		private List<String> allowMethod;
		private String LoginRedirect;
		private boolean isLoginRequired = true;
		private String AuthorizerRedirect;
		private boolean isAuthorizerRequired = true;
		private int AuthorizerStep = -1;
		private boolean isStepExisted = true;
		
		
		public DispatcherHanlder(String requestName,String[] handles) {
			if (handles == null) {
				throw new IllegalArgumentException("handles is null");
			}
			this.requestName = requestName;
			if (handles.length >= 2) {
				className = handles[0];
				methodName = handles[1];
			}
			if (handles.length >= 3) {
				String[] methods = handles[2].split("/");
				allowMethod = Arrays.asList(methods);
			}
		}
		
		public String invokeHandler(HttpServletRequest request, HttpServletResponse response) 
		throws NoRequestFoundException, RequestMethodException, InterceptorParseException,
		ActionMethodException, OfficeException{
			try {
				Object actionObject = Class.forName(className).newInstance();
				setRequestVal(actionObject);
				//动态代理
//				Object actionProxy = getActionProxy(request, actionObject);
//				Method method = actionProxy.getClass().getMethod(methodName);
//				Object returnVal = method.invoke(actionProxy);
				
				Method method = actionObject.getClass().getMethod(methodName, HttpServletRequest.class);
				Object returnVal = method.invoke(actionObject, request);
				logger.debug("returnVal:" + returnVal);
//				if (returnVal == null) {
//					throw new ActionMethodException(className, methodName);
//				}
				return (String)returnVal;
				
			} catch (InstantiationException e) {
				throw new NoRequestFoundException(requestName, e);
			} catch (IllegalAccessException e) {
				throw new NoRequestFoundException(requestName, e);
			} catch (ClassNotFoundException e) {
				throw new NoRequestFoundException(requestName, e);
			} catch (SecurityException e) {
				throw new NoRequestFoundException(requestName, e);
			} catch (NoSuchMethodException e) {
				throw new NoRequestFoundException(requestName, e);
			} catch (IllegalArgumentException e) {
				throw new RequestMethodException(requestName, e);
			} catch (InvocationTargetException e) {
				throw new RequestMethodException(requestName, e);
			}
		}
		
		
		public synchronized String getAuthorizerRedirect() throws NoRequestFoundException, RequestMethodException{
			
			try {
				if (isAuthorizerRequired && AuthorizerRedirect == null){
					Class<?> serviceClass = Class.forName(className);
					AuthorizerRequest loginAnno = (AuthorizerRequest)serviceClass.getAnnotation(AuthorizerRequest.class);
					if (loginAnno != null){
						AuthorizerRedirect = loginAnno.authorizerRedirect();
					}
					Method method = serviceClass.getMethod(methodName, HttpServletRequest.class);
					loginAnno = (AuthorizerRequest)method.getAnnotation(AuthorizerRequest.class);
					if (loginAnno != null){
						AuthorizerRedirect = loginAnno.authorizerRedirect();
					}
					if (AuthorizerRedirect == null){
						isAuthorizerRequired = false;
					}
				}
				return AuthorizerRedirect;
				
			} catch (ClassNotFoundException e) {
				throw new NoRequestFoundException(requestName, e);
			} catch (SecurityException e) {
				throw new NoRequestFoundException(requestName, e);
			} catch (NoSuchMethodException e) {
				throw new NoRequestFoundException(requestName, e);
			} catch (IllegalArgumentException e) {
				throw new RequestMethodException(requestName, e);
			}
		}
		
		public synchronized String getLoginRedirect() throws NoRequestFoundException, RequestMethodException{
			try {
				if (isLoginRequired && LoginRedirect == null){
					Class<?> serviceClass = Class.forName(className);
					AuthorizerRequest loginAnno = (AuthorizerRequest)serviceClass.getAnnotation(AuthorizerRequest.class);
					if (loginAnno != null){
						LoginRedirect = loginAnno.loginRedirect();
					}
					Method method = serviceClass.getMethod(methodName, HttpServletRequest.class);
					loginAnno = (AuthorizerRequest)method.getAnnotation(AuthorizerRequest.class);
					if (loginAnno != null){
						LoginRedirect = loginAnno.loginRedirect();
					}
					if (LoginRedirect == null){
						isLoginRequired = false;
					}
				}
				return LoginRedirect;
				
			} catch (ClassNotFoundException e) {
				throw new NoRequestFoundException(requestName, e);
			} catch (SecurityException e) {
				throw new NoRequestFoundException(requestName, e);
			} catch (NoSuchMethodException e) {
				throw new NoRequestFoundException(requestName, e);
			} catch (IllegalArgumentException e) {
				throw new RequestMethodException(requestName, e);
			}
		}
		
		public synchronized int getAuthorizerStep() throws NoRequestFoundException, RequestMethodException{
			try {
				if (isStepExisted && AuthorizerStep == -1){
					Class<?> serviceClass = Class.forName(className);
					AuthorizerRequest loginAnno = (AuthorizerRequest)serviceClass.getAnnotation(AuthorizerRequest.class);
					if (loginAnno != null){
						AuthorizerStep = loginAnno.authorizerStep();
					}
					Method method = serviceClass.getMethod(methodName, HttpServletRequest.class);
					loginAnno = (AuthorizerRequest)method.getAnnotation(AuthorizerRequest.class);
					if (loginAnno != null){
						AuthorizerStep = loginAnno.authorizerStep();
					}
					if (AuthorizerStep == -1){
						isStepExisted = false;
					}
				}
				return AuthorizerStep;
				
			} catch (ClassNotFoundException e) {
				throw new NoRequestFoundException(requestName, e);
			} catch (SecurityException e) {
				throw new NoRequestFoundException(requestName, e);
			} catch (NoSuchMethodException e) {
				throw new NoRequestFoundException(requestName, e);
			} catch (IllegalArgumentException e) {
				throw new RequestMethodException(requestName, e);
			}
		}
		
		public boolean canProcess(HttpServletRequest request){
			if (allowMethod == null || allowMethod.size() == 0){
				return true;
			}
			return allowMethod.contains(request.getMethod());
		}
		
		public String toString(){
			return "" + requestName + " => " + className + "::" + methodName;
		}
		
	}
	
//	class ParamType{
//		
//		private String type1;
//		private String type2;
//		private String type3;
//		private String type4;
//		
//		public ParamType(String type1, String type2, String type3, String type4) {
//			this.type1 = type1;
//			this.type2 = type2;
//			this.type3 = type3;
//			this.type4 = type4;
//		}
//		
//	}
	
	
	@SuppressWarnings("serial")
	class ActionMethodException extends Exception{
		public ActionMethodException(String actionClassName, String methodName) {
			super(actionClassName + " 中的方法:" + methodName);
		}
		public ActionMethodException(String message, Throwable cause){
			super(message, cause);
		}
	}
    	
	
	@SuppressWarnings("serial")
	class NoRequestFoundException extends Exception{
		public NoRequestFoundException(String requestName) {
			super("bad request name : " + requestName);
		}
		public NoRequestFoundException(String requestName,Throwable cause) {
			super("bad request name : " + requestName,cause);
		}
	}
	
	@SuppressWarnings("serial")
	class RequestMethodException extends Exception{
		public RequestMethodException(String requestName,Throwable cause) {
			super("an exception occur in : " + requestName,cause);
		}
	}
	
	@SuppressWarnings("serial")
	class InterceptorParseException extends Exception{
		public InterceptorParseException(String message, Throwable cause) {
			super("Interceptor fail to parse:" + message,cause);
		}
	}
	
	@SuppressWarnings("serial")
	class RequestMethodNotAllowException extends Exception{
		public RequestMethodNotAllowException(HttpServletRequest request, String requestName) {
			super("requestName: '"+requestName+"' is not allow for the Method(GET/POST): " + request.getMethod());
		}
	}
	
}
