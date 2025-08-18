/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.convalidacion.secretariadocente;

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
public class SecretariaDocenteGetExcelService {

    /**
     *
     * @param genericSession
     * @param secreSession
     * @param key
     * @param file
     * @return
     */
    public String service(GenericSession genericSession, SecretariaSession secreSession, String key, File file) {        
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
