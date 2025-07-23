/*
 * @(#)EgresadoEstudiosNewEstudioService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */


package service.egresado;


import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;


/**
 *
 * @author Alvaro Romero C.
 */
public class EgresadoEstudiosNewEstudioService {

    /**
     * Method description
     *
     *
     * @param genericSession
     * @param key
     *
     * @return
     */
    public String service(GenericSession genericSession, String key) {

        /*
         * WorkSession ws = genericSession.getWorkSession(key);
         * Integer agnoMat;
         * ParametroRepository parametroRepository
         *           = ContextUtil.getDAO().getParametroRepository(AppStaticsUtil.USER_TYPE_COMMON);
         * agnoMat = Integer.valueOf(parametroRepository.find("agno_mat").getParValor());
         * ws.getFechaActual();
         */
        return SUCCESS;
    }
}
