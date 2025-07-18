/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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
import infrastructure.support.ActaConsultaSupport;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.FormatUtil;
import static infrastructure.util.SystemParametersUtil.PATH_TEMP_FILES;
import infrastructure.util.common.CommonExcelUtil;
import static infrastructure.util.common.CommonFacultadUtil.getNombrexAsign;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Servicio para generar y exportar actas de cursos en formato Excel. Este
 * servicio se encarga de crear un archivo Excel que contiene información
 * detallada sobre los cursos y sus respectivos estados de actas.
 *
 * <p>
 * El archivo generado incluye una cabecera con el nombre de la facultad, el
 * periodo académico y una tabla con los datos de los cursos, como el código del
 * curso, nombre, profesor, tipo, folio y estado.</p>
 *
 * <p>
 * El archivo Excel generado se guarda temporalmente en el sistema y se devuelve
 * como un flujo de entrada (InputStream) para su descarga.</p>
 *
 * @author Usach
 */
public class CommonActaActasCarreraExportService {

    /**
     * Genera y devuelve un flujo de entrada con el archivo Excel que contiene
     * la definición de los cursos y sus actas correspondientes.
     *
     * <p>
     * Este método obtiene la sesión de trabajo y usa la información del período
     * académico para generar el nombre del archivo. Luego, se crea el archivo
     * Excel con los datos de los cursos.</p>
     *
     * @param genericSession La sesión general que contiene la información de
     * trabajo del usuario.
     * @param key La llave para acceder a los datos específicos de la sesión.
     * @return Un objeto ActionInputStreamUtil con el archivo Excel generado.
     * @throws Exception Si ocurre algún error al generar el archivo Excel.
     */
    public ActionInputStreamUtil service(GenericSession genericSession, String key) throws Exception {
        // Obtiene la sesión de trabajo para acceder a los datos del usuario
        WorkSession ws = genericSession.getWorkSession(key);

        // Genera el nombre del archivo Excel basado en el año y semestre
        String fileName = "ESTADO_ACTAS_" + ws.getAgnoAct() + "-" + ws.getSemAct() + ".xlsx";

        // Obtiene el flujo de entrada con el archivo Excel
        InputStream input = getInput(genericSession, fileName, key);

        // Obtiene la descripción MIME del archivo
        String description = FormatUtil.getMimeType(fileName);

        // Devuelve el archivo generado como un flujo de entrada con la descripción MIME
        return new ActionInputStreamUtil(fileName, description, input);
    }

    /**
     * Crea el archivo Excel que contiene los detalles de los cursos y sus
     * actas.
     *
     * <p>
     * Este método construye el archivo Excel desde cero, configurando el libro
     * de trabajo, las hojas, las celdas, el estilo y el contenido. La
     * información sobre los cursos se inserta en el archivo Excel junto con el
     * formato adecuado.</p>
     *
     * @param genericSession La sesión general que contiene la información de
     * trabajo del usuario.
     * @param file El nombre del archivo que se va a generar.
     * @param key La llave para acceder a los datos de la sesión.
     * @return El flujo de entrada del archivo Excel generado.
     * @throws Exception Si ocurre algún error al generar el archivo.
     */
    private InputStream getInput(GenericSession genericSession, String file, String key) throws Exception {
        // Obtiene la sesión de trabajo
        WorkSession ws = genericSession.getWorkSession(key);

        // Obtiene la primera acta de la consulta
        ActaConsultaSupport acta = ws.getActaConsultaSupportList().get(0);

        // Crea un nuevo libro de trabajo Excel
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("hoja");

        // Configura los anchos de las columnas en el archivo Excel
        setColumnWidths(sheet);

        // Inserta el logo en la hoja de trabajo
        CommonExcelUtil.putLogo(workbook, sheet);

        // Configura el estilo de las celdas
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 9);
        XSSFCellStyle titleStyle = createCellStyle(workbook, font);
        XSSFCellStyle headerStyle = createCellStyle(workbook, font);

        // Inserta la cabecera con el nombre de la facultad
        CommonExcelUtil.putCabeceraFacultad(workbook, sheet, font, headerStyle, headerStyle, workbook.getStylesSource().getIndexedColors(), getNombrexAsign(acta.getAcalAsign()));

        // Inserta el título del reporte
        int rowNum = 5;
        insertTitleRow(sheet, rowNum, titleStyle, ws);

        // Inserta las cabeceras de las columnas
        rowNum = 8;
        insertHeaders(sheet, rowNum, headerStyle);

        // Inserta los datos de las actas en el archivo Excel
        rowNum = 9;
        insertActasData(sheet, ws.getActaConsultaSupportList(), rowNum);

        // Guarda el archivo Excel y lo convierte en un flujo de entrada
        return saveWorkbookToInputStream(workbook, file);
    }

    /**
     * Configura el ancho de las columnas en la hoja de trabajo Excel.
     *
     * @param sheet La hoja de trabajo Excel.
     */
    private void setColumnWidths(XSSFSheet sheet) {
        sheet.setColumnWidth(0, CommonExcelUtil.calculateColWidth(10));
        sheet.setColumnWidth(1, CommonExcelUtil.calculateColWidth(2));
        sheet.setColumnWidth(2, CommonExcelUtil.calculateColWidth(2));
        sheet.setColumnWidth(3, CommonExcelUtil.calculateColWidth(2));
        sheet.setColumnWidth(4, CommonExcelUtil.calculateColWidth(50)); // Nombre del curso
        sheet.setColumnWidth(5, CommonExcelUtil.calculateColWidth(50)); // Nombre del profesor
        sheet.setColumnWidth(6, CommonExcelUtil.calculateColWidth(10)); // Folio
        sheet.setColumnWidth(7, CommonExcelUtil.calculateColWidth(12)); // Tipo
        sheet.setColumnWidth(8, CommonExcelUtil.calculateColWidth(12)); // Estado
    }

    /**
     * Crea un estilo de celda con una fuente y alineación opcional.
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
     * Crea un estilo de celda con una fuente predeterminada (sin alineación).
     *
     * @param workbook El libro de trabajo Excel.
     * @param font La fuente a utilizar en el estilo.
     * @return El estilo de la celda configurado.
     */
    private XSSFCellStyle createCellStyle(XSSFWorkbook workbook, XSSFFont font) {
        return createCellStyle(workbook, font, null);
    }

    /**
     * Inserta la fila del título en el archivo Excel.
     *
     * @param sheet La hoja de trabajo Excel.
     * @param rowNum El número de fila donde insertar el título.
     * @param titleStyle El estilo de la celda para el título.
     * @param ws La sesión de trabajo con los datos actuales.
     */
    private void insertTitleRow(XSSFSheet sheet, int rowNum, XSSFCellStyle titleStyle, WorkSession ws) {
        XSSFRow row = sheet.createRow(rowNum);
        XSSFCell cell = row.createCell(3);
        XSSFRichTextString title = new XSSFRichTextString("CARRERA/PROGRAMA  " + ws.getNombreCarrera());
        cell.setCellValue(title);
        cell.setCellStyle(titleStyle);
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 3, 7));

        rowNum++;
        row = sheet.createRow(rowNum);
        cell = row.createCell(3);
        title = new XSSFRichTextString("PERIODO " + ws.getSemAct() + "/" + ws.getAgnoAct());
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
        String[] headers = {"CÓDIGO", "", "", "", "NOMBRE", "PROFESOR", "TIPO", "FOLIO", "ESTADO"};

        IntStream.range(0, headers.length).forEach(i -> {
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(new XSSFRichTextString(headers[i]));
            cell.setCellStyle(headerStyle);
        });

        // Fusiona las celdas de la primera columna para el "CÓDIGO"
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 3));
    }

    /**
     * Inserta los datos de las actas en el archivo Excel.
     *
     * @param sheet La hoja de trabajo Excel.
     * @param actasList La lista de actas a insertar.
     * @param rowNum El número de fila donde insertar los datos.
     * @param horizontalCenterStyle El estilo de celda con alineación centrada.
     */
    private void insertActasData(XSSFSheet sheet, List<ActaConsultaSupport> actasList, int rowNum) {
        AtomicInteger rowIndex = new AtomicInteger(rowNum);

        actasList.forEach(acta -> {
            XSSFRow row = sheet.createRow(rowIndex.getAndIncrement());
            String[] data = {
                acta.getAcalAsign().toString(),
                acta.getAcalElect(),
                acta.getAcalCoord(),
                acta.getAcalSecc().toString(),
                acta.getNombreCurso(),
                acta.getProfesores(),
                acta.getAcalTipo(),
                acta.getId().getAcalFolio().toString(),
                acta.getEstado()
            };
            IntStream.range(0, data.length).forEach(i -> row.createCell(i).setCellValue(data[i]));
        });
    }

    /**
     * Guarda el libro de trabajo Excel en un archivo temporal y devuelve el
     * flujo de entrada del archivo generado.
     *
     * @param workbook El libro de trabajo Excel.
     * @param file El nombre del archivo a guardar.
     * @return El flujo de entrada del archivo generado.
     * @throws IOException Si ocurre un error al guardar el archivo.
     */
    private InputStream saveWorkbookToInputStream(XSSFWorkbook workbook, String file) throws IOException {
        // Guarda el archivo Excel en el sistema de archivos temporal
        try (FileOutputStream fileOut = new FileOutputStream(PATH_TEMP_FILES + file)) {
            workbook.write(fileOut);
            fileOut.flush();
        }

        // Devuelve el flujo de entrada del archivo guardado
        return new FileInputStream(new File(PATH_TEMP_FILES + file));
    }
}
