/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.oficinacurricular;


import java.util.Map;
import session.GenericSession;

/**
 *
 * @author Ricardo
 */
public class OficinaCurricularActaPrintActasService {

    //static PdfFont font;

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param parameters
     * @param key LLave para aceder a los datos de la sesion.
     * @throws java.lang.Exception
     */
    public void service(GenericSession genericSession, Map<String, String[]> parameters, String key) throws Exception {}

        /*WorkSession ws = genericSession.getWorkSession(key);

        Date fecha = getSysdate();

        for (int i = 0; i < ws.getActas().size(); i++) {
            if (parameters.get("ck_" + i) != null) {
                generarActa(ws.getActas().get(i), fecha);
            }
        }

        String name_tmp = RandomStringUtils.randomAlphanumeric(64) + ".pdf";
        ws.setPdfTempFile(name_tmp);

        PdfDocument pdf = new PdfDocument(new PdfWriter(SystemParametersUtil.PATH_TEMP_FILES + name_tmp));
        PdfMerger merger = new PdfMerger(pdf);
        for (int i = 0; i < ws.getActas().size(); i++) {
            if (parameters.get("ck_" + i) != null) {
                PdfDocument sourcePdf = new PdfDocument(new PdfReader(SystemParametersUtil.PATH_TEMP_FILES + getName(ws.getActas().get(i))));
                merger.merge(sourcePdf, 1, sourcePdf.getNumberOfPages());
                sourcePdf.close();
            }
        }
        pdf.close();

    }

    private void generarActa(ActaCalificacion acta, Date fecha) {
        Unidad unidad = ContextUtil.getDAO().getUnidadPersistence(ActionUtil.getDBUser()).find(100, acta.getCurso().getAsignatura().getAsiCod());

        String user = ActionUtil.getDBUser();
        String name = getName(acta);
        deleteFile(PATH_TEMP_FILES + name);
        deleteFile(PATH_ACTAS + name);

        try {
            font = CommonCertificacionUtil.getFont("tahoma");
            PdfADocument pdfDoc = CommonCertificacionUtil.getPdfDoc(PATH_TEMP_FILES + name);             
            Document doc = new com.itextpdf.layout.Document(pdfDoc, com.itextpdf.kernel.geom.PageSize.LETTER).setFont(font);
            doc.setMargins(170.0f, 50.0f, 200.0f, 50.0f);

            PdfDocumentInfo info = pdfDoc.getDocumentInfo();
            info
                    .setTitle("Acta de Calificaciones")
                    .setAuthor("FAE-USACH")
                    .setSubject("Acta de Calificaciones")
                    .setCreator("Intranet FAE: " + fecha)
                    .setKeywords("PDF")
                    .addCreationDate();

            String header = unidad.getUniNom().toUpperCase(ContextUtil.getLocale());

            Header headerHandler = new Header(header);
            pdfDoc.addEventHandler(PdfDocumentEvent.START_PAGE, headerHandler);

            PageXofY footerHandler = new PageXofY(pdfDoc);
            pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, footerHandler);

            putHeader(doc, acta);
            printActa(doc, acta);

            footerHandler.writeTotal(pdfDoc);
            doc.close();
            putSecurity(name);

            if ("E".equals(acta.getEstadoActa().getEacCod())) {
                HibernateUtil.beginTransaction(user);
                ContextUtil.getDAO().getActaCalificacionPersistence(user).putActaEstado(acta.getId().getAcalFolio(), acta.getId().getAcalAgno(), acta.getId().getAcalSem(), "I");
                HibernateUtil.commitTransaction();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void putHeader(Document doc, ActaCalificacion acta) throws Exception {

        Paragraph p1 = new Paragraph("Folio " + acta.getId().getAcalFolio());
        p1.setFixedPosition(0, 630, 560).setTextAlignment(TextAlignment.RIGHT).setFontSize(9);
        doc.add(p1);

        float[] columnWidths = {30, 70};
        Table table = new Table(UnitValue.createPercentArray(columnWidths)).setWidth(512);

        Cell tnombre = new Cell()
                .add(new Paragraph("CURSO").setBold())
                .setFont(font).setFontSize(9).setTextAlignment(TextAlignment.LEFT)
                .setBorder(Border.NO_BORDER).setHeight(14).setPadding(0);
        Cell nombre = new Cell()
                .add(new Paragraph(acta.getCurso().getNombreCorto()))
                .setFont(font).setFontSize(9).setTextAlignment(TextAlignment.LEFT)
                .setBorder(Border.NO_BORDER).setHeight(14).setPadding(0);
        Cell tprofesor = new Cell()
                .add(new Paragraph("PROFESOR").setBold())
                .setFont(font).setFontSize(9).setTextAlignment(TextAlignment.LEFT)
                .setBorder(Border.NO_BORDER).setHeight(14).setPadding(0);
        Cell profesor = new Cell()
                .add(new Paragraph(acta.getCurso().getCurProfesores()))
                .setFont(font).setFontSize(9).setTextAlignment(TextAlignment.LEFT)
                .setBorder(Border.NO_BORDER).setHeight(14).setPadding(0);

        table.addCell(tnombre).addCell(nombre);
        table.addCell(tprofesor).addCell(profesor);

        doc.add(table);
    }

    private static Table creaTabla() {
        float[] columnWidths = {8, 2, 20, 20, 30, 10, 10};
        Table table = new Table(UnitValue.createPercentArray(columnWidths)).setWidth(512);
        Color myColor = new DeviceRgb(204, 204, 204);

        Cell h1 = new Cell()
                .add(new Paragraph("RUT"))
                .setFont(font)
                .setFontSize(10)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(myColor)
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(Border.NO_BORDER);

        Cell h2 = new Cell()
                .add(new Paragraph("DV"))
                .setFont(font)
                .setFontSize(10)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(myColor)
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(Border.NO_BORDER);

        Cell h3 = new Cell()
                .add(new Paragraph("PATERNO"))
                .setFont(font)
                .setFontSize(10)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(myColor)
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(Border.NO_BORDER);

        Cell h4 = new Cell()
                .add(new Paragraph("MATERNO"))
                .setFont(font)
                .setFontSize(10)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(myColor)
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(Border.NO_BORDER);

        Cell h5 = new Cell()
                .add(new Paragraph("NOMBRES"))
                .setFont(font)
                .setFontSize(10)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(myColor)
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(Border.NO_BORDER);

        Cell h6 = new Cell()
                .add(new Paragraph("CARRERA"))
                .setFont(font)
                .setFontSize(10)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(myColor)
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(Border.NO_BORDER);

        Cell h7 = new Cell()
                .add(new Paragraph("NOTA"))
                .setFont(font)
                .setFontSize(10)
                .setFontColor(DeviceGray.BLACK)
                .setBackgroundColor(myColor)
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(Border.NO_BORDER);
        table.addHeaderCell(h1).addHeaderCell(h2).addHeaderCell(h3).addHeaderCell(h4).addHeaderCell(h5).addHeaderCell(h6).addHeaderCell(h7);

        return table;
    }

    private void printActa(Document doc, ActaCalificacion acta) {
        Table tabla = creaTabla();
        Boolean par = false;
        Color myColorGris = new DeviceRgb(240, 240, 240);
        Color myColorBlanco = new DeviceRgb(255, 255, 255);
        Color myColor;

        List<ActaCalificacionNomina> nomina = ContextUtil.getDAO().getActaCalificacionNominaPersistence(ActionUtil.getDBUser()).findCalificaciones(acta.getId().getAcalFolio(), acta.getId().getAcalAgno(), acta.getId().getAcalSem());

        for (ActaCalificacionNomina alumno : nomina) {
            if (par) {
                myColor = myColorGris;
            } else {
                myColor = myColorBlanco;
            }
            par = !par;

            Cell rut = new Cell()
                    .add(new Paragraph(alumno.getAluCar().getAlumno().getAluRut().toString()))
                    .setFont(font).setFontSize(9).setTextAlignment(TextAlignment.LEFT)
                    .setBorder(Border.NO_BORDER).setBackgroundColor(myColor).setHeight(13).setPadding(0);

            Cell dv = new Cell()
                    .add(new Paragraph(alumno.getAluCar().getAlumno().getAluDv()))
                    .setFont(font).setFontSize(9).setTextAlignment(TextAlignment.CENTER)
                    .setBorder(Border.NO_BORDER).setBackgroundColor(myColor).setHeight(13).setPadding(0);

            Cell paterno = new Cell()
                    .add(new Paragraph(alumno.getAluCar().getAlumno().getAluPaterno()))
                    .setFont(font).setFontSize(9).setTextAlignment(TextAlignment.LEFT)
                    .setBorder(Border.NO_BORDER).setBackgroundColor(myColor).setHeight(13).setPadding(0);

            Cell materno = new Cell()
                    .add(new Paragraph(alumno.getAluCar().getAlumno().getAluMaterno()))
                    .setFont(font).setFontSize(9).setTextAlignment(TextAlignment.LEFT)
                    .setBorder(Border.NO_BORDER).setBackgroundColor(myColor).setHeight(13).setPadding(0);

            Cell nombre = new Cell()
                    .add(new Paragraph(alumno.getAluCar().getAlumno().getAluNombre()))
                    .setFont(font).setFontSize(9).setTextAlignment(TextAlignment.LEFT)
                    .setBorder(Border.NO_BORDER).setBackgroundColor(myColor).setHeight(13).setPadding(0);

            Cell carrera = new Cell()
                    .add(new Paragraph(alumno.getAluCar().getId().getAcaCodCar().toString()))
                    .setFont(font).setFontSize(9).setTextAlignment(TextAlignment.LEFT)
                    .setBorder(Border.NO_BORDER).setBackgroundColor(myColor).setHeight(13).setPadding(0);

            Cell nota = new Cell()
                    .add(new Paragraph(alumno.getNota()))
                    .setFont(font).setFontSize(9).setTextAlignment(TextAlignment.CENTER).
                    setBorder(Border.NO_BORDER).setBackgroundColor(myColor).setHeight(13).setPadding(0);

            tabla.addCell(rut).addCell(dv).addCell(paterno).addCell(materno).addCell(nombre).addCell(carrera).addCell(nota);
        }
        doc.add(tabla);
    }

    public void putBold(Paragraph p) {
        p.setTextRenderingMode(PdfCanvasConstants.TextRenderingMode.FILL_STROKE)
                .setStrokeWidth(0.25f)
                .setStrokeColor(DeviceGray.BLACK);
    }

    private static void putSecurity(String name) throws Exception {
        SignUtil.signPdf(PATH_TEMP_FILES + name, PATH_ACTAS + name, "Acta de Calificaciones");
    }

    protected class PageXofY implements IEventHandler {

        protected PdfFormXObject placeholder;
        protected float side = 20;
        protected float x = 300;
        protected float y = 25;
        protected float space = 4.5f;
        protected float descent = 3;

        public PageXofY(PdfDocument pdf) {
            placeholder
                    = new PdfFormXObject(new Rectangle(0, 0, side, side));
        }

        @SuppressWarnings("deprecation")
        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdf = docEvent.getDocument();

            PdfPage page = docEvent.getPage();
            int pageNumber = pdf.getPageNumber(page);
            Rectangle pageSize = page.getPageSize();
            PdfCanvas pdfCanvas = new PdfCanvas(
                    page.getLastContentStream(), page.getResources(), pdf);
            Canvas canvas = new Canvas(pdfCanvas, pageSize);
            canvas.setFont(font);
            Paragraph p = new Paragraph()
                    .add("Pág ").add(String.valueOf(pageNumber)).add(" de").setFontSize(9);
            canvas.showTextAligned(p, x, y, TextAlignment.RIGHT);
            ////
            pdfCanvas.addXObject(placeholder, x + space, y - descent);
            pdfCanvas.release();
        }

        public void writeTotal(PdfDocument pdf) {
            Canvas canvas = new Canvas(placeholder, pdf);
            canvas.setFont(font).setFontSize(9);
            canvas.showTextAligned(String.valueOf(pdf.getNumberOfPages()),
                    0, descent, TextAlignment.LEFT);
        }
    }

    protected class Header implements IEventHandler {

        String facultad;

        public Header(String facultad) {
            this.facultad = facultad;
        }

        @SuppressWarnings("deprecation")
        @Override
        public void handleEvent(Event event) {

            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdf = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            Rectangle pageSize = page.getPageSize();
            PdfCanvas pdfCanvas = new PdfCanvas(
                    page.getLastContentStream(), page.getResources(), pdf);
            Canvas canvas = new Canvas(pdfCanvas,pageSize);
            canvas.setFont(font).setFontSize(14f);

            canvas.showTextAligned("UNIVERSIDAD DE SANTIAGO DE CHILE",
                    pageSize.getWidth() / 2,
                    pageSize.getTop() - 70, TextAlignment.CENTER);

            canvas.showTextAligned(facultad,
                    pageSize.getWidth() / 2,
                    pageSize.getTop() - 90, TextAlignment.CENTER);

            canvas.showTextAligned("ACTA DE CALIFICACIONES",
                    pageSize.getWidth() / 2,
                    pageSize.getTop() - 140, TextAlignment.CENTER);

            try {
                Image qr = new Image(ImageDataFactory.create(ServletActionContext.getServletContext().getRealPath(SystemParametersUtil.UNIVERSITY_LOGO_BN_PATH)));
                canvas.add(qr.setRelativePosition(100, 20, 0, 0));
            } catch (Exception e) {
            }
        }

    }

    private String getName(ActaCalificacion acta) {
        return acta.getId().getAcalFolio() + "_" + acta.getId().getAcalAgno() + "_" + acta.getId().getAcalSem() + ".pdf";
    }

*/
  
}
