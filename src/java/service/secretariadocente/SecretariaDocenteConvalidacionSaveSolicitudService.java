/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.secretariadocente;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import domain.model.Asignatura;
import domain.model.ConvalidacionComision;
import domain.model.ConvalidacionSolicitud;
import domain.model.ConvalidacionSolicitudAsign;
import domain.model.ConvalidacionSolicitudAsignId;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.util.Map;
import session.GenericSession;
import session.SecretariaSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getSysdate;
import infrastructure.util.HibernateUtil;
import infrastructure.util.LogUtil;
import java.util.stream.IntStream;

/**
 *
 * @author rcontreras
 */
public class SecretariaDocenteConvalidacionSaveSolicitudService {

    /*public static String service(ActionCommonSupport action, GenericSession genericSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        String user =ActionUtil.getDBUser();
        SecretariaSession secreSession = (SecretariaSession)action.getSesion().get("secretariaSession");

        String retValue = SUCCESS;
 
        Integer asign;
        String cursada;
        BigDecimal nota;
        String notaStr;
        String electivo;
        String obs;
        String estado;        
        AluCar aluCar = ws.getAluCar();
        ConvalidacionSolicitud solicitud = secreSession.getConvalidacion();
        Integer correl;
        
        HibernateUtil.beginTransaction(user);

        if ("N".equals(solicitud.getCosEstado())) {
            ConvalidacionComision comision = new ConvalidacionComision();
            correl = ContextUtil.getDAO().getScalarPersistence(user).getSecuenciaConvalidacion();
            comision.setCcoCod(ContextUtil.getDAO().getScalarPersistence(user).getComision(aluCar.getId().getAcaCodCar(), aluCar.getAcaCodMen()));
            solicitud.setCosCorrel(correl);
            solicitud.setCosFecha(getSysdate());
            solicitud.setAluCar(aluCar);
            solicitud.setComision(comision);
            solicitud.setCosAgno(parseInt(parameters.get("agno")[0]));
            solicitud.setCosSem(parseInt(parameters.get("sem")[0]));
            solicitud.setCosEstado("G");
            ContextUtil.getDAO().getConvalidacionSolicitudPersistence(user).makePersistent(solicitud);
        }

        for (int i = 0; i < secreSession.getPorAprobar().size(); i++) {
            if (parameters.get("ck_" + i) != null) {
                Asignatura asignatura = secreSession.getPorAprobar().get(i).getAsignatura();
                asign = asignatura.getAsiCod();
                cursada = parameters.get("cursada_" + i)[0];
                notaStr = parameters.get("nota_" + i)[0];
                electivo = "";
                if ("S".equals(asignatura.getAsiElect())) {
                    electivo = parameters.get("electivo_" + i)[0];
                }
                obs = parameters.get("obs_" + i)[0];
                estado = parameters.get("estado_" + i)[0];

                if (notaStr.isEmpty()) {
                    nota = null;
                } else {
                    nota = new BigDecimal(notaStr.replace(",", "."));                    
                }

                ConvalidacionSolicitudAsign asignConv = new ConvalidacionSolicitudAsign();
                ConvalidacionSolicitudAsignId id = new ConvalidacionSolicitudAsignId();
                id.setCsaAsign(asign);
                id.setCsaCorrel(solicitud.getCosCorrel());
                asignConv.setId(id);
                asignConv.setAsignatura(asignatura);
                asignConv.setCsaCursada(cursada);
                asignConv.setCsaNota(nota);
                asignConv.setCsaElectivo(electivo);
                asignConv.setCsaEstado(estado);
                asignConv.setCsaObs(obs);

                ContextUtil.getDAO().getConvalidacionSolicitudAsignPersistence(user).makePersistent(asignConv);
            }
        }
        HibernateUtil.commitTransaction();
                
        LogUtil.setLog(genericSession.getRut(), solicitud.getCosCorrel());
        
        return retValue;
    }*/
    public static String service(ActionCommonSupport action, GenericSession genericSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        String user = ActionUtil.getDBUser();
        SecretariaSession secreSession = (SecretariaSession) action.getSesion().get("secretariaSession");

        String retValue = SUCCESS;

        AluCar aluCar = ws.getAluCar();
        ConvalidacionSolicitud solicitud = secreSession.getConvalidacion();

        HibernateUtil.beginTransaction(user);

        try {
            if ("N".equals(solicitud.getCosEstado())) {
                inicializarSolicitud(solicitud, aluCar, parameters, user);
            }

            procesarAsignaturas(secreSession, parameters, solicitud, user);

            HibernateUtil.commitTransaction();

            LogUtil.setLog(genericSession.getRut(), solicitud.getCosCorrel());
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            // Aquí puedes agregar logging o manejo de excepción más detallado
            throw e;
        }

        return retValue;
    }

    private static void inicializarSolicitud(ConvalidacionSolicitud solicitud, AluCar aluCar, Map<String, String[]> parameters, String user) {
        ConvalidacionComision comision = new ConvalidacionComision();
        Integer correl = ContextUtil.getDAO().getScalarPersistence(user).getSecuenciaConvalidacion();
        comision.setCcoCod(ContextUtil.getDAO().getScalarPersistence(user).getComision(aluCar.getId().getAcaCodCar(), aluCar.getAcaCodMen()));

        solicitud.setCosCorrel(correl);
        solicitud.setCosFecha(getSysdate());
        solicitud.setAluCar(aluCar);
        solicitud.setComision(comision);
        solicitud.setCosAgno(parseInt(parameters.get("agno")[0]));
        solicitud.setCosSem(parseInt(parameters.get("sem")[0]));
        solicitud.setCosEstado("G");

        ContextUtil.getDAO().getConvalidacionSolicitudPersistence(user).makePersistent(solicitud);
    }

    private static void procesarAsignaturas(SecretariaSession secreSession, Map<String, String[]> parameters, ConvalidacionSolicitud solicitud, String user) {
        IntStream.range(0, secreSession.getPorAprobar().size())
                .filter(i -> parameters.get("ck_" + i) != null)
                .forEach(i -> {
                    Asignatura asignatura = secreSession.getPorAprobar().get(i).getAsignatura();

                    Integer asign = asignatura.getAsiCod();
                    String cursada = parameters.get("cursada_" + i)[0];
                    String notaStr = parameters.get("nota_" + i)[0];
                    String electivo = "S".equals(asignatura.getAsiElect()) ? parameters.get("electivo_" + i)[0] : "";
                    String obs = parameters.get("obs_" + i)[0];
                    String estado = parameters.get("estado_" + i)[0];

                    BigDecimal nota = notaStr.isEmpty() ? null : new BigDecimal(notaStr.replace(",", "."));

                    ConvalidacionSolicitudAsign asignConv = new ConvalidacionSolicitudAsign();
                    ConvalidacionSolicitudAsignId id = new ConvalidacionSolicitudAsignId();
                    id.setCsaAsign(asign);
                    id.setCsaCorrel(solicitud.getCosCorrel());

                    asignConv.setId(id);
                    asignConv.setAsignatura(asignatura);
                    asignConv.setCsaCursada(cursada);
                    asignConv.setCsaNota(nota);
                    asignConv.setCsaElectivo(electivo);
                    asignConv.setCsaEstado(estado);
                    asignConv.setCsaObs(obs);

                    ContextUtil.getDAO().getConvalidacionSolicitudAsignPersistence(user).makePersistent(asignConv);
                });
    }

}
