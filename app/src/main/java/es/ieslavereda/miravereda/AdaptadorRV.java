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

public class AdaptadorRV extends RecyclerView.Adapter<AdaptadorRV.ViewHolder> {
    private List<Contenido> contenidos;
    private LayoutInflater inflater;
    private View.OnClickListener onClickListener;

    public AdaptadorRV(Context context, List<Contenido> contenidos, View.OnClickListener onClickListener) {
        this.contenidos = contenidos;
        this.onClickListener = onClickListener;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param ViewType The view type of the new View.
     *
     * @return
     */
    @NonNull
    @Override
    public AdaptadorRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType) {
        View view = inflater.inflate(R.layout.viewholder_layout, parent, false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }

    /**
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull AdaptadorRV.ViewHolder holder, int position) {
        Contenido contenido = contenidos.get(position);
        holder.titulo.setText(contenido.getTitulo());
        Picasso.get().load(contenido.getPoster_path()).into(holder.poster);
        holder.notaMediaValor.setText(String.valueOf(contenido.getPuntuacion_media()));
    }

    //devuelve el número de elementos de la lista

    /**
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return contenidos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView titulo;
        TextView notaMediaValor; // Corregido a TextView

        /**
         *
         * @param itemView
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.Poster); // Nombre corregido
            titulo = itemView.findViewById(R.id.Titulo);
            notaMediaValor = itemView.findViewById(R.id.NotaMediaNumero);
        }
    }

}