/*
 * @(#)TeleTrabajoExportReporteService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.teletrabajo;

import domain.model.Funcionario;
import domain.model.ActividadTeletrabajo;
import infrastructure.util.ActionInputStreamUtil;
import java.io.*;
import java.util.*;
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
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getFormattedDate;
import infrastructure.util.FormatUtil;
import static infrastructure.util.SystemParametersUtil.DATE_FORMAT;
import infrastructure.util.common.CommonExcelUtil;
import infrastructure.util.common.CommonSequenceUtil;
import session.WorkSession;

/**
 *
 * @author Felipe and Javier
 */
public final class TeleTrabajoExportReporteService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * 
     * @return
     * @throws java.lang.Exception
     */
    
     public ActionInputStreamUtil service(GenericSession genericSession, String key) throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);

        String fileName = "REPORTE_TELETRABAJO_" + CommonSequenceUtil.getDocumentSeq() + ".xlsx";
        String mimeType = FormatUtil.getMimeType(fileName);

        return new ActionInputStreamUtil(fileName, mimeType, getInput(ws, fileName));
    }

    public InputStream getInput(WorkSession ws, String name) throws Exception {
    
        InputStream retValue = null;
        String file;
        FileOutputStream fileOut = null;

        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja = libro.createSheet("hoja");
        
        hoja.setColumnWidth(0, CommonExcelUtil.calculateColWidth(10));
        hoja.setColumnWidth(1, CommonExcelUtil.calculateColWidth(10));
        hoja.setColumnWidth(2, CommonExcelUtil.calculateColWidth(45));
        hoja.setColumnWidth(3, CommonExcelUtil.calculateColWidth(15));
        hoja.setColumnWidth(4, CommonExcelUtil.calculateColWidth(45));
        hoja.setColumnWidth(5, CommonExcelUtil.calculateColWidth(50));
        hoja.setColumnWidth(6, CommonExcelUtil.calculateColWidth(15));

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
        XSSFRichTextString textoTeletrabajo = new XSSFRichTextString("Reporte de Teletrabajo");
        celdaTeletrabajo.setCellValue(textoTeletrabajo);
        celdaTeletrabajo.setCellStyle(estiloMembrete);
        CellRangeAddress regionTeletrabajo = new CellRangeAddress(1, 1, (short) 2, (short) 4);
        hoja.addMergedRegion(regionTeletrabajo);

        int fila = 6;

        XSSFRow cabecera = hoja.createRow(fila);
        
        XSSFCell celdaCabFecha = cabecera.createCell(0);
        XSSFRichTextString textoCabFecha = new XSSFRichTextString("FECHA");
        celdaCabFecha.setCellValue(textoCabFecha);
        celdaCabFecha.setCellStyle(estiloCabecera);

        XSSFCell celdaCabRutJefe = cabecera.createCell(1);
        XSSFRichTextString textoCabRutJefe = new XSSFRichTextString("RUT_JEFE");
        celdaCabRutJefe.setCellValue(textoCabRutJefe);
        celdaCabRutJefe.setCellStyle(estiloCabecera);
        
        XSSFCell celdaCabNombreJefe = cabecera.createCell(2);
        XSSFRichTextString textoCabNombreJefe = new XSSFRichTextString("NOMBRE_JEFE");
        celdaCabNombreJefe.setCellValue(textoCabNombreJefe);
        celdaCabNombreJefe.setCellStyle(estiloCabecera);

        XSSFCell celdaCabRutFuncionario = cabecera.createCell(3);
        XSSFRichTextString textoCabRutFuncionario = new XSSFRichTextString("RUT_FUNCIONARIO");
        celdaCabRutFuncionario.setCellValue(textoCabRutFuncionario);
        celdaCabRutFuncionario.setCellStyle(estiloCabecera);
        
        XSSFCell celdaCabNombreFun = cabecera.createCell(4);
        XSSFRichTextString textoCabNombreFun = new XSSFRichTextString("NOMBRE_FUNCIONARIO");
        celdaCabNombreFun.setCellValue(textoCabNombreFun);
        celdaCabNombreFun.setCellStyle(estiloCabecera);
        
        XSSFCell celdaCabDescripcion = cabecera.createCell(5);
        XSSFRichTextString textoCabDescripcion = new XSSFRichTextString("DESCRIPCION");
        celdaCabDescripcion.setCellValue(textoCabDescripcion);
        celdaCabDescripcion.setCellStyle(estiloCabecera);
        
        XSSFCell celdaCabEstado = cabecera.createCell(6);
        XSSFRichTextString textoCabEstado = new XSSFRichTextString("ESTADO");
        celdaCabEstado.setCellValue(textoCabEstado);
        celdaCabEstado.setCellStyle(estiloCabecera);

        fila++;
        getGrid(hoja, fila);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        libro.write(buffer);
        libro.close();

        return new ByteArrayInputStream(buffer.toByteArray());
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
    private static void getGrid(XSSFSheet hoja, int fila) {

        int filaAux = fila;
        List<ActividadTeletrabajo> actividadesTeletrabajo = ContextUtil.getDAO().getActividadTeletrabajoPersistence("TT").findAll();

        for (ActividadTeletrabajo actividad : actividadesTeletrabajo) {
            Funcionario jefe = ContextUtil.getDAO().getFuncionarioPersistence("TT").find(actividad.getFuncionarioTeletrabajo().getFtelRutJefe());
            
            XSSFRow rowExcel = hoja.createRow(filaAux);
            
            XSSFCell celdaFecha = rowExcel.createCell(0);
            XSSFRichTextString textoFecha = new XSSFRichTextString(getFormattedDate(actividad.getId().getAtelFecha(), DATE_FORMAT));
            celdaFecha.setCellValue(textoFecha);

            XSSFCell celdaRutJefe = rowExcel.createCell(1);
            XSSFRichTextString textoRutJefe = new XSSFRichTextString(jefe.getRut());
            celdaRutJefe.setCellValue(textoRutJefe);
            
            XSSFCell celdaNombreJefe = rowExcel.createCell(2);
            XSSFRichTextString textoNombreJefe = new XSSFRichTextString(jefe.getNombre());
            celdaNombreJefe.setCellValue(textoNombreJefe);

            XSSFCell celdaRutFuncionario = rowExcel.createCell(3);
            XSSFRichTextString textoRutFuncionario = new XSSFRichTextString(actividad.getFuncionarioTeletrabajo().getFuncionario().getRut());
            celdaRutFuncionario.setCellValue(textoRutFuncionario);
            
            XSSFCell celdaNombreFun = rowExcel.createCell(4);
            XSSFRichTextString textoNombreFun = new XSSFRichTextString(actividad.getFuncionarioTeletrabajo().getFuncionario().getNombre());
            celdaNombreFun.setCellValue(textoNombreFun);
            
            XSSFCell celdaDescripcion = rowExcel.createCell(5);
            XSSFRichTextString textoDescripcion = new XSSFRichTextString(actividad.getAtelDes());
            celdaDescripcion.setCellValue(textoDescripcion);
            
            XSSFCell celdaEstado = rowExcel.createCell(6);
            XSSFRichTextString textoEstado = new XSSFRichTextString(actividad.getEstado().getEatDes());
            celdaEstado.setCellValue(textoEstado);

            filaAux++;
        }
    }
}
