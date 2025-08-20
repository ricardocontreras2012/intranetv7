/*
 * @(#)GetResumenAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */


package action.material;


import service.material.GetResumenService;
import infrastructure.support.action.common.ActionCommonSupport;


/**
 * Procesa el action mapeado del request a la URL CommonMaterialGetResumen
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class GetResumenAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;
    private Integer           agno;
    private Integer           pos;
    private Integer           sem;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new GetResumenService().service(getGenericSession(), agno, sem, pos, getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAgno() {
        return agno;
    }

    /**
     * Method description
     *
     * @param agno
     */
    public void setAgno(Integer agno) {
        this.agno = agno;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getPos() {
        return pos;
    }

    /**
     * Method description
     *
     * @param pos
     */
    public void setPos(Integer pos) {
        this.pos = pos;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getSem() {
        return sem;
    }

    /**
     * Method description
     *
     * @param sem
     */
    public void setSem(Integer sem) {
        this.sem = sem;
    }
}
