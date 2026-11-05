package com.example.restaurante.ui.restaurantes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.restaurante.R;
import com.example.restaurante.databinding.ActivityMainBinding;
import com.example.restaurante.bd.entidad.RestauranteEntidad;
import com.example.restaurante.ui.ConstantesNavegacion;
import com.example.restaurante.ui.detalle.ActividadDetalleRestaurante;

public class ActividadPrincipal extends AppCompatActivity {

    private ActivityMainBinding enlace;
    private RestauranteVistaModelo vistaModelo;
    private RestauranteAdaptador adaptador;

    @Override
    protected void onCreate(Bundle estadoGuardado) {
        super.onCreate(estadoGuardado);
        enlace = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(enlace.getRoot());

        setSupportActionBar(enlace.toolbar);

        vistaModelo = new ViewModelProvider(this).get(RestauranteVistaModelo.class);

        configurarListaReciclable();
        observarDatos();
    }

    private void configurarListaReciclable() {
        adaptador = new RestauranteAdaptador(new RestauranteAdaptador.EscuchadorClicRestaurante() {
            @Override
            public void alHacerClic(RestauranteEntidad restaurante) {
                navegarADetalle(restaurante.getId(), -1);
            }

            @Override
            public void alMantenerPresionado(RestauranteEntidad restaurante, View ancla) {
                mostrarMenuContextual(restaurante, ancla);
            }
        });

        enlace.recyclerViewRestaurants.setLayoutManager(new LinearLayoutManager(this));
        enlace.recyclerViewRestaurants.setAdapter(adaptador);
    }

    private void observarDatos() {
        vistaModelo.getRestaurantes().observe(this, restaurantes -> {
            adaptador.submitList(restaurantes);
            enlace.textViewEmpty.setVisibility(
                    restaurantes == null || restaurantes.isEmpty() ? View.VISIBLE : View.GONE);
        });
    }

    private void mostrarMenuContextual(RestauranteEntidad restaurante, View ancla) {
        PopupMenu menuEmergente = new PopupMenu(this, ancla);
        menuEmergente.getMenuInflater().inflate(R.menu.menu_context_restaurant, menuEmergente.getMenu());
        menuEmergente.setOnMenuItemClickListener(elemento -> {
            int idElemento = elemento.getItemId();
            if (idElemento == R.id.context_comida) {
                navegarADetalle(restaurante.getId(), ConstantesNavegacion.PESTANA_COMIDA);
                return true;
            } else if (idElemento == R.id.context_bebida) {
                navegarADetalle(restaurante.getId(), ConstantesNavegacion.PESTANA_BEBIDAS);
                return true;
            } else if (idElemento == R.id.context_complementos) {
                navegarADetalle(restaurante.getId(), ConstantesNavegacion.PESTANA_COMPLEMENTOS);
                return true;
            }
            return false;
        });
        menuEmergente.show();
    }

    private void navegarADetalle(int idRestaurante, int indicePestana) {
        Intent intencion = new Intent(this, ActividadDetalleRestaurante.class);
        intencion.putExtra(ConstantesNavegacion.EXTRA_ID_RESTAURANTE, idRestaurante);
        if (indicePestana >= 0) {
            intencion.putExtra(ConstantesNavegacion.EXTRA_INDICE_PESTANA, indicePestana);
        }
        startActivity(intencion);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem elementoBusqueda = menu.findItem(R.id.action_search);
        SearchView vistaBusqueda = (SearchView) elementoBusqueda.getActionView();
        if (vistaBusqueda != null) {
            vistaBusqueda.setQueryHint(getString(R.string.search_hint));
            vistaBusqueda.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String consulta) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String nuevoTexto) {
                    vistaModelo.establecerConsultaBusqueda(nuevoTexto);
                    return true;
                }
            });
        }
        return true;
    }
}
