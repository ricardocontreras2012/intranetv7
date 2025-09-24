/*
 * @(#)AlumnoAddInscripcionAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.inscripcion.alumno;

import infrastructure.dto.InscripcionJsonDTO;
import service.inscripcion.alumno.AlumnoAddInscripcionService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL
 * AlumnoInscripcionAddInscripcion
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class AlumnoAddInscripcionAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    InscripcionJsonDTO response;
    private Integer curAsign;
    private String curElect;
    private String curCoord;
    private Integer curSecc;
    private Integer curAgno;
    private Integer curSem;
    private String curComp;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        response = new AlumnoAddInscripcionService().service(this, getGenericSession(), curAsign, curElect, curCoord, curSecc, curAgno, curSem, curComp, getKey());
        return SUCCESS;
    }

    public InscripcionJsonDTO getResponse() {
        return response;
    }

    public void setCurAsign(Integer curAsign) {
        this.curAsign = curAsign;
    }

    public void setCurElect(String curElect) {
        this.curElect = curElect;
    }

    public void setCurCoord(String curCoord) {
        this.curCoord = curCoord;
    }

    public void setCurSecc(Integer curSecc) {
        this.curSecc = curSecc;
    }

    public void setCurAgno(Integer curAgno) {
        this.curAgno = curAgno;
    }

    public void setCurSem(Integer curSem) {
        this.curSem = curSem;
    }

    public void setCurComp(String curComp) {
        this.curComp = curComp;
    }
}
