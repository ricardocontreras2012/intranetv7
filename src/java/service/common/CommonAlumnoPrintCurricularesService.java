/*
 * @(#)CommonAlumnoPrintCurricularesService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import domain.model.AluCar;
import infrastructure.util.common.CommonAlumnoPrintUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonAlumnoPrintCurricularesService extends CommonAlumnoPrintUtil {

    @Override
    protected String getDocumentTitle() {
        return "Antecedentes Curriculares";
    }

    @Override
    protected void putDetail(Document document, AluCar aluCar) throws DocumentException {
        putHeader2(document, aluCar);
    }

    private static void putHeader2(Document doc, AluCar aluCar) throws DocumentException {
        PdfPTable table = getTableTwoCols();
        // Datos académicos adicionales
        addRow(table, "Calidad:", aluCar.getTcalidad().getTcaDescrip());
        addRow(table, "Situación:", aluCar.getTsacademica().getTsaDescrip());
        addRow(table, "Factor Avance:", aluCar.getAluCarFunction().getFactorAvance().toString());
        addRow(table, "Factor Eficiencia:", aluCar.getAluCarFunction().getFactorEficiencia().toString());
        addRow(table, "Nivel:", aluCar.getAluCarFunction().getNivel().toString());
        addRow(table, "Progresión:", aluCar.getAluCarFunction().getProgresion());

        doc.add(table);
    }
}
