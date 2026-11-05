package com.example.restaurante.bd.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.restaurante.bd.entidad.PlatilloEntidad;

import java.util.List;

@Dao
public interface PlatilloDao {

    @Query("SELECT * FROM platillos WHERE id_restaurante = :idRestaurante AND categoria = :categoria ORDER BY nombre ASC")
    LiveData<List<PlatilloEntidad>> obtenerPorRestauranteYCategoria(int idRestaurante, String categoria);

    @Query("SELECT * FROM platillos WHERE id_restaurante = :idRestaurante AND categoria = :categoria AND nombre LIKE '%' || :consulta || '%' ORDER BY nombre ASC")
    LiveData<List<PlatilloEntidad>> buscarEnCategoria(int idRestaurante, String categoria, String consulta);

    @Query("SELECT * FROM platillos WHERE id = :id")
    LiveData<PlatilloEntidad> obtenerPorId(int id);

    @Insert
    void insertarTodos(List<PlatilloEntidad> platillos);
}
