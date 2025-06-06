package es.ieslavereda.miravereda;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Locale;

import es.ieslavereda.miravereda.Base.ImageDownloader;
import es.ieslavereda.miravereda.Model.Contenido;
import es.ieslavereda.miravereda.Model.OnCarritoDeleteListener;

/**
 * Adaptador para RecyclerView que muestra una lista de contenidos añadidos al carrito.
 * Permite eliminar elementos mediante un botón en cada item y actualiza el total del carrito.
 */
public class CarritoAdaptadorRV extends RecyclerView.Adapter<CarritoAdaptadorRV.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Contenido> contenidos;
    private final Context context;
    private final TextView totalTextView;
    private final OnCarritoDeleteListener deleteListener;

    /**
     * Constructor del adaptador.
     *
     * @param context       Contexto de la aplicación.
     * @param contenidos    Lista de contenidos que se mostrarán.
     * @param totalTextView TextView donde se mostrará el total acumulado de precios.
     * @param deleteListener Listener que se ejecuta cuando se elimina un contenido.
     */
    public CarritoAdaptadorRV(Context context, List<Contenido> contenidos, TextView totalTextView, OnCarritoDeleteListener deleteListener) {
        this.context = context;
        this.contenidos = contenidos;
        this.totalTextView = totalTextView;
        this.deleteListener = deleteListener;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        actualizarTotal();
    }

    /**
     * Crea una nueva vista para un item del RecyclerView.
     *
     * @param parent   El ViewGroup padre donde se añadirá la vista.
     * @param viewType Tipo de la vista.
     * @return Un nuevo ViewHolder con la vista inflada.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.carrito_viewholder_layout, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Vincula los datos de un contenido con la vista del ViewHolder.
     *
     * @param holder   ViewHolder que contiene las vistas.
     * @param position Posición del elemento en la lista.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contenido contenido = contenidos.get(position);
        if (contenido == null) {
            holder.vh_carrito_tituloTV.setText("");
            holder.vh_carrito_directorTV.setText("");
            holder.vh_carrito_costeTV.setText("");
            holder.vh_carrito_portada.setImageDrawable(null);
            holder.vh_carrito_borrarButton.setOnClickListener(null);
            return;
        }

        holder.vh_carrito_tituloTV.setText(contenido.getTitulo());
        holder.vh_carrito_directorTV.setText(contenido.getNombre_dir());
        holder.vh_carrito_costeTV.setText(String.format(Locale.getDefault(), "%.2f €", contenido.getPrecio()));
        Log.d("CarritoAdaptadorRV", "Título: " + contenido.getTitulo() + ", Precio: " + contenido.getPrecio());
        ImageDownloader.downloadImage(contenido.getPoster_path(), holder.vh_carrito_portada);

        holder.vh_carrito_borrarButton.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition == RecyclerView.NO_POSITION) return;
            if (deleteListener != null) {
                deleteListener.onDelete(contenido, adapterPosition);
            }
        });
    }

    /**
     * Devuelve la cantidad de elementos en la lista.
     *
     * @return Número de elementos.
     */
    @Override
    public int getItemCount() {
        return contenidos.size();
    }

    /**
     * Calcula el total acumulado del precio de todos los contenidos.
     *
     * @return Total de precios sumados.
     */
    private double calcularTotal() {
        double total = 0;
        for (Contenido contenido : contenidos) {
            if (contenido != null) {
                total += contenido.getPrecio();
            }
        }
        return total;
    }

    /**
     * Actualiza el TextView que muestra el total del carrito con el valor calculado.
     */
    public void actualizarTotal() {
        if (totalTextView != null) {
            totalTextView.setText(String.format(Locale.getDefault(), "%.2f €", calcularTotal()));
        }
    }

    /**
     * ViewHolder que contiene las vistas de cada elemento del RecyclerView.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView vh_carrito_portada;
        FloatingActionButton vh_carrito_borrarButton;
        TextView vh_carrito_tituloTV, vh_carrito_directorTV, vh_carrito_costeTV;

        /**
         * Constructor que enlaza las vistas del layout con los atributos del ViewHolder.
         *
         * @param itemView Vista del elemento.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vh_carrito_portada = itemView.findViewById(R.id.vh_carrito_portada);
            vh_carrito_tituloTV = itemView.findViewById(R.id.vh_carrito_tituloTV);
            vh_carrito_directorTV = itemView.findViewById(R.id.vh_carrito_directorTV);
            vh_carrito_costeTV = itemView.findViewById(R.id.vh_carrito_costeTV);
            vh_carrito_borrarButton = itemView.findViewById(R.id.vh_carrito_borrarButton);
        }
    }
}