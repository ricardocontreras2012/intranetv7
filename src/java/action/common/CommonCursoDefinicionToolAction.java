/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import static service.common.CommonCursoDefinicionToolService.serviceGet;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class CommonCursoDefinicionToolAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;
        
    @Override
    public String action() throws Exception {
        return SUCCESS;
    }

    public String getHorario() throws Exception
    {
        return serviceGet(getGenericSession(), "HOR", getKey());
    }

    public String getProfesor() throws Exception
    {
        return serviceGet(getGenericSession(), "PR", getKey());
    }

    public String getAyudante() throws Exception
    {
        return serviceGet(getGenericSession(), "AY", getKey());
    }
}
