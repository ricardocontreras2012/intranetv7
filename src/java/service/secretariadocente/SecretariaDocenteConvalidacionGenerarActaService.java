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
import java.math.BigDecimal;
import java.util.Map;
import session.GenericSession;
import session.SecretariaSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.HibernateUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonActaUtil;
import java.util.stream.IntStream;
import domain.repository.ActaConvalidacionAsignaturaRepository;

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
    public String service(ActionCommonSupport action, GenericSession genericSession, SecretariaSession secreSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();
        ConvalidacionSolicitud solicitud = secreSession.getConvalidacion();
        String user = ActionUtil.getDBUser();
        String retValue = SUCCESS;

        ActaConvalidacionAsignaturaRepository actaAsigRepository
                = ContextUtil.getDAO().getActaConvalidacionAsignaturaRepository(user);

        if (aluCar.estaMatriculado(solicitud.getCosAgno(), solicitud.getCosSem())) {
            Integer folio;
            HibernateUtil.beginTransaction(user);
            ContextUtil.getDAO().getConvalidacionSolicitudRepository(user).setEstado(solicitud.getCosCorrel(), "E");

            folio = crearActa(aluCar, solicitud.getCosAgno(), solicitud.getCosSem(), user);
            IntStream.range(0, secreSession.getPorAprobar().size())
                    .filter(i -> "C".equals(parameters.get("estado_" + i)[0]))
                    .forEach(i -> {
                        Asignatura asignatura = secreSession.getPorAprobar().get(i).getAsignatura();
                        Integer asign = asignatura.getAsiCod();
                        String cursada = parameters.get("cursada_" + i)[0];
                        String notaStr = parameters.get("nota_" + i)[0];
                        String electivo = "N"; // valor por defecto

                        if ("S".equals(asignatura.getAsiElect())) {
                            electivo = parameters.get("electivo_" + i)[0];
                        }

                        BigDecimal nota = notaStr.isEmpty() ? null : new BigDecimal(notaStr.replace(",", "."));

                        actaAsigRepository.convalidar(folio, asign, electivo, cursada, nota);
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

    private Integer crearActa(AluCar aluCar, Integer agno, Integer sem, String user) {
        Integer folio = CommonActaUtil.getFolio(user);
        ContextUtil.getDAO().getActaConvalidacionRepository(user).crearActa(folio, agno, sem, aluCar.getId());

        return folio;
    }
}
