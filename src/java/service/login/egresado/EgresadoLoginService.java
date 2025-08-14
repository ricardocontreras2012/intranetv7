/*
 * @(#)EgresadoLoginService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.login.egresado;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import domain.model.Alumno;
import static java.lang.Integer.valueOf;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.apache.struts2.ServletActionContext.getRequest;
import session.EgresadoSession;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import static infrastructure.util.ActionUtil.retError;
import static infrastructure.util.AppStaticsUtil.ACTION_EXCEPTION;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;
import domain.repository.AlumnoRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class EgresadoLoginService {

    /**
     * Method description
     *
     * @param action Clase(action) que invoca al servicio.
     * @param sesion Contenedor de la genericSession.
     * @param rut
     * @param password
     * @param key LLave para aceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(ActionCommonSupport action, Map<String, Object> sesion, Integer rut, String password,
            String key) {
        String retValue = SUCCESS;
        String user = ActionUtil.getDBUser();

        if (!sesion.isEmpty()) {
            AlumnoRepository alumnoRepository
                    = ContextUtil.getDAO().getAlumnoRepository(user);
            Alumno alumno = alumnoRepository.find(rut, password);

            if (alumno != null) {
                GenericSession genericSession = new GenericSession(user, rut, password, 0);
                WorkSession ws = new WorkSession(user);

                genericSession.setSessionMap(new HashMap<>());
                genericSession.getSessionMap().put(key, ws);

                EgresadoSession egresadoSession = new EgresadoSession();
                egresadoSession.setAlumno(alumno);
                alumnoRepository.setLastLogin(rut);

                List<AluCar> aluCarList = ContextUtil.getDAO().getAluCarRepository(user).findEgresado(rut);

                if ((aluCarList != null) && !aluCarList.isEmpty()) {
                    AluCar aluCar = aluCarList.iterator().next();
                    aluCar.setAlumno(alumno);
                    ws.setAluCar(aluCar);
                    getComplemento(genericSession, aluCar, key);

                    genericSession.getWorkSession(key).setAluCarList(aluCarList);
                    egresadoSession.setAgno(valueOf(ContextUtil.getDAO().getParametroRepository(ActionUtil.getDBUser()).find("agno_act").getParValor()));

                    //retValue = "multiplesIngresos";
                    getRequest().getSession().setMaxInactiveInterval(1800);

                    sesion.put("genericSession", genericSession);
                    sesion.put("egresadoSession", egresadoSession);
                    LogUtil.setLog(rut);
                } else {
                    action.addActionError(action.getText("error.alumno.no.egresado"));
                    retValue = retError();
                }
            } else {
                action.addActionError(action.getText("error.rut.password"));
                retValue = retError();
            }
        } else {
            retValue = ACTION_EXCEPTION;
        }

        return retValue;
    }

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param aluCar
     * @param key LLave para acceder a los datos de la sesion.
     */
    public void getComplemento(GenericSession genericSession, AluCar aluCar, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        Alumno alumno = aluCar.getAlumno();

        ws.setAluCar(aluCar);
        genericSession.setDv(alumno.getAluDv());
        genericSession.setPaterno(alumno.getAluPaterno());
        genericSession.setMaterno(alumno.getAluMaterno());
        genericSession.setNombres(alumno.getAluNombre());
        genericSession.setNombre(alumno.getNombreStd());
        genericSession.setNombreMensaje(alumno.getNombreMensaje());
        ws.setNombre(alumno.getNombre());
    }
}
