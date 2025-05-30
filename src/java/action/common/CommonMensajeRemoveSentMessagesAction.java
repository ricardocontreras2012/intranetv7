/*
 * @(#)CommonMensajeRemoveSentMessagesAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static service.common.CommonMensajeRemoveSentMessagesService.service;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 * Procesa el action mapeado del request a la URL
 * CommonMensajeRemoveSentMessages
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonMensajeRemoveSentMessagesAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;
    
    private int start;
    private int length;
    private String searchValue;
    private String tipoOrder;
    private String nombreDataColumnaActual;
    
    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), getMapParameters(), getKey(), start, length, searchValue, tipoOrder, nombreDataColumnaActual);
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getTipoOrder() {
        return tipoOrder;
    }

    public void setTipoOrder(String tipoOrder) {
        this.tipoOrder = tipoOrder;
    }

    public String getNombreDataColumnaActual() {
        return nombreDataColumnaActual;
    }

    public void setNombreDataColumnaActual(String nombreDataColumnaActual) {
        this.nombreDataColumnaActual = nombreDataColumnaActual;
    }
    
}