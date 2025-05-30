/*
 * @(#)TeletrabajoAsignarGetHorarioAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.teletrabajo;

import static service.teletrabajo.TeleTrabajoAsignarGetHorarioService.service;
import session.Manager;
import infrastructure.support.action.ActionValidationPosSupport;

/**
 * Procesa el action mapeado del request a la URL CommonSalaReservaGetHorario
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class TeleTrabajoAsignarGetHorarioAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;
    private String inicio;
    private String termino;
    private String inicioSemana;
    private String terminoSemana;
    private String inicioPos;
    private String terminoPos;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), Manager.getTeleTrabajoSession(sesion), inicio, termino, inicioSemana, terminoSemana, inicioPos, terminoPos, getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException { 
        //OJO
        //return isValidPos(getPos(), getGenericSession().getTeleTrabajoSession().getListaSubordinados());
        return true;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getTermino() {
        return termino;
    }

    public void setTermino(String termino) {
        this.termino = termino;
    }

    public String getInicioSemana() {
        return inicioSemana;
    }

    public void setInicioSemana(String inicioSemana) {
        this.inicioSemana = inicioSemana;
    }

    public String getTerminoSemana() {
        return terminoSemana;
    }

    public void setTerminoSemana(String terminoSemana) {
        this.terminoSemana = terminoSemana;
    }

    public String getInicioPos() {
        return inicioPos;
    }

    public void setInicioPos(String inicioPos) {
        this.inicioPos = inicioPos;
    }

    public String getTerminoPos() {
        return terminoPos;
    }

    public void setTerminoPos(String terminoPos) {
        this.terminoPos = terminoPos;
    }
    
}