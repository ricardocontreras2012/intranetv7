package service.common;

import domain.model.Curso;
import java.io.*;
import java.util.List;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.FormatUtil;
import static infrastructure.util.SystemParametersUtil.PATH_TEMP_FILES;
import infrastructure.util.common.CommonCursoUtil;
import infrastructure.util.common.CommonExcelUtil;
import static infrastructure.util.common.CommonFacultadUtil.getNombrexAsign;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Servicio para la exportación de la definición de cursos a un archivo Excel.
 */
public class CommonCursoDefinicionExportService {

    /**
     * Genera y devuelve un flujo de entrada con el archivo Excel de definición
     * de cursos.
     *
     * @param genericSession La sesión general que contiene la información de
     * trabajo.
     * @param key La llave para acceder a los datos de la sesión.
     * @return Un objeto ActionInputStreamUtil con el archivo generado.
     * @throws Exception Si ocurre algún error al generar el archivo.
     */
    public static ActionInputStreamUtil service(GenericSession genericSession, String key) throws Exception {
        // Obtiene la sesión de trabajo
        WorkSession ws = genericSession.getWorkSession(key);

        // Obtiene la lista de cursos
        CommonCursoUtil.getCursos(genericSession, "*", key);

        // Genera el nombre del archivo
        String fileName = "DEFINICION_CURSOS_" + ws.getAgnoAct() + "-" + ws.getSemAct() + ".xlsx";

        // Obtiene el flujo de entrada con el archivo Excel
        InputStream input = getInput(genericSession, fileName, key);

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
    private static InputStream getInput(GenericSession genericSession, String file, String key) throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
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
        XSSFCellStyle horizontalCenterStyle = createCellStyle(workbook, font, HorizontalAlignment.CENTER);

        // Inserta la cabecera con el nombre de la facultad
        CommonExcelUtil.putCabeceraFacultad(workbook, sheet, font, headerStyle, headerStyle, workbook.getStylesSource().getIndexedColors(), getNombrexAsign(curso.getId().getCurAsign()));

        // Inserta el título del reporte
        int rowNum = 5;
        insertTitleRow(sheet, rowNum++, titleStyle, ws);

        // Inserta las cabeceras de las columnas
        insertHeaders(sheet, rowNum++, headerStyle);

        // Inserta los datos de los cursos en el archivo Excel
        insertCoursesData(sheet, ws.getCursoList(), rowNum, horizontalCenterStyle);

        // Genera el archivo Excel y lo guarda en un flujo de entrada
        return saveWorkbookToInputStream(workbook, file);
    }

    /**
     * Configura el ancho de las columnas en la hoja de trabajo.
     *
     * @param sheet La hoja de trabajo Excel.
     */
    private static void setColumnWidths(XSSFSheet sheet) {
        sheet.setColumnWidth(0, CommonExcelUtil.calculateColWidth(10));
        sheet.setColumnWidth(1, CommonExcelUtil.calculateColWidth(2));
        sheet.setColumnWidth(2, CommonExcelUtil.calculateColWidth(2));
        sheet.setColumnWidth(3, CommonExcelUtil.calculateColWidth(2));
        sheet.setColumnWidth(4, CommonExcelUtil.calculateColWidth(50));
        sheet.setColumnWidth(5, CommonExcelUtil.calculateColWidth(50));
        sheet.setColumnWidth(6, CommonExcelUtil.calculateColWidth(50));
        sheet.setColumnWidth(7, CommonExcelUtil.calculateColWidth(12));
        sheet.setColumnWidth(8, CommonExcelUtil.calculateColWidth(24));
        sheet.setColumnWidth(9, CommonExcelUtil.calculateColWidth(4));
        sheet.setColumnWidth(10, CommonExcelUtil.calculateColWidth(4));
        sheet.setColumnWidth(11, CommonExcelUtil.calculateColWidth(10));
    }

    /**
     * Crea un estilo de celda con fuente y alineación opcionales.
     *
     * @param workbook El libro de trabajo Excel.
     * @param font La fuente a utilizar en el estilo.
     * @param alignment La alineación de la celda (opcional).
     * @return El estilo de la celda configurado.
     */
    private static XSSFCellStyle createCellStyle(XSSFWorkbook workbook, XSSFFont font, HorizontalAlignment alignment) {
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
    private static XSSFCellStyle createCellStyle(XSSFWorkbook workbook, XSSFFont font) {
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
    private static void insertTitleRow(XSSFSheet sheet, int rowNum, XSSFCellStyle titleStyle, WorkSession ws) {
        XSSFRow row = sheet.createRow(rowNum);
        XSSFCell cell = row.createCell(3);
        XSSFRichTextString title = new XSSFRichTextString("DEFINICIÓN DE CURSOS " + ws.getSemAct() + "/" + ws.getAgnoAct());
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
    private static void insertHeaders(XSSFSheet sheet, int rowNum, XSSFCellStyle headerStyle) {
        XSSFRow headerRow = sheet.createRow(rowNum);

        // Crea las celdas de las cabeceras
        String[] headers = {"CÓDIGO", "NOMBRE", "PROFESOR", "AYUDANTE", "HORARIO", "SALAS", "INI", "INS", "TIPO"};

        IntStream.range(0, headers.length).forEach(i -> {
            XSSFCell cell = headerRow.createCell(i);
            XSSFRichTextString header = new XSSFRichTextString(headers[i]);
            cell.setCellValue(header);
            cell.setCellStyle(headerStyle);
        });
        // Fusiona las celdas de la primera columna para el "CÓDIGO"
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 3));
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
    private static void insertCoursesData(XSSFSheet sheet, List<Curso> cursoList, int rowNum, XSSFCellStyle horizontalCenterStyle) {
        AtomicInteger rowNumAtomic = new AtomicInteger(rowNum); // Usamos AtomicInteger para poder actualizar el contador dentro de un lambda
        cursoList.forEach(curso -> {
            XSSFRow row = sheet.createRow(rowNumAtomic.getAndIncrement()); // Usamos getAndIncrement() para aumentar el valor de rowNum
            // Inserta los datos de cada curso en las celdas correspondientes
            row.createCell(0).setCellValue(curso.getId().getCurAsign());
            row.createCell(1).setCellValue(curso.getId().getCurElect());
            row.createCell(2).setCellValue(curso.getId().getCurCoord());
            row.createCell(3).setCellValue(curso.getId().getCurSecc());
            row.createCell(4).setCellValue(curso.getCurNombre());
            row.createCell(5).setCellValue(curso.getCurProfesores());
            row.createCell(6).setCellValue(curso.getCurAyudantes());
            row.createCell(7).setCellValue(curso.getCurHorario());
            row.createCell(8).setCellValue(curso.getCurSalas());
            row.createCell(9).setCellValue(curso.getCurCupoIni());
            row.createCell(10).setCellValue(curso.getCurCupoIni() - curso.getCurCupoDis());
            XSSFCell tipoCell = row.createCell(11);
            tipoCell.setCellValue(curso.getCurTipo());
            tipoCell.setCellStyle(horizontalCenterStyle);
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
    private static InputStream saveWorkbookToInputStream(XSSFWorkbook workbook, String file) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(PATH_TEMP_FILES + file)) {
            workbook.write(fileOut);
            fileOut.flush();
        }
        return new FileInputStream(new File(PATH_TEMP_FILES + file));
    }
}
