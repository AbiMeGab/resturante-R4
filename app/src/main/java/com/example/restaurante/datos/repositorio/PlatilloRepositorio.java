package com.example.restaurante.datos.repositorio;

import androidx.lifecycle.LiveData;

import com.example.restaurante.bd.dao.PlatilloDao;
import com.example.restaurante.bd.entidad.PlatilloEntidad;

import java.util.List;

public class PlatilloRepositorio {

    private final PlatilloDao platilloDao;

    public PlatilloRepositorio(PlatilloDao platilloDao) {
        this.platilloDao = platilloDao;
    }

    public LiveData<List<PlatilloEntidad>> obtenerPorCategoria(int idRestaurante, String categoria) {
        return platilloDao.obtenerPorRestauranteYCategoria(idRestaurante, categoria);
    }

    public LiveData<List<PlatilloEntidad>> buscarEnCategoria(int idRestaurante, String categoria, String consulta) {
        return platilloDao.buscarEnCategoria(idRestaurante, categoria, consulta);
    }

    public LiveData<PlatilloEntidad> obtenerPorId(int id) {
        return platilloDao.obtenerPorId(id);
    }
}
