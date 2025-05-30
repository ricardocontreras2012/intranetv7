/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.support.action.common;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.action.SessionAware;
import session.GenericSession;
import static infrastructure.util.AppStaticsUtil.ACTION_EXCEPTION;

/**
 *
 * @author Ricardo
 */
public class ActionCommonSupportDirect extends ActionSupport implements SessionAware {

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

        try {
            retValue = this.action();

        } catch (Exception e) {
            //log.error(FormatUtil.msgLog("Exception " + e.getMessage() + ' ' + Arrays.toString(e.getStackTrace())));
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
     * @param genericSession
     */
    protected void setGenericSession(GenericSession genericSession) {
        sesion.put("genericSession", genericSession);
    }

    /**
     * Method description
     *
     * @return
     */
    public String getKey() {
        return key;
    }

    /**
     * Method description
     *
     * @param key
     */
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

    /**
     * Method description
     *
     *
     * @return
     */
    protected Map<String, Object> getSession() {
        return sesion;
    }
}
