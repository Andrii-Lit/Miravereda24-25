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

import es.ieslavereda.miravereda.Model.Contenido;

public class AdaptadorRV extends RecyclerView.Adapter<AdaptadorRV.ViewHolder> {
    private List<Contenido> contenidos;
    private LayoutInflater inflater;
    private View.OnClickListener onClickListener;

    public AdaptadorRV(Context context, List<Contenido> contenidos, View.OnClickListener onClickListener){
        this.contenidos= contenidos;
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
        Contenido contenido =contenidos.get(position);
        holder.titulo.setText(contenido.getTitulo());
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
            imageView = itemView.findViewById(R.id.Poster); // Nombre corregido
            titulo = itemView.findViewById(R.id.Titulo);
        }
    }

}