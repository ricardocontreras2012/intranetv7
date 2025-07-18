/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import static com.lowagie.text.pdf.PdfWriter.getInstance;
import domain.model.AluCar;
import domain.model.Curso;
import domain.model.ModuloHorario;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.InscripcionSupport;
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
public class CommonAlumnoHorarioInscripcionPrintService {
    /**
     * Method description
     *
     * @param genericSession
     * @param key
     * @return
     * @throws java.lang.Exception
     */
    public ActionInputStreamUtil service(GenericSession genericSession,
            String key) throws Exception {

        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aca = ws.getAluCar();
        Integer rut = aca.getId().getAcaRut();
        String name = "horario_inscripcion_" + rut + ".pdf";
        String description = FormatUtil.getMimeType(name);
        ws.setModuloHorarioList(CommonHorarioUtil.getModuloHorarioDocencia(aca.getParametros().getAgnoIns(), aca.getParametros().getSemIns()));

        return new ActionInputStreamUtil(name, description, getInput(name, "AL", genericSession.getRut(), ws.getType(), rut, ws.getNombre(), ws.getModuloHorarioList(), InscripcionSupport.getCursoList(ws.getAluCar().getInsList())));
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
        CommonHorarioUtil.printHorario(doc, writer, id, modList, cursoList, userType, userName,"HORARIO INSCRIPCIÓN");
        doc.close();

        CommonCertificacionUtil.writeFile(buffer, PATH_TEMP_FILES + name);
        buffer.close();

        LogUtil.setLog(genera, rut);

        return CommonArchivoUtil.getFile(name, "tmp");
    }
}
