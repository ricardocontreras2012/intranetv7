/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import service.common.CommonCursoDefinicionToolService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class CommonCursoDefinicionToolAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;
    private CommonCursoDefinicionToolService svc =  new CommonCursoDefinicionToolService();
        
    @Override
    public String action() throws Exception {
        return SUCCESS;
    }

    public String getHorario() throws Exception
    {
        return svc.serviceGet(getGenericSession(), "HOR", getKey());
    }

    public String getProfesor() throws Exception
    {
        return svc.serviceGet(getGenericSession(), "PR", getKey());
    }

    public String getAyudante() throws Exception
    {
        return svc.serviceGet(getGenericSession(), "AY", getKey());
    }
}
