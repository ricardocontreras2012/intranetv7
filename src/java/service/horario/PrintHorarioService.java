/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.horario;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import static com.lowagie.text.pdf.PdfWriter.getInstance;
import domain.model.Curso;
import domain.model.ModuloHorario;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionInputStreamUtil;
import static infrastructure.util.DateUtil.getSysdate;
import infrastructure.util.FormatUtil;
import infrastructure.util.LogUtil;
import static infrastructure.util.SystemParametersUtil.PATH_TEMP_FILES;
import infrastructure.util.common.CommonArchivoUtil;
import infrastructure.util.common.CommonCertificacionUtil;
import infrastructure.util.common.CommonHorarioUtil;

/**
 *
 * @author Usach
 */
public class PrintHorarioService {
    /**
     * Method description
     *
     * @param genericSession
     * @param id
     * @param key
     * @return
     * @throws java.lang.Exception
     */
    public ActionInputStreamUtil service(GenericSession genericSession,
            String id, String key) throws Exception {
  
        WorkSession ws = genericSession.getWorkSession(key);
        Integer rut;

        boolean isCMType = "CM".equals(ws.getType());
        switch (id) {
            case "AL":
                rut = isCMType ? ws.getAlumno().getAluRut() : genericSession.getRut();
                break;
            case "PR":
                rut = isCMType ? ws.getProfesor().getProfRut() : genericSession.getRut();
                break;
            case "AY":
                rut = isCMType ? ws.getAyudante().getAyuRut() : genericSession.getRut();
                break;
            default:
                rut = 0;
                break;
        }

        String name = "horario_" + rut + ".pdf";
        String description = FormatUtil.getMimeType(name);

        return new ActionInputStreamUtil(name, description, getInput(name, id, genericSession.getRut(), ws.getType(), rut, ws.getNombre(), ws.getModuloHorarioList(), ws.getCursoList()));                
    }

    private InputStream getInput(String name, String id, Integer genera, String userType, Integer rut, String userName, List<ModuloHorario> modList, List<Curso> cursoList) throws Exception {

        Date fecha = getSysdate();

        Document doc = new Document(PageSize.LETTER.rotate(), 50, 50, 20, 20);
        doc.addTitle("Horario");
        doc.addAuthor("USACH");
        doc.addSubject(userName);
        doc.addCreator("Intranet: " + fecha);
        doc.addCreationDate();

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PdfWriter writer = getInstance(doc, buffer);

        doc.open();
        CommonHorarioUtil.printHorario(doc, writer, id, modList, cursoList, userType, userName,"HORARIO");
        doc.close();

        CommonCertificacionUtil.writeFile(buffer, PATH_TEMP_FILES + name);
        buffer.close();

        LogUtil.setLog(genera, rut);

        return CommonArchivoUtil.getFile(name, "tmp");
    }
}
