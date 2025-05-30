/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import static service.common.CommonCursoDefinicionModifyElectivoService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Administrador
 */
public class CommonCursoDefinicionModifyElectivoAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    Integer asignMod;
    String electMod;
    String electivoMod;
    Integer minorMod;
    Integer areaMod;
    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), asignMod, electMod, electivoMod, areaMod, minorMod, getKey());
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

