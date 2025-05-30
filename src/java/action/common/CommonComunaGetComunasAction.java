/*
 * @(#)CommonComunaGetComunasAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static service.common.CommonComunaGetComunasService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonComunaGetComunas
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonComunaGetComunasAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer region;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), getKey(), region);
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getRegion() {
        return region;
    }

    /**
     * Method description
     *
     * @param region
     */
    public void setRegion(Integer region) {
        this.region = region;
    }
}
