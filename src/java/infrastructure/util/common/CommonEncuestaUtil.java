/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.util.common;

import com.lowagie.text.*;
import static com.lowagie.text.Element.ALIGN_CENTER;
import com.lowagie.text.Font;
import static com.lowagie.text.Font.ITALIC;
import com.lowagie.text.Image;
import static com.lowagie.text.Image.getInstance;
import static com.lowagie.text.PageSize.LETTER;
import com.lowagie.text.Rectangle;
import static com.lowagie.text.Rectangle.NO_BORDER;
import com.lowagie.text.pdf.*;
import static com.lowagie.text.pdf.BaseFont.createFont;
import static com.lowagie.text.pdf.PdfWriter.getInstance;
import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import domain.model.ComentarioEncuestaDocente;
import domain.model.Curso;
import domain.model.CursoProfesor;
import domain.model.EncuestaDocente;
import java.awt.*;
import static java.awt.Color.BLACK;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import static org.apache.struts2.ServletActionContext.getServletContext;
import domain.repository.RespEnctaCursoRepository;
import session.AlumnoSession;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getDate;
import static infrastructure.util.SystemParametersUtil.DATE_FULL_FORMAT;
import static infrastructure.util.SystemParametersUtil.UNIVERSITY_LOGO_PATH1;
import domain.model.RespEnctaCursoView;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import domain.repository.ComentarioEncuestaDocenteRepository;

/**
 *
 * @author Ricardo Contreras S.
 */
public class CommonEncuestaUtil {

    private static final int CELL_HEIGHT = 14;
    private static final int MARGINS = 50;
    private static final int NORMAL_FONT_SIZE = 8;
    private static final int SMALL_FONT_SIZE = 6;
    private static final int TOP_Y_POSITION = 730;
    private BaseFont baseFont;
    private int respuestaHeight;

    /**
     * Method Print
     *
     * @param action Clase(action) que invoca al servicio.
     * @param genericSession Sesion de trabajo.
     * @param parameters Todos los valores del formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     * @throws Exception Si el servicio genera una exception
     */
    public InputStream print(ActionCommonSupport action, GenericSession genericSession,
            Map<String, String[]> parameters, String key)
            throws Exception {
        Document document = new Document(LETTER);    // 612x792 (21.59cm x 27.94cm)
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PdfWriter writer = getInstance(document, buffer);
        TableHeader event = new TableHeader();

        writer.setPageEvent(event);
        this.respuestaHeight = (int) (document.getPageSize().getHeight() - 172);    // Cabecera = 172
        this.baseFont = createFont("Helvetica", "Cp1252", false);
        document.open();
        document.setMargins(MARGINS, MARGINS, MARGINS, MARGINS);

        PdfContentByte cb = writer.getDirectContent();
        Image image = getInstance(getServletContext().getRealPath(UNIVERSITY_LOGO_PATH1));
        WorkSession ws = genericSession.getWorkSession(key);
        String flag = parameters.get("flag")[0];

        AtomicInteger index = new AtomicInteger(0);

        ws.getCursoList().stream()
                .filter(curso -> "F".equals(flag) || parameters.get("ck_" + index.get()) != null)
                .forEach(curso -> {
                    printEncuesta(action, index.getAndIncrement(), curso, document, cb, image);
                });

        document.close();

        InputStream pdfStream = new ByteArrayInputStream(buffer.toByteArray());

        buffer.close();

        return pdfStream;
    }

    /**
     * Method description
     *
     * @param action Clase(action) que invoca al servicio. param n
     * @param curso
     * @param document
     * @param cb
     * @param image
     * @throws Exception
     */
    private void printEncuesta(ActionCommonSupport action, int n, Curso curso,
            Document document, PdfContentByte cb, Image image) {

        try {
            RespEnctaCursoRepository respEnctaCursoRepository
                    = ContextUtil.getDAO().getRespEnctaCursoRepository(ActionUtil.getDBUser());
            ComentarioEncuestaDocenteRepository comenEnctaRepository
                    = ContextUtil.getDAO().getComentarioEncuestaDocenteRepository(ActionUtil.getDBUser());

            /// OJO puse 0 e I mientras
            List<RespEnctaCursoView> respEnctaCursoViewList = respEnctaCursoRepository.find(curso.getId(), 0, "I");
            List<ComentarioEncuestaDocente> comenEnctaList = comenEnctaRepository.find(curso, 0, "I");

            if (n > 0) {
                document.newPage();
            }

            float[] rowsEncuesta = {
                20.0f, 372.0f, 30.0f, 30.0f, 30.0f, 30.0f
            };
            PdfPTable encuestaTable = new PdfPTable(rowsEncuesta);

            encuestaTable.setTotalWidth(rowsEncuesta);

            float[] rowsComentario = {512.0f};
            PdfPTable comentarioTable = new PdfPTable(rowsComentario);

            comentarioTable.setTotalWidth(rowsComentario);

            Iterator<RespEnctaCursoView> iterResp = respEnctaCursoViewList.iterator();
            int pos = newPage(action, curso, image, cb, document);

            setRowHeader(encuestaTable);
            pos -= CELL_HEIGHT;

            RespEnctaCursoView respEnctaCursoView = null;
            int num = 0;

            while (iterResp.hasNext()) {
                respEnctaCursoView = iterResp.next();
                pos = checkNewPage(pos, action, curso, image, encuestaTable, rowsEncuesta, cb,
                        document, this.respuestaHeight);

                if (pos == this.respuestaHeight) {
                    rowsEncuesta = new float[]{
                        20.0f, 372.0f, 30.0f, 30.0f, 30.0f, 30.0f
                    };
                    encuestaTable = new PdfPTable(rowsEncuesta);
                    encuestaTable.setTotalWidth(rowsEncuesta);
                    setRowHeader(encuestaTable);
                    pos -= CELL_HEIGHT * 2;
                }

                setRow(num, encuestaTable, respEnctaCursoView);
                num++;
            }

            encuestaTable.writeSelectedRows(0, -1, MARGINS, this.respuestaHeight, cb);

            if (respEnctaCursoView != null) {
                pos = checkNewPage(pos, action, curso, image, cb, document);
                putText(cb, MARGINS, pos - CELL_HEIGHT / 2, "#Alumnos Respondieron: ");
                putText(cb, MARGINS + 100, pos - CELL_HEIGHT / 2, respEnctaCursoView.getNumResp().toString());
                pos = checkNewPage(pos, action, curso, image, cb, document);
                putText(cb, MARGINS, pos - CELL_HEIGHT / 2, "Promedio Curso: ");
                putText(cb, MARGINS + 100, pos - CELL_HEIGHT / 2, respEnctaCursoView.getCedPromCur().toString());
                pos = checkNewPage(pos, action, curso, image, cb, document);
                putText(cb, MARGINS, pos - CELL_HEIGHT / 2, "Promedio Area: ");
                putText(cb, MARGINS + 100, pos - CELL_HEIGHT / 2, (respEnctaCursoView.getCedPromArea() == null)
                        ? ""
                        : respEnctaCursoView.getCedPromArea().toString());
            }

            pos -= CELL_HEIGHT;

            int inicio = pos;

            if (!comenEnctaList.isEmpty()) {
                pos = checkNewPage(pos, action, curso, image, comentarioTable, rowsComentario, cb, document,
                        inicio);

                if (pos == this.respuestaHeight) {
                    rowsComentario = new float[]{512.0f};
                    comentarioTable = new PdfPTable(rowsComentario);
                    comentarioTable.setTotalWidth(rowsComentario);
                    inicio = this.respuestaHeight;
                }

                if (curso.getId().getCurAgno() >= 2020) {
                    setTitleComentarios("PROBLEMAS Y DESAFÍOS", num, comentarioTable);
                } else {
                    setTitleComentarios("COMENTARIOS: POSITIVOS", num, comentarioTable);
                }

                pos -= CELL_HEIGHT;

                for (ComentarioEncuestaDocente comenEncta : comenEnctaList) {
                    if (comenEncta.getCedComen1() != null) {
                        pos = checkNewPage(pos, action, curso, image, comentarioTable, rowsComentario, cb,
                                document, inicio);

                        if (pos == this.respuestaHeight) {
                            rowsComentario = new float[]{512.0f};
                            comentarioTable = new PdfPTable(rowsComentario);
                            comentarioTable.setTotalWidth(rowsComentario);
                            inicio = this.respuestaHeight;
                            pos -= CELL_HEIGHT;
                        }

                        setRowComentario(num + 1, comentarioTable, comenEncta.getCedComen1());
                        num++;
                    }
                }

                if (curso.getId().getCurAgno() >= 2020) {
                    setTitleComentarios("SOLUCIONES", num, comentarioTable);
                } else {
                    setTitleComentarios("COMENTARIOS POR MEJORAR", num, comentarioTable);
                }

                pos -= CELL_HEIGHT;

                for (ComentarioEncuestaDocente comenEncta : comenEnctaList) {
                    if (comenEncta.getCedComen2() != null) {
                        pos = checkNewPage(pos, action, curso, image, comentarioTable, rowsComentario, cb,
                                document, inicio);

                        if (pos == this.respuestaHeight) {
                            rowsComentario = new float[]{512.0f};
                            comentarioTable = new PdfPTable(rowsComentario);
                            comentarioTable.setTotalWidth(rowsComentario);
                            inicio = this.respuestaHeight;
                            pos -= CELL_HEIGHT;
                        }

                        setRowComentario(num + 1, comentarioTable, comenEncta.getCedComen2());
                        num++;
                    }
                }

                comentarioTable.writeSelectedRows(0, -1, MARGINS, inicio, cb);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method description
     *
     * @param action Clase(action) que invoca al servicio. param curso
     * @param image
     * @param cb
     * @param document
     * @return
     */
    private int newPage(ActionCommonSupport action, Curso curso, Image image,
            PdfContentByte cb, Document document)
            throws Exception {
        document.newPage();
        putHeader(action, curso, cb, image);

        return this.respuestaHeight;
    }

    /**
     * Method description
     *
     * @param pos Numero del registro seleccionado en el formulario.
     * @param action Clase(action) que invoca al servicio. param curso
     * @param image
     * @param table
     * @param rows
     * @param cb
     * @param document
     * @param inicio
     * @return
     */
    private int checkNewPage(int pos, ActionCommonSupport action, Curso curso,
            Image image, PdfPTable table, float[] rows, PdfContentByte cb, Document document,
            int inicio)
            throws Exception {
        int retValue;

        if (pos < MARGINS + CELL_HEIGHT) {
            table.writeSelectedRows(0, -1, MARGINS, inicio, cb);
            table = new PdfPTable(rows);
            table.setTotalWidth(rows);
            retValue = newPage(action, curso, image, cb, document);
        } else {
            retValue = pos - CELL_HEIGHT;
        }

        return retValue;
    }

    /**
     * Method description
     *
     * @param pos Numero del registro seleccionado en el formulario.
     * @param action Clase(action) que invoca al servicio.
     * @param curso
     * @param image
     * @param cb
     * @param document
     * @return
     */
    private int checkNewPage(int pos, ActionCommonSupport action, Curso curso,
            Image image, PdfContentByte cb, Document document)
            throws Exception {
        int retValue;

        retValue = pos < MARGINS + CELL_HEIGHT
                ? newPage(action, curso, image, cb, document)
                : pos - CELL_HEIGHT;

        return retValue;
    }

    /**
     * Method description
     *
     * @param num
     * @param table
     */
    private void setTitleComentarios(String tipoComentario, int num, PdfPTable table) {
        Chunk descChunck = new Chunk(tipoComentario, new Font(Font.TIMES_ROMAN, NORMAL_FONT_SIZE, ITALIC));
        PdfPCell cell = new PdfPCell(new Phrase(descChunck));

        setColor(num, cell);
        cell.setFixedHeight(CELL_HEIGHT);
        cell.setNoWrap(false);
        cell.setPadding(2);
        cell.setHorizontalAlignment(ALIGN_CENTER);
        table.addCell(cell);
    }

    /**
     * Method description
     *
     * @param table
     */
    private void setRowHeader(PdfPTable table) {
        Chunk pregChunck = new Chunk("", new Font(Font.TIMES_ROMAN, NORMAL_FONT_SIZE, ITALIC));
        PdfPCell cell = new PdfPCell(new Phrase(pregChunck));

        setColor(0, cell);
        cell.setFixedHeight(CELL_HEIGHT);
        cell.setNoWrap(false);
        cell.setPadding(2);
        cell.setBorder(NO_BORDER);
        table.addCell(cell);

        Chunk descChunck = new Chunk("", new Font(Font.TIMES_ROMAN, NORMAL_FONT_SIZE, ITALIC));

        cell = new PdfPCell(new Phrase(descChunck));
        setColor(0, cell);
        cell.setFixedHeight(CELL_HEIGHT);
        cell.setNoWrap(false);
        cell.setPadding(2);
        cell.setBorder(NO_BORDER);
        table.addCell(cell);

        Chunk promChunck = new Chunk("PROM", new Font(Font.TIMES_ROMAN, NORMAL_FONT_SIZE, ITALIC));

        cell = new PdfPCell(new Phrase(promChunck));
        setColor(0, cell);
        cell.setFixedHeight(CELL_HEIGHT);
        cell.setNoWrap(false);
        cell.setPadding(2);
        cell.setHorizontalAlignment(ALIGN_CENTER);
        table.addCell(cell);

        Chunk desvChunck = new Chunk("DESV", new Font(Font.TIMES_ROMAN, NORMAL_FONT_SIZE, ITALIC));

        cell = new PdfPCell(new Phrase(desvChunck));
        setColor(0, cell);
        cell.setFixedHeight(CELL_HEIGHT);
        cell.setNoWrap(false);
        cell.setPadding(2);
        cell.setHorizontalAlignment(ALIGN_CENTER);
        table.addCell(cell);

        Chunk maxChunck = new Chunk("MAX", new Font(Font.TIMES_ROMAN, NORMAL_FONT_SIZE, ITALIC));

        cell = new PdfPCell(new Phrase(maxChunck));
        setColor(0, cell);
        cell.setFixedHeight(CELL_HEIGHT);
        cell.setNoWrap(false);
        cell.setPadding(2);
        cell.setHorizontalAlignment(ALIGN_CENTER);
        table.addCell(cell);

        Chunk minChunck = new Chunk("MIN", new Font(Font.TIMES_ROMAN, NORMAL_FONT_SIZE, ITALIC));

        cell = new PdfPCell(new Phrase(minChunck));
        setColor(0, cell);
        cell.setFixedHeight(CELL_HEIGHT);
        cell.setNoWrap(false);
        cell.setPadding(2);
        cell.setHorizontalAlignment(ALIGN_CENTER);
        table.addCell(cell);
    }

    /**
     * Method description
     *
     * @param num
     * @param table
     * @param respEnctaCursoView
     */
    private void setRow(int num, PdfPTable table, RespEnctaCursoView respEnctaCursoView) {
        Chunk pregChunck = new Chunk(respEnctaCursoView.getPregNro().toString(),
                new Font(Font.TIMES_ROMAN, NORMAL_FONT_SIZE, ITALIC));
        PdfPCell cell = new PdfPCell(new Phrase(pregChunck));

        setColor(num, cell);
        cell.setFixedHeight(CELL_HEIGHT);
        cell.setNoWrap(false);
        cell.setPadding(2);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);

        Chunk descChunck = new Chunk(respEnctaCursoView.getPregDes(), new Font(Font.TIMES_ROMAN, 8, ITALIC));

        cell = new PdfPCell(new Phrase(descChunck));
        setColor(num, cell);
        cell.setFixedHeight(CELL_HEIGHT);
        cell.setNoWrap(false);
        cell.setPadding(2);
        table.addCell(cell);

        Chunk promChunck = new Chunk(respEnctaCursoView.getProm().toString(),
                new Font(Font.TIMES_ROMAN, NORMAL_FONT_SIZE, ITALIC));

        cell = new PdfPCell(new Phrase(promChunck));
        setColor(num, cell);
        cell.setFixedHeight(CELL_HEIGHT);
        cell.setNoWrap(false);
        cell.setPadding(2);
        cell.setHorizontalAlignment(ALIGN_CENTER);
        table.addCell(cell);

        Chunk desvChunck = new Chunk(respEnctaCursoView.getDesv().toString(),
                new Font(Font.TIMES_ROMAN, NORMAL_FONT_SIZE, ITALIC));

        cell = new PdfPCell(new Phrase(desvChunck));
        setColor(num, cell);
        cell.setFixedHeight(CELL_HEIGHT);
        cell.setNoWrap(false);
        cell.setPadding(2);
        cell.setHorizontalAlignment(ALIGN_CENTER);
        table.addCell(cell);

        Chunk maxChunck = new Chunk(respEnctaCursoView.getMaximo().toString(),
                new Font(Font.TIMES_ROMAN, NORMAL_FONT_SIZE, ITALIC));

        cell = new PdfPCell(new Phrase(maxChunck));
        setColor(num, cell);
        cell.setFixedHeight(CELL_HEIGHT);
        cell.setNoWrap(false);
        cell.setPadding(2);
        cell.setHorizontalAlignment(ALIGN_CENTER);
        table.addCell(cell);

        Chunk minChunck = new Chunk(respEnctaCursoView.getMinimo().toString(),
                new Font(Font.TIMES_ROMAN, NORMAL_FONT_SIZE, ITALIC));

        cell = new PdfPCell(new Phrase(minChunck));
        setColor(num, cell);
        cell.setFixedHeight(CELL_HEIGHT);
        cell.setNoWrap(false);
        cell.setPadding(2);
        cell.setHorizontalAlignment(ALIGN_CENTER);
        table.addCell(cell);
    }

    /**
     * Method description
     *
     * @param num
     * @param table
     * @param comentario
     */
    private void setRowComentario(int num, PdfPTable table, String comentario) {
        Chunk comentarioChunck = new Chunk(comentario, new Font(Font.TIMES_ROMAN, SMALL_FONT_SIZE, ITALIC));
        PdfPCell cell = new PdfPCell(new Phrase(comentarioChunck));

        setColor(num, cell);
        cell.setFixedHeight(CELL_HEIGHT);
        cell.setNoWrap(false);
        cell.setPadding(2);
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
    private void putText(PdfContentByte cb, Integer x, Integer y, String text) throws Exception {
        cb.beginText();
        cb.setFontAndSize(createFont(BaseFont.TIMES_ROMAN, "Cp1252", false), NORMAL_FONT_SIZE);
        cb.setTextMatrix(x, y);
        cb.showText(text);
        cb.setColorFill(BLACK);
        cb.endText();
    }

    /**
     * Method description
     *
     * @param num
     * @param cell
     */
    private void setColor(int num, PdfPCell cell) {
        if (num % 2 == 0) {
            cell.setBackgroundColor(new Color(0xFF, 0xFF, 0xFF));
        } else {
            cell.setBackgroundColor(new Color(0xF0, 0xF0, 0xF0));
        }
    }

    /**
     * Method description
     *
     * @param cb
     * @param text
     */
    private void putDate(PdfContentByte cb, String text) {
        cb.beginText();
        cb.setFontAndSize(this.baseFont, SMALL_FONT_SIZE);

        float textSize = this.baseFont.getWidthPoint(text, SMALL_FONT_SIZE);
        float adjust = this.baseFont.getWidthPoint("0", SMALL_FONT_SIZE);
        float x = 612 - textSize - adjust;

        cb.setTextMatrix(x, TOP_Y_POSITION);
        cb.setColorFill(BLACK);
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, text, x, TOP_Y_POSITION, 0);
        cb.endText();
    }

    /**
     * Method description
     *
     * @param action Clase(action) que invoca al servicio. param curso
     * @param cb
     * @param image
     * @throws Exception
     */
    private void putHeader(ActionCommonSupport action, Curso curso,
            PdfContentByte cb, Image image)
            throws Exception {
        image.setAbsolutePosition(50.0f, 690.0f);
        image.scaleAbsolute(39.0f, 50.0f);
        cb.addImage(image);
        putText(cb, 120, TOP_Y_POSITION, action.getText("label.universidad").toUpperCase(ContextUtil.getLocale()));
        putText(cb, 120, 710, action.getText("label.facultad").toUpperCase(ContextUtil.getLocale()));
        putText(cb, MARGINS, 670, action.getText("label.curso"));
        putText(cb, MARGINS, 650, action.getText("label.profesor"));
        putText(cb, 120, 670,
                curso.getId().getCurAsign() + " " + curso.getId().getCurElect() + ' '
                + curso.getId().getCurCoord() + curso.getId().getCurSecc() + ' '
                + curso.getId().getCurSem() + '/' + curso.getId().getCurAgno() + ' '
                + curso.getCurNombre());
        putText(cb, 120, 650, curso.getCurProfesores());
        putDate(cb, getDate(DATE_FULL_FORMAT));
    }

    /**
     * Class description
     *
     * @author Ricardo Contreras S.
     * @version 7, 24/05/2012
     */
    class TableHeader extends PdfPageEventHelper {

        /**
         * The headertable.
         */
        public PdfPTable table;

        /**
         * A template that will hold the total number of pages.
         */
        public PdfTemplate tpl;

        /**
         * Creates the PdfTemplate that will hold the total number of pages.
         */
        @Override
        public void onOpenDocument(PdfWriter writer, Document document) {
            try {
                table = new PdfPTable(2);
                tpl = writer.getDirectContent().createTemplate(100, 100);
                tpl.setBoundingBox(new Rectangle(-20, -20, 100, 100));
                baseFont = createFont("Helvetica", "Cp1252", false);
            } catch (DocumentException e) {
                throw new ExceptionConverter(e);
            } catch (IOException e) {
                throw new ExceptionConverter(e);
            }
        }

        /**
         * Adds a header to every page
         */
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                PdfContentByte cb = writer.getDirectContent();

                table.setTotalWidth(document.right() - document.left());
                table.writeSelectedRows(0, -1, document.left(), document.getPageSize().getHeight() - 50, cb);

                // compose the footer
                String text = "Pág " + writer.getPageNumber() + " de ";
                float textSize = baseFont.getWidthPoint(text, SMALL_FONT_SIZE);
                float textBase = document.bottom() - 20;

                cb.beginText();
                cb.setFontAndSize(baseFont, SMALL_FONT_SIZE);

                float adjust = baseFont.getWidthPoint("0", SMALL_FONT_SIZE);

                cb.setTextMatrix(document.right() - textSize - adjust, textBase);
                cb.showText(text);
                cb.endText();
                cb.addTemplate(tpl, document.right() - adjust, textBase);
            } catch (Exception de) {
                throw new ExceptionConverter(de);
            }
        }

        /**
         * Method description
         *
         * @param writer
         * @param document
         */
        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {
            tpl.beginText();
            tpl.setFontAndSize(baseFont, SMALL_FONT_SIZE);
            tpl.setTextMatrix(0, 0);
            tpl.showText("" + (writer.getPageNumber() - 1));
            tpl.endText();
        }
    }

    public static String showFormService(GenericSession genericSession, AlumnoSession alumnoSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        EncuestaDocente encuesta = ws.getEncuestaDocente();

        verificarRespuestas(alumnoSession, ws.getCursoProfesorList(), ws.getAluCar(), encuesta);

        String retValue = SUCCESS;

        if (encuesta != null && !ws.getCursoProfesorList().isEmpty()) {
            ws.setCursoProfesor(ws.getCursoProfesorList().get(0));
            if (ws.getPreguntasEncuesta() == null) {
                ws.setPreguntasEncuesta(ContextUtil.getDAO().getPregEnctaRepository(ActionUtil.getDBUser()).find(encuesta));
            }
        } else {
            retValue = "stack";
        }

        return retValue;
    }

    private static void verificarRespuestas(AlumnoSession alumnoSession, List<CursoProfesor> cursoList, AluCar aluCar, EncuestaDocente encuesta) {
        if (encuesta != null) {
            while (!cursoList.isEmpty()) {
                // EncuestaDocente ya esta contestada?
                alumnoSession.setCantEncuestasPorContestar(ContextUtil.getDAO().getRespuestaEncuestaDocenteRepository(ActionUtil.getDBUser()).find(aluCar, encuesta) / encuesta.getEdoNroPreg());
                if (ContextUtil.getDAO().getRespuestaEncuestaDocenteRepository(ActionUtil.getDBUser()).find(aluCar, encuesta, cursoList.get(0)).isEmpty()) {
                    cursoList.remove(0);
                } else {
                    break;
                }
            }
        }

    }

    public static List<ComentarioEncuestaDocente> getComentarios(
            List<ComentarioEncuestaDocente> comenEnctaList,
            Function<ComentarioEncuestaDocente, String> getter,
            BiConsumer<ComentarioEncuestaDocente, String> setter) {

        return comenEnctaList.stream()
                .filter(comenEncta -> comenEncta != null && getter.apply(comenEncta) != null)
                .map(comenEncta -> {
                    ComentarioEncuestaDocente comenEnctaAux = new ComentarioEncuestaDocente();
                    setter.accept(comenEnctaAux, getter.apply(comenEncta));
                    return comenEnctaAux;
                })
                .collect(Collectors.toList());
    }
}
