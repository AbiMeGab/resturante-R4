package com.example.restaurante.ui.detalle;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import com.example.restaurante.R;
import com.example.restaurante.databinding.ActivityRestaurantDetailBinding;
import com.example.restaurante.ui.ConstantesNavegacion;
import com.google.android.material.tabs.TabLayoutMediator;

public class ActividadDetalleRestaurante extends AppCompatActivity {

    private ActivityRestaurantDetailBinding enlace;
    private DetalleRestauranteVistaModelo vistaModelo;

    private String[] titulosPestanas;

    @Override
    protected void onCreate(Bundle estadoGuardado) {
        super.onCreate(estadoGuardado);
        enlace = ActivityRestaurantDetailBinding.inflate(getLayoutInflater());
        setContentView(enlace.getRoot());

        titulosPestanas = new String[]{
                getString(R.string.tab_comida),
                getString(R.string.tab_bebidas),
                getString(R.string.tab_complementos)
        };

        setSupportActionBar(enlace.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        int idRestaurante = getIntent().getIntExtra(ConstantesNavegacion.EXTRA_ID_RESTAURANTE, -1);
        int indicePestana = getIntent().getIntExtra(ConstantesNavegacion.EXTRA_INDICE_PESTANA, -1);

        vistaModelo = new ViewModelProvider(this).get(DetalleRestauranteVistaModelo.class);
        vistaModelo.establecerIdRestaurante(idRestaurante);

        vistaModelo.getRestaurante().observe(this, restaurante -> {
            if (restaurante != null && getSupportActionBar() != null) {
                getSupportActionBar().setTitle(restaurante.getNombre());
            }
        });

        configurarPaginador();

        if (indicePestana >= 0 && indicePestana < titulosPestanas.length) {
            enlace.viewPager.post(() -> enlace.viewPager.setCurrentItem(indicePestana, false));
        }
    }

    private void configurarPaginador() {
        PaginadorMenuAdaptador adaptadorPaginador = new PaginadorMenuAdaptador(this);
        enlace.viewPager.setAdapter(adaptadorPaginador);

        new TabLayoutMediator(enlace.tabLayout, enlace.viewPager,
                (pestana, posicion) -> pestana.setText(titulosPestanas[posicion])
        ).attach();
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

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
