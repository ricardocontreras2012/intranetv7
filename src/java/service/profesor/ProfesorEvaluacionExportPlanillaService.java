/*
 * @(#)ProfesorEvaluacionExportPlanillaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.profesor;

//import comparable.AlumnoComparable;
import domain.model.Curso;
import domain.model.Evaluacion;
import domain.model.Alumno;
import domain.model.EvaluacionAlumno;
import domain.model.CursoTevaluacion;
import domain.model.comparator.EvaluacionComparable;
import java.io.*;
import java.math.BigDecimal;
import static java.math.BigInteger.ZERO;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.*;
import static java.util.Collections.sort;
import static org.apache.commons.io.FileUtils.openInputStream;
import static org.apache.commons.lang3.StringUtils.substring;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import session.GenericSession;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.FormatUtil;
import infrastructure.util.LogUtil;
import static infrastructure.util.SystemParametersUtil.PATH_TEMP_FILES;
import infrastructure.util.common.CommonAlumnoUtil;
import infrastructure.util.common.CommonCursoUtil;
import infrastructure.util.common.CommonExcelUtil;
import static infrastructure.util.common.CommonFacultadUtil.getNombrexAsign;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ProfesorEvaluacionExportPlanillaService {

    public static ActionInputStreamUtil service(GenericSession genericSession, String key)
            throws Exception {
        String name;
        InputStream input;
        String description;
        name = "NOTAS_CURSO_" + CommonCursoUtil.getNombreFile(genericSession.getCurso(key)) + ".xlsx";
        input = getInput(genericSession, name, key);
        description = FormatUtil.getMimeType(name);
        return new ActionInputStreamUtil(name, description, input);
    }

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param name
     * @param key LLave para acceder a los datos de la sesion.
     * @return
     * @throws java.lang.Exception
     */
    public static InputStream getInput(GenericSession genericSession, String name, String key) throws Exception {

        try {
            InputStream retValue = null;
            FileOutputStream fileOut = null;
            Curso curso = genericSession.getCurso(key);

            List<EvaluacionAlumno> planilla = ContextUtil.getDAO().getEvaluacionAlumnoPersistence(ActionUtil.getDBUser()).getPlanilla(curso);
            List<CursoTevaluacion> cursoListTecaluacion = ContextUtil.getDAO().getCursoTevaluacionPersistence(ActionUtil.getDBUser()).find(curso);
            List<Evaluacion> lEvaluacion
                    = ContextUtil.getDAO().getEvaluacionPersistence(ActionUtil.getDBUser()).find(curso);

            sort(lEvaluacion, new EvaluacionComparable());
            curso.setEvaluacionList(lEvaluacion);

            boolean notasFinales = curso.notasFinales();
            XSSFWorkbook libro = new XSSFWorkbook();
            XSSFSheet hoja = libro.createSheet("hoja");

            CommonExcelUtil.putLogo(libro, hoja);
            XSSFFont fuente = libro.createFont();

            fuente.setFontHeightInPoints((short) 9);
            // fuente.setFontName(FONT_ARIAL);
            //fuente.setBoldweight(BOLDWEIGHT_BOLD);

            XSSFCellStyle estiloTitulo = libro.createCellStyle();

            estiloTitulo.setFont(fuente);
            IndexedColorMap colorMap = libro.getStylesSource().getIndexedColors();
            //estiloTitulo.setAlignment(ALIGN_CENTER);

            XSSFCellStyle estiloMembrete = libro.createCellStyle();

            estiloMembrete.setFont(fuente);
            estiloMembrete.setAlignment(HorizontalAlignment.CENTER);

            XSSFCellStyle estiloCabecera = libro.createCellStyle();

            estiloCabecera.setFont(fuente);
            estiloCabecera.setAlignment(HorizontalAlignment.CENTER);
            estiloCabecera.setVerticalAlignment(VerticalAlignment.TOP);
            XSSFColor tan = new XSSFColor(new java.awt.Color(173, 216, 230), colorMap);
            estiloCabecera.setFillForegroundColor(tan);
            estiloCabecera.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            XSSFCellStyle estiloCabecera2 = libro.createCellStyle();

            estiloCabecera2.setFont(fuente);
            estiloCabecera2.setAlignment(HorizontalAlignment.CENTER);
            estiloCabecera2.setVerticalAlignment(VerticalAlignment.TOP);
            XSSFColor yellow = new XSSFColor(new java.awt.Color(255, 252, 187), colorMap);
            estiloCabecera2.setFillForegroundColor(yellow);
            estiloCabecera2.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            XSSFRow univ = hoja.createRow(0);
            XSSFCell celdaUniv = univ.createCell(2);
            XSSFRichTextString textoUniv = new XSSFRichTextString("UNIVERSIDAD DE SANTIAGO DE CHILE");
            celdaUniv.setCellValue(textoUniv);
            celdaUniv.setCellStyle(estiloMembrete);
            CellRangeAddress regionUniv = new CellRangeAddress(0, 0, (short) 2, (short) 4);
            hoja.addMergedRegion(regionUniv);

            XSSFRow facultad = hoja.createRow(1);
            XSSFCell celdaFacultad = facultad.createCell(2);
            XSSFRichTextString textoFacultad = new XSSFRichTextString(getNombrexAsign(curso.getId().getCurAsign()));
            celdaFacultad.setCellValue(textoFacultad);
            celdaFacultad.setCellStyle(estiloMembrete);
            CellRangeAddress regionFacultad = new CellRangeAddress(1, 1, (short) 2, (short) 4);
            hoja.addMergedRegion(regionFacultad);

            int fila = 5;
            XSSFRow titulo = hoja.createRow(fila);
            XSSFCell celdaTitulo = titulo.createCell(3);
            XSSFRichTextString textoTitulo = new XSSFRichTextString("PLANILLA DE CALIFICACIONES");

            celdaTitulo.setCellValue(textoTitulo);
            celdaTitulo.setCellStyle(estiloTitulo);

            CellRangeAddress regionTitulo = new CellRangeAddress(fila, fila, (short) 3, (short) 7);

            hoja.addMergedRegion(regionTitulo);
            fila++;

            XSSFRow cursoRow = hoja.createRow(fila);
            XSSFCell celdaCurso = cursoRow.createCell(3);
            XSSFRichTextString textoCurso = new XSSFRichTextString(genericSession.getCurso(key).getNombreFull());

            celdaCurso.setCellValue(textoCurso);
            celdaCurso.setCellStyle(estiloTitulo);

            CellRangeAddress regionCurso = new CellRangeAddress(fila, fila, (short) 3, (short) 7);

            hoja.addMergedRegion(regionCurso);
            fila++;
            fila++;

            XSSFRow cabeceraEval = hoja.createRow(fila);
            Iterator<CursoTevaluacion> iterTeval = cursoListTecaluacion.iterator();
            int col = 5;

            while (iterTeval.hasNext()) {
                CursoTevaluacion cursoTevaluacion = iterTeval.next();
                int nEval = getNumEvaluaciones(lEvaluacion, cursoTevaluacion.getId().getCtevaTeva());
                XSSFCell celdaTipoEval = cabeceraEval.createCell(col);
                XSSFRichTextString textoCabEval
                        = new XSSFRichTextString(cursoTevaluacion.getTevaluacion().getTevalDes());

                celdaTipoEval.setCellValue(textoCabEval);
                celdaTipoEval.setCellStyle(estiloCabecera);

                if (nEval > 1) {
                    CellRangeAddress region = new CellRangeAddress(fila, fila, (short) col,
                            (short) (col + nEval - 1));
                    hoja.addMergedRegion(region);
                }

                col += nEval;
            }

            if ("R".equals(curso.getCurModalEval())) {
                fila++;
                cabeceraEval = hoja.createRow(fila);
                iterTeval = cursoListTecaluacion.iterator();
                col = 5;

                while (iterTeval.hasNext()) {
                    CursoTevaluacion cursoTevaluacion = iterTeval.next();
                    int nEval = getNumEvaluaciones(lEvaluacion, cursoTevaluacion.getId().getCtevaTeva());
                    XSSFCell celdaTipoEval = cabeceraEval.createCell(col);
                    XSSFRichTextString textoCabEval = new XSSFRichTextString(cursoTevaluacion.getCtevaPorc().toString()
                            + '%');

                    celdaTipoEval.setCellValue(textoCabEval);
                    celdaTipoEval.setCellStyle(estiloCabecera);

                    if (nEval > 1) {
                        CellRangeAddress region = new CellRangeAddress(fila, fila, (short) col,
                                (short) (col + nEval - 1));

                        hoja.addMergedRegion(region);
                    }
                    col += nEval;
                }
            }
            fila++;

            XSSFRow cabecera = hoja.createRow(fila);

            hoja.setColumnWidth(0, CommonExcelUtil.calculateColWidth(8));
            hoja.setColumnWidth(1, CommonExcelUtil.calculateColWidth(2));
            hoja.setColumnWidth(2, CommonExcelUtil.calculateColWidth(20));
            hoja.setColumnWidth(3, CommonExcelUtil.calculateColWidth(20));
            hoja.setColumnWidth(4, CommonExcelUtil.calculateColWidth(30));

            Iterator<Evaluacion> iterList = lEvaluacion.iterator();
            int cellNum = 5;

            while (iterList.hasNext()) {
                Evaluacion evaluacion = iterList.next();
                XSSFCell celdaEval = cabecera.createCell(cellNum);

                celdaEval.setCellValue(substring(evaluacion.getCursoTevaluacion().getTevaluacion().getTevalDes(), 0, 3)
                        + evaluacion.getId().getEvalEval());
                celdaEval.setCellStyle(estiloCabecera2);
                cellNum++;
            }

            fila++;
            iterList = lEvaluacion.iterator();
            cellNum = 5;
            cabecera = hoja.createRow(fila);

            while (iterList.hasNext()) {
                Evaluacion evaluacion = iterList.next();
                XSSFCell celdaEval = cabecera.createCell(cellNum);

                celdaEval.setCellValue(evaluacion.getEvalPorc() + "%");
                celdaEval.setCellStyle(estiloCabecera2);
                cellNum++;
            }

            fila++;
            cabecera = hoja.createRow(fila);

            XSSFCell celdaCabRut = cabecera.createCell(0);
            XSSFRichTextString textoCabRut = new XSSFRichTextString("RUT");
            celdaCabRut.setCellValue(textoCabRut);
            celdaCabRut.setCellStyle(estiloCabecera);

            XSSFCell celdaCabDv = cabecera.createCell(1);
            XSSFRichTextString textoCabDv = new XSSFRichTextString("DV");
            celdaCabDv.setCellValue(textoCabDv);
            celdaCabDv.setCellStyle(estiloCabecera);

            XSSFCell celdaCabPaterno = cabecera.createCell(2);
            XSSFRichTextString textoCabPaterno = new XSSFRichTextString("PATERNO");
            celdaCabPaterno.setCellValue(textoCabPaterno);
            celdaCabPaterno.setCellStyle(estiloCabecera);

            XSSFCell celdaCabMaterno = cabecera.createCell(3);
            XSSFRichTextString textoCabMaterno = new XSSFRichTextString("MATERNO");
            celdaCabMaterno.setCellValue(textoCabMaterno);
            celdaCabMaterno.setCellStyle(estiloCabecera);

            XSSFCell celdaCabNombre = cabecera.createCell(4);
            XSSFRichTextString textoCabNombre = new XSSFRichTextString("NOMBRE");
            celdaCabNombre.setCellValue(textoCabNombre);
            celdaCabNombre.setCellStyle(estiloCabecera);
            iterList = lEvaluacion.iterator();
            cellNum = 5;

            while (iterList.hasNext()) {
                iterList.next();

                XSSFCell celdaEval = cabecera.createCell(cellNum);

                celdaEval.setCellValue("NOTA");
                celdaEval.setCellStyle(estiloCabecera2);
                cellNum++;
            }

            if (notasFinales) {
                XSSFCell celdaEval = cabecera.createCell(cellNum);

                celdaEval.setCellValue("NOTA FINAL");
                celdaEval.setCellStyle(estiloCabecera2);
                hoja.setColumnWidth(cellNum, CommonExcelUtil.calculateColWidth(10));
            }

            fila++;
            getGrid(lEvaluacion, planilla, libro, hoja, fila, notasFinales, curso.getCurModalEval());

            try {
                fileOut = new FileOutputStream(PATH_TEMP_FILES + name);

                libro.write(fileOut);
                fileOut.flush();
                retValue = openInputStream(new File(PATH_TEMP_FILES + name));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileOut != null) {
                        fileOut.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            LogUtil.setLogCurso(genericSession.getRut(), curso);
            return retValue;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Method description
     *
     * @param evaluaciones
     * @param planilla
     * @param libro
     * @param hoja
     * @param fila
     * @param notasFinales
     */
    private static void getGrid(List<Evaluacion> evaluaciones, List<EvaluacionAlumno> planilla, XSSFWorkbook libro,
            XSSFSheet hoja, int fila, boolean notasFinales, String modalidad) {

        Set<Alumno> nominaSet = new HashSet<>();
        Iterator<EvaluacionAlumno> iterLista = planilla.iterator();
        XSSFCellStyle estiloNota = libro.createCellStyle();
        XSSFDataFormat dataFormat = libro.createDataFormat();

        //estiloNota.setAlignment(ALIGN_CENTER);
        estiloNota.setDataFormat(dataFormat.getFormat("#.0"));

        while (iterLista.hasNext()) {
            nominaSet.add(iterLista.next().getAluCar().getAlumno());
        }

        List<Alumno> nominaList = new ArrayList<>(nominaSet);
        nominaList.sort(Comparator.comparing(Alumno::getNombre));

        int filaAux = fila;

        for (Alumno alumno : nominaList) {
            BigDecimal acum = new BigDecimal(ZERO);
            XSSFRow rowExcel = hoja.createRow(filaAux);
            Iterator<Evaluacion> iterEval = evaluaciones.iterator();

            XSSFCell celdaRut = rowExcel.createCell(0);
            celdaRut.setCellValue(alumno.getAluRut());
            //celdaRut.setCellType(CELL_TYPE_NUMERIC);

            XSSFCell celdaDv = rowExcel.createCell(1);
            XSSFRichTextString textoDv = new XSSFRichTextString(alumno.getAluDv());
            celdaDv.setCellValue(textoDv);

            XSSFCell celdaPaterno = rowExcel.createCell(2);
            XSSFRichTextString textoPaterno = new XSSFRichTextString(alumno.getAluPaterno());
            celdaPaterno.setCellValue(textoPaterno);

            XSSFCell celdaMaterno = rowExcel.createCell(3);
            XSSFRichTextString textoMaterno = new XSSFRichTextString(alumno.getAluMaterno());
            celdaMaterno.setCellValue(textoMaterno);

            XSSFCell celdaNombre = rowExcel.createCell(4);
            XSSFRichTextString textoNombre = new XSSFRichTextString(CommonAlumnoUtil.getNombreSocial(alumno));
            celdaNombre.setCellValue(textoNombre);

            int col = 5;

            while (iterEval.hasNext()) {
                Evaluacion evaluacion = iterEval.next();
                XSSFCell celdaEval = rowExcel.createCell(col);

                //celdaEval.setCellType(CELL_TYPE_NUMERIC);
                String nota = getEvaluacion(evaluacion, planilla, alumno);

                if (nota != null && !"".equals(nota)) {
                    celdaEval.setCellValue(Double.parseDouble(nota.replace(",", ".")));
                    celdaEval.setCellStyle(estiloNota);

                    if ("R".equals(modalidad)) {
                        acum = acum.add(
                                new BigDecimal(nota.replace(",", ".")).multiply(
                                        evaluacion.getEvalPorc()).multiply(
                                        new BigDecimal(evaluacion.getCursoTevaluacion().getCtevaPorc())).divide(
                                                new BigDecimal(10000)));
                    } else {
                        if ("A".equals(modalidad)) {
                            acum = acum.add(
                                    new BigDecimal(nota.replace(",", ".")).multiply(
                                            evaluacion.getEvalPorc()).divide(
                                            new BigDecimal(100)));
                        }
                    }
                }
                col++;
            }

            if (notasFinales) {
                XSSFCell celdaFinal = rowExcel.createCell(col);
                MathContext mathContext = new MathContext(2);
                celdaFinal.setCellValue(Double.parseDouble(acum.round(mathContext).toString().replace(",", ".")));
                celdaFinal.setCellStyle(estiloNota);
            }

            filaAux++;
        }
    }

    /**
     * Method description
     *
     * @param evaluacion
     * @param planilla
     * @param alumno
     * @return
     */
    private static String getEvaluacion(Evaluacion evaluacion, List<EvaluacionAlumno> planilla, Alumno alumno) {
        return planilla.stream()
                .filter(ea -> Objects.equals(ea.getAluCar().getAlumno(), alumno)
                && Objects.equals(ea.getEvaluacion(), evaluacion))
                .findFirst()
                .map(ea -> {
                    BigDecimal nota = ea.getEvaluNota();
                    return (nota == null)
                            ? "1,0"
                            : new DecimalFormat("#.0").format(nota).replace(".", ",");
                })
                .orElse("");
    }

    private static int getNumEvaluaciones(List<Evaluacion> lEvaluacion, int tipo) {
        return (int) lEvaluacion.stream()
                .filter(e -> e.getId().getEvalTeva() == tipo)
                .count();
    }
}
