/*
 * @(#)ProfesorAutoEvaluacionAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.profesor;


import service.profesor.ProfesorAutoEvaluacionService;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 * Class description
 *
 *
 * @version 7, 26/12/2013
 * @author Ricardo Contreras S.
 */
public final class ProfesorAutoEvaluacionAction extends ActionParameterAwareSupport {
    
    private final ProfesorAutoEvaluacionService svc = new ProfesorAutoEvaluacionService();

    public String search() {
        return svc.searchAction(getGenericSession(), getKey());
    }
   /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return showForm();
    }

    /**
     * Method description
     *
     * @return
     */
    public String showForm() {
        return svc.showFormService(getGenericSession(), getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    public String save() {
        return svc.saveService(getGenericSession(), getMapParameters(), getKey());
    }

    public String remove() {
        return svc.removeService(getGenericSession());
    }
}