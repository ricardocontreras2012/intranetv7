/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import domain.model.AluCar;
import domain.model.Ccalidad;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.DateUtil;
import infrastructure.util.PdfUtil;
import infrastructure.util.common.CommonAlumnoPrintUtil;
import java.awt.Color;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author Usach
 */
public class CommonAlumnoPrintCalidadesService extends CommonAlumnoPrintUtil {

    @Override
    protected String getDocumentTitle() {
        return "Calidades Académicas";
    }

    @Override
    protected void putDetail(Document doc, AluCar aluCar) throws DocumentException {
        List<Ccalidad> lista = ContextUtil.getDAO()
                .getCcalidadRepository(ActionUtil.getDBUser())
                .find(aluCar);

        PdfUtil.putBlank(doc);
        PdfPTable table = creaTabla();

        // Procesar la lista y añadir filas a la tabla
        IntStream.range(0, lista.size()).forEach(i -> {
            Ccalidad calidad = lista.get(i);
            Color myColor = COLORS.get(i % 2);

            // Añadir celdas a la tabla
            Stream.of(
                    calidad.getId().getCcaAgno().toString(),
                    calidad.getId().getCcaSem().toString(),
                    calidad.getCcaFecha() == null ? "" : DateUtil.getFormattedDate(calidad.getCcaFecha(), "dd-MM-yyyy"),
                    calidad.getTcalidad().getTcaDescrip()
            ).map(value -> getPdfPCell(value, Element.ALIGN_RIGHT, myColor))
                    .forEach(table::addCell);
        });

        try {
            doc.add(table);
        } catch (DocumentException e) {
            throw new RuntimeException("Error adding table to document", e);
        }

    }

    private static PdfPTable creaTabla() {
        float[] columnWidths = {2, 1, 4, 20};

        PdfPTable table = new PdfPTable(4);
        table.setTotalWidth(512);
        table.setWidths(columnWidths);
        table.setLockedWidth(true);

        Color myColor = new Color(204, 204, 204);

        table.addCell(getPdfPCell("AÑO", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("SEM", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("FECHA", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("CALIDAD", Element.ALIGN_CENTER, myColor));

        table.setHeaderRows(1);

        return table;
    }
}
