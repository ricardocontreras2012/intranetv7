/*
 * @(#)CommonAlumnoPrintLogInscripcionService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

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
import domain.model.LogInscripcion;
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
public final class CommonAlumnoPrintLogInscripcionService extends CommonAlumnoPrintUtil{  
    
    @Override
    protected String getDocumentTitle() {
        return "Log de Inscripciones";
    }
    
    @Override
    protected void putDetail(Document doc, AluCar aluCar) throws DocumentException {
        List<LogInscripcion> lista = ContextUtil.getDAO()
                .getLogInscripcionRepository(ActionUtil.getDBUser())
                .find(aluCar);

        PdfUtil.putBlank(doc);
        PdfPTable table = creaTabla();

        // Procesar la lista y añadir filas a la tabla
        IntStream.range(0, lista.size()).forEach(i -> {
            LogInscripcion log = lista.get(i);
            Color myColor = COLORS.get(i % 2); // Alternar entre blanco y gris

            // Añadir celdas a la tabla
            Stream.of(
                    log.getAsignatura().getAsiCod() + " " + log.getLogElect() + " " + log.getLogCoord() + " " + log.getLogAgno() + " " + log.getLogSem(),
                    log.getAsignatura().getAsiNom(),
                    log.getLogTipoMod(),
                    log.getProcesoInscripcion().getPinsDes(),
                    log.getLogUser() == null ? "" : log.getLogUser() + " " + (log.getLogRutReali() == null ? "" : log.getLogRutReali().toString()),
                    DateUtil.getFormattedDate(log.getLogFecha(), "dd-MM-yyyy hh:mm:ss")
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
        float[] columnWidths = {10, 30, 4, 15, 10, 10};

        PdfPTable table = new PdfPTable(6);
        table.setTotalWidth(542);
        table.setWidths(columnWidths);
        table.setLockedWidth(true);

        Color myColor = new Color(204, 204, 204);

        table.addCell(getPdfPCell("CÓDIGO", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("ASIGNATURA", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("TIPO", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("PROCESO", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("REALIZADOR", Element.ALIGN_CENTER, myColor));
        table.addCell(getPdfPCell("FECHA", Element.ALIGN_CENTER, myColor));

        table.setHeaderRows(1);

        return table;
    }
}
