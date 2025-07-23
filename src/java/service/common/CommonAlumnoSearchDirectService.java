/*
 * @(#)CommonAlumnoSearchDirectService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.common.CommonAlumnoUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonAlumnoSearchDirectService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param typeSearch
     * @param key LLave para acceder a los datos de la sesion.
     * @param keyParent
     * @param pos Numero del registro seleccionado en el formulario.
     * @return Action status.
     */
    public String service(GenericSession genericSession, String typeSearch, String key,
            String keyParent, Integer pos) {
        WorkSession ws = new WorkSession(ActionUtil.getDBUser());

        ws.setKeyParent(keyParent);
        genericSession.getSessionMap().put(key, ws);
        ws.setTypeSearch(typeSearch);

        AluCar aluCarOri = genericSession.getWorkSession(keyParent).getNominaCurso().get(pos);
        AluCar aluCar = ContextUtil.getDAO().getAluCarRepository(ActionUtil.getDBUser()).find(aluCarOri.getId());

        CommonAlumnoUtil.loadAluCar(genericSession, ws, aluCar);

        ws.setNombre(CommonAlumnoUtil.getNombreSocial(aluCar.getAlumno()));
        ws.setAlumno(aluCar.getAlumno());

        return SUCCESS;
    }
}
