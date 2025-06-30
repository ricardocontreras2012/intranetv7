/*
 * @(#)SecretariaDocenteConvalidacionGenerarActaService.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package service.secretariadocente;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import domain.model.Asignatura;
import domain.model.ConvalidacionSolicitud;
import domain.model.ConvalidacionSolicitudAsign;
import java.math.BigDecimal;
import java.util.Map;
import domain.repository.ActaConvalidacionAsignaturaPersistence;
import session.GenericSession;
import session.SecretariaSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.HibernateUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonActaUtil;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * @author Ricardo Contreras S.
 */
public class SecretariaDocenteConvalidacionGenerarActaService {

    /**
     * Method description
     *
     *
     * @param action
     * @param genericSession
     * @param secreSession
     * @param parameters
     * @param key
     *
     * @return
     */
    public static String service(ActionCommonSupport action, GenericSession genericSession, SecretariaSession secreSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();
        ConvalidacionSolicitud solicitud = secreSession.getConvalidacion();
        String user = ActionUtil.getDBUser();
        String retValue = SUCCESS;

        ActaConvalidacionAsignaturaPersistence actaAsigPersistence
                = ContextUtil.getDAO().getActaConvalidacionAsignaturaPersistence(user);

        if (aluCar.estaMatriculado(solicitud.getCosAgno(), solicitud.getCosSem())) {
            Integer folio;
            HibernateUtil.beginTransaction(user);
            ContextUtil.getDAO().getConvalidacionSolicitudPersistence(user).setEstado(solicitud.getCosCorrel(), "E");

            folio = crearActa(aluCar, solicitud.getCosAgno(), solicitud.getCosSem(), user);
            List<ConvalidacionSolicitudAsign> porAprobarList = secreSession.getPorAprobar();
            IntStream.range(0, porAprobarList.size())
                    .filter(i -> "C".equals(parameters.get("estado_" + i)))
                    .forEach(i -> {
                        Asignatura asignatura = porAprobarList.get(i).getAsignatura();
                        Integer asign = asignatura.getAsiCod();
                        String cursada = parameters.get("cursada_" + i)[0];
                        String notaStr = parameters.get("nota_" + i)[0];
                        String electivo = null;
                        if ("S".equals(asignatura.getAsiElect())) {
                            electivo = parameters.get("electivo_" + i)[0];
                        }
                        BigDecimal nota = (notaStr.isEmpty()) ? null : new BigDecimal(notaStr.replace(",", "."));
                        actaAsigPersistence.convalidar(folio, asign, electivo, cursada, nota);
                    });

            HibernateUtil.commitTransaction();
            action.addActionMessage(action.getText("message.acta.emitida"));

            LogUtil.setLog(genericSession.getRut(), folio);
        } else {
            action.addActionError(action.getText("error.alumno.no.matriculado"));
            retValue = "noMatriculado";
        }
        return retValue;
    }

    private static Integer crearActa(AluCar aluCar, Integer agno, Integer sem, String user) {
        Integer folio = CommonActaUtil.getFolio(user);
        ContextUtil.getDAO().getActaConvalidacionPersistence(user).crearActa(folio, agno, sem, aluCar.getId());

        return folio;
    }
}
