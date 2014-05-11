/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.orm.Teacher;

import java.util.Map;

/**
 *
 * @author Administrator
 */
public class LoginInterceptor extends AbstractInterceptor {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public String intercept(ActionInvocation ai) throws Exception {
        ActionContext ctx = ai.getInvocationContext();
        @SuppressWarnings("rawtypes")
		Map session =  ctx.getSession();
        if (session.get("student")==null || session.get("teacher")==null ) {
            return Action.LOGIN;
        }
        return ai.invoke();
    }
    
}
