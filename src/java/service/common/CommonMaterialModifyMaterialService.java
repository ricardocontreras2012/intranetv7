/*
 * @(#)CommonMaterialModifyMaterialService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.MaterialApoyo;
import java.io.File;
import domain.repository.MaterialApoyoPersistence;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;
import static infrastructure.util.common.CommonMaterialUtil.doNewFile;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonMaterialModifyMaterialService {

    /**
     * Method description
     *
     * @param action Clase(action) que invoca al servicio.
     * @param genericSession Sesion de trabajo.
     * @param tipo
     * @param upload
     * @param uploadFileName
     * @param caption
     * @param key LLave para aceder a los datos de la sesion.
     * @return Action status.
     * @throws Exception Si el servico genera una exception.
     */
    public String service(ActionCommonSupport action, GenericSession genericSession, Integer tipo, File upload,
            String uploadFileName, String caption, String key)
            throws Exception {   
        
        if (uploadFileName != null) {
            doChangeFile(action, genericSession, tipo, upload, uploadFileName, caption, key);
        } else {
            doUpdate(genericSession, tipo, caption, key);
        }

        LogUtil.setLogCurso(genericSession.getRut(), genericSession.getWorkSession(key).getCurso());
        
        return SUCCESS;
    }

    /**
     * Method description
     *
     * @param action Clase(action) que invoca al servicio.
     * @param genericSession Sesion de trabajo.
     * @param tipo
     * @param upload
     * @param uploadFileName
     * @param caption
     * @param key LLave para aceder a los datos de la sesion.
     * @throws Exception
     */
    private void doChangeFile(ActionCommonSupport action, GenericSession genericSession, Integer tipo, File upload,
            String uploadFileName, String caption, String key)
            throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        MaterialApoyo materialApoyo = ws.getMaterial();

        if (materialApoyo.getMatRutAutor().equals(genericSession.getRut())) {
            MaterialApoyoPersistence materialApoyoPersistence
                    = ContextUtil.getDAO().getMaterialApoyoPersistence(ActionUtil.getDBUser());

            doNewFile(action, genericSession, tipo, upload, uploadFileName, caption, key);
            beginTransaction(ActionUtil.getDBUser());
            materialApoyoPersistence.makeTransient(materialApoyo);
            commitTransaction();
            ws.setMaterial(null);
        }
    }

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param tipo
     * @param caption
     * @param key LLave para acceder a los datos de la sesion.
     */
    private void doUpdate(GenericSession genericSession, Integer tipo, String caption, String key) {
        MaterialApoyo materialApoyo = genericSession.getWorkSession(key).getMaterial();

        if (materialApoyo != null
                && materialApoyo.getMatRutAutor().equals(genericSession.getRut())) {
            materialApoyo.setMatDescripcion(caption);
            materialApoyo.setMatTipo(tipo);

            MaterialApoyoPersistence materialApoyoPersistence
                    = ContextUtil.getDAO().getMaterialApoyoPersistence(ActionUtil.getDBUser());

            beginTransaction(ActionUtil.getDBUser());
            materialApoyoPersistence.makePersistent(materialApoyo);
            commitTransaction();
        }
    }
}
