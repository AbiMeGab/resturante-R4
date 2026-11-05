package com.example.restaurante.ui.detalleplatillo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.restaurante.AplicacionRestaurante;
import com.example.restaurante.datos.repositorio.PlatilloRepositorio;
import com.example.restaurante.bd.entidad.PlatilloEntidad;

public class DetallePlatilloVistaModelo extends AndroidViewModel {

    private final PlatilloRepositorio repositorio;

    public DetallePlatilloVistaModelo(@NonNull Application aplicacion) {
        super(aplicacion);
        AplicacionRestaurante app = (AplicacionRestaurante) aplicacion;
        repositorio = new PlatilloRepositorio(app.getBaseDeDatos().platilloDao());
    }

    public LiveData<PlatilloEntidad> getPlatillo(int id) {
        return repositorio.obtenerPorId(id);
    }
}
