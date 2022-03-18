package com.example.caira15.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.caira15.databinding.FragmentInstruccionesBinding;
import com.example.caira15.fragments.descripcion.EstudiantesFragment;
import com.example.caira15.fragments.descripcion.FuturoFragment;
import com.example.caira15.fragments.descripcion.UniversidadFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmentInstruccionesBinding binding;


    public static Fragment newInstance(int index) {
        Fragment fragment = null;
        switch (index) {
            case 1:
                fragment = new FuturoFragment();
                break;
            case 2:
                fragment = new EstudiantesFragment();
                break;
            case 3:
                fragment = new UniversidadFragment();
                break;

        }
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentInstruccionesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}