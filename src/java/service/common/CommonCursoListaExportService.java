/*
 * @(#)CommonCursoListaExportService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import domain.model.Curso;
import domain.model.AluCar;
import domain.model.Alumno;
import java.io.*;
import java.util.*;
import static org.apache.commons.io.FileUtils.openInputStream;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import session.GenericSession;
import infrastructure.util.LogUtil;
import static infrastructure.util.SystemParametersUtil.PATH_TEMP_FILES;
import infrastructure.util.common.CommonAlumnoUtil;
import infrastructure.util.common.CommonExcelUtil;
import static infrastructure.util.common.CommonFacultadUtil.getNombrexAsign;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonCursoListaExportService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param content
     * @param key LLave para acceder a los datos de la sesion.
     * @return
     * @throws java.lang.Exception
     */
    public static InputStream service(GenericSession genericSession, String content, String key) throws Exception {

        InputStream retValue = null;
        String file;
        FileOutputStream fileOut = null;
        Curso curso = genericSession.getCurso(key);

        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja = libro.createSheet("hoja");

        hoja.setColumnWidth(0, CommonExcelUtil.calculateColWidth(8));
        hoja.setColumnWidth(1, CommonExcelUtil.calculateColWidth(2));
        hoja.setColumnWidth(2, CommonExcelUtil.calculateColWidth(30));
        hoja.setColumnWidth(3, CommonExcelUtil.calculateColWidth(30));
        hoja.setColumnWidth(4, CommonExcelUtil.calculateColWidth(50));
        hoja.setColumnWidth(5, CommonExcelUtil.calculateColWidth(50));

        CommonExcelUtil.putLogo(libro, hoja);
        XSSFFont fuente = libro.createFont();

        fuente.setFontHeightInPoints((short) 9);
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
        XSSFRichTextString textoTitulo = new XSSFRichTextString("LISTA DE CURSO");

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
        fila++;
        XSSFRow cabecera = hoja.createRow(fila);

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

        XSSFCell celdaCabEmail = cabecera.createCell(5);
        XSSFRichTextString textoCabEmail = new XSSFRichTextString("E-MAIL");
        celdaCabEmail.setCellValue(textoCabEmail);
        celdaCabEmail.setCellStyle(estiloCabecera);

        fila++;
        getGrid(curso, hoja, fila);

        try {
            file = content.replace("attachment;filename=", "").replaceAll("\"", "");
            fileOut = new FileOutputStream(PATH_TEMP_FILES + file);

            libro.write(fileOut);
            fileOut.flush();
            retValue = openInputStream(new File(PATH_TEMP_FILES + file));
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
    private static void getGrid(Curso curso, XSSFSheet hoja, int fila) {        
        List<AluCar> nomina = curso.getNominaAlumnos();
        AtomicInteger filaAux = new AtomicInteger(fila);  // Usamos AtomicInteger para poder incrementarlo dentro de la lambda

        // Se itera sobre la lista de alumnos utilizando un Stream
        nomina.forEach(aluCar -> {
            Alumno alumno = aluCar.getAlumno();
            XSSFRow rowExcel = hoja.createRow(filaAux.getAndIncrement());  // Incrementamos filaAux dentro de la lambda

            // Crear las celdas de la fila en un solo bloque
            crearCelda(rowExcel, 0, alumno.getAluRut().toString());
            crearCelda(rowExcel, 1, alumno.getAluDv());
            crearCelda(rowExcel, 2, alumno.getAluPaterno());
            crearCelda(rowExcel, 3, alumno.getAluMaterno());
            crearCelda(rowExcel, 4, CommonAlumnoUtil.getNombreSocial(alumno));
            crearCelda(rowExcel, 5, alumno.getAluEmailUsach());
        });
    }

    private static void crearCelda(XSSFRow rowExcel, int columna, String valor) {
        XSSFCell celda = rowExcel.createCell(columna);
        XSSFRichTextString texto = new XSSFRichTextString(valor);
        celda.setCellValue(texto);
    }
}
