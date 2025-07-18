/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import domain.model.Curso;
import domain.model.ReporteClase;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.FormatUtil;
import static infrastructure.util.SystemParametersUtil.PATH_TEMP_FILES;
import infrastructure.util.common.CommonExcelUtil;
import static infrastructure.util.common.CommonFacultadUtil.getNombrexAsign;
import infrastructure.util.common.CommonSequenceUtil;

/**
 *
 * @author Usach
 */
public class CommonReporteExportService {

    //public InputStream service(GenericSession genericSession, String content, String key) throws Exception {
    public ActionInputStreamUtil service(GenericSession genericSession, Map<String, String[]> parameters, String key) throws Exception {

        WorkSession ws = genericSession.getWorkSession(key);

        // Genera el nombre del archivo
        String fileName = "REPORTES_DE_CLASES_" + ws.getCurso().getNombreNormalizado() + "_" + CommonSequenceUtil.getDocumentSeq() + ".xlsx";

        // Obtiene el flujo de entrada con el archivo Excel
        InputStream input = getInput(fileName, ws, parameters);

        // Obtiene la descripción MIME del archivo
        String description = FormatUtil.getMimeType(fileName);

        return new ActionInputStreamUtil(fileName, description, input);
    }

    /**
     * Crea el archivo Excel de definición de cursos.
     *
     * @param genericSession La sesión general que contiene la información de
     * trabajo.
     * @param file El nombre del archivo a generar.
     * @param key La llave para acceder a los datos de la sesión.
     * @return El flujo de entrada del archivo Excel generado.
     * @throws Exception Si ocurre algún error al generar el archivo.
     */
    private InputStream getInput(String file, WorkSession ws, Map<String, String[]> parameters) throws Exception {
        Curso curso = ws.getCursoList().get(0); // Solo el primer curso

        // Crea un nuevo libro de trabajo Excel
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("hoja");

        // Define el ancho de las columnas
        setColumnWidths(sheet);

        // Inserta el logo en el archivo Excel
        CommonExcelUtil.putLogo(workbook, sheet);

        // Configura los estilos de las celdas
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 9);
        XSSFCellStyle titleStyle = createCellStyle(workbook, font);

        // Estilos de celdas para el contenido
        XSSFCellStyle headerStyle = createCellStyle(workbook, font);
        
        // Inserta la cabecera con el nombre de la facultad
        CommonExcelUtil.putCabeceraFacultad(workbook, sheet, font, headerStyle, headerStyle, workbook.getStylesSource().getIndexedColors(), getNombrexAsign(curso.getId().getCurAsign()));

        // Inserta el título del reporte
        int rowNum = 5;
        insertTitleRow(sheet, rowNum++, titleStyle, ws);

        rowNum = 8;
        // Inserta las cabeceras de las columnas
        insertHeaders(sheet, rowNum++, headerStyle);

        // Inserta los datos de los cursos en el archivo Excel
        insertReportsData(sheet, parameters, ws, rowNum);

        // Genera el archivo Excel y lo guarda en un flujo de entrada
        return saveWorkbookToInputStream(workbook, file);
    }

    /**
     * Configura el ancho de las columnas en la hoja de trabajo.
     *
     * @param sheet La hoja de trabajo Excel.
     */
    private void setColumnWidths(XSSFSheet sheet) {
        sheet.setColumnWidth(0, CommonExcelUtil.calculateColWidth(10));
        sheet.setColumnWidth(1, CommonExcelUtil.calculateColWidth(5));
        sheet.setColumnWidth(2, CommonExcelUtil.calculateColWidth(20));
        sheet.setColumnWidth(3, CommonExcelUtil.calculateColWidth(50));
        sheet.setColumnWidth(4, CommonExcelUtil.calculateColWidth(50));
        sheet.setColumnWidth(5, CommonExcelUtil.calculateColWidth(50));
        sheet.setColumnWidth(6, CommonExcelUtil.calculateColWidth(50));
    }

    /**
     * Crea un estilo de celda con fuente y alineación opcionales.
     *
     * @param workbook El libro de trabajo Excel.
     * @param font La fuente a utilizar en el estilo.
     * @param alignment La alineación de la celda (opcional).
     * @return El estilo de la celda configurado.
     */
    private XSSFCellStyle createCellStyle(XSSFWorkbook workbook, XSSFFont font, HorizontalAlignment alignment) {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        if (alignment != null) {
            style.setAlignment(alignment);
        }
        return style;
    }

    /**
     * Crea un estilo de celda con fuente predeterminada.
     *
     * @param workbook El libro de trabajo Excel.
     * @param font La fuente a utilizar en el estilo.
     * @return El estilo de la celda configurado.
     */
    private XSSFCellStyle createCellStyle(XSSFWorkbook workbook, XSSFFont font) {
        return createCellStyle(workbook, font, null);
    }

    /**
     * Inserta la fila de título en el archivo Excel.
     *
     * @param sheet La hoja de trabajo Excel.
     * @param rowNum El número de fila donde insertar el título.
     * @param titleStyle El estilo de la celda para el título.
     * @param ws La sesión de trabajo con los datos actuales.
     */
    private void insertTitleRow(XSSFSheet sheet, int rowNum, XSSFCellStyle titleStyle, WorkSession ws) {
        XSSFRow row = sheet.createRow(rowNum);
        XSSFCell cell = row.createCell(3);
        XSSFRichTextString title = new XSSFRichTextString("REPORTES DE CLASES: " + ws.getCurso().getNombreFull());
        cell.setCellValue(title);
        cell.setCellStyle(titleStyle);
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 3, 7));
        rowNum++;
        row = sheet.createRow(rowNum);
        cell = row.createCell(3);
        title = new XSSFRichTextString("PROFESOR: " + ws.getCurso().getCurProfesores());
        cell.setCellValue(title);
        cell.setCellStyle(titleStyle);
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 3, 7));
    }

    /**
     * Inserta las cabeceras de las columnas en el archivo Excel.
     *
     * @param sheet La hoja de trabajo Excel.
     * @param rowNum El número de fila donde insertar las cabeceras.
     * @param headerStyle El estilo de la celda para las cabeceras.
     */
    private void insertHeaders(XSSFSheet sheet, int rowNum, XSSFCellStyle headerStyle) {
        XSSFRow headerRow = sheet.createRow(rowNum);

        // Define las cabeceras
        String[] headers = {"", "SESIÓN", "FECHA", "OBJETIVOS", "CONTENIDOS", "MÉTODO", "OBSERVACIONES"};

        // Crea las celdas de las cabeceras utilizando Streams
        IntStream.range(1, headers.length).forEach(i -> {
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(new XSSFRichTextString(headers[i]));
            cell.setCellStyle(headerStyle);
        });
    }

    /**
     * Inserta los datos de los cursos en el archivo Excel.
     *
     * @param sheet La hoja de trabajo Excel.
     * @param cursoList La lista de cursos a insertar.
     * @param rowNum El número de fila donde insertar los datos.
     * @param horizontalCenterStyle El estilo de la celda con alineación
     * central.
     */
    private void insertReportsData(XSSFSheet sheet, Map<String, String[]> parameters, WorkSession ws, int rowNum) {

        // Filtra los reportes seleccionados con stream y los agrega a la lista "nomina"
        List<ReporteClase> nomina = IntStream.range(0, ws.getReportes().size())
                .filter(i -> parameters.get("ck_" + i) != null) // Solo selecciona los reportes que están marcados
                .mapToObj(ws.getReportes()::get) // Convierte el índice a un ReporteClase
                .collect(Collectors.toList());  // Recoge los elementos en una lista

        // Usamos un contador explícito para rowNum
        final int[] currentRowNum = {rowNum};  // Usamos un array para permitir la modificación dentro de la lambda

        // Itera sobre los reportes seleccionados y crea las filas en la hoja
        nomina.forEach(reporte -> {
            XSSFRow row = sheet.createRow(currentRowNum[0]++);  // Incrementa el contador en cada iteración
            // Inserta los datos de cada curso en las celdas correspondientes
            row.createCell(1).setCellValue(reporte.getRclaSesion());
            row.createCell(2).setCellValue(reporte.getRclaFecGen());
            row.createCell(3).setCellValue(reporte.getRclaObApren());
            row.createCell(4).setCellValue(reporte.getRclaContenido());
            row.createCell(5).setCellValue(reporte.getRclaMetodo());
            row.createCell(6).setCellValue(reporte.getRclaObs());

            XSSFCellStyle dateCellStyle = sheet.getWorkbook().createCellStyle();
            short dateFormat = sheet.getWorkbook().getCreationHelper().createDataFormat().getFormat("dd-MM-yyyy"); // o el formato que necesites
            dateCellStyle.setDataFormat(dateFormat);
            row.getCell(2).setCellStyle(dateCellStyle);
        });
    }

    /**
     * Guarda el libro de trabajo Excel en un archivo y devuelve el flujo de
     * entrada del archivo.
     *
     * @param workbook El libro de trabajo Excel.
     * @param file El nombre del archivo a guardar.
     * @return El flujo de entrada del archivo generado.
     * @throws IOException Si ocurre un error al guardar el archivo.
     */
    private InputStream saveWorkbookToInputStream(XSSFWorkbook workbook, String file) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(PATH_TEMP_FILES + file)) {
            workbook.write(fileOut);
            fileOut.flush();
        }

        return new FileInputStream(new File(PATH_TEMP_FILES + file));
    }
}
