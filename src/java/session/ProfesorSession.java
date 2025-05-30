/*
 * @(#)ProfesorSession.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package session;

import domain.model.Profesor;
import java.io.Serializable;
import java.util.List;
import domain.model.MencionInfoIntranetProfesorView;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ProfesorSession implements Serializable {

    private static final long serialVersionUID = 1L;
    private Profesor profesor;
    private List<MencionInfoIntranetProfesorView> mencionInfoIntranetProfesorViewList;
    private String horarioComun;

    /**
     * Method description
     *
     * @return
     */
    public Profesor getProfesor() {
        return profesor;
    }

    /**
     * Method description
     *
     * @param profesor
     */
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<MencionInfoIntranetProfesorView> getMencionInfoIntranetProfesorViewList() {
        return mencionInfoIntranetProfesorViewList;
    }

    public void setMencionInfoIntranetProfesorViewList(List<MencionInfoIntranetProfesorView> mencionInfoIntranetProfesorViewList) {
        this.mencionInfoIntranetProfesorViewList = mencionInfoIntranetProfesorViewList;
    }  

    public String getHorarioComun() {
        return horarioComun;
    }

    public void setHorarioComun(String horarioComun) {
        this.horarioComun = horarioComun;
    }
}
