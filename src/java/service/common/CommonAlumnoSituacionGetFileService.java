/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import domain.model.AluCar;
import domain.model.Alumno;
import domain.model.Sacarrera;
import java.io.InputStream;
import session.GenericSession;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.FormatUtil;
import infrastructure.util.common.CommonArchivoUtil;

/**
 *
 * @author Administrador
 */
public class CommonAlumnoSituacionGetFileService {
    
    public static ActionInputStreamUtil service(GenericSession genericSession, Integer pos, String key) throws Exception {
        String name;
        InputStream input;
        String description;
        
        AluCar aluCar = genericSession.getWorkSession(key).getAluCar();
        Alumno alumno = aluCar.getAlumno();
        Sacarrera sacarrera = aluCar.getSituaciones().get(pos);
        name = String.format("%09d", alumno.getAluRut()) + "-" + alumno.getAluDv()+"-"+sacarrera.getSacAgnoInic()+"-"+String.format("%05d", sacarrera.getSacDocAcep())+".pdf";
        
        input = CommonArchivoUtil.getFile(name,"sit");
        description = FormatUtil.getMimeType(name);
        return new ActionInputStreamUtil(name, description, input);
    }
}
