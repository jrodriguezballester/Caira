package com.example.caira15.ui.main;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.caira15.R;
import com.example.caira15.databinding.ActivityInstruccionesBinding;
import com.example.caira15.ui.main.SectionsPagerAdapter;

public class InstruccionesActivity extends AppCompatActivity {

    private ActivityInstruccionesBinding binding;
    ViewPager viewPager;
    private LinearLayout linearPuntos;
    private TextView[] puntosSlide;
    private final Integer numFragments=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInstruccionesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);

        linearPuntos = binding.idLinearPuntos;
        agregarIndicadorPuntos(0);
        viewPager.addOnPageChangeListener(viewListener);


    }

    private void agregarIndicadorPuntos(int pos) {
        linearPuntos.removeAllViews();
        char uni = '\u0021';
        puntosSlide = new TextView[numFragments];// numero de fragments
        for (int i = 0; i < puntosSlide.length; i++) {
            puntosSlide[i] = new TextView(this);
            puntosSlide[i].setText("\u2022");
            puntosSlide[i].setTextSize(52);
            puntosSlide[i].setTextColor(getResources().getColor(R.color.BlancoTransparente, null));
            linearPuntos.addView(puntosSlide[i]);
        }
        // Marca la vista
        if (puntosSlide.length > 0) {
            puntosSlide[pos].setTextColor(getResources().getColor(R.color.Blanco, null));
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            agregarIndicadorPuntos(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}