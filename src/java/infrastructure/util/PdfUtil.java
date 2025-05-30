/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.util;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import static com.lowagie.text.FontFactory.register;
import com.lowagie.text.Paragraph;
import java.awt.color.ICC_Profile;
import java.io.File;
import java.io.FileInputStream;
import static org.apache.struts2.ServletActionContext.getServletContext;

/**
 *
 * @author Ricardo
 */
public class PdfUtil {

    public static Font getFont(String font, float fSize, int style) {
        String path = getServletContext().getRealPath("/fonts/local/" + font + ".ttf");
        register(path, font + "_font");

        return FontFactory.getFont(font, fSize, style);
    }

    public static void putBlank(Document doc) {
        Paragraph p = new Paragraph("\n");
        doc.add(p);
    }
    
    public static void putBlank(Document doc, Font font) {
        Paragraph p = new Paragraph("\n", font);
        doc.add(p);
    }

    public static String createValidXMPMetadata(String title, String description) {
        return "<?xpacket begin='' id='W5M0MpCehiHzreSzNTczkc9d'?>"
                + "<x:xmpmeta xmlns:x='adobe:ns:meta/'>"
                + "  <rdf:RDF xmlns:rdf='http://www.w3.org/1999/02/22-rdf-syntax-ns#'>"
                + "    <rdf:Description rdf:about=''"
                + "      xmlns:dc='http://purl.org/dc/elements/1.1/'"
                + "      xmlns:xmp='http://ns.adobe.com/xap/1.0/'"
                + "      xmlns:pdf='http://ns.adobe.com/pdf/1.3/'"
                + "      xmlns:pdfaid='http://www.aiim.org/pdfa/ns/id/'>"
                + "      <dc:title>"
                + "        <rdf:Alt>"
                + "          <rdf:li xml:lang='x-default'>" + title + "</rdf:li>"
                + "        </rdf:Alt>"
                + "      </dc:title>"
                + "      <dc:creator>"
                + "        <rdf:Seq>"
                + "          <rdf:li>FAE-USACH</rdf:li>"
                + "        </rdf:Seq>"
                + "      </dc:creator>"
                + "      <dc:description>"
                + "        <rdf:Alt>"
                + "          <rdf:li xml:lang='x-default'>" + description + "</rdf:li>"
                + "        </rdf:Alt>"
                + "      </dc:description>"
                + "      <dc:format>application/pdf</dc:format>"
                + "      <pdf:Producer>OpenPDF</pdf:Producer>"
                + "      <pdfaid:part>3</pdfaid:part>"
                + "      <pdfaid:conformance>B</pdfaid:conformance>"
                + "    </rdf:Description>"
                + "  </rdf:RDF>"
                + "</x:xmpmeta>"
                + "<?xpacket end='w'?>";
    }

    public static ICC_Profile loadICCProfile(String path) throws Exception {
        try (FileInputStream fis = new FileInputStream(new File(path))) {
            return ICC_Profile.getInstance(fis);
        }
    }
}
