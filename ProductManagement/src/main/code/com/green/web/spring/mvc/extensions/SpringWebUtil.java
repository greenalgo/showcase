package com.green.web.spring.mvc.extensions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: gaurav
 * Date: 20/08/11
 * Time: 7:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class SpringWebUtil {

    private HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}

    private RequestContext getRequestContext(){
        return new RequestContext(getRequest());
    }

    public String getMessageFromResourceBundle(String key){
        return getRequestContext().getMessage(key);
    }

    public void setMessageInCurrentRequest(String message){
        getRequest().setAttribute("message",message);
    }

    public void storeInSession(String key,String value){
         storeInSession(key,(Object)value);
    }

    public void storeInSession(String key,Object value){
        getRequest().getSession().setAttribute(key,value);
    }

    public String getStringFromSession(String key){
          return (String)getRequest().getSession().getAttribute(key);
    }

    public Object getObjectFromSession(String key){
         return getRequest().getSession().getAttribute(key);
    }




}
