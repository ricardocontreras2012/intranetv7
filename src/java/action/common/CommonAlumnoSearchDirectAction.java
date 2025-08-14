/*
 * @(#)CommonAlumnoSearchDirectAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.alumno.CommonAlumnoSearchDirectService;
import infrastructure.support.action.common.ActionCommonSupport;
import static infrastructure.util.common.CommonRandomUtil.getKeySession;

/**
 * Procesa el action mapeado del request a la URL CommonAlumnoSearchDirect
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonAlumnoSearchDirectAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer pos;
    private String typeSearch;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        String keyParent = getKey();

        setKey(getKeySession());     
        
        return new CommonAlumnoSearchDirectService().service(getGenericSession(), typeSearch, getKey(),
                keyParent, pos);
    }


    /**
     * Method description
     *
     * @param typeSearch
     */
    public void setTypeSearch(String typeSearch) {
        this.typeSearch = typeSearch;
    }

    /**
     * Method description
     *
     * @param pos
     */
    public void setPos(Integer pos) {
        this.pos = pos;
    }
}
