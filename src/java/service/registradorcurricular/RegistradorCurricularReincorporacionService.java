/*
 * @(#)RegistradorCurricularReincorporacionService.java
 *
 * Creado por: Ricardo Contreras S.
 * Fecha Actualizacion: 17/07/2014
 *
 * License agreement: Uso exclusivo por FAE
 * Copyright (c) 2019 FAE-USACH
 */
package service.registradorcurricular;

import domain.model.AluCar;
import domain.model.PlanId;
import domain.model.Alumno;
import domain.model.Reincorporacion;
import com.lowagie.text.*;
import static com.lowagie.text.Element.ALIGN_JUSTIFIED;
import static com.lowagie.text.Element.ALIGN_LEFT;
import static com.lowagie.text.Element.ALIGN_RIGHT;
import static com.lowagie.text.Font.NORMAL;
import static com.lowagie.text.Font.UNDERLINE;
import static com.lowagie.text.FontFactory.getFont;
import static com.lowagie.text.FontFactory.register;
import static com.lowagie.text.PageSize.LETTER;
import com.lowagie.text.pdf.PdfWriter;
import static com.lowagie.text.pdf.PdfWriter.getInstance;
import java.io.ByteArrayOutputStream;
import static java.lang.Integer.valueOf;
import static java.lang.String.format;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import static org.apache.struts2.ServletActionContext.getServletContext;
import domain.repository.ParametroPersistence;
import domain.repository.ReincorporacionPersistence;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import static infrastructure.util.BarCodeUtil.putBarCode;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getFechaCiudad;
import static infrastructure.util.DateUtil.getFormattedDate;
import static infrastructure.util.DateUtil.getSysdate;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.SystemParametersUtil;
import static infrastructure.util.common.CommonArchivoUtil.getAttachFileName;
import infrastructure.util.common.CommonCertificacionUtil;
import infrastructure.util.common.CommonSimpleMessageUtil;
import infrastructure.util.common.CommonCertificacionUtil.BackgroundImage;
import static infrastructure.util.common.CommonCertificacionUtil.newParrafo;
import infrastructure.util.common.CommonSequenceUtil;

/**
 *
 * @author Ricardo Contreras S.
 */
public class RegistradorCurricularReincorporacionService {

    /**
     *
     * @param genericSession
     * @param tipoReinc
     * @param key
     */
    public void service(GenericSession genericSession, String tipoReinc, String key) {
        String user = ActionUtil.getDBUser();
        try {
            WorkSession ws = genericSession.getWorkSession(key);
            ParametroPersistence parametroPersistence
                    = ContextUtil.getDAO().getParametroPersistence(user);
            Integer agno = valueOf(parametroPersistence.find("agno_reincorporacion").getParValor());
            Integer sem = valueOf(parametroPersistence.find("sem_reincorporacion").getParValor());
            Integer agnoReinc;
            Integer semReinc;
            Integer nomina = ContextUtil.getDAO().getScalarPersistence(user).getSecuenciaNomina();

            ReincorporacionPersistence reincorporacionPersistence
                    = ContextUtil.getDAO().getReincorporacionPersistence(ActionUtil.getDBUser());

            List<Reincorporacion> reincorporacionList;
            String nReinc = "";

            switch (tipoReinc) {
                case "EL": //Eliminados
                    nReinc = "1,2,3";
                    reincorporacionPersistence.reincorporaEliminados(agno, sem, nomina);
                    break;
                case "MA-EL": //Masiva Eliminados
                    nReinc = "4";
                    reincorporacionPersistence.reincorporaEliminadosFull(agno, sem, nomina);
                    break;
                case "RT": //Retiro Temporal
                    nReinc = "5";
                    reincorporacionPersistence.reincorporaRetiros(agno, sem, nomina);
                    break;
                case "PPL": //Prorroga Periodo Lectivo
                    nReinc = "7";
                    reincorporacionPersistence.reincorporaProrrogas(agno, sem, nomina);
            }

            if (sem == 1) {
                semReinc = 2;
                agnoReinc = agno;
            } else {
                semReinc = 1;
                agnoReinc = agno + 1;
            }

            reincorporacionList = reincorporacionPersistence.find(agnoReinc, semReinc, nomina, nReinc);

            // Evitar lazy
            for (Reincorporacion reincorporacion : reincorporacionList) {
                reincorporacion.getAluCar().getNombreCarrera();
            }

            ws.setReincorporacionList(reincorporacionList);
            AluCar aluCar;
            Date fecha = getSysdate();
            String fechaString = getFechaCiudad(fecha);
            String glosaPrincipal;
            String glosaFinal = getGlosaFinal();
            Integer tipo;

            for (Reincorporacion reincorporacion : reincorporacionList) {
                tipo = reincorporacion.getReiTipo();
                if (tipo >= 1 && tipo <= 4) {
                    aluCar = reincorporacion.getAluCar();
                    glosaPrincipal = getGlosaPrincipal(aluCar, agno, sem, fecha, tipo);

                    int solicitud = reincorporacion.getReiSolicitud();
                    String file = "Constancia_Reincorporacion_" + aluCar.getId().getAcaRut() + "_" + solicitud
                            + ".pdf";
                    Integer folio = CommonSequenceUtil.getDocumentSeq();

                    file = SystemParametersUtil.PATH_SITUACIONES + getAttachFileName(file, "_" + 0, folio);
                    String fileCopia = SystemParametersUtil.PATH_SITUACIONES + format("%09d", aluCar.getId().getAcaRut()) + "-" + aluCar.getAlumno().getAluDv() + "-" + reincorporacion.getId().getReiAgno() + "-" + format("%05d", solicitud) + ".pdf";
                    writePdf(file, solicitud, aluCar, glosaPrincipal, glosaFinal, fechaString);                    
                    Files.copy(Paths.get(file), Paths.get(fileCopia), StandardCopyOption.REPLACE_EXISTING);

                    CommonSimpleMessageUtil.send(file.substring(file.indexOf("Constancia")), key, aluCar.getAlumno().getAluRut(), aluCar.getAlumno().getNombreMensaje(),  genericSession.getRut(), "Registrador Curricular", genericSession.getUserType(), glosaPrincipal,
                            glosaFinal, "REI", "Constancia Reincorporación", "TM_SIT");
                }
            }

            marcarProcesado(reincorporacionList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param file
     * @param solicitud
     * @param aluCar
     * @param glosaPrincipal* @param sem
     * @param glosaFinal
     * @param fechaString
     * @throws Exception
     */
    public void writePdf(String file, Integer solicitud, AluCar aluCar, String glosaPrincipal,
            String glosaFinal, String fechaString)
            throws Exception {
        Document document = new Document(LETTER);
        document.setMargins(100.0f, 100.0f, 50.0f, 50.0f);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PdfWriter writer = getInstance(document, buffer);

        // /// FONTS
        String path = getServletContext().getRealPath("/fonts/local/tahoma.ttf");
        register(path, "tahoma_font");
        Font normal = getFont("tahoma_font", 10f, NORMAL);
        Font titulo = getFont("tahoma_font", 12f, NORMAL);
        Font subrayado = getFont("tahoma_font", 12f, UNDERLINE);
        Font big = getFont("tahoma_font", 18f, NORMAL);
        
        aluCar.setAluCarFunction();     
        
        BackgroundImage bi = new BackgroundImage("const", aluCar.getAluCarFunction().getUnidadFacultad());
        writer.setPageEvent(bi);

        document.open();

        putHeader(document, titulo, normal, subrayado, big, solicitud, aluCar.getAluCarFunction().getNombreFacultad().toUpperCase(ContextUtil.getLocale()));
        putBody(document, normal, glosaPrincipal, glosaFinal, fechaString);
        putBarCode(writer.getDirectContent(), aluCar.getAlumno().getAluRut(), aluCar.getAlumno().getAluDv() + "-" + solicitud, 0,"");

        document.close();
        CommonCertificacionUtil.writeFile(buffer, file);
        buffer.close();        
    }

    /**
     * Method description
     *
     *
     * @param document
     * @param titulo
     * @param normal
     * @param subrayado
     * @param negrita
     * @param solicitud
     * @param facultad
     *
     * @throws Exception
     */
    private void putHeader(Document document, Font titulo, Font normal, Font subrayado, Font negrita,
            Integer solicitud, String facultad)
            throws Exception {
        Paragraph parrafo1 = newParrafo(149, 80);

        parrafo1.setAlignment(ALIGN_LEFT);
        parrafo1.add(new Chunk("REPÚBLICA DE CHILE", titulo));
        document.add(parrafo1);

        Paragraph parrafo2 = newParrafo(100, 10);

        parrafo2.setAlignment(ALIGN_LEFT);
        parrafo2.add(new Chunk("UNIVERSIDAD DE SANTIAGO DE CHILE", titulo));
        document.add(parrafo2);

        Paragraph parrafo3 = newParrafo(82, 10);

        parrafo3.setAlignment(ALIGN_LEFT);
        parrafo3.add(new Chunk(facultad, subrayado));
        document.add(parrafo3);

        Paragraph parrafo4 = newParrafo(137, 10);

        parrafo4.setAlignment(ALIGN_LEFT);
        parrafo4.add(new Chunk("REGISTRO CURRICULAR", titulo));
        document.add(parrafo4);

        Paragraph parrafo5 = newParrafo(146, 60);

        parrafo5.setAlignment(ALIGN_LEFT);
        parrafo5.add(new Chunk("CONSTANCIA", negrita));
        document.add(parrafo5);

        Paragraph parrafo6 = newParrafo(150, 10);

        parrafo6.setAlignment(ALIGN_RIGHT);
        parrafo6.add(new Chunk("SOLICITUD N° " + solicitud, normal));
        document.add(parrafo6);
    }

    /**
     * Method description
     *
     *
     * @param document
     * @param normal
     * @param glosaPrincipal
     * @param glosaFinal
     * @param fechaString
     *
     * @throws Exception
     */
    private void putBody(Document document, Font normal, String glosaPrincipal, String glosaFinal,
            String fechaString)
            throws Exception {
        Paragraph parrafo1 = newParrafo(0, 40);

        parrafo1.setAlignment(ALIGN_JUSTIFIED);
        parrafo1.add(new Chunk(glosaPrincipal, normal));
        document.add(parrafo1);

        Paragraph parrafo2 = newParrafo(0, 10);

        parrafo2.setAlignment(ALIGN_LEFT);
        parrafo2.add(new Chunk(glosaFinal, normal));
        document.add(parrafo2);

        Paragraph parrafo4 = newParrafo(0, 100);

        parrafo4.setAlignment(ALIGN_LEFT);
        parrafo4.add(new Chunk(fechaString, normal));
        document.add(parrafo4);
    }    

    /**
     *
     * @param aluCar
     * @param agno
     * @param sem
     * @param fecha
     * @param tipo
     * @return
     */
    public String getGlosaPrincipal(AluCar aluCar, Integer agno, Integer sem, Date fecha, Integer tipo) {
        String glosa = null;
        Alumno alumno = aluCar.getAlumno();
        Integer agnoReinc;
        Integer semReinc;
        String next;
        PlanId plan = aluCar.getPlan().getId();

        next = ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getSiguienteSemestre(agno, sem, plan.getPlaCodCar(), plan.getPlaCodMen(), plan.getPlaCod());
        agnoReinc = Integer.parseInt(next.substring(0, 4));
        semReinc = Integer.parseInt(next.substring(4));

        String prefijoAlumno;
        String prefijoEliminado;
        String prefijoReincorporado;

        if ("1".equals(alumno.getAluSexo())) {
            prefijoAlumno = "la alumna ";
            prefijoEliminado = "eliminada";
            prefijoReincorporado = "reincorporada";
        } else {
            prefijoAlumno = "el alumno ";
            prefijoEliminado = "eliminado";
            prefijoReincorporado = "reincorporado";
        }

        switch (tipo) {
            case 1:
                glosa = "Se deja constancia que " + prefijoAlumno + " " + alumno.getNombreStd() + ", Cédula de Identidad N° "
                        + alumno.getAluRut() + "-" + alumno.getAluDv() + ", ha sido " + prefijoEliminado
                        + " por causal académica de la carrera de " + aluCar.getNombreCarrera() + " el " + sem + "/" + agno
                        + " y que ha sido " + prefijoReincorporado + " automáticamente por primera vez a partir del " + semReinc + "/"
                        + agnoReinc + " con fecha " + getFormattedDate(fecha, "dd/MM/yyyy")
                        + " debido a que cumple los requisitos de reincorporación establecidos en el Reglamento Complementario al Reglamento General del Régimen de Estudios de Pregrado, por lo que no debe presentar solicitud.";
                break;
            case 2:
                glosa = "Se deja constancia que " + prefijoAlumno + " " + alumno.getNombreStd() + ", Cédula de Identidad N° "
                        + alumno.getAluRut() + "-" + alumno.getAluDv() + ", ha sido " + prefijoEliminado
                        + " por causal académica de la carrera de " + aluCar.getNombreCarrera() + " el " + sem + "/" + agno
                        + " y que ha sido " + prefijoReincorporado + " automáticamente por segunda vez a partir del " + semReinc + "/"
                        + agnoReinc + " con fecha " + getFormattedDate(fecha, "dd/MM/yyyy")
                        + " debido a que cumple los requisitos de reincorporación establecidos en el Reglamento Complementario al Reglamento General del Régimen de Estudios de Pregrado, por lo que no debe presentar solicitud.";
                break;
            case 3:
                glosa = "Se deja constancia que " + prefijoAlumno + " " + alumno.getNombreStd() + ", Cédula de Identidad N° "
                        + alumno.getAluRut() + "-" + alumno.getAluDv() + ", ha sido " + prefijoEliminado
                        + " por causal académica(progresión) de la carrera de " + aluCar.getNombreCarrera() + " el " + sem + "/" + agno
                        + " y que ha sido " + prefijoReincorporado + " automáticamente a partir del " + semReinc + "/"
                        + agnoReinc + " con fecha " + getFormattedDate(fecha, "dd/MM/yyyy")
                        + " debido a que cumple con el Reglamento Complementario de la Facultad, por lo que no debe presentar solicitud.";
                break;
            case 4:
                glosa = "Se deja constancia que " + prefijoAlumno + " " + alumno.getNombreStd() + ", Cédula de Identidad N° "
                        + alumno.getAluRut() + "-" + alumno.getAluDv() + ", ha sido " + prefijoEliminado
                        + " por causal académica de la carrera de " + aluCar.getNombreCarrera() + " el " + sem + "/" + agno
                        + " y que ha sido " + prefijoReincorporado + " automáticamente a partir del " + semReinc + "/"
                        + agnoReinc + " con fecha " + getFormattedDate(fecha, "dd/MM/yyyy")
                        + ".";
                break;
        }
        return glosa;
    }

    /**
     *
     * @return
     */
    public String getGlosaFinal() {
        return "Esta reincorporación quedará registrada en su hoja de vida como una oportunidad. Revise su situación en Mis Datos Curriculares de la Intranet Académica.";
    }    

    private void marcarProcesado(List<Reincorporacion> reincorporacionList) {
        ReincorporacionPersistence reincorporacionPersistence
                = ContextUtil.getDAO().getReincorporacionPersistence(ActionUtil.getDBUser());

        beginTransaction(ActionUtil.getDBUser());
        for (Reincorporacion reincorporacion : reincorporacionList) {
            Integer tipo = reincorporacion.getReiTipo();
            if (tipo >= 1 && tipo <= 3) {
                reincorporacionPersistence.marcarProcesado(reincorporacion.getReiSolicitud());
            }
        }
        commitTransaction();
    }
}
