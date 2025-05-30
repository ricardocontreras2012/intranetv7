/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.util;

import com.lowagie.text.Font;
import static com.lowagie.text.Font.NORMAL;
import static com.lowagie.text.FontFactory.getFont;
import static com.lowagie.text.FontFactory.register;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfSignatureAppearance;
import com.lowagie.text.pdf.PdfStamper;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.Date;
import org.apache.struts2.ServletActionContext;
import static org.apache.struts2.ServletActionContext.getServletContext;

/**
 *
 * @author Administrador
 */
public class SignUtil {

    public static void sign(String input, String output, Integer folio, String rut, Date fecha) {

        try {

            /*KeyStore ks = KeyStore.getInstance("pkcs12");
            ks.load(new FileInputStream(ServletActionContext.getServletContext().getRealPath("/ks/intra.keystore")), "dante.2001".toCharArray());
            String alias = ks.aliases().nextElement();
            PrivateKey key = (PrivateKey) ks.getKey(alias, "dante.2001".toCharArray());
            Certificate[] chain = ks.getCertificateChain(alias);*/
            
            
            KeyStore ks = KeyStore.getInstance("pkcs12");
            ks.load(new FileInputStream(ServletActionContext.getServletContext().getRealPath("/ks/test-pkcs12.jks")), "dante.2001".toCharArray());
            String alias = ks.aliases().nextElement();
            PrivateKey key = (PrivateKey) ks.getKey(alias, "2012".toCharArray());
            Certificate[] chain = ks.getCertificateChain(alias);
            
            /////

            PdfReader reader = new PdfReader(input);
            FileOutputStream fout = new FileOutputStream(output);

            PdfStamper stp = PdfStamper.createSignature(reader, fout, '?');
            PdfSignatureAppearance sap = stp.getSignatureAppearance();
            sap.setCrypto(key, chain, null, PdfSignatureAppearance.WINCER_SIGNED);
            sap.setReason("Firma Digital Certificado " + folio);
            sap.setLocation("FAE_USACH");

            sap.setLayer2Text("Firma Digital Intranet FAE-USACH\nCertificado Alumno Regular " + rut + "\nFolio " + folio + "\n" + fecha);

            sap.setLayer2Font(getSignFont());
            sap.setLayer4Text("");

            sap.setVisibleSignature(new Rectangle(480, 20, 600, 70), 1, null);
            stp.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Font getSignFont() {
        String path = getServletContext().getRealPath("/fonts/local/tahoma.ttf");
        register(path, "tahoma_font");
        return getFont("tahoma_font", 6.0f, NORMAL);
    }

}
