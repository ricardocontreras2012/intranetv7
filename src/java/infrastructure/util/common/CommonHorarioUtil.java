/*
 * @(#)CommonHorarioUtil.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package infrastructure.util.common;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import static com.lowagie.text.Font.NORMAL;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import domain.model.Curso;
import domain.model.Dia;
import domain.model.Horario;
import domain.model.ModuloHorario;
import domain.model.Sala;
import java.awt.Color;
import java.util.*;
import org.apache.struts2.ServletActionContext;
import static org.apache.struts2.ServletActionContext.getServletContext;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getDate;
import infrastructure.util.PdfUtil;
import infrastructure.util.SystemParametersUtil;
import static infrastructure.util.SystemParametersUtil.DATE_FULL_FORMAT;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonHorarioUtil {

    private CommonHorarioUtil() {
    }

    /**
     * Method description
     *
     * @param id
     * @param modList
     * @param cursoList
     * @param userType
     * @return
     */
    public static Horario[][] getHorario(String id, List<ModuloHorario> modList, List<Curso> cursoList, String userType) {
        int dias = ContextUtil.getDiaList().size();
        int modulos = modList.size();
        Horario[][] horarioReturn = new Horario[modulos][dias];

        // Crear un mapa de días (código de día -> índice)
        Map<String, Integer> diaMap = IntStream.range(0, dias)
                .boxed()
                .collect(Collectors.toMap(i -> ContextUtil.getDiaList().get(i).getDiaCod(), i -> i));

        // Procesar cada curso
        cursoList.forEach(curso -> {
            Set<Horario> horarios = new HashSet<>(CommonCursoUtil.getHorario(curso));
            horarios.forEach(horario -> {
                String dia = horario.getId().getHorDia();
                Integer modulo = horario.getId().getHorModulo();

                // Verificar si el día y módulo son válidos
                if (diaMap.containsKey(dia) && modulo != null && modulo - 1 < modulos) {
                    int diaIndex = diaMap.get(dia);
                    int moduloIndex = modulo - 1;

                    // Comprobar si se debe mostrar y si la sala no es nula o sobrescribe              
                    if (muestra(id, horario.getHorTipoClase())) {
                        if (horarioReturn[moduloIndex][diaIndex] == null || horario.getSala() != null) {
                            horarioReturn[moduloIndex][diaIndex] = horario;
                        }
                    }
                }
            });
        });

        return horarioReturn;
    }

    /**
     * Method description
     *
     * @param doc
     * @param writer
     * @param id
     * @param userType
     * @param userName
     * @param modList
     * @param cursoList
     * @param title
     * @throws java.lang.Exception
     */
    public static void printHorario(Document doc, PdfWriter writer, String id, List<ModuloHorario> modList, List<Curso> cursoList, String userType, String userName, String title) throws Exception {
        Font fontMed = PdfUtil.getFont("tahoma", 12.0f, NORMAL);
        Font font = PdfUtil.getFont("tahoma", 7.0f, NORMAL);
        Font dummy = PdfUtil.getFont("tahoma", 4f, NORMAL);
        Font fontTitle = PdfUtil.getFont("times", 14.0f, Font.BOLD);

        String imagePath = ServletActionContext.getServletContext().getRealPath(SystemParametersUtil.UNIVERSITY_LOGO_PATH3);
        Image image = Image.getInstance(imagePath);
        image.scaleToFit(80, 80);
        image.setAbsolutePosition(40, doc.getPageSize().getHeight() - image.getScaledHeight() - 20);
        doc.add(image); // Agregar la imagen al documento

        Horario[][] horarioMatrix = CommonHorarioUtil.getHorario(id, modList, cursoList, userType);
        String hor;

        Paragraph p1 = new Paragraph("UNIVERSIDAD DE SANTIAGO DE CHILE\n", fontTitle);
        Paragraph p2 = new Paragraph(title + "\n", fontMed);
        Paragraph p3 = new Paragraph(userName + "\n\n", fontMed);
        p1.setAlignment(Paragraph.ALIGN_CENTER);
        p2.setAlignment(Paragraph.ALIGN_CENTER);
        p3.setAlignment(Paragraph.ALIGN_CENTER);
        doc.add(p1);
        doc.add(p2);
        doc.add(p3);

        BaseFont baseFont = BaseFont.createFont(getServletContext().getRealPath("/fonts/local/tahoma.ttf"), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED); // Cambia la ruta de la fuente si es neces
        PdfContentByte canvas = writer.getDirectContent();
        canvas.beginText();
        canvas.setFontAndSize(baseFont, 7); // Usa la fuente que ya tienes definida
        canvas.showTextAligned(PdfContentByte.ALIGN_LEFT, getDate(DATE_FULL_FORMAT), doc.getPageSize().getWidth() - 120, doc.getPageSize().getHeight() - 20, 0);
        canvas.endText();

        // Crear tabla para días de la semana
        PdfPTable headerTable = new PdfPTable(new float[]{1, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f});
        headerTable.setWidthPercentage(100);
        PdfPCell emptyCell = new PdfPCell();
        emptyCell.setBorderWidth(0);
        headerTable.addCell(emptyCell);

        ContextUtil.getDiaList().forEach(dia -> {
            PdfPCell cell = new PdfPCell(new Paragraph(dia.getDiaNom(), fontMed));
            cell.setFixedHeight(25f);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            cell.setBackgroundColor(new Color(230, 230, 230));
            cell.setBorderWidth(5);
            cell.setBorderColor(Color.WHITE);
            headerTable.addCell(cell);
        });

        doc.add(headerTable);
        doc.add(new Paragraph("\n", dummy));

        // Crear bloques de módulos
        for (int block = 0; block < 3; block++) {
            PdfPTable blockTable = new PdfPTable(new float[]{1, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f});
            blockTable.setWidthPercentage(100);

            // Agregar módulos y celdas de horario
            for (int i = 0; i < 3; i++) {
                int modIndex = block * 3 + i;
                PdfPCell modCell = new PdfPCell(new Paragraph((modIndex + 1) + "  " + modList.get(modIndex).getModDesde() + ":" + modList.get(modIndex).getModHasta(), font));
                modCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                modCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                modCell.setBorderWidth(1);
                modCell.setFixedHeight(30f);
                blockTable.addCell(modCell);

                // Calcular altura máxima
                float maxHeight = 40f;

                for (int j = 0; j < 6; j++) {
                    PdfPTable nestedTable = new PdfPTable(1);
                    nestedTable.setWidthPercentage(100);

                    hor = horarioMatrix[modIndex][j] == null ? "" : horarioMatrix[modIndex][j].getCurso().getNombreHorario();
                    String sala = (horarioMatrix[modIndex][j] == null || horarioMatrix[modIndex][j].getSala() == null) ? "" : horarioMatrix[modIndex][j].getSala().getSalaNum();

                    PdfPCell courseCell = new PdfPCell(new Paragraph(hor, font));
                    courseCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    courseCell.setBorderWidth(0);

                    PdfPCell roomCell = new PdfPCell(new Paragraph(sala, font));
                    roomCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    roomCell.setBorderWidth(0);

                    nestedTable.addCell(courseCell);
                    nestedTable.addCell(roomCell);

                    PdfPCell horarioCell = new PdfPCell(nestedTable);
                    horarioCell.setBorderWidth(1);
                    horarioCell.setFixedHeight(maxHeight);
                    blockTable.addCell(horarioCell);
                }
            }

            doc.add(blockTable);
            doc.add(new Paragraph("\n", dummy));
        }

        PdfPTable cursosTable = new PdfPTable(new float[]{2.5f, 6f, 1f, 4f, 6f});
        cursosTable.setWidthPercentage(100);
        cursoList.forEach(curso -> {
            Stream.of(
                    new PdfPCell(new Paragraph(curso.getCodigo(" "), font)),
                    new PdfPCell(new Paragraph(curso.getCurNombre(), font)),
                    new PdfPCell(new Paragraph(curso.getCurHorario(), font)),
                    new PdfPCell(new Paragraph(curso.getCurSalas(), font)),
                    new PdfPCell(new Paragraph(curso.getCurProfesores(), font))
            ).forEach(cell -> {
                cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                cell.setBorderWidth(0);
                cursosTable.addCell(cell);
            });
        });

        doc.add(cursosTable);
    }

    public static String getColorHexPorAsignatura(Integer asignatura) {
        return ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getColorHexPorAsignatura(asignatura);
    }

    /**
     * Method description
     *
     * @param modList
     * @param sala
     * @param agno
     * @param sem
     * @return
     */
    public static Horario[][] getHorario(List<ModuloHorario> modList, Sala sala, Integer agno, Integer sem) {
        // Obtener la lista de horarios
        List<Horario> horarioList = ContextUtil.getDAO()
                .getHorarioPersistence(ActionUtil.getDBUser())
                .findSalas(sala, agno, sem);

        int dias = ContextUtil.getDiaList().size();
        int modulos = modList.size();

        // Crear el arreglo bidimensional de horarios
        Horario[][] horarioReturn = new Horario[modulos][dias];

        // Crear un mapa de día a índice para acceso rápido
        Map<String, Integer> diaIndexMap = ContextUtil.getDiaList().stream()
                .collect(Collectors.toMap(Dia::getDiaCod, dia -> dia.getDiaCorrel() - 1));

        // Llenar el arreglo de horarios
        horarioList.forEach(horario -> {
            Integer moduloAux = horario.getId().getHorModulo() - 1;
            Integer diaAux = diaIndexMap.getOrDefault(horario.getId().getHorDia(), -1);

            if (moduloAux >= 0 && moduloAux < modulos && diaAux >= 0 && diaAux < dias) {
                horarioReturn[moduloAux][diaAux] = horario;
            }
        });

        return horarioReturn;
    }

    public static List<ModuloHorario> getModuloHorarioList(List<Curso> cursoList) {
        // Encontrar el máximo valor de "10 * CurAgno + CurSem" usando Streams
        int max = cursoList.stream()
                .mapToInt(curso -> 10 * curso.getId().getCurAgno() + curso.getId().getCurSem())
                .max()
                .orElse(0);

        // Calcular año y semestre
        int agno = max / 10;
        int sem = max % 10;

        // Retornar la lista de ModuloHorario
        return ContextUtil.getDAO()
                .getModuloHorarioPersistence(ActionUtil.getDBUser())
                .findDocencia(agno, sem);
    }

    private static boolean muestra(String id, char clase) {
        switch (id) {
            case "AL":
                return true;
            case "PR":
                return 'C' == clase;
            case "AY":
                return 'A' == clase;
            default:
                return false;
        }
    }

    public static List<ModuloHorario> getModuloHorarioDocencia(Integer agno, Integer sem) {
        return ContextUtil.getDAO().getModuloHorarioPersistence(ActionUtil.getDBUser()).findDocencia(agno, sem);
    }

    public static List<ModuloHorario> getModuloHorarioAll(String inicio, String termino) {
        return ContextUtil.getDAO().getModuloHorarioPersistence(ActionUtil.getDBUser()).findAll(inicio, termino);
    }

    public static List<Curso> getCarga(WorkSession ws) {
        List<Curso> lCurso = new ArrayList<>(ws.getCarga());
        switch (ws.getIdHorario()) {
            case "PR":
                lCurso.addAll(ContextUtil.getDAO().getAyudantePersistence(ActionUtil.getDBUser()).findCarga(
                        ws.getProfesor().getProfRut()));
                break;
            case "AL":
                break;
            case "AY":
                break;
            default:

        }

        return lCurso;
    }
}
