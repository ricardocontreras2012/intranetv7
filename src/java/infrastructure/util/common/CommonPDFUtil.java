/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.util.common;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

/**
 *
 * @author Usach
 */
public class CommonPDFUtil {

    public static void addFooter(PdfWriter writer, Font font, Image total) {
        addFooter(writer, font, total, 612);
    }

    public static void addFooter(PdfWriter writer, Font font, Image total, int width) {
        PdfPTable footer = new PdfPTable(3);
        try {

            float[] columnWidths = {30, 20, 50};
            footer.setWidths(columnWidths);
            footer.setTotalWidth(width);
            footer.setLockedWidth(true);

            PdfPCell cDummy = new PdfPCell(new Paragraph(""));
            cDummy.setBorder(Rectangle.NO_BORDER);
            footer.addCell(cDummy);

            footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            Phrase phrase = new Phrase(String.format("Pág %d de", writer.getPageNumber()), font);
            PdfPCell cPage = new PdfPCell(phrase);
            cPage.setBorder(Rectangle.NO_BORDER);
            cPage.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cPage.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cPage.setPaddingBottom(5);
            footer.addCell(cPage);

            PdfPCell totalPageCount = new PdfPCell(total);
            totalPageCount.setBorder(Rectangle.NO_BORDER);
            footer.addCell(totalPageCount);

            PdfContentByte canvas = writer.getDirectContent();
            PdfName customTag = new PdfName("CustomTag");
            canvas.beginMarkedContentSequence(customTag);

            footer.writeSelectedRows(0, -1, 0, 40, canvas);

            canvas.endMarkedContentSequence();
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }

    }

    public static void onCloseDocument(PdfWriter writer, Document document, PdfTemplate template, Font font) {

        int totalLength = String.valueOf(writer.getPageNumber() - 1).length();
        int totalWidth = totalLength * 1;

        ColumnText.showTextAligned(template, Element.ALIGN_LEFT,
                new Phrase(String.valueOf(writer.getPageNumber() - 1), font),
                totalWidth, 5, 0);

    }
}
