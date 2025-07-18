/*
 * @(#)AlumnoPracticaSavePracticaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Comuna;
import domain.model.Empleador;
import domain.model.Persona;
import domain.model.Practica;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.HibernateUtil;
import static infrastructure.util.DateUtil.stringToDate;
import infrastructure.util.LogUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AlumnoPracticaSavePracticaService {

    /**
     * Method description
     *
     * @param action Clase(action) que invoca al servicio.
     * @param genericSession Sesion de trabajo.
     * @param rutEmp
     * @param fonoEmp
     * @param labor
     *
     * @param key LLave para aceder a los datos de la sesion.
     * @param direccion
     * @param rutAut
     * @param fonoAut
     * @param email
     * @param calidad
     * @param inicio
     * @param termino
     * @param comuna
     * @return Action status.
     * @throws java.lang.Exception
     */
    public String service(ActionCommonSupport action, GenericSession genericSession, Integer rutEmp, String fonoEmp, String labor, Integer comuna,
            String direccion, Integer rutAut, String fonoAut, String email, String calidad, String inicio, String termino, String key) throws Exception {

            WorkSession ws = genericSession.getWorkSession(key);
            String user = ActionUtil.getDBUser();            
            Practica practica = ws.getPractica();
            Empleador empleador = ContextUtil.getDAO().getEmpleadorPersistence(user).find(rutEmp);
            Persona autoriza = ContextUtil.getDAO().getPersonaPersistence(user).find(rutAut);
            Comuna comunaAux = ContextUtil.getDAO().getComunaPersistence(user).find(comuna);
            
            practica.setEmpleador(empleador);
            practica.setComuna(comunaAux);
            practica.setPraCalidadAut(calidad);
            practica.setPraDireccion(direccion);
            practica.setPraEstado("S"); //Solicitada
            practica.setPraFechaInicio(stringToDate(inicio));
            practica.setPraFechaTermino(stringToDate(termino));
            practica.setPraLabor(labor);
            practica.setAutoriza(autoriza);
            practica.setPraTelefonoAut(fonoAut);
            practica.setPraTelefonoEmp(fonoEmp);
            practica.setPraEmail(email);

            HibernateUtil.beginTransaction(user);
            ContextUtil.getDAO().getSolicitudPersistence(user).save(practica.getSolicitud());
            HibernateUtil.commitTransaction();
            HibernateUtil.beginTransaction(user);
            ContextUtil.getDAO().getPracticaPersistence(user).insert(practica);
            HibernateUtil.commitTransaction();

            LogUtil.setLog(genericSession.getRut());

            return SUCCESS;
    }
}
