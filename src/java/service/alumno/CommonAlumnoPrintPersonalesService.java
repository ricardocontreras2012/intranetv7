/*
 * @(#)CommonAlumnoPrintPersonalesService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import domain.model.AluCar;
import domain.model.Alumno;
import infrastructure.util.common.CommonAlumnoPrintUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonAlumnoPrintPersonalesService extends CommonAlumnoPrintUtil {
    
    @Override
    protected String getDocumentTitle() {
        return "Antecedentes Personales";
    }

    @Override
    protected void putDetail(Document document, AluCar aluCar) throws DocumentException {
        putHeader2(document, aluCar);
    }    
    
     private void putHeader2(Document doc, AluCar aluCar) throws DocumentException {         
        Alumno alumno = aluCar.getAlumno();
        PdfPTable table = getTableTwoCols();

        // Espaciado antes de los datos de contacto
        addEmptyRow(table);
        addEmptyRow(table);

        // Datos de contacto
        addRow(table, "Correo Usach:", alumno.getAluEmailUsach());
        addRow(table, "Correo Personal:", alumno.getAluEmail());
        addRow(table, "Teléfono:", alumno.getAluFonoAlu());
        addRow(table, "Dirección:", alumno.getAluDirecAlu() + " " + alumno.getComunaAlu().getComNom() + " " + alumno.getComunaAlu().getRegion().getRegNom());

        doc.add(table);
    }
}
