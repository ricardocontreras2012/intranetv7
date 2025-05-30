/*
 * @(#)SecretariaDocenteConvalidacionGenerarActaService.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package service.secretariadocente;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import domain.model.Asignatura;
import domain.model.ConvalidacionSolicitud;
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
        String user =ActionUtil.getDBUser();
        String retValue = SUCCESS;

        ActaConvalidacionAsignaturaPersistence actaAsigPersistence
                = ContextUtil.getDAO().getActaConvalidacionAsignaturaPersistence(user);

            if (aluCar.estaMatriculado(solicitud.getCosAgno(), solicitud.getCosSem())) {
                Integer folio;
                Integer asign;
                String cursada;
                String electivo = null;
                BigDecimal nota;
                String notaStr;

                HibernateUtil.beginTransaction(user);
                ContextUtil.getDAO().getConvalidacionSolicitudPersistence(user).setEstado(solicitud.getCosCorrel(), "E");

                folio = crearActa(aluCar, solicitud.getCosAgno(), solicitud.getCosSem(), user);

                for (int i = 0; i < secreSession.getPorAprobar().size(); i++) {
                    String estado = parameters.get("estado_" + i)[0];
                    if ("C".equals(estado)) {
                        Asignatura asignatura = secreSession.getPorAprobar().get(i).getAsignatura();
                        asign = asignatura.getAsiCod();
                        cursada = parameters.get("cursada_" + i)[0];
                        notaStr = parameters.get("nota_" + i)[0];
                        if ("S".equals(asignatura.getAsiElect())) {
                            electivo = parameters.get("electivo_" + i)[0];
                        }

                        if (notaStr.isEmpty()) {
                            nota = null;
                        } else {                           
                             nota = new BigDecimal(notaStr.replace(",", "."));
                        }
                        actaAsigPersistence.convalidar(folio, asign, electivo, cursada, nota);
                    }
                }
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
