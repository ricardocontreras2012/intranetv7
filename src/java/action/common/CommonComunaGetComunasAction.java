/*
 * @(#)CommonComunaGetComunasAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import domain.model.Comuna;
import service.common.CommonComunaGetComunasService;
import infrastructure.support.action.common.ActionCommonSupport;
import java.util.List;

/**
 * Procesa el action mapeado del request a la URL CommonComunaGetComunas
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonComunaGetComunasAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer region;
    private List<Comuna> comunaList;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        comunaList =  new CommonComunaGetComunasService().service(getGenericSession(), getKey(), region);
        return SUCCESS;
    }

    /**
     * Method description
     *
     * @param region
     */
    public void setRegion(Integer region) {
        this.region = region;
    }

    public List<Comuna> getComunaList() {
        return comunaList;
    }
}
