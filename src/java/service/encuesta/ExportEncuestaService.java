/**
 *
 * @author Usach
 */
package service.encuesta;

import domain.model.ComentarioEncuestaDocente;
import domain.model.Curso;
import domain.model.Profesor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.FormatUtil;
import infrastructure.util.SystemParametersUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import org.apache.poi.ss.util.CellRangeAddress;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getDate;
import static infrastructure.util.SystemParametersUtil.DATE_FULL_FORMAT;
import infrastructure.util.common.CommonExcelUtil;
import infrastructure.util.common.CommonEncuestaUtil;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.IntStream;

public class ExportEncuestaService {

    public ActionInputStreamUtil service(GenericSession genericSession, String key) throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);

        String name = "encuesta_" + ws.getCurso().getCodigo("_") + ".xlsx";
        String description = FormatUtil.getMimeType(name);

        return new ActionInputStreamUtil(name, description, getInput(genericSession, ws, name));
    }

    private InputStream getInput(GenericSession genericSession, WorkSession ws, String name) throws Exception {
        Curso curso = ws.getCurso();
        List<Profesor> profList = ContextUtil.getDAO().getProfesorRepository("CM").getProfesores(curso.getId());

        XSSFWorkbook workbook = new XSSFWorkbook();

        if ("PR".equals(genericSession.getUserType())) {
            getSheet(workbook, ws, genericSession.getProfesorSession().getProfesor(), curso);
        } else {
            profList.forEach(prof -> {
                getSheet(workbook, ws, prof, curso);
            });
        }

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        workbook.write(buffer);
        workbook.close();

        try (FileOutputStream fos = new FileOutputStream(SystemParametersUtil.PATH_TEMP_FILES + name)) {
            buffer.writeTo(fos);
        }

        return new ByteArrayInputStream(buffer.toByteArray());
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

    private void getSheet(XSSFWorkbook workbook, WorkSession ws, Profesor prof, Curso curso) {
        try {

            XSSFSheet sheet = workbook.createSheet("Encuesta " + prof.getNombre());

            // Configurar anchos de columna usando stream
            IntStream.rangeClosed(3, 7).forEach(i -> sheet.setColumnWidth(i, CommonExcelUtil.calculateColWidth(i == 3 ? 100 : 12)));

            // Crear estilos reutilizables
            CellStyle decimalStyle = createDecimalStyle(workbook);
            //CellStyle centerStyle = createCenterStyle(workbook);
            CellStyle comenStyle = createComenStyle(workbook);
            CellStyle headerStyle = createHeaderStyle(workbook);

            CommonExcelUtil.putLogo(workbook, sheet);

            // Crear filas de encabezado
            createMergedRow(sheet, 0, "UNIVERSIDAD DE SANTIAGO DE CHILE", createTitleCellStyle(workbook));
            createMergedRow(sheet, 1, "Encuesta Curso: " + curso.getNombreFull(), createTitleCellStyle(workbook));
            createMergedRow(sheet, 2, "Profesor: " + prof.getProfNombreSimple(), createTitleCellStyle(workbook));
            createMergedRow(sheet, 3, "Fecha: " + getDate(DATE_FULL_FORMAT), createSmallCellStyle(workbook));

            // Crear fila de cabecera con stream
            String[] headers = {"", "PREGUNTA", "PROM", "MAX", "MIN", "DESV STD"};
            Row headerRow = sheet.createRow(5);
            IntStream.range(2, 8).forEach(i -> {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i - 2]);
                cell.setCellStyle(headerStyle);
            });

            // Agregar datos con manejo de errores seguro usando stream
            AtomicInteger rowNum = new AtomicInteger(6);
            ws.getRespEncta().forEach(resp -> {
                Row dataRow = sheet.createRow(rowNum.getAndIncrement());
                dataRow.createCell(2).setCellValue(resp.getPregNro());
                dataRow.createCell(3).setCellValue(resp.getPregDes());

                createNumericCell(dataRow, 4, resp.getProm(), decimalStyle);
                dataRow.createCell(5).setCellValue(resp.getMaximo());
                dataRow.createCell(6).setCellValue(resp.getMinimo());
                createNumericCell(dataRow, 7, resp.getDesv(), decimalStyle);
            });

            // Agregar filas adicionales
            addInfoRow(sheet, rowNum, "#ALUMNO RESPONDIERON: " + ws.getRespEncta().get(0).getNumResp());
            addInfoRow(sheet, rowNum, "PROMEDIO CURSO: " + ws.getRespEncta().get(0).getCedPromCur());
            addInfoRow(sheet, rowNum, "PROMEDIO AREA " + ws.getRespEncta().get(0).getCedPromArea());

            // Agregar comentarios positivos y de mejora
            addComments(sheet, rowNum, "COMENTARIOS POSITIVOS", comenStyle,
                    CommonEncuestaUtil.getComentarios(ws.getComentarioEncuestaDocenteList(),
                            ComentarioEncuestaDocente::getCedComen1,
                            ComentarioEncuestaDocente::setCedComen1),
                    ComentarioEncuestaDocente::getCedComen1);

            addComments(sheet, rowNum, "COMENTARIOS MEJORAS", comenStyle,
                    CommonEncuestaUtil.getComentarios(ws.getComentarioEncuestaDocenteList(),
                            ComentarioEncuestaDocente::getCedComen2,
                            ComentarioEncuestaDocente::setCedComen2),
                    ComentarioEncuestaDocente::getCedComen2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

// Métodos auxiliares para reutilización
    private void createMergedRow(XSSFSheet sheet, int rowIndex, String text, CellStyle style) {
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(2);
        cell.setCellValue(text);
        cell.setCellStyle(style);
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 2, 7));
    }

    private void createNumericCell(Row row, int column, Object value, CellStyle style) {
        try {
            Cell cell = row.createCell(column, CellType.NUMERIC);
            cell.setCellValue(Double.parseDouble(value.toString().replace(",", ".")));
            cell.setCellStyle(style);
        } catch (NumberFormatException e) {
            row.createCell(column).setCellValue("-");
        }
    }

    private void addInfoRow(XSSFSheet sheet, AtomicInteger rowNum, String text) {
        Row row = sheet.createRow(rowNum.getAndIncrement());
        row.createCell(3).setCellValue(text);
    }

    private void addComments(XSSFSheet sheet, AtomicInteger rowNum, String title, CellStyle style,
            List<ComentarioEncuestaDocente> comentarios, Function<ComentarioEncuestaDocente, String> commentExtractor) {
        Row row = sheet.createRow(rowNum.getAndIncrement());
        Cell cell = row.createCell(3);
        cell.setCellValue(title);
        cell.setCellStyle(style);

        comentarios.stream()
                .map(commentExtractor)
                .filter(Objects::nonNull)
                .forEach(comment -> {
                    Row dataRow = sheet.createRow(rowNum.getAndIncrement());
                    dataRow.createCell(3).setCellValue(comment);
                });
    }

    private CellStyle createDecimalStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("0.0"));
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private CellStyle createComenStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    private CellStyle createHeaderStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }
}
