package com.fnst.officeapply.framework.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorizerRequest {
	
	public String loginRedirect() default "loginPage.action";
	
	public String authorizerRedirect() default "authFailPage.action";

	public int authorizerStep() default 0;
}
