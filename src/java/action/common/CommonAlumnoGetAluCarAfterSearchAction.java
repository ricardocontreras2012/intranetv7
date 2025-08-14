/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import service.alumno.CommonAlumnoGetAluCarAfterSearchService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author rcontreras
 */
public class CommonAlumnoGetAluCarAfterSearchAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    private String keyParent;
    private String actionNested;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        keyParent = getGenericSession().getWorkSession(getKey()).getKeyParent();
        actionNested = getGenericSession().getWorkSession(getKey()).getActionNested();
        
        return  new CommonAlumnoGetAluCarAfterSearchService().service(getGenericSession(), getKey());
    }

    public String getKeyParent() {
        return keyParent;
    }    

    public String getActionNested() {
        return actionNested;
    }   
}
