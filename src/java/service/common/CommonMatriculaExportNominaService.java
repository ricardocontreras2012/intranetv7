/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import domain.model.AluCar;
import domain.model.Alumno;
import domain.model.MatriculaHistorico;
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
import session.WorkSession;
import infrastructure.support.MiCarreraSupport;
import infrastructure.util.DateUtil;
import infrastructure.util.LogUtil;
import static infrastructure.util.SystemParametersUtil.PATH_TEMP_FILES;
import infrastructure.util.common.CommonExcelUtil;
import infrastructure.util.common.CommonFacultadUtil;

/**
 *
 * @author Administrador
 */
public class CommonMatriculaExportNominaService {

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
        WorkSession ws = genericSession.getWorkSession(key);
        MiCarreraSupport carrera = ws.getMiCarreraSupport();

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

        XSSFRichTextString textoFacultad = new XSSFRichTextString(CommonFacultadUtil.getNombrexTcarrera(carrera.getTcrCtip(), carrera.getEspCod()).toUpperCase());

        celdaFacultad.setCellValue(textoFacultad);
        celdaFacultad.setCellStyle(estiloMembrete);
        CellRangeAddress regionFacultad = new CellRangeAddress(1, 1, (short) 2, (short) 4);
        hoja.addMergedRegion(regionFacultad);

        int fila = 5;
        XSSFRow titulo = hoja.createRow(fila);
        XSSFCell celdaTitulo = titulo.createCell(3);
        XSSFRichTextString textoTitulo = new XSSFRichTextString("NÓMINA DE ALUMNOS MATRICULADOS");

        celdaTitulo.setCellValue(textoTitulo);
        celdaTitulo.setCellStyle(estiloTitulo);

        CellRangeAddress regionTitulo = new CellRangeAddress(fila, fila, (short) 3, (short) 7);

        hoja.addMergedRegion(regionTitulo);
        fila++;

        XSSFRow carreraRow = hoja.createRow(fila);
        XSSFCell celdaCarrera = carreraRow.createCell(3);
        XSSFRichTextString textoCarrera = new XSSFRichTextString(carrera.getNombreCarrera().toUpperCase());

        celdaCarrera.setCellValue(textoCarrera);
        celdaCarrera.setCellStyle(estiloTitulo);

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

        XSSFCell celdaCabCarrera = cabecera.createCell(5);
        XSSFRichTextString textoCabCarrera = new XSSFRichTextString("CARRERA");
        celdaCabCarrera.setCellValue(textoCabCarrera);
        celdaCabCarrera.setCellStyle(estiloCabecera);

        XSSFCell celdaCabAgno = cabecera.createCell(6);
        XSSFRichTextString textoCabAgno = new XSSFRichTextString("AÑO ING");
        celdaCabAgno.setCellValue(textoCabAgno);
        celdaCabAgno.setCellStyle(estiloCabecera);

        XSSFCell celdaCabSem = cabecera.createCell(7);
        XSSFRichTextString textoCabSem = new XSSFRichTextString("SEM ING");
        celdaCabSem.setCellValue(textoCabSem);
        celdaCabSem.setCellStyle(estiloCabecera);

        XSSFCell celdaCabFecha = cabecera.createCell(8);
        XSSFRichTextString textoCabFecha = new XSSFRichTextString("FECHA MAT");
        celdaCabFecha.setCellValue(textoCabFecha);
        celdaCabFecha.setCellStyle(estiloCabecera);

        XSSFCell celdaCabUsach = cabecera.createCell(9);
        XSSFRichTextString textoCabUsach = new XSSFRichTextString("CORREO USACH");
        celdaCabUsach.setCellValue(textoCabUsach);
        celdaCabUsach.setCellStyle(estiloCabecera);

        XSSFCell celdaCabPersonal = cabecera.createCell(10);
        XSSFRichTextString textoCabPersonal = new XSSFRichTextString("CORREO PERSONAL");
        celdaCabPersonal.setCellValue(textoCabPersonal);
        celdaCabPersonal.setCellStyle(estiloCabecera);

        fila++;
        getGrid(ws.getMatriculaList(), hoja, fila);

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

        LogUtil.setLog(genericSession.getRut(), ws.getNombreCarrera());
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
    private static void getGrid(List<MatriculaHistorico> nomina,
            XSSFSheet hoja, int fila) {

        int filaAux = fila;


        for (MatriculaHistorico mat : nomina) {
            AluCar aluCar = mat.getAluCar();
            Alumno alumno = aluCar.getAlumno();
            XSSFRow rowExcel = hoja.createRow(filaAux);

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
            XSSFRichTextString textoNombre = new XSSFRichTextString(alumno.getAluNombre());
            celdaNombre.setCellValue(textoNombre);

            XSSFCell celdaCarrera = rowExcel.createCell(5);
            XSSFRichTextString textoCarrera = new XSSFRichTextString(aluCar.getId().getAcaCodCar().toString());
            celdaCarrera.setCellValue(textoCarrera);
            
            XSSFCell celdaAgno = rowExcel.createCell(6);
            XSSFRichTextString textoAgno = new XSSFRichTextString(aluCar.getId().getAcaAgnoIng().toString());
            celdaAgno.setCellValue(textoAgno);
            
            XSSFCell celdaSem = rowExcel.createCell(7);
            XSSFRichTextString textoSem = new XSSFRichTextString(aluCar.getId().getAcaSemIng().toString());
            celdaSem.setCellValue(textoSem);
            
            XSSFCell celdaFecha = rowExcel.createCell(8);
            XSSFRichTextString textoFecha = new XSSFRichTextString( DateUtil.getFormatedDate(mat.getMathFecha(),"dd-MM-yyyy"));
            celdaFecha.setCellValue(textoFecha);
            
            XSSFCell celdaUsach = rowExcel.createCell(9);
            XSSFRichTextString textoUsach = new XSSFRichTextString(alumno.getAluEmailUsach());
            celdaUsach.setCellValue(textoUsach);
            
            XSSFCell celdaPersonal = rowExcel.createCell(10);
            XSSFRichTextString textoPersonal = new XSSFRichTextString(alumno.getAluEmail());
            celdaPersonal.setCellValue(textoPersonal);

            filaAux++;
        }
    }
}
