/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import service.common.CommonCursoDefinicionModifyCursoService;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 *
 * @author Ricardo
 */
public class CommonCursoDefinicionModifyCursoAction  extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;
    private Integer cupoId;
    private String inicioId;
    private String terminoId;
    private String diurnoId;
    private String vespertinoId;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonCursoDefinicionModifyCursoService().service(getGenericSession(), getPos(), cupoId, inicioId, terminoId, diurnoId, vespertinoId, getKey());
    }
    
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(getPos(),
                getGenericSession().getWorkSession(getKey()).getCursoList());
    }    

    public void setCupoId(Integer cupoId) {
        this.cupoId = cupoId;
    }

    public void setInicioId(String inicioId) {
        this.inicioId = inicioId;
    }

    public void setTerminoId(String terminoId) {
        this.terminoId = terminoId;
    }

    public void setDiurnoId(String diurnoId) {
        this.diurnoId = diurnoId;
    }

    public void setVespertinoId(String vespertinoId) {
        this.vespertinoId = vespertinoId;
    }
}
