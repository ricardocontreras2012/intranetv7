/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.curso;

import service.curso.GetToolService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class GetToolAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;
    private final GetToolService svc =  new GetToolService();
        
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
