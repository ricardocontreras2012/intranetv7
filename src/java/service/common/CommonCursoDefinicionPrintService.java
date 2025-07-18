package service.common;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
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
import java.util.Arrays;
import org.apache.struts2.ServletActionContext;
import infrastructure.util.DateUtil;
import infrastructure.util.common.CommonCertificacionUtil;
import infrastructure.util.common.CommonPDFUtil;

/**
 * Servicio para generar un informe PDF con la definición de los cursos de una
 * carrera. El informe incluye información como código, nombre, profesor,
 * horarios, salas, cupos, y tipo de curso. También agrega un encabezado con
 * información relevante y un pie de página con la numeración de página.
 */
public class CommonCursoDefinicionPrintService {

    // Fuentes para diferentes tamaños de texto en el PDF.
    static Font fontBig = PdfUtil.getFont("times", 12.0f, Font.BOLD);
    static Font fontSmall = PdfUtil.getFont("tahoma", 7.0f, Font.NORMAL);
    static Font fontMed = PdfUtil.getFont("tahoma", 8.0f, Font.NORMAL);
    static Font font = PdfUtil.getFont("tahoma", 8.5f, Font.NORMAL);

    // Ruta al logo de la universidad.
    private static final String LOGO_PATH = ServletActionContext.getServletContext().getRealPath(SystemParametersUtil.UNIVERSITY_LOGO_PATH3);
   

    /**
     * Servicio para generar el archivo PDF con la definición de cursos de la
     * carrera.
     *
     * @param genericSession La sesión de trabajo.
     * @param key La clave para acceder a los datos específicos de la sesión.
     * @return Un objeto ActionInputStreamUtil que contiene el archivo PDF
     * generado.
     * @throws Exception Si ocurre algún error durante la generación del PDF.
     */
    public ActionInputStreamUtil service(GenericSession genericSession, String key) throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        String name = StringUtils.isEmpty(ws.getNombreCarrera()) ? "" : ws.getNombreCarrera();
        String file = name.replace(" ", "_") + "_" + ws.getSemAct() + "_" + ws.getAgnoAct() + ".pdf";
        String description = FormatUtil.getMimeType(file);

        // Devuelve el archivo PDF generado como un InputStream
        return new ActionInputStreamUtil(name, description, getInput(genericSession, ws, name, file));
    }

    /**
     * Genera el contenido del archivo PDF, creando una tabla con la definición
     * de los cursos.
     *
     * @param genericSession La sesión de trabajo.
     * @param ws La sesión de trabajo actual.
     * @param name El nombre de la carrera.
     * @param file El nombre del archivo PDF.
     * @return Un InputStream con el contenido del archivo PDF.
     * @throws Exception Si ocurre algún error durante la creación del PDF.
     */
    public InputStream getInput(GenericSession genericSession, WorkSession ws, String name, String file) throws Exception {

        Integer genera = genericSession.getRut();
        Image logo;
        // Cargar el logo de la universidad
        logo = Image.getInstance(LOGO_PATH);
        logo.scaleToFit(60, 60); // Ajustar tamaño del logo
        
        String fecha = DateUtil.getFormattedDate(DateUtil.getSysdate(), "dd/MM/yyyy hh:mm:ss");

        // Crear el documento PDF con tamaño Letter rotado y márgenes personalizados
        Document doc = new Document(PageSize.LETTER.rotate(), 50, 50, 150, 50);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(doc, buffer);

        // Crear evento para manejar encabezados y pies de página
        HeaderFooterPageEvent event = new HeaderFooterPageEvent(name, ws.getSemAct(), ws.getAgnoAct(), fecha, logo);
        writer.setPageEvent(event);

        doc.open();

        // Crear la tabla con 9 columnas para los datos de los cursos
        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100);
        float[] columnWidths = new float[]{10, 24, 18, 16, 8, 15, 3, 3, 3};
        table.setWidths(columnWidths);

        // Encabezados de la tabla
        String[] headers = {"Código", "Nombre", "Profesor", "Ayudante", "Horario", "Salas", "Ini", "Ins", "Tipo"};
        Arrays.stream(headers).forEach(headerText -> {
            PdfPCell cell = new PdfPCell(new Phrase(headerText, new Font(Font.NORMAL, 8)));
            cell.setBackgroundColor(Color.LIGHT_GRAY); // Color de fondo gris claro
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        });

        // Agregar filas de la tabla con los datos de los cursos
        ws.getCursoList().forEach(curso -> {
            table.addCell(createCell(curso.getId().getCodigoCorto(" "))); // Código
            table.addCell(createCell(curso.getCurNombre())); // Nombre
            table.addCell(createCell(curso.getCurProfesores())); // Profesor
            table.addCell(createCell(curso.getCurAyudantes())); // Ayudante
            table.addCell(createCell(curso.getCurHorario())); // Horario
            table.addCell(createCell(curso.getCurSalas())); // Salas
            table.addCell(createCell(curso.getCurCupoIni() != null ? String.valueOf(curso.getCurCupoIni()) : "")); // Cupo Inicial
            table.addCell(createCell(String.valueOf(curso.getCurCupoIni() - curso.getCurCupoDis()))); // Inscritos
            table.addCell(createCell(curso.getCurTipo())); // Tipo: Cerrado, Transversal y Espejo
        });

        doc.add(table);
        doc.close();

        // Guardar el archivo generado en la ruta temporal
        CommonCertificacionUtil.writeFile(buffer, PATH_TEMP_FILES + file);
        buffer.close();

        // Registrar el log con el generador y el nombre del archivo
        LogUtil.setLog(genera, name);

        // Devolver el archivo generado como un InputStream
        return CommonArchivoUtil.getFile(file, "tmp");
    }

    /**
     * Crea una celda en la tabla PDF con el contenido proporcionado.
     *
     * @param content El contenido de la celda.
     * @return Una celda de PDF con el contenido proporcionado.
     */
    private PdfPCell createCell(String content) {
        PdfPCell cell = new PdfPCell(new Phrase(content != null ? content : "", fontSmall));
        cell.setBorder(PdfPCell.NO_BORDER); // Sin borde
        return cell;
    }

    /**
     * Clase interna para manejar los encabezados y pies de página del documento
     * PDF.
     */
    private class HeaderFooterPageEvent extends PdfPageEventHelper {

        private final String name;
        private final Integer agno;
        private final Integer sem;
        private PdfTemplate template;
        private Image total;
        private final String fecha;
        private final Image logo;

        HeaderFooterPageEvent(String name, Integer sem, Integer agno, String fecha, Image logo) {
            this.name = name;
            this.sem = sem;
            this.agno = agno;
            this.fecha = fecha;
            this.logo = logo;
        }

        // Configurar el evento al abrir el documento
        @Override
        public void onOpenDocument(PdfWriter writer, Document document) {
            template = writer.getDirectContent().createTemplate(30, 15);
            try {
                total = Image.getInstance(template);
            } catch (DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }

        // Configurar los elementos al final de cada página
        @Override
        public void onEndPage(PdfWriter writer, Document document) {

            // Insertar logo en la página
            logo.setAbsolutePosition(document.leftMargin(), document.getPageSize().getHeight() - 90);
            writer.getDirectContent().addImage(logo);

            // Agregar texto centralizado en la parte superior
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
                    new Phrase("UNIVERSIDAD DE SANTIAGO DE CHILE", fontBig),
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.getPageSize().getHeight() - 50,
                    0);

            // Agregar fecha a la derecha
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT,
                    new Phrase(fecha, fontSmall),
                    document.right(),
                    document.getPageSize().getHeight() - 50,
                    0);

            // Agregar el nombre de la carrera en la parte superior
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
                    new Phrase("Carrera " + name, fontMed),
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.getPageSize().getHeight() - 70,
                    0);

            // Agregar el periodo (año y semestre)
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
                    new Phrase("Periodo: " + agno + "/" + sem, fontSmall),
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.getPageSize().getHeight() - 90,
                    0);

            // Agregar pie de página
            CommonPDFUtil.addFooter(writer, font, total, 742);
        }

        // Configurar el evento al cerrar el documento
        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {
            CommonPDFUtil.onCloseDocument(writer, document, template, font);
        }
    }
}
