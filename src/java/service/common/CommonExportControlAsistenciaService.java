/*
 * @(#)CommonExportControlAsistenciaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import domain.model.Horario;
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
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.SystemParametersUtil.PATH_TEMP_FILES;
import infrastructure.util.common.CommonExcelUtil;

/**
 *
 * @author Felipe and Javier
 */
public final class CommonExportControlAsistenciaService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @param content
     * @param agno
     * @param sem
     * 
     * @return
     * @throws java.lang.Exception
     */
    public static InputStream service(GenericSession genericSession, String key, String content, Integer agno, Integer sem) throws Exception {
        InputStream retValue = null;
        String file;
        FileOutputStream fileOut = null;

        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja = libro.createSheet("hoja");
        
        hoja.setColumnWidth(0, CommonExcelUtil.calculateColWidth(10));
        hoja.setColumnWidth(1, CommonExcelUtil.calculateColWidth(10));
        hoja.setColumnWidth(2, CommonExcelUtil.calculateColWidth(20));
        hoja.setColumnWidth(3, CommonExcelUtil.calculateColWidth(10));
        hoja.setColumnWidth(4, CommonExcelUtil.calculateColWidth(10));
        hoja.setColumnWidth(5, CommonExcelUtil.calculateColWidth(10));
        hoja.setColumnWidth(6, CommonExcelUtil.calculateColWidth(100));
        hoja.setColumnWidth(7, CommonExcelUtil.calculateColWidth(45));

        CommonExcelUtil.putLogo(libro, hoja);
        XSSFFont fuente = libro.createFont();

        fuente.setFontHeightInPoints((short) 9);
        XSSFCellStyle estiloTitulo = libro.createCellStyle();

        estiloTitulo.setFont(fuente);
        IndexedColorMap colorMap = libro.getStylesSource().getIndexedColors();

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

        XSSFRow teletrabajo = hoja.createRow(1);
        XSSFCell celdaTeletrabajo = teletrabajo.createCell(2);
        XSSFRichTextString textoTeletrabajo = new XSSFRichTextString("Reporte de Asistencia");
        celdaTeletrabajo.setCellValue(textoTeletrabajo);
        celdaTeletrabajo.setCellStyle(estiloMembrete);
        CellRangeAddress regionTeletrabajo = new CellRangeAddress(1, 1, (short) 2, (short) 4);
        hoja.addMergedRegion(regionTeletrabajo);

        int fila = 6;

        XSSFRow cabecera = hoja.createRow(fila);
        
        XSSFCell celdaAsistencia = cabecera.createCell(0);
        XSSFRichTextString textoAsistencia = new XSSFRichTextString("ASISTENCIA");
        celdaAsistencia.setCellValue(textoAsistencia);
        celdaAsistencia.setCellStyle(estiloCabecera);

        XSSFCell celdaSala = cabecera.createCell(1);
        XSSFRichTextString textoSala = new XSSFRichTextString("SALA");
        celdaSala.setCellValue(textoSala);
        celdaSala.setCellStyle(estiloCabecera);
        
        XSSFCell celdaCodCurso = cabecera.createCell(2);
        XSSFRichTextString textoCodCurso = new XSSFRichTextString("CODIGO CURSO");
        celdaCodCurso.setCellValue(textoCodCurso);
        celdaCodCurso.setCellStyle(estiloCabecera);
        
        XSSFCell celdaDia = cabecera.createCell(3);
        XSSFRichTextString textoDia = new XSSFRichTextString("DÍA");
        celdaDia.setCellValue(textoDia);
        celdaDia.setCellStyle(estiloCabecera);

        XSSFCell celdaModulo = cabecera.createCell(4);
        XSSFRichTextString textoModulo = new XSSFRichTextString("MODULO");
        celdaModulo.setCellValue(textoModulo);
        celdaModulo.setCellStyle(estiloCabecera);
        
        XSSFCell celdaTipo = cabecera.createCell(5);
        XSSFRichTextString textoTipo = new XSSFRichTextString("TIPO");
        celdaTipo.setCellValue(textoTipo);
        celdaTipo.setCellStyle(estiloCabecera);
        
        XSSFCell celdaAsignatura = cabecera.createCell(6);
        XSSFRichTextString textoAsignatura = new XSSFRichTextString("ASIGNATURA");
        celdaAsignatura.setCellValue(textoAsignatura);
        celdaAsignatura.setCellStyle(estiloCabecera);
        
        XSSFCell celdaProfesor = cabecera.createCell(7);
        XSSFRichTextString textoProfesor = new XSSFRichTextString("PROFESOR");
        celdaProfesor.setCellValue(textoProfesor);
        celdaProfesor.setCellStyle(estiloCabecera);

        fila++;
        getGrid(hoja, fila, agno, sem);

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

        //LogUtil.setLogCurso(genericSession.getRut(), curso);
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
    private static void getGrid(XSSFSheet hoja, int fila, Integer agno, Integer sem) {

        int filaAux = fila;
        for(String dia : new String[] {"L", "M", "W", "J", "V", "S"}) {
            List<Horario> listaHorario = ContextUtil.getDAO().getHorarioPersistence(ActionUtil.getDBUser()).getHorarioFAEByAgnoSem(agno, sem, dia);

            for (Horario horario : listaHorario) {
                XSSFRow rowExcel = hoja.createRow(filaAux);

                XSSFCell celdaEnBlanco = rowExcel.createCell(0);
                XSSFRichTextString textoEnBlanco = new XSSFRichTextString("");
                celdaEnBlanco.setCellValue(textoEnBlanco);

                XSSFCell celdaSala = rowExcel.createCell(1);
                XSSFRichTextString textoSala = new XSSFRichTextString(horario.getSala().getSalaNum());
                celdaSala.setCellValue(textoSala);
                
                XSSFCell celdaCodCurso = rowExcel.createCell(2);
                XSSFRichTextString textCodCurso = new XSSFRichTextString(horario.getCurso().getId().getCodigoCorto("-"));
                celdaCodCurso.setCellValue(textCodCurso);

                XSSFCell celdaDia = rowExcel.createCell(3);
                XSSFRichTextString textoDia = new XSSFRichTextString(horario.getDia().getDiaNom());
                celdaDia.setCellValue(textoDia);

                XSSFCell celdaModulo = rowExcel.createCell(4);
                XSSFRichTextString textoModulo = new XSSFRichTextString(horario.getId().getHorModulo().toString());
                celdaModulo.setCellValue(textoModulo);
                
                XSSFCell celdaTipo = rowExcel.createCell(5);
                XSSFRichTextString textoTipo = new XSSFRichTextString(horario.getCurso().getCurTipo());
                celdaTipo.setCellValue(textoTipo);

                XSSFCell celdaAsignatura = rowExcel.createCell(6);
                XSSFRichTextString textoAsignatura = new XSSFRichTextString(horario.getCurso().getAsignatura().getAsiNom());
                celdaAsignatura.setCellValue(textoAsignatura);
                
                XSSFCell celdaProfesor = rowExcel.createCell(7);
                XSSFRichTextString textoProfesor = new XSSFRichTextString(horario.getCurso().getCurProfesores());
                celdaProfesor.setCellValue(textoProfesor);

                filaAux++;
            }
        }
    }
}
