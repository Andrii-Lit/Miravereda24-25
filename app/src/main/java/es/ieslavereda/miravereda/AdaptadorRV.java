package es.ieslavereda.miravereda;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

import es.ieslavereda.miravereda.Model.Posicion;

public class AdaptadorRV extends RecyclerView.Adapter<AdaptadorRV.ViewHolder> {
    private List<Posicion> contenidos;
    private LayoutInflater inflater;
    private View.OnClickListener onClickListener;

    public AdaptadorRV(Context context, List<Posicion> posicions, View.OnClickListener onClickListener){
        this.contenidos= posicions;
        this.onClickListener=onClickListener;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public AdaptadorRV.ViewHolder onCreateViewHolder (@NonNull ViewGroup parent,int ViewType){
        View view=inflater.inflate(R.layout.viewholder_layout,parent,false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorRV.ViewHolder holder, int position) {
        Posicion posicion =contenidos.get(position);
        holder.titulo.setText(posicion.getTitulo());
        holder.imageView.setImageResource(posicion.getPortadaImagen());
        holder.filmaffinity.setText(posicion.getFilmaffinity());
        holder.tomatometer.setText(posicion.getTomatometer());
        holder.popcornmeter.setText(posicion.getPopcornmeter());
    }

    //devuelve el número de elementos de la lista
    @Override
    public int getItemCount() {
        return contenidos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titulo;
        TextView filmaffinity; // Corregido a TextView
        TextView tomatometer;  // Corregido a TextView
        TextView popcornmeter; // Corregido a TextView

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView); // Nombre corregido
            titulo = itemView.findViewById(R.id.Titulo);
            filmaffinity = itemView.findViewById(R.id.Filmaffinity);
            tomatometer = itemView.findViewById(R.id.Tomatometer);
            popcornmeter = itemView.findViewById(R.id.Popcornmeter);
        }
    }

}