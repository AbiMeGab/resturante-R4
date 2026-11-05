package com.example.restaurante.bd;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.restaurante.bd.dao.PlatilloDao;
import com.example.restaurante.bd.dao.RestauranteDao;
import com.example.restaurante.bd.entidad.PlatilloEntidad;
import com.example.restaurante.bd.entidad.RestauranteEntidad;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {RestauranteEntidad.class, PlatilloEntidad.class}, version = 1, exportSchema = false)
public abstract class BaseDeDatos extends RoomDatabase {

    public abstract RestauranteDao restauranteDao();
    public abstract PlatilloDao platilloDao();

    private static volatile BaseDeDatos INSTANCIA;
    public static final ExecutorService ejecutorBd = Executors.newFixedThreadPool(2);

    public static BaseDeDatos obtenerInstancia(Context contexto) {
        if (INSTANCIA == null) {
            synchronized (BaseDeDatos.class) {
                if (INSTANCIA == null) {
                    INSTANCIA = Room.databaseBuilder(
                            contexto.getApplicationContext(),
                            BaseDeDatos.class,
                            "restaurante_base_datos"
                    )
                    .addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase bd) {
                            super.onCreate(bd);
                            ejecutorBd.execute(() -> {
                                BaseDeDatos baseDeDatos = INSTANCIA;
                                if (baseDeDatos != null) {
                                    PobladorDatos.poblar(baseDeDatos);
                                }
                            });
                        }
                    })
                    .build();
                }
            }
        }
        return INSTANCIA;
    }
}
