/*
 * @(#)TitulosyGradosNominaSaveNominaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.titulosygrados;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.ExpedienteLogro;
import java.util.List;
import java.util.Map;
import domain.repository.ExpedienteNominaPersistence;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.HibernateUtil;
import java.util.stream.IntStream;

/**
 *
 * @author Ricardo Contreras S.
 */
public class TitulosyGradosNominaSaveNominaService {

    /**
     * Method description
     *
     *
     * @param genericSession
     * @param parameters
     * @param key
     *
     * @return
     */
    public String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        List<ExpedienteLogro> expedienteLogroList = ws.getExpedienteLogroList();
        String user = ActionUtil.getDBUser();

        if (expedienteLogroList != null && !expedienteLogroList.isEmpty()) {
            HibernateUtil.beginTransaction(user);

            ExpedienteNominaPersistence persistence
                    = ContextUtil.getDAO().getExpedienteNominaPersistence(user);

            persistence.saveNomina(ws.getExpedienteLogro().getNomina());

            IntStream.range(0, expedienteLogroList.size())
                    .forEach(i -> {
                        int ne = Integer.parseInt(parameters.get("ne_" + i)[0]);
                        String fe = parameters.get("fe_" + i)[0];
                        String rol = parameters.get("rol_" + i)[0];

                        ContextUtil.getDAO()
                                .getExpedienteLogroPersistence(user)
                                .saveExpediente(expedienteLogroList.get(i), ne, fe, rol);
                    });

            HibernateUtil.commitTransaction();
        }
        return SUCCESS;
    }
}
