package com.example.restaurante.ui.detalleplatillo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.restaurante.databinding.ActivityItemDetailBinding;
import com.example.restaurante.ui.ConstantesNavegacion;

import java.util.Locale;

public class ActividadDetallePlatillo extends AppCompatActivity {

    private ActivityItemDetailBinding enlace;

    @Override
    protected void onCreate(Bundle estadoGuardado) {
        super.onCreate(estadoGuardado);
        enlace = ActivityItemDetailBinding.inflate(getLayoutInflater());
        setContentView(enlace.getRoot());

        setSupportActionBar(enlace.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        int idPlatillo = getIntent().getIntExtra(ConstantesNavegacion.EXTRA_ID_PLATILLO, -1);

        DetallePlatilloVistaModelo vistaModelo =
                new ViewModelProvider(this).get(DetallePlatilloVistaModelo.class);

        vistaModelo.getPlatillo(idPlatillo).observe(this, platillo -> {
            if (platillo == null) return;

            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(platillo.getNombre());
            }

            enlace.textViewName.setText(platillo.getNombre());
            enlace.textViewPrice.setText(String.format(Locale.getDefault(), "$%.2f", platillo.getPrecio()));
            enlace.textViewDescription.setText(platillo.getDescripcion());

            int idRecurso = getResources().getIdentifier(
                    platillo.getNombreRecursoImagen(), "drawable", getPackageName());
            if (idRecurso != 0) {
                enlace.imageViewItem.setImageResource(idRecurso);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
