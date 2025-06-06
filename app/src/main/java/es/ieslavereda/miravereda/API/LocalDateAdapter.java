package es.ieslavereda.miravereda.API;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Adaptador personalizado para serializar y deserializar objetos LocalDate con Gson.
 * Utiliza el formato ISO_LOCAL_DATE (ej. "2024-06-06").
 */
public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Escribe un objeto LocalDate como una cadena en formato ISO.
     *
     * @param out   JsonWriter para escribir la salida.
     * @param value Objeto LocalDate a convertir (puede ser null).
     * @throws IOException Si ocurre un error de escritura.
     */
    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.value(value.format(formatter));
    }

    /**
     * Lee una cadena JSON y la convierte a un objeto LocalDate.
     *
     * @param in JsonReader para leer la entrada.
     * @return Objeto LocalDate correspondiente.
     * @throws IOException Si ocurre un error de lectura.
     */
    @Override
    public LocalDate read(JsonReader in) throws IOException {
        String str = in.nextString();
        return LocalDate.parse(str, formatter);
    }
}