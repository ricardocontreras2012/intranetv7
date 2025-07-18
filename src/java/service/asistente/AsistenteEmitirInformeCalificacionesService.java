/*
 * @(#)AsistenteEmitirInformeCalificacionesService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.asistente;

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
import domain.model.Unidad;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.apache.struts2.ServletActionContext.getServletContext;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.DateUtil;
import static infrastructure.util.DateUtil.getSysdate;
import infrastructure.util.FormatUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.PdfUtil;
import infrastructure.util.SystemParametersUtil;
import static infrastructure.util.SystemParametersUtil.I3;
import static infrastructure.util.SystemParametersUtil.PATH_CERT;
import infrastructure.util.common.CommonArchivoUtil;
import infrastructure.util.common.CommonCertificacionUtil;
import domain.model.CartolaView;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author Felipe
 */
public final class AsistenteEmitirInformeCalificacionesService {

    static Font fontBig = PdfUtil.getFont("tahoma", 12.0f, NORMAL);
    static Font fontSmall = PdfUtil.getFont("tahoma", 7.0f, NORMAL);
    static Font font = PdfUtil.getFont("tahoma", 8.5f, NORMAL);

    /**
     * Method description
     *
     * @param genericSession
     * @param key
     * @return
     */
    public ActionInputStreamUtil service(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();

        Integer folio = CommonCertificacionUtil.getFolio();
        String name = CommonCertificacionUtil.getNameFile(aluCar, folio, I3);

        String description = FormatUtil.getMimeType(name);
        return new ActionInputStreamUtil(name, description, getInput(name, genericSession, key));
    }

    public InputStream getInput(String name, GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();
        Unidad facultad = aluCar.getUnidadFacultad();

        Date fecha = getSysdate();

        try {
            Document doc = new Document(PageSize.LETTER);
            doc.setMargins(50.0f, 50.0f, 150.0f, 150.0f);
            doc.addTitle("Informe de Calificaciones");
            doc.addAuthor("FAE-USACH");
            doc.addSubject(aluCar.getAlumno().getNombre());
            doc.addCreator("Intranet FAE: " + fecha);
            doc.addCreationDate();

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            PdfWriter writer = getInstance(doc, buffer);

            AsistenteEmitirInformeCalificacionesService.HeaderFooterPageEvent hf = new AsistenteEmitirInformeCalificacionesService.HeaderFooterPageEvent();
            writer.setPageEvent(hf);
            //addProvider(new BouncyCastleProvider());

            doc.open();

            putHeader(doc, writer, aluCar, facultad.getUniNom());
            putCalificacionesPrimer(doc, aluCar);
            putCalificacionesSegundo(doc, aluCar);
            putFooter(doc, fecha);
            doc.close();
            CommonCertificacionUtil.writeFile(buffer, PATH_CERT + name);
            buffer.close();

            LogUtil.setLog(genericSession.getRut(), aluCar.getId().getAcaRut());

            return CommonArchivoUtil.getFile(name, "cert");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void putHeader(Document doc, PdfWriter writer, AluCar aluCar, String facultad) {

        Alumno alumno = aluCar.getAlumno();
        Integer agno = aluCar.getCartolaView().get(0).getId().getCartAgno();
        float[] columnWidthsHeader = {100};
        PdfPTable header = new PdfPTable(1);
        header.setTotalWidth(512);
        header.setWidths(columnWidthsHeader);
        header.setLockedWidth(true);

        PdfPCell c1 = new PdfPCell(new Phrase("UNIVERSIDAD DE SANTIAGO DE CHILE", fontBig));
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.addCell(c1);

        PdfPCell c2 = new PdfPCell(new Phrase(facultad.toUpperCase(ContextUtil.getLocale()), fontBig));
        c2.setBorder(Rectangle.NO_BORDER);
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.addCell(c2);

        PdfPCell c3 = new PdfPCell(new Phrase("BIENESTAR ESTUDIANTIL", fontBig));
        c3.setBorder(Rectangle.NO_BORDER);
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.addCell(c3);

        PdfPCell c4 = new PdfPCell(new Phrase("INFORME DE CALIFICACIONES AÑO " + agno, fontBig));
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

        PdfPCell tcodcarrera = new PdfPCell(new Phrase("Código Carrera:", font));
        tcodcarrera.setBorder(Rectangle.NO_BORDER);
        PdfPCell plan = new PdfPCell(new Phrase(aluCar.getId().getAcaCodCar().toString(), font));
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
        table.addCell(tcodcarrera);
        table.addCell(plan);
        table.addCell(tingreso);
        table.addCell(ingreso);

        doc.add(table);
    }

    private void putCalificacionesPrimer(Document doc, AluCar aluCar) {
        List<CartolaView> cartola = aluCar.getCartolaView();
        List<CartolaView> cartolaPrimer = new ArrayList<>();
        cartolaPrimer = setSemestre(cartola, cartolaPrimer, 1);
        if (!cartolaPrimer.isEmpty()) {
            Paragraph texto = new Paragraph("PRIMER SEMESTRE");
            doc.add(texto);
            putLista(doc, cartolaPrimer);
        }

    }

    private void putCalificacionesSegundo(Document doc, AluCar aluCar) {
        List<CartolaView> cartola = aluCar.getCartolaView();
        List<CartolaView> cartolaSegundo = new ArrayList<>();
        cartolaSegundo = setSemestre(cartola, cartolaSegundo, 2);
        if (!cartolaSegundo.isEmpty()) {
            PdfUtil.putBlank(doc);
            Paragraph texto = new Paragraph("SEGUNDO SEMESTRE");
            doc.add(texto);
            putLista(doc, cartolaSegundo);
        }
    }

    private void putFooter(Document doc, Date fecha) throws Exception {
        float[] columnWidths = {60};
        PdfPTable table = new PdfPTable(1);
        table.setTotalWidth(512);
        table.setWidths(columnWidths);

        PdfPCell c1 = new PdfPCell(new Paragraph("Documento emitido el " + DateUtil.getFechaEnPalabras(fecha), font));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setBorder(Rectangle.NO_BORDER);

        table.addCell(c1);
        PdfUtil.putBlank(doc);
        PdfUtil.putBlank(doc);
        doc.add(table);
    }

    private PdfPCell getPdfPCell(String txt, int align, Color bgColor) {
        PdfPCell cell = new PdfPCell(new Phrase(txt, font));
        cell.setHorizontalAlignment(align);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setBackgroundColor(bgColor);
        return cell;
    }

    private PdfPTable creaTabla() {
        float[] columnWidths = {18, 70, 8, 14, 14, 16};

        PdfPTable table = new PdfPTable(6);
        table.setTotalWidth(512);
        table.setWidths(columnWidths);
        table.setLockedWidth(true);

        Color myColor = new Color(204, 204, 204);

        table.addCell(getPdfPCell("CURSO", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("ASIGNATURA", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("NOTA", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("SEM/AÑO", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("SITUACION", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("PROC", Element.ALIGN_CENTER, myColor));

        table.setHeaderRows(1);

        return table;
    }
   
    private void putLista(Document doc, List<CartolaView> lista) {
        PdfUtil.putBlank(doc);

        PdfPTable table = creaTabla();
        Color myColorGris = new Color(240, 240, 240);
        Color myColorBlanco = new Color(255, 255, 255);

        // Procesar cada elemento de la lista y añadirlo a la tabla
        IntStream.range(0, lista.size()).forEach(i -> {
            CartolaView cal = lista.get(i);

            // Determinar color de fondo alternando por índice
            Color myColor = (i % 2 == 0) ? myColorBlanco : myColorGris;

            // Añadir celdas a la tabla
            table.addCell(getPdfPCell(
                    cal.getId().getCartAsign() + " " + cal.getId().getCartElect() + "-"
                    + cal.getCartCoord() + "-" + cal.getCartSecc(),
                    Element.ALIGN_LEFT, myColor
            ));
            table.addCell(getPdfPCell(cal.getCartNombreCompleto(), Element.ALIGN_CENTER, myColor));
            table.addCell(getPdfPCell(cal.getCartNotaFin(), Element.ALIGN_CENTER, myColor));
            table.addCell(getPdfPCell(
                    cal.getId().getCartSem() + "/" + cal.getId().getCartAgno(),
                    Element.ALIGN_CENTER, myColor
            ));
            table.addCell(getPdfPCell(cal.getCartSitAlu(), Element.ALIGN_CENTER, myColor));
            table.addCell(getPdfPCell(cal.getCartProc(), Element.ALIGN_CENTER, myColor));
        });

        // Añadir celdas vacías (si es necesario)
        IntStream.range(0, 6).forEach(i -> {
            PdfPCell emptyCell = new PdfPCell(new Phrase("", font));
            emptyCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(emptyCell);
        });

        // Pie de la tabla con el promedio
        PdfPCell foot = new PdfPCell(new Paragraph("PROMEDIO SEMESTRAL = " + setPromedio(lista), fontSmall));
        foot.setColspan(6);
        foot.setHorizontalAlignment(Element.ALIGN_CENTER);
        foot.setBackgroundColor(new Color(204, 204, 204));
        foot.setBorder(Rectangle.NO_BORDER);
        table.addCell(foot);

        try {
            doc.add(table);
        } catch (DocumentException e) {
            throw new RuntimeException("Error adding table to document", e);
        }
    }

    private List<CartolaView> setSemestre(List<CartolaView> lista, List<CartolaView> newLista, Integer sem) {
        newLista.addAll(
                lista.stream()
                        .filter(cartola -> sem.equals(cartola.getId().getCartSem()))
                        .collect(Collectors.toList())
        );
        return newLista;
    }

    private String setPromedio(List<CartolaView> lista) {
        DecimalFormat df = new DecimalFormat("#.#");

        double promedio = lista.stream()
                .map(CartolaView::getCartNotaFin) // Obtiene las notas
                .filter(nota -> nota != null) // Filtra valores nulos
                .mapToDouble(Double::parseDouble) // Convierte a double
                .average() // Calcula el promedio
                .orElse(0.0);                    // Valor por defecto si no hay datos

        return df.format(promedio);
    }

    private class HeaderFooterPageEvent extends PdfPageEventHelper {

        private PdfTemplate template;
        private Image total;

        public HeaderFooterPageEvent() {
        }

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

            table.addCell(cDummy);
            table.addCell(cDummy);

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
