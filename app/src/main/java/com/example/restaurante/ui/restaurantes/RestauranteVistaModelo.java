package com.example.restaurante.ui.restaurantes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.restaurante.AplicacionRestaurante;
import com.example.restaurante.datos.repositorio.RestauranteRepositorio;
import com.example.restaurante.bd.entidad.RestauranteEntidad;

import java.util.List;

public class RestauranteVistaModelo extends AndroidViewModel {

    private final RestauranteRepositorio repositorio;
    private final MutableLiveData<String> consultaBusqueda = new MutableLiveData<>("");
    private final LiveData<List<RestauranteEntidad>> restaurantes;

    public RestauranteVistaModelo(@NonNull Application aplicacion) {
        super(aplicacion);
        AplicacionRestaurante app = (AplicacionRestaurante) aplicacion;
        repositorio = new RestauranteRepositorio(app.getBaseDeDatos().restauranteDao());

        restaurantes = Transformations.switchMap(consultaBusqueda, consulta -> {
            if (consulta == null || consulta.trim().isEmpty()) {
                return repositorio.obtenerTodos();
            } else {
                return repositorio.buscar(consulta.trim());
            }
        });
    }

    public LiveData<List<RestauranteEntidad>> getRestaurantes() {
        return restaurantes;
    }

    public void establecerConsultaBusqueda(String consulta) {
        consultaBusqueda.setValue(consulta);
    }
}
