/*
 * @(#)CommonMensajePrintMessageService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import static com.lowagie.text.Font.NORMAL;
import com.lowagie.text.Image;
import static com.lowagie.text.Image.getInstance;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import static com.lowagie.text.pdf.PdfWriter.getInstance;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import static java.security.Security.addProvider;
import java.util.Date;
import static org.apache.struts2.ServletActionContext.getServletContext;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import session.GenericSession;
import infrastructure.util.ActionInputStreamUtil;
import static infrastructure.util.DateUtil.getSysdate;
import infrastructure.util.FormatUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.PdfUtil;
import infrastructure.util.SystemParametersUtil;
import infrastructure.util.common.CommonArchivoUtil;
import infrastructure.util.common.CommonCertificacionUtil;
import infrastructure.util.common.CommonPDFUtil;
import domain.model.Mensaje;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonMensajePrintMessageService {
    
    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param report Parametros requeridos para JsperReport
     * @param key LLave para aceder a los datos de la sesion.
     * @return
     */
    static Font fontBig = PdfUtil.getFont("tahoma", 12.0f, NORMAL);
    static Font fontSmall = PdfUtil.getFont("tahoma", 6.5f, NORMAL);
    static Font fontMed = PdfUtil.getFont("tahoma", 7.5f, NORMAL);
    static Font font = PdfUtil.getFont("tahoma", 8.0f, NORMAL);

    public ActionInputStreamUtil service(GenericSession genericSession, Integer pos, String tipo, String key) throws Exception {

        try {
            String name;
            InputStream input;
            String description;
            
            Mensaje mensaje;

        mensaje = "R".equals(tipo)
                ? genericSession.getWorkSession(key).getReceivedMsgs().get(pos).getMensaje()
                : genericSession.getWorkSession(key).getSentMsgs().get(pos);

            name = "email_" + mensaje.getMsgCorrel() + ".pdf";
            input = getInput(genericSession, key, name, mensaje);
            description = FormatUtil.getMimeType(name);
            return new ActionInputStreamUtil(name, description, input );

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private static InputStream getInput(GenericSession genericSession,
            String key, String name, Mensaje msg)
            throws Exception {
        
        Date fecha = getSysdate();
        Integer genera = genericSession.getRut();

        Document doc = new Document(PageSize.LETTER);
        doc.setMargins(50.0f, 50.0f, 150.0f, 150.0f);
        doc.addTitle("Mensaje");
        doc.addAuthor("FAE-USACH");
        doc.addSubject("");
        doc.addCreator("Intranet FAE: " + fecha);
        doc.addCreationDate();

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PdfWriter writer = getInstance(doc, buffer);

        CommonMensajePrintMessageService.HeaderFooterPageEvent hf = new CommonMensajePrintMessageService.HeaderFooterPageEvent();
        writer.setPageEvent(hf);
        addProvider(new BouncyCastleProvider());

        doc.open();
        putHeader(doc, writer, msg);
        doc.close();

        CommonCertificacionUtil.writeFile(buffer, SystemParametersUtil.PATH_TEMP_FILES + name);
        buffer.close();

        LogUtil.setLog(genera, msg.getMsgCorrel());

        return CommonArchivoUtil.getFile(name, "tmp");
    }

    private static void putHeader(Document doc, PdfWriter writer, Mensaje msg) {
        

        float[] columnWidthsHeader = {100};
        PdfPTable header = new PdfPTable(1);
        header.setTotalWidth(512);
        header.setWidths(columnWidthsHeader);
        header.setLockedWidth(true);

        PdfPCell c1 = new PdfPCell(new Phrase("UNIVERSIDAD DE SANTIAGO DE CHILE", fontBig));
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.addCell(c1);
        

        PdfPCell emptyCell = new PdfPCell(new Phrase(" "));
        emptyCell.setBorder(0); // No mostrar borde de la celda vacía
        header.addCell(emptyCell);

        PdfPCell c4 = new PdfPCell(new Phrase("MENSAJE", fontBig));
        c4.setBorder(Rectangle.NO_BORDER);
        c4.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.addCell(c4);

        PdfContentByte canvas = writer.getDirectContent();
        header.writeSelectedRows(0, -1, 0, 750, canvas);

        float[] columnWidths = {30, 70};
        PdfPTable table = new PdfPTable(2);
        table.setTotalWidth(512);
        table.setWidths(columnWidths);
        table.setLockedWidth(true);

        PdfPCell tenv = new PdfPCell(new Phrase("Enviado por:", font));
        tenv.setBorder(Rectangle.NO_BORDER);
        PdfPCell env = new PdfPCell(new Phrase(msg.getMsgNombreEnv(), font));
        env.setBorder(Rectangle.NO_BORDER);

        PdfPCell tdir = new PdfPCell(new Phrase("Dirigido a:", font));
        tdir.setBorder(Rectangle.NO_BORDER);
        PdfPCell dir = new PdfPCell(new Phrase(msg.getMsgPara(), font));
        dir.setBorder(Rectangle.NO_BORDER);

        PdfPCell tfecha = new PdfPCell(new Phrase("Fecha:", font));
        tfecha.setBorder(Rectangle.NO_BORDER);
        PdfPCell fecha = new PdfPCell(new Phrase(msg.getFormattedFullDate(), font));
        fecha.setBorder(Rectangle.NO_BORDER);

        PdfPCell tasunto = new PdfPCell(new Phrase("Asunto:", font));
        tasunto.setBorder(Rectangle.NO_BORDER);
        PdfPCell asunto = new PdfPCell(new Phrase(msg.getMsgSubject(), font));
        asunto.setBorder(Rectangle.NO_BORDER);

        PdfPCell tmensaje = new PdfPCell(new Phrase("Mensaje:", font));
        tmensaje.setBorder(Rectangle.NO_BORDER);
        PdfPCell mensaje = new PdfPCell(new Phrase(msg.getMsgMsg(), font));
        mensaje.setBorder(Rectangle.NO_BORDER);
               
        table.addCell(tenv);
        table.addCell(env);
        table.addCell(tdir);
        table.addCell(dir);
        table.addCell(tfecha);
        table.addCell(fecha);
        table.addCell(tasunto);
        table.addCell(asunto);
        table.addCell(tmensaje);
        table.addCell(mensaje);
        
        doc.add(table);
    }

    public static class HeaderFooterPageEvent extends PdfPageEventHelper {

        private PdfTemplate template;
        private Image total;

        public HeaderFooterPageEvent() {
        }

        /**
         *
         * @param writer
         * @param document
         */
        @Override
        public void onOpenDocument(PdfWriter writer, Document document) {
            template = writer.getDirectContent().createTemplate(30, 16);
            try {
                total = Image.getInstance(template);
            } catch (DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            addHeader(writer);
            addFooter(writer);
        }

        private void addHeader(PdfWriter writer) {
            Image background = null;
            try {
                background = getInstance(getServletContext().getRealPath(SystemParametersUtil.CERTIFICATE_BACKGROUND_CLEAN_IMAGE_PATH));
            } catch (Exception e) {
            }

            float width = 612;
            float height = 792;
            writer.getDirectContentUnder()
                    .addImage(background, width, 0, 0, height, 0, 0);

            float[] columnWidths = {65, 35};
            PdfPTable table = new PdfPTable(2);
            table.setTotalWidth(512);
            table.setWidths(columnWidths);
            table.setLockedWidth(true);

            table.writeSelectedRows(0, -1, 34, 700, writer.getDirectContent());
        }

        private void addFooter(PdfWriter writer) {
            CommonPDFUtil.addFooter(writer, font, total);
        }

        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {
            CommonPDFUtil.onCloseDocument(writer, document, template, font);
        }
    }
}
