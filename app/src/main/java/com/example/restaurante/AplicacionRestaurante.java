package com.example.restaurante;

import android.app.Application;

import com.example.restaurante.bd.BaseDeDatos;

public class AplicacionRestaurante extends Application {

    private BaseDeDatos baseDeDatos;

    public BaseDeDatos getBaseDeDatos() {
        if (baseDeDatos == null) {
            baseDeDatos = BaseDeDatos.obtenerInstancia(this);
        }
        return baseDeDatos;
    }
}
