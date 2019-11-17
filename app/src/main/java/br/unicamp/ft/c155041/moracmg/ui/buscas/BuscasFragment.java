package br.unicamp.ft.c155041.moracmg.ui.buscas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import br.unicamp.ft.c155041.moracmg.R;

public class BuscasFragment extends Fragment {

    private BuscasViewModel buscasViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        buscasViewModel =
                ViewModelProviders.of(this).get(BuscasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_buscas, container, false);

        return root;
    }
}