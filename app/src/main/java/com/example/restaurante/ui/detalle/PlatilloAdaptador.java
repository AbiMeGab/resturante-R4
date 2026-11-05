package com.example.restaurante.ui.detalle;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurante.databinding.ItemMenuBinding;
import com.example.restaurante.bd.entidad.PlatilloEntidad;

import java.util.Locale;

public class PlatilloAdaptador extends ListAdapter<PlatilloEntidad, PlatilloAdaptador.PlatilloContenedor> {

    public interface EscuchadorClicPlatillo {
        void alHacerClic(PlatilloEntidad platillo);
    }

    private final EscuchadorClicPlatillo escuchador;

    public PlatilloAdaptador(EscuchadorClicPlatillo escuchador) {
        super(COMPARADOR);
        this.escuchador = escuchador;
    }

    private static final DiffUtil.ItemCallback<PlatilloEntidad> COMPARADOR =
            new DiffUtil.ItemCallback<PlatilloEntidad>() {
                @Override
                public boolean areItemsTheSame(@NonNull PlatilloEntidad anterior, @NonNull PlatilloEntidad nuevo) {
                    return anterior.getId() == nuevo.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull PlatilloEntidad anterior, @NonNull PlatilloEntidad nuevo) {
                    return anterior.getNombre().equals(nuevo.getNombre())
                            && anterior.getPrecio() == nuevo.getPrecio();
                }
            };

    @NonNull
    @Override
    public PlatilloContenedor onCreateViewHolder(@NonNull ViewGroup padre, int tipoVista) {
        ItemMenuBinding enlace = ItemMenuBinding.inflate(
                LayoutInflater.from(padre.getContext()), padre, false);
        return new PlatilloContenedor(enlace);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatilloContenedor contenedor, int posicion) {
        contenedor.vincular(getItem(posicion));
    }

    class PlatilloContenedor extends RecyclerView.ViewHolder {

        private final ItemMenuBinding enlace;

        PlatilloContenedor(ItemMenuBinding enlace) {
            super(enlace.getRoot());
            this.enlace = enlace;
        }

        void vincular(PlatilloEntidad platillo) {
            enlace.textViewItemName.setText(platillo.getNombre());
            enlace.textViewItemDescription.setText(platillo.getDescripcion());
            enlace.textViewItemPrice.setText(String.format(Locale.getDefault(), "$%.2f", platillo.getPrecio()));

            itemView.setOnClickListener(v -> escuchador.alHacerClic(platillo));
        }
    }
}
