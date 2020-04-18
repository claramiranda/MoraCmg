package br.unicamp.ft.c155041.moracmg.ui.moradias;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.unicamp.ft.c155041.moracmg.DatabaseHandler;
import br.unicamp.ft.c155041.moracmg.R;
import br.unicamp.ft.c155041.moracmg.Usuario;

public class MoradiasFragment extends Fragment {
    private MoradiasViewModel moradiaViewModel;
    public final static String TAG = "MoradiasFragment";



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        moradiaViewModel =
                ViewModelProviders.of(this).get(MoradiasViewModel.class);
        View view = inflater.inflate(R.layout.fragment_moradias, container, false);

        return view;
    }

}