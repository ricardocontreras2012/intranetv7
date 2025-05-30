package infrastructure.util;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import javax.servlet.ServletContext;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FontsPDFUtil {

    // Constantes para los nombres de las fuentes
    private static final String FUENTE_TAHOMA = "tahoma";
    private static final String FUENTE_TIMES = "times";

    private final Map<String, BaseFont> fuentesBase = new HashMap<>();

    public FontsPDFUtil(ServletContext ctx) {
        try {
            cargarFuentes(ctx);
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar fuentes PDF", e);
        }
    }

    private void cargarFuentes(ServletContext ctx) throws Exception {
        // Validamos si las rutas son correctas antes de cargarlas
        String rutaTahoma = ctx.getRealPath("/fonts/local/tahoma.ttf");
        if (rutaTahoma != null && !rutaTahoma.isEmpty() && new File(rutaTahoma).exists()) {
            fuentesBase.put(FUENTE_TAHOMA, BaseFont.createFont(rutaTahoma, BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
        }

        String rutaTimes = ctx.getRealPath("/fonts/local/times.ttf");
        if (rutaTimes != null && !rutaTimes.isEmpty() && new File(rutaTimes).exists()) {
            fuentesBase.put(FUENTE_TIMES, BaseFont.createFont(rutaTimes, BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
        }
    }

    public Font crearFont(String nombreFuente, float tamaño, int estilo) {
        if (nombreFuente == null || nombreFuente.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la fuente no puede estar vacío.");
        }

        // Convertimos el nombre de la fuente a minúsculas
        nombreFuente = nombreFuente.toLowerCase();
        BaseFont bf = fuentesBase.get(nombreFuente);
        
        if (bf == null) {
            throw new IllegalArgumentException("Fuente no soportada: " + nombreFuente);
        }

        return new Font(bf, tamaño, estilo);
    }
}

