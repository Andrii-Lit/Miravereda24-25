package es.ieslavereda.miravereda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import es.ieslavereda.miravereda.Model.Contenido;

/**
 * Adaptador para RecyclerView que muestra una lista de objetos Contenido.
 */
public class AdaptadorRV extends RecyclerView.Adapter<AdaptadorRV.ViewHolder> {
    private List<Contenido> contenidos;
    private LayoutInflater inflater;
    private View.OnClickListener onClickListener;

    /**
     * Constructor del adaptador.
     *
     * @param context Contexto donde se usará el adaptador.
     * @param contenidos Lista de contenidos a mostrar.
     * @param onClickListener Listener para manejar clicks en los items.
     */
    public AdaptadorRV(Context context, List<Contenido> contenidos, View.OnClickListener onClickListener) {
        this.contenidos = contenidos;
        this.onClickListener = onClickListener;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Crea y devuelve un nuevo ViewHolder que contiene la vista del item.
     *
     * @param parent El ViewGroup en el que la nueva vista será añadida después de ser ligada a
     *               una posición del adaptador.
     * @param viewType Tipo de vista del nuevo View.
     * @return Un nuevo ViewHolder que contiene la vista del item.
     */
    @NonNull
    @Override
    public AdaptadorRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.viewholder_layout, parent, false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }

    /**
     * Enlaza los datos del contenido a la vista en la posición indicada.
     *
     * @param holder El ViewHolder que debe ser actualizado para representar los datos del
     *               elemento en la posición dada.
     * @param position La posición del elemento dentro del conjunto de datos del adaptador.
     */
    @Override
    public void onBindViewHolder(@NonNull AdaptadorRV.ViewHolder holder, int position) {
        Contenido contenido = contenidos.get(position);
        holder.titulo.setText(contenido.getTitulo());
        Picasso.get().load(contenido.getPoster_path()).into(holder.poster);
        holder.notaMediaValor.setText(String.valueOf(contenido.getPuntuacion_media()));
    }

    /**
     * Devuelve el número total de elementos en la lista.
     *
     * @return Cantidad de elementos.
     */
    @Override
    public int getItemCount() {
        return contenidos.size();
    }

    /**
     * Clase ViewHolder que mantiene referencias a las vistas de cada item para mejorar rendimiento.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView titulo;
        TextView notaMediaValor;

        /**
         * Constructor del ViewHolder.
         *
         * @param itemView Vista del item.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.Poster);
            titulo = itemView.findViewById(R.id.Titulo);
            notaMediaValor = itemView.findViewById(R.id.NotaMediaNumero);
        }
    }
}