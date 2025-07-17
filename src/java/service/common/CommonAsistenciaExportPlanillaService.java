package service.common;

import domain.model.AluCar;
import domain.model.Alumno;
import domain.model.AsistenciaAlumno;
import domain.model.Curso;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.DateUtil;
import infrastructure.util.FormatUtil;
import static infrastructure.util.SystemParametersUtil.PATH_TEMP_FILES;
import infrastructure.util.common.CommonCursoUtil;
import infrastructure.util.common.CommonExcelUtil;
import static infrastructure.util.common.CommonFacultadUtil.getNombrexAsign;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * Servicio para generar y exportar una planilla de asistencia en formato Excel.
 * Este servicio toma los datos de los estudiantes, las clases y su asistencia,
 * y genera un archivo Excel con la información organizada.
 */
public final class CommonAsistenciaExportPlanillaService {

    /**
     * Servicio principal para generar el archivo de Excel de asistencia. Este
     * método crea un archivo Excel con los datos de asistencia de los
     * estudiantes y lo prepara para ser descargado.
     *
     * @param genericSession La sesión de trabajo que contiene los datos de los
     * estudiantes y el curso.
     * @param key La clave para acceder a la sesión de trabajo específica.
     * @return Un objeto `ActionInputStreamUtil` que contiene el archivo
     * generado.
     * @throws Exception Si ocurre algún error al generar el archivo Excel.
     */
    public static ActionInputStreamUtil service(GenericSession genericSession, String key) throws Exception {
        String name = "asistencia_" + CommonCursoUtil.getNombreFile(genericSession.getWorkSession(key).getCurso()) + ".xlsx";
        InputStream input = getInput(genericSession, name, key);
        String description = FormatUtil.getMimeType(name);
        return new ActionInputStreamUtil(name, description, input);
    }

    /**
     * Crea el archivo Excel con la información de asistencia de los
     * estudiantes. Este método genera el archivo y lo retorna como un flujo de
     * entrada para su descarga.
     *
     * @param genericSession La sesión de trabajo que contiene los datos de los
     * estudiantes y el curso.
     * @param file El nombre del archivo Excel que se generará.
     * @param key La clave para acceder a la sesión de trabajo específica.
     * @return El flujo de entrada del archivo Excel generado.
     * @throws Exception Si ocurre algún error al generar el archivo.
     */
    private static InputStream getInput(GenericSession genericSession, String file, String key) throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        Curso curso = genericSession.getCurso(key);

        try (XSSFWorkbook libro = new XSSFWorkbook()) {
            XSSFSheet hoja = libro.createSheet("hoja");
            CellStyle decimalStyle = createDecimalCellStyle(libro);
            XSSFFont font = createFont(libro);
            XSSFCellStyle estiloTitulo = createTitleStyle(libro, font);
            XSSFCellStyle estiloCabecera = createHeaderStyle(libro);

            // Insertar el logo y cabecera de la facultad
            CommonExcelUtil.putLogo(libro, hoja);
            CommonExcelUtil.putCabeceraFacultad(libro, hoja, font, estiloCabecera, createHeaderStyle2(libro), libro.getStylesSource().getIndexedColors(), getNombrexAsign(curso.getId().getCurAsign()));

            // Crear las filas de título y cabecera
            int fila = createTitleRows(hoja, estiloTitulo, genericSession.getCurso(key));
            fila = createHeaderRow(hoja, fila, estiloCabecera, ws.getAsistenciaAlumnoList());

            // Crear las filas de datos de los estudiantes
            getGrid(ws, hoja, decimalStyle, fila);

            // Escribir el archivo en un flujo de entrada
            return writeWorkbookToInputStream(libro, file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error al generar el archivo Excel", e);
        }
    }

    /**
     * Crea el estilo de celda para mostrar valores decimales con un solo
     * decimal.
     *
     * @param libro El libro de trabajo de Excel donde se aplicará el estilo.
     * @return El estilo de celda configurado para decimales.
     */
    private static CellStyle createDecimalCellStyle(XSSFWorkbook libro) {
        CellStyle decimalStyle = libro.createCellStyle();
        DataFormat format = libro.createDataFormat();
        decimalStyle.setDataFormat(format.getFormat("0.0"));
        return decimalStyle;
    }

    /**
     * Crea una fuente con tamaño 9 puntos para usar en las celdas.
     *
     * @param libro El libro de trabajo de Excel donde se aplicará la fuente.
     * @return La fuente creada con tamaño 9.
     */
    private static XSSFFont createFont(XSSFWorkbook libro) {
        XSSFFont font = libro.createFont();
        font.setFontHeightInPoints((short) 9);
        return font;
    }

    /**
     * Crea un estilo de celda para los títulos utilizando la fuente
     * proporcionada.
     *
     * @param libro El libro de trabajo de Excel donde se aplicará el estilo.
     * @param font La fuente a utilizar para los títulos.
     * @return El estilo de celda para los títulos.
     */
    private static XSSFCellStyle createTitleStyle(XSSFWorkbook libro, XSSFFont font) {
        XSSFCellStyle style = libro.createCellStyle();
        style.setFont(font);
        return style;
    }

    /**
     * Crea un estilo de celda para las cabeceras de las columnas.
     *
     * @param libro El libro de trabajo de Excel donde se aplicará el estilo.
     * @return El estilo de celda para las cabeceras.
     */
    private static XSSFCellStyle createHeaderStyle(XSSFWorkbook libro) {
        return libro.createCellStyle();
    }

    /**
     * Crea un estilo de celda adicional para las cabeceras de la planilla.
     *
     * @param libro El libro de trabajo de Excel donde se aplicará el estilo.
     * @return El estilo de celda adicional para las cabeceras.
     */
    private static XSSFCellStyle createHeaderStyle2(XSSFWorkbook libro) {
        return libro.createCellStyle();
    }

    /**
     * Crea las filas de título con el nombre del curso y el título de la
     * planilla.
     *
     * @param hoja La hoja de trabajo donde se agregan las filas.
     * @param estiloTitulo El estilo que se usará en las celdas de título.
     * @param curso El curso cuyo nombre se mostrará en la planilla.
     * @return El número de la siguiente fila a crear.
     */
    private static int createTitleRows(XSSFSheet hoja, XSSFCellStyle estiloTitulo, Curso curso) {
        int fila = 5;

        // Crear fila de título de la planilla
        XSSFRow titulo = hoja.createRow(fila);
        XSSFCell celdaTitulo = titulo.createCell(3);
        celdaTitulo.setCellValue(new XSSFRichTextString("PLANILLA DE ASISTENCIAS"));
        celdaTitulo.setCellStyle(estiloTitulo);
        hoja.addMergedRegion(new CellRangeAddress(fila, fila, 3, 7));
        fila++;

        // Crear fila con el nombre del curso
        XSSFRow cursoRow = hoja.createRow(fila);
        XSSFCell celdaCurso = cursoRow.createCell(3);
        celdaCurso.setCellValue(new XSSFRichTextString(curso.getNombreFull()));
        celdaCurso.setCellStyle(estiloTitulo);
        hoja.addMergedRegion(new CellRangeAddress(fila, fila, 3, 7));

        return fila + 3;
    }

    /**
     * Crea la fila de cabecera con los nombres de las columnas.
     *
     * @param hoja La hoja donde se agregarán las cabeceras.
     * @param fila El número de la fila donde comenzar a agregar las cabeceras.
     * @param estiloCabecera El estilo que se usará en las celdas de cabecera.
     * @param asistenciaAlumnoList La lista de asistencia de los alumnos para
     * crear las columnas correspondientes.
     * @return El número de la siguiente fila después de agregar las cabeceras.
     */
    private static int createHeaderRow(XSSFSheet hoja, int fila, XSSFCellStyle estiloCabecera, List<AsistenciaAlumno> asistenciaAlumnoList) {
        XSSFRow cabecera = hoja.createRow(fila);

        // Crear las celdas de cabecera
        List<String> headers = Arrays.asList("RUT", "DV", "PATERNO", "MATERNO", "NOMBRE");
        int length = headers.size();

        // Crear las celdas de cabecera dinámicamente usando Streams
        IntStream.range(0, length)
                .forEach(i -> createHeaderCell(cabecera, i, headers.get(i), estiloCabecera));

        // Crear las celdas de asistencia de cada día usando Streams
        final int[] cellNum = {length}; // Usar un arreglo para manejar el índice de columna
        asistenciaAlumnoList.forEach(asistenciaAlumno -> {
            hoja.setColumnWidth(cellNum[0], CommonExcelUtil.calculateColWidth(length));
            XSSFCell celdaEval = cabecera.createCell(cellNum[0]);
            celdaEval.setCellValue(DateUtil.getFormattedDate(asistenciaAlumno.getAsaFecha(), "dd/MM"));
            celdaEval.setCellStyle(estiloCabecera);
            cellNum[0]++;
        });

        // Crear la celda de porcentaje de asistencia
        createHeaderCell(cabecera, cellNum[0], "% Asistencia", estiloCabecera);

        return fila + 1;
    }

    /**
     * Crea una celda en la cabecera con el valor especificado.
     *
     * @param cabecera La fila de cabecera donde se agregará la celda.
     * @param col El índice de la columna donde se agregará la celda.
     * @param value El valor que contendrá la celda.
     * @param estiloCabecera El estilo que se aplicará a la celda.
     */
    private static void createHeaderCell(XSSFRow cabecera, int col, String value, XSSFCellStyle estiloCabecera) {
        XSSFCell cell = cabecera.createCell(col);
        cell.setCellValue(new XSSFRichTextString(value));
        cell.setCellStyle(estiloCabecera);
    }

    /**
     * Rellena la hoja con los datos de asistencia de cada estudiante.
     *
     * @param ws La sesión de trabajo que contiene los datos de asistencia.
     * @param hoja La hoja donde se insertarán los datos.
     * @param decimalStyle El estilo de celda para los valores decimales.
     * @param fila El número de fila donde se comenzarán a insertar los datos.
     */
    private static void getGrid(WorkSession ws, XSSFSheet hoja, CellStyle decimalStyle, int fila) {
        List<AluCar> nomina = ws.getNominaCurso();
        List<AsistenciaAlumno> asistencia = ws.getAsistenciaAlumnoList();
        DecimalFormat df = new DecimalFormat("0.0");

        // Usar un arreglo para manejar la fila
        final int[] filaAux = {fila};

        nomina.stream().forEach(aluCar -> {
            Alumno alumno = aluCar.getAlumno();
            XSSFRow rowExcel = hoja.createRow(filaAux[0]);

            // Crear fila con los datos del estudiante
            createStudentRow(rowExcel, alumno);

            // Crear las celdas de asistencia usando un Stream
            AtomicInteger colIndex = new AtomicInteger(5); // Índice inicial de columnas
            long attendedClasses = asistencia.stream()
                    .map(asistenciaAlumno -> {
                        String val = ws.asisteClases(asistenciaAlumno.getAsaCorrel(), alumno.getAluRut()) ? "S" : "N";
                        rowExcel.createCell(colIndex.getAndIncrement()).setCellValue(val);
                        return val;
                    })
                    .filter("S"::equals)
                    .count();

            // Calcular el porcentaje de asistencia y escribirlo en la celda
            int totalClasses = asistencia.size();
            if (totalClasses > 0) {
                float attendancePercentage = 100f * attendedClasses / totalClasses;
                XSSFCell celdaPorc = rowExcel.createCell(colIndex.get(), CellType.NUMERIC);
                celdaPorc.setCellStyle(decimalStyle);
                celdaPorc.setCellValue(Float.parseFloat(df.format(attendancePercentage).replace(",", ".")));
            }

            filaAux[0]++; // Incrementar la fila para el siguiente alumno
        });
    }

    /**
     * Crea una fila con los datos básicos de un estudiante.
     *
     * @param row La fila donde se agregarán los datos del estudiante.
     * @param alumno El objeto `Alumno` que contiene la información del
     * estudiante.
     */
    private static void createStudentRow(XSSFRow row, Alumno alumno) {
        row.createCell(0).setCellValue(alumno.getAluRut());
        row.createCell(1).setCellValue(new XSSFRichTextString(alumno.getAluDv()));
        row.createCell(2).setCellValue(new XSSFRichTextString(alumno.getAluPaterno()));
        row.createCell(3).setCellValue(new XSSFRichTextString(alumno.getAluMaterno()));
        row.createCell(4).setCellValue(new XSSFRichTextString(alumno.getAluNombre()));
    }

    /**
     * Escribe el archivo Excel generado en un flujo de entrada para su
     * descarga.
     *
     * @param libro El libro de trabajo (archivo Excel).
     * @param file El nombre del archivo Excel a generar.
     * @return El flujo de entrada del archivo Excel.
     * @throws IOException Si ocurre un error al escribir el archivo.
     */
    private static InputStream writeWorkbookToInputStream(XSSFWorkbook libro, String file) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(PATH_TEMP_FILES + file)) {
            libro.write(fileOut);
            fileOut.flush();
        }

        return new FileInputStream(new File(PATH_TEMP_FILES + file));
    }
}
