/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import service.common.CommonPracticaSaveResolucionPracticaService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author rcontreras
 */
public class CommonPracticaSaveResolucionPracticaAction  extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    private String estado;
    private String respuesta;
    private Integer agno;
    private Integer sem;

    @Override
    public String action() throws Exception {

        return new CommonPracticaSaveResolucionPracticaService().service(getGenericSession(), getKey(), estado, respuesta, agno, sem);
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Integer getAgno() {
        return agno;
    }

    public void setAgno(Integer agno) {
        this.agno = agno;
    }

    public Integer getSem() {
        return sem;
    }

    public void setSem(Integer sem) {
        this.sem = sem;
    }
}
