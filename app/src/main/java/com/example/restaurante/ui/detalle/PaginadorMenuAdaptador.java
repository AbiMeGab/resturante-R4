package com.example.restaurante.ui.detalle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.restaurante.ui.ConstantesNavegacion;

public class PaginadorMenuAdaptador extends FragmentStateAdapter {

    private static final String[] CATEGORIAS = {
            ConstantesNavegacion.CATEGORIA_COMIDA,
            ConstantesNavegacion.CATEGORIA_BEBIDA,
            ConstantesNavegacion.CATEGORIA_COMPLEMENTO
    };

    public PaginadorMenuAdaptador(@NonNull FragmentActivity actividad) {
        super(actividad);
    }

    @NonNull
    @Override
    public Fragment createFragment(int posicion) {
        return PestanaMenuFragmento.nuevaInstancia(CATEGORIAS[posicion]);
    }

    @Override
    public int getItemCount() {
        return CATEGORIAS.length;
    }
}
