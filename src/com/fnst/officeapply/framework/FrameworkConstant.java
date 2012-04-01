package com.fnst.officeapply.framework;

import org.apache.log4j.Logger;

public interface FrameworkConstant {
	
	String FRAMEWORK_LOG4J_LOGGER = "framelog";

	Logger logger = Logger.getLogger(FRAMEWORK_LOG4J_LOGGER);

	String DISPATCHER_EXECUTER = "DispatcherExecuter";

	String INTERCEPTOR_KEY_PREFIX = "interceptor.";

	String ERROR_PAGE_ACTION_KEY = "error.page.action";

	String REDIRECT_PAGE_KEY = "redirect.page";

	String REQUEST_TABLE = "request_table";

	String INTERCEPTOR_LIST = "interceptor_list";

	String CONSTANT_DEVMODE = "constant.devMode";

	String CONSTANT_ACTION_EXTENSION = "constant.action.extension";

	String CONSTANT_ENCODING = "constant.encoding";

	String DEFAULT_ACTION = "default.action";

	String ACTION_SET_METHOD_PREFIX = "set";

	String METHOD_TABLE = "method_table";

	String PARAM_TYPES_MAP = "paramTypes_map";
}
