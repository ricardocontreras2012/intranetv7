/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.solicitud.alumno;

import domain.model.AluCar;
import domain.model.Asignatura;
import domain.model.Curso;
import domain.model.EstadoSolicitud;
import domain.model.Practica;
import domain.model.PracticaId;
import domain.model.Solicitud;
import domain.model.Tsolicitud;
import java.util.List;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.DateUtil;
import infrastructure.util.LogUtil;
import static java.lang.Integer.valueOf;

/**
 *
 * @author rcontreras
 */
public class AlumnoEnableFormService {

    public String service(GenericSession genericSession, String key, Integer tipo) {
        String retValue = null;

        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();
        Tsolicitud tsolicitud = new Tsolicitud();        
        Solicitud solicitud = new Solicitud();
        solicitud.setAluCar(aluCar);
        solicitud.setSolFolio(ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).getSecuenciaSolicitud());
        tsolicitud.setTsolCodigo(tipo);
        EstadoSolicitud estadoSolicitud = new EstadoSolicitud();
        estadoSolicitud.setEsolCod(10);
        solicitud.setEstadoSolicitud(estadoSolicitud);    
        solicitud.setTsolicitud(tsolicitud);
        solicitud.setSolFecha(DateUtil.getSysdate());
        ws.setSolicitud(solicitud);

        switch (tipo) {
            case 10:
                solicitud.setSolSolicita("Retiro Temporal sin Expresión de Causa");
                solicitud.setSolAgno(aluCar.getParametros().getAgnoAct());
                solicitud.setSolSem(aluCar.getParametros().getSemAct());

                retValue = "retiro_sin_exp";
                break;
            case 11:
                solicitud.setSolSolicita("Retiro Temporal con Expresión de Causa");
                solicitud.setSolAgno(aluCar.getParametros().getAgnoAct());
                solicitud.setSolSem(aluCar.getParametros().getSemAct());

                retValue = "retiro_con_exp";
                break;

            case 12:
                solicitud.setSolSolicita("Prórroga de Período Lectivo");
                solicitud.setSolAgno(aluCar.getParametros().getAgnoAct());
                solicitud.setSolSem(aluCar.getParametros().getSemAct());

                retValue = "prorroga";
                break;

            case 20:
                solicitud.setSolSolicita("Renuncia a la Carrera");
                solicitud.setSolAgno(aluCar.getParametros().getAgnoAct());
                solicitud.setSolSem(aluCar.getParametros().getSemAct());          

                retValue = "renuncia";
                break;

            case 25:
                solicitud.setSolSolicita("Reincorporación Eiminación Causal Académica");
                solicitud.setSolAgno(aluCar.getParametros().getAgnoAct());
                solicitud.setSolSem(aluCar.getParametros().getSemAct());

                retValue = "reincCausalAcad";
                break;

            ////
            case 30:
                solicitud.setSolSolicita("Reincorporación por no Matrícula(Abandono)");
                solicitud.setSolAgno(valueOf(ContextUtil.getDAO().getParametroRepository(ActionUtil.getDBUser()).find("agno_reinc_aba_y_notit").getParValor()));
                solicitud.setSolSem(valueOf(ContextUtil.getDAO().getParametroRepository(ActionUtil.getDBUser()).find("sem_reinc_aba_y_notit").getParValor()));

                retValue = "reincNoMat";
                break;

            case 35:
                solicitud.setSolSolicita("Reincorporación por no Titulación");
                solicitud.setSolAgno(valueOf(ContextUtil.getDAO().getParametroRepository(ActionUtil.getDBUser()).find("agno_reinc_aba_y_notit").getParValor()));
                solicitud.setSolSem(valueOf(ContextUtil.getDAO().getParametroRepository(ActionUtil.getDBUser()).find("sem_reinc_aba_y_notit").getParValor()));

                retValue = "reincNoTit";
                break;

            case 40:
                solicitud.setSolSolicita("Autorización Matrícula Fuera de Plazo");
                solicitud.setSolAgno(aluCar.getParametros().getAgnoAct());
                solicitud.setSolSem(aluCar.getParametros().getSemAct());

                retValue = "matricula";
                break;

            ////
            case 50:
                List<Curso> cursoList = ContextUtil.getDAO().getCursoRepository(ActionUtil.getDBUser()).getCursosSolicitudInscripcion(aluCar, aluCar.getParametros().getAgnoIns(), aluCar.getParametros().getSemIns());
                ws.setCursoSolicitudList(cursoList);

                retValue = "inscripcion";
                break;

            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
                Practica practica = new Practica();
                PracticaId id = new PracticaId();
                Asignatura asignatura = ContextUtil.getDAO().getAsignaturaRepository(ActionUtil.getDBUser()).find(aluCar, tipo);

                id.setPraRut(aluCar.getId().getAcaRut());
                id.setPraCodCar(aluCar.getId().getAcaCodCar());
                id.setPraAgnoIng(aluCar.getId().getAcaAgnoIng());
                id.setPraSemIng(aluCar.getId().getAcaSemIng());
                id.setPraAgno(aluCar.getParametros().getAgnoAct());
                id.setPraSem(aluCar.getParametros().getSemAct());
                id.setPraAsign(asignatura.getAsiCod());
                practica.setId(id);

                practica.setAsignatura(asignatura);
                practica.setAluCar(aluCar);
                practica.setSolicitud(solicitud);
                ws.setPractica(practica);
                solicitud.setSolSolicita("Solicitud de Inscripción de: " + asignatura.getAsiNom());
                solicitud.setSolAgno(id.getPraAgno());
                solicitud.setSolSem(id.getPraSem());

                retValue = "practica";
                break;

            case 70:
                ws.setCursoSolicitudList(ws.getCarga());
                retValue = "justificativo";
                break;
            case 80:
                retValue = "tramitacionLogro";
                break;
        }

        LogUtil.setLog(genericSession.getRut());
        return retValue;
    }
}
