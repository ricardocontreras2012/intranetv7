/*
 * @(#)ActionPostCommonSupport.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.support.action.post;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.action.ServletRequestAware;
import infrastructure.support.action.common.ActionCommonSupport;
import static infrastructure.util.AppStaticsUtil.ACTION_DENIED;
import static infrastructure.util.AppStaticsUtil.ACTION_EXCEPTION;

/**
 *
 * @author Ricardo Contreras S.
 */
public class ActionPostCommonSupport extends ActionCommonSupport implements ServletRequestAware {
    private HttpServletRequest request;

    /**
     * Method description
     *
     *
     * @return
     */
    @Override
    public String execute() {
        String retValue;
        try {
            if ("post".equalsIgnoreCase(request.getMethod())) { 
                retValue = this.action();
            } else {    
                retValue = ACTION_DENIED;
            }
        } catch (Exception e) {
            retValue = ACTION_EXCEPTION;
        }

        return retValue;
    }

    @Override
    public void withServletRequest(HttpServletRequest hsr) {
        this.request = hsr;       
    }        
}
