/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import domain.model.AluCar;
import domain.model.CartolaView;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.PdfUtil;
import infrastructure.util.common.CommonAlumnoPrintUtil;
import java.awt.Color;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class PrintCartolaService extends CommonAlumnoPrintUtil {
    
    @Override
    protected String getDocumentTitle() {
        return "Cartola de Calificaciones";
    }

    @Override
    protected void putDetail(Document document, AluCar aluCar) throws DocumentException {
        putHeader2(document, aluCar);
        putCartola(document, aluCar);
    }    

    private void putHeader2(Document doc, AluCar aluCar) throws DocumentException {
        PdfPTable table = getTableTwoCols();
        addRow(table, "Promedio:", formatDecimal(aluCar.getAluCarFunction().getPromedio()));
        addRow(table, "Avance:", formatDecimal(100 * aluCar.getAluCarFunction().getFactorAvance()) + "%");
        addRow(table, "Eficiencia:", formatDecimal(100 * aluCar.getAluCarFunction().getFactorEficiencia()) + "%");

        doc.add(table);
    }

    private void putCartola(Document doc, AluCar aluCar) {
        List<CartolaView> lista = ContextUtil.getDAO()
                .getCartolaViewRepository(ActionUtil.getDBUser())
                .find(aluCar);

        PdfUtil.putBlank(doc);
        PdfPTable table = creaTabla();

        // Procesar la lista y añadir filas a la tabla
        IntStream.range(0, lista.size()).forEach(i -> {
            CartolaView cartola = lista.get(i);
            Color myColor = COLORS.get(i % 2); // Alternar entre blanco y gris

            // Añadir celdas a la tabla
            Stream.of(
                    cartola.getId().getCartAsign().toString(),
                    cartola.getId().getCartElect(),
                    cartola.getCartCoord(),
                    cartola.getCartSecc() == null ? "" : cartola.getCartSecc().toString(),
                    cartola.getCartNombreCompleto(),
                    cartola.getCartHC().toString(),
                    cartola.getId().getCartSem().toString(),
                    cartola.getId().getCartAgno().toString(),
                    cartola.getCartNotaFin(),
                    cartola.getCartSitAlu(),
                    cartola.getCartProc()
            ).map(value -> getPdfPCell(value, Element.ALIGN_RIGHT, myColor))
                    .forEach(table::addCell);
        });

        try {
            doc.add(table);
        } catch (DocumentException e) {
            throw new RuntimeException("Error adding table to document", e);
        }
    }

    private PdfPTable creaTabla() {
        float[] columnWidths = {3, 1, 1, 1, 20, 2, 2, 2, 2, 2, 2};

        PdfPTable table = new PdfPTable(11);
        table.setTotalWidth(512);
        table.setWidths(columnWidths);
        table.setLockedWidth(true);

        Color myColor = new Color(204, 204, 204);

        PdfPCell codCell = getPdfPCell("CÓDIGO", Element.ALIGN_LEFT, myColor);
        codCell.setColspan(4);
        table.addCell(codCell);
        table.addCell(getPdfPCell("ASIGNATURA", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("H/C", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("SEM", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("AÑO", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("NOTA", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("SIT", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("PROC", Element.ALIGN_CENTER, myColor));

        table.setHeaderRows(1);

        return table;
    }    
}
