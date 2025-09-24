/*
 * @(#)AlumnoGetCursosAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.inscripcion.alumno;

import domain.model.Curso;
import service.inscripcion.alumno.AlumnoGetCursosService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;
import java.util.List;

/**
 * Procesa el action mapeado del request a la URL AlumnoInscripcionGetCursos
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class AlumnoGetCursosAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer asign; 
    private List<Curso> lCurso;
    private String retValue;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action(){ 

        lCurso =  new AlumnoGetCursosService().service(this, getGenericSession(), Manager.getAlumnoSession(sesion), asign, getKey());
        return retValue;
    }

    public void setAsign(Integer asign) {
        this.asign = asign;
    }

    public List<Curso> getlCurso() {
        return lCurso;
    }

    public void setRetValue(String retValue) {
        this.retValue = retValue;
    }    
}
