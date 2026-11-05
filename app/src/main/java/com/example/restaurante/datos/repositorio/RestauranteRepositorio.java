package com.example.restaurante.datos.repositorio;

import androidx.lifecycle.LiveData;

import com.example.restaurante.bd.dao.RestauranteDao;
import com.example.restaurante.bd.entidad.RestauranteEntidad;

import java.util.List;

public class RestauranteRepositorio {

    private final RestauranteDao restauranteDao;

    public RestauranteRepositorio(RestauranteDao restauranteDao) {
        this.restauranteDao = restauranteDao;
    }

    public LiveData<List<RestauranteEntidad>> obtenerTodos() {
        return restauranteDao.obtenerTodos();
    }

    public LiveData<List<RestauranteEntidad>> buscar(String consulta) {
        return restauranteDao.buscarPorNombre(consulta);
    }

    public LiveData<RestauranteEntidad> obtenerPorId(int id) {
        return restauranteDao.obtenerPorId(id);
    }
}
