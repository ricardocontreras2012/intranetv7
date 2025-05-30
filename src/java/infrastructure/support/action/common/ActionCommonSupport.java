/*
 * @(#)ActionCommonSupport.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package infrastructure.support.action.common;

import static com.opensymphony.xwork2.ActionContext.getContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.struts2.action.SessionAware;
import session.GenericSession;
//import static util.ActionUtil.isHelp;
import static infrastructure.util.AppStaticsUtil.ACTION_EXCEPTION;

/**
 * Clase de la cual se extienden los actions de la aplicacion.
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class ActionCommonSupport extends ActionSupport implements SessionAware {

    private static final long serialVersionUID = 1L;
    private String key;

    /**
     *
     */
    protected Map<String, Object> sesion;

    /**
     * Ejecuta el action que ha sido invocado desde el cliente WEB, invocando su
     * correspondiente service.
     *
     * @return Action status.
     */
    @Override
    public String execute() {
        String retValue;
        String currentAction;

        try {
            retValue = this.action();
            currentAction = getContext().getActionName();           

            //if (key != null && getGenericSession() != null && isHelp(currentAction)) {
            if (key != null && getGenericSession() != null) {
                getGenericSession().setCurrentAction(currentAction);                
            }
        } catch (Exception e) {
            System.out.println("getGenericSession().rut -->"+(getGenericSession()==null?"":getGenericSession().getRut()));            

            System.out.println(Arrays.stream(e.getStackTrace()).map(s->s.toString()).collect(Collectors.joining("\n"))); 
            retValue = ACTION_EXCEPTION;
        }
  
        return retValue;
    }

    /**
     * Invoca el servicio correspondiente.
     *
     * @return
     * @throws java.lang.Exception
     */
    protected String action() throws Exception {
        return "success";
    }

    /**
     * Method description
     *
     * @return
     */
    protected GenericSession getGenericSession() {
        return (GenericSession) sesion.get("genericSession");
    }
    
    /**
     * Method description
     *
     * @return
     */
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }    

    /**
     * Method description
     *
     * @return
     */
    public Map<String, Object> getSesion() {
        return this.sesion;
    }

    @Override
    public void withSession(Map<String, Object> map) {
         sesion = map;       
    }
}
