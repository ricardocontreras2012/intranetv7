/*
 * @(#)AlumnoCertificacionEmitirISO27001Service.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.registradorcurricular;

import com.lowagie.text.*;
import static com.lowagie.text.Element.ALIGN_JUSTIFIED;
import static com.lowagie.text.Element.ALIGN_LEFT;
import static com.lowagie.text.Element.ALIGN_RIGHT;
import static com.lowagie.text.Font.NORMAL;
import static com.lowagie.text.FontFactory.getFont;
import static com.lowagie.text.FontFactory.register;
import static com.lowagie.text.PageSize.LETTER;
import com.lowagie.text.pdf.PdfWriter;
import static com.lowagie.text.pdf.PdfWriter.getInstance;
import domain.model.AluCar;
import domain.model.Alumno;
import domain.model.LogCertificacion;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import static java.security.Security.addProvider;
import java.util.Date;
import static org.apache.struts2.ServletActionContext.getServletContext;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import session.GenericSession;
import session.WorkSession;

import static infrastructure.util.BarCodeUtil.putBarCode;
import static infrastructure.util.DateUtil.getFechaCiudad;
import static infrastructure.util.DateUtil.getSysdate;
import infrastructure.util.SystemParametersUtil;
import static infrastructure.util.SystemParametersUtil.CERTIFICATE_ISO_27001_BACKGROUND_IMAGE_PATH;
import static infrastructure.util.SystemParametersUtil.PATH_CERT;
import infrastructure.util.common.CommonArchivoUtil;
import infrastructure.util.common.CommonCertificacionUtil;
import static infrastructure.util.common.CommonCertificacionUtil.newParrafo;

/**
 *
 * @author Ricardo Contreras S.
 */
public class RegistradorCurricularCertificacionEmitirISO27001Service {

    /**
     * Method description
     *
     *
     * @param genericSession
     * @param codTramite
     * @param name
     * @param folio
     * @param key
     *
     * @return
     */
    public InputStream service(
            GenericSession genericSession, Integer codTramite, String name, Integer folio, String key) {

        try {
            WorkSession ws = genericSession.getWorkSession(key);
            Alumno alumno = ws.getAluCar().getAlumno();
            AluCar aluCar = ws.getAluCar();

            String urlWeb = aluCar.getUnidadFacultad().getUniUrl();
            Date fecha = getSysdate();
            String fechaString = getFechaCiudad(fecha);

            if (fecha != null) {
                String verificador = CommonCertificacionUtil.getVerificador(folio);

                String prefijoAlumno = CommonCertificacionUtil.getProfijoAlumon(alumno.getAluSexo());

                String certifico = "Se certifica que " + prefijoAlumno
                        + alumno.getNombreStd()
                        + ", Cédula Nacional de Identidad N° "
                        + alumno.getAluRut() + '-'
                        + alumno.getAluDv() + ", ha aprobado satisfactoriamente la evaluación que le permite certificar sus competencias como:";

                String logro = "Auditor Líder ISO 27.001:2013";
                String otorga = "Se otorga esta certificación bajo el cumplimiento de las exigencias establecidas en la Resolución Exenta N° 7275 del 21.12.2005 y en "
                        + "conformidad con los requisitos para la Auditoría de Sistemas de Gestión establecidos en la ISO 19011:2018.";

                String valido = "Este certificado es válido desde el 01 de febrero de 2020.";

                ws.setLogCertificacion(new LogCertificacion());
                CommonCertificacionUtil.registraLog(aluCar, folio, verificador, fecha, SystemParametersUtil.CERTIFICATE_ISO_CODE, null, null, codTramite, logro, null, genericSession.getRut(), genericSession.getUserType());

                Document document = new Document(LETTER);

                document.setMargins(100.0f, 100.0f, 50.0f, 50.0f);
                // /// FONTS
                String path = getServletContext().getRealPath("/fonts/local/tahoma.ttf");
                register(path, "tahoma_font");
                Font normal = getFont("tahoma_font", 10f, NORMAL);                
                Font big = getFont("tahoma_font", 18f, NORMAL);

                // //
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                PdfWriter writer = getInstance(document, buffer);
                CommonCertificacionUtil.BackgroundImage bi = new CommonCertificacionUtil.BackgroundImage(CERTIFICATE_ISO_27001_BACKGROUND_IMAGE_PATH);

                writer.setPageEvent(bi);
                addProvider(new BouncyCastleProvider());
                //writer.setEncryption(null, null, ALLOW_PRINTING, ENCRYPTION_AES_128);
                
                document.open();
                putHeader(document,  normal,  big, folio, verificador);
                putBody(document, normal, big, certifico, logro, otorga, valido, fechaString, urlWeb);
                putBarCode(writer.getDirectContent(), folio, verificador, 100,"");

                document.close();
                CommonCertificacionUtil.writeFile(buffer, PATH_CERT + name); 
                buffer.close();
                
                ws.setLogCertificacion(new LogCertificacion());
                return CommonArchivoUtil.getFile(name,"cert");

            }

        } catch (Throwable e) { //NO BORRAR
            e.printStackTrace();
        }

        return null;
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
     * @param folio
     * @param codigo
     *
     * @throws Exception
     */
    private void putHeader(Document document, Font normal,  Font negrita,
            Integer folio, String codigo)
            throws Exception {        

        Paragraph dummy = newParrafo(150, 350);
        dummy.add(" ");
        document.add(dummy);

        Paragraph parrafo5 = newParrafo(200, 100);
        parrafo5.setAlignment(ALIGN_LEFT);
        parrafo5.add(new Chunk("CERTIFICADO", negrita));
        document.add(parrafo5);

        Paragraph parrafo6 = newParrafo(150, 20);
        parrafo6.setAlignment(ALIGN_RIGHT);
        parrafo6.add(new Chunk("FOLIO N° " + folio, normal));
        document.add(parrafo6);

        Paragraph parrafo7 = newParrafo(120, 5);
        parrafo7.setAlignment(ALIGN_RIGHT);
        parrafo7.add(new Chunk("CODIGO DE VERIFICACION: " + codigo, normal));
        document.add(parrafo7);
    }

    /**
     * Method description
     *
     *
     * @param document
     * @param normal
     * @param certifico
     * @param extiende
     * @param fecha
     *
     * @throws Exception
     */
    private void putBody(Document document, Font normal, Font big, String certifico, String logro, String otorga, String valido, String fecha, String web)
            throws Exception {
        Paragraph parrafo1 = newParrafo(70, 25);
        parrafo1.setAlignment(ALIGN_JUSTIFIED);
        parrafo1.add(new Chunk(certifico, normal));
        document.add(parrafo1);

        Paragraph parrafo2 = newParrafo(70, 25);
        parrafo2.setAlignment(ALIGN_JUSTIFIED);
        parrafo2.add(new Chunk(logro, big));
        document.add(parrafo2);

        Paragraph parrafo3 = newParrafo(70, 10);
        parrafo3.setAlignment(ALIGN_JUSTIFIED);
        parrafo3.add(new Chunk(otorga, normal));
        document.add(parrafo3);

        Paragraph parrafo4 = newParrafo(70, 15);
        parrafo4.setAlignment(ALIGN_JUSTIFIED);
        parrafo4.add(new Chunk(valido, normal));
        document.add(parrafo4);

        Paragraph parrafo5 = newParrafo(70, 15);
        parrafo5.setAlignment(ALIGN_LEFT);
        parrafo5.add(new Chunk("La autenticidad de este certificado podrá ser verificada en " + web,
                normal));
        document.add(parrafo5);

        Paragraph parrafo6 = newParrafo(70, 100);
        parrafo6.setAlignment(ALIGN_LEFT);
        parrafo6.add(new Chunk(fecha, normal));
        document.add(parrafo6);
    }   
}
