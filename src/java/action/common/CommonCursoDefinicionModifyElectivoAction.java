/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import service.curso.CommonCursoDefinicionModifyElectivoService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Administrador
 */
public class CommonCursoDefinicionModifyElectivoAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer asignMod;
    private String electMod;
    private String electivoMod;
    private Integer minorMod;
    private Integer areaMod;
    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonCursoDefinicionModifyElectivoService().service(getGenericSession(), asignMod, electMod, electivoMod, areaMod, minorMod, getKey());
    }

    public void setAsignMod(Integer asignMod) {
        this.asignMod = asignMod;
    }

    public void setElectMod(String electMod) {
        this.electMod = electMod;
    }

    public void setElectivoMod(String electivoMod) {
        this.electivoMod = electivoMod;
    }

    public void setAreaMod(Integer areaMod) {
        this.areaMod = areaMod;
    }    

    public void setMinorMod(Integer minorMod) {
        this.minorMod = minorMod;
    }
}

