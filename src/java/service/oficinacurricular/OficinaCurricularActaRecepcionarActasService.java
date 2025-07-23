/*
 * @(#)OficinaCurricularActaRecepcionarActasService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.oficinacurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import java.util.stream.IntStream;
import domain.repository.ActaCalificacionRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class OficinaCurricularActaRecepcionarActasService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param parameters Todos los valores del formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        ActaCalificacionRepository actaRepository
                = ContextUtil.getDAO().getActaCalificacionRepository(ActionUtil.getDBUser());

        beginTransaction(ActionUtil.getDBUser());

        IntStream.range(0, ws.getActas().size())
                .filter(i -> parameters.get("ck_" + i) != null)
                .mapToObj(i -> ws.getActas().get(i))
                .forEach(acta -> {
                    actaRepository.recepcionarActa(
                            acta.getId().getAcalAgno(),
                            acta.getId().getAcalSem(),
                            acta.getId().getAcalFolio()
                    );
                });

        commitTransaction();
        return SUCCESS;
    }
}
