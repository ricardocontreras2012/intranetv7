/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.util.common;

import java.io.FileInputStream;
import java.io.InputStream;
import static java.lang.Math.floor;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import static org.apache.poi.ss.usermodel.Workbook.PICTURE_TYPE_JPEG;
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
import static org.apache.struts2.ServletActionContext.getServletContext;
import static infrastructure.util.SystemParametersUtil.UNIVERSITY_LOGO_PATH3;

/**
 *
 * @author Ricardo
 */
public class CommonExcelUtil {
    public static void putLogo(XSSFWorkbook libro, XSSFSheet hoja) throws Exception {

        InputStream inputStream = new FileInputStream(getServletContext().getRealPath(UNIVERSITY_LOGO_PATH3));
        byte[] imageBytes = IOUtils.toByteArray(inputStream);
        int pictureureIdx = libro.addPicture(imageBytes, PICTURE_TYPE_JPEG);
        inputStream.close();

        CreationHelper helper = libro.getCreationHelper();
        ClientAnchor anchor = helper.createClientAnchor();

        anchor.setCol1(0); //Column A
        anchor.setRow1(0); //Row 1
        anchor.setCol2(2); //Column C
        anchor.setRow2(5); //Row 4

        Picture pict = hoja.createDrawingPatriarch().createPicture(anchor, pictureureIdx);
        pict.resize(0.70, 0.90);

    }

     public static int calculateColWidth(int width) {
        if (width > 254) {
            return 65280;    // Maximum allowed column width.
        }

        if (width > 1) {
            int floor = (int) floor(width / 5.0);
            int factor = 30 * floor;

            return 450 + factor + (width - 1) * 250;
        } else {
            return 450;    // default to column size 1 if zero, one or
        }
    }
     
    public static void putCabeceraFacultad(XSSFWorkbook libro, XSSFSheet hoja, XSSFFont fuente,  XSSFCellStyle estiloCabecera, XSSFCellStyle estiloCabecera2, IndexedColorMap colorMap, String facultadName)
    {
        XSSFCellStyle estiloMembrete = libro.createCellStyle();
        estiloMembrete.setFont(fuente);
        estiloMembrete.setAlignment(HorizontalAlignment.CENTER);

        estiloCabecera.setFont(fuente);
        estiloCabecera.setAlignment(HorizontalAlignment.CENTER);
        estiloCabecera.setVerticalAlignment(VerticalAlignment.TOP);
        XSSFColor tan = new XSSFColor(new java.awt.Color(173, 216, 230), colorMap);
        estiloCabecera.setFillForegroundColor(tan);
        estiloCabecera.setFillPattern(FillPatternType.SOLID_FOREGROUND);

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
        XSSFRichTextString textoFacultad = new XSSFRichTextString(facultadName);
        celdaFacultad.setCellValue(textoFacultad);
        celdaFacultad.setCellStyle(estiloMembrete);
        CellRangeAddress regionFacultad = new CellRangeAddress(1, 1, (short) 2, (short) 4);
        hoja.addMergedRegion(regionFacultad);

    }
}
