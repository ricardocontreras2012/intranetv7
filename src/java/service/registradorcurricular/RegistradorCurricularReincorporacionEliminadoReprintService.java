/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.registradorcurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import domain.model.Reincorporacion;
import static java.lang.String.format;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import static service.registradorcurricular.RegistradorCurricularReincorporacionService.getGlosaFinal;
import static service.registradorcurricular.RegistradorCurricularReincorporacionService.getGlosaPrincipal;
import static service.registradorcurricular.RegistradorCurricularReincorporacionService.writePdf;
import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getFechaCiudad;
import infrastructure.util.SystemParametersUtil;
import static infrastructure.util.common.CommonArchivoUtil.getAttachFileName;

/**
 *
 * @author Ricardo Contreras S.
 */
public class RegistradorCurricularReincorporacionEliminadoReprintService {

    /**
     *
     * @param genericSession
     * @param key
     * @param folio
     * @return
     * @throws Exception
     */
    public static String service(GenericSession genericSession, String key, Integer folio) throws Exception {
        Reincorporacion reincorporacion = ContextUtil.getDAO().getReincorporacionPersistence(ActionUtil.getDBUser()).find(folio);

        AluCar aluCar;
        Date fecha = reincorporacion.getReiFecha();
        String fechaString = getFechaCiudad(fecha);
        String glosaPrincipal;
        String glosaFinal = getGlosaFinal();
        Integer tipo;
        Integer agnoReinc = reincorporacion.getId().getReiAgno();
        Integer semReinc = reincorporacion.getId().getReiSem();

        if (semReinc == 1) {
            semReinc = 2;
            agnoReinc -= 1;
        } else {
            semReinc = 1;
        }

        tipo = reincorporacion.getReiTipo();
        aluCar = reincorporacion.getAluCar();
        glosaPrincipal = getGlosaPrincipal(aluCar, agnoReinc, semReinc, fecha, tipo);

        int solicitud = reincorporacion.getReiSolicitud();
        String file = "Constancia_Reincorporacion_" + aluCar.getId().getAcaRut() + "_" + solicitud
                + ".pdf";

        file = SystemParametersUtil.PATH_TEMP_FILES + getAttachFileName(file, "_" + 0, folio);
        String fileCopia = SystemParametersUtil.PATH_SITUACIONES + format("%09d", aluCar.getId().getAcaRut()) + "-" + aluCar.getAlumno().getAluDv() + "-" + reincorporacion.getId().getReiAgno() + "-" + format("%05d", solicitud) + ".pdf";
        writePdf(file, solicitud, aluCar, glosaPrincipal, glosaFinal, fechaString);
        Files.copy(Paths.get(file), Paths.get(fileCopia), StandardCopyOption.REPLACE_EXISTING);

        return SUCCESS;
    }
}
