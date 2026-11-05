package com.example.restaurante.bd.entidad;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "restaurantes")
public class RestauranteEntidad {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "descripcion")
    private String descripcion;

    @ColumnInfo(name = "nombre_recurso_imagen")
    private String nombreRecursoImagen;

    public RestauranteEntidad(String nombre, String descripcion, String nombreRecursoImagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nombreRecursoImagen = nombreRecursoImagen;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getNombreRecursoImagen() { return nombreRecursoImagen; }
    public void setNombreRecursoImagen(String nombreRecursoImagen) { this.nombreRecursoImagen = nombreRecursoImagen; }
}
