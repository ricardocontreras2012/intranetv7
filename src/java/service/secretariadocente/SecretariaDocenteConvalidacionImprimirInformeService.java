/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.secretariadocente;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import static com.lowagie.text.Font.NORMAL;
import com.lowagie.text.Image;
import static com.lowagie.text.Image.getInstance;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import static com.lowagie.text.pdf.PdfWriter.getInstance;
import domain.model.AluCar;
import domain.model.Alumno;
import domain.model.ConvalidacionComisionProf;
import domain.model.ConvalidacionSolicitud;
import domain.model.ConvalidacionSolicitudAsign;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.security.Security.addProvider;
import java.util.Date;
import java.util.List;
import static org.apache.struts2.ServletActionContext.getServletContext;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import session.GenericSession;
import session.SecretariaSession;
import session.WorkSession;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getSysdate;
import infrastructure.util.FormatUtil;
import infrastructure.util.HibernateUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.PdfUtil;
import infrastructure.util.SystemParametersUtil;
import static infrastructure.util.SystemParametersUtil.PATH_ATTACH_MESSAGES;
import static infrastructure.util.SystemParametersUtil.PATH_CERT;
import infrastructure.util.common.CommonArchivoUtil;
import infrastructure.util.common.CommonCertificacionUtil;
import java.util.stream.IntStream;

/**
 *
 * @author rcontreras
 */
public class SecretariaDocenteConvalidacionImprimirInformeService {

    static Font fontBig = PdfUtil.getFont("tahoma", 12.0f, NORMAL);
    static Font fontSmall = PdfUtil.getFont("tahoma", 6.5f, NORMAL);
    static Font fontMed = PdfUtil.getFont("tahoma", 7.5f, NORMAL);
    static Font font = PdfUtil.getFont("tahoma", 8.0f, NORMAL);

    public ActionInputStreamUtil service(GenericSession genericSession, SecretariaSession secreSession, String key) throws Exception {
        String name;
        InputStream input;
        String description;

        Integer folio = secreSession.getPorAprobar().get(0).getId().getCsaCorrel();
        name = CommonCertificacionUtil.getNameFile(genericSession.getWorkSession(key).getAluCar(), folio, 50);
        input = getInput(genericSession, secreSession, key, name, folio);
        description = FormatUtil.getMimeType(name);
        return new ActionInputStreamUtil(name, description, input);
    }

    private InputStream getInput(GenericSession genericSession, SecretariaSession secreSession,
            String key, String name, Integer folio)
            throws Exception {

        WorkSession ws = genericSession.getWorkSession(key);
        ConvalidacionSolicitud conv = secreSession.getConvalidacion();
        String user = ActionUtil.getDBUser();

        HibernateUtil.beginTransaction(user);
        ContextUtil.getDAO().getConvalidacionSolicitudRepository(user).setEstado(conv.getCosCorrel(), "I");
        HibernateUtil.commitTransaction();

        AluCar aluCar = ws.getAluCar();
        Date fecha = getSysdate();
        Integer genera = genericSession.getRut();
        String type = genericSession.getUserType();

        Document doc = new Document(PageSize.LETTER);
        doc.setMargins(50.0f, 50.0f, 150.0f, 150.0f);
        doc.addTitle("Solicitud de Convalidaciones");
        doc.addAuthor("FAE-USACH");
        doc.addSubject(aluCar.getAlumno().getNombre());
        doc.addCreator("Intranet FAE: " + fecha);
        doc.addCreationDate();

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PdfWriter writer = getInstance(doc, buffer);

        SecretariaDocenteConvalidacionImprimirInformeService.HeaderFooterPageEvent hf = new SecretariaDocenteConvalidacionImprimirInformeService.HeaderFooterPageEvent(folio);
        writer.setPageEvent(hf);
        addProvider(new BouncyCastleProvider());

        doc.open();

        putHeader(doc, writer, aluCar);
        putAsignaturas(type, doc, aluCar, conv.getCosCorrel());
        putComision(type, doc, conv.getComision().getCcoCod());

        doc.close();
        CommonCertificacionUtil.writeFile(buffer, PATH_CERT + name);
        buffer.close();

        try {
            Files.createSymbolicLink(Paths.get(PATH_ATTACH_MESSAGES + name), Paths.get(PATH_CERT + name));
        } catch (Exception e) {
        }// No eleiminar este try/catch, evita el problema si el link ya existe            

        LogUtil.setLog(genera, aluCar.getId().getAcaRut());

        return CommonArchivoUtil.getFile(name, "cert");
    }

    private void putHeader(Document doc, PdfWriter writer, AluCar aluCar) {

        Alumno alumno = aluCar.getAlumno();

        float[] columnWidthsHeader = {100};
        PdfPTable header = new PdfPTable(1);
        header.setTotalWidth(512);
        header.setWidths(columnWidthsHeader);
        header.setLockedWidth(true);

        PdfPCell c1 = new PdfPCell(new Phrase("UNIVERSIDAD DE SANTIAGO DE CHILE", fontBig));
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.addCell(c1);

        PdfPCell c2 = new PdfPCell(new Phrase(aluCar.getAluCarFunction().getNombreFacultad().toUpperCase(ContextUtil.getLocale()), fontBig));
        c2.setBorder(Rectangle.NO_BORDER);
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.addCell(c2);

        PdfPCell emptyCell = new PdfPCell(new Phrase(" "));
        emptyCell.setBorder(0); // No mostrar borde de la celda vacía
        header.addCell(emptyCell);

        PdfPCell c4 = new PdfPCell(new Phrase("Solicitud de Convalidaciones", fontBig));
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

        PdfPCell tnombre = new PdfPCell(new Phrase("Nombre:", font));
        tnombre.setBorder(Rectangle.NO_BORDER);
        PdfPCell nombre = new PdfPCell(new Phrase(alumno.getNombre(), font));
        nombre.setBorder(Rectangle.NO_BORDER);

        PdfPCell tced = new PdfPCell(new Phrase("Cédula Nacional de Identidad N°:", font));
        tced.setBorder(Rectangle.NO_BORDER);
        PdfPCell ced = new PdfPCell(new Phrase(alumno.getAluRut().toString() + '-' + alumno.getAluDv(), font));
        ced.setBorder(Rectangle.NO_BORDER);

        PdfPCell tcarrera = new PdfPCell(new Phrase("Carrera:", font));
        tcarrera.setBorder(Rectangle.NO_BORDER);
        PdfPCell carrera = new PdfPCell(new Phrase(aluCar.getNombreCarrera(), font));
        carrera.setBorder(Rectangle.NO_BORDER);

        PdfPCell tplan = new PdfPCell(new Phrase("Plan de Estudios: Resolución(es):", font));
        tplan.setBorder(Rectangle.NO_BORDER);
        PdfPCell plan = new PdfPCell(new Phrase(aluCar.getPlan().getPlaResoluciones(), font));
        plan.setBorder(Rectangle.NO_BORDER);

        PdfPCell tingreso = new PdfPCell(new Phrase("Ingreso:", font));
        tingreso.setBorder(Rectangle.NO_BORDER);
        PdfPCell ingreso = new PdfPCell(new Phrase(aluCar.getId().getAcaAgnoIng().toString(), font));
        ingreso.setBorder(Rectangle.NO_BORDER);

        table.addCell(tnombre);
        table.addCell(nombre);
        table.addCell(tced);
        table.addCell(ced);
        table.addCell(tcarrera);
        table.addCell(carrera);
        table.addCell(tplan);
        table.addCell(plan);
        table.addCell(tingreso);
        table.addCell(ingreso);

        doc.add(table);
    }

    private void putAsignaturas(String user, Document doc, AluCar aluCar, Integer correl) {
        List<ConvalidacionSolicitudAsign> lAsign = ContextUtil.getDAO().getConvalidacionSolicitudAsignRepository(user).getPorConvalidar(aluCar, correl);
        PdfUtil.putBlank(doc);
        PdfPTable table = creaTabla();

        Color myColorGris = new Color(240, 240, 240);
        Color myColorBlanco = new Color(255, 255, 255);

        IntStream.range(0, lAsign.size())
                .forEach(i -> {
                    ConvalidacionSolicitudAsign conv = lAsign.get(i);
                    Color myColor = (i % 2 == 0) ? myColorBlanco : myColorGris;

                    table.addCell(getPdfPCell(conv.getAsignatura().getAsiCod().toString(), Element.ALIGN_RIGHT, myColor));
                    table.addCell(getPdfPCell(conv.getAsignatura().getAsiNom(), Element.ALIGN_LEFT, myColor));
                    table.addCell(getPdfPCell(conv.getCsaElectivo(), Element.ALIGN_LEFT, myColor));
                    table.addCell(getPdfPCell(conv.getCsaCursada(), Element.ALIGN_LEFT, myColor));
                    table.addCell(getPdfPCell(
                            conv.getCsaNota() == null ? "" : conv.getCsaNota().toString(),
                            Element.ALIGN_RIGHT, myColor));
                    table.addCell(getPdfPCell(
                            "C".equals(conv.getCsaEstado()) ? "Conv" : "Rechazada",
                            Element.ALIGN_RIGHT, myColor));
                    table.addCell(getPdfPCell(conv.getCsaObs(), Element.ALIGN_LEFT, myColor));
                });

        doc.add(table);
    }

    private void putComision(String user, Document doc, Integer correl) {
        List<ConvalidacionComisionProf> lProf = ContextUtil.getDAO().getConvalidacionComisionProfRepository(user).find(correl);

        Paragraph dummy = new Paragraph("\n\n");
        doc.add(dummy);
        Paragraph p = new Paragraph("COMISIÓN", font);
        PdfPCell cell = new PdfPCell(p);
        Color myColor = new Color(216, 216, 216);
        cell.setBackgroundColor(myColor);
        cell.setBorder(0);
        PdfPTable table = new PdfPTable(new float[]{100});
        table.setWidthPercentage(100);
        table.addCell(cell);
        doc.add(table);

        for (ConvalidacionComisionProf prof : lProf) {
            Paragraph pProf = new Paragraph(prof.getProfesor().getNombre(), font);
            doc.add(pProf);
        }
    }

    private PdfPCell getPdfPCell(String txt, int align, Color bgColor) {
        PdfPCell cell = new PdfPCell(new Phrase(txt, fontSmall));
        cell.setHorizontalAlignment(align);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setBackgroundColor(bgColor);
        return cell;
    }

    private PdfPTable creaTabla() {
        float[] columnWidths = {6, 25, 12, 10, 3, 7, 30};

        PdfPTable table = new PdfPTable(7);
        table.setTotalWidth(512);
        table.setWidths(columnWidths);
        table.setLockedWidth(true);

        Color myColor = new Color(204, 204, 204);

        PdfPCell asignCell = getPdfPCell("Asignatura", Element.ALIGN_LEFT, myColor);
        asignCell.setColspan(2);
        table.addCell(asignCell);
        table.addCell(getPdfPCell("Electivo", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("Cursada", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("Nota", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("Estado", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("Observaciones", Element.ALIGN_CENTER, myColor));

        table.setHeaderRows(1);

        return table;
    }

    private class HeaderFooterPageEvent extends PdfPageEventHelper {

        private PdfTemplate template;
        private Image total;
        private final Integer folio;

        public HeaderFooterPageEvent(Integer folio) {
            this.folio = folio;
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

            PdfPCell cDummy = new PdfPCell(new Paragraph(""));
            cDummy.setBorder(Rectangle.NO_BORDER);
            PdfPCell cFolio = new PdfPCell(new Paragraph("Folio:" + folio, fontMed));
            cFolio.setHorizontalAlignment(Element.ALIGN_LEFT);
            cFolio.setBorder(Rectangle.NO_BORDER);

            table.addCell(cDummy);
            table.addCell(cFolio);

            table.writeSelectedRows(0, -1, 34, 700, writer.getDirectContent());
        }

        private void addFooter(PdfWriter writer) {
            PdfPTable footer = new PdfPTable(3);
            try {
                float[] columnWidths = {90, 8, 2};
                footer.setWidths(columnWidths);
                footer.setTotalWidth(527);
                footer.setLockedWidth(true);

                PdfPCell cDummy = new PdfPCell(new Paragraph(""));
                cDummy.setBorder(Rectangle.NO_BORDER);
                footer.addCell(cDummy);

                footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                PdfPCell cPage = new PdfPCell(new Paragraph(String.format("Pág %d de", writer.getPageNumber()), font));
                cPage.setBorder(Rectangle.NO_BORDER);
                footer.addCell(cPage);

                PdfPCell totalPageCount = new PdfPCell(total);
                totalPageCount.setBorder(Rectangle.NO_BORDER);
                footer.addCell(totalPageCount);

                PdfContentByte canvas = writer.getDirectContent();
                PdfName customTag = new PdfName("CustomTag");
                canvas.beginMarkedContentSequence(customTag);

                footer.writeSelectedRows(0, -1, 0, 40, canvas);

                canvas.endMarkedContentSequence();
            } catch (DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }

        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {

            int totalLength = String.valueOf(writer.getPageNumber() - 1).length();
            int totalWidth = totalLength * 1;

            ColumnText.showTextAligned(template, Element.ALIGN_LEFT,
                    new Phrase(String.valueOf(writer.getPageNumber() - 1), font),
                    totalWidth, 5, 0);

        }
    }
}
