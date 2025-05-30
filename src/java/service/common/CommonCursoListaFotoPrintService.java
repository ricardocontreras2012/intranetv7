package service.common;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import domain.model.AluCar;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.apache.commons.lang3.StringUtils;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.FormatUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.PdfUtil;
import infrastructure.util.SystemParametersUtil;
import static infrastructure.util.SystemParametersUtil.PATH_TEMP_FILES;
import infrastructure.util.common.CommonArchivoUtil;
import domain.model.Curso;
import org.apache.struts2.ServletActionContext;
import infrastructure.util.DateUtil;
import infrastructure.util.common.CommonCertificacionUtil;
import infrastructure.util.common.CommonUsersUtil;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class CommonCursoListaFotoPrintService {

    static Font fontBig = PdfUtil.getFont("times", 12.0f, Font.BOLD);
    static Font fontSmall = PdfUtil.getFont("tahoma", 7.0f, Font.NORMAL);
    static Font fontMed = PdfUtil.getFont("tahoma", 8.0f, Font.NORMAL);
    static Font font = PdfUtil.getFont("tahoma", 8.5f, Font.NORMAL);
    private static final String LOGO_PATH = ServletActionContext.getServletContext().getRealPath(SystemParametersUtil.UNIVERSITY_LOGO_PATH3);
    private static Image logo;

    public ActionInputStreamUtil service(GenericSession genericSession, String key) throws Exception {

        WorkSession ws = genericSession.getWorkSession(key);
        Curso curso = ws.getCurso();
        String name = StringUtils.isEmpty(ws.getNombreCarrera()) ? "" : ws.getNombreCarrera();
        String file = "NOMINA_FOTO_" + curso.getNombreFull().replace(" ", "_") + ".pdf";
        String description = FormatUtil.getMimeType(file);

        return new ActionInputStreamUtil(name, description, getInput(genericSession, ws, name, file, curso));
    }

    public InputStream getInput(GenericSession genericSession, WorkSession ws, String name, String file, Curso curso) throws Exception {

        Integer genera = genericSession.getRut();
        String fecha = DateUtil.getFormatedDate(DateUtil.getSysdate(), "dd/MM/yyyy hh:MM:ss");

        Document doc = new Document(PageSize.LETTER, 50, 50, 150, 50);// Establecer tamaño Letter
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(doc, buffer);
        HeaderFooterPageEvent event = new HeaderFooterPageEvent(fecha, curso); // Crear evento para manejar encabezados y pies
        writer.setPageEvent(event); // Registrar el evento

        // Cargar el logo
        logo = Image.getInstance(LOGO_PATH);
        logo.scaleToFit(60, 60); // Ajustar tamaño del logo

        doc.open();

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        float[] columnWidths = new float[]{4, 20, 30, 30, 50, 20};
        table.setWidths(columnWidths);

        String[] headers = {"", "RUN", "Paterno", "Materno", "Nombre", "Foto"};
        Color lightGray = new Color(230, 230, 230); // Gris claro
        Font headerFont = new Font(Font.NORMAL, 8);

        Stream.of(headers).map(headerText -> {
            PdfPCell cell = new PdfPCell(new Phrase(headerText, headerFont));
            cell.setBackgroundColor(lightGray);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            return cell;
        }).forEach(table::addCell);
        
        AtomicInteger index = new AtomicInteger(1);
        java.util.List<AluCar> nomina = curso.getNominaAlumnos();

        nomina.stream().forEachOrdered(aca -> {
            table.addCell(createCell(String.valueOf(index.getAndIncrement())));

            PdfPCell idCell = createCell(aca.getAlumno().getAluRut() + "-" + aca.getAlumno().getAluDv());
            idCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(idCell);

            table.addCell(createCell(aca.getAlumno().getAluPaterno()));
            table.addCell(createCell(aca.getAlumno().getAluMaterno()));
            table.addCell(createCell(aca.getAlumno().getAluNombre()));

            try {
                Image alumnoImage = Image.getInstance(CommonUsersUtil.getPathFoto(aca.getAlumno().getAluRut(), aca.getAlumno().getAluDv()));

                PdfPCell imageCell;
                if (alumnoImage != null) {
                    alumnoImage.scaleToFit(50, 50);
                    imageCell = new PdfPCell(alumnoImage);
                    imageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    imageCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    imageCell.setBorder(1);
                } else {
                    imageCell = createCell(""); // Si no hay imagen
                }

                table.addCell(imageCell);
            } catch (Exception e) {
                table.addCell(createCell("")); // Si hay error cargando la imagen
            }
        });

        doc.add(table);
        doc.close();
        CommonCertificacionUtil.writeFile(buffer, PATH_TEMP_FILES + file);
        buffer.close();

        LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());
        return CommonArchivoUtil.getFile(file, "tmp");
    }

    private PdfPCell createCell(String content) {
        PdfPCell cell = new PdfPCell(new Phrase(content != null ? content : "", fontSmall));
        cell.setBorder(1);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

    private class HeaderFooterPageEvent extends PdfPageEventHelper {

        private PdfTemplate template;
        private Image total;
        private final String fecha;
        private final Curso curso;

        HeaderFooterPageEvent(String fecha, Curso curso) {
            this.fecha = fecha;
            this.curso = curso;
        }

        @Override
        public void onOpenDocument(PdfWriter writer, Document document) {
            // Crear el template para el número total de páginas
            template = writer.getDirectContent().createTemplate(30, 15);
            try {
                total = Image.getInstance(template);
            } catch (DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            logo.setAbsolutePosition(document.leftMargin(), document.getPageSize().getHeight() - 90);
            writer.getDirectContent().addImage(logo);

            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
                    new Phrase("UNIVERSIDAD DE SANTIAGO DE CHILE", fontBig),
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.getPageSize().getHeight() - 50,
                    0);

            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT,
                    new Phrase(fecha, fontSmall),
                    document.right(),
                    document.getPageSize().getHeight() - 50,
                    0);

            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
                    new Phrase("CURSO " + curso.getNombreFull(), fontBig),
                    document.leftMargin(),
                    document.getPageSize().getHeight() - 110,
                    0);

            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
                    new Phrase("PROFESOR: " + curso.getCurProfesores(), fontBig),
                    document.leftMargin(),
                    document.getPageSize().getHeight() - 130,
                    0);

            PdfPTable footer = new PdfPTable(3);

            float[] columnWidths = {90f, 8f, 2f};
            footer.setWidths(columnWidths);
            footer.setTotalWidth(562);
            footer.setLockedWidth(true);

            PdfPCell cDummy = new PdfPCell(new Paragraph("", font));
            cDummy.setBorder(Rectangle.NO_BORDER);
            footer.addCell(cDummy);

            PdfPCell cPage = new PdfPCell(new Paragraph(String.format("Pág %d de", writer.getPageNumber()), fontSmall));
            cPage.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cPage.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cPage.setPaddingBottom(4);
            cPage.setBorder(Rectangle.NO_BORDER);
            cPage.setPaddingRight(0);
            footer.addCell(cPage);

            PdfPCell totalPageCount = new PdfPCell(total);
            totalPageCount.setBorder(Rectangle.NO_BORDER);
            totalPageCount.setHorizontalAlignment(Element.ALIGN_LEFT);
            totalPageCount.setVerticalAlignment(Element.ALIGN_MIDDLE);
            footer.addCell(totalPageCount);

            PdfContentByte canvas = writer.getDirectContent();
            footer.writeSelectedRows(0, -1, 0, 40, canvas);
        }

        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {
            int totalLength = String.valueOf(writer.getPageNumber() - 1).length();
            int totalWidth = totalLength * 1;

            ColumnText.showTextAligned(template, Element.ALIGN_LEFT,
                    new Phrase(String.valueOf(writer.getPageNumber() - 1), fontSmall),
                    totalWidth, 5, 0);
        }
    }
}
