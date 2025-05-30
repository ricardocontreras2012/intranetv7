/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.registradorcurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import domain.model.Solicitud;
import static java.lang.String.format;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import static service.alumno.AlumnoSolicitudRetiroConstanciaService.getGlosaFinal;
import static service.alumno.AlumnoSolicitudRetiroConstanciaService.getGlosaPrincipal;

import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.DateUtil;
import infrastructure.util.HibernateUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.SystemParametersUtil;
import static infrastructure.util.common.CommonArchivoUtil.getAttachFileName;
import infrastructure.util.common.CommonCertificacionUtil;
import infrastructure.util.common.CommonConstanciaUtil;
import infrastructure.util.common.CommonSimpleMessageUtil;

/**
 *
 * @author Ricardo
 */
public class RegistradorCurricularSolicitudReprintService {
 public static String service(GenericSession genericSession, String key, Integer folio) throws Exception {
        
        Solicitud sol = ContextUtil.getDAO().getSolicitudPersistence(ActionUtil.getDBUser()).find(folio);

        if (sol.getEstadoSolicitud().getEsolCod()==40) {
            ///Necesario recuperar aluCar de la BD
            AluCar aluCar = ContextUtil.getDAO().getAluCarPersistence(ActionUtil.getDBUser()).find(sol.getAluCar().getId());
            aluCar.setAluCarFunction();
            ///

            Integer solicitud = sol.getSolFolio();
            String user = ActionUtil.getDBUser();
            
            Date fecha = sol.getSolFecha();
            
            HibernateUtil.beginTransaction(user);
            ContextUtil.getDAO().getSacarreraPersistence(user).retiroConExp(aluCar.getId(), sol.getSolAgno(), sol.getSolSem(), solicitud, DateUtil.getFormatedDate(fecha,"dd/MM/yyyy"));
            HibernateUtil.commitTransaction();
                        
            String verificador = CommonCertificacionUtil.getVerificador(folio);

            String fechaString =  DateUtil.getFechaCiudad(fecha);
            String glosaPrincipal;
            String glosaFinal = getGlosaFinal(aluCar);

            glosaPrincipal = getGlosaPrincipal(aluCar, sol.getSolAgno(), sol.getSolSem(), fecha, 11);

            String file = "Constancia_Retiro_Con_Expresion_Causa_" + aluCar.getId().getAcaRut() + "_" + solicitud + ".pdf";

            String fileTmp = getAttachFileName(file, "_" + 0, folio);
            String fileCopia = format("%09d", aluCar.getId().getAcaRut()) + "-" + aluCar.getAlumno().getAluDv() + "-" + sol.getSolAgno() + "-" + format("%05d", solicitud) + ".pdf";

            CommonConstanciaUtil.writePdf(SystemParametersUtil.PATH_ATTACH_MESSAGES + fileTmp, solicitud, verificador, aluCar, glosaPrincipal, glosaFinal, fechaString);
            Files.copy(Paths.get(SystemParametersUtil.PATH_ATTACH_MESSAGES + fileTmp), Paths.get(SystemParametersUtil.PATH_SITUACIONES + fileCopia), StandardCopyOption.REPLACE_EXISTING);

            CommonSimpleMessageUtil.send(fileTmp, key, aluCar.getAlumno().getAluRut(), aluCar.getAlumno().getNombreMensaje(),  genericSession.getRut(), "Registrador Curricular", genericSession.getUserType(), glosaPrincipal,
                    glosaFinal, "RT", "Retiro Temporal con Expresión de Causa", "TM_SIT");

            LogUtil.setLog(genericSession.getRut(), folio);

        }    
        return SUCCESS;
    }
}
