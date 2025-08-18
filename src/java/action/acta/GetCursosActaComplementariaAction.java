/*
 * @(#)GetCursosActaComplementariaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.acta;


import service.acta.GetCursosActaComplementariaService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class GetCursosActaComplementariaAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer agnoCal;
    private Integer semCal;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new GetCursosActaComplementariaService().service(this, getGenericSession(), getKey(), agnoCal,
                semCal);
    }

    public void setAgnoCal(Integer agnoCal) {
        this.agnoCal = agnoCal;
    }

    public void setSemCal(Integer semCal) {
        this.semCal = semCal;
    }

    public Integer getAgnoCal() {
        return agnoCal;
    }

    public Integer getSemCal() {
        return semCal;
    }
}
