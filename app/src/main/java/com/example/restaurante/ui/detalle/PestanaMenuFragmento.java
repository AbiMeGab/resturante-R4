package com.example.restaurante.ui.detalle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.restaurante.databinding.FragmentMenuTabBinding;
import com.example.restaurante.ui.ConstantesNavegacion;
import com.example.restaurante.ui.detalleplatillo.ActividadDetallePlatillo;

public class PestanaMenuFragmento extends Fragment {

    private static final String ARG_CATEGORIA = "arg_categoria";

    private FragmentMenuTabBinding enlace;

    public static PestanaMenuFragmento nuevaInstancia(String categoria) {
        PestanaMenuFragmento fragmento = new PestanaMenuFragmento();
        Bundle argumentos = new Bundle();
        argumentos.putString(ARG_CATEGORIA, categoria);
        fragmento.setArguments(argumentos);
        return fragmento;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflador, @Nullable ViewGroup contenedor,
                             @Nullable Bundle estadoGuardado) {
        enlace = FragmentMenuTabBinding.inflate(inflador, contenedor, false);
        return enlace.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View vista, @Nullable Bundle estadoGuardado) {
        super.onViewCreated(vista, estadoGuardado);

        String categoria = requireArguments().getString(ARG_CATEGORIA);
        DetalleRestauranteVistaModelo vistaModelo =
                new ViewModelProvider(requireActivity()).get(DetalleRestauranteVistaModelo.class);

        PlatilloAdaptador adaptador = new PlatilloAdaptador(platillo -> {
            Intent intencion = new Intent(requireContext(), ActividadDetallePlatillo.class);
            intencion.putExtra(ConstantesNavegacion.EXTRA_ID_PLATILLO, platillo.getId());
            startActivity(intencion);
        });

        enlace.recyclerViewMenu.setLayoutManager(new LinearLayoutManager(requireContext()));
        enlace.recyclerViewMenu.setAdapter(adaptador);

        vistaModelo.getPlatillos(categoria).observe(getViewLifecycleOwner(), platillos -> {
            adaptador.submitList(platillos);
            enlace.textViewEmpty.setVisibility(
                    platillos == null || platillos.isEmpty() ? View.VISIBLE : View.GONE);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        enlace = null;
    }
}
