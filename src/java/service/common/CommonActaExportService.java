package service.common;

import domain.model.Curso;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.FormatUtil;
import domain.model.ActaNominaView;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.apache.poi.ss.util.CellRangeAddress;
import static infrastructure.util.DateUtil.getDate;
import static infrastructure.util.SystemParametersUtil.DATE_FULL_FORMAT;
import infrastructure.util.common.CommonExcelUtil;
import java.util.List;
import java.util.stream.IntStream;

public class CommonActaExportService {

    private static final String FILE_PREFIX = "notas_finales_";
    private static final String SHEET_NAME = "Notas Finales";
    private static final String TITLE = "UNIVERSIDAD DE SANTIAGO DE CHILE";
    private static final String DATE_LABEL = "Fecha: ";

    public ActionInputStreamUtil service(GenericSession genericSession, String key) throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        Curso curso = ws.getCurso();

        String fileName = FILE_PREFIX + curso.getCodigo("_") + ".xlsx";
        String mimeType = FormatUtil.getMimeType(fileName);

        return new ActionInputStreamUtil(fileName, mimeType, getInput(ws, fileName));
    }

    private InputStream getInput(WorkSession ws, String name) throws Exception {
        Curso curso = ws.getCurso();

        // Crear el workbook y la hoja
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(SHEET_NAME);

        // Ajustar el ancho de las columnas
        setColumnWidths(sheet);

        // Crear los estilos que se usarán en varias celdas
        CellStyle headerStyle = createHeaderCellStyle(workbook);
        CellStyle titleStyle = createTitleCellStyle(workbook);
        CellStyle smallCellStyle = createSmallCellStyle(workbook);
        CellStyle decimalStyle = createDecimalCellStyle(workbook);

        // Insertar el logo
        CommonExcelUtil.putLogo(workbook, sheet);

        // Agregar las filas de título, facultad y fecha
        createTitleRow(sheet, titleStyle);
        createFacultyRow(sheet, titleStyle, curso);
        createDateRow(sheet, smallCellStyle);

        // Crear encabezados de las columnas
        createHeaderRow(sheet, headerStyle);

        // Llenar los datos de la nómina
        populateNominaData(sheet, ws.getNominaActa(), decimalStyle);

        // Crear el archivo en memoria
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        workbook.write(buffer);
        workbook.close();

        return new ByteArrayInputStream(buffer.toByteArray());
    }

    private void setColumnWidths(XSSFSheet sheet) {
        sheet.setColumnWidth(3, CommonExcelUtil.calculateColWidth(12));
        sheet.setColumnWidth(4, CommonExcelUtil.calculateColWidth(30));
        sheet.setColumnWidth(5, CommonExcelUtil.calculateColWidth(30));
        sheet.setColumnWidth(6, CommonExcelUtil.calculateColWidth(50));
        sheet.setColumnWidth(7, CommonExcelUtil.calculateColWidth(6));
    }

    private void createTitleRow(XSSFSheet sheet, CellStyle style) {
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(2);
        titleCell.setCellValue(TITLE);
        titleCell.setCellStyle(style);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 7));
    }

    private void createFacultyRow(XSSFSheet sheet, CellStyle style, Curso curso) {
        Row facultyRow = sheet.createRow(1);
        Cell facultyCell = facultyRow.createCell(2);
        facultyCell.setCellValue("Notas Finales Curso: " + curso.getNombreFull());
        facultyCell.setCellStyle(style);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 7));
    }

    private void createDateRow(XSSFSheet sheet, CellStyle style) {
        Row dateRow = sheet.createRow(2);
        Cell dateCell = dateRow.createCell(2);
        dateCell.setCellValue(DATE_LABEL + getDate(DATE_FULL_FORMAT));
        dateCell.setCellStyle(style);
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 2, 7));
    }

    private void createHeaderRow(XSSFSheet sheet, CellStyle style) {
        Row headerRow = sheet.createRow(4);
        String[] headers = {"N°", "RUT", "PATERNO", "MATERNO", "NOMBRE", "NOTA"};

        IntStream.range(0, headers.length).forEach(i -> {
            Cell cell = headerRow.createCell(i + 2);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(style);
        });
    }

    private void populateNominaData(XSSFSheet sheet, List<ActaNominaView> nomina, CellStyle decimalStyle) {
        IntStream.range(0, nomina.size()).forEach(i -> {
            ActaNominaView acta = nomina.get(i);
            Row dataRow = sheet.createRow(i + 5);

            dataRow.createCell(2).setCellValue(i + 1);
            dataRow.createCell(3).setCellValue(acta.getAluRut() + "-" + acta.getAluDv());
            dataRow.createCell(4).setCellValue(acta.getAluPaterno());
            dataRow.createCell(5).setCellValue(acta.getAluMaterno());
            dataRow.createCell(6).setCellValue(acta.getAluNombre());

            Cell notaCell = dataRow.createCell(7, CellType.NUMERIC);
            notaCell.setCellStyle(decimalStyle);
            notaCell.setCellValue(Float.parseFloat(acta.getAcanFinal().toString().replace(",", ".")));
        });
    }

    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private CellStyle createTitleCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private CellStyle createSmallCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.RIGHT);
        return style;
    }

    private CellStyle createDecimalCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("#.0"));
        return style;
    }
}
