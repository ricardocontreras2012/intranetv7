/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.secretariadocente;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import session.GenericSession;
import session.SecretariaSession;

/**
 *
 * @author Ricardo Contreras S.
 */
public class SecretariaDocenteConvalidacionGetExcelService {

    /**
     *
     * @param genericSession
     * @param secreSession
     * @param key
     * @param file
     * @return
     */
    /*public static String service(GenericSession genericSession, SecretariaSession secreSession, String key, File file) {

        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFCell cellAsign;
            HSSFCell cellCursada;

            int rows; // No of rows
            rows = sheet.getPhysicalNumberOfRows();

            int cols = 0; // No of columns
            int tmp;

            // This trick ensures that we get the data properly even if it doesn't start from first few rows
            for (int i = 0; i < 10 || i < rows; i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if (tmp > cols) {
                        cols = tmp;
                    }
                }
            }

            for (int r = 0; r < rows; r++) {
                row = sheet.getRow(r);
                if (row != null) {
                    cellAsign = row.getCell(0);
                    cellCursada = row.getCell(1);
                    if (cellAsign != null) {
                        for (int i = 0; i < secreSession.getPorAprobar().size(); i++) {
                            ConvalidacionSolicitudAsign porAprobar = secreSession.getPorAprobar().get(i);

                            if (porAprobar.getAsignatura().getAsiCod() == cellAsign.getNumericCellValue()) {
                                secreSession.getPorAprobar().get(i).setCsaCursada(cellCursada.getStringCellValue());
                            }
                        }
                    }
                }
            }
        } catch (Exception | Error ioe) {
            ioe.printStackTrace();
        }

        return SUCCESS;
    }*/
    public static String service(GenericSession genericSession, SecretariaSession secreSession, String key, File file) {

System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        
        try (FileInputStream fis = new FileInputStream(file);
                POIFSFileSystem fs = new POIFSFileSystem(fis);
                HSSFWorkbook wb = new HSSFWorkbook(fs)) {

            HSSFSheet sheet = wb.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();

            for (int r = 0; r < rows; r++) {
                HSSFRow row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }

                HSSFCell cellAsign = row.getCell(0);
                HSSFCell cellCursada = row.getCell(1);

                if (cellAsign != null && cellAsign.getCellType() == CellType.NUMERIC && cellCursada != null) {
                    int codAsignatura = (int) cellAsign.getNumericCellValue();
                    String cursada = cellCursada.getStringCellValue();

                    // Filtrar y actualizar con Streams
                    secreSession.getPorAprobar().stream()
                            .filter(p -> p.getAsignatura().getAsiCod() == codAsignatura)
                            .forEach(p -> p.setCsaCursada(cursada));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

}
