/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.support;

import infrastructure.util.FormatUtil;
import com.lowagie.text.Font;

import com.lowagie.text.*;
import static com.lowagie.text.Font.NORMAL;
import static com.lowagie.text.Font.BOLD;
import static com.lowagie.text.FontFactory.getFont;
import static com.lowagie.text.FontFactory.register;
import static com.lowagie.text.Image.getInstance;
import static com.lowagie.text.PageSize.LETTER;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import static com.lowagie.text.pdf.PdfWriter.getInstance;

import domain.model.Convenio;
import domain.model.Unidad;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.lang3.StringUtils;
import static org.apache.struts2.ServletActionContext.getServletContext;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.DateUtil;
import infrastructure.util.SystemParametersUtil;
import infrastructure.util.common.CommonArchivoUtil;

/**
 *
 * @author Ricardo
 */
public class ConvenioSupport {

    Font normalFont;
    Font boldFont;

    private String folioConvenio;
    private String name;
    private String administrado;
    private String unidadMayor;
    private String unidadMayorInitCap;
    private String nombreProyecto;
    private String codigoProyecto;
    private String fechaInicio;
    private String fechaTermino;
    private String funcion;
    private Integer monto;
    private String montoFormateado;
    private String tipoPago;
    private String obsPago;

    private String fecha;
    private String donDonaAutoridad;
    private String nombreFirma;
    private Integer rutJefe;
    private String srSraJefe;
    //String nombreJefe;
    //String depto;

    private String donDona;
    private Integer rutPrestador;
    private String nombrePrestador;
    private String dirPrestador;

    private String aTravesShort;
    private String aTraves;
    private String objUnidad;
    private String unidadFirma;
    private String rutUnidad;
    private String retencion;

    public InputStream print(Integer folio, String nameFile) {

        Convenio convenio = ContextUtil.getDAO().getConvenioPersistence(ActionUtil.getDBUser()).find(folio);
        Unidad unidad = ContextUtil.getDAO().getUnidadPersistence(ActionUtil.getDBUser()).find(convenio.getProyecto().getUnidad().getUniCod());

        folioConvenio = folio.toString();
        name = nameFile;
        
        unidadMayorInitCap = unidad.getUniMayor().getUniNom();
        unidadMayor = unidadMayorInitCap.toUpperCase(ContextUtil.getLocale());
        
        if ("FACULTAD DE ADMINISTRACIÓN Y ECONOMÍA".equals(unidadMayor))
        {
            unidadMayor="DECANATO";
        }        

        administrado = convenio.getProyecto().getProyAdministrado();
        nombreProyecto = convenio.getProyecto().getProyNom();
        codigoProyecto = convenio.getProyecto().getProyCodOfi();
        fechaInicio = DateUtil.getFormatedDate(convenio.getConvFechaIni(), "dd-MM-yyyy");
        fechaTermino = DateUtil.getFormatedDate(convenio.getConvFechaTer(), "dd-MM-yyyy");
        funcion = convenio.getConvFuncion();
        monto = convenio.getConvMonto();
        montoFormateado = ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getNumeroEnPalabras(monto);
        tipoPago = convenio.getConvTipoMonto();
        obsPago = convenio.getConvObsPago();
        fecha = DateUtil.getFechaEnPalabras(convenio.getConvFecha());
        donDonaAutoridad = "1".equals(convenio.getFirma().getProfSexo()) ? "doña " : "don ";
        nombreFirma = convenio.getFirma().getProfNombreSimple();
        rutJefe = convenio.getFirma().getProfRut();
        srSraJefe = "1".equals(convenio.getFirma().getProfSexo()) ? "de la Profesora doña " : "del Profesor don ";

        donDona = convenio.getFuncionario().getFunSexo() == 1 ? "doña " : "don ";
        rutPrestador = convenio.getFuncionario().getFunRut();
        nombrePrestador = convenio.getFuncionario().getNombre();
        dirPrestador = convenio.getFuncionario().getFunDireccion();

        switch (DateUtil.getFormatedDate(convenio.getConvFecha(), "yyyy")) {
            case "2020":
                retencion = "10,75";
                break;
            case "2021":
                retencion = "11,50";
                break;
            case "2022":
                retencion = "12,25";
                break;
            case "2023":
                retencion = "13,00";
                break;
            case "2024":
                retencion = "13,75";
                break;
            case "2025":
                retencion = "14,50";
                break;
            case "2026":
                retencion = "15,25";
                break;
            case "2027":
                retencion = "16,00";
                break;            
            default:
                retencion = "17,00";
        }

        switch (administrado) {
            case "SDT":
                aTravesShort = "SDT USACH";
                aTraves = "la Sociedad de Desarrollo Tecnológico de la Universidad de Santiago de Chile Ltda.";
                unidadFirma = "SOCIEDAD DE DESARROLLO TECNOLÓGICO DE LA UNIVERSIDAD DE SANTIAGO DE CHILE";
                rutUnidad = "78.172.420-3";
                objUnidad = "el desarrollo, coordinación, promoción y apoyo a las actividades que realice la Universidad de Santiago de Chile en materias de adaptación y desarrollo de tecnología, asistencia técnica, educación continua y prestación de servicios técnicos orientados hacia la comunidad en general, y el sector empresarial en particular, así como la administración contable y financiera de los programas, servicios y cursos de nivel académico que desarrolla la  Universidad. ";
                break;
            case "CAP":
                aTravesShort = "CAPACITACIÓN USACH";
                aTraves = "Capacitación Usach Compañía Limitada";
                unidadFirma = "CAPACITACIÓN USACH COMPAÑÍA LTDA.";
                rutUnidad = "76.421.320-3";
                objUnidad = "la prestación de servicios de capacitación.";
                break;
            case "FUDE":
                aTravesShort = "FUDE";
                aTraves = "Fundación para el Desarrollo del Emprendimiento";
                unidadFirma = "FUNDACIÓN PARA EL DESARROLLO DEL EMPRENDIMIENTO";
                rutUnidad = "72.499.400-8";
                objUnidad = "el desarrollo, coordinación, promoción y apoyo a las actividades que realice la Universidad de Santiago de Chile en materias de adaptación y desarrollo de tecnología, asistencia técnica, educación continua, y prestación de servicios orientados a la comunidad en general.";
                break;
        }

        return printConvenio();

    }

    private InputStream printConvenio() {

        String cab = "\nEn Santiago, a " + fecha.toLowerCase(ContextUtil.getLocale()) + ", comparecen " + donDonaAutoridad + " " + nombreFirma + ", cédula nacional de identidad " + FormatUtil.getRutFormateado(rutJefe) + ", en nombre y representación de " + aTraves + ", en adelante ”" + aTravesShort + "”, RUT " + rutUnidad + ", y " + donDona.toLowerCase(ContextUtil.getLocale()) + ": " + nombrePrestador + ", RUT " + FormatUtil.getRutFormateado(rutPrestador) + ", domiciliado en: " + dirPrestador + " en adelante 'el prestador' y exponen lo siguiente:";

        String p1 = "PRIMERO: ANTECEDENTES. " + aTravesShort + " es una persona jurídica de derecho privado que tiene por objeto " + objUnidad
                + "\nEl " + unidadMayorInitCap + " se encuentra realizando el siguiente proyecto a través de " + aTraves + ": " + nombreProyecto + ", proyecto " + codigoProyecto + ", bajo la dirección " + srSraJefe + nombreFirma + ".";

        String p2 = "SEGUNDO: OBJETO. Por el presente instrumento " + donDona + nombrePrestador + ", se compromete a prestar para " + aTravesShort + " la función de: " + funcion + " del programa " + nombreProyecto + ", a partir de " + fechaInicio + " hasta " + fechaTermino + ". En la prestación de sus servicios, el prestador deberá respetar las normas internas definidas por " + aTravesShort + " y el jefe del proyecto.";

        String p3 = "TERCERO: HONORARIOS. Por la prestación de los servicios " + donDona + nombrePrestador + ", contra la presentación de la respectiva boleta de honorarios, percibirá de parte de " + aTraves + " la suma de $" + FormatUtil.getIntegerFormateado(monto) + ".-(" + montoFormateado.toLowerCase(ContextUtil.getLocale()) + " pesos) " + ("G".equals(tipoPago) ? "global" : "mensual") + (obsPago == null ? "" : " " + obsPago) + ", de la cual se retendrá un " + retencion + "% por concepto de impuesto a la renta, establecido en la ley tributaria vigente al momento de la emisión del documento tributario.";

        String p4 = "CUARTO: CONFIDENCIALIDAD Y ÉTICA.";
        String p4_a = "El Prestador declara conocer y aceptar que toda información que, con motivo del contrato de prestación de servicios profesionales antes señalado, le sea entregada o resulte de la ejecución de este (ambas en adelante, la información “Confidencial”), sólo podrá ser utilizada para los fines señalados en dicho contrato, lo que deberá interpretarse siempre en sentido restrictivo, de modo tal, que la información recabada, recibida o a la que tenga acceso, deberá aplicarse o destinarse exclusiva y únicamente al objeto materia del señalado contrato de prestación de servicios profesionales.";
        String p4_b = "Por información “Confidencial”, se entenderá toda información que no sea de conocimiento público, tales como los documentos, programas de trabajo, procedimientos, contratos de los trabajadores, manuales operativos o protocolares de las Empresas Asociadas a " + aTravesShort + ", ó cualquier otro que documente los antecedentes previos, desarrollo y resultados de los servicios contratados y en general, toda la información que se genere, con ocasión del referido contrato de prestación de servicios. Dicha información deberá mantenerse bajo la más estricta confidencialidad en los términos establecidos en la Ley Nº 19.628.";
        String p4_c = "A no ser que de otro modo fuese específicamente estipulado en el presente acuerdo, los Prestadores no podrán, sin contar con el permiso previo manifestado por escrito por " + aTravesShort + ", suministrar ninguna copia de la información Confidencial a ninguna persona o entidad, que no participe directa y efectivamente del proceso de evaluación a realizar. Además, las Partes harán sus mejores esfuerzos en limitar el conocimiento, y el acceso a dicha información, solamente a aquellos profesionales quienes, dentro del curso y alcance ordinario del trabajo, requieren tener conocimiento de ella.";
        String p4_d = "El prestador, por el presente acuerdo, se obliga a: (i) usar la Información Confidencial única y exclusivamente para los efectos de cumplir en forma adecuada con sus obligaciones y realización de actos bajo el Contrato de Prestación de Servicios que lo vincula con " + aTravesShort + "; (ii) mantener en estricta reserva y manejar confidencialmente respecto de cualquier persona natural o jurídica, la Información Confidencial a que acceda; (iii) custodiar y proteger diligentemente toda la Información Confidencial a que tenga acceso o conocimiento o que se encuentre en su poder; así como custodiar y proteger diligentemente, de igual forma, todos y cada uno de los soportes, de cualquier especie o formato, en los que conste o se contenga parte cualquiera de la Información Confidencial; iv) abstenerse de hacer copias o reproducciones de la Información Confidencial que no sean estrictamente necesarias para los efectos de la prestación de sus servicios profesionales; v) no impugnar ni pretender titularidad o autoría de ninguna especie sobre la Información Confidencial; vi) no solicitar privilegio de propiedad intelectual o industrial alguna relativo a la Información Confidencial; vii) no impugnar las solicitudes y tramitaciones de obtención de privilegios de propiedad intelectual o industrial de la otra Parte; viii) comunicar inmediatamente y por escrito a la otra Parte acerca de la ocurrencia de cualquier acto, hecho u omisión que constituya una infracción a las obligaciones asumidas precedentemente, sea por acciones u omisiones propias o de terceros; e, ix) impetrar todas las medidas que fueren necesarias o convenientes y cooperar para que, en el evento que por un acto, hecho u omisión suya o de los terceros antes señalados, todo o parte de la Información Confidencial hubiere sido divulgada en contravención a lo establecido en este Acuerdo. "
                + "El deber de confidencialidad que debe guardar el Prestador tiene el carácter de permanente, incluso en el evento que no se firme contrato alguno, en el futuro. "
                + "En lo que al aspecto ético se refiere, el Prestador deberá respetar el secreto profesional y de no revelar, por ningún motivo, en beneficio propio o de terceros, los hechos, datos o circunstancias de que tenga o hubiese tenido conocimiento en el ejercicio de sus labores relativas al contrato de prestación de servicios que lo vincula con " + aTravesShort + ".";

        String p5 = "QUINTO: PROPIEDAD INTELECTUAL E INDUSTRIAL.";
        String p5_a = "Las partes reconocen que como parte de la naturaleza de las funciones encomendadas, la realización de labores creativas o inventivas. Por lo anterior, la propiedad intelectual e industrial de los productos y resultados del servicio contratado, incluyendo aquellos productos que el prestador haya contribuido a crear o perfeccionar, sean éstos libros, programas computacionales, artículos, memorándums, notas o materiales gráficos, informes, estudios, bases de datos, diseños, memorias o, en general, ideas, marcas, invenciones, procesos, mejoras, entre otros, que sean patentables o protegibles por las leyes de propiedad intelectual o industrial, y que el prestador, sus dependientes y subcontratistas hayan creado o desarrollado durante el curso del presente Contrato o con ocasión de él, son de propiedad de la Universidad de Santiago de Chile, y no podrá ser traspasada a terceros.";
        String p5_b = "Al término del presente contrato el prestador entregará íntegramente los documentos físicos o electrónicos, normativas, procedimientos, bases de datos, estudios, especificaciones técnicas, términos de referencia, y cualquier otro tipo de información que le hayan sido entregados para los efectos de este Contrato o producidos a causa de este.";
        String p5_c = "El prestador se obliga a no registrar como propiedad intelectual y/o industrial suya, patentes, creaciones u otras formas de obtener derechos sobre los bienes intelectuales e industriales señalados en el párrafo primero de esta cláusula, aun cuando en dichos productos haya intervenido el trabajador. Si el trabajador ha registrado sobre tales o partes que los componen, propiedad de alguna naturaleza bajo su nombre, deberá ceder dicha propiedad a la Universidad de Santiago de Chile. Lo anterior se entiende sin perjuicio de las acciones legales que pueda impetrar la Universidad de Santiago de Chile como tercera beneficiaria de esta cláusula, o " + aTravesShort + " como contratante, para exigir el cumplimiento de este contrato y/o resarcirse de los daños que pudiere haber sufrido, y perseguir las sanciones civiles y penales que correspondan.";

        String p6 = "SEXTO: CONSTANCIA. Se deja expresa constancia que en el caso en que el prestador del servicio sea funcionario de la Universidad de Santiago de Chile, o que durante la vigencia de este contrato adquiera tal calidad, los servicios independientes y específicos a que se compromete por este contrato deberán realizarse sin interferir ni afectar las funciones que debe ejecutar en su calidad de empleado público ni tampoco su jornada de trabajo en dicha institución.";
        String p7 = "SÉPTIMO: CLÁUSULA DE SALIDA. " + aTravesShort + " podrá poner término al presente contrato en forma unilateral sin expresión de causa y sin derecho a indemnización a favor del prestador, a través de la sola comunicación escrita o enviada por correo electrónico, percibiendo el prestador del servicio sus honorarios proporcionales al servicio otorgado a la fecha de término según los servicios efectivamente prestados.";

        String p8 = "OCTAVO: ARBITRAJE. Cualquier dificultad o controversia que se produzca entre los contratantes respecto de la aplicación, interpretación, duración, validez o ejecución de este contrato o cualquier otro motivo será sometida a arbitraje, conforme al Reglamento Procesal de Arbitraje del Centro de Arbitraje y Mediación de Santiago, vigente al momento de solicitarlo.";
        String p8_a = "Las partes confieren poder especial irrevocable a la Cámara de Comercio de Santiago A.G., para que, a petición escrita de cualquiera de ellas, designe a un árbitro arbitrador en cuanto al procedimiento y de derecho en cuanto al fallo, de entre los integrantes del cuerpo arbitral del Centro de Arbitraje y Mediación de Santiago.";
        String p8_b = "En contra de las resoluciones del árbitro no procederá recurso alguno. El árbitro queda especialmente facultado para resolver todo asunto relacionado con su competencia y/o jurisdicción.";

        String p9 = "NOVENO: PERSONERÍA. La personería del representante de " + aTravesShort + " consta en convenio general de administración de proyectos otorgado por escritura pública de 12 de agosto de 2015, de la Notaría de don Félix Jara Cadot, Santiago, repertorio Nº 24299-2015.";

        return generarPDF(cab, p1, p2, p3, p4, p4_a, p4_b, p4_c, p4_d,
                p5, p5_a, p5_b, p5_c, p6, p7, p8, p8_a, p8_b, p9);

    }

    private InputStream generarPDF(String cab, String p1, String p2, String p3,
            String p4, String p4_a, String p4_b, String p4_c, String p4_d,
            String p5, String p5_a, String p5_b, String p5_c,
            String p6, String p7,
            String p8, String p8_a, String p8_b, String p9) {

        try {

            CommonArchivoUtil.deleteFile(SystemParametersUtil.PATH_CONV + name);

            Document doc = new Document(LETTER);
            doc.addCreator("Intranet FAE: " + fecha);
            doc.addTitle("CONTRATO PRESTACIÓN DE SERVICIOS");
            doc.addAuthor("FAE-USACH");
            doc.addSubject("Contrato: " + folioConvenio);
            doc.setMargins(50.0f, 50.0f, 100.0f, 50.0f);

            String path = getServletContext().getRealPath("/fonts/local/bookos.ttf");
            register(path, "bookos_font");
            normalFont = getFont("bookos_font", 10f, NORMAL);
            boldFont = getFont("bookos_font", 10f, BOLD);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            PdfWriter writer = getInstance(doc, buffer);
            MyHeaderFooter hf = new MyHeaderFooter();
            writer.setPageEvent(hf);

            doc.open();
            putHeader(doc);
            putBody(doc, cab, p1, p2, p3, p4, p4_a, p4_b, p4_c, p4_d, p5, p5_a, p5_b, p5_c, p6, p7, p8, p8_a, p8_b, p9);
            putFooter(doc);
            doc.close();

            InputStream pdfStream = new ByteArrayInputStream(buffer.toByteArray());
            byte[] bufferByte = new byte[pdfStream.available()];
            pdfStream.read(bufferByte);
            File targetFile = new File(SystemParametersUtil.PATH_CONV + name);
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(bufferByte);
            outStream.close();
            buffer.close();

            return CommonArchivoUtil.getFile(name, "conv");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private void putHeader(Document doc) throws Exception {
        addParagraph(doc, "CONTRATO DE PRESTACIÓN\nDE SERVICIOS PROFESIONALES", 2, Element.ALIGN_CENTER, boldFont);

        Table top = new Table(2);
        top.setWidths(new int[]{80, 20});
        top.setWidth(100);
        top.setPadding(0);
        top.setBorder(0);
        
        Paragraph p1 = newParagraph("FACULTAD DE ADMINISTRACIÓN Y ECONOMÍA", 0, Element.ALIGN_LEFT, normalFont);
        Paragraph p2 = newParagraph(unidadMayor, 0, Element.ALIGN_LEFT, normalFont);
        Paragraph p3 = newParagraph(codigoProyecto, 0, Element.ALIGN_LEFT, normalFont);
        Cell cell1 = new Cell();
        cell1.add(p1);
        cell1.add(p2);
        cell1.add(p3);
        cell1.setBorder(0);
        cell1.setHorizontalAlignment(HorizontalAlignment.LEFT);
        top.addCell(cell1);

        Paragraph p4 = newParagraph(folioConvenio, 0, Element.ALIGN_LEFT, normalFont);
        Cell cell2 = new Cell();        
        cell2.add(p4);
        cell2.setBorder(0);
        cell2.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        top.addCell(cell2);

        doc.add(top);
    }

    private void putBody(Document doc, String cab, String p1, String p2, String p3,
            String p4, String p4_a, String p4_b, String p4_c, String p4_d,
            String p5, String p5_a, String p5_b, String p5_c,
            String p6, String p7,
            String p8, String p8_a, String p8_b, String p9) throws Exception {

        addParagraph(doc, cab, 2, Element.ALIGN_JUSTIFIED, normalFont);
        addParagraph(doc, p1, 2, Element.ALIGN_JUSTIFIED, normalFont);
        addParagraph(doc, p2, 2, Element.ALIGN_JUSTIFIED, normalFont);
        addParagraph(doc, p3, 2, Element.ALIGN_JUSTIFIED, normalFont);
        addParagraph(doc, p4, 2, Element.ALIGN_JUSTIFIED, normalFont);
        addParagraph(doc, p4_a, 2, Element.ALIGN_JUSTIFIED, normalFont);
        addParagraph(doc, p4_b, 2, Element.ALIGN_JUSTIFIED, normalFont);
        addParagraph(doc, p4_c, 2, Element.ALIGN_JUSTIFIED, normalFont);
        addParagraph(doc, p4_d, 2, Element.ALIGN_JUSTIFIED, normalFont);
        addParagraph(doc, p5, 2, Element.ALIGN_JUSTIFIED, normalFont);
        addParagraph(doc, p5_a, 2, Element.ALIGN_JUSTIFIED, normalFont);
        addParagraph(doc, p5_b, 2, Element.ALIGN_JUSTIFIED, normalFont);
        addParagraph(doc, p5_c, 2, Element.ALIGN_JUSTIFIED, normalFont);
        addParagraph(doc, p6, 2, Element.ALIGN_JUSTIFIED, normalFont);
        addParagraph(doc, p7, 2, Element.ALIGN_JUSTIFIED, normalFont);
        addParagraph(doc, p8, 2, Element.ALIGN_JUSTIFIED, normalFont);
        addParagraph(doc, p8_a, 2, Element.ALIGN_JUSTIFIED, normalFont);
        addParagraph(doc, p8_b, 2, Element.ALIGN_JUSTIFIED, normalFont);
        addParagraph(doc, p9, 2, Element.ALIGN_JUSTIFIED, normalFont);
    }

    private void putFooter(Document doc) throws Exception {
        addParagraph(doc, "", 7, Element.ALIGN_CENTER, normalFont);

        Table firman = new Table(2);
        firman.setWidths(new int[]{50, 50});
        firman.setWidth(100);
        firman.setPadding(0);
        firman.setBorder(0);

        Cell cell1 = new Cell();
        Paragraph p1 = newParagraph(nombreFirma, 0, Element.ALIGN_CENTER, boldFont);
        cell1.add(p1);
        cell1.setBorder(0);
        cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        firman.addCell(cell1);

        Cell cell2 = new Cell();
        Paragraph p2 = newParagraph(nombrePrestador, 0, Element.ALIGN_CENTER, boldFont);
        cell2.add(p2);
        cell2.setBorder(0);
        cell2.setHorizontalAlignment(HorizontalAlignment.CENTER);
        firman.addCell(cell2);

        Cell cell3 = new Cell();
        Paragraph p3 = newParagraph(unidadFirma, 0, Element.ALIGN_CENTER, normalFont);
        cell3.add(p3);
        cell3.setBorder(0);
        cell3.setHorizontalAlignment(HorizontalAlignment.CENTER);
        firman.addCell(cell3);

        Cell cell4 = new Cell();
        Paragraph p4 = newParagraph("Prestador del Servicio", 0, Element.ALIGN_CENTER, normalFont);
        cell4.add(p4);
        cell4.setBorder(0);
        cell4.setHorizontalAlignment(HorizontalAlignment.CENTER);
        firman.addCell(cell4);

        doc.add(firman);
    }

    private void addParagraph(Document doc, String texto, Integer interlineado, Integer align, Font font) throws Exception {
        doc.add(newParagraph(texto, interlineado, align, font));
    }

    private Paragraph newParagraph(String texto, Integer interlineado, Integer align, Font font) throws Exception {
        String skip = StringUtils.repeat("\n", interlineado);
        Chunk chunk = new Chunk(texto + skip);
        chunk.setFont(font);
        Paragraph p = new Paragraph(chunk);
        p.setAlignment(align);
        return p;
    }

    public class MyHeaderFooter extends PdfPageEventHelper {

        /**
         * Method description
         *
         *
         * @param writer
         * @param document
         */
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                Image header = getInstance(getServletContext().getRealPath(SystemParametersUtil.CONTRATO_BCK_DIR + administrado.toLowerCase(ContextUtil.getLocale()) + "-header.png"));
                Image footer = getInstance(getServletContext().getRealPath(SystemParametersUtil.CONTRATO_BCK_DIR + administrado.toLowerCase(ContextUtil.getLocale()) + "-footer.png"));

                header.setAbsolutePosition(50, 700);
                footer.setAbsolutePosition(0, 0);
                footer.scaleAbsolute(612, 50);
                document.add(header);
                document.add(footer);
            } catch (DocumentException e) {
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
