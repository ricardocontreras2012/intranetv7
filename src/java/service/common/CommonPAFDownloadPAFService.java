/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import com.lowagie.text.Document;
import static com.lowagie.text.PageSize.LETTER;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import static com.lowagie.text.pdf.PdfContentByte.ALIGN_CENTER;
import static com.lowagie.text.pdf.PdfContentByte.ALIGN_RIGHT;
import com.lowagie.text.pdf.PdfWriter;
import static com.lowagie.text.pdf.PdfWriter.getInstance;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import static java.security.Security.addProvider;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.FormatUtil;
import static infrastructure.util.SystemParametersUtil.PATH_CERT;
import infrastructure.util.common.CommonArchivoUtil;
import infrastructure.util.common.CommonCertificacionUtil;

/**
 *
 * @author Javier Bastián Frez Valdenegro
 */
public class CommonPAFDownloadPAFService {
    
    public ActionInputStreamUtil service(Integer rut, String key, String keyProf) {
        String name = "Certificado_Alumno_Regular_10860143-4_411265.pdf";
        String description = FormatUtil.getMimeType(name);

        return new ActionInputStreamUtil(name, description, getInput(name));
    }
    
    public static InputStream getInput(String name) {
        try {
            Document document = new Document(LETTER);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            PdfWriter writer = getInstance(document, buffer);
            //CommonCertificacionUtil.BackgroundImage bi = new CommonCertificacionUtil.BackgroundImage(60);
            //writer.setPageEvent(bi);
            addProvider(new BouncyCastleProvider());

            document.open();
            putHeader(writer.getDirectContent(), document, 0, 0);
            LugarYNiveles(writer.getDirectContent(), 0, -20);
            IdentificacionDelFuncionario(writer.getDirectContent(), document, 0, -5);
            putEstudios(writer.getDirectContent(), 20, 275, 580, 140);
            putIdentificacionDelCargo(writer.getDirectContent(), 20, 155, 580, 120);
            putOHastaCuandoSeanNecesariosSusServicios(writer.getDirectContent(), 20, 35, 580, 120);
            putPieDePagina01(writer.getDirectContent(), 20, 17, 580, 18);
            
            //## Nueva Pagina
            document.newPage();
            
            putSituacion(writer.getDirectContent(), 20, 660, 580, 120);
            putCargoQueDejaDeServir(writer.getDirectContent(), 20, 460, 580, 200);
            putCargoOActividadQueSeguiraDesempeñando(writer.getDirectContent(), 20, 130, 580, 330);
            putFirmas(writer.getDirectContent(), 20, 30, 580, 100);
            document.close();

            CommonCertificacionUtil.writeFile(buffer, PATH_CERT + name);
            buffer.close();

            return CommonArchivoUtil.getFile(name, "cert");
        } 
        catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Metodo que escribe todo lo relacionado con el logo y el titulo 
     * del documento
     *
     * @param content
     * @param coorX
     * @param coorY
     *
     * @throws Exception
     */
    private static void putHeader(PdfContentByte content, Document document, Integer coorX, Integer coorY) throws Exception {
        //###########################
        //## Ajustes de la seccion ##
        //###########################
        Integer largoCajas = 12;
        BaseFont fuente = BaseFont.createFont();
        
        //#################################
        //## Inicio de escritura de logo ##
        //#################################
        content.beginText();
        content.setFontAndSize(BaseFont.createFont(), 8);
        
        //## Logo
        content.showTextAligned(0, "UNIVERSIDAD DE SANTAIGO DE CHILE", 100 + coorX, 775 + coorY, 0);
        content.showTextAligned(0, "DEPTO. DE RECURSOS HUMANOS", 100 + coorX, 765 + coorY, 0);
        
        //## Termino de escritura de logo
        content.endText();
        
        //###################################
        //## Inicio de escritura de titulo ##
        //###################################
        content.beginText();
        content.setFontAndSize(BaseFont.createFont(), 10);
        
        //## Titulo
        content.showTextAligned(ALIGN_CENTER, "PROPUESTA Y ASUNCIÓN DE FUNCIONES", (document.getPageSize().getWidth()/2) + coorX, 725 + coorY, 0);
        
        //## Termino de escritura de titulo
        content.endText();
        
        //###################################
        //## Inicio de escritura de fecha ##
        //###################################
        content.beginText();
        content.setFontAndSize(BaseFont.createFont(), 5);
        
        //## Fecha
        content.showTextAligned(ALIGN_CENTER, "FECHA", (580 - (largoCajas*2)) + coorX, (725 + ((largoCajas/4) - (fuente.getAscentPoint("FECHA", 5)/2)) - (largoCajas*1)) + coorY, 0);
        
        //## Termino de escritura de titulo
        content.endText();
        
        //#########################
        //## Creaccion de lineas ##
        //#########################
        
        //## Original
        content.rectangle((580 - (largoCajas*4)) + coorX, (725 - (largoCajas/2)) + coorY, largoCajas*4, largoCajas/2);
        content.stroke();
        
        //## Primera copia
        content.rectangle((580 - (largoCajas*4)) + coorX, (725 - (largoCajas*1)) + coorY, largoCajas*4, largoCajas);
        content.stroke();
    }
    
    /**
     * Metodo que escribe la primera parte de la PAF, la que contiene el LUGAR y los 3 niveles.
     *
     * @param content
     * @param coorX
     * @param coorY
     *
     * @throws Exception
     */
    private static void LugarYNiveles(PdfContentByte content, Integer coorX, Integer coorY) throws Exception  {
        //################################
        //## Inicio de escritura normal ##
        //################################
        content.beginText();
        content.setFontAndSize(BaseFont.createFont(), 10);
        
        //## Texto
        content.showTextAligned(0, "LUGAR", 50 + coorX, 700 + coorY, 0);
        content.showTextAligned(0, "NIVEL 1:", 50 + coorX, 675 + coorY, 0);
        content.showTextAligned(0, "NIVEL 2:", 50 + coorX, 650 + coorY, 0);
        content.showTextAligned(0, "NIVEL 3:", 50 + coorX, 625 + coorY, 0);
        
        //## Termino de escritura normal
        content.endText();
        
        //#####################################
        //## Inicio de escritura chica ##
        //#####################################
        content.beginText();
        content.setFontAndSize(BaseFont.createFont(), 5);
        
        //## Texto
        content.showTextAligned(0, "C. de Costo", 475 + coorX, 694 + coorY, 0);
        content.showTextAligned(0, "(RECTORIA — PRO-RECTORIA — VICERRECTORIA O FACULTAD)", 100 + coorX, 669 + coorY, 0);
        content.showTextAligned(0, "(DEPARTAMENTO)", 100 + coorX, 644 + coorY, 0);
        content.showTextAligned(0, "(SECCION U OFICINA)", 100 + coorX, 619 + coorY, 0);
        content.showTextAligned(0, "USO OFICINA DE PARTES", 514 + coorX, 627 + coorY, 0);
        
        //## Termino de escritura chica
        content.endText();
        
        //#########################
        //## Creaccion de lineas ##
        //#########################
        
        content.moveTo(100 + coorX, 700 + coorY);
        content.lineTo(500 + coorX, 700 + coorY);
        content.stroke();
        
        content.moveTo(100 + coorX, 675 + coorY);
        content.lineTo(475 + coorX, 675 + coorY);
        content.stroke();
        
        content.rectangle(475 + coorX, 675 + coorY, 25, 12);
        content.stroke();
        
        content.moveTo(100 + coorX, 650 + coorY);
        content.lineTo(475 + coorX, 650 + coorY);
        content.stroke();
        
        content.rectangle(475 + coorX, 650 + coorY, 25, 12);
        content.stroke();
        
        content.moveTo(100 + coorX, 625 + coorY);
        content.lineTo(475 + coorX, 625 + coorY);
        content.stroke();
        
        content.rectangle(475 + coorX, 625 + coorY, 25, 12);
        content.stroke();
        
        content.rectangle(510 + coorX, 625 + coorY, 70, 87);
        content.stroke();
    }
    
    /**
     * Metodo que escribe la seccion IDENTIFICACION DEL FUNCIONARIO.
     * Las variables coorX y coorY mueven todo el contenido degun las correspondientes coordenadas.
     *
     * @param content
     * @param coorX
     * @param coorY
     *
     * @throws Exception
     */
    private static void IdentificacionDelFuncionario(PdfContentByte content, Document document, Integer coorX, Integer coorY) throws Exception {
        //##############################################
        //## Inicio de escritura de titulo y detalles ##
        //##############################################
        Integer largoCajas = 20;
        Integer tamanioFuenteNormal = 10;
        Integer tamanioFuentePequeña = 8;
        Integer tamanioFuenteDominuta = 5;
        BaseFont fuente = BaseFont.createFont();
        
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteNormal);
        
        //## Titulo
        content.showTextAligned(ALIGN_CENTER, "IDENTIFICACION DEL FUNCIONARIO", (document.getPageSize().getWidth()/2) + coorX, 585 + coorY, 0);
        
        //## Termino de escritura de titulo
        content.endText();
        
        //#########################
        //## Inicio de escritura ##
        //#########################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuentePequeña);
        
        //## Nombre
        content.showTextAligned(ALIGN_CENTER, "APELLIDO PATERNO", (40 + coorX + (140/2)), 552 + coorY, 0);
        content.showTextAligned(ALIGN_CENTER, "APELLIDO MATERNO", (180 + coorX + (140/2)), 552 + coorY, 0);
        content.showTextAligned(ALIGN_CENTER, "NOMBRES", (320 + coorX + (270/2)), 552 + coorY, 0);
        
        //## RUT y nacimiento
        content.showTextAligned(ALIGN_CENTER, "CEDULA IDENTIDAD", 40 + ((550/5)/2) + coorX, 520 + (largoCajas/2) - (fuente.getAscentPoint("CEDULA IDENTIDAD", tamanioFuentePequeña)/2) + coorY, 0);
        content.showTextAligned(ALIGN_CENTER, "LUGAR Y FECHA DE", 40 + ((550/5)*2) + ((550/5)/2) + coorX, 520 + ((largoCajas/4)*3) - (fuente.getAscentPoint("LUGAR Y FECHA DE", tamanioFuentePequeña)/2) + coorY, 0);
        content.showTextAligned(ALIGN_CENTER, "NACIMIENTO", 40 + ((550/5)*2) + ((550/5)/2) + coorX, 520 + (largoCajas/4) - (fuente.getAscentPoint("NACIMIENTO", tamanioFuentePequeña)/2) + coorY, 0);
        content.showTextAligned(ALIGN_CENTER, "LUGAR", 40 + ((550/5)*3) + ((550/5)/2) + coorX, 520 + (largoCajas/4) - (fuente.getAscentPoint("LUGAR", tamanioFuentePequeña)/2) + coorY, 0);
        content.showTextAligned(ALIGN_CENTER, "FECHA", 40 + ((550/5)*4) + ((550/5)/2) + coorX, 520 + (largoCajas/4) - (fuente.getAscentPoint("FECHA", tamanioFuentePequeña)/2) + coorY, 0);
        
        //## Sexo
        content.showTextAligned(0, "SEXO:", 40 + coorX, 510 - fuente.getAscentPoint("SEXO:", tamanioFuentePequeña) + coorY, 0);
        
        //## Nacionalidad, Estado civil, Correo, Correo corporativo.
        content.showTextAligned(0, "NACIONALIDAD:", 40 + fuente.getWidthPoint("SEXO:", tamanioFuentePequeña) + 2 + largoCajas + 10 + coorX, 510 - fuente.getAscentPoint("NACIONALIDAD:", tamanioFuentePequeña) + coorY, 0);
        content.showTextAligned(0, "ESTADO CIVIL:", 40 + fuente.getWidthPoint("SEXO:", tamanioFuentePequeña) + 2 + largoCajas + 10 + coorX, 510 - (fuente.getAscentPoint("NACIONALIDAD:", tamanioFuentePequeña)*3) - fuente.getAscentPoint("ESTADO CIVIL:", tamanioFuentePequeña) + coorY, 0);
        content.showTextAligned(0, "CORREO:", 40 + fuente.getWidthPoint("SEXO:", tamanioFuentePequeña) + 2 + largoCajas + 10 + coorX, 510 - (fuente.getAscentPoint("NACIONALIDAD:", tamanioFuentePequeña)*3) - (fuente.getAscentPoint("ESTADO CIVIL:", tamanioFuentePequeña)*3) - fuente.getAscentPoint("CORREO:", tamanioFuentePequeña) + coorY, 0);
        content.showTextAligned(0, "CORREO CORPORATIVO:", 40 + fuente.getWidthPoint("SEXO:", tamanioFuentePequeña) + 2 + largoCajas + 10 + coorX, 510 - (fuente.getAscentPoint("NACIONALIDAD:", tamanioFuentePequeña)*3) - (fuente.getAscentPoint("ESTADO CIVIL:", tamanioFuentePequeña)*3) - (fuente.getAscentPoint("CORREO:", tamanioFuentePequeña)*3) - fuente.getAscentPoint("CORREO CORPORATIVO:", tamanioFuentePequeña) + coorY, 0);
        
        //## Domicilio
        content.showTextAligned(0, "DOMICILIO:", 40 + coorX, 510 - (fuente.getAscentPoint("NACIONALIDAD:", tamanioFuentePequeña)*3) - (fuente.getAscentPoint("ESTADO CIVIL:", tamanioFuentePequeña)*3) - (fuente.getAscentPoint("CORREO:", tamanioFuentePequeña)*3) - (fuente.getAscentPoint("CORREO CORPORATIVO:", tamanioFuentePequeña)*3) - fuente.getAscentPoint("DOMICILIO:", tamanioFuentePequeña) + coorY, 0);
        
        //## Termino de escritura
        content.endText();
        
        //##################################
        //## Inicio de escritura diminuta ##
        //##################################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteDominuta);
        
        //## Rector - Vicerector decano
        content.showTextAligned(ALIGN_CENTER, "RECTOR - VICE-RECTOR", 545 + coorX, 500 - (tamanioFuentePequeña*9) + 2 + tamanioFuenteDominuta + coorY, 0);
        content.showTextAligned(ALIGN_CENTER, "DECANO", 545 + coorX, 500 - (tamanioFuentePequeña*9) + 2 + coorY, 0);
        
        //## Termino de escritura diminuta
        content.endText();
        
        //#########################
        //## Creaccion de lineas ##
        //#########################
        
        //## Nombre
        content.rectangle(40 + coorX, 560 + coorY, 140, largoCajas);
        content.stroke();
        
        content.rectangle(180 + coorX, 560 + coorY, 140, largoCajas);
        content.stroke();
        
        content.rectangle(320 + coorX, 560 + coorY, 270, largoCajas);
        content.stroke();
        
        //## RUT y nacimiento
        content.rectangle(40 + coorX, 520 + coorY, (550/5), largoCajas);
        content.stroke();
        
        content.rectangle(40 + (550/5) + coorX, 520 + coorY, (550/5), largoCajas);
        content.stroke();
        
        content.rectangle(40 + ((550/5)*2) + coorX, 520 + coorY, (550/5), largoCajas);
        content.stroke();
        
        content.rectangle(40 + ((550/5)*3) + coorX, 520 + coorY, (550/5), largoCajas);
        content.stroke();
        
        content.rectangle(40 + ((550/5)*4) + coorX, 520 + coorY, (550/5), largoCajas);
        content.stroke();
        
        content.moveTo(40 + ((550/5)*3) + coorX, 520 + (largoCajas/2) + coorY);
        content.lineTo(40 + ((550/5)*5) + coorX, 520 + (largoCajas/2) + coorY);
        content.stroke();
        
        //## Sexo
        content.rectangle(40 + fuente.getWidthPoint("SEXO:", tamanioFuentePequeña) + 2 + coorX, 510 - largoCajas + 2 + coorY, largoCajas, largoCajas);
        content.stroke();
        
        //## Nacionalidad, Estado civil, Correo, Correo corporativo.
        content.moveTo(220 + coorX, 510 - fuente.getAscentPoint("NACIONALIDAD:", tamanioFuentePequeña) + coorY);
        content.lineTo(500 + coorX, 510 - fuente.getAscentPoint("NACIONALIDAD:", tamanioFuentePequeña) + coorY);
        content.stroke();
        
        content.moveTo(220 + coorX, 510 - (fuente.getAscentPoint("NACIONALIDAD:", tamanioFuentePequeña)*3) - fuente.getAscentPoint("ESTADO CIVIL:", tamanioFuentePequeña) + coorY);
        content.lineTo(500 + coorX, 510 - (fuente.getAscentPoint("NACIONALIDAD:", tamanioFuentePequeña)*3) - fuente.getAscentPoint("ESTADO CIVIL:", tamanioFuentePequeña) + coorY);
        content.stroke();
        
        content.moveTo(220 + coorX, 510 - (fuente.getAscentPoint("NACIONALIDAD:", tamanioFuentePequeña)*3) - (fuente.getAscentPoint("ESTADO CIVIL:", tamanioFuentePequeña)*3) - fuente.getAscentPoint("CORREO:", tamanioFuentePequeña) + coorY);
        content.lineTo(500 + coorX, 510 - (fuente.getAscentPoint("NACIONALIDAD:", tamanioFuentePequeña)*3) - (fuente.getAscentPoint("ESTADO CIVIL:", tamanioFuentePequeña)*3) - fuente.getAscentPoint("CORREO:", tamanioFuentePequeña) + coorY);
        content.stroke();
        
        content.moveTo(220 + coorX, 510 - (fuente.getAscentPoint("NACIONALIDAD:", tamanioFuentePequeña)*3) - (fuente.getAscentPoint("ESTADO CIVIL:", tamanioFuentePequeña)*3) - (fuente.getAscentPoint("CORREO:", tamanioFuentePequeña)*3) - fuente.getAscentPoint("CORREO CORPORATIVO:", tamanioFuentePequeña) + coorY);
        content.lineTo(500 + coorX, 510 - (fuente.getAscentPoint("NACIONALIDAD:", tamanioFuentePequeña)*3) - (fuente.getAscentPoint("ESTADO CIVIL:", tamanioFuentePequeña)*3) - (fuente.getAscentPoint("CORREO:", tamanioFuentePequeña)*3) - fuente.getAscentPoint("CORREO CORPORATIVO:", tamanioFuentePequeña) + coorY);
        content.stroke();
        
        //## Domicilio
        content.moveTo(40 + fuente.getWidthPoint("SEXO:", tamanioFuentePequeña) + 2 + largoCajas + coorX, 510 - (fuente.getAscentPoint("NACIONALIDAD:", tamanioFuentePequeña)*3) - (fuente.getAscentPoint("ESTADO CIVIL:", tamanioFuentePequeña)*3) - (fuente.getAscentPoint("CORREO:", tamanioFuentePequeña)*3) - (fuente.getAscentPoint("CORREO CORPORATIVO:", tamanioFuentePequeña)*3) - fuente.getAscentPoint("DOMICILIO:", tamanioFuentePequeña) + coorY);
        content.lineTo(500 + coorX, 510 - (fuente.getAscentPoint("NACIONALIDAD:", tamanioFuentePequeña)*3) - (fuente.getAscentPoint("ESTADO CIVIL:", tamanioFuentePequeña)*3) - (fuente.getAscentPoint("CORREO:", tamanioFuentePequeña)*3) - (fuente.getAscentPoint("CORREO CORPORATIVO:", tamanioFuentePequeña)*3) - fuente.getAscentPoint("DOMICILIO:", tamanioFuentePequeña) + coorY);
        content.stroke();
        
        //## Rector - Vicerector decano
        content.rectangle(510 + coorX, 500 - (tamanioFuentePequeña*9) + coorY, 70, 87);
        content.stroke();
        
        //## Dividir seccion entera.
        content.rectangle(20 + coorX, 500 - (tamanioFuentePequeña*10) + coorY, 580, 180);
        content.stroke();
    }
    
    /**
     * Metodo que escribe la seccion ESTUDIOS de la PAF.
     * Las coordenadas que se ingresan representan la posicion de la esquina 
     * inferior izquierda de la seccion (el rectangulo que cubre toda la seccion), 
     * mientras que las otras variables numericas representan el ancho y el 
     * largo del rectangulo.
     *
     * @param content
     * @param coorX
     * @param coorY
     * @param ancho
     * @param largo
     *
     * @throws Exception
     */
    private static void putEstudios(PdfContentByte content, Integer coorX, Integer coorY, Integer ancho, Integer largo) throws Exception {
        //###########################
        //## Ajustes de la seccion ##
        //###########################
        Integer largoCajas = 10;
        Integer tamanioFuenteTitulo = 10;
        Integer tamanioFuenteNormal = 8;
        Integer tamanioFuenteDiminuta = 5;
        BaseFont fuente = BaseFont.createFont();
        
        String titulo = "ESTUDIOS";
        
        String variable01 = "ED. BASICA:";
        String variable02 = "ED. MEDIA:";
        String variable03 = "ED. UNIVERSITARIA:";
        
        String variable04 = "TITULO:";
        String variable05 = "INSTITUCION:";
        String variable06 = "FECHA:";
        String variable07 = "N° SEMESTRES:";
        
        String variable08 = "GRADOS ACADEMICOS:";
        String variable09 = "INSTITUCION:";
        String variable10 = "FECHA:";
        String variable11 = "N° SEMESTRES:";
        
        //######################
        //## Inicio de titulo ##
        //######################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteTitulo);
        
        //## Titulo
        content.showTextAligned(ALIGN_CENTER, titulo, coorX + (ancho/2), coorY + largo - 10, 0);
        
        //## Termino de titulo
        content.endText();
        
        //#########################
        //## Inicio de escritura ##
        //#########################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteNormal);
        
        //## Cacular posicion de las 3 "filas"
        Integer tercioVertical = ((coorY + largo - 20) - coorY)/3;
        
        //## Cacular posicion de las 3 "columnas"
        Integer tercioHorizontal = ((coorX + ancho -90) - (coorX + 20))/3;
        
        //## Basica
        content.showTextAligned(0, variable01, coorX + 20, coorY + (tercioVertical*3) - fuente.getAscentPoint(variable01, tamanioFuenteNormal), 0);
        content.showTextAligned(0, "SI", coorX + 20 + fuente.getWidthPoint(variable01, tamanioFuenteNormal) + 10, coorY + (tercioVertical*3) - fuente.getAscentPoint(variable01, tamanioFuenteNormal), 0);
        content.showTextAligned(0, "NO", coorX + 20 + fuente.getWidthPoint(variable01, tamanioFuenteNormal) + 10, coorY + (tercioVertical*3) - fuente.getAscentPoint(variable01, tamanioFuenteNormal) - largoCajas, 0);
        
        //## Media
        content.showTextAligned(0, variable02, (coorX + 20) + tercioHorizontal, coorY + (tercioVertical*3) - fuente.getAscentPoint(variable02, tamanioFuenteNormal), 0);
        content.showTextAligned(0, "SI", ((coorX + 20) + tercioHorizontal) + fuente.getWidthPoint(variable02, tamanioFuenteNormal) + 10, coorY + (tercioVertical*3) - fuente.getAscentPoint(variable02, tamanioFuenteNormal), 0);
        content.showTextAligned(0, "NO", ((coorX + 20) + tercioHorizontal) + fuente.getWidthPoint(variable02, tamanioFuenteNormal) + 10, coorY + (tercioVertical*3) - fuente.getAscentPoint(variable02, tamanioFuenteNormal) - largoCajas, 0);
        
        //## Universitaria
        content.showTextAligned(0, variable03, (coorX + 20) + tercioHorizontal*2, coorY + (tercioVertical*3) - fuente.getAscentPoint(variable03, tamanioFuenteNormal), 0);
        content.showTextAligned(0, "SI", ((coorX + 20) + tercioHorizontal*2) + fuente.getWidthPoint(variable03, tamanioFuenteNormal) + 10, coorY + (tercioVertical*3) - fuente.getAscentPoint(variable03, tamanioFuenteNormal), 0);
        content.showTextAligned(0, "NO", ((coorX + 20) + tercioHorizontal*2) + fuente.getWidthPoint(variable03, tamanioFuenteNormal) + 10, coorY + (tercioVertical*3) - fuente.getAscentPoint(variable03, tamanioFuenteNormal) - largoCajas, 0);
        
        //## Titulo (no el titulo de la seccion)
        content.showTextAligned(0, variable04, (coorX + 20), coorY + (tercioVertical*2) - fuente.getAscentPoint(variable04, tamanioFuenteNormal), 0);
        content.showTextAligned(0, variable05, (coorX + 20) + tercioHorizontal, coorY + (tercioVertical*2) - (fuente.getAscentPoint(variable04, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable05, tamanioFuenteNormal), 0);
        content.showTextAligned(0, variable06, (coorX + 20) + (tercioHorizontal*2), coorY + (tercioVertical*2) - (fuente.getAscentPoint(variable04, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable06, tamanioFuenteNormal), 0);
        content.showTextAligned(0, variable07, (coorX + 20), coorY + (tercioVertical*2) - (fuente.getAscentPoint(variable04, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable07, tamanioFuenteNormal), 0);
        
        //## Grados academicos
        content.showTextAligned(0, variable08, (coorX + 20), coorY + (tercioVertical*1) - fuente.getAscentPoint(variable08, tamanioFuenteNormal), 0);
        content.showTextAligned(0, variable09, (coorX + 20) + tercioHorizontal, coorY + (tercioVertical*1) - (fuente.getAscentPoint(variable08, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable09, tamanioFuenteNormal), 0);
        content.showTextAligned(0, variable10, (coorX + 20) + (tercioHorizontal*2), coorY + (tercioVertical*1) - (fuente.getAscentPoint(variable08, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable10, tamanioFuenteNormal), 0);
        content.showTextAligned(0, variable11, (coorX + 20), coorY + (tercioVertical*1) - (fuente.getAscentPoint(variable08, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable11, tamanioFuenteNormal), 0);
        
        
        //## Termino de escritura
        content.endText();
        
        //##################################
        //## Inicio de escritura diminuta ##
        //##################################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteDiminuta);
        
        //## VICE-RECTOR ADMINISTRACION Y FINANZAS
        if(largo <= 87) {
            content.showTextAligned(ALIGN_CENTER, "VICE-RECTOR", coorX + ancho -55, (coorY + (largo/2) - ((largo - 4)/2) + (tamanioFuenteDiminuta*2)) + 2, 0);
            content.showTextAligned(ALIGN_CENTER, "ADMINISTRACION Y", coorX + ancho -55, coorY + (largo/2) - ((largo - 4)/2) + tamanioFuenteDiminuta + 2, 0);
            content.showTextAligned(ALIGN_CENTER, "FINANZAS", coorX + ancho -55, coorY + (largo/2) - ((largo - 4)/2) + 2, 0);
        }
        else {
            content.showTextAligned(ALIGN_CENTER, "VICE-RECTOR", coorX + ancho -55, (coorY + (largo/2) - (87/2) + (tamanioFuenteDiminuta*2)) + 2, 0);
            content.showTextAligned(ALIGN_CENTER, "ADMINISTRACION Y", coorX + ancho -55, (coorY + (largo/2) - (87/2) + tamanioFuenteDiminuta) + 2, 0);
            content.showTextAligned(ALIGN_CENTER, "FINANZAS", coorX + ancho -55, (coorY + (largo/2) - (87/2)) + 2, 0);
        }
        
        //## Termino de escritura diminuta
        content.endText();
        
        //#########################
        //## Creaccion de lineas ##
        //#########################
        
        //## Basica
        content.rectangle(coorX + 20 + fuente.getWidthPoint(variable01, tamanioFuenteNormal) + 10 + fuente.getWidthPoint("NO", tamanioFuenteNormal) + 5, (coorY + (tercioVertical*3) - fuente.getAscentPoint(variable01, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint("SI", tamanioFuenteNormal))/2)), largoCajas, largoCajas);
        content.stroke();
        content.rectangle(coorX + 20 + fuente.getWidthPoint(variable01, tamanioFuenteNormal) + 10 + fuente.getWidthPoint("NO", tamanioFuenteNormal) + 5, ((coorY + (tercioVertical*3) - fuente.getAscentPoint(variable01, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint("SI", tamanioFuenteNormal))/2))) - largoCajas, largoCajas, largoCajas);
        content.stroke();
        
        //## Media
        content.rectangle((((coorX + 20) + tercioHorizontal) + fuente.getWidthPoint(variable02, tamanioFuenteNormal) + 10) + fuente.getWidthPoint("NO", tamanioFuenteNormal) + 5, (coorY + (tercioVertical*3) - fuente.getAscentPoint(variable02, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint("SI", tamanioFuenteNormal))/2)), largoCajas, largoCajas);
        content.stroke();
        content.rectangle((((coorX + 20) + tercioHorizontal) + fuente.getWidthPoint(variable02, tamanioFuenteNormal) + 10) + fuente.getWidthPoint("NO", tamanioFuenteNormal) + 5, ((coorY + (tercioVertical*3) - fuente.getAscentPoint(variable02, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint("SI", tamanioFuenteNormal))/2))) - largoCajas, largoCajas, largoCajas);
        content.stroke();
        
        //## Universitaria
        content.rectangle((((coorX + 20) + tercioHorizontal*2) + fuente.getWidthPoint(variable03, tamanioFuenteNormal) + 10) + fuente.getWidthPoint("NO", tamanioFuenteNormal) + 5, (coorY + (tercioVertical*3) - fuente.getAscentPoint(variable03, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint("SI", tamanioFuenteNormal))/2)), largoCajas, largoCajas);
        content.stroke();
        content.rectangle((((coorX + 20) + tercioHorizontal*2) + fuente.getWidthPoint(variable03, tamanioFuenteNormal) + 10) + fuente.getWidthPoint("NO", tamanioFuenteNormal) + 5, ((coorY + (tercioVertical*3) - fuente.getAscentPoint(variable03, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint("SI", tamanioFuenteNormal))/2))) - largoCajas, largoCajas, largoCajas);
        content.stroke();
        
        //## Titulo (no el titulo de la seccion)
        content.rectangle(((coorX + 20) + fuente.getWidthPoint(variable07, tamanioFuenteNormal) + 5), ((coorY + (tercioVertical*2) - (fuente.getAscentPoint(variable04, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable07, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable07, tamanioFuenteNormal))/2))), largoCajas, largoCajas);
        content.stroke();
        
        //## Grados academicos
        content.rectangle(((coorX + 20) + fuente.getWidthPoint(variable11, tamanioFuenteNormal) + 5), ((coorY + (tercioVertical*1) - (fuente.getAscentPoint(variable08, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable11, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable11, tamanioFuenteNormal))/2))), largoCajas, largoCajas);
        content.stroke();
        
        //## VICE-RECTOR ADMINISTRACION Y FINANZAS
        if(largo <= 87) {
            content.rectangle(coorX + ancho -90, coorY + (largo/2) - ((largo - 4)/2), 70, largo - 4);
            content.stroke();
        }
        else {
            content.rectangle(coorX + ancho -90, coorY + (largo/2) - (87/2), 70, 87);
            content.stroke();
        }
        
        //## Dividir seccion entera.
        content.rectangle(coorX, coorY, ancho, largo);
        content.stroke();
    }
    
    /**
     * Method description
     *
     * @param content
     * @param coorX
     * @param coorY
     *
     * @throws Exception
     */
    private static void putIdentificacionDelCargo(PdfContentByte content, Integer coorX, Integer coorY, Integer ancho, Integer largo) throws Exception {
        //###########################
        //## Ajustes de la seccion ##
        //###########################
        Integer largoCajas = 12;
        Integer tamanioFuenteTitulo = 10;
        Integer tamanioFuenteNormal = 8;
        Integer tamanioFuenteDiminuta = 5;
        BaseFont fuente = BaseFont.createFont();
        
        String titulo = "IDENTIFICACION DEL CARGO";
        
        String variable01 = "PLANTA:";
        String variable02 = "CARGO:";
        String variable03 = "NIVEL:";
        
        String variable04 = "GRADO:";
        String variable05 = "RANGO:";
        
        String variable06 = "CALIDAD:";
        String variable07 = " - PROPIEDAD:";
        String variable08 = " - SUPLENTE:";
        String variable09 = " - CONTRATA:";
        
        String variable10 = "A CONTAR DE:";
        
        String variable11 = "HASTA:";
        
        //######################
        //## Inicio de titulo ##
        //######################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteTitulo);
        
        //## Titulo
        content.showTextAligned(ALIGN_CENTER, titulo, coorX + (ancho/2), coorY + largo - 10, 0);
        
        //## Termino de titulo
        content.endText();
        
        //#########################
        //## Inicio de escritura ##
        //#########################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteNormal);
        
        //## Cacular posicion de las "filas"
        Integer divisionVertical = ((coorY + largo - 20) - coorY)/3;
        
        //## Cacular posicion de las "columnas"
        Integer divisionHorizontal = ((coorX + ancho -90) - (coorX + 20))/4;
        
        //## Planta, Cargo y Nivel
        content.showTextAligned(0, variable01, ((coorX + 20) + (divisionHorizontal*0)), coorY + (divisionVertical*3) - fuente.getAscentPoint(variable01, tamanioFuenteNormal), 0);
        content.showTextAligned(0, variable02, ((coorX + 20) + (divisionHorizontal*0)), coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable02, tamanioFuenteNormal), 0);
        content.showTextAligned(0, variable03, ((coorX + 20) + (divisionHorizontal*0)), coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable02, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable03, tamanioFuenteNormal), 0);
        
        //## Grado y Rango
        content.showTextAligned(0, variable04, (coorX + 20 + (divisionHorizontal*2)), coorY + (divisionVertical*3) - fuente.getAscentPoint(variable04, tamanioFuenteNormal), 0);
        content.showTextAligned(0, variable05, (coorX + 20 + (divisionHorizontal*2)), coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable04, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable05, tamanioFuenteNormal), 0);
        
        //## Calidad
        content.showTextAligned(0, variable06, (coorX + 20 + (divisionHorizontal*3)), coorY + (divisionVertical*3) - fuente.getAscentPoint(variable06, tamanioFuenteNormal), 0);
        content.showTextAligned(0, variable07, (coorX + 20 + (divisionHorizontal*3)), coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable06, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable07, tamanioFuenteNormal), 0);
        content.showTextAligned(0, variable08, (coorX + 20 + (divisionHorizontal*3)), coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable06, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable07, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable08, tamanioFuenteNormal), 0);
        content.showTextAligned(0, variable09, (coorX + 20 + (divisionHorizontal*3)), coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable06, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable07, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable08, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable09, tamanioFuenteNormal), 0);
        
        //## A contar de
        content.showTextAligned(0, variable10, (coorX + 20 + (divisionHorizontal*0)), coorY + (divisionVertical*1) - fuente.getAscentPoint(variable10, tamanioFuenteNormal), 0);
        
        //## Hasta
        content.showTextAligned(0, variable11, (coorX + 20 + (divisionHorizontal*3)), coorY + (divisionVertical*1) - fuente.getAscentPoint(variable11, tamanioFuenteNormal), 0);
        
        //## Termino de escritura
        content.endText();
        
        //##################################
        //## Inicio de escritura diminuta ##
        //##################################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteDiminuta);
        
        //## JEFE DEPTO. RECURSOS HUMANOS
        if(largo <= 87) {
            content.showTextAligned(ALIGN_CENTER, "JEFE DEPTO.", coorX + ancho - 55, coorY + (largo/2) - ((largo - 4)/2) + tamanioFuenteDiminuta + 2, 0);
            content.showTextAligned(ALIGN_CENTER, "RECURSOS HUMANOS", coorX + ancho -55, coorY + (largo/2) - ((largo - 4)/2) + 2, 0);
        }
        else {
            content.showTextAligned(ALIGN_CENTER, "JEFE DEPTO.", coorX + ancho - 55, (coorY + (largo/2) - (87/2) + tamanioFuenteDiminuta) + 2, 0);
            content.showTextAligned(ALIGN_CENTER, "RECURSOS HUMANOS", coorX + ancho -55, (coorY + (largo/2) - (87/2)) + 2, 0);
        }
        
        //## Termino de escritura diminuta
        content.endText();
        
        //#########################
        //## Creaccion de lineas ##
        //#########################
        
        //## Buscar el ancho más alto entre las variables 1, 2 y 3
        float mayorAncho123 = Math.max(Math.max(fuente.getWidthPoint(variable01, tamanioFuenteNormal), fuente.getWidthPoint(variable02, tamanioFuenteNormal)), fuente.getWidthPoint(variable03, tamanioFuenteNormal));
        
        //## Planta, Cargo y Nivel
        content.moveTo((coorX + 20 + mayorAncho123 + 5), (coorY + (divisionVertical*3) - fuente.getAscentPoint(variable01, tamanioFuenteNormal)));
        content.lineTo((coorX + 20 + (divisionHorizontal*2) - 5), (coorY + (divisionVertical*3) - fuente.getAscentPoint(variable01, tamanioFuenteNormal)));
        content.stroke();
        content.moveTo((coorX + 20 + mayorAncho123 + 5), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable02, tamanioFuenteNormal)));
        content.lineTo((coorX + 20 + (divisionHorizontal*2) - 5), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable02, tamanioFuenteNormal)));
        content.stroke();
        content.moveTo((coorX + 20 + mayorAncho123 + 5), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable02, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable03, tamanioFuenteNormal)));
        content.lineTo((coorX + 20 + (divisionHorizontal*2) - 5), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable02, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable03, tamanioFuenteNormal)));
        content.stroke();
        
        //## Buscar el ancho más alto entre las variables 4 y 5
        float mayorAncho45 = Math.max(fuente.getWidthPoint(variable04, tamanioFuenteNormal), fuente.getWidthPoint(variable05, tamanioFuenteNormal));
        
        //## Grado y Rango
        content.rectangle(((coorX + 20 + (divisionHorizontal*2)) + mayorAncho45 + 5), (coorY + (divisionVertical*3) - fuente.getAscentPoint(variable04, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable04, tamanioFuenteNormal))/2)), largoCajas*3, largoCajas);
        content.stroke();
        content.rectangle(((coorX + 20 + (divisionHorizontal*2)) + mayorAncho45 + 5), ((coorY + (divisionVertical*3) - fuente.getAscentPoint(variable05, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable05, tamanioFuenteNormal))/2)) - largoCajas), largoCajas*3, largoCajas);
        content.stroke();
        
        //## Buscar el ancho más alto entre las variables 7, 8, y 9
        float mayorAncho789 = Math.max(Math.max(fuente.getWidthPoint(variable07, tamanioFuenteNormal), fuente.getWidthPoint(variable08, tamanioFuenteNormal)), fuente.getWidthPoint(variable09, tamanioFuenteNormal));
        
        //## Calidad
        content.rectangle(((coorX + 20 + (divisionHorizontal*3)) + mayorAncho789 + 5), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable06, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable07, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable04, tamanioFuenteNormal))/2)), largoCajas*3, largoCajas);
        content.stroke();
        content.rectangle(((coorX + 20 + (divisionHorizontal*3)) + mayorAncho789 + 5), ((coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable06, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable07, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable05, tamanioFuenteNormal))/2)) - largoCajas), largoCajas*3, largoCajas);
        content.stroke();
        content.rectangle(((coorX + 20 + (divisionHorizontal*3)) + mayorAncho789 + 5), ((coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable06, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable07, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable05, tamanioFuenteNormal))/2)) - (largoCajas*2)), largoCajas*3, largoCajas);
        content.stroke();
        
        //## A contar de
        content.rectangle(((coorX + 20 + (divisionHorizontal*0)) + fuente.getWidthPoint(variable10, tamanioFuenteNormal) + 5), (coorY + (divisionVertical*1) - fuente.getAscentPoint(variable10, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable10, tamanioFuenteNormal))/2)), largoCajas*5, largoCajas);
        content.stroke();
        
        //## Hasta
        content.rectangle(((coorX + 20 + (divisionHorizontal*3)) + fuente.getWidthPoint(variable11, tamanioFuenteNormal) + 5), (coorY + (divisionVertical*1) - fuente.getAscentPoint(variable11, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable11, tamanioFuenteNormal))/2)), largoCajas*5, largoCajas);
        content.stroke();
        
        //## JEFE DEPTO. RECURSOS HUMANOS
        if(largo <= 87) {
            content.rectangle(coorX + ancho -90, coorY + (largo/2) - ((largo - 4)/2), 70, largo - 4);
            content.stroke();
        }
        else {
            content.rectangle(coorX + ancho -90, coorY + (largo/2) - (87/2), 70, 87);
            content.stroke();
        }
        
        //## Dividir seccion entera.
        content.rectangle(coorX, coorY, ancho, largo);
        content.stroke();
    }
    
    /**
     * Method description
     *
     * @param content
     * @param coorX
     * @param coorY
     *
     * @throws Exception
     */
    private static void putOHastaCuandoSeanNecesariosSusServicios(PdfContentByte content, Integer coorX, Integer coorY, Integer ancho, Integer largo) throws Exception {
        //###########################
        //## Ajustes de la seccion ##
        //###########################
        Integer largoCajas = 12;
        Integer tamanioFuenteTitulo = 10;
        Integer tamanioFuenteNormal = 8;
        //Integer tamanioFuenteDiminuta = 5;
        BaseFont fuente = BaseFont.createFont();
        
        String titulo = "O HASTA CUANDO SEAN NECESARIOS SUS SERVICIOS";
        
        String variable01 = "ASIGNATURAS:";
        String variable02 = "N° HORAS";
        String variable03 = "N° HORAS";
        String variable04 = "N° HORAS";
        String variable05 = "CATEG.";
        String variable06 = "CATEG.";
        String variable07 = "CATEG.";
        String variable08 = "FUNCION";
        String variable09 = "RINDE FIANZA";
        String variable10 = "SI";
        String variable11 = "NO";
        
        //######################
        //## Inicio de titulo ##
        //######################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteTitulo);
        
        //## Titulo
        content.showTextAligned(ALIGN_CENTER, titulo, coorX + (ancho/2), coorY + largo - 10, 0);
        
        //## Termino de titulo
        content.endText();
        
        //#########################
        //## Inicio de escritura ##
        //#########################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteNormal);
        
        //## Cacular posicion de las "filas"
        Integer divisionVertical = ((coorY + largo - 20) - coorY)/2;
        
        //## Cacular posicion de las "columnas"
        Integer divisionHorizontal = ((coorX + ancho -90) - (coorX + 20))/2;
        
        //## Asignatura
        content.showTextAligned(0, variable01, ((coorX + 20) + (divisionHorizontal*0)), coorY + (divisionVertical*2) - fuente.getAscentPoint(variable01, tamanioFuenteNormal), 0);
        
        //## Horas y Categorias
        content.showTextAligned(0, variable02, ((coorX + 20) + (divisionHorizontal*1)), coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable02, tamanioFuenteNormal), 0);
        content.showTextAligned(0, variable03, ((coorX + 20) + (divisionHorizontal*1)), coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable02, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable03, tamanioFuenteNormal), 0);
        content.showTextAligned(0, variable04, ((coorX + 20) + (divisionHorizontal*1)), coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable02, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable03, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable04, tamanioFuenteNormal), 0);
        content.showTextAligned(0, variable05, ((coorX + 20) + (divisionHorizontal*1) + (fuente.getWidthPoint(variable02, tamanioFuenteNormal) + 10 + (largoCajas*2))), coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable05, tamanioFuenteNormal), 0);
        content.showTextAligned(0, variable06, ((coorX + 20) + (divisionHorizontal*1) + (fuente.getWidthPoint(variable02, tamanioFuenteNormal) + 10 + (largoCajas*2))), coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable05, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable06, tamanioFuenteNormal), 0);
        content.showTextAligned(0, variable07, ((coorX + 20) + (divisionHorizontal*1) + (fuente.getWidthPoint(variable02, tamanioFuenteNormal) + 10 + (largoCajas*2))), coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable05, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable06, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable07, tamanioFuenteNormal), 0);
        
        //## Funcion
        content.showTextAligned(0, variable08, ((coorX + 20) + (divisionHorizontal*0)), (coorY + (divisionVertical*0) + (fuente.getAscentPoint(variable11, tamanioFuenteNormal)*2) + 10), 0);
        content.showTextAligned(0, variable09, ((coorX + 20) + (divisionHorizontal*0) + fuente.getWidthPoint(variable08, tamanioFuenteNormal) + (largoCajas*13)), (coorY + (divisionVertical*0) + (fuente.getAscentPoint(variable11, tamanioFuenteNormal)*2) + 10), 0);
        
        //## Si y No
        content.showTextAligned(0, variable10, ((coorX + 20) + (divisionHorizontal*1) + (fuente.getWidthPoint(variable02, tamanioFuenteNormal) + 10 + (largoCajas*2))), (coorY + (divisionVertical*0) + (fuente.getAscentPoint(variable11, tamanioFuenteNormal)*2) + 10), 0);
        content.showTextAligned(0, variable11, ((coorX + 20) + (divisionHorizontal*1) + (fuente.getWidthPoint(variable02, tamanioFuenteNormal) + 10 + (largoCajas*2))), (coorY + (divisionVertical*0) + 10), 0);
        
        //## Termino de escritura
        content.endText();
        
        //#########################
        //## Creaccion de lineas ##
        //#########################
        
        //## Asignatura y Horas
        content.moveTo(((coorX + 20) + (divisionHorizontal*0)), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable02, tamanioFuenteNormal)));
        content.lineTo(((coorX + 20) + (divisionHorizontal*1) - 5), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable02, tamanioFuenteNormal)));
        content.stroke();
        content.moveTo(((coorX + 20) + (divisionHorizontal*0)), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable02, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable03, tamanioFuenteNormal)));
        content.lineTo(((coorX + 20) + (divisionHorizontal*1) - 5), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable02, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable03, tamanioFuenteNormal)));
        content.stroke();
        content.moveTo(((coorX + 20) + (divisionHorizontal*0)), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable02, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable03, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable04, tamanioFuenteNormal)));
        content.lineTo(((coorX + 20) + (divisionHorizontal*1) - 5), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable02, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable03, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable04, tamanioFuenteNormal)));
        content.stroke();
        
        content.rectangle(((coorX + 20) + (divisionHorizontal*1) + fuente.getWidthPoint(variable02, tamanioFuenteNormal) + 5), ((coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable02, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable02, tamanioFuenteNormal))/2))), (largoCajas*2), largoCajas);
        content.stroke();
        content.rectangle(((coorX + 20) + (divisionHorizontal*1) + fuente.getWidthPoint(variable02, tamanioFuenteNormal) + 5), ((coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable02, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable02, tamanioFuenteNormal))/2)) - (largoCajas*1)), (largoCajas*2), largoCajas);
        content.stroke();
        content.rectangle(((coorX + 20) + (divisionHorizontal*1) + fuente.getWidthPoint(variable02, tamanioFuenteNormal) + 5), ((coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable02, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable02, tamanioFuenteNormal))/2)) - (largoCajas*2)), (largoCajas*2), largoCajas);
        content.stroke();
        
        //## Categorias
        content.moveTo(((coorX + 20) + (divisionHorizontal*1) + fuente.getWidthPoint(variable02, tamanioFuenteNormal) + 10 + (largoCajas*2) + fuente.getWidthPoint(variable05, tamanioFuenteNormal) + 5), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable05, tamanioFuenteNormal)));
        content.lineTo((coorX + ancho - 20), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable05, tamanioFuenteNormal)));
        content.stroke();
        content.moveTo(((coorX + 20) + (divisionHorizontal*1) + fuente.getWidthPoint(variable03, tamanioFuenteNormal) + 10 + (largoCajas*2) + fuente.getWidthPoint(variable06, tamanioFuenteNormal) + 5), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable05, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable06, tamanioFuenteNormal)));
        content.lineTo((coorX + ancho - 20), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable05, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable06, tamanioFuenteNormal)));
        content.stroke();
        content.moveTo(((coorX + 20) + (divisionHorizontal*1) + fuente.getWidthPoint(variable04, tamanioFuenteNormal) + 10 + (largoCajas*2) + fuente.getWidthPoint(variable07, tamanioFuenteNormal) + 5), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable05, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable06, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable07, tamanioFuenteNormal)));
        content.lineTo((coorX + ancho - 20), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable05, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable06, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable07, tamanioFuenteNormal)));
        content.stroke();
        
        //## Funcion
        content.moveTo(((coorX + 20) + (divisionHorizontal*0) + fuente.getWidthPoint(variable08, tamanioFuenteNormal) + 1), (coorY + (divisionVertical*0) + (fuente.getAscentPoint(variable11, tamanioFuenteNormal)*2) + 10));
        content.lineTo(((coorX + 20) + (divisionHorizontal*0) + fuente.getWidthPoint(variable08, tamanioFuenteNormal) + (largoCajas*13) - 1), (coorY + (divisionVertical*0) + (fuente.getAscentPoint(variable11, tamanioFuenteNormal)*2) + 10));
        content.stroke();
        
        //## Buscar el ancho más alto entre las variables 11 y 12
        float mayorAncho11y12 = Math.max(fuente.getWidthPoint(variable10, tamanioFuenteNormal), fuente.getWidthPoint(variable11, tamanioFuenteNormal));
        
        //## Si y No
        content.rectangle((((coorX + 20) + (divisionHorizontal*1) + (fuente.getWidthPoint(variable02, tamanioFuenteNormal) + 10 + (largoCajas*2))) + mayorAncho11y12 + 5), (coorY + (divisionVertical*0) + (fuente.getAscentPoint(variable11, tamanioFuenteNormal)*2) + 10) - ((largoCajas/2) - ((fuente.getAscentPoint(variable10, tamanioFuenteNormal))/2)), largoCajas, largoCajas);
        content.stroke();
        content.rectangle((((coorX + 20) + (divisionHorizontal*1) + (fuente.getWidthPoint(variable02, tamanioFuenteNormal) + 10 + (largoCajas*2))) + mayorAncho11y12 + 5), (coorY + (divisionVertical*0) + (fuente.getAscentPoint(variable11, tamanioFuenteNormal)*2) + 10) - ((largoCajas/2) - ((fuente.getAscentPoint(variable10, tamanioFuenteNormal))/2)) - largoCajas, largoCajas, largoCajas);
        content.stroke();
        
        //## Dividir seccion entera.
        content.rectangle(coorX, coorY, ancho, largo);
        content.stroke();
    }
    
    /**
     * Method description
     *
     * @param content
     * @param coorX
     * @param coorY
     *
     * @throws Exception
     */
    private static void putPieDePagina01(PdfContentByte content, Integer coorX, Integer coorY, Integer ancho, Integer largo) throws Exception {
        //###########################
        //## Ajustes de la seccion ##
        //###########################
        Integer largoCajas = 12;
        //Integer tamanioFuenteTitulo = 10;
        //Integer tamanioFuenteNormal = 8;
        Integer tamanioFuenteDiminuta = 5;
        BaseFont fuente = BaseFont.createFont();
        
        String variable01 = "ORIGINAL: CONTRALORIA GRAL. DE LA REPUBLICA";
        String variable02 = "1° COPIA: UNIDAD DE ORIGEN";
        String variable03 = "2° COPIA: SECCION REMUNERACIONES";
        
        //##################################
        //## Inicio de escritura diminuta ##
        //##################################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteDiminuta);
        
        //## Cacular posicion de las "filas"
        //Integer divisionVertical = ((coorY + largo - 20) - coorY)/4;
        
        //## Cacular posicion de las "columnas"
        Integer divisionHorizontal = ((coorX + ancho -5) - (coorX + 5))/3;
        
        //## Original
        content.showTextAligned(0, variable01, (coorX + 10 + (divisionHorizontal*0) + largoCajas), (coorY + ((largo/2) - (fuente.getAscentPoint(variable01, tamanioFuenteDiminuta)/2))), 0);
        
        //## Primera copia
        content.showTextAligned(0, variable02, (coorX + 10 + (divisionHorizontal*1) + largoCajas), (coorY + ((largo/2) - (fuente.getAscentPoint(variable02, tamanioFuenteDiminuta)/2))), 0);
        
        //## Segunda copia
        content.showTextAligned(0, variable03, (coorX + 10 + (divisionHorizontal*2) + largoCajas), (coorY + ((largo/2) - (fuente.getAscentPoint(variable03, tamanioFuenteDiminuta)/2))), 0);
        
        //## Termino de escritura diminuta
        content.endText();
        
        //#########################
        //## Creaccion de lineas ##
        //#########################
        
        //## Original
        content.rectangle((coorX + 5 + (divisionHorizontal*0)), (coorY + ((largo/2) - (largoCajas/2))), largoCajas, largoCajas);
        content.stroke();
        
        //## Primera copia
        content.rectangle((coorX + 5 + (divisionHorizontal*1)), (coorY + ((largo/2) - (largoCajas/2))), largoCajas, largoCajas);
        content.stroke();
        
        //## Segunda copia
        content.rectangle((coorX + 5 + (divisionHorizontal*2)), (coorY + ((largo/2) - (largoCajas/2))), largoCajas, largoCajas);
        content.stroke();
        
        //## Dividir seccion entera.
        content.rectangle(coorX, coorY, ancho, largo);
        content.stroke();
    }
    
    /**
     * Method description
     *
     * @param content
     * @param coorX
     * @param coorY
     * @param ancho
     * @param largo
     *
     * @throws Exception
     */
    private static void putSituacion(PdfContentByte content, Integer coorX, Integer coorY, Integer ancho, Integer largo) throws Exception {
        //###########################
        //## Ajustes de la seccion ##
        //###########################
        Integer largoCajas = 12;
        Integer tamanioFuenteTitulo = 10;
        Integer tamanioFuenteNormal = 8;
        Integer tamanioFuenteDiminuta = 5;
        BaseFont fuente = BaseFont.createFont();
        
        String titulo = "SITUACION";
        
        String variable01 = "EN LA ADMINISTRACION PUBLICA:";
        String variable02 = "YA PERTENECE";
        String variable03 = "PRIMERA VEZ";
        String variable04 = "SE REINCORPORA";
        
        String variable05 = "EN LA USACH:";
        String variable06 = "YA PERTENECE";
        String variable07 = "PRIMERA VEZ";
        String variable08 = "SE REINCORPORA";
        
        String variable09 = "JUBILADO:";
        String variable10 = "SI";
        String variable11 = "NO";
        String variable12 = "INSTITUCION:";
        
        //## Buscar el ancho más alto entre las variables 10 y 11
        float mayorAncho10y11 = Math.max(fuente.getWidthPoint(variable10, tamanioFuenteNormal), fuente.getWidthPoint(variable11, tamanioFuenteNormal));
        //## Buscar el ancho más alto entre las variables 02 y 06
        float mayorAncho02y06 = Math.max(fuente.getWidthPoint(variable02, tamanioFuenteNormal), fuente.getWidthPoint(variable06, tamanioFuenteNormal));
        //## Buscar el ancho más alto entre las variables 03 y 07
        float mayorAncho03y07 = Math.max(fuente.getWidthPoint(variable03, tamanioFuenteNormal), fuente.getWidthPoint(variable07, tamanioFuenteNormal));
        //## Buscar el ancho más alto entre las variables 04 y 08
        float mayorAncho04y08 = Math.max(fuente.getWidthPoint(variable04, tamanioFuenteNormal), fuente.getWidthPoint(variable08, tamanioFuenteNormal));
        //## Buscar el ancho más alto entre las variables 04 y 08
        
        //######################
        //## Inicio de titulo ##
        //######################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteTitulo);
        
        //## Titulo
        content.showTextAligned(ALIGN_CENTER, titulo, coorX + (ancho/2), coorY + largo - 10, 0);
        
        //## Termino de titulo
        content.endText();
        
        //#########################
        //## Inicio de escritura ##
        //#########################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteNormal);
        
        //## Cacular posicion de las "filas"
        Integer divisionVertical = ((coorY + largo - 20) - coorY)/2;
        
        //## Cacular posicion de las "columnas"
        Integer divisionHorizontal = ((coorX + ancho - 20) - (coorX + 20))/5;
        
        //## En la administracion publica
        content.showTextAligned(0, variable01, (coorX + 20 + (divisionHorizontal*0)), (coorY + (divisionVertical*2) - fuente.getAscentPoint(variable01, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable02, (coorX + 20 + (divisionHorizontal*2)), (coorY + (divisionVertical*2) - fuente.getAscentPoint(variable02, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable03, (coorX + 20 + (divisionHorizontal*3)), (coorY + (divisionVertical*2) - fuente.getAscentPoint(variable03, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable04, (coorX + 20 + (divisionHorizontal*4)), (coorY + (divisionVertical*2) - fuente.getAscentPoint(variable04, tamanioFuenteNormal)), 0);
        
        //## En la Usach
        content.showTextAligned(0, variable05, (coorX + 20 + (divisionHorizontal*0)), (coorY + (divisionVertical*2)  - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable05, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable06, (coorX + 20 + (divisionHorizontal*2)), (coorY + (divisionVertical*2)  - (fuente.getAscentPoint(variable02, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable06, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable07, (coorX + 20 + (divisionHorizontal*3)), (coorY + (divisionVertical*2)  - (fuente.getAscentPoint(variable03, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable07, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable08, (coorX + 20 + (divisionHorizontal*4)), (coorY + (divisionVertical*2)  - (fuente.getAscentPoint(variable04, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable08, tamanioFuenteNormal)), 0);
        
        //## Jubilado
        content.showTextAligned(0, variable09, (coorX + 20 + (divisionHorizontal*0)), (coorY + (divisionVertical*1) - fuente.getAscentPoint(variable09, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable10, (coorX + 20 + (divisionHorizontal*1)), (coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable09, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable10, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable11, (coorX + 20 + (divisionHorizontal*1)), (coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable10, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable09, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable11, tamanioFuenteNormal)), 0);
        
        //## Institucion
        content.showTextAligned(0, variable12, (coorX + 30 + (divisionHorizontal*1) + mayorAncho10y11 + largoCajas), (coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable09, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable10, tamanioFuenteNormal)), 0);
        
        
        //## Termino de escritura
        content.endText();
        
        //##################################
        //## Inicio de escritura diminuta ##
        //##################################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteDiminuta);
        
        //## Termino de escritura diminuta
        content.endText();
        
        //#########################
        //## Creaccion de lineas ##
        //#########################
        
        //## En la administracion publica
        content.rectangle((coorX + 20 + (divisionHorizontal*2) + mayorAncho02y06 + 5), ((coorY + (divisionVertical*2) - fuente.getAscentPoint(variable02, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable02, tamanioFuenteNormal))/2))), largoCajas, largoCajas);
        content.stroke();
        content.rectangle((coorX + 20 + (divisionHorizontal*3) + mayorAncho03y07 + 5), ((coorY + (divisionVertical*2) - fuente.getAscentPoint(variable03, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable03, tamanioFuenteNormal))/2))), largoCajas, largoCajas);
        content.stroke();
        content.rectangle((coorX + 20 + (divisionHorizontal*4) + mayorAncho04y08 + 5), ((coorY + (divisionVertical*2) - fuente.getAscentPoint(variable04, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable04, tamanioFuenteNormal))/2))), largoCajas, largoCajas);
        content.stroke();
        
        //## En la Usach
        content.rectangle((coorX + 20 + (divisionHorizontal*2) + mayorAncho02y06 + 5), ((coorY + (divisionVertical*2) - fuente.getAscentPoint(variable02, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable02, tamanioFuenteNormal))/2)) - largoCajas), largoCajas, largoCajas);
        content.stroke();
        content.rectangle((coorX + 20 + (divisionHorizontal*3) + mayorAncho03y07 + 5), ((coorY + (divisionVertical*2) - fuente.getAscentPoint(variable03, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable03, tamanioFuenteNormal))/2)) - largoCajas), largoCajas, largoCajas);
        content.stroke();
        content.rectangle((coorX + 20 + (divisionHorizontal*4) + mayorAncho04y08 + 5), ((coorY + (divisionVertical*2) - fuente.getAscentPoint(variable04, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable04, tamanioFuenteNormal))/2)) - largoCajas), largoCajas, largoCajas);
        content.stroke();
        
        //## Jubilado
        content.rectangle((coorX + 20 + (divisionHorizontal*1) + mayorAncho10y11 + 5), ((coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable09, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable10, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable10, tamanioFuenteNormal))/2))), largoCajas, largoCajas);
        content.stroke();
        content.rectangle((coorX + 20 + (divisionHorizontal*1) + mayorAncho10y11 + 5), ((coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable09, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable10, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable10, tamanioFuenteNormal))/2)) - largoCajas), largoCajas, largoCajas);
        content.stroke();
        
        //## Institucion
        content.moveTo((coorX + 20 + (divisionHorizontal*2)), (coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable09, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable10, tamanioFuenteNormal)));
        content.lineTo((coorX + ancho - 20), (coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable09, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable10, tamanioFuenteNormal)));
        content.stroke();
        content.moveTo((coorX + 20 + (divisionHorizontal*2)), (coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable10, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable09, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable11, tamanioFuenteNormal)));
        content.lineTo((coorX + ancho - 20), (coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable10, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable09, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable11, tamanioFuenteNormal)));
        content.stroke();
        
        //## Dividir seccion entera.
        content.rectangle(coorX, coorY, ancho, largo);
        content.stroke();
    }
    
    /**
     * Method description
     *
     * @param content
     * @param coorX
     * @param coorY
     * @param ancho
     * @param largo
     *
     * @throws Exception
     */
    private static void putCargoQueDejaDeServir(PdfContentByte content, Integer coorX, Integer coorY, Integer ancho, Integer largo) throws Exception {
        //###########################
        //## Ajustes de la seccion ##
        //###########################
        Integer largoCajas = 12;
        Integer tamanioFuenteTitulo = 10;
        Integer tamanioFuenteNormal = 8;
        //Integer tamanioFuenteDiminuta = 5;
        BaseFont fuente = BaseFont.createFont();
        
        String titulo01 = "1. CARGO QUE DEJA DE SERVIR EN LA USACH";
        
        String variable01 = "CARGO:";
        String variable02 = "CALIDAD";
        String variable03 = "GRADO";
        String variable04 = "NIVEL";
        String variable05 = "RANGO";
        
        String titulo02 = "2. CARGO U HORA DE CLASE QUE DEJA DE SERVIR EN OTRO ESTABLECIMIENTO O REPART. PUBLICA O PRIVADA";
        
        String variable06 = "CARGO:";
        String variable07 = "GRADO";
        String variable08 = "NIVEL O CATEG.";
        String variable09 = "CALIDAD";
        String variable10 = "HORAS DE CLASE:";
        String variable11 = "ASIGNATURA";
        String variable12 = "ASIGNATURA";
        String variable13 = "GRADO O CATEG.";
        String variable14 = "GRADO O CATEG.";
        String variable15 = "CALIDAD";
        
        //######################
        //## Inicio de titulo ##
        //######################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteTitulo);
        //## Cacular posicion de las "filas"
        float divisionVertical = ((coorY + largo - 5 - fuente.getAscentPoint(titulo01, tamanioFuenteTitulo)) - coorY)/5;
        //## Cacular posicion de las "columnas"
        float divisionHorizontal = ((coorX + ancho - 20) - (coorX + 20))/2;
        //## Titulo 01
        content.showTextAligned(0, titulo01, (coorX + 20 + (divisionHorizontal*0)), (coorY + (divisionVertical*5)), 0);
        //## Titulo 02
        content.showTextAligned(0, titulo02, (coorX + 20 + (divisionHorizontal*0)), (coorY + (divisionVertical*3)), 0);
        //## Termino de titulo
        content.endText();
        
        //#########################
        //## Inicio de escritura ##
        //#########################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteNormal);
        
        //#### Titulo 01
        
        //## Cargo y Calidad
        content.showTextAligned(0, variable01, ((coorX + 20) + (divisionHorizontal*0)), (coorY + (divisionVertical*5) - (fuente.getAscentPoint(titulo01, tamanioFuenteTitulo)*2) - fuente.getAscentPoint(variable01, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable02, ((coorX + 20) + (divisionHorizontal*1) - ((fuente.getWidthPoint(variable04, tamanioFuenteNormal) + largoCajas)/2)), (coorY + (divisionVertical*5) - (fuente.getAscentPoint(titulo01, tamanioFuenteTitulo)*2) - fuente.getAscentPoint(variable02, tamanioFuenteNormal)), 0);
        
        //## Grado, Nivel y Rango
        content.showTextAligned(0, variable03, ((coorX + 20) + (divisionHorizontal*0)), (coorY + (divisionVertical*5) - (fuente.getAscentPoint(titulo01, tamanioFuenteTitulo)*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable03, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable04, ((coorX + 20) + (divisionHorizontal*1) - ((fuente.getWidthPoint(variable04, tamanioFuenteNormal) + largoCajas)/2)), (coorY + (divisionVertical*5) - (fuente.getAscentPoint(titulo01, tamanioFuenteTitulo)*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable04, tamanioFuenteNormal)), 0);
        content.showTextAligned(ALIGN_RIGHT, variable05, ((coorX + 25) + (divisionHorizontal*2) - largoCajas), (coorY + (divisionVertical*5) - (fuente.getAscentPoint(titulo01, tamanioFuenteTitulo)*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable05, tamanioFuenteNormal)), 0);
        
        //#### Titulo 02
        
        //## Cargo y Calidad
        content.showTextAligned(0, variable06, ((coorX + 20) + (divisionHorizontal*0)), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(titulo02, tamanioFuenteTitulo)*2) - fuente.getAscentPoint(variable06, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable09, ((coorX + 20) + (divisionHorizontal*1)), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(titulo02, tamanioFuenteTitulo)*2) - fuente.getAscentPoint(variable09, tamanioFuenteNormal)), 0);
        
        //## Grado y Nivel
        content.showTextAligned(0, variable07, ((coorX + 20) + (divisionHorizontal*0)), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(titulo02, tamanioFuenteTitulo)*2) - (fuente.getAscentPoint(variable06, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable07, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable08, ((coorX + 20) + (divisionHorizontal*1)), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(titulo02, tamanioFuenteTitulo)*2) - (fuente.getAscentPoint(variable09, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable08, tamanioFuenteNormal)), 0);
        
        //## Horas de clase
        content.showTextAligned(0, variable10, ((coorX + 20) + (divisionHorizontal*0)), (coorY + (divisionVertical*1)- fuente.getAscentPoint(variable10, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable11, ((coorX + 20) + (divisionHorizontal*0) + fuente.getWidthPoint(variable10, tamanioFuenteNormal) + 5), (coorY + (divisionVertical*1)- fuente.getAscentPoint(variable11, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable12, ((coorX + 20) + (divisionHorizontal*0) + fuente.getWidthPoint(variable10, tamanioFuenteNormal) + 5), (coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable11, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable12, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable13, ((coorX + 20) + (divisionHorizontal*1)), (coorY + (divisionVertical*1) - fuente.getAscentPoint(variable13, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable14, ((coorX + 20) + (divisionHorizontal*1)), (coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable13, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable14, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable15, ((coorX + 20) + (divisionHorizontal*1)), (coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable13, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable14, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable15, tamanioFuenteNormal)), 0);
        
        //## Termino de escritura
        content.endText();
        
        //#########################
        //## Creaccion de lineas ##
        //#########################
        
        //#### Titulo 01
        
        //## Cargo y Calidad
        content.moveTo(((coorX + 20) + (divisionHorizontal*0) + fuente.getWidthPoint(variable01, tamanioFuenteNormal) + 2), (coorY + (divisionVertical*5) - (fuente.getAscentPoint(titulo01, tamanioFuenteTitulo)*2) - fuente.getAscentPoint(variable01, tamanioFuenteNormal)));
        content.lineTo(((coorX + 20) + (divisionHorizontal*1) - ((fuente.getWidthPoint(variable04, tamanioFuenteNormal) + largoCajas)/2) - 2), (coorY + (divisionVertical*5) - (fuente.getAscentPoint(titulo01, tamanioFuenteTitulo)*2) - fuente.getAscentPoint(variable01, tamanioFuenteNormal)));
        content.stroke();
        content.moveTo(((coorX + 20) + (divisionHorizontal*1) - ((fuente.getWidthPoint(variable04, tamanioFuenteNormal) + largoCajas)/2) + fuente.getWidthPoint(variable02, tamanioFuenteNormal) + 2), (coorY + (divisionVertical*5) - (fuente.getAscentPoint(titulo01, tamanioFuenteTitulo)*2) - fuente.getAscentPoint(variable02, tamanioFuenteNormal)));
        content.lineTo(((coorX + 20) + (divisionHorizontal*1) - ((fuente.getWidthPoint(variable04, tamanioFuenteNormal) + largoCajas)/2) + fuente.getWidthPoint(variable02, tamanioFuenteNormal) + (largoCajas*3)), (coorY + (divisionVertical*5) - (fuente.getAscentPoint(titulo01, tamanioFuenteTitulo)*2) - fuente.getAscentPoint(variable02, tamanioFuenteNormal)));
        content.stroke();
        
        //## Grado, Nivel y Rango
        content.rectangle(((coorX + 20) + (divisionHorizontal*0) + fuente.getWidthPoint(variable03, tamanioFuenteNormal) + 5), ((coorY + (divisionVertical*5) - (fuente.getAscentPoint(titulo01, tamanioFuenteTitulo)*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable03, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable03, tamanioFuenteNormal))/2))), largoCajas, largoCajas);
        content.stroke();
        content.rectangle(((coorX + 20) + (divisionHorizontal*1) + fuente.getWidthPoint(variable04, tamanioFuenteNormal) + 5 - ((fuente.getWidthPoint(variable04, tamanioFuenteNormal) + largoCajas)/2)), ((coorY + (divisionVertical*5) - (fuente.getAscentPoint(titulo01, tamanioFuenteTitulo)*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable04, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable04, tamanioFuenteNormal))/2))), largoCajas, largoCajas);
        content.stroke();
        content.rectangle(((coorX + 20) + (divisionHorizontal*2) + 10 - largoCajas), ((coorY + (divisionVertical*5) - (fuente.getAscentPoint(titulo01, tamanioFuenteTitulo)*2) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable05, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable05, tamanioFuenteNormal))/2))), largoCajas, largoCajas);
        content.stroke();
        
        //#### Titulo 02
        
        //## Cargo y Calidad
        content.moveTo(((coorX + 20) + (divisionHorizontal*0) + fuente.getWidthPoint(variable06, tamanioFuenteNormal) + 2), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(titulo02, tamanioFuenteTitulo)*2) - fuente.getAscentPoint(variable06, tamanioFuenteNormal)));
        content.lineTo(((coorX + 20) + (divisionHorizontal*1) - 2), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(titulo02, tamanioFuenteTitulo)*2) - fuente.getAscentPoint(variable06, tamanioFuenteNormal)));
        content.stroke();
        content.moveTo(((coorX + 20) + (divisionHorizontal*1) + fuente.getWidthPoint(variable09, tamanioFuenteNormal) + 2), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(titulo02, tamanioFuenteTitulo)*2) - fuente.getAscentPoint(variable09, tamanioFuenteNormal)));
        content.lineTo(((coorX + 20) + (divisionHorizontal*1) + fuente.getWidthPoint(variable09, tamanioFuenteNormal) + (largoCajas*3)), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(titulo02, tamanioFuenteTitulo)*2) - fuente.getAscentPoint(variable09, tamanioFuenteNormal)));
        content.stroke();
        
        //## Graco y Nivel o Categoria
        content.rectangle(((coorX + 20) + (divisionHorizontal*0) + fuente.getWidthPoint(variable07, tamanioFuenteNormal) + 5), ((coorY + (divisionVertical*3) - (fuente.getAscentPoint(titulo02, tamanioFuenteTitulo)*2) - (fuente.getAscentPoint(variable06, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable07, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable07, tamanioFuenteNormal))/2))), largoCajas, largoCajas);
        content.stroke();
        content.rectangle(((coorX + 20) + (divisionHorizontal*1) + fuente.getWidthPoint(variable08, tamanioFuenteNormal) + 5), ((coorY + (divisionVertical*3) - (fuente.getAscentPoint(titulo02, tamanioFuenteTitulo)*2) - (fuente.getAscentPoint(variable09, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable08, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable08, tamanioFuenteNormal))/2))), largoCajas, largoCajas);
        content.stroke();
        
        //## Horas de clase y Asignaturas
        content.moveTo(((coorX + 20) + (divisionHorizontal*0) + fuente.getWidthPoint(variable10, tamanioFuenteNormal) + fuente.getWidthPoint(variable11, tamanioFuenteNormal) + 7), (coorY + (divisionVertical*1) - fuente.getAscentPoint(variable11, tamanioFuenteNormal)));
        content.lineTo(((coorX + 20) + (divisionHorizontal*1) - 2), (coorY + (divisionVertical*1)- fuente.getAscentPoint(variable11, tamanioFuenteNormal)));
        content.stroke();
        content.moveTo(((coorX + 20) + (divisionHorizontal*0) + fuente.getWidthPoint(variable10, tamanioFuenteNormal) + fuente.getWidthPoint(variable12, tamanioFuenteNormal) + 7), (coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable11, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable12, tamanioFuenteNormal)));
        content.lineTo(((coorX + 20) + (divisionHorizontal*1) - 2), (coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable11, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable12, tamanioFuenteNormal)));
        content.stroke();
        
        //## Grado o categoria
        content.rectangle(((coorX + 20) + (divisionHorizontal*1) + fuente.getWidthPoint(variable13, tamanioFuenteNormal) + 5), ((coorY + (divisionVertical*1) - fuente.getAscentPoint(variable13, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable13, tamanioFuenteNormal))/2))), largoCajas, largoCajas);
        content.stroke();
        content.rectangle(((coorX + 20) + (divisionHorizontal*1) + fuente.getWidthPoint(variable14, tamanioFuenteNormal) + 5), ((coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable13, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable14, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable14, tamanioFuenteNormal))/2))), largoCajas, largoCajas);
        content.stroke();
        
        //## Calidad
        content.moveTo(((coorX + 20) + (divisionHorizontal*1) + fuente.getWidthPoint(variable15, tamanioFuenteNormal) + 2), (coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable13, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable14, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable15, tamanioFuenteNormal)));
        content.lineTo(((coorX + 20) + (divisionHorizontal*1) + fuente.getWidthPoint(variable15, tamanioFuenteNormal) + (largoCajas*3)), (coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable13, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable14, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable15, tamanioFuenteNormal)));
        content.stroke();
        
        content.moveTo(0, 0);
        content.lineTo(0, 0);
        content.stroke();
        
        //## Dividir seccion entera.
        content.rectangle(coorX, coorY, ancho, largo);
        content.stroke();
    }
    
    /**
     * Method description
     *
     * @param content
     * @param coorX
     * @param coorY
     * @param ancho
     * @param largo
     *
     * @throws Exception
     */
    private static void putCargoOActividadQueSeguiraDesempeñando(PdfContentByte content, Integer coorX, Integer coorY, Integer ancho, Integer largo) throws Exception {
        //###########################
        //## Ajustes de la seccion ##
        //###########################
        Integer largoCajas = 12;
        Integer tamanioFuenteTitulo = 10;
        Integer tamanioFuenteNormal = 8;
        //Integer tamanioFuenteDiminuta = 5;
        BaseFont fuente = BaseFont.createFont();
        
        //## Cacular posicion de las "filas"
        Integer divisionVertical = ((coorY + largo - 20) - coorY)/5;
        
        //## Cacular posicion de las "columnas"
        Integer divisionHorizontal = ((coorX + ancho - 20) - (coorX + 20))/4;
        
        String titulo = "CARGO O ACTIVIDAD QUE SEGUIRA DESEMPEÑANDO ADEMAS DE ESTA DESIGNACION";
        
        String subTitulo01 = "SECTOR PUBLICO";
        
        String variable01 = "EN LA USACH";
        String variable02 = "PLANTA";
        String variable03 = "GRADO";
        String variable04 = "CALIDAD";
        String variable05 = "CARGO";
        String variable06 = "NIVEL";
        
        String variable07 = "HORAS";
        String variable08 = "ASIGNATURA";
        String variable09 = "CAT.";
        String variable10 = "CALIDAD";
        String variable11 = "HORAS";
        String variable12 = "ASIGNATURA";
        String variable13 = "CAT.";
        String variable14 = "CALIDAD";
        
        String variable15 = "EN OTRAS REPARTICIONES O ESTABLECIMIENTOS DE LA ADMINISTRACION DEL ESTADO";
        
        String variable16 = "INSTITUCION";
        String variable17 = "CARGO";
        String variable18 = "CALIDAD";
        String variable19 = "N° DE HORAS";
        String variable20 = "GRADO";
        
        String subTitulo02 = "SECTOR PRIVADO";
        
        String variable21 = "HORAS DOCENTES";
        String variable22 = "INSTITUCION";
        String variable23 = "ASIGNATURA";
        String variable24 = "CALIDAD";
        String variable25 = "N° DE HORAS";
        String variable26 = "GRADO";
        String variable27 = "INSTITUCION";
        String variable28 = "ASIGNATURA";
        String variable29 = "CALIDAD";
        String variable30 = "N° DE HORAS";
        String variable31 = "GRADO";
        
        //## Calculos de las variables mas largas
        float mayorAncho02y05 = Math.max(fuente.getWidthPoint(variable02, tamanioFuenteNormal), fuente.getWidthPoint(variable05, tamanioFuenteNormal));
        float mayorAncho08y10y12y14 = Math.max(fuente.getWidthPoint(variable08, tamanioFuenteNormal), Math.max(fuente.getWidthPoint(variable10, tamanioFuenteNormal), Math.max(fuente.getWidthPoint(variable12, tamanioFuenteNormal), fuente.getWidthPoint(variable14, tamanioFuenteNormal))));
        float mayorAncho17y18 = Math.max(fuente.getWidthPoint(variable18, tamanioFuenteNormal), fuente.getWidthPoint(variable17, tamanioFuenteNormal));
        float mayorAncho23y24 = Math.max(fuente.getWidthPoint(variable23, tamanioFuenteNormal), fuente.getWidthPoint(variable24, tamanioFuenteNormal));
        float mayorAncho28y29 = Math.max(fuente.getWidthPoint(variable28, tamanioFuenteNormal), fuente.getWidthPoint(variable29, tamanioFuenteNormal));
        
        float mayorAncho03y06 = Math.max(fuente.getWidthPoint(variable03, tamanioFuenteNormal), fuente.getWidthPoint(variable06, tamanioFuenteNormal));
        float mayorAncho07y09y11y13 = Math.max(fuente.getWidthPoint(variable07, tamanioFuenteNormal), Math.max(fuente.getWidthPoint(variable09, tamanioFuenteNormal), Math.max(fuente.getWidthPoint(variable11, tamanioFuenteNormal), fuente.getWidthPoint(variable13, tamanioFuenteNormal))));
        
        //######################
        //## Inicio de titulo ##
        //######################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteTitulo);
        
        //## Titulo
        content.showTextAligned(ALIGN_CENTER, titulo, coorX + (ancho/2), coorY + largo - 10, 0);
        
        //#### Subtitulo01
        content.showTextAligned(ALIGN_CENTER, subTitulo01, coorX + (ancho/2), coorY + largo - 13 - fuente.getAscentPoint(subTitulo01, tamanioFuenteTitulo), 0);
        
        //#### Subtitulo02
        content.showTextAligned(ALIGN_CENTER, subTitulo02, coorX + (ancho/2), coorY + (divisionVertical*2), 0);
        
        //## Termino de titulo
        content.endText();
        
        //#########################
        //## Inicio de escritura ##
        //#########################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteNormal);
        
        //#### Subtitulo01
        
        //## En la Usach
        content.showTextAligned(0, variable01, (coorX + 20 + (divisionHorizontal*0)), (coorY + (divisionVertical*5) - fuente.getAscentPoint(variable01, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable02, (coorX + 25 + (divisionHorizontal*0)), (coorY + (divisionVertical*5) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable02, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable03, (coorX + 20 + (divisionHorizontal*2)), (coorY + (divisionVertical*5) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable03, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable04, (coorX + 20 + (divisionHorizontal*2) + fuente.getWidthPoint(variable03, tamanioFuenteNormal) + 5 + largoCajas + 5), (coorY + (divisionVertical*5) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable04, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable05, (coorX + 25 + (divisionHorizontal*0)), (coorY + (divisionVertical*5) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable02, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable05, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable06, (coorX + 20 + (divisionHorizontal*2)), (coorY + (divisionVertical*5) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable03, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable06, tamanioFuenteNormal)), 0);
        
        //## Horas y asignaturas
        content.showTextAligned(0, variable07, (coorX + 25 + (divisionHorizontal*0)), (coorY + (divisionVertical*4) - fuente.getAscentPoint(variable07, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable09, (coorX + 25 + (divisionHorizontal*0)), (coorY + (divisionVertical*4) - (fuente.getAscentPoint(variable07, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable09, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable11, (coorX + 25 + (divisionHorizontal*0)), (coorY + (divisionVertical*4) - (fuente.getAscentPoint(variable07, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable09, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable11, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable13, (coorX + 25 + (divisionHorizontal*0)), (coorY + (divisionVertical*4) - (fuente.getAscentPoint(variable07, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable09, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable11, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable13, tamanioFuenteNormal)), 0);
        
        content.showTextAligned(0, variable08, (coorX + 20 + (divisionHorizontal*1)), (coorY + (divisionVertical*4) - fuente.getAscentPoint(variable08, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable10, (coorX + 20 + (divisionHorizontal*1)), (coorY + (divisionVertical*4) - (fuente.getAscentPoint(variable08, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable10, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable12, (coorX + 20 + (divisionHorizontal*1)), (coorY + (divisionVertical*4) - (fuente.getAscentPoint(variable08, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable10, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable12, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable14, (coorX + 20 + (divisionHorizontal*1)), (coorY + (divisionVertical*4) - (fuente.getAscentPoint(variable08, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable10, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable12, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable14, tamanioFuenteNormal)), 0);
        
        //## En otras reparticiones o establecimientos de la administracion del estado.
        content.showTextAligned(0, variable15, (coorX + 20 + (divisionHorizontal*0)), (coorY + (divisionVertical*3) - fuente.getAscentPoint(variable15, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable16, (coorX + 25 + (divisionHorizontal*0)), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable15, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable16, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable17, (coorX + 20 + (divisionHorizontal*2)), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable15, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable17, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable18, (coorX + 20 + (divisionHorizontal*2)), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable15, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable17, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable18, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable19, (coorX + 25 + (divisionHorizontal*0)), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable15, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable17, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable18, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable19, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable20, (coorX + 25 + (divisionHorizontal*0) + fuente.getWidthPoint(variable19, tamanioFuenteNormal) + 5 + (largoCajas*3) + 5), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable15, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable17, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable18, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable20, tamanioFuenteNormal)), 0);
        
        //#### Subtitulo02
        
        //## Horas docentes
        content.showTextAligned(0, variable21, (coorX + 20 + (divisionHorizontal*0)), (coorY + (divisionVertical*2) - fuente.getAscentPoint(variable21, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable22, (coorX + 25 + (divisionHorizontal*0)), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable21, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable22, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable23, (coorX + 20 + (divisionHorizontal*2)), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable21, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable23, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable24, (coorX + 20 + (divisionHorizontal*2)), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable21, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable23, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable24, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable25, (coorX + 25 + (divisionHorizontal*0)), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable21, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable23, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable24, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable25, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable26, (coorX + 25 + (divisionHorizontal*0) + fuente.getWidthPoint(variable25, tamanioFuenteNormal) + 5 + (largoCajas*3) + 5), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable21, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable23, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable24, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable26, tamanioFuenteNormal)), 0);
        
        content.showTextAligned(0, variable27, (coorX + 25 + (divisionHorizontal*0)), (coorY + (divisionVertical*1) - fuente.getAscentPoint(variable27, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable28, (coorX + 20 + (divisionHorizontal*2)), (coorY + (divisionVertical*1) - fuente.getAscentPoint(variable28, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable29, (coorX + 20 + (divisionHorizontal*2)), (coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable28, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable29, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable30, (coorX + 25 + (divisionHorizontal*0)), (coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable28, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable29, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable30, tamanioFuenteNormal)), 0);
        content.showTextAligned(0, variable31, (coorX + 25 + (divisionHorizontal*0) + fuente.getWidthPoint(variable30, tamanioFuenteNormal) + 5 + (largoCajas*3) + 5), (coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable28, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable29, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable31, tamanioFuenteNormal)), 0);
        
        //## Termino de escritura
        content.endText();
        
        //#########################
        //## Creaccion de lineas ##
        //#########################
        
        //## En la Usach
        content.moveTo((coorX + 25 + (divisionHorizontal*0) + mayorAncho02y05 + 5), (coorY + (divisionVertical*5) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable02, tamanioFuenteNormal)));
        content.lineTo((coorX + 20 + (divisionHorizontal*2) - 2), (coorY + (divisionVertical*5) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable02, tamanioFuenteNormal)));
        content.stroke();
        content.moveTo((coorX + 20 + (divisionHorizontal*2) + fuente.getWidthPoint(variable03, tamanioFuenteNormal) + 5 + largoCajas + 5 + fuente.getWidthPoint(variable04, tamanioFuenteNormal) + 5), (coorY + (divisionVertical*5) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable04, tamanioFuenteNormal)));
        content.lineTo((coorX + 20 + (divisionHorizontal*4)), (coorY + (divisionVertical*5) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable04, tamanioFuenteNormal)));
        content.stroke();
        content.moveTo((coorX + 25 + (divisionHorizontal*0) + mayorAncho02y05 + 5), (coorY + (divisionVertical*5) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable02, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable05, tamanioFuenteNormal)));
        content.lineTo((coorX + 20 + (divisionHorizontal*2) - 2), (coorY + (divisionVertical*5) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable02, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable05, tamanioFuenteNormal)));
        content.stroke();
        
        content.rectangle((coorX + 20 + (divisionHorizontal*2) + mayorAncho03y06 + 5), ((coorY + (divisionVertical*5) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable03, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable03, tamanioFuenteNormal))/2))), largoCajas, largoCajas);
        content.stroke();
        content.rectangle((coorX + 20 + (divisionHorizontal*2) + mayorAncho03y06 + 5), ((coorY + (divisionVertical*5) - (fuente.getAscentPoint(variable01, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable03, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable03, tamanioFuenteNormal))/2)) - largoCajas), largoCajas, largoCajas);
        content.stroke();
        
        //##Horas y asignaturas
        content.moveTo((coorX + 20 + (divisionHorizontal*1) + mayorAncho08y10y12y14 + 5), (coorY + (divisionVertical*4) - fuente.getAscentPoint(variable08, tamanioFuenteNormal)));
        content.lineTo((coorX + 20 + (divisionHorizontal*4)), (coorY + (divisionVertical*4) - fuente.getAscentPoint(variable08, tamanioFuenteNormal)));
        content.stroke();
        content.moveTo((coorX + 20 + (divisionHorizontal*1) + mayorAncho08y10y12y14 + 5), (coorY + (divisionVertical*4) - (fuente.getAscentPoint(variable08, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable10, tamanioFuenteNormal)));
        content.lineTo((coorX + 20 + (divisionHorizontal*4)), (coorY + (divisionVertical*4) - (fuente.getAscentPoint(variable08, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable10, tamanioFuenteNormal)));
        content.stroke();
        content.moveTo((coorX + 20 + (divisionHorizontal*1) + mayorAncho08y10y12y14 + 5), (coorY + (divisionVertical*4) - (fuente.getAscentPoint(variable08, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable10, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable12, tamanioFuenteNormal)));
        content.lineTo((coorX + 20 + (divisionHorizontal*4)), (coorY + (divisionVertical*4) - (fuente.getAscentPoint(variable08, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable10, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable12, tamanioFuenteNormal)));
        content.stroke();
        content.moveTo((coorX + 20 + (divisionHorizontal*1) + mayorAncho08y10y12y14 + 5), (coorY + (divisionVertical*4) - (fuente.getAscentPoint(variable08, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable10, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable12, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable14, tamanioFuenteNormal)));
        content.lineTo((coorX + 20 + (divisionHorizontal*4)), (coorY + (divisionVertical*4) - (fuente.getAscentPoint(variable08, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable10, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable12, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable14, tamanioFuenteNormal)));
        content.stroke();
        
        content.rectangle((coorX + 25 + (divisionHorizontal*0) + mayorAncho07y09y11y13 + 5), ((coorY + (divisionVertical*4) - fuente.getAscentPoint(variable07, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable07, tamanioFuenteNormal))/2))), (largoCajas*3), largoCajas);
        content.stroke();
        content.rectangle((coorX + 25 + (divisionHorizontal*0) + mayorAncho07y09y11y13 + 5), ((coorY + (divisionVertical*4) - fuente.getAscentPoint(variable07, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable09, tamanioFuenteNormal))/2)) - (largoCajas*1)), (largoCajas*3), largoCajas);
        content.stroke();
        content.rectangle((coorX + 25 + (divisionHorizontal*0) + mayorAncho07y09y11y13 + 5), ((coorY + (divisionVertical*4) - fuente.getAscentPoint(variable07, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable11, tamanioFuenteNormal))/2)) - (largoCajas*2)), (largoCajas*3), largoCajas);
        content.stroke();
        content.rectangle((coorX + 25 + (divisionHorizontal*0) + mayorAncho07y09y11y13 + 5), ((coorY + (divisionVertical*4) - fuente.getAscentPoint(variable07, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable13, tamanioFuenteNormal))/2)) - (largoCajas*3)), (largoCajas*3), largoCajas);
        content.stroke();
        
        //## En otras reparticiones o establecimientos de la administracion del estado.
        content.moveTo((coorX + 25 + (divisionHorizontal*0) + fuente.getWidthPoint(variable16, tamanioFuenteNormal) + 5), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable15, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable16, tamanioFuenteNormal)));
        content.lineTo((coorX + 20 + (divisionHorizontal*2) - 2), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable15, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable16, tamanioFuenteNormal)));
        content.stroke();
        content.moveTo((coorX + 20 + (divisionHorizontal*2) + mayorAncho17y18 + 5), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable15, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable17, tamanioFuenteNormal)));
        content.lineTo((coorX + 20 + (divisionHorizontal*4)), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable15, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable17, tamanioFuenteNormal)));
        content.stroke();
        content.moveTo((coorX + 20 + (divisionHorizontal*2) + mayorAncho17y18 + 5), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable15, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable17, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable18, tamanioFuenteNormal)));
        content.lineTo((coorX + 20 + (divisionHorizontal*4)), (coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable15, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable17, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable18, tamanioFuenteNormal)));
        content.stroke();
        
        //(0 - ((largoCajas/2) - ((fuente.getAscentPoint(, tamanioFuenteNormal))/2)))
        content.rectangle((coorX + 25 + (divisionHorizontal*0) + fuente.getWidthPoint(variable19, tamanioFuenteNormal) + 5), ((coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable15, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable17, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable18, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable19, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable19, tamanioFuenteNormal))/2))), (largoCajas*3), largoCajas);
        content.stroke();
        content.rectangle((coorX + 25 + (divisionHorizontal*0) + fuente.getWidthPoint(variable19, tamanioFuenteNormal) + 5 + (largoCajas*3) + 5 + fuente.getWidthPoint(variable20, tamanioFuenteNormal) + 5), ((coorY + (divisionVertical*3) - (fuente.getAscentPoint(variable15, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable17, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable18, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable20, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable20, tamanioFuenteNormal))/2))), (largoCajas*3), largoCajas);
        content.stroke();
        
        //#### Subtitulo02
        
        //## Horas docentes
        content.moveTo((coorX + 25 + (divisionHorizontal*0) + fuente.getWidthPoint(variable22, tamanioFuenteNormal) + 5), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable21, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable22, tamanioFuenteNormal)));
        content.lineTo((coorX + 20 + (divisionHorizontal*2) - 2), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable21, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable22, tamanioFuenteNormal)));
        content.stroke();
        content.moveTo((coorX + 20 + (divisionHorizontal*2) + mayorAncho23y24 + 5), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable21, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable23, tamanioFuenteNormal)));
        content.lineTo((coorX + 20 + (divisionHorizontal*4)), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable21, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable23, tamanioFuenteNormal)));
        content.stroke();
        content.moveTo((coorX + 20 + (divisionHorizontal*2) + mayorAncho23y24 + 5), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable21, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable23, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable24, tamanioFuenteNormal)));
        content.lineTo((coorX + 20 + (divisionHorizontal*4)), (coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable21, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable23, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable24, tamanioFuenteNormal)));
        content.stroke();
        
        content.rectangle((coorX + 25 + (divisionHorizontal*0) + fuente.getWidthPoint(variable25, tamanioFuenteNormal) + 5), ((coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable21, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable23, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable24, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable25, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable25, tamanioFuenteNormal))/2))), (largoCajas*3), largoCajas);
        content.stroke();
        content.rectangle((coorX + 25 + (divisionHorizontal*0) + fuente.getWidthPoint(variable25, tamanioFuenteNormal) + 5 + (largoCajas*3) + 5 + fuente.getWidthPoint(variable26, tamanioFuenteNormal) + 5), ((coorY + (divisionVertical*2) - (fuente.getAscentPoint(variable21, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable23, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable24, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable26, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable26, tamanioFuenteNormal))/2))), (largoCajas*3), largoCajas);
        content.stroke();
        
        content.moveTo((coorX + 25 + (divisionHorizontal*0) + fuente.getWidthPoint(variable27, tamanioFuenteNormal) + 5), (coorY + (divisionVertical*1) - fuente.getAscentPoint(variable27, tamanioFuenteNormal)));
        content.lineTo((coorX + 20 + (divisionHorizontal*2) - 2), (coorY + (divisionVertical*1) - fuente.getAscentPoint(variable27, tamanioFuenteNormal)));
        content.stroke();
        content.moveTo((coorX + 20 + (divisionHorizontal*2) + mayorAncho28y29 + 5), (coorY + (divisionVertical*1) - fuente.getAscentPoint(variable28, tamanioFuenteNormal)));
        content.lineTo((coorX + 20 + (divisionHorizontal*4)), (coorY + (divisionVertical*1) - fuente.getAscentPoint(variable28, tamanioFuenteNormal)));
        content.stroke();
        content.moveTo((coorX + 20 + (divisionHorizontal*2) + mayorAncho28y29 + 5), (coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable28, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable29, tamanioFuenteNormal)));
        content.lineTo((coorX + 20 + (divisionHorizontal*4)), (coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable28, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable29, tamanioFuenteNormal)));
        content.stroke();
        
        content.rectangle((coorX + 25 + (divisionHorizontal*0) + fuente.getWidthPoint(variable30, tamanioFuenteNormal) + 5), ((coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable28, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable29, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable30, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable30, tamanioFuenteNormal))/2))), (largoCajas*3), largoCajas);
        content.stroke();
        content.rectangle((coorX + 25 + (divisionHorizontal*0) + fuente.getWidthPoint(variable30, tamanioFuenteNormal) + 5 + (largoCajas*3) + 5 + fuente.getWidthPoint(variable31, tamanioFuenteNormal) + 5), ((coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable28, tamanioFuenteNormal)*2) - (fuente.getAscentPoint(variable29, tamanioFuenteNormal)*2) - fuente.getAscentPoint(variable31, tamanioFuenteNormal)) - ((largoCajas/2) - ((fuente.getAscentPoint(variable31, tamanioFuenteNormal))/2))), (largoCajas*3), largoCajas);
        content.stroke();
        
        //## Dividir seccion entera.
        content.rectangle(coorX, coorY, ancho, largo);
        content.stroke();
    }
    
    /**
     * Method description
     *
     * @param content
     * @param coorX
     * @param coorY
     * @param ancho
     * @param largo
     *
     * @throws Exception
     */
    private static void putFirmas(PdfContentByte content, Integer coorX, Integer coorY, Integer ancho, Integer largo) throws Exception {
        //###########################
        //## Ajustes de la seccion ##
        //###########################
        Integer largoCajas = 12;
        Integer tamanioFuenteTitulo = 10;
        //Integer tamanioFuenteNormal = 8;
        Integer tamanioFuenteDiminuta = 5;
        Integer tamanioFuenteSuperDiminuta = 4;
        BaseFont fuente = BaseFont.createFont();
        
        //## Cacular posicion de las "filas"
        Integer divisionVertical = ((coorY + largo - 20) - coorY)/3;
        
        //## Cacular posicion de las "columnas"
        Integer divisionHorizontal = ((coorX + ancho - 20) - (coorX + 20))/8;
        
        String variable01 = "ACEPTACION DEL CARGO";
        String variable02 = "DIRECTIVO SUPERIOR";
        
        String variable03 = "INSTRUCCIÓN PARA EL LLENADO DE LOS RECUADROS DE FIRMA.";
        
        String variable04 = "Recuadro N°1 - 2: Será utilizado por el Directivo Superior para proponer el Nombramiento o Contratación a la autoridad que corresponda con firma y timbre.";
        String variable05 = "Recuadro N°3: Corresponderá estampar V° B° con firma y timbre del Jefe del Departamento de Recursos Humanos.";
        
        //######################
        //## Inicio de titulo ##
        //######################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteTitulo);
        
        //## Caculo de ubicaciones de las variables
        float posicionAceptacion = ((coorX + 20 + (divisionHorizontal*3)) - (coorX + 20 + (divisionHorizontal*0)))/2;
        float posicionDirectivo = ((coorX + 20 + (divisionHorizontal*8)) - (coorX + 20 + (divisionHorizontal*5)))/2;
        
        //## Aceptacion del cargo
        content.showTextAligned(ALIGN_CENTER, variable01, (coorX + 20 + (divisionHorizontal*0) + posicionAceptacion), (coorY + (divisionVertical*2) - fuente.getAscentPoint(variable01, tamanioFuenteTitulo) - 2), 0);
        
        //## Directivo superior
        content.showTextAligned(ALIGN_CENTER, variable02, (coorX + 20 + (divisionHorizontal*5) + posicionDirectivo), (coorY + (divisionVertical*2) - fuente.getAscentPoint(variable02, tamanioFuenteTitulo) - 2), 0);
        
        //## Termino de titulo
        content.endText();
        
        //##################################
        //## Inicio de escritura diminuta ##
        //##################################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteDiminuta);
        
        //## Instruccion para el llenado
        content.showTextAligned(0, variable03, (coorX + 20 + (divisionHorizontal*0)), (coorY + (divisionVertical*1) - fuente.getAscentPoint(variable03, tamanioFuenteDiminuta)), 0);
        
        //## Termino de escritura diminuta
        content.endText();
        
        //########################################
        //## Inicio de escritura super diminuta ##
        //########################################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteSuperDiminuta);
        
        //## Recuadro 1 - 2.
        content.showTextAligned(0, variable04, (coorX + 20 + (divisionHorizontal*0)), (coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable03, tamanioFuenteDiminuta)*2) - fuente.getAscentPoint(variable04, tamanioFuenteSuperDiminuta)), 0);
        //## Recuadro 3
        content.showTextAligned(0, variable05, (coorX + 20 + (divisionHorizontal*0)), (coorY + (divisionVertical*1) - (fuente.getAscentPoint(variable03, tamanioFuenteDiminuta)*2) - (fuente.getAscentPoint(variable04, tamanioFuenteSuperDiminuta)*2) - fuente.getAscentPoint(variable05, tamanioFuenteSuperDiminuta)), 0);
        
        //## Termino de escritura diminuta
        content.endText();
        
        //#########################
        //## Creaccion de lineas ##
        //#########################
        
        //## Aceptacion y Directivo
        content.moveTo((coorX + 20 + (divisionHorizontal*0)), (coorY + (divisionVertical*2)));
        content.lineTo((coorX + 20 + (divisionHorizontal*3)), (coorY + (divisionVertical*2)));
        content.stroke();
        
        content.moveTo((coorX + 20 + (divisionHorizontal*5)), (coorY + (divisionVertical*2)));
        content.lineTo((coorX + 20 + (divisionHorizontal*8)), (coorY + (divisionVertical*2)));
        content.stroke();
        
        //## Dividir seccion entera.
        content.rectangle(coorX, coorY, ancho, largo);
        content.stroke();
    }
    
    /**
     * Este no es un metodo que se utilice, más bien es un modelo para utilizar como base para crear otras secciones del documento PAF
     *
     * @param content
     * @param coorX
     * @param coorY
     * @param ancho
     * @param largo
     *
     * @throws Exception
     */
    /*
    private static void ModeloCuerpos(PdfContentByte content, Integer coorX, Integer coorY, Integer ancho, Integer largo) throws Exception {
        //###########################
        //## Ajustes de la seccion ##
        //###########################
        Integer largoCajas = 12;
        Integer tamanioFuenteTitulo = 10;
        Integer tamanioFuenteNormal = 8;
        Integer tamanioFuenteDiminuta = 5;
        BaseFont fuente = BaseFont.createFont();
        
        String titulo = "IDENTIFICACION DEL CARGO";
        
        String variable01 = "ED. BASICA";
        String variable02 = "ED. MEDIA";
        String variable03 = "ED. UNIVERSITARIA";
        
        String variable04 = "TITULO";
        String variable05 = "INSTITUCION";
        String variable06 = "FECHA";
        String variable07 = "N° SEMESTRES";
        
        String variable08 = "GRADOS ACADEMICOS";
        String variable09 = "INSTITUCION";
        String variable10 = "FECHA";
        String variable11 = "N° SEMESTRES";
        
        //######################
        //## Inicio de titulo ##
        //######################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteTitulo);
        
        //## Titulo
        content.showTextAligned(ALIGN_CENTER, titulo, coorX + (ancho/2), coorY + largo - 10, 0);
        
        //## Termino de titulo
        content.endText();
        
        //#########################
        //## Inicio de escritura ##
        //#########################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteNormal);
        
        //## Cacular posicion de las "filas"
        Integer divisionVertical = ((coorY + largo - 20) - coorY)/4;
        
        //## Cacular posicion de las "columnas"
        Integer divisionHorizontal = ((coorX + ancho - 20) - (coorX + 20))/5;
        
        //## Termino de escritura
        content.endText();
        
        //##################################
        //## Inicio de escritura diminuta ##
        //##################################
        content.beginText();
        content.setFontAndSize(fuente, tamanioFuenteDiminuta);
        
        //## Termino de escritura diminuta
        content.endText();
        
        //#########################
        //## Creaccion de lineas ##
        //#########################
        
        //## JEFE DEPTO. RECURSOS HUMANOS
        if(largo <= 87) {
            content.rectangle(coorX + ancho -90, coorY + (largo/2) - ((largo - 4)/2), 70, largo - 4);
            content.stroke();
        }
        else {
            content.rectangle(coorX + ancho -90, coorY + (largo/2) - (87/2), 70, 87);
            content.stroke();
        }
        
        //## Dividir seccion entera.
        content.rectangle(coorX, coorY, ancho, largo);
        content.stroke();
    }
    */
}
