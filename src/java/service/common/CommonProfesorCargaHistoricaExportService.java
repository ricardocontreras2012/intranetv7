/*
 * @(#)CommonProfesorCargaHistoricaPrintService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import domain.model.Curso;
import domain.model.Profesor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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
import infrastructure.util.common.CommonExcelUtil;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonProfesorCargaHistoricaExportService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param content
     * @param key LLave para aceder a los datos de la sesion.
     * @return
     * @throws java.lang.Exception
     */
    public InputStream service(GenericSession genericSession, String content, String key) throws Exception {

        Profesor prof = genericSession.getWorkSession(key).getProfesor();
        InputStream retValue = null;
        String file;
        FileOutputStream fileOut = null;
        List<Curso> nomina = prof.getCargaHistorica();

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

        XSSFRow profesor = hoja.createRow(1);
        XSSFCell celdaProfesor = profesor.createCell(2);
        XSSFRichTextString textoProfesor = new XSSFRichTextString("");
        celdaProfesor.setCellValue(textoProfesor);
        celdaProfesor.setCellStyle(estiloMembrete);
        CellRangeAddress regionProfesor = new CellRangeAddress(1, 1, (short) 2, (short) 4);
        hoja.addMergedRegion(regionProfesor);

        int fila = 5;
        XSSFRow titulo = hoja.createRow(fila);
        XSSFCell celdaTitulo = titulo.createCell(3);
        XSSFRichTextString textoTitulo = new XSSFRichTextString("CARGA HISTÓRICA :" + prof.getNombre());

        celdaTitulo.setCellValue(textoTitulo);
        celdaTitulo.setCellStyle(estiloTitulo);

        CellRangeAddress regionTitulo = new CellRangeAddress(fila, fila, (short) 3, (short) 7);

        hoja.addMergedRegion(regionTitulo);
        fila++;
        fila++;
        fila++;

        XSSFRow cabecera = hoja.createRow(fila);

        XSSFCell celdaCabAgno = cabecera.createCell(0);
        XSSFRichTextString textoCabAgno = new XSSFRichTextString("AÑO");
        celdaCabAgno.setCellValue(textoCabAgno);
        celdaCabAgno.setCellStyle(estiloCabecera);

        XSSFCell celdaCabSem = cabecera.createCell(1);
        XSSFRichTextString textoCabSem = new XSSFRichTextString("SEM");
        celdaCabSem.setCellValue(textoCabSem);
        celdaCabSem.setCellStyle(estiloCabecera);

        XSSFCell celdaCabCurso = cabecera.createCell(2);
        XSSFRichTextString textoCabCurso = new XSSFRichTextString("CURSO");
        celdaCabCurso.setCellValue(textoCabCurso);
        celdaCabCurso.setCellStyle(estiloCabecera);

        XSSFCell celdaCabNombre = cabecera.createCell(3);
        XSSFRichTextString textoCabNombre = new XSSFRichTextString("NOMBRE");
        celdaCabNombre.setCellValue(textoCabNombre);
        celdaCabNombre.setCellStyle(estiloCabecera);

        fila++;
        getGrid(nomina, hoja, fila);

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

        LogUtil.setLog(genericSession.getRut(), prof.getProfRut());
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
    private void getGrid(List<Curso> nomina, XSSFSheet hoja, int fila) {
        AtomicInteger filaAux = new AtomicInteger(fila);

        nomina.forEach(curso -> {
            XSSFRow rowExcel = hoja.createRow(filaAux.getAndIncrement());

            rowExcel.createCell(0).setCellValue(curso.getId().getCurAgno());
            rowExcel.createCell(1).setCellValue(curso.getId().getCurSem());
            rowExcel.createCell(2).setCellValue(new XSSFRichTextString(curso.getId().getCodigoCorto(" ")));
            rowExcel.createCell(3).setCellValue(new XSSFRichTextString(curso.getCurNombre()));
        });
    }
}
