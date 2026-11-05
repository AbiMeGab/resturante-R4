package com.example.restaurante.ui.restaurantes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurante.databinding.ItemRestaurantBinding;
import com.example.restaurante.bd.entidad.RestauranteEntidad;

public class RestauranteAdaptador extends ListAdapter<RestauranteEntidad, RestauranteAdaptador.RestauranteContenedor> {

    public interface EscuchadorClicRestaurante {
        void alHacerClic(RestauranteEntidad restaurante);
        void alMantenerPresionado(RestauranteEntidad restaurante, View ancla);
    }

    private final EscuchadorClicRestaurante escuchador;

    public RestauranteAdaptador(EscuchadorClicRestaurante escuchador) {
        super(COMPARADOR);
        this.escuchador = escuchador;
    }

    private static final DiffUtil.ItemCallback<RestauranteEntidad> COMPARADOR =
            new DiffUtil.ItemCallback<RestauranteEntidad>() {
                @Override
                public boolean areItemsTheSame(@NonNull RestauranteEntidad anterior, @NonNull RestauranteEntidad nuevo) {
                    return anterior.getId() == nuevo.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull RestauranteEntidad anterior, @NonNull RestauranteEntidad nuevo) {
                    return anterior.getNombre().equals(nuevo.getNombre());
                }
            };

    @NonNull
    @Override
    public RestauranteContenedor onCreateViewHolder(@NonNull ViewGroup padre, int tipoVista) {
        ItemRestaurantBinding enlace = ItemRestaurantBinding.inflate(
                LayoutInflater.from(padre.getContext()), padre, false);
        return new RestauranteContenedor(enlace);
    }

    @Override
    public void onBindViewHolder(@NonNull RestauranteContenedor contenedor, int posicion) {
        RestauranteEntidad restaurante = getItem(posicion);
        contenedor.vincular(restaurante);
    }

    class RestauranteContenedor extends RecyclerView.ViewHolder {

        private final ItemRestaurantBinding enlace;

        RestauranteContenedor(ItemRestaurantBinding enlace) {
            super(enlace.getRoot());
            this.enlace = enlace;
        }

        void vincular(RestauranteEntidad restaurante) {
            enlace.textViewRestaurantName.setText(restaurante.getNombre());
            enlace.textViewRestaurantDescription.setText(restaurante.getDescripcion());

            int idRecurso = itemView.getContext().getResources().getIdentifier(
                    restaurante.getNombreRecursoImagen(), "drawable",
                    itemView.getContext().getPackageName());
            if (idRecurso != 0) {
                enlace.imageViewRestaurant.setImageResource(idRecurso);
            }

            itemView.setOnClickListener(v -> escuchador.alHacerClic(restaurante));
            itemView.setOnLongClickListener(v -> {
                escuchador.alMantenerPresionado(restaurante, v);
                return true;
            });
        }
    }
}
