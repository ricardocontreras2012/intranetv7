/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.expediente.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import static com.opensymphony.xwork2.Action.ERROR;
import domain.model.Alumno;
import domain.repository.EstadoDocExpRepository;
import domain.model.ExpedienteLogro;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import session.GenericSession;
import session.WorkSession;

/**
 *
 * @author Ricardo
 */
public class AlumnoNewSolicitudService {

    public String service(ActionCommonSupport action, GenericSession genericSession, int pos, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        ws.setExpedienteLogro(ws.getExpedienteLogroList().get(pos));
        ws.setExpedienteLogroList(ContextUtil.getDAO().getExpedienteLogroRepository(ActionUtil.getDBUser()).findGeneradas(ws.getAluCar()));
        EstadoDocExpRepository estadoRepo = ContextUtil.getDAO().getEstadoDocExpRepository(key);
        
        ExpedienteLogro expLogro = ws.getExpedienteLogro();
        if(expLogro.getExplSol()!=null) {
            action.addActionMessage(action.getText("Solicitud por logro ya enviada"));
            return ERROR;
        }
        
        Alumno alumnoAux = ContextUtil.getDAO().getAlumnoRepository(ActionUtil.getDBUser()).getMisDatos(genericSession.getRut());
        Alumno alumno = genericSession.getWorkSession(key).getAluCar().getAlumno();
        alumno.setAluEstCiv(alumnoAux.getAluEstCiv());
        alumno.setComunaAlu(alumnoAux.getComunaAlu());
        alumno.setAluDirecAlu(alumnoAux.getAluDirecAlu());
        alumno.setAluFonoAlu(alumnoAux.getAluFonoAlu());
        alumno.setAluFechaNac(alumnoAux.getAluFechaNac());
        alumno.setAluEmail(alumnoAux.getAluEmail());
        alumno.setAluEmailLaboral(alumnoAux.getAluEmailLaboral());

        try {
            ws.setEstadoDocExpList(
                    estadoRepo.find(ws.getExpedienteLogroList().get(pos).getId()));
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Índice fuera de rango: " + pos);
        } catch (NullPointerException e) {
            System.err.println("Referencia nula encontrada: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
            e.printStackTrace();
        }

        return SUCCESS;
    }
}
