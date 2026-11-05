package com.example.restaurante.ui.detalle;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.restaurante.AplicacionRestaurante;
import com.example.restaurante.datos.repositorio.PlatilloRepositorio;
import com.example.restaurante.datos.repositorio.RestauranteRepositorio;
import com.example.restaurante.bd.entidad.PlatilloEntidad;
import com.example.restaurante.bd.entidad.RestauranteEntidad;

import java.util.List;

public class DetalleRestauranteVistaModelo extends AndroidViewModel {

    private final RestauranteRepositorio restauranteRepositorio;
    private final PlatilloRepositorio platilloRepositorio;
    private final MutableLiveData<String> consultaBusqueda = new MutableLiveData<>("");
    private int idRestaurante;

    public DetalleRestauranteVistaModelo(@NonNull Application aplicacion) {
        super(aplicacion);
        AplicacionRestaurante app = (AplicacionRestaurante) aplicacion;
        restauranteRepositorio = new RestauranteRepositorio(app.getBaseDeDatos().restauranteDao());
        platilloRepositorio = new PlatilloRepositorio(app.getBaseDeDatos().platilloDao());
    }

    public void establecerIdRestaurante(int id) {
        this.idRestaurante = id;
    }

    public LiveData<RestauranteEntidad> getRestaurante() {
        return restauranteRepositorio.obtenerPorId(idRestaurante);
    }

    public LiveData<List<PlatilloEntidad>> getPlatillos(String categoria) {
        return Transformations.switchMap(consultaBusqueda, consulta -> {
            if (consulta == null || consulta.trim().isEmpty()) {
                return platilloRepositorio.obtenerPorCategoria(idRestaurante, categoria);
            } else {
                return platilloRepositorio.buscarEnCategoria(idRestaurante, categoria, consulta.trim());
            }
        });
    }

    public void establecerConsultaBusqueda(String consulta) {
        consultaBusqueda.setValue(consulta);
    }
}
