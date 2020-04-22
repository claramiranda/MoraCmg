package br.unicamp.ft.c155041.moracmg.ui.moradias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.ft.c155041.moracmg.R;
import br.unicamp.ft.c155041.moracmg.classes.CardMoradiaAdapter;
import br.unicamp.ft.c155041.moracmg.classes.Moradia;

public class MoradiasFragment extends Fragment {
    private MoradiasViewModel moradiaViewModel;
    public final static String TAG = "MoradiasFragment";

    private List<Moradia> moradiaList;
    private RecyclerView recyclerView;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        moradiaViewModel =
                ViewModelProviders.of(this).get(MoradiasViewModel.class);
        View view = inflater.inflate(R.layout.fragment_moradias, container, false);

        //meu codigo daqui pra baixo
        recyclerView = view.findViewById(R.id.recycler_view_user_moradias);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        //todo arruma essa merda plmdds, tem que ler essas fita do banco
        //Pendência bug do paralelismo
        moradiaList = new ArrayList<>();

        moradiaList.add(
                new Moradia(0001,
                        "Rota Alternativa",
                        8, "Masculina",
                        "República masculina da FT, amigos e agitados. Vizinhos da rep hour",
                        4.5,
                        "próximo à pizzaria Castelli, 5 min FT e 10 min FCA a pé",
                        R.drawable.icon_house,
                        "Republica")
        );

        moradiaList.add(
                new Moradia(0002,
                        "Kisapê",
                        8, "Mista",
                        "Apartamento por formados trabalhadores que querem viver a vida do centro.",
                        2,
                        "Próximo ao mercado Assaí no centro, super bem localizado.",
                         R.drawable.icon_house,
                        "Apartamento")
        );

        moradiaList.add(
                new Moradia(0001,
                        "Rota Alternativa",
                        8, "Masculina",
                        "República masculina da FT, amigos e agitados. Vizinhos da rep hour",
                        4.5,
                        "próximo à pizzaria Castelli, 5 min FT e 10 min FCA a pé",
                         R.drawable.icon_house,
                        "Republica")
        );

        moradiaList.add(
                new Moradia(0002,
                        "Kisapê",
                        8, "Mista",
                        "Apartamento por formados trabalhadores que querem viver a vida do centro.",
                        2,
                        "Próximo ao mercado Assaí no centro, super bem localizado.",
                         R.drawable.icon_house,
                        "Apartamento")
        );

        moradiaList.add(
                new Moradia(0001,
                        "Rota Alternativa",
                        8, "Masculina",
                        "República masculina da FT, amigos e agitados. Vizinhos da rep hour",
                        4.5,
                        "próximo à pizzaria Castelli, 5 min FT e 10 min FCA a pé",
                         R.drawable.icon_house,
                        "Republica")
        );

        moradiaList.add(
                new Moradia(0002,
                        "Kisapê",
                        8, "Mista",
                        "Apartamento por formados trabalhadores que querem viver a vida do centro.",
                        2,
                        "Próximo ao mercado Assaí no centro, super bem localizado.",
                         R.drawable.icon_house,
                        "Apartamento")
        );



        CardMoradiaAdapter adapter = new CardMoradiaAdapter(view.getContext(), moradiaList);
        recyclerView.setAdapter(adapter);


        return view;
    }

}