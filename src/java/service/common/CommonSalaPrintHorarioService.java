/*
 * @(#)CommonSalaPrintHorarioService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import com.lowagie.text.*;
import static com.lowagie.text.Element.ALIGN_CENTER;
import static com.lowagie.text.Element.ALIGN_MIDDLE;
import com.lowagie.text.Font;
import static com.lowagie.text.Font.BOLD;
import static com.lowagie.text.Font.ITALIC;
import com.lowagie.text.Image;
import static com.lowagie.text.Image.getInstance;
import static com.lowagie.text.PageSize.LETTER;
import com.lowagie.text.pdf.*;
import static com.lowagie.text.pdf.BaseFont.createFont;
import static com.lowagie.text.pdf.PdfContentByte.ALIGN_RIGHT;
import static com.lowagie.text.pdf.PdfWriter.getInstance;
import domain.model.Dia;
import domain.model.Horario;
import domain.model.ModuloHorario;
import domain.model.Sala;
import java.awt.*;
import static java.awt.Color.BLACK;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import static org.apache.struts2.ServletActionContext.getServletContext;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getDate;
import infrastructure.util.FormatUtil;
import static infrastructure.util.SystemParametersUtil.DATE_FULL_FORMAT;
import static infrastructure.util.SystemParametersUtil.UNIVERSITY_LOGO_PATH1;
import infrastructure.util.common.CommonHorarioUtil;
import infrastructure.util.common.CommonSalaUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonSalaPrintHorarioService {

    public static ActionInputStreamUtil service(ActionCommonSupport action, GenericSession genericSession, Integer agno,
            Integer sem, String key) throws Exception
    { 
        InputStream input;
        String description;
        String name;                        
        name = "horario_" + genericSession.getWorkSession(key).getSala().getSalaNum()+".pdf";                
        input = getInput(action, genericSession, agno, sem, key );
        description = FormatUtil.getMimeType(name);
                                        
        return new ActionInputStreamUtil(name, description, input);
    }
            
    /**
     * Method description
     *
     * @param action Clase(action) que invoca al servicio.
     * @param genericSession Sesion de trabajo.
     * @param agno
     * @param sem
     * @param key LLave para acceder a los datos de la sesion.
     * @return
     * @throws java.lang.Exception
     */
    private static InputStream getInput(ActionCommonSupport action, GenericSession genericSession, Integer agno,
            Integer sem, String key)
            throws Exception {
        
        WorkSession ws = genericSession.getWorkSession(key);
        ws.setModuloHorarioList(CommonHorarioUtil.getModuloHorarioDocencia(agno, sem));
        Iterator<ModuloHorario> iterModuloHorario = ws.getModuloHorarioList().iterator();
        Sala sala = ws.getSala();
        Horario[][] horario = ws.getHorario();
        Document document = new Document(LETTER.rotate());
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PdfWriter writer = getInstance(document, buffer);
        
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        String imageUrl
                = getServletContext().getRealPath(UNIVERSITY_LOGO_PATH1);
        Image image = getInstance(imageUrl);

        image.setAbsolutePosition(50.0f, 525.0f);
        image.scaleAbsolute(39.0f, 50.0f);
        cb.addImage(image);
        putText(cb, 120, 560, action.getText("label.universidad"));
        putText(cb, 120, 540, action.getText("label.facultad"));
        putText(cb, 140, 500, action.getText("label.sala"));
        putText(cb, 140, 480, action.getText("label.sala.programacion"));
        putText(cb, 140, 460, action.getText("label.sala.capacidad"));
        putText(cb, 140, 440, action.getText("label.sala.administrada.por"));
        putText(cb, 250, 500, sala.getSalaNum() + " (" + sala.getSalaDescrip() + ')');
        putText(cb, 250, 480, sem + "/" + agno);
        putText(cb, 250, 460, sala.getSalaCapacidad().toString());
        putTextColor(cb, 250, 440, sala.getUnidad().getUniNom(), CommonSalaUtil.getColorPDF(sala));
        putDate(cb, 680, 560, getDate(DATE_FULL_FORMAT));

        float[] rows = {
            90.0f, 90.0f, 90.0f, 90.0f, 90.0f, 90.0f, 90.0f
        };
        PdfPTable table0 = new PdfPTable(rows);
        PdfPTable table1 = new PdfPTable(rows);
        PdfPTable table2 = new PdfPTable(rows);
        PdfPTable table3 = new PdfPTable(rows);

        table0.setTotalWidth(rows);
        table1.setTotalWidth(rows);
        table2.setTotalWidth(rows);
        table3.setTotalWidth(rows);

        int dias = ContextUtil.getDiaList().size();

        setCellHeader(table0, ContextUtil.getDiaList());

        for (int i = 0; i < 3; i++) {
            setCellLeft(table1, iterModuloHorario);

            for (int j = 0; j < dias; j++) {
                setCell(table1, i, j, horario);
            }
        }

        for (int i = 3; i < 6; i++) {
            setCellLeft(table2, iterModuloHorario);

            for (int j = 0; j < dias; j++) {
                setCell(table2, i, j, horario);
            }
        }

        for (int i = 6; i < 8; i++) {
            setCellLeft(table3, iterModuloHorario);

            for (int j = 0; j < dias; j++) {
                setCell(table3, i, j, horario);
            }
        }

        table0.writeSelectedRows(0, -1, 50.0F, 400.0F, cb);
        table1.writeSelectedRows(0, -1, 50.0F, 370.0F, cb);
        table2.writeSelectedRows(0, -1, 50.0F, 270.0F, cb);
        table3.writeSelectedRows(0, -1, 50.0F, 170.0F, cb);
        document.close();

        InputStream pdfStream = new ByteArrayInputStream(buffer.toByteArray());

        buffer.close();

        return pdfStream;
    }

    /**
     * Method description
     *
     * @param table
     * @param diaList
     */
    private static void setCellHeader(PdfPTable table, List<Dia> diaList) {
        PdfPCell cell = new PdfPCell(new Phrase(""));

        cell.setBorder(0);
        table.addCell(cell);

        for (Dia aDiaList : diaList) {
            String diaNombre = aDiaList.getDiaNom().toUpperCase(ContextUtil.getLocale());
            Chunk diaChunk = new Chunk(diaNombre, new Font(Font.TIMES_ROMAN, 8.0F, ITALIC));

            cell = new PdfPCell(new Phrase(diaChunk));
            cell.setFixedHeight(30.0f);
            cell.setNoWrap(false);
            cell.setVerticalAlignment(ALIGN_MIDDLE);
            cell.setHorizontalAlignment(ALIGN_CENTER);
            cell.setBorder(0);
            cell.setBackgroundColor(new Color(0xEE, 0xEE, 0xEE));
            table.addCell(cell);
        }
    }

    /**
     * Method description
     *
     * @param table
     * @param modulo
     * @param dia
     * @param horario
     * @throws Exception
     */
    private static void setCell(PdfPTable table, Integer modulo, Integer dia, Horario[][] horario) throws Exception {
        String curso = "";
        Horario horarioAux = horario[modulo][dia];

        if (horarioAux != null) {
            curso = horarioAux.getCurso().getNombreHorario();
        }

        Chunk diaModulo = new Chunk(curso, new Font(Font.TIMES_ROMAN, 8.0F, ITALIC));

        // diaModulo.setBackground(new Color(0xFF, 0x00, 0x00));
        PdfPCell cell = new PdfPCell(new Phrase(diaModulo));

        if (horarioAux != null) {
            cell.setBackgroundColor(horarioAux.getColorHexPorAsignaturaPDF());
        } else {
            cell.setBackgroundColor(new Color(0xFF, 0xFF, 0xFF));
        }

        cell.setFixedHeight(30.0f);
        cell.setNoWrap(false);
        cell.setPadding(2.0F);
        table.addCell(cell);
    }

    /**
     * Method description
     *
     * @param table
     * @param iter
     */
    private static void setCellLeft(PdfPTable table, Iterator<ModuloHorario> iter) {
        ModuloHorario moduloHorario = iter.next();
        String curso = moduloHorario.getModDesde() + '-' + moduloHorario.getModHasta();
        Chunk diaModulo = new Chunk(curso, new Font(Font.TIMES_ROMAN, 8.0F, BOLD));
        PdfPCell cell = new PdfPCell(new Phrase(diaModulo));

        cell.setBackgroundColor(new Color(0xEE, 0xEE, 0xEE));
        cell.setFixedHeight(30.0f);
        cell.setNoWrap(false);
        cell.setBorder(0);
        cell.setVerticalAlignment(ALIGN_MIDDLE);
        cell.setHorizontalAlignment(ALIGN_CENTER);
        table.addCell(cell);
    }

    /**
     * Method description
     *
     * @param cb
     * @param x
     * @param y
     * @param text
     * @throws Exception
     */
    private static void putText(PdfContentByte cb, Integer x, Integer y, String text) throws Exception {
        cb.beginText();
        cb.setFontAndSize(createFont(BaseFont.TIMES_ROMAN, "Cp1252", false), 14.0F);
        cb.setTextMatrix(x, y);
        cb.showText(text);
        cb.setColorFill(BLACK);
        cb.endText();
    }

    /**
     * Method description
     *
     * @param cb
     * @param x
     * @param y
     * @param text
     * @param color
     * @throws Exception
     */
    private static void putTextColor(PdfContentByte cb, Integer x, Integer y, String text, Color color)
            throws Exception {
        cb.rectangle(x, (y - 5), 400.0F, 20.0F);
        cb.setColorFill(color);
        cb.fillStroke();
        cb.beginText();
        cb.setColorFill(BLACK);
        cb.setFontAndSize(createFont(BaseFont.TIMES_ROMAN, "Cp1252", false), 14.0F);
        cb.setTextMatrix(x, y);
        cb.showText(text);
        cb.endText();
    }

    /**
     * Method description
     *
     * @param cb
     * @param x
     * @param y
     * @param text
     * @throws Exception
     */
    private static void putDate(PdfContentByte cb, Integer x, Integer y, String text) throws Exception {
        cb.beginText();
        cb.setFontAndSize(createFont(BaseFont.TIMES_ROMAN, "Cp1252", false), 10.0F);
        cb.setTextMatrix(x, y);
        cb.setColorFill(BLACK);
        cb.showTextAligned(ALIGN_RIGHT, text, x, y, 0.0F);
        cb.endText();
    }
}
