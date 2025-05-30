/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.util.common;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import static com.lowagie.text.Element.ALIGN_JUSTIFIED;
import static com.lowagie.text.Element.ALIGN_LEFT;
import static com.lowagie.text.Element.ALIGN_RIGHT;
import com.lowagie.text.Font;
import static com.lowagie.text.Font.NORMAL;
import static com.lowagie.text.Font.UNDERLINE;
import static com.lowagie.text.FontFactory.getFont;
import static com.lowagie.text.FontFactory.register;
import static com.lowagie.text.PageSize.LETTER;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import static com.lowagie.text.pdf.PdfWriter.ALLOW_PRINTING;
import static com.lowagie.text.pdf.PdfWriter.ENCRYPTION_AES_128;
import static com.lowagie.text.pdf.PdfWriter.getInstance;
import domain.model.AluCar;
import java.io.FileOutputStream;
import static java.security.Security.addProvider;
import static org.apache.struts2.ServletActionContext.getServletContext;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import static infrastructure.util.BarCodeUtil.putBarCode;
import infrastructure.util.ContextUtil;
import static infrastructure.util.common.CommonCertificacionUtil.newParrafo;

/**
 *
 * @author Usach
 */
public class CommonConstanciaUtil {
     public static void writePdf(String file, Integer solicitud, String verificador, AluCar aluCar, String glosaPrincipal,
            String glosaFinal, String fechaString)
            throws Exception {
        Document document = new Document(LETTER);
        document.setMargins(100.0f, 100.0f, 50.0f, 50.0f);

        PdfWriter writer = getInstance(document,
                new FileOutputStream(file));

        // /// FONTS
        String path = getServletContext().getRealPath("/fonts/local/tahoma.ttf");
        register(path, "tahoma_font");
        Font normal = getFont("tahoma_font", 10.0f, NORMAL);
        Font titulo = getFont("tahoma_font", 12.0f, NORMAL);
        Font subrayado = getFont("tahoma_font", 12.0f, UNDERLINE);
        Font big = getFont("tahoma_font", 18.0f, NORMAL);

        CommonCertificacionUtil.BackgroundImage bi = new CommonCertificacionUtil.BackgroundImage("const", aluCar.getAluCarFunction().getUnidadFacultad());
        writer.setPageEvent(bi);
        addProvider(new BouncyCastleProvider());
        writer.setEncryption(null, null, ALLOW_PRINTING, ENCRYPTION_AES_128);

        document.open();

        putHeader(document, titulo, normal, subrayado, big, solicitud, aluCar.getAluCarFunction().getNombreFacultad().toUpperCase(ContextUtil.getLocale()));
        putBody(document, normal, glosaPrincipal, glosaFinal, fechaString);
        putBarCode(writer.getDirectContent(), solicitud, verificador, 0,"");
        document.close();

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
    private static void putHeader(Document document, Font titulo, Font normal, Font subrayado, Font negrita,
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
    private static void putBody(Document document, Font normal, String glosaPrincipal, String glosaFinal,
            String fechaString)
            throws Exception {
        Paragraph parrafo1 = newParrafo(0, 40);

        parrafo1.setAlignment(ALIGN_JUSTIFIED);
        parrafo1.add(new Chunk(glosaPrincipal, normal));
        document.add(parrafo1);

        Paragraph parrafo2 = newParrafo(0, 10);

        parrafo2.setAlignment(ALIGN_JUSTIFIED);
        parrafo2.add(new Chunk(glosaFinal, normal));
        document.add(parrafo2);

        Paragraph parrafo4 = newParrafo(0, 100);

        parrafo4.setAlignment(ALIGN_LEFT);
        parrafo4.add(new Chunk(fechaString, normal));
        document.add(parrafo4);
    }  
}
