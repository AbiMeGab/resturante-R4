package com.example.restaurante.bd.entidad;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
    tableName = "platillos",
    foreignKeys = @ForeignKey(
        entity = RestauranteEntidad.class,
        parentColumns = "id",
        childColumns = "id_restaurante",
        onDelete = ForeignKey.CASCADE
    ),
    indices = @Index("id_restaurante")
)
public class PlatilloEntidad {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "id_restaurante")
    private int idRestaurante;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "precio")
    private double precio;

    @ColumnInfo(name = "descripcion")
    private String descripcion;

    @ColumnInfo(name = "categoria")
    private String categoria;

    @ColumnInfo(name = "nombre_recurso_imagen")
    private String nombreRecursoImagen;

    public PlatilloEntidad(int idRestaurante, String nombre, double precio, String descripcion,
                           String categoria, String nombreRecursoImagen) {
        this.idRestaurante = idRestaurante;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.nombreRecursoImagen = nombreRecursoImagen;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdRestaurante() { return idRestaurante; }
    public void setIdRestaurante(int idRestaurante) { this.idRestaurante = idRestaurante; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getNombreRecursoImagen() { return nombreRecursoImagen; }
    public void setNombreRecursoImagen(String nombreRecursoImagen) { this.nombreRecursoImagen = nombreRecursoImagen; }
}
