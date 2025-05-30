/*
 * @(#)BarCodeUtil.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package infrastructure.util;

import static com.google.zxing.BarcodeFormat.QR_CODE;
import com.google.zxing.EncodeHintType;
import static com.google.zxing.EncodeHintType.CHARACTER_SET;
import static com.google.zxing.EncodeHintType.ERROR_CORRECTION;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import static com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.L;
import com.java4less.rbarcode.BarCode;
import static com.java4less.rbarcode.BarCode.CODE128;
import com.lowagie.text.Image;
import static com.lowagie.text.Image.getInstance;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import static java.awt.Color.black;
import static java.awt.Color.white;
import static java.awt.Font.BOLD;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_BYTE_INDEXED;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.util.EnumMap;
import java.util.Map;
import static org.apache.struts2.ServletActionContext.getServletContext;
import static infrastructure.util.SystemParametersUtil.CERTIFICATE_BARCODE_BLANK_IMAGE_PATH;

/**
 *
 * @author Ricardo Contreras S.
 */
public class BarCodeUtil {

    /**
     * Method description
     *
     *
     * @param cb
     * @param folio
     * @param verificador
     * @param margen
     * @param pago
     *
     * @throws Exception
     */

    public static void putBarCode(PdfContentByte cb, Integer folio, String verificador, Integer margen, String pago) throws Exception {

        String codigo = folio + "-" + verificador;

        BarCode bc = new BarCode();

        bc.setSize(400, 200);
        bc.barType = CODE128;
        bc.resolution = 1;
        bc.leftMarginCM = 50;
        bc.topMarginCM = 50;
        bc.checkCharacter = false;
        bc.code = codigo;
        bc.barColor = black;
        bc.backColor = white;
        bc.fontColor = black;
        bc.textFont = new java.awt.Font("Arial", BOLD, 12);
        bc.X = 1;
        bc.N = 3;

        // create rbarcode image
        java.awt.image.BufferedImage bImage = new java.awt.image.BufferedImage(bc.getSize().width,
                bc.getSize().height, TYPE_BYTE_INDEXED);
        java.awt.Graphics imgGraphics = bImage.createGraphics();

        bc.paint(imgGraphics);

        Image image = getInstance(bImage, null);
        image.setAbsolutePosition(160.0f + margen, 40.0f);
        image.scaleAbsolute(300.0f, 80.0f);
        cb.addImage(image);

        Image imageBlank = getInstance(getServletContext().getRealPath(CERTIFICATE_BARCODE_BLANK_IMAGE_PATH));
        imageBlank.setAbsolutePosition(60.0f + margen, 100.0f);
        imageBlank.scaleAbsolute(300.0f, 20.0f);
        cb.addImage(imageBlank);

        String myCodeText = ActionUtil.getURL() + "/VerificacionCertificadoQR?folio=" + folio + "&verificador=" + verificador;

        int size = 250;
        Map<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
        hintMap.put(CHARACTER_SET, "UTF-8");
        hintMap.put(ERROR_CORRECTION, L);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, QR_CODE, size,
                size, hintMap);
        int CrunchifyWidth = byteMatrix.getWidth();
        bImage = new BufferedImage(CrunchifyWidth, CrunchifyWidth, TYPE_INT_RGB);
        bImage.createGraphics();

        Graphics2D graphics = (Graphics2D) bImage.getGraphics();
        graphics.setColor(WHITE);
        graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
        graphics.setColor(BLACK);

        for (int i = 0; i < CrunchifyWidth; i++) {
            for (int j = 0; j < CrunchifyWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }

        Image qrImage = getInstance(bImage, null);
        qrImage.setAbsolutePosition(93f + margen, 63f);
        qrImage.scaleAbsolute(60.0f, 60.0f);

        cb.addImage(qrImage);
        
        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        cb.beginText();
        cb.moveText(500, 20);
        cb.setFontAndSize(bf, 10);
        cb.showText(pago);
        cb.endText();

    }
}
