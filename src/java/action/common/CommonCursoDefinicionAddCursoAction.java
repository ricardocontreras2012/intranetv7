/*
 * @(#)CommonCursoDefinicionAddCursoAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.curso.CommonCursoDefinicionAddCursoService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonCursoDefinicionEnableAddCurso
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonCursoDefinicionAddCursoAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer asign;
    private String elect;
    private String electivo;
    private String coord;
    private Integer secc;
    private Integer cupo;
    private String inicio;
    private String termino;
    private String diurno;
    private String vespertino;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonCursoDefinicionAddCursoService().service(getGenericSession(), asign, elect, electivo, coord, secc, cupo, inicio, termino, diurno, vespertino, getKey());
    }

    public void setAsign(Integer asign) {
        this.asign = asign;
    }

    public void setElect(String elect) {
        this.elect = elect;
    }

    public void setElectivo(String electivo) {
        this.electivo = electivo;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public void setSecc(Integer secc) {
        this.secc = secc;
    }

    public void setCupo(Integer cupo) {
        this.cupo = cupo;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public void setTermino(String termino) {
        this.termino = termino;
    }

    public void setDiurno(String diurno) {
        this.diurno = diurno;
    }

    public void setVespertino(String vespertino) {
        this.vespertino = vespertino;
    }
}
