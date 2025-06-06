package es.ieslavereda.miravereda.API;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Adaptador personalizado para serializar y deserializar objetos LocalDateTime con Gson.
 * Utiliza el formato ISO_LOCAL_DATE_TIME (ej. "2025-06-06T15:30:00").
 */
public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Escribe un objeto LocalDateTime como una cadena en formato ISO.
     *
     * @param out   JsonWriter para escribir la salida.
     * @param value Objeto LocalDateTime a convertir (puede ser null).
     * @throws IOException Si ocurre un error de escritura.
     */
    @Override
    public void write(JsonWriter out, LocalDateTime value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.value(value.format(formatter));
    }

    /**
     * Lee una cadena JSON y la convierte en un objeto LocalDateTime.
     *
     * @param in JsonReader para leer la entrada.
     * @return Objeto LocalDateTime correspondiente.
     * @throws IOException Si ocurre un error de lectura.
     */
    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        String str = in.nextString();
        return LocalDateTime.parse(str, formatter);
    }
}