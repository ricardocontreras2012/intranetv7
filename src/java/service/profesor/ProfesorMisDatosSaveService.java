/*
 * @(#)ProfesorMisDatosSaveService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.profesor;

import com.opensymphony.xwork2.Action;
import session.GenericSession;
import session.ProfesorSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.FormatUtil.emailNormalizado;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ProfesorMisDatosSaveService {

    /**
     * Method description
     *
     * @param action Clase(action) que invoca al servicio.
     * @param genericSession Sesion de trabajo.
     * @param ps
     * @param emailUsach
     * @param email
     * @param fechaNac
     * @param direccion
     * @param comuna
     * @param fono
     * @param key
     * @return Action status.
     * @throws Exception Si el servico genera una exception.
     */
    public String service(ActionCommonSupport action, GenericSession genericSession, ProfesorSession ps, String emailUsach, String email,
            String fechaNac, String direccion, Integer comuna, String fono, String key)
            throws Exception {       
      
        String emailProf = (emailUsach == null || emailUsach.isEmpty()) ? email : emailUsach + "," + email;
        beginTransaction(ActionUtil.getDBUser());
        ContextUtil.getDAO().getProfesorPersistence(ActionUtil.getDBUser()).setMisDatos(genericSession.getRut(), emailNormalizado(emailProf), 
                //new SimpleDateFormat("yyyy-MM-dd").parse(fechaNac), direccion, comuna,
                fono);
        commitTransaction();
        action.addActionMessage(action.getText("message.datos.grabados"));
        
        genericSession.getWorkSession(key).setProfesor(ContextUtil.getDAO().getProfesorPersistence(ActionUtil.getDBUser()).find(ps.getProfesor().getProfRut()));
        if (ps.getProfesor().getProfUpdated() == null) {
            return "stack";
        } else {          
            return Action.SUCCESS;
        }        
    }
}
