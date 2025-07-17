package infrastructure.util;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

import javax.servlet.ServletContext;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FontsPDFUtil {

    private static final String FUENTE_TAHOMA = "tahoma";
    private static final String FUENTE_TIMES = "times";

    private final Map<String, BaseFont> fuentesBase;

    public FontsPDFUtil(ServletContext ctx) {
        try {
            this.fuentesBase = cargarFuentes(ctx);
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar fuentes PDF", e);
        }
    }

    private Map<String, BaseFont> cargarFuentes(ServletContext ctx) throws Exception {
        Map<String, BaseFont> fuentes = new HashMap<>();

        String basePathStr = ctx.getRealPath("/fonts/local");
        if (basePathStr == null) return Collections.emptyMap();

        Path basePath = Paths.get(basePathStr).toRealPath();

        cargarFuente(ctx, basePath, "tahoma.ttf", FUENTE_TAHOMA, fuentes);
        cargarFuente(ctx, basePath, "times.ttf", FUENTE_TIMES, fuentes);

        return fuentes;
    }

    private void cargarFuente(ServletContext ctx, Path basePath, String nombreArchivo, String clave, Map<String, BaseFont> mapaFuentes) throws Exception {
        String rutaStr = ctx.getRealPath("/fonts/local/" + nombreArchivo);
        if (rutaStr == null) return;

        Path ruta = Paths.get(rutaStr).toRealPath();

        // Seguridad: validar que esté dentro de la carpeta base
        if (!ruta.startsWith(basePath)) {
            throw new SecurityException("Ruta de fuente fuera del directorio permitido: " + ruta);
        }

        if (Files.exists(ruta)) {
            try (InputStream in = Files.newInputStream(ruta);
                 ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, bytesRead);
                }

                byte[] bytes = baos.toByteArray();
                BaseFont bf = BaseFont.createFont(nombreArchivo, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, false, bytes, null);
                mapaFuentes.put(clave, bf);
            }
        }
    }

    public Font crearFont(String nombreFuente, float size, int estilo) {
        if (nombreFuente == null || nombreFuente.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la fuente no puede estar vacío.");
        }

        BaseFont bf = fuentesBase.get(nombreFuente.toLowerCase());
        if (bf == null) {
            throw new IllegalArgumentException("Fuente no soportada: " + nombreFuente);
        }

        return new Font(bf, size, estilo);
    }
}
