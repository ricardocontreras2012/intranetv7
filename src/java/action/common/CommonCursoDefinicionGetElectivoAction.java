/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import service.curso.CommonCursoDefinicionGetElectivoService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class CommonCursoDefinicionGetElectivoAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    private Integer asign;
    private String elect;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonCursoDefinicionGetElectivoService().service(getGenericSession(), asign, elect, getKey());
    }

    public void setAsign(Integer asign) {
        this.asign = asign;
    }

    public void setElect(String elect) {
        this.elect = elect;
    }
}
