package com.example.restaurante.bd.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.restaurante.bd.entidad.RestauranteEntidad;

import java.util.List;

@Dao
public interface RestauranteDao {

    @Query("SELECT * FROM restaurantes ORDER BY nombre ASC")
    LiveData<List<RestauranteEntidad>> obtenerTodos();

    @Query("SELECT * FROM restaurantes WHERE nombre LIKE '%' || :consulta || '%' ORDER BY nombre ASC")
    LiveData<List<RestauranteEntidad>> buscarPorNombre(String consulta);

    @Query("SELECT * FROM restaurantes WHERE id = :id")
    LiveData<RestauranteEntidad> obtenerPorId(int id);

    @Insert
    void insertarTodos(List<RestauranteEntidad> restaurantes);
}
