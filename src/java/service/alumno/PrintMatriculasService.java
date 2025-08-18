/*
 * @(#)PrintMatriculasService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import domain.model.AluCar;
import java.awt.Color;
import java.util.List;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.PdfUtil;
import domain.model.MatriculaHistorico;
import infrastructure.util.DateUtil;
import infrastructure.util.common.CommonAlumnoPrintUtil;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class PrintMatriculasService extends CommonAlumnoPrintUtil {
    
    @Override
    protected String getDocumentTitle() {
        return "Matrículas";
    }

    @Override
    protected void putDetail(Document doc, AluCar aluCar) throws DocumentException {
        List<MatriculaHistorico> lista = ContextUtil.getDAO()
                .getMatriculaHistoricoRepository(ActionUtil.getDBUser())
                .find(aluCar);

        PdfUtil.putBlank(doc);
        PdfPTable table = creaTabla();

        // Procesar la lista y añadir filas a la tabla
        IntStream.range(0, lista.size()).forEach(i -> {
            MatriculaHistorico mat = lista.get(i);
            Color myColor = COLORS.get(i % 2); // Alternar entre blanco y gris

            // Añadir celdas a la tabla
            Stream.of(
                    mat.getId().getMathAgno().toString(),
                    mat.getId().getMathSem().toString(),
                    mat.getMathFecha() == null ? "" : DateUtil.getFormattedDate(mat.getMathFecha(), "dd-MM-yyyy")
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
        float[] columnWidths = {3, 1, 4};

        PdfPTable table = new PdfPTable(3);
        table.setTotalWidth(150);
        table.setWidths(columnWidths);
        table.setLockedWidth(true);

        Color myColor = new Color(204, 204, 204);

        table.addCell(getPdfPCell("AÑO", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("SEM", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("FECHA", Element.ALIGN_CENTER, myColor));

        table.setHeaderRows(1);

        return table;
    }
}
